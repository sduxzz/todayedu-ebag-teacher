/*
 * ExaminationSystem-Tea com.todayedu.examinationsystem.teacher.Util
 * 2012 2012-6-1 锟斤拷锟斤拷10:12:47
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.todayedu.ebag.teacher.Constants.StateStr;
import com.todayedu.ebag.teacher.Parameters;
import com.todayedu.ebag.teacher.Parameters.ParaIndex;
import com.todayedu.ebag.teacher.DataSource.DataObj.Answer;
import com.todayedu.ebag.teacher.DataSource.DataObj.Problem;

/**
 * @author zhenzxie
 * 
 */
public class DataBaseHelper extends SQLiteOpenHelper {
	
	public static final String TAG = "DataBaseHelper";
	private static final String DBNAME = "todayedu.db";
	private static final int DBVERSION = 1;
	
	public DataBaseHelper(Context context) {
	
		super(context, DBNAME, null, DBVERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
	
		Class<?>[] classes = { Problem.class, Answer.class };
		TableHelper.createTablesByClasses(db, classes);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	
		Class<?>[] classes = { Problem.class, Answer.class };
		TableHelper.dropTablesByClasses(db, classes);
		onCreate(db);
	}
	
	@Override
	public void onOpen(SQLiteDatabase db) {
	
		super.onOpen(db);
		if (!db.isReadOnly()) {
			db.execSQL("PRAGMA foreign_keys=ON;");
		}
	}

	public <T> long insert(Class<T> clazz, T entity, String tableName,
	        ContentValues cv) {
	
		Log.i(TAG,
		        "[insert]: inset into " + tableName + " " + entity.toString());
		SQLiteDatabase db = this.getWritableDatabase();

		try {
			long row = db.insert(tableName, null, cv);
			return row;
		} catch (Exception e) {
			Log.i(TAG, "[insert] into DB Exception.");
			e.printStackTrace();
		} finally {
			if (db != null) {
				db.close();
			}
		}
		return 0L;
	}
	
	public String queryProState(int pid) {
	
		String state = null;
		String eid = Parameters.getStr(ParaIndex.EID_INDEX);
		String cid = Parameters.getStr(ParaIndex.CID_INDEX);
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query("PROBLEM", new String[] { "state" },
		        "pid = ? and eid = ? and cid = ?",
		        new String[] { String.valueOf(pid), eid, cid }, null, null,
		        null);
		if (cursor.moveToFirst()) {
			state = cursor.getString(cursor.getColumnIndex("state"));
		} else {
			state = StateStr.COMMENT;// 如果数据库没有存，则是未讲评的题
		}
		db.close();
		return state;
	}
	
	public Answer queryAnswer(int id, int sid, int pid) {
	
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db
		        .query("ANSWER", new String[] { "STATE", "POINT",
		                "TEXTOFTEACHER", "ANSWEROFTEA" },
		                "ID = ? and SID = ? and PID = ?",
		                new String[] { String.valueOf(id), String.valueOf(sid),
		                        String.valueOf(pid) }, null, null, null);
		Answer answer = null;
		if (cursor.moveToFirst()) {
			answer = new Answer();
			answer.setState(cursor.getString(cursor.getColumnIndex("STATE")));
			answer.setPoint(cursor.getDouble(cursor.getColumnIndex("POINT")));
			answer.setTextOfTeacher(cursor.getString(cursor
			        .getColumnIndex("TEXTOFTEACHER")));
			answer.setAnswerofTea(cursor.getString(cursor
			        .getColumnIndex("ANSWEROFTEA")));
		}
		db.close();
		return answer;
	}

	/**
	 * query a row by a special id
	 */
	public boolean isAnswerExist(int id) {
	
		String selection = "ID = ?";
		String[] selectionArgs = { Integer.toString(id) };
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query("ANSWER", new String[] { "ANSWEROFTEA" },
		        selection, selectionArgs, null, null, null);
		boolean result = cursor.moveToFirst();
		db.close();
		return result;
	}
	
	public boolean updateAnswer(ContentValues cv, int id) {
	
		String selection = "ID = ?";
		String[] selectionArgs = { Integer.toString(id) };
		SQLiteDatabase db = null;
		int count = -1;
		try {
			db = this.getWritableDatabase();
			count = db.update("ANSWER", cv, selection, selectionArgs);
		} catch (Exception e) {
			Log.d(TAG, "[update] DB Exception.");
			e.printStackTrace();
		} finally {
			if (db != null)
				db.close();
		}
		return count >= 1 ? true : false;
	}

}