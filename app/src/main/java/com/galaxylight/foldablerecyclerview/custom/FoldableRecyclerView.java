package com.galaxylight.foldablerecyclerview.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.galaxylight.foldablerecyclerview.R;

/**
 * 可折叠RecyclerView{@link RecyclerView}
 * Created by gzh on 2017-9-15.
 */

public class FoldableRecyclerView extends RecyclerView {
    private int itemHeight_def;//默认高度
    private int itemHeight_fold;//折叠高度
    private int maxDistance;//最大距离
    private int itemHeight_diff;//差异距离
    private int itemSize;
    private int item_visible = 0;

    private FoldableAdapter adapter;

    public FoldableRecyclerView(Context context) {
        super(context);
        init(context, null);
    }

    public FoldableRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FoldableRecyclerView);
        itemHeight_def = (int) typedArray.getDimension(R.styleable.FoldableRecyclerView_itemHeight_def, 100);
        itemHeight_fold = (int) typedArray.getDimension(R.styleable.FoldableRecyclerView_itemHeight_fold, 200);
        itemHeight_diff = itemHeight_fold - itemHeight_def;
        maxDistance = itemHeight_fold;
        typedArray.recycle();
        addOnScrollListener(scrollListener);
    }

    private OnScrollListener scrollListener = new OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            if (getLayoutManager() instanceof LinearLayoutManager) {
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) getLayoutManager();
                if (linearLayoutManager.getOrientation() == LinearLayoutManager.VERTICAL) {
                    item_visible = linearLayoutManager.getItemCount();
                    itemSize = dy > 0 ? 1 : 0;
                    changeHeight(recyclerView);
                }
            }
        }
    };

    /**
     * 滑动时的Item高度变化
     *
     * @param recyclerView recyclerView
     */
    @SuppressWarnings("unchecked")
    private void changeHeight(RecyclerView recyclerView) {
        for (int i = 0; i < item_visible; i++) {
            View view = recyclerView.getChildAt(i);
            if (view != null) {
                float distance = Math.abs(view.getTop());
                if (distance > maxDistance) {
                    view.getLayoutParams().height = itemHeight_def;
                    view.requestLayout();
                } else if (distance <= maxDistance) {
                    view.getLayoutParams().height = (int) (itemHeight_fold - ((distance * (itemHeight_diff)) / maxDistance));
                    view.requestLayout();
                }
                if (i == itemSize) {
                    if (adapter != null)
                        adapter.onLargeItemSize(recyclerView.getChildViewHolder(view), itemSize, ((view.getHeight() - itemHeight_def) * 100) / itemHeight_diff);
                } else {
                    if (adapter != null)
                        adapter.onSmallItemSize(recyclerView.getChildViewHolder(view), itemSize, ((view.getHeight() - itemHeight_def) * 100) / itemHeight_diff);
                }
            }
        }
    }

    @Override
    public void setAdapter(Adapter adapter) {
        if (adapter instanceof FoldableAdapter)
            this.adapter = (FoldableAdapter) adapter;
        super.setAdapter(adapter);
    }
}
