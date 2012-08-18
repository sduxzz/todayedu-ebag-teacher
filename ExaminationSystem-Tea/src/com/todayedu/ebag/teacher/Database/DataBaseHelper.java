/*
 * ExaminationSystem-Tea com.todayedu.examinationsystem.teacher.Util
 * 2012 2012-6-1 ����10:12:47
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.Database;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.todayedu.ebag.teacher.DataSource.Data;
import com.todayedu.ebag.teacher.DataSource.DataSource;
import com.todayedu.ebag.teacher.DataSource.DataSourceLoader;
import com.todayedu.ebag.teacher.DataSource.DataObj.EClass;
import com.todayedu.ebag.teacher.DataSource.DataObj.Exam;
import com.todayedu.ebag.teacher.DataSource.DataObj.Problem;
import com.todayedu.ebag.teacher.DataSource.DataObj.Student;
import com.todayedu.ebag.teacher.Database.annotation.Column;

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
	
		Class<?>[] classes = { EClass.class, Exam.class, Problem.class,
		        Student.class };
		TableHelper.createTablesByClasses(db, classes);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	
		Class<?>[] classes = { EClass.class, Exam.class, Problem.class,
		        Student.class };
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

	public <T> long insert(Class<T> clazz, T entity, String tableName) {
	
		Log.d(TAG,
				"[insert]: inset into " + tableName + " " + entity.toString());
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();

		try {
			setContentValues(clazz, entity, cv);
			long row = db.insert(tableName, null, cv);
			return row;
		} catch (Exception e) {
			Log.d(TAG, "[insert] into DB Exception.");
			e.printStackTrace();
		} finally {
			if (db != null) {
				db.close();
			}
		}
		return 0L;
	}
	
	private <T> void setContentValues(Class<T> clazz, T entity, ContentValues cv)
			throws IllegalAccessException {
	
		Field[] allFields = clazz.getDeclaredFields();
		for (Field field : allFields) {
			if (!field.isAnnotationPresent(Column.class)) {
				continue;
			}
			field.setAccessible(true);
			Object fieldValue = field.get(entity);
			if (fieldValue == null)
				continue;
			Column column = field.getAnnotation(Column.class);
			cv.put(column.name(), fieldValue.toString());
		}
	}
	
	/**
	 * 
	 * @param sql
	 * @param selectionArgs
	 * @param loader
	 * @return
	 */
	public List<Data> query2MapList(String sql, String[] selectionArgs,
			DataSourceLoader loader) {
	
		Log.d(TAG, "[query2MapList]: " + sql);
		List<Data> list = new ArrayList<Data>();
		SQLiteDatabase db = this.getReadableDatabase();
		DataSource ds = loader.getzDataSource();
		Class<? extends Data> cl = ds.getzClass();
		Cursor cursor = db.rawQuery(sql, selectionArgs);
		try {
			Data entity;
			int count = cursor.getCount();
			int num = 0;
			if (cursor.moveToFirst()) {
				do {
					num++;
					entity = cl.newInstance();
					entity.fillData(cursor);
					list.add(entity);
					loader.onPublishProgress(num * 10 / count * 10);
				} while (cursor.moveToNext());
			}
		} catch (Exception e) {
			Log.e(TAG, "[query2MapList] from DB exception");
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			if (db != null) {
				db.close();
			}
		}
		return list;
	}

}