package com.example.innovationandentrepreneurship;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

public class SplashActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGHT = 2000; // 两秒后进入系统

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final View view = View.inflate(this, R.layout.activity_splash, null);
        setContentView(view);

//        new android.os.Handler().postDelayed(new Runnable() {
//            public void run() {
//                Intent mainIntent = new Intent(SplashActivity.this,
//                        SignInActivity.class);
//                SplashActivity.this.startActivity(mainIntent);
//                SplashActivity.this.finish();
//            }
//        }, SPLASH_DISPLAY_LENGHT);
        //渐变展示启动屏
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.3f,1.0f);
        alphaAnimation.setDuration(2000);
        view.startAnimation(alphaAnimation);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation arg0) {
                redirectTo();
            }
            @Override
            public void onAnimationRepeat(Animation animation) {}
            @Override
            public void onAnimationStart(Animation animation) {}
        });
    }

    /**
     * 跳转到...
     */
    private void redirectTo(){
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
        finish();
    }
}
