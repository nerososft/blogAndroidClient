package com.neroyang.leban.leban;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.RequestParams;
import com.neroyang.leban.leban.Bean.LuntanListBean;
import com.neroyang.leban.leban.Model.Event;
import com.neroyang.leban.leban.Model.luntanListModel;
import com.neroyang.leban.leban.Ui.LuntanFragment;
import com.neroyang.leban.leban.Ui.MyselfFragment;
import com.neroyang.leban.leban.Ui.ZhuyeFragment;
import com.neroyang.leban.leban.view.LuntanAdapter;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.w3c.dom.Text;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    //底部tab
    private LinearLayout bottom_bt_zhuye;
    private LinearLayout bottom_bt_luntan;
    private LinearLayout bottom_bt_myself;

    private ImageView bottom_bt_luntan_img;
    private TextView bottom_bt_luntan_tx;

    private ImageView bottom_zhuye_img;

    private ImageView bottom_bt_myself_img;
    private TextView bottom_bt_myself_tx;

    private int text_color_o = 0xff03a8a2;
    private int text_color_n = 0xff8a8a8a;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private CircleImageView mFloatButton;
    private WindowManager.LayoutParams mWindowManager;
    private WindowManager mmWindow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frag_container, new LuntanFragment());
        fragmentTransaction.commit();

        initView();
       mmWindow =  (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        //createFloatButton();


    }

    private void createFloatButton() {
        mFloatButton = new CircleImageView(this);
        mFloatButton.setImageResource(R.drawable.jy_o);

       mWindowManager = new  WindowManager.LayoutParams(60, 60,0,0, PixelFormat.TRANSPARENT);

        mWindowManager.type  = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        mWindowManager.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL  |
                                                       WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE    |
                                                       WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED;
        mWindowManager.gravity = Gravity.LEFT   |   Gravity.TOP;
        mWindowManager.x =300;
        mWindowManager.y = 100;
        mmWindow.addView(mFloatButton,mWindowManager);

        mFloatButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.v("s", "onTouch");
                int x = (int) event.getX();
                int y = (int) event.getY();
                Rect rect = new Rect();
                mFloatButton.getGlobalVisibleRect(rect);
                if (!rect.contains(x, y)) {
                   Log.v("点了","浮动按钮");
                }
                return false;
            }
        });
    }


    @TargetApi(Build.VERSION_CODES.M)
    private void initView() {
        bottom_bt_zhuye = (LinearLayout) findViewById(R.id.bottom_bt_zhuye);
        bottom_bt_luntan = (LinearLayout)findViewById(R.id.bottom_bt_luntan);
        bottom_bt_myself = (LinearLayout)findViewById(R.id.bottom_bt_myself);

        bottom_bt_myself_img = (ImageView) findViewById(R.id.bottom_bt_myself_img);
        bottom_bt_myself_tx = (TextView)findViewById(R.id.bottom_bt_myself_tx);

        bottom_zhuye_img = (ImageView)findViewById(R.id.bottom_bt_zhuye_img);

        bottom_bt_luntan_img = (ImageView)findViewById(R.id.bottom_bt_luntan_img);
        bottom_bt_luntan_tx = (TextView)findViewById(R.id.bottom_bt_luntan_tx);

        bottom_bt_zhuye.setOnClickListener(this);
        bottom_bt_luntan.setOnClickListener(this);
        bottom_bt_myself.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        fragmentTransaction = fragmentManager.beginTransaction();
        bottom_reset();
        switch (v.getId()){
            case R.id.bottom_bt_luntan:
                Log.v("点击底部","论坛");
                bottom_bt_luntan_img.setImageDrawable(getResources().getDrawable(R.drawable.home_o));
                bottom_bt_luntan_tx.setTextColor(text_color_o);
                fragmentTransaction.replace(R.id.frag_container, new LuntanFragment());
                break;
            case R.id.bottom_bt_zhuye:
                bottom_zhuye_img.setImageDrawable(getResources().getDrawable(R.drawable.jy_o));
                Log.v("点击底部","主页");
                fragmentTransaction.replace(R.id.frag_container, new ZhuyeFragment());
                break;
            case R.id.bottom_bt_myself:
                bottom_bt_myself_img.setImageDrawable(getResources().getDrawable(R.drawable.my_o));
                bottom_bt_myself_tx.setTextColor(text_color_o);
                Log.v("点击底部","个人");
                fragmentTransaction.replace(R.id.frag_container, new MyselfFragment());
                break;
        }
        fragmentTransaction.commit();
    }

    private void bottom_reset() {
        bottom_bt_luntan_tx.setTextColor(text_color_n);
        bottom_bt_luntan_tx.setText("论坛");
        bottom_bt_myself_tx.setTextColor(text_color_n);
        bottom_bt_myself_tx.setText("我");
        bottom_bt_luntan_img.setImageDrawable(getResources().getDrawable(R.drawable.home_n));
        bottom_bt_myself_img.setImageDrawable(getResources().getDrawable(R.drawable.my_n));
        bottom_zhuye_img.setImageDrawable(getResources().getDrawable(R.drawable.jy_o));
    }

    public class SpaceItemDecoration extends RecyclerView.ItemDecoration{

        private int space;

        public SpaceItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

            if(parent.getChildPosition(view) != 0)
                outRect.top = space;
        }
    }
}
