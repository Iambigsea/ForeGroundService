package com.dahai.foregroundservice;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.dahai.foregroundservice.adapter.ContentAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Class description goes here.
 *
 * @author 帅哥海
 */
public class SwipeRefreshActivity extends AppCompatActivity
        implements SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ListView mListView;
    private List<String> mDatas = new ArrayList<>();
    private ContentAdapter mAdapter;
    private int mIndex;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_refresh);
        initUI();
        initData();
        addEvent();
    }
    private void addEvent() {
        //添加刷新监听
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }
    private void initUI() {
        mListView = (ListView) findViewById(R.id.lv_refresh);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swp);
        //设置圈圈颜色
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light, android.R.color.holo_orange_light,
                android.R.color.holo_green_light);
    }
    private void initData() {
        generateData();
        mAdapter = new ContentAdapter(mDatas, this);
        mListView.setAdapter(mAdapter);
    }

    /**
     * 生成listview的数据
     */
    private void generateData() {
        for (int i = 0; i < 20; i++) {
            mDatas.add("数据" + mIndex++);
        }
    }

    /**
     * 刷新的监听,这里模拟一个耗时的操作
     */
    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mDatas.clear();
                generateData();
                mAdapter.notifyDataSetChanged();
                //完成刷新操作,隐藏圈圈
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }, 1500);
    }
}
