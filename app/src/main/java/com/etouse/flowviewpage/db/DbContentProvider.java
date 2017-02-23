package com.etouse.flowviewpage.db;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.etouse.flowviewpage.util.Constant;


public class DbContentProvider extends ContentProvider {

	private SQLiteDatabase db;
	UriMatcher uriMatcher = new UriMatcher(-1);
	{
		uriMatcher.addURI(Constant.UriDb, "t_pay_type", 1);
	}

	@Override
	public boolean onCreate() {
		MySqliteOpenHelper helper = new MySqliteOpenHelper(getContext());
		db = helper.getReadableDatabase();
		return true;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		String table = getTble(uri);
		long insert = db.insert(table, null, values);
		ContentUris contentUris = new ContentUris();
		Uri uri2 = contentUris.withAppendedId(uri, insert);
		return uri2;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		int delete = db.delete(getTble(uri), selection, selectionArgs);
		return delete;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
					  String[] selectionArgs) {
		int update = db.update(getTble(uri), values, selection, selectionArgs);
		return update;
	}
	
	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
						String[] selectionArgs, String sortOrder) {
		Cursor cursor = db.query(getTble(uri), projection, selection, selectionArgs, null, null, null, null);
		return cursor;
	}

	@Override
	public String getType(Uri uri) {
		return null;
	}
	
	private String getTble(Uri uri) {
		int match = uriMatcher.match(uri);
		String table = "";
		if(match == 1){
			table = "t_pay_type";
		}
		return table;
	}
	
	

}
