package com.example.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.nio.channels.NetworkChannel;

public class MainActivity extends AppCompatActivity {

    private IntentFilter intentFilter;
    private LocalReceiver localReceiver;//本地广播
    private LocalBroadcastManager localBroadcastManager;
    private NetworkChangeReceiver networkChangeReceiver;//网络发生变化---类，继承自BroadcastReceiver

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        localBroadcastManager=LocalBroadcastManager.getInstance(this);//获取本地广播实例

        Button button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent("com.example.broadcasttest.LOCAL_BROADCAST");
                localBroadcastManager.sendBroadcast(intent);
//                Intent intent=new Intent("com.example.broadcasttest.MY_BROADCAST");
//                //sendBroadcast(intent);//标准广播
//                sendOrderedBroadcast(intent,null);//有序广播
            }
        });

        intentFilter=new IntentFilter();
        intentFilter.addAction("com.example.broadcasttest.LOCAL_BROADCAST");
        localReceiver=new LocalReceiver();
        localBroadcastManager.registerReceiver(localReceiver,intentFilter);//注册本地广播监听器

//        intentFilter=new IntentFilter();//intent意图  filter过滤器
//        //广播监听器想监听什么，就在过滤器里添加什么
//        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");//网络发生变化时，发出这条广播
//        networkChangeReceiver=new NetworkChangeReceiver();//创建网络变化实例
//        registerReceiver(networkChangeReceiver,intentFilter);   // register官方记录  记录所接收到的东西
//                                                                // 传进网络变化和意图过滤器
//                                                                // 如此NetworkChangeReceiver会收到值为android.net.conn.CONNECTIVITY_CHANGE的广播


    }

    //销毁
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //unregisterReceiver(networkChangeReceiver);//用于取消动态注册广播接收器
        localBroadcastManager.unregisterReceiver(localReceiver);
    }

    //继承自BroadcastReceiver，并重写onReceive方法
    //每当网络发上变化，执行intent
    class NetworkChangeReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            //ConnectivityManager--系统服务类,得到系统服务类的实例
            ConnectivityManager connectivityManager=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
            //NetworkInfo类，判断是否有网络
            NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();//得到NetworkInfo实例
            if(networkInfo!=null && networkInfo.isAvailable()){
                Toast.makeText(context,"network is available",Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(context,"network is unavailable",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class LocalReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {//本地广播
            Toast.makeText(context,"receiver local broadcast",Toast.LENGTH_SHORT).show();
        }
    }
}
