package com.dahai.foregroundservice;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;

import com.dahai.foregroundservice.adapter.ContentAdapter;
import com.dahai.foregroundservice.view.CustomLoadListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Class description goes here.
 *
 * @author 帅哥海
 */
public class CustomLoadRefreshActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, CustomLoadListView.Pagingable {
    private CustomLoadListView mListView;
    private List<String> mDatas = new ArrayList<>();
    private ContentAdapter mAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private int mIndex;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_load_refresh);
        initUI();
        initData();
        addEvent();
    }

    private void initUI() {
        mListView = (CustomLoadListView) findViewById(R.id.paging_list_view);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swp);
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
    }

    private void initData() {

        generateData();
        mAdapter = new ContentAdapter(mDatas, this);
        mListView.setAdapter(mAdapter);
        mListView.setHasMoreItems(true);
    }

    private void generateData() {
        for (int i = 0; i < 20; i++) {
            mDatas.add("数据" + mIndex ++);
        }
    }

    private void addEvent() {
        mListView.setPagingableListener(this);
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onLoadMoreItems() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                generateData();
                mAdapter.notifyDataSetChanged();
                mListView.setIsLoading(false);
            }
        }, 1500);
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mIndex = 0;
                mDatas.clear();
                generateData();
                mAdapter.notifyDataSetChanged();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }, 1500);
    }

}
