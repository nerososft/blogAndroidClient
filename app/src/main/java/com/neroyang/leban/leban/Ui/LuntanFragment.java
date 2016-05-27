package com.neroyang.leban.leban.Ui;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.loopj.android.http.RequestParams;
import com.neroyang.leban.leban.Model.luntanListModel;
import com.neroyang.leban.leban.R;
import com.neroyang.leban.leban.view.LuntanAdapter;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by nero on 2016/3/11.
 */
public class LuntanFragment extends Fragment {

    private ImageView bt_add;
    private SwipeRefreshLayout swipeRefreshLayout_luntan;
    private RecyclerView recyclerView_luntan;

    private LuntanAdapter mAdapter;

    //布局管理器

    private LinearLayoutManager mLayoutManager;

    private luntanListModel m_model;
    private Handler mHandler;


    private int page = 1;
    private RequestParams m_parmas;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.luntan_page,null);
        //创建默认的ImageLoader配置参数
        ImageLoaderConfiguration configuration = ImageLoaderConfiguration
                .createDefault(this.getActivity());
        //Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(configuration);
        m_parmas = new RequestParams();
        m_model = new luntanListModel();
        mAdapter = new LuntanAdapter(m_model.getLuntanBeanList(),this.getActivity());



        m_model = new luntanListModel();

        getData();
        initView(view);
        recyclerView_luntan.setAdapter(mAdapter);

        return view;

    }
    private void getData() {
        m_parmas.put("page", page);
        m_model.setParams(m_parmas);
        m_model.get_list();
        set_handler();
    }

    private void set_handler() {
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 1: {
                        //Log.v("主线程收到更新信息", m_model.getLuntanBeanList().get(1).getTitle());
                        if(m_model.getLuntanBeanList().size()!=0) {
                            mAdapter.updateData(m_model.getLuntanBeanList());
                        }
                        //Log.v("主线程UI更新完毕", m_model.getLuntanBeanList().get(1).getTitle());
                        break;
                    }
                    default:
                        break;
                }
            }
        };
        m_model.setHandler(mHandler);
    }


    private void initView(View view) {
        bt_add = (ImageView)view.findViewById(R.id.bt_add);
        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Fabu.class);
                startActivity(intent);
            }
        });
        swipeRefreshLayout_luntan = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_luntan);
        swipeRefreshLayout_luntan.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                // TODO Auto-generated method stub
                page=1;
                mAdapter.refresh_Data();
                getData();
                swipeRefreshLayout_luntan.setRefreshing(false);
                Log.v("刷新", "刷新一下");


            }
        });
        recyclerView_luntan = (RecyclerView) view.findViewById(R.id.recyclerView_luntan);

       /* Animation list_animation = AnimationUtils.loadAnimation(getActivity(),R.anim.anim_item);
        LayoutAnimationController layoutAnimationController = new LayoutAnimationController(list_animation);
        layoutAnimationController.setDelay(0.5f);
        layoutAnimationController.setOrder(LayoutAnimationController.ORDER_NORMAL);
        recyclerView_luntan.setLayoutAnimation(layoutAnimationController);
*/
        recyclerView_luntan.setItemAnimator(new DefaultItemAnimator());
        recyclerView_luntan.setHasFixedSize(true);

        //创建线性布局
        mLayoutManager = new LinearLayoutManager(this.getActivity());
        //垂直方向
        mLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        //给RecyclerView设置布局管理器
        recyclerView_luntan.setLayoutManager(mLayoutManager);
        //监听子线程信息

        //间距
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.space);
        recyclerView_luntan.addItemDecoration(new SpaceItemDecoration(spacingInPixels));
        //设置Item增加、移除动画
        recyclerView_luntan.setItemAnimator(new DefaultItemAnimator());


        recyclerView_luntan.setOnScrollListener(new RecyclerView.OnScrollListener() {
            boolean isSlideToLast = false;

            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();

                // 当不滚动时
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    //获取最后一个完全显示的ItemPosition
                    int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();
                    int totalItemCount = manager.getItemCount();

                    // 判断是否滚动到底部，并且是向右滚动
                    if (lastVisibleItem == (totalItemCount - 1) && isSlideToLast) {
                        //加载更多功能的代码
                        Log.v("滑动到底加载更多", "" + manager.findLastCompletelyVisibleItemPosition());
                        //Toast.makeText(getApplicationContext(),"加载更多",Toast.LENGTH_SHORT).show();
                        page++;
                        getData();
                    }
                }

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //dx用来判断横向滑动方向，dy用来判断纵向滑动方向
                if (dy > 0) {
                    //大于0表示，正在向右滚动
                    isSlideToLast = true;
                } else {
                    //小于等于0 表示停止或向左滚动
                    isSlideToLast = false;
                }
            }
        });


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
