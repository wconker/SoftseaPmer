package com.android.softsea.ui.Auth;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.softsea.R;
import com.android.softsea.base.BaseActivity;
import com.android.softsea.callback.MessageCallBack;
import com.android.softsea.newwork.MessageCenter;
import com.android.softsea.ui.MainActivity;
import com.android.softsea.utils.CommonUtil;
import com.android.softsea.utils.JSONUtils;
import com.android.softsea.utils.SharedPrefsUtil;

import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;

public class Login extends BaseActivity implements MessageCallBack {


    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.iv_icon_left)
    ImageView mLeftLogo;
    @Bind(R.id.iv_icon_right)
    ImageView mRightLogo;
    @Bind(R.id.et_username)
    EditText et_username;
    @Bind(R.id.et_password)
    EditText et_password;
    private Context mContext=this;

    private Observer<String> ober;
    private MessageCenter messageCenter;

    @Override
    public void setButterKnife() {

        ButterKnife.bind(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.ui_auth_login;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

        if(!SharedPrefsUtil.getValue(this,"loginXML","UserName","").equals(""))
        {
            et_username.setText(SharedPrefsUtil.getValue(this,"loginXML","UserName",""));
            et_password.setText(SharedPrefsUtil.getValue(this,"loginXML","UserPWD",""));
        }

        messageCenter = new MessageCenter(this);
        et_username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                mLeftLogo.setImageResource(R.drawable.ic_22);
                mRightLogo.setImageResource(R.drawable.ic_33);
            }
        });
        et_password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                mLeftLogo.setImageResource(R.drawable.ic_22_hide);
                mRightLogo.setImageResource(R.drawable.ic_33_hide);
            }
        });
        et_username.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 如果用户名清空了 清空密码 清空记住密码选项
                et_password.setText("");
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        ober = new Observer<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {

                JSONObject cmd = JSONUtils.StringToJSON(s);

                if (JSONUtils.getInt(cmd, "code", -1) == 0) {
                    login();
                }else {
                    Toast.makeText(mContext,JSONUtils.getString(cmd, "message"),Toast.LENGTH_SHORT).show();
                }
            }
        };
    }


    @OnClick({R.id.btn_login})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                //登录
                boolean isNetConnected = CommonUtil.isNetworkAvailable(this);
                if (!isNetConnected) {
                    return;
                }
                messageCenter.SendYouMessage(messageCenter.ChooseCommand().Login(et_username.getText().toString(), et_password.getText().toString()));
                break;

        }
    }

    private void login() {
        SharedPrefsUtil.putValue(this, "versionXML", "Version", "1.0");
        SharedPrefsUtil.putValue(this, "loginXML", "UserName", et_username.getText().toString());
        SharedPrefsUtil.putValue(this, "loginXML", "UserPWD", et_password.getText().toString());
        startActivity(new Intent(Login.this, MainActivity.class));
        finish();
    }

    @Override
    public void onMessage(String str) {
        JSONObject cmd = JSONUtils.StringToJSON(str);
        if (JSONUtils.getString(cmd, "cmd").equals("login")) {
            Observable.just(str).observeOn(AndroidSchedulers.mainThread()).subscribe(ober);
        } else {

        }
    }

}





