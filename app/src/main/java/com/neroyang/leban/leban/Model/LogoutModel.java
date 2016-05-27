package com.neroyang.leban.leban.Model;

import android.os.Handler;
import android.util.Log;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import com.neroyang.leban.leban.Api.ApiClient;
import com.neroyang.leban.leban.Bean.MsgBean;

import cz.msebera.android.httpclient.Header;

/**
 * Created by nero on 2016/3/17.
 */
public class LogoutModel {
    private ApiClient logoutClient;
    private MsgBean logoutMsgBean;
    private Handler handler;
    private String msg;
    private RequestParams requestParams;

    public String getMsg() {
        return msg;
    }

    public LogoutModel() {
        logoutClient = new ApiClient();
    }

    public ApiClient getLogoutClient() {
        return logoutClient;
    }

    public void setLogoutClient(ApiClient logoutClient) {
        this.logoutClient = logoutClient;
    }

    public MsgBean getLogoutMsgBean() {
        return logoutMsgBean;
    }

    public void setLogoutMsgBean(MsgBean logoutMsgBean) {
        this.logoutMsgBean = logoutMsgBean;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public RequestParams getRequestParams() {
        return requestParams;
    }

    public void setRequestParams(RequestParams requestParams) {
        this.requestParams = requestParams;
    }
    public void logout_do(){
        logoutClient.post(logoutClient.getBase_url() + logoutClient.getUser_logout_url(), requestParams, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.v("登出错误", "");
                msg = "登出错误";
                handler.sendEmptyMessage(6);//登出失败

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Gson gson = new Gson();
                logoutMsgBean = gson.fromJson(responseString,MsgBean.class);
                msg = logoutMsgBean.getMsg();
                if(logoutMsgBean.getStatus().equals("error")) {
                    handler.sendEmptyMessage(4);//登出失败
                }else if(logoutMsgBean.getStatus().equals("success")){
                    handler.sendEmptyMessage(5);//登出成功
                }
            }
        });
    }
}
