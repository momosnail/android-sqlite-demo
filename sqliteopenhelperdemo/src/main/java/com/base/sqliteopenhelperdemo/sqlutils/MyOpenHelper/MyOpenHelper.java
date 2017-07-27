package com.base.sqliteopenhelperdemo.sqlutils.MyOpenHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyOpenHelper extends SQLiteOpenHelper {

    public MyOpenHelper(Context context) {
        /**
         * name 数据库的名字
         * factory 游标工厂  目的创建 cursor(结果集)
         * version 版本    版本从1开始  版本必须>=1
         */
        super(context, "person.db", null, 3);
    }

    /**
     * Called when the database is created for the first time
     * 当 数据第一次创建的时候调用
     * 该方法适合做 表结构的初始化
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        System.out.println("oncreate");
        //创建一个教info的表里面的字段有 id(android官方建议前面加下划线_) integer类型 主键  自动增长
        db.execSQL("create table info(_id integer primary key autoincrement,name varchar(20),phone varchar(20))");

    }

    /**
     * 当数据库需要升级的时候调用
     * 该方法适合做 表结构修改
     *
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        System.out.println("onUpgrade");
        // 比如 我给 info 表添加一列   phone
//		db.execSQL("alter table info add phone varchar(20)");

    }


}
