package com.example.notificationtest;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onClick(View view) {
        switch (view.getId()){
            case R.id.send_notice_Button:
                //添加通知的点击事件
                Intent intent=new Intent(this, NotificationActivity.class);
                PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,0);
                //添加通知
                NotificationManager manager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                Notification notification=new NotificationCompat.Builder(this)
                        .setContentTitle("This is Title.")
                        .setContentText("This is text")
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                        //添加点击事件
                        .setContentIntent(pendingIntent)
                        //表示点击了这个通知后会自动取消掉
                        .setAutoCancel(true)
                        //.setContentText("qwertyuiop[]asdfghjjklzxcvbnmqwertyuiop[]asdfghjjkl;zxcvbnm,")
                        /*
                        //发出通知的时候播放一段音频
                        .setSound(Uri.fromFile(new File("/system/media/audio/ringtons/Luna.ogg")))
                        //震动,同时需要权限
                        .setVibrate(new long[]{0,1000,1000,1000})
                        //设置呼吸灯 - 颜色，亮起时长，灭掉时长
                        .setLights(Color.GREEN,1000,1000)
                         */
                        //直接使用通知的默认效果
                        .setDefaults(NotificationCompat.DEFAULT_ALL)
                        //设置长文字
//                        .setStyle(new NotificationCompat.BigTextStyle()
//                                .bigText("qwertyuiop[]asdfghjjklzxcvbnmqwertyuiop[]asdfghjjkl;zxcvbnm,"))
                        //设置大图片
                        .setStyle(new NotificationCompat.BigPictureStyle().bigPicture
                                (BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher)))
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .build();
                manager.notify(1,notification);
                break;
            default:
                break;
        }
    }
}
