package com.dahai.foregroundservice.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AbsListView;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.dahai.foregroundservice.R;


/**
 * Class description goes here.
 *
 * @author 帅哥海
 */
public class CustomLoadListView extends ListView {
    private boolean isLoading;
    private CustomLoadingView loadingView;
    public boolean hasMoreItems;
    private Pagingable pagingableListener;

    public CustomLoadListView(Context context) {
        this(context, null);
    }

    public CustomLoadListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomLoadListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    public boolean isLoading() {
        return this.isLoading;
    }

    public void setIsLoading(boolean isLoading) {
        this.isLoading = isLoading;
    }

    public void setPagingableListener(Pagingable pagingableListener) {
        this.pagingableListener = pagingableListener;
    }

    public void setHasMoreItems(boolean hasMoreItems) {
        this.hasMoreItems = hasMoreItems;
        if (!this.hasMoreItems) {
            removeFooterView(loadingView);
        } else if (findViewById(R.id.custom_loading_view) == null) {
            addFooterView(loadingView);
            ListAdapter adapter = ((HeaderViewListAdapter) getAdapter()).getWrappedAdapter();
            setAdapter(adapter);
        }
    }

    public boolean hasMoreItems() {
        return this.hasMoreItems;
    }

    private void init() {
        isLoading = false;
        loadingView = new CustomLoadingView(getContext());
        addFooterView(loadingView);
        super.setOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                int lastVisibleItem = firstVisibleItem + visibleItemCount;
                if (!isLoading && hasMoreItems && (lastVisibleItem == totalItemCount)) {
                    if (pagingableListener != null) {
                        isLoading = true;
                        pagingableListener.onLoadMoreItems();
                    }

                }
            }
        });
    }
    public interface Pagingable {
        void onLoadMoreItems();
    }
}
