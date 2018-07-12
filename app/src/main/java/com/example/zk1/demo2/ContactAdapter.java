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

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder>{
    private Context context;
    private List<Map<String,String>> mDatas;

    public ContactAdapter(Context context, List<Map<String, String>> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_contacts,parent,false);
        ContactViewHolder holder = new ContactViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        Map<String, String> map = mDatas.get(position);
        holder.idTv.setText(map.get("id"));
        holder.nameTv.setText(map.get("name"));
        holder.phoneTv.setText(map.get("phone"));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class ContactViewHolder extends RecyclerView.ViewHolder{
        TextView idTv,nameTv,phoneTv;
        public ContactViewHolder(View itemView) {
            super(itemView);
            idTv = itemView.findViewById(R.id.contact_id);
            nameTv =  itemView.findViewById(R.id.contact_name);
            phoneTv = itemView.findViewById(R.id.contact_phone);
        }
    }
}
