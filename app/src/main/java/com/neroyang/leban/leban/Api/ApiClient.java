package com.neroyang.leban.leban.Api;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;

/**
 * Created by nero on 2016/3/11.
 */
public class ApiClient extends AsyncHttpClient {
    private String base_url = "http://121.42.157.180/";//服务器地址
    private String luntan_list_url = "lib/Api/item/get_items";//论坛列表接口地址
    private String user_login_url = "lib/Api/user/login_do";//用户登录接口地址
    private String user_logout_url = "lib/Api/user/logout_do";//用户登出接口
    private String user_info_url = "lib/Api/user/get_user_info";//用户数据接口
    private String passage_detail_url =  "lib/Api/item/get_item";//详情接口

    public String getPassage_detail_url() {
        return passage_detail_url;
    }

    public void setPassage_detail_url(String passage_detail_url) {
        this.passage_detail_url = passage_detail_url;
    }

    public String getUser_info_url() {
        return user_info_url;
    }

    public void setUser_info_url(String user_info_url) {
        this.user_info_url = user_info_url;
    }

    public String getUser_logout_url() {
        return user_logout_url;
    }

    public void setUser_logout_url(String user_logout_url) {
        this.user_logout_url = user_logout_url;
    }

    public String getUser_login_url() {
        return user_login_url;
    }

    public void setUser_login_url(String user_login_url) {
        this.user_login_url = user_login_url;
    }

    public ApiClient() {
        super();
    }

    @Override
    public RequestHandle get(String url, ResponseHandlerInterface responseHandler) {
        return super.get(url, responseHandler);
    }

    @Override
    public RequestHandle get(String url, RequestParams params, ResponseHandlerInterface responseHandler) {
        return super.get(url, params, responseHandler);
    }

    @Override
    public RequestHandle get(Context context, String url, ResponseHandlerInterface responseHandler) {
        return super.get(context, url, responseHandler);
    }

    @Override
    public RequestHandle get(Context context, String url, RequestParams params, ResponseHandlerInterface responseHandler) {
        return super.get(context, url, params, responseHandler);
    }

    @Override
    public RequestHandle get(Context context, String url, Header[] headers, RequestParams params, ResponseHandlerInterface responseHandler) {
        return super.get(context, url, headers, params, responseHandler);
    }

    @Override
    public RequestHandle get(Context context, String url, HttpEntity entity, String contentType, ResponseHandlerInterface responseHandler) {
        return super.get(context, url, entity, contentType, responseHandler);
    }

    @Override
    public RequestHandle post(String url, ResponseHandlerInterface responseHandler) {
        return super.post(url, responseHandler);
    }

    @Override
    public RequestHandle post(String url, RequestParams params, ResponseHandlerInterface responseHandler) {
        return super.post(url, params, responseHandler);
    }

    @Override
    public RequestHandle post(Context context, String url, RequestParams params, ResponseHandlerInterface responseHandler) {
        return super.post(context, url, params, responseHandler);
    }

    @Override
    public RequestHandle post(Context context, String url, HttpEntity entity, String contentType, ResponseHandlerInterface responseHandler) {
        return super.post(context, url, entity, contentType, responseHandler);
    }

    @Override
    public RequestHandle post(Context context, String url, Header[] headers, RequestParams params, String contentType, ResponseHandlerInterface responseHandler) {
        return super.post(context, url, headers, params, contentType, responseHandler);
    }

    @Override
    public RequestHandle post(Context context, String url, Header[] headers, HttpEntity entity, String contentType, ResponseHandlerInterface responseHandler) {
        return super.post(context, url, headers, entity, contentType, responseHandler);
    }

    private AsyncHttpClient ApiHttpClient = new AsyncHttpClient();
    private RequestParams params = new RequestParams();


    public String getBase_url() {
        return base_url;
    }

    public String getLuntan_list_url() {
        return luntan_list_url;
    }

    public AsyncHttpClient getApiHttpClient() {
        return ApiHttpClient;
    }

    public RequestParams getParams() {
        return params;
    }
}
