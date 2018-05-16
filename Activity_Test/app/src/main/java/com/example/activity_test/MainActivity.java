package com.example.activity_test;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("nihao","Task id is"+getTaskId());

        setContentView(R.layout.first_layout);  //加载布局
        Button button1 = (Button) findViewById(R.id.隐式intent);  //获取布局文件中定义的元素，点击按钮弹出Toast
        button1.setOnClickListener(new View.OnClickListener() {  //click 咔哒声+listener 听者==监听器
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);
//                Intent intent = new Intent("com.example.MainActivity.ACTION_START");
//                intent.addCategory("com.example.MainActivity.MY_CATEGORY");
            }
        });
        Button button2=(Button) findViewById(R.id.finish);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();  //结束当前活动
            }
        });
        Button button3=(Button)findViewById(R.id.toast);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "you clicked toast", Toast.LENGTH_SHORT).show();  //环境contest + Toast现实内容 + Toast显示时长
            }
        });
        Button button4=(Button)findViewById(R.id.显式intent活动二);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);
            }
        });
        Button button5=(Button) findViewById(R.id.百度);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.baidu.com"));
                startActivity(intent);
            }
        });
        Button button7=(Button) findViewById(R.id.活动三);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SecondActivity.actionStart(MainActivity.this,"data1","data2");
//                Intent intent=new Intent(MainActivity.this,ThirdActivity.class);
//                startActivity(intent);
            }
        });

//        传递字符串
//        Button button8=(Button) findViewById(R.id.传递字符串);
//        button8.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String data="hello SecondActivity";
//                Intent intent=new Intent(MainActivity.this,SecondActivity.class);
//                intent.putExtra("extra_data",data);
//                startActivity(intent);
//            }
//        });

        Button button8=(Button) findViewById(R.id.向上传递);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,SecondActivity.class);
                startActivityForResult(intent,1);
            }
        });
    }

//    向上传递 extra额外的
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 1:
                if(resultCode==RESULT_OK){
                    String returnedData=data.getStringExtra("data_return");
                    Log.d("MainActivity",returnedData);
                }
                break;
            default:
        }
    }

    @Override  //创造选择菜单
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);  //获得MenuInflater对象-菜单栏  create创造  menu菜单  Options选择
        return true;
    }
    @Override  //选项
    public boolean onOptionsItemSelected(MenuItem item) {  //Options选择  item项目  selected选择的
        switch (item.getItemId()) {
            case R.id.add_item:
                Toast.makeText(this, "you clicked Add", Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item:
                Toast.makeText(this, "you clicked Remove", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("onrestart","onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("huimie","onRestart");
    }
}