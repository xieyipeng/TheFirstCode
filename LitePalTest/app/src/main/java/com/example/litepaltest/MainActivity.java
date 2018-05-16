package com.example.litepaltest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private int a=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button createDatabaseButton = findViewById(R.id.create_database_Button);
        Button addDatabaseButton = findViewById(R.id.add_database_Button);
        Button updateDatabaseButton = findViewById(R.id.update_database_Button);
        Button deleteDatabaseButton = findViewById(R.id.delete_database_Button);
        Button queryDatabaseButton = findViewById(R.id.query_database_Button);

        createDatabaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connector.getDatabase();
            }
        });
        addDatabaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setName("The Da Vinci Code");
                book.setAuthor("Dan Brown");
                book.setPages(454);
                book.setPrice(16.96);
                book.setPress("Unknow");
                book.save();
            }
        });
        updateDatabaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Book book=new Book();
//                book.setName("The Lost Symbol");
//                book.setAuthor("Dan Brown");
//                book.setPages(510);
//                book.setPrice(19.95);
//                book.setPress("Unknow");
//                book.save();
//                book.setPrice(10.99);
//                book.save();
                Book book = new Book();
                book.setPrice(14.95);
                book.setPress("Anchor");
                book.updateAll("name = ? and author = ?", "The Lost Symbol",
                        "Dan Brown");
//                对 pages 列进行更新:
//                book.setToDefault("pages");
//                book.updateAll();
            }
        });
        deleteDatabaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataSupport.deleteAll(Book.class, "price < ?", "15");
            }
        });
        queryDatabaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Book> books = DataSupport.findAll(Book.class);
                for (Book book:books){
                    a++;
                    Log.d("MainActivity", "book name is"+book.getName());
                    Log.d("MainActivity", "book author is"+book.getAuthor());
                    Log.d("MainActivity", "book pages is"+book.getPages());
                    Log.d("MainActivity", "book price is"+book.getPrice());
                    Log.d("MainActivity", "book press is"+book.getPress());
                }
                Toast.makeText(MainActivity.this, "log:"+a, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
