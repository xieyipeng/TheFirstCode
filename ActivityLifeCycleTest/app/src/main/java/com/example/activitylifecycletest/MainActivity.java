package com.example.activitylifecycletest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("TAG","onCreate");
        setContentView(R.layout.main_layout);

        if(savedInstanceState!=null){
            String tempDate=savedInstanceState.getString("data_key");
            Log.d("TAG",tempDate);
        }

        Button start_nomal_activity=(Button) findViewById(R.id.start_nomal_activity);
        Button start_dialog_activity=(Button) findViewById(R.id.start_dialog_activity);
        start_nomal_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Normal_Activity.class);
                startActivity(intent);
            }
        });
        start_dialog_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,DialogActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String tempData="something you just type";
        outState.putString("data_key",tempData);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("TAG","onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("TAG","onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("TAG","onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("TAG","onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("TAG","onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("TAG","onRestart");
    }
}
