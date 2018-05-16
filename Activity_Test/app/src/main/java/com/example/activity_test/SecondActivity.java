package com.example.activity_test;

import android.content.Context;
import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends BaseActivity {

    public static void actionStart(Context context,String data1,String data2) {
        Intent intent = new Intent(context, SecondActivity.class);
        intent.putExtra("param1", data1);
        intent.putExtra("param2", data2);
        context.startActivity(intent);
    }
//    重写back方法
    @Override
    public void onBackPressed() {
        Intent intent=new Intent();
        intent.putExtra("data_return","hello MainActivity");
        setResult(RESULT_OK,intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);

        Log.d("nihao","Task id is"+getTaskId());

//        传递字符串
//        Intent intent=getIntent();
//        String data=intent.getStringExtra("extra_data");
//        Log.d("SecondActivity",data);

        Button button1 = (Button) findViewById(R.id.打电话);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:15035081949"));
                startActivity(intent);
            }
        });

        Button button2 = (Button) findViewById(R.id.向上传递);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.putExtra("data_return","hello MainActivity");
                setResult(RESULT_OK,intent);
                finish();
            }
        });

        Button button3 = (Button) findViewById(R.id.button_3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SecondActivity.this,ThirdActivity.class);
                startActivity(intent);
            }
        });
    }
}
