package com.example.contactstest;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 *
 * Created by 解奕鹏 on 2018/1/29.
 */

public class MyProvider extends ContentProvider{

    public static final int TABLE1_DIR=0;
    public static final int TABLE1_ITME=1;
    public static final int TABLE2_DIR=2;
    public static final int TABLE2_ITME=3;

    private static UriMatcher uriMatcher;

    static {
        uriMatcher=new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("com.example.app.provider","table1",TABLE1_DIR);
        uriMatcher.addURI("com.example.app.provider","table1/#",TABLE1_ITME);
        uriMatcher.addURI("com.example.app.provider","table2",TABLE2_DIR);
        uriMatcher.addURI("com.example.app.provider","table2/#",TABLE2_ITME);
    }

    /**
     * 在这里完成数据库的创建和升级操作
     * 只有当存在ContentProvider尝试访问我们程序中的数据时内容器才会初始化
     * @return true表示成功，false表示失败
     */
    @Override
    public boolean onCreate() {
        return false;
    }

    /**
     * 从内容器中查询数据
     * @param uri 确定查询那张表
     * @param projection 确定查询那一列
     * @param selection 用来约束查询哪一行
     * @param selectionArgs 用来约束查询哪一行
     * @param sortOrder 对结果进行排序
     * @return 查询结果在 Cursor 对象中返回
     */
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        switch (uriMatcher.match(uri)){
            case TABLE1_DIR:
                break;
            case TABLE1_ITME:
                break;
            case TABLE2_DIR:
                break;
            case TABLE2_ITME:
                break;
            default:
                break;
        }
        return null;
    }

    /**
     * 根据Uri返回MIME
     * @param uri 确定表
     * @return MIME
     */
    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)){
            case TABLE1_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.app.provider.table1";
            case TABLE1_ITME:
                return "vnd.android.cursor.item/vnd.com.example.app.provider.table1";
            case TABLE2_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.app.provider.table2";
            case TABLE2_ITME:
                return "vnd.android.cursor.item/vnd.com.example.app.provider.table2";
            default:
                break;
        }
        return null;
    }

    /**
     * 插入
     * @param uri 确定添加那张表
     * @param values 待添加的数据存放在values参数中
     * @return 返回最新的 Uri 对象
     */
    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    /**
     * 删除
     * @param uri 确定表
     * @param selection 确定行
     * @param selectionArgs 确定行
     * @return 被删除的行数
     */
    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    /**
     * 更新
     * @param uri 确定表
     * @param values 新数据保存在values中
     * @param selection 约束行
     * @param selectionArgs 约束行
     * @return 受影响的行数作为返回值返回
     */
    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection,
                      @Nullable String[] selectionArgs) {
        return 0;
    }

}
