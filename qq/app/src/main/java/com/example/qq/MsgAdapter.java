package com.example.qq;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.ViewHolder>{

    private List<Msg> mMsgList;

    //ViewHolder类
    public class ViewHolder extends RecyclerView.ViewHolder {

        //用具体的view注册监听事件
        View MsgView;

        ImageView imageView;
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);

            //修改ViewHolder，保存布局实例
            MsgView=itemView;

            imageView=(ImageView)itemView.findViewById(R.id.item_Image_View);
            textView=(TextView)itemView.findViewById(R.id.item_Text_View);
        }
    }

    //消息适配器的构造函数---用于把要展示的数据传给全局变量mMsgList
    public MsgAdapter(List<Msg> MsgList){
        mMsgList=MsgList;
    }

    //创建ViewHolder实例，引进子项布局
    //可在里面创建点击事件
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.msg_item,parent,false);//引入msg_item实例
//        ViewHolder viewHolder=new ViewHolder(view);//创建viewHolder实例

        //创建点击事件
        final ViewHolder holder=new ViewHolder(view);
        holder.MsgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();//获得用户点击的position
                Msg msg = mMsgList.get(position);//拿到Msg实例
                Toast.makeText(v.getContext(),"you clicked view"+msg.getImagedId(),Toast.LENGTH_SHORT).show();
            }
        });

        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();//获得用户点击的position
                Msg msg = mMsgList.get(position);//拿到Msg实例
                Toast.makeText(v.getContext(),"you clicked text"+msg.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        return holder;
    }

    //对子项数据进行赋值--每个子项滚进屏幕的时候执行
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {//通过position获得Msg实例
        Msg msg=mMsgList.get(position);//获得实例
        holder.imageView.setImageResource(msg.getImagedId());
        holder.textView.setText(msg.getMessage());
        //将数据设置到ViewHolder中
    }

    //返回大小
    @Override
    public int getItemCount() {
        return mMsgList.size();
    }

}
