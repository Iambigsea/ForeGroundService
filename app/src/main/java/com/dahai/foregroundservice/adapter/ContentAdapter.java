package com.dahai.foregroundservice.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dahai.foregroundservice.R;

import java.util.List;

/**
 * Class description goes here.
 *
 * @author 帅哥海
 */
public class ContentAdapter extends BaseAdapter {

    private List<String> mDatas;
    private Context mContext;

    public ContentAdapter(List<String> mDatas, Context mContext) {
        this.mDatas = mDatas;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_test, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.mTv.setText(mDatas.get(i));
        return view;
    }

    private static class ViewHolder {
        TextView mTv;

        public ViewHolder(View view) {
            mTv = (TextView) view.findViewById(R.id.tv);
        }
    }
}


