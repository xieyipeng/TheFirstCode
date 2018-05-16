package com.example.recycleviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private List<Fruit> fruitList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFruits();

        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recycler_view);

        StaggeredGridLayoutManager layoutManager=new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);

//        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        recyclerView.setLayoutManager(layoutManager);
        FruitAdapter adapter=new FruitAdapter(fruitList);
        recyclerView.setAdapter(adapter);
    }

    private void initFruits() {
        for(int i=0;i<2;i++){
            Fruit apple=new Fruit(getRandomLengthName("apple"),R.drawable.one);
            fruitList.add(apple);
            Fruit banana=new Fruit(getRandomLengthName("banana"),R.drawable.two);
            fruitList.add(banana);
            Fruit orange=new Fruit(getRandomLengthName("orange"),R.drawable.three);
            fruitList.add(orange);
            Fruit watermelon=new Fruit(getRandomLengthName("watermelon"),R.drawable.five);
            fruitList.add(watermelon);
            Fruit pear=new Fruit(getRandomLengthName("pear"),R.drawable.five);
            fruitList.add(pear);
            Fruit grape=new Fruit(getRandomLengthName("grape"),R.drawable.six);
            fruitList.add(grape);
            Fruit pineapple=new Fruit(getRandomLengthName("pineapple"),R.drawable.seven);
            fruitList.add(pineapple);
            Fruit strawberry=new Fruit(getRandomLengthName("strawberry"),R.drawable.eight);
            fruitList.add(strawberry);
            Fruit cherry=new Fruit(getRandomLengthName("cherry"),R.drawable.nine);
            fruitList.add(cherry);
            Fruit mango=new Fruit(getRandomLengthName("mango"),R.drawable.ten);
            fruitList.add(mango);
        }
    }

    private String getRandomLengthName(String name){
        Random random=new Random();
        int length=random.nextInt(20)+1;
        StringBuilder builder=new StringBuilder();
        for(int i=1;i<length;i++){
            builder.append(name);
        }
        return builder.toString();
    }
}
