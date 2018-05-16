package com.example.innovationandentrepreneurship.Adopter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.innovationandentrepreneurship.R;
import com.example.innovationandentrepreneurship.base.ResultInfo;

import java.util.List;

/**
 * Created by 解奕鹏 on 2018/3/18.
 */

public class ResultAdopter extends RecyclerView.Adapter<ResultAdopter.ViewHolder> {

    private List<ResultInfo> mResultInfo;

    static class ViewHolder extends RecyclerView.ViewHolder{

        //点击事件
        // View resultView;
        TextView itemNameTextView;
        TextView itemDistanceTextView;
        TextView itemSynopsisTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            itemNameTextView=itemView.findViewById(R.id.item_name_TextView);
            itemDistanceTextView=itemView.findViewById(R.id.item_distance_TextView);
            itemSynopsisTextView=itemView.findViewById(R.id.item_synopsis_TextView);
        }
    }

    //构造函数
    public ResultAdopter(List<ResultInfo> mResultInfo) {
        this.mResultInfo = mResultInfo;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.result_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ResultInfo resultInfo=mResultInfo.get(position);
        holder.itemNameTextView.setText(resultInfo.getName());
        holder.itemDistanceTextView.setText(resultInfo.getDistance());
        holder.itemSynopsisTextView.setText(resultInfo.getSynopsis());
    }

    @Override
    public int getItemCount() {
        return mResultInfo.size();
    }
}
