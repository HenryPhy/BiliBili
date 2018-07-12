package com.example.zk1.demo2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zk1.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by 帅比浩宇 on 2018/1/28.
 */

public class CookAdapter extends RecyclerView.Adapter<CookAdapter.CookViewHolder>{
    private Context context;
    private List<FoodBean.DataBean> mDatas;

    public CookAdapter(Context context, List<FoodBean.DataBean> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }

    @Override
    public CookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_cook,parent,false);
        CookViewHolder holder = new CookViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(CookViewHolder holder, int position) {
        FoodBean.DataBean bean = mDatas.get(position);
        holder.titleTv.setText(bean.getTitle());
        holder.summaryTv.setText(bean.getFood_str());
        Picasso.with(context).load(bean.getPic()).into(holder.iv);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class CookViewHolder extends RecyclerView.ViewHolder{
        ImageView iv;
        TextView titleTv,summaryTv;
        public CookViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.cook_iv);
            titleTv = (TextView) itemView.findViewById(R.id.cook_title);
            summaryTv = (TextView) itemView.findViewById(R.id.cook_summary);
        }
    }
}
