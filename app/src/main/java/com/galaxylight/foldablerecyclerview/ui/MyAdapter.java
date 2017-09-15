package com.galaxylight.foldablerecyclerview.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.galaxylight.foldablerecyclerview.R;
import com.galaxylight.foldablerecyclerview.custom.FoldableAdapter;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 适配器
 * Created by gzh on 2017-9-15.
 */

public class MyAdapter extends FoldableAdapter<MyAdapter.MyViewHolder> {
    private String[] urls;//图片地址数组
    private List<Integer> drawables;//图片ResId集合
    private Context context;

    public MyAdapter(List<Integer> drawables, String[] urls) {
        //判断是哪种数据，默认图片ResId集合
        if (drawables != null && urls == null) {
            this.drawables = drawables;
        } else if (urls != null && drawables == null) {
            this.urls = urls;
        } else if (drawables != null) {
            this.drawables = drawables;
        } else {
            throw new NullPointerException("至少要有一种数据");
        }
    }

    @Override
    public MyViewHolder onCreateFoldableViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item, parent, false));
    }

    @Override
    public void onBindFoldableViewHolder(MyViewHolder holder, int position) {
        if (drawables != null) {
            Glide.with(context).load(drawables.get(position)).into(holder.imageView);
        } else {
            Glide.with(context).load(urls[position]).into(holder.imageView);
        }
        holder.textView.setText("Item\t"+position);
    }

    @Override
    public int getFoldableItemCount() {
        if (drawables != null)
            return drawables.size();
        return urls.length;
    }

    @Override
    public void onSmallItemSize(MyViewHolder holder, int position, float offset) {
        holder.textView.setAlpha(offset / 100f);
    }

    @Override
    public void onLargeItemSize(MyViewHolder holder, int position, float offset) {
        holder.textView.setAlpha(offset / 100f);
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv)
        ImageView imageView;
        @BindView(R.id.tv)
        TextView textView;

        MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
