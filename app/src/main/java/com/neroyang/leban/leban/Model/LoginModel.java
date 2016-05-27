package com.neroyang.leban.leban.Model;

import android.os.Handler;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import com.neroyang.leban.leban.Api.ApiClient;
import com.neroyang.leban.leban.Bean.MsgBean;
import com.neroyang.leban.leban.Ui.Login;


import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by nero on 2016/3/17.
 */
public class LoginModel {
    private Handler handler;
    private ApiClient loginClient;
    private String msg;
    private RequestParams requestParams;
    private MsgBean msgBeans;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public MsgBean getMsgBeans() {
        return msgBeans;
    }

    public void setMsgBeans(MsgBean msgBeans) {
        this.msgBeans = msgBeans;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public ApiClient getLoginClient() {
        return loginClient;
    }

    public void setLoginClient(ApiClient loginClient) {
        this.loginClient = loginClient;
    }

    public RequestParams getRequestParams() {
        return requestParams;
    }

    public void setRequestParams(RequestParams requestParams) {
        this.requestParams = requestParams;
    }
    public LoginModel(){
        loginClient = new ApiClient();
       // msgBeans = new ArrayList<MsgBean>();
    }
    public void login_do(){
        Log.v("HTTP:","请求"+ loginClient.getBase_url()+loginClient.getLuntan_list_url()+"中,参数:"+requestParams);
        loginClient.post(loginClient.getBase_url() + loginClient.getUser_login_url(), this.requestParams, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.v("failed", responseString);
                msg = "登陆错误";
                handler.sendEmptyMessage(4);//登录失败

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.v("success", responseString);
                Gson gson = new Gson();
                msgBeans = gson.fromJson(responseString,MsgBean.class);
                if(msgBeans.getStatus().equals("error")) {
                    msg = msgBeans.getMsg();
                    handler.sendEmptyMessage(2);//登录失败
                }else if(msgBeans.getStatus().equals("success")){
                    msg = msgBeans.getMsg();
                    handler.sendEmptyMessage(3);//登录成功
                }
            }
        });
    }
}
