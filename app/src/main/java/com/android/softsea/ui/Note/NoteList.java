package com.android.softsea.ui.Note;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.softsea.R;
import com.android.softsea.adapter.MyAdapter.MyRecyclerViewAdapter;
import com.android.softsea.base.BaseActivity;
import com.android.softsea.callback.MessageCallBack;
import com.android.softsea.entity.note.WorkBean;
import com.android.softsea.newwork.MessageCenter;
import com.android.softsea.utils.JSONUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;

public class NoteList extends BaseActivity implements View.OnClickListener, MessageCallBack {


    @Bind(R.id.back_btn)
    ImageView backBtn;
    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;
    @Bind(R.id.listView)
    RecyclerView list;
    @Bind(R.id.addNote)
    FloatingActionButton addNote;
    @Bind(R.id.swipe)
    SwipeRefreshLayout swipe;

    private Context context = this;
    private MessageCenter messageCenter;


    private Observer<String> observer;
    private WorkBean workBean;
    private MyRecyclerViewAdapter adapter;
    private List<WorkBean.DataBean> listdata = new ArrayList<>();


    @Override
    public void setButterKnife() {

        ButterKnife.bind(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.ui_note_note_list;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

        messageCenter = new MessageCenter(this);

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                messageCenter.SendYouMessage(messageCenter.ChooseCommand().getWorkingDynamics());
            }
        });
        toolbarTitle.setText("日志列表");
        backBtn.setOnClickListener(this);
        adapter = new MyRecyclerViewAdapter(this, listdata);
        list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        list.setAdapter(adapter);
        messageCenter.SendYouMessage(messageCenter.ChooseCommand().getWorkingDynamics());
        ControlCenter();
        addNote.setOnClickListener(this);

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
                if (JSONUtils.getString(cmd, "cmd").equals("getWorkingDynamics")) {
                    swipe.setRefreshing(false);
                    Gson gson = new Gson();
                    Type type = new TypeToken<WorkBean>() {
                    }.getType();
                    workBean = gson.fromJson(String.valueOf(cmd), type);
                    listdata.clear();
                    listdata.addAll(workBean.getData());
                    adapter.notifyDataSetChanged();
                }
            }
        };
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (observer == null) {
            Log.e("COnker", "他妈的为空了已经");
        } else {
            ControlCenter();
        }


        messageCenter.setCallBackInterFace(this);
        messageCenter.SendYouMessage(messageCenter.ChooseCommand().getWorkingDynamics());


    }

    void sendObj(String s) {
        Observable.just(s)
                .observeOn(AndroidSchedulers
                        .mainThread())
                .subscribe(observer);
    }

    @Override
    public void onMessage(String str) {
        sendObj(str);
    }


    @Override
    public void onClick(View view) {

        int id = view.getId();
        switch (id) {
            case R.id.back_btn:
                finish();
                break;
            case R.id.addNote:
                Intent i = new Intent(NoteList.this, EditNode.class);
                ActivityOptions optionsCompat = ActivityOptions.makeSceneTransitionAnimation(NoteList.this, addNote, "text");
                startActivity(i, optionsCompat.toBundle());
                break;

        }

    }


}
