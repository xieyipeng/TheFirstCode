package com.example.networktest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;

import java.net.URL;

import javax.xml.parsers.SAXParserFactory;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "TAG_MainActivity";
    TextView responseTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button sendRequestButton=findViewById(R.id.send_request_Button);
        responseTest=findViewById(R.id.response_text_TextView);
        sendRequestButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.send_request_Button){
            Log.d(TAG, "onClick: success!");
            //sendRequestWithHttpURLConnection();
            sendRequestWithOkHttp();
        }
    }

    private void sendRequestWithOkHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                OkHttpClient client=new OkHttpClient();
                Request request=new Request.Builder()
                        .url("http://10.0.0.0/get_data.xml")
                        .build();
                    Response response=client.newCall(request).execute();
                    String responseData=response.body().string();
                    parseXMLWithSAX(responseData);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void parseXMLWithSAX(String xmlData){
        try{
            SAXParserFactory factory=SAXParserFactory.newInstance();
            XMLReader xmlReader=factory.newSAXParser().getXMLReader();
            ContentHandler contentHandler=new ContentHandler();
            xmlReader.setContentHandler(contentHandler);
            xmlReader.parse(new InputSource(new StringReader(xmlData)));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void parseXMLWithPull(String xmlData){
        try{
            XmlPullParserFactory factory=XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser=factory.newPullParser();
            xmlPullParser.setInput(new StringReader(xmlData));
            int eventType=xmlPullParser.getEventType();
            String id="";
            String name="";
            String version="";
            while (eventType!=XmlPullParser.END_DOCUMENT){
                String nodeName=xmlPullParser.getName();
                switch (eventType){
                    //开始解析某个节点
                    case XmlPullParser.START_TAG: {
                        if ("id".equals(nodeName)) {
                            id = xmlPullParser.nextText();
                        } else if ("name".equals(nodeName)) {
                            name = xmlPullParser.nextText();
                        } else if ("version".equals(nodeName)) {
                            version = xmlPullParser.nextText();
                        }
                        break;
                    }
                    case XmlPullParser.END_TAG:{
                        if ("app".equals(nodeName)){
                            Log.d(TAG, "parseXMLWithPull: id is "+id);
                            Log.d(TAG, "parseXMLWithPull: name is "+name);
                            Log.d(TAG, "parseXMLWithPull: version is "+version);
                        }
                            break;
                    }
                    default:
                        break;
                }
                eventType=xmlPullParser.next();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void sendRequestWithHttpURLConnection(){
        //开启线程来发起网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection=null;
                BufferedReader reader=null;
                try {
                    Log.d(TAG, "run: try run");
                    URL url=new URL("http://www.baidu.com");
                    connection=(HttpURLConnection)url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream inputStream=connection.getInputStream();
                    String input=inputStream.toString();
                    Log.d(TAG, "input:"+input);
                    //下面对获取到的输入流进行读取
                    reader=new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder response=new StringBuilder();
                    Log.d(TAG, "reader:"+reader);
                    Log.d(TAG, "reader.readLine():"+reader.readLine());
                    String line;
                    while ((line=reader.readLine())!=null){
                        Log.d(TAG, "run: while");
                        response.append(line);
                    }
                    showResponse(response.toString());
                } catch (Exception e) {
                    Log.d(TAG, "run: catch");
                    e.printStackTrace();
                }finally {
                    Log.d(TAG, "run: finally");
                    if (reader!=null){
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (connection!=null){
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }

    private void showResponse(final String response){
        Log.d(TAG, "showResponse: success!");
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //在这里进行UI操作，将结果显示到界面上
                responseTest.setText(response);
            }
        });
    }
}
