package com.neroyang.leban.leban.Ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.TextView;

import com.neroyang.leban.leban.Model.DetilModel;
import com.neroyang.leban.leban.R;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Created by nero on 2016/3/15.
 */
public class Detail extends Activity {
    private Handler mHandler;
    private DetilModel detilModel;
    private TextView titleTextView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.luntan_detail);
        //Log.v("跳转详情页", "成功");
       // Intent detail_intent=getIntent();
        //String lid=getIntent().getStringExtra("lid");
        Log.v("Detai页面获取到id",""+getIntent().getStringExtra("lid"));
        init_view();
        set_handler();

    }

    private void set_handler() {

    }

    private void init_view() {
        titleTextView = (TextView)findViewById(R.id.detail_title);
    }
}
