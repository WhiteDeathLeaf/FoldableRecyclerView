package com.galaxylight.foldablerecyclerview.custom;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * 可折叠RecyclerView适配器
 * {@link RecyclerView.Adapter<T>}
 * Created by gzh on 2017-9-15.
 */

public abstract class FoldableAdapter<T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<T> {

    public abstract T onCreateFoldableViewHolder(ViewGroup parent, int viewType);

    public abstract void onBindFoldableViewHolder(T holder, int position);

    public abstract int getFoldableItemCount();

    /**
     * Item小尺寸时
     *
     * @param holder   T{@link RecyclerView.ViewHolder}
     * @param position 位置
     * @param offset   偏移量
     */
    public abstract void onSmallItemSize(T holder, int position, float offset);

    /**
     * Item大尺寸时
     *
     * @param holder   T{@link RecyclerView.ViewHolder}
     * @param position 位置
     * @param offset   偏移量
     */
    public abstract void onLargeItemSize(T holder, int position, float offset);

    @Override
    public T onCreateViewHolder(ViewGroup parent, int viewType) {
        return onCreateFoldableViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(T holder, int position) {
        onBindFoldableViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return getFoldableItemCount();
    }
}
