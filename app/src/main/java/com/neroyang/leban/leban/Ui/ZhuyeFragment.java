package com.neroyang.leban.leban.Ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.neroyang.leban.leban.R;

/**
 * Created by nero on 2016/3/11.
 */
public class ZhuyeFragment extends Fragment {
    private WebView main_web;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.main_page, null);

        InitView(view);

        return view;
    }

    private void InitView(View view) {
        main_web = (WebView) view.findViewById(R.id.main_web);
        WebSettings webSettings = main_web.getSettings();
        webSettings.setJavaScriptEnabled(true);
        main_web.loadUrl("http://121.42.157.180");

    }
}
