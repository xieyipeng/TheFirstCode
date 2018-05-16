package com.example.innovationandentrepreneurship;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText userNameEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        Button signInButton=findViewById(R.id.sign_in_Button);
        Button forgetPasswordButton=findViewById(R.id.forget_password_Button);
        Button signUpButton=findViewById(R.id.sign_up_Button);
        userNameEditText=findViewById(R.id.user_name_EditText);
        passwordEditText=findViewById(R.id.password_EditText);

        signInButton.setOnClickListener(this);
        forgetPasswordButton.setOnClickListener(this);
        signUpButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sign_in_Button:
                String account=userNameEditText.getText().toString();
                String password=passwordEditText.getText().toString();
                if (account.equals("123")&&password.equals("123")){
                    Intent sign_in=new Intent(SignInActivity.this,MainActivity.class);
                    startActivity(sign_in);
                    finish();
                }else {
                    Toast.makeText(this, "请输入正确密码", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.forget_password_Button:
                Intent forget_password=new Intent(SignInActivity.this,ForgetPasswordActivity.class);
                startActivity(forget_password);
                break;
            case R.id.sign_up_Button:
                AlertDialog.Builder builder=new AlertDialog.Builder(SignInActivity.this);
                builder.setMessage("请选择用户类型");
                builder.setTitle("App");
                builder.setPositiveButton("店家", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent sign_up=new Intent(SignInActivity.this,ShopSignUp.class);
                        startActivity(sign_up);
                    }
                });
                builder.setNegativeButton("球友", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent sign_up=new Intent(SignInActivity.this,CustomerSignUp.class);
                        startActivity(sign_up);
                    }
                });
                builder.show();
                break;
            default:
                break;
        }
    }
}
