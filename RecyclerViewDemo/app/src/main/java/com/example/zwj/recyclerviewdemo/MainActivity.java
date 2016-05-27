package com.example.zwj.recyclerviewdemo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        //默认加载list标准模式
        loadListData(false, true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_list_normal) {
            //list标准展示
            loadListData(false, true);
            return true;
        } else if (id == R.id.action_list_vertical_reverse) {
            //list垂直反向显示
            loadListData(true, true);
            return true;
        } else if (id == R.id.action_list_horizontal) {
            //list水平显示
            loadListData(false, false);
            return true;
        } else if (id == R.id.action_list_horizontal_reverse) {
            //list水平反向显示
            loadListData(true, false);
            return true;
        } else if (id == R.id.action_grid_normal) {
            //Grid标准展示
            loadGridData(false, true);
            return true;
        } else if (id == R.id.action_grid_vertical_reverse) {
            loadGridData(true, true);
            return true;
        } else if (id == R.id.action_grid_horizontal) {
            loadGridData(false, false);
            return true;
        } else if (id == R.id.action_grid_horizontal_reverse) {
            loadGridData(true, false);
            return true;
        } else if (id == R.id.action_staggered_normal) {
            //瀑布流标准展示
            loadStaggeredData(false, true);
            return true;
        } else if (id == R.id.action_staggered_vertical_reverse) {
            loadStaggeredData(true, true);
            return true;
        } else if (id == R.id.action_staggered_horizontal) {
            loadStaggeredData(false, false);
            return true;
        } else if (id == R.id.action_staggered_horizontal_reverse) {
            loadStaggeredData(true, false);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void loadListData(boolean reverse, boolean vertical) {
        //给RecycleView加载数据
        List<DataBean> datas = new ArrayList<>();
//        DataBean bean = new DataBean();
//        bean.icon = R.mipmap.img_01;
//        bean.name = "第一张";
//        datas.add(bean);
        for (int i = 0; i < DATAS.ICONS.length; i++) {
            DataBean bean = new DataBean();
            bean.icon = DATAS.ICONS[i];
            bean.name = "图片_" + (i + 1);
            datas.add(bean);
        }

        //1.设置布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置布局是否反向显示
        layoutManager.setReverseLayout(reverse);
        //设置显示的方向
        layoutManager.setOrientation(vertical ? LinearLayoutManager.VERTICAL : LinearLayoutManager.HORIZONTAL);

        mRecyclerView.setLayoutManager(layoutManager);

        //2.设置适配器
        mRecyclerView.setAdapter(new ListAdapter(this, datas));

    }

    //网格的Grid
    private void loadGridData(boolean reverse, boolean vertical) {
        //给RecycleView加载数据
        List<DataBean> datas = new ArrayList<>();
        for (int i = 0; i < DATAS.ICONS.length; i++) {
            DataBean bean = new DataBean();
            bean.icon = DATAS.ICONS[i];
            bean.name = "图片_" + (i + 1);
            datas.add(bean);
        }
        //1.设置布局管理器
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        //设置布局是否反向显示
        gridLayoutManager.setReverseLayout(reverse);
        //设置显示的方向
//        gridLayoutManager.setOrientation(vertical ? LinearLayoutManager.VERTICAL : LinearLayoutManager.HORIZONTAL);
        gridLayoutManager.setOrientation(vertical ? GridLayoutManager.VERTICAL : GridLayoutManager.HORIZONTAL);

        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setAdapter(new GridAdapter(this, datas));
    }

    //瀑布流效果展示
    private void loadStaggeredData(boolean reverse, boolean vertical) {
        //给RecycleView加载数据
        List<DataBean> datas = new ArrayList<>();
        for (int i = 0; i < DATAS.ICONS.length; i++) {
            DataBean bean = new DataBean();
            bean.icon = DATAS.ICONS[i];
            bean.name = "图片_" + (i + 1);
            datas.add(bean);
        }
        //1.设置布局管理器
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, vertical ? StaggeredGridLayoutManager.VERTICAL : StaggeredGridLayoutManager.HORIZONTAL);
        //设置布局是否反向显示
        staggeredGridLayoutManager.setReverseLayout(reverse);

        mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        mRecyclerView.setAdapter(new StaggeredAdapter(this, datas));
    }
}
