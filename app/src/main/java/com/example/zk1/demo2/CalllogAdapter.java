package com.example.zk1.demo2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zk1.R;

import java.util.List;
import java.util.Map;

/**
 * Created by 帅比浩宇 on 2018/1/28.
 */

public class CalllogAdapter extends RecyclerView.Adapter<CalllogAdapter.CalllogViewHolder>{
    private Context context;
    private List<Map<String,String>> mDatas;

    public CalllogAdapter(Context context, List<Map<String, String>> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }

    @Override
    public CalllogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_calllog,parent,false);
        CalllogViewHolder holder = new CalllogViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(CalllogViewHolder holder, int position) {
        Map<String, String> map = mDatas.get(position);
        holder.idTv.setText(map.get("id"));
        holder.numberTv.setText(map.get("number"));
        holder.timeTv.setText(map.get("time"));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class CalllogViewHolder extends RecyclerView.ViewHolder{
        TextView idTv,numberTv,timeTv;
        public CalllogViewHolder(View itemView) {
            super(itemView);
            idTv = (TextView) itemView.findViewById(R.id.calllog_name);
            numberTv = (TextView) itemView.findViewById(R.id.call_log_number);
            timeTv = (TextView) itemView.findViewById(R.id.call_log_time);
        }
    }
}
