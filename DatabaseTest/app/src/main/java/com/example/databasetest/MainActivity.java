package com.example.databasetest;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private MyDatabaseHelper myDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button createDatabaseButton=findViewById(R.id.create_database_Button);
        Button addDatabaseButton=findViewById(R.id.add_database_Button);
        Button updateDatabaseButton=findViewById(R.id.update_database_Button);
        Button deleteDatabaseButton=findViewById(R.id.delete_database_Button);
        Button queryDatabaseButton=findViewById(R.id.query_database_Button);

        myDatabaseHelper=new MyDatabaseHelper(this,"BookStore.db",
                null,2);

        createDatabaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDatabaseHelper.getWritableDatabase();
            }
        });
        addDatabaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase sqLiteDatabase=myDatabaseHelper.getWritableDatabase();
                ContentValues values=new ContentValues();
                //第一条数据
                values.put("name","the Da Vinci Code");
                values.put("author","Dan Brown");
                values.put("pages",456);
                values.put("price",16.96);
                sqLiteDatabase.insert("Book",null,values);
                values.clear();
                //插入第二条数据
                values.put("name","the Lost Symbol");
                values.put("author","Dan Brown");
                values.put("pages",510);
                values.put("price",19.96);
                sqLiteDatabase.insert("Book",null,values);
            }
        });
        updateDatabaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase sqLiteDatabase=myDatabaseHelper.getWritableDatabase();
                ContentValues values=new ContentValues();
                values.put("price",10.99);
                sqLiteDatabase.update("Book",values,"name = ?",new String[]
                        {"The Da Vinci Code "});
            }
        });
        deleteDatabaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase sqLiteDatabase=myDatabaseHelper.getWritableDatabase();
                sqLiteDatabase.delete("Book","pages > ?",new String[]
                        {"500"});
            }
        });
        queryDatabaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase sqLiteDatabase=myDatabaseHelper.getWritableDatabase();
                Cursor cursor=sqLiteDatabase.query("Book",null,null,
                        null,null,null,null);
                if (cursor.moveToFirst()){
                    do {
                        //遍历Cursor对象
                        String name=cursor.getString(cursor.getColumnIndex("name"));
                        String author=cursor.getString(cursor.getColumnIndex("author"));
                        int pages=cursor.getInt(cursor.getColumnIndex("pages"));
                        double price=cursor.getDouble(cursor.getColumnIndex("price"));
                        Log.d("MainActivity", "book name is:"+name);
                        Log.d("MainActivity", "book author is:"+author);
                        Log.d("MainActivity", "book pages is:"+pages);
                        Log.d("MainActivity", "book price is:"+price);
                    }while (cursor.moveToNext());
                }
                cursor.close();
            }
        });
    }
}
