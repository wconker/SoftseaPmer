package com.android.softsea.ui.Contact;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.softsea.R;
import com.android.softsea.adapter.MyAdapter.SortAdapter;
import com.android.softsea.base.BaseActivity;
import com.android.softsea.callback.MessageCallBack;
import com.android.softsea.entity.User;
import com.android.softsea.newwork.MessageCenter;
import com.android.softsea.utils.JSONUtils;
import com.android.softsea.utils.SideBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;

public class ContactList extends BaseActivity implements MessageCallBack {
    @Bind(R.id.back_btn)
    ImageView backBtn;
    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;
    private ListView listView;
    private SideBar sideBar;
    private ArrayList<User> list = new ArrayList<>();
    private static final int READ_PHONE_STATE = 100;
    private MessageCenter messageCenter;
    private Observer<String> observer;
    private Context context = this;

    @Override
    public void setButterKnife() {
        ButterKnife.bind(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.ui_contact_contact_list;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        ControlCenter();
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContactList.this.finish();
            }
        });
        toolbarTitle.setText("员工通讯录");
        messageCenter = new MessageCenter(this);
        messageCenter.SendYouMessage(messageCenter.ChooseCommand().getEmpList());
        initView();

    }

    @Override
    public void onMessage(String str) {

        Observable.just(str)
                .observeOn(AndroidSchedulers
                        .mainThread())
                .subscribe(observer);
    }


    private void ControlCenter() {

        observer = new Observer<String>() {
            @Override
            public void onCompleted() {


            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                JSONObject cmd = JSONUtils.StringToJSON(s);
                if (JSONUtils.getString(cmd, "cmd").equals("getEmpList")) {
                    JSONArray userDataList = JSONUtils.getJSONArray(cmd, "data");
                    for (int i = 0; i < userDataList.length(); i++) {

                        try {
                            JSONObject obj = userDataList.getJSONObject(i);
                            list.add(new User(obj.getString("XM"), obj.getString("MOBILE"), obj.getString("GW")));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    initData();
                }
            }
        };
    }

    private void initView() {
        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + list.get(i).getPhone()));
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    showContacts();
                    return;
                }
                startActivity(intent);
            }
        });
        sideBar = (SideBar) findViewById(R.id.side_bar);
        sideBar.setOnStrSelectCallBack(new SideBar.ISideBarSelectCallBack() {

            @Override
            public void onSelectStr(int index, String selectStr) {
                for (int i = 0; i < list.size(); i++) {
                    if (selectStr.equalsIgnoreCase(list.get(i).getFirstLetter())) {
                        listView.setSelection(i); // 选择到首字母出现的位置
                        return;
                    }
                }
            }
        });
    }

    private void initData() {
        Collections.sort(list); // 对list进行排序，需要让User实现Comparable接口重写compareTo方法
        SortAdapter adapter = new SortAdapter(this, list);
        listView.setAdapter(adapter);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    //检查权限，6.0以上需要动态检查权限
    public void showContacts() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
           // Toast.makeText(this, "没有权限,请手动开启定位权限", Toast.LENGTH_SHORT).show();
            // 申请一个（或多个）权限，并提供用于回调返回的获取码（用户定义）
            ActivityCompat.requestPermissions(this,
                    new String[]{
                            Manifest.permission.CALL_PHONE,  //为止
                            Manifest.permission.READ_PHONE_STATE}, //电话
                    READ_PHONE_STATE);
        }
        else
            {
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            // requestCode即所声明的权限获取码，在checkSelfPermission时传入
            case READ_PHONE_STATE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 获取到权限，作相应处理（调用定位SDK应当确保相关权限均被授权，否则可能引起定位失败）

                } else {
                    // 没有获取到权限，做特殊处理
                    Toast.makeText(this, "获取位置权限失败，请手动开启", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }
}
