package com.neroyang.leban.leban.Ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.loopj.android.http.RequestParams;
import com.neroyang.leban.leban.MainActivity;
import com.neroyang.leban.leban.Model.LoginModel;
import com.neroyang.leban.leban.R;
import com.neroyang.leban.leban.Tools.MToast;

/**
 * Created by nero on 2016/3/17.
 */
public class Login extends Activity {
    private Handler mHandler;
    private EditText tx_phone;
    private EditText tx_password;
    private LinearLayout bt_login;
    private  String phone;
    private String password;
    private LoginModel m_model;
    private RequestParams requestParams;
    private MToast mToast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        InitView();
    }

    private void InitView() {
        tx_phone = (EditText) findViewById(R.id.tx_phone);
        tx_password = (EditText) findViewById(R.id.tx_password);
        bt_login = (LinearLayout) findViewById(R.id.bt_login);
        m_model = new LoginModel();
        mToast = new MToast();
        requestParams = new RequestParams();
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone = tx_phone.getText().toString();
                password = tx_password.getText().toString();

                login_do(phone, password);
            }
        });
    }

    private void set_handler() {
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 3: {
                        //Log.v("主线程收到更新信息", m_model.getLuntanBeanList().get(1).getTitle());
                            Log.v("登录成功","");
                            SharedPreferences sharedPreferences = getSharedPreferences("islog_in", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
                            editor.putString("islogin", "1");
                            editor.commit();//提交修改
                            mToast.show(getApplicationContext(),m_model.getMsg(),true);
                            Intent luntanintent = new Intent(getApplicationContext(),MainActivity
                                    .class);
                            startActivity(luntanintent);
                        //Log.v("主线程UI更新完毕", m_model.getLuntanBeanList().get(1).getTitle());
                        break;
                    }
                    case 2:{
                        Log.v("登录失败",m_model.getMsg());
                        mToast.show(getApplicationContext(),m_model.getMsg(),false);
                        break;
                    }
                    case 4:{
                        mToast.show(getApplicationContext(),m_model.getMsg(),false);
                        break;
                    }
                    default:
                        break;
                }
            }
        };
        m_model.setHandler(mHandler);
    }
    private void login_do(String phn,String pwd) {
        Log.v("登录参数："+phone,password);
        set_handler();
        requestParams.put("uid",phn);
        requestParams.put("pwd",pwd);
        m_model.setRequestParams(requestParams);
        m_model.login_do();
    }
}
