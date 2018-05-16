package com.example.playvideotest;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView=findViewById(R.id.video_VideoView);
        Button playButton=findViewById(R.id.play_Button);
        Button pauseButton=findViewById(R.id.pause_Button);
        Button replayButton=findViewById(R.id.replay_Button);

        playButton.setOnClickListener(this);
        pauseButton.setOnClickListener(this);
        replayButton.setOnClickListener(this);

        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission
                .WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest
                    .permission.WRITE_EXTERNAL_STORAGE},1);
        }else {
            //初始化MediaPlayer
            initVideoPath();
        }
    }

    private void initVideoPath(){
        File file=new File(Environment.getExternalStorageDirectory(),"movie.mp4");
        //指定路径
        videoView.setVideoPath(file.getPath());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if (grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    initVideoPath();
                }else {
                    Toast.makeText(this, "拒绝权限", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.play_Button:
                if (!videoView.isPlaying()){
                    videoView.start();
                }
                break;
            case R.id.pause_Button:
                if (videoView.isPlaying()){
                    videoView.pause();
                }
                break;
            case R.id.replay_Button:
                if (videoView.isPlaying()){
                    videoView.resume();
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (videoView!=null){
            videoView.suspend();
        }
    }
}
