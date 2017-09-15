package com.galaxylight.foldablerecyclerview.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.galaxylight.foldablerecyclerview.R;
import com.galaxylight.foldablerecyclerview.custom.FoldableLinearLayoutManager;
import com.galaxylight.foldablerecyclerview.custom.FoldableRecyclerView;
import com.galaxylight.foldablerecyclerview.util.DataUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.frv)
    FoldableRecyclerView frv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        frv.setLayoutManager(new FoldableLinearLayoutManager(this));
        frv.setAdapter(new MyAdapter(DataUtil.getDrawableData(),DataUtil.getUrlData()));
    }
}
