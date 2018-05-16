package com.example.webviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView webView=findViewById(R.id.web_WebView);

        webView.getSettings().setJavaScriptEnabled(true);
        //当从一个网页跳转另一个网页时，不是打开系统自带浏览器，而是仍留在WebView中显示
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://www.baidu.com");

    }
}
