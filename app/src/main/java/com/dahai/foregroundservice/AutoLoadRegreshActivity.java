package com.dahai.foregroundservice;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;

import com.dahai.foregroundservice.adapter.ContentAdapter;
import com.dahai.foregroundservice.view.AutoLoadListView;

import java.util.ArrayList;
import java.util.List;


/**
 * Class description goes here.
 *
 * @author 帅哥海
 */
public class AutoLoadRegreshActivity extends AppCompatActivity
        implements AutoLoadListView.Pagingable, SwipeRefreshLayout.OnRefreshListener {
    private AutoLoadListView mListView;
    private List<String> mDatas = new ArrayList<>();
    private ContentAdapter mAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private int mIndex;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initUI();
        initData();
        addEvent();
    }

    private void initUI() {
        mListView = (AutoLoadListView) findViewById(R.id.paging_list_view);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swp);
        //设置圈圈颜色
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light, android.R.color.holo_orange_light,
                android.R.color.holo_green_light);
    }

    private void initData() {
        generateData();
        mAdapter = new ContentAdapter(mDatas,this);
        mListView.setAdapter(mAdapter);
        //设置还有更多数据加载
        mListView.setHasMoreItems(true);
    }

    /**
     * 生成数据
     */
    private void generateData() {
        for (int i = 0; i < 20; i++) {
            mDatas.add("数据" + mIndex ++);
        }
    }

    private void addEvent() {
        mListView.setPagingableListener(this);
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    /**
     * 加载更多数据
     */
    @Override
    public void onLoadMoreItems() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                generateData();
                mAdapter.notifyDataSetChanged();
                //设置为loading完成
                mListView.setIsLoading(false);
            }
        }, 1500);
    }

    /**
     * 刷新数据,把集合清空,吧角标设置为0
     */
    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mDatas.clear();
                mIndex = 0;
                generateData();
                mAdapter.notifyDataSetChanged();
                //隐藏圈圈
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }, 1500);
    }
}
