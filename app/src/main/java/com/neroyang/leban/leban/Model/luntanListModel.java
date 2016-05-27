package com.neroyang.leban.leban.Model;

import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import com.neroyang.leban.leban.Api.ApiClient;
import com.neroyang.leban.leban.Bean.LuntanListBean;
import com.neroyang.leban.leban.view.LuntanAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by nero on 2016/3/11.
 */
public class luntanListModel{
    private List<LuntanListBean> luntanBeanList;
    private List<LuntanListBean> luntan_BeanList;


    private Handler handler;

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public List<LuntanListBean> getLuntanBeanList() {
        return luntanBeanList;
    }

    private ApiClient luntanListClient;
    private RequestParams params;

    public RequestParams getParams() {
        return params;
    }

    public void setParams(RequestParams params) {
        this.params = params;
    }

    public luntanListModel(){
        luntanBeanList = new ArrayList<LuntanListBean>();
        luntanListClient= new ApiClient();
    }
    public  void get_list(){
        Log.v("HTTP:","请求"+luntanListClient.getBase_url()+luntanListClient.getLuntan_list_url()+"中,参数:"+params);
        luntanListClient.post(luntanListClient.getBase_url() + luntanListClient.getLuntan_list_url(), this.params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
               // Log.v("error", statusCode + "msg:" + responseString);
            }
            @Override
            public  void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.v("success", responseString);
                Gson m_goon = new Gson();
                luntan_BeanList = m_goon.fromJson(responseString, new TypeToken<List<LuntanListBean>>(){}.getType());

                Log.v("size:", "" + luntan_BeanList.size());
                Log.v("is_null", "" + (handler == null));
                //Log.v("数据请求成功：", luntanBeanList.get(0).getTitle());
                if(luntan_BeanList.size()!=0) {
                    luntanBeanList = luntan_BeanList;
                    Collections.reverse(luntanBeanList);
                    handler.sendEmptyMessage(1);
                    Log.v("size:", "" + luntanBeanList.size());
                    Log.v("通知主线程更新ui", luntanBeanList.get(2).getTitle());
                }
            }
        });
    }
}