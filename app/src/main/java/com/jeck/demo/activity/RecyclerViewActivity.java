package com.jeck.demo.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.jeck.demo.R;
import com.jeck.demo.adapter.RecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerAdapter mRecyclerAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Spinner mSpinner;

    private List<String> mData = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        initView();
        initData();
    }

    private void initView(){
        mSpinner = (Spinner) findViewById(R.id.spinner);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        //设置显示动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        //设置为线性布局
                        mRecyclerView.setLayoutManager(new LinearLayoutManager(RecyclerViewActivity.this));
                        break;
                    case 1:
                        //设置为表格布局
                        mRecyclerView.setLayoutManager(new GridLayoutManager(RecyclerViewActivity.this, 3));
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initData(){
        //增加测试数据
        for (int i = 0;i < 10;i++){
            mData.add("Recycler"+i);
        }

        mRecyclerAdapter = new RecyclerAdapter(mData);
        mRecyclerView.setAdapter(mRecyclerAdapter);

        mRecyclerAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(final View view, int position) {
                view.animate().translationZ(15f).setDuration(300).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        view.animate().translationZ(1f).setDuration(500).start();
                    }
                });
            }
        });
    }

    public void addRecycler(View view){
        int position = mData.size();
        mData.add("Recycler"+position);
        if (position > 0){
            mRecyclerAdapter.notifyDataSetChanged();
        }
    }

    public void delRecycler(View view){
        int position = mData.size();
        if (position > 0){
            mData.remove(position - 1);
            mRecyclerAdapter.notifyDataSetChanged();
        }
    }

}
