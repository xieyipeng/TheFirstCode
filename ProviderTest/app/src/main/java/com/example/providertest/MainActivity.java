package com.example.providertest;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private String newId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addDataToBookButton=findViewById(R.id.add_data_Button);
        Button queryDataFromBookButton=findViewById(R.id.query_data_Button);
        Button updateBookButton=findViewById(R.id.update_data_Button);
        Button deleteDataFromBook=findViewById(R.id.delete_data_Button);

        addDataToBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri=Uri.parse("content://com.example.databasetest.provider/book");
                ContentValues contentValues=new ContentValues();
                contentValues.put("name","A Clash Kings");
                contentValues.put("author","George Martin");
                contentValues.put("pages",1040);
                contentValues.put("price",22.58);
                Uri newUri=getContentResolver().insert(uri,contentValues);
                newId=newUri.getPathSegments().get(1);
            }
        });
        queryDataFromBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri=Uri.parse("content://com.example.databasetest.provider/book");
                Cursor cursor=getContentResolver().query(uri,null,null,
                        null,null);
                if (cursor!=null){
                    while (cursor.moveToNext()){
                        String name=cursor.getString(cursor.getColumnIndex("name"));
                        String author=cursor.getString(cursor.getColumnIndex("author"));
                        int pages=cursor.getInt(cursor.getColumnIndex("pages"));
                        double price=cursor.getDouble(cursor.getColumnIndex("price"));
                        Log.d("MainActivity", "book name is "+name);
                        Log.d("MainActivity", "book author is "+author);
                        Log.d("MainActivity", "book pages is "+pages);
                        Log.d("MainActivity", "book price is "+price);
                    }
                    cursor.close();
                }
            }
        });
        updateBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri=Uri.parse("content://com.example.databasetest.provider/book/"+newId);
                ContentValues contentValues=new ContentValues();
                contentValues.put("name","A Storm of Swords");
                contentValues.put("pages",1216);
                contentValues.put("price",24.05);
                getContentResolver().update(uri,contentValues,null,null);
            }
        });
        deleteDataFromBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri=Uri.parse("content://com.example.databasetest.provider/book/"+newId);
                getContentResolver().delete(uri,null,null);
            }
        });

    }
}
