package com.base.sqliteopenhelperdemo.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.base.sqliteopenhelperdemo.domain.Person;
import com.base.sqliteopenhelperdemo.sqlutils.MyOpenHelper.MyOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class PersonDao {


	private MyOpenHelper helper;

	public PersonDao(Context context){

		helper = new MyOpenHelper(context);


	}


	/**
	 * 数据库的增加方法
	 */
	public boolean  add(String name,String phone){
		SQLiteDatabase db = helper.getWritableDatabase();
//		db.execSQL("insert into info(name,phone) values(?,?)", new Object[]{NULL,phone});

		/**
		 * table 表明
		 * nullColumnHack  null
		 */
		ContentValues values = new ContentValues();  //实际就是一个map    key:对应我们表的列名   value :值
		values.put("name", name);
		values.put("phone", phone);
		//实际底层原理 就是在组拼sql语句
		long result = db.insert("info", null, values);
		db.close();
		if (result == -1) {
			//说明插入失败
			return false;

		}else {
			return true;
		}

	}

	/**
	 * 数据库的删除方法
	 */
	public int del(String name){
		SQLiteDatabase db = helper.getReadableDatabase();
//		db.execSQL("delete from info where name=?", new Object[]{name});

		/**
		 * table 表名
		 * whereClause  删除条件
		 */
		//代表 影响了多少行
		int delete = db.delete("info", "name=?", new String[]{name});
		db.close();
		return delete;

	}

	/**
	 * 数据库的删除方法
	 */
	public int update(String name,String newphone){
		SQLiteDatabase db = helper.getWritableDatabase();
//		db.execSQL("update info set phone= ? where name= ?", new Object[]{newphone,name});

		/**
		 * table 表名
		 *
		 * whereClause 更新的条件
		 */
		ContentValues values = new ContentValues();
		values.put("phone", newphone);
		int update = db.update("info", values, "name=?", new String[]{name});
		db.close();
		return update;

	}

	/**
	 * 数据库的删除方法
	 */
	public List<Person> find(){
		List<Person> personLists  = new ArrayList<Person>();
		SQLiteDatabase db = helper.getWritableDatabase();
//		Cursor cursor = db.rawQuery("select * from info", null);

		/**
		 * table 表名
		 * columns  查询的列  具体查询的是哪一列
		 * selection 根据什么条件去
		 * selectionArgs
		 */
//		Cursor cursor = db.query("info", new String[]{"phone"}, "name=?", new String[]{name}, null, null, null);

		Cursor cursor = db.query("info", null, null, null, null, null, null);
		//对cursor 判断一下cursor
		if (cursor!=null&&cursor.getCount()>0) {
			while(cursor.moveToNext()){
				// 开始取 字段数据   sqliet 不区分数据类型  想要什么你就自己拿什么
				String name = cursor.getString(1);    //获取我们的name值
				String phone = cursor.getString(2);   //获取我们的phone
				Person person = new Person();

				System.out.println("name--"+name+"phone--"+phone);
				person.setName(name);
				person.setPhone(phone);
				// 把Person对象 加入到 personLists集合中.
				personLists.add(person);

			}
			cursor.close();
			db.close();


		}
		return personLists;


	}



}
