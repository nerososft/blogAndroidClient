package com.neroyang.leban.leban.Tools;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.neroyang.leban.leban.R;

/**
 * Created by nero on 2016/3/17.
 */
public class MToast{
    private Toast toast;
    public MToast(){

    }
    public void show(Context context,String msg,boolean status) {
        toast = Toast.makeText(context,
                msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        LinearLayout toastView = (LinearLayout) toast.getView();
        ImageView imageCodeProject = new ImageView(context);
        if(!status) {
            imageCodeProject.setImageResource(R.drawable.error);
        }else{
            imageCodeProject.setImageResource(R.drawable.success);
        }
        toastView.addView(imageCodeProject, 0);
        toast.show();
    }
}
