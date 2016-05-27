package com.neroyang.leban.leban.Model;

import android.os.Handler;
import android.util.Log;

import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import com.neroyang.leban.leban.Api.ApiClient;
import com.neroyang.leban.leban.Bean.PassageDetailBean;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * Created by nero on 2016/3/26.
 */
public class DetilModel {
    private ApiClient mApiClient;
    private Handler mHandler;
    private PassageDetailBean mDetailBean;
    private RequestParams mRequestParams;

    public ApiClient getmApiClient() {
        return mApiClient;
    }

    public void setmApiClient(ApiClient mApiClient) {
        this.mApiClient = mApiClient;
    }

    public Handler getmHandler() {
        return mHandler;
    }

    public void setmHandler(Handler mHandler) {
        this.mHandler = mHandler;
    }

    public PassageDetailBean getmDetailBean() {
        return mDetailBean;
    }

    public void setmDetailBean(PassageDetailBean mDetailBean) {
        this.mDetailBean = mDetailBean;
    }

    public RequestParams getmRequestParams() {
        return mRequestParams;
    }

    public void setmRequestParams(RequestParams mRequestParams) {
        this.mRequestParams = mRequestParams;
    }
    public DetilModel(){
        mApiClient = new ApiClient();
    }
    public void get_detail(){
        mApiClient.post(mApiClient.getBase_url() + mApiClient.getPassage_detail_url(), mRequestParams, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.v("详情",responseString);
            }
        });
    }
}
