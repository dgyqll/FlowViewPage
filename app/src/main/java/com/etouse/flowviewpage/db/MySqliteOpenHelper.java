package com.etouse.flowviewpage.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySqliteOpenHelper extends SQLiteOpenHelper {
	


	public MySqliteOpenHelper(Context context) {
		super(context, "db", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		//新建表
		db.execSQL("create table t_pay_type(_id int primary key,_pid varchar(20),_name varchar(20),_used varchar(1))");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}

}
