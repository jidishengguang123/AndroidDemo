package com.jeck.demo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jeck.demo.R;

import java.util.List;

/**
 * Author:liyang
 * Email:jidishengguang123@163.com
 * Date: 2016-01-29
 * Time: 11:22
 * Description:
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private OnItemClickListener mOnItemClickListener;

    private List<String> mData;

    public RecyclerAdapter(List<String> data){
        mData = data;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //将布局转化为View并传递给RecycleView封装好的ViewHolder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rc_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView;
            mTextView.setOnClickListener(this);
        }

        //通过接口回调来实现RecyclerView的点击事件
        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null){
                mOnItemClickListener.onItemClick(v,getAdapterPosition());
            }
        }
    }
}
