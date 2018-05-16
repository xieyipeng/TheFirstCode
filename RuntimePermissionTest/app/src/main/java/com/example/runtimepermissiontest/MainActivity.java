package com.example.runtimepermissiontest;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button makeCallButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.make_call_button:
                /**
                 * 1、判断用户是否已经授权
                 *      相等表示已经授权
                 *
                 *  如果已经授权，则直接打电话
                 *  如果没有授权，则调用 ActivityCompat.requestPermissions() 函数
                 *      来申请权限
                 */
                if (ContextCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.CALL_PHONE},1);
                }else {
                    call();
                }
                break;
        }
    }

    private void call(){
        try{
            Intent intent=new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:10086"));
            startActivity(intent);
            Toast.makeText(this, "telePhone", Toast.LENGTH_SHORT).show();
        }catch (SecurityException e){
            e.printStackTrace();
        }
    }

    /**
     * 不论同意或者拒绝，都调用这个函数
     * @param requestCode 请求码
     * @param permissions 权限
     * @param grantResults 授权结果封装在这里面
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    call();
                }else {
                    Toast.makeText(this, "You denied the permission",
                            Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }
}