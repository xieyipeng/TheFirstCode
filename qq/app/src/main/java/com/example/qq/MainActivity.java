package com.example.qq;

import android.app.Activity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {

    private List<Msg> msgList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initMsg();//传入数据

        Button titleBack=(Button)findViewById(R.id.face_title_back);
        Button titleEdit=(Button)findViewById(R.id.face_title_edit);
        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        titleEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"you clicked Edit button",Toast.LENGTH_SHORT).show();
            }
        });

        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recycler_view);//滚动布局

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);//布局管理器
        recyclerView.setLayoutManager(layoutManager);
        MsgAdapter msgAdapter=new MsgAdapter(msgList);//消息适配器--将msg数据传进适配器
        recyclerView.setAdapter(msgAdapter);//使用滚动布局的建立适配器方法建立适配器
    }




    private void initMsg() {
        for(int i=0;i<10;i++){
            Msg msg1=new Msg("潘森"+i,R.drawable.one);
            msgList.add(msg1);
            Msg msg2=new Msg("富仁"+i,R.drawable.two);
            msgList.add(msg2);
            Msg msg3=new Msg("老妈"+i,R.drawable.three);
            msgList.add(msg3);
            Msg msg4=new Msg("老爸"+i,R.drawable.four);
            msgList.add(msg4);
            Msg msg5=new Msg("姐姐"+i,R.drawable.five);
            msgList.add(msg5);
            Msg msg6=new Msg("大哥"+i,R.drawable.six);
            msgList.add(msg6);
        }
    }
}
