package com.example.innovationandentrepreneurship;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.innovationandentrepreneurship.Adopter.ResultAdopter;
import com.example.innovationandentrepreneurship.base.ResultInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SearchResult extends AppCompatActivity {

    private List<ResultInfo> resultInfoList=new ArrayList<>();
    private android.support.v7.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        initResultInfo();
        initView();
        initDo();
        initClick();
    }

    private void initResultInfo() {
        String text=getIntent().getStringExtra("text");
        for (int i = 0; i < 20; i++) {
            double distance=300+new Random().nextDouble()*(200);
            double temp=( ( int )( distance * 100 + 0.5 ) ) / 100.0;
            String string=String .valueOf(temp)+"米";
            ResultInfo resultInfo=new ResultInfo(text+i,"简介",string);
            resultInfoList.add(resultInfo);
        }
    }

    private void initClick() {

    }

    private void initDo() {
        toolbar.setTitle("搜索结果");
        setSupportActionBar(toolbar);

        RecyclerView recyclerView=findViewById(R.id.result_RecycleView);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        ResultAdopter adopter=new ResultAdopter(resultInfoList);
        recyclerView.setAdapter(adopter);
    }

    private void initView() {
        toolbar=findViewById(R.id.toolbar);
    }
}
