package com.example.uiwidgettest;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private ImageView imageView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button=(Button)findViewById(R.id.AlertDialog);

//        editText=(EditText)findViewById(R.id.edit_text) ;
        imageView=(ImageView)findViewById(R.id.image_view) ;
//        progressBar=(ProgressBar)findViewById(R.id.progress_bar) ;

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//AlertDialog
                AlertDialog.Builder dialog=new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("this is dialog");  //dialog会话
                dialog.setMessage("sonething important.");
                dialog.setCancelable(false);  //用户back键无效
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();

//                int progress=progressBar.getProgress();  //水平进度条增加
//                progress=progress+10;
//                progressBar.setProgress(progress);

//                if(progressBar.getVisibility()==View.GONE){  //圆形进度条的显示与隐藏
//                    progressBar.setVisibility(View.VISIBLE);
//                }
//                else
//                {
//                    progressBar.setVisibility(View.GONE);
//                }

//                String inputText=editText.getText().toString();  //输出输入框内写入的字符串
//                Toast.makeText(MainActivity.this,inputText,Toast.LENGTH_SHORT).show();
            }
        });

        Button button1=(Button)findViewById(R.id.image_view2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setImageResource(R.drawable.leopard);
            }
        });

        Button button2=(Button)findViewById(R.id.progress_dialog);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog progressDialog=new ProgressDialog(MainActivity.this);
                progressDialog.setTitle("this is ProgressDialog");
                progressDialog.setMessage("Loading...");
                progressDialog.setCancelable(true);
                progressDialog.show();
            }
        });

    }
}
