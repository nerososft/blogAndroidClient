package com.neroyang.leban.leban.Model;

import android.os.Handler;
import android.util.Log;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import com.neroyang.leban.leban.Api.ApiClient;
import com.neroyang.leban.leban.Bean.MsgBean;
import com.neroyang.leban.leban.Bean.UserInfoBean;

import cz.msebera.android.httpclient.Header;

/**
 * Created by nero on 2016/3/18.
 */
public class UserInfoModel {
    private Handler handler;
    private ApiClient UserInfoClient;
    private RequestParams requestParams;
    private UserInfoBean userInfoBean;

    public UserInfoBean getUserInfoBean() {
        return userInfoBean;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public void setUserInfoBean(UserInfoBean userInfoBean) {
        this.userInfoBean = userInfoBean;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }

    private String Msg;
    public UserInfoModel(){
        UserInfoClient  = new ApiClient();
    }
    public void get_user_info(){
        UserInfoClient.post(UserInfoClient.getBase_url() + UserInfoClient.getUser_info_url(), requestParams, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Msg="获取用户信息错误";
                handler.sendEmptyMessage(10);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Gson gson = new Gson();
                userInfoBean = gson.fromJson(responseString,UserInfoBean.class);
                if(userInfoBean==null){
                    Msg = "出错咯";
                    handler.sendEmptyMessage(12);//反正就是获取出错了
                }else{
                    Log.v("userinfo",responseString );
                    handler.sendEmptyMessage(11);//登录成功
                }
            }
        });
    }
}
