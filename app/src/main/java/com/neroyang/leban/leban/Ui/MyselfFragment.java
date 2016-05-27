package com.neroyang.leban.leban.Ui;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.neroyang.leban.leban.MainActivity;
import com.neroyang.leban.leban.Model.LogoutModel;
import com.neroyang.leban.leban.Model.UserInfoModel;
import com.neroyang.leban.leban.R;
import com.neroyang.leban.leban.Tools.MToast;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by nero on 2016/3/11.
 */
public class MyselfFragment extends Fragment implements View.OnClickListener {
    private LinearLayout bt_logout;
    private Context m_context;
    private Handler mHandler;
    private MToast mToast;
    private LogoutModel m_model;
    private UserInfoModel userInfoModel;
    private ImageView user_avatar;
    private TextView user_username;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.myself_page, null);
        m_context = getActivity();
        InitView(view);
        set_handler();
        if(is_login()){
            //此处获取当前在线用户信息
            // mToast.show(m_context, "你已经登录", true)
            userInfoModel.get_user_info();
            Log.v("你已经登录","");
        }else{
            mToast.show(m_context,"你尚未登陆",false);
            Intent log_intent = new Intent(m_context,Login.class);
            startActivity(log_intent);
            Log.v("你尚未登陆","");
        };

        return view;
    }

    private void set_handler() {
        mHandler = new Handler(){
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 5: {
                        SharedPreferences sharedPreferences = m_context.getSharedPreferences("islog_in", m_context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
                        editor.putString("islogin", "");
                        editor.commit();//提交修改
                        mToast.show(m_context,m_model.getLogoutMsgBean().getMsg(),true);
                        Intent luntan_indent = new Intent(m_context, MainActivity.class);
                        startActivity(luntan_indent);
                        break;
                    }
                    case 4:{
                        mToast.show(m_context,m_model.getLogoutMsgBean().getMsg(),false);
                        break;
                    }

                    case 6:{
                            mToast.show(m_context,m_model.getMsg(),false);
                        break;
                    }
                    case 10:{
                        mToast.show(m_context,userInfoModel.getMsg(),false);
                        break;
                    }
                    case 11:{
                        //此处获取用户信息成功,更新UI
                        //updateUserUi();
                        break;
                    }
                    case 12:{
                        mToast.show(m_context,userInfoModel.getMsg(),false);
                        break;
                    }

                    default:
                        break;
                }
            }
        };
        m_model.setHandler(mHandler);
        userInfoModel.setHandler(mHandler);
    }

    private void updateUserUi() {
        //获取用户信息
        userInfoModel.get_user_info();
       // userInfoModel.setUserInfoBean();
    }

    private void InitView(View view) {
        user_avatar=  (ImageView)view.findViewById(R.id.user_avatar);
        user_username = (TextView)view.findViewById(R.id.user_username);

        m_model = new LogoutModel();
        userInfoModel = new UserInfoModel();
        mToast = new MToast();
        bt_logout =(LinearLayout)view.findViewById(R.id.bt_logout);
        bt_logout.setOnClickListener(this);
    }

    public boolean is_login() {
        SharedPreferences sharedPreferences= m_context.getSharedPreferences("islog_in", Activity.MODE_PRIVATE);
        // 使用getString方法获得value，注意第2个参数是value的默认值
        String islogin =sharedPreferences.getString("islogin", "");

        //使用toast信息提示框显示信息
        if(islogin.equals("")){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_logout:
                logout_do();
                break;
            default:
                break;
        }
    }

    private void logout_do() {
        set_handler();
        //此处调用登出接口
        m_model.logout_do();
    }
}
