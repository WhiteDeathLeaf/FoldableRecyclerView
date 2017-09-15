package com.galaxylight.foldablerecyclerview.custom;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * 可折叠RecyclerView线性布局管理器
 * Created by gzh on 2017-9-15.
 */

public class FoldableLinearLayoutManager extends LinearLayoutManager {
    public FoldableLinearLayoutManager(Context context) {
        super(context);
    }

    public FoldableLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    //LayoutManager预留的额外空间
    @Override
    protected int getExtraLayoutSpace(RecyclerView.State state) {
        return 1000;
    }
}
