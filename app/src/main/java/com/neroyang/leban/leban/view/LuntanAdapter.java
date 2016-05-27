package com.neroyang.leban.leban.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.neroyang.leban.leban.Api.ApiClient;
import com.neroyang.leban.leban.Bean.LuntanListBean;
import com.neroyang.leban.leban.R;
import com.neroyang.leban.leban.Ui.Detail;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.io.IOException;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by nero on 2016/3/12.
 */
public class LuntanAdapter extends Adapter<LuntanAdapter.ViewHolder> {
    private static List<LuntanListBean> luntanListBeans;
    private Context mcontext;
    private ApiClient m_apiclient;
    private Intent toDetail_activity;

    public LuntanAdapter(List<LuntanListBean> luntanListBeans,Context context) {
        this.luntanListBeans = luntanListBeans;
        mcontext = context;
    }
    public void  updateData(List<LuntanListBean> luntanListBeans){
        //this.luntanListBeans = luntanListBeans;//这儿改成add?
        this.luntanListBeans.addAll(luntanListBeans);
        //for (int i = 0;i<luntanListBeans.size();i++){
         //   this.luntanListBeans.add(luntanListBeans.get(i));
        //}
        notifyDataSetChanged();
    }
    public void refresh_Data(){
        this.luntanListBeans.clear();
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.luntan_item,parent,false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

   // @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mTextView.setText(luntanListBeans.get(position).getTitle());
        holder.mTextView_name.setText(luntanListBeans.get(position).getUsername());
        holder.mTextView_time.setText(luntanListBeans.get(position).getCreatetime());

        m_apiclient = new ApiClient();
        /*这种方式会导致加载的顺序出错
        ImageLoader.getInstance().displayImage(m_apiclient.getBase_url()+luntanListBeans.get(position).getAvatar(), new SimpleImageLoadingListener(){
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                super.onLoadingComplete(imageUri, view, loadedImage);
                holder.iv_avatar.setImageBitmap(loadedImage);
            }

        });*/
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                             .showImageOnLoading(R.drawable.loading) // 加载中
                             .showImageForEmptyUri(R.drawable.sorry) // 加载不到
                             .showImageOnFail(R.drawable.sorry) // 加载失败
                             .resetViewBeforeLoading(false) // default
                             .delayBeforeLoading(1000).cacheInMemory(false) // default
                             .cacheInMemory(true)//内存缓存
                             .cacheOnDisk(true) // 磁盘缓存
                             .considerExifParams(false) // default
                             .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2) // default
                             .bitmapConfig(Bitmap.Config.ARGB_8888) // default
                             .displayer(new SimpleBitmapDisplayer()) // default
                             .handler(new Handler()) // default
                             .build();
        ImageLoader.getInstance().displayImage(m_apiclient.getBase_url() + luntanListBeans.get(position).getAvatar(), holder.iv_avatar, options);

    }

    @Override
    public int getItemCount() {
        return luntanListBeans.size();
    }
    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mTextView;
        public TextView mTextView_name;
        public TextView mTextView_time;
        public CircleImageView iv_avatar;
        public View view_item;
        public ViewHolder(View view){
            super(view);
            view_item = view;
            mTextView = (TextView) view.findViewById(R.id.tv_item);
            mTextView_name = (TextView) view.findViewById(R.id.tv_name);
            mTextView_time = (TextView) view.findViewById(R.id.tv_time);
            iv_avatar = (CircleImageView) view.findViewById(R.id.iv_avatar);
            view_item.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.v("被点击的id", "" + luntanListBeans.get(getPosition()).getId());
            //Toast.makeText(mcontext, luntanListBeans.get(getPosition()).getTitle(),Toast.LENGTH_SHORT).show();
            toDetail_activity = new Intent(mcontext,Detail.class);
            toDetail_activity.putExtra("lid", luntanListBeans.get(getPosition()).getId());
            toDetail_activity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
            mcontext.startActivity(toDetail_activity);

        }
    }
}
