/*
 * ExaminationSystem-Tea com.todayedu.examinationsystem.teacher.Util
 * 2012 2012-6-1 ����10:12:47
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.Database;

import java.lang.reflect.Field;
import java.sql.Blob;
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
import com.todayedu.ebag.teacher.DataSource.DataObj.CEP;
import com.todayedu.ebag.teacher.DataSource.DataObj.EClass;
import com.todayedu.ebag.teacher.DataSource.DataObj.ES;
import com.todayedu.ebag.teacher.DataSource.DataObj.Exam;
import com.todayedu.ebag.teacher.DataSource.DataObj.Problem;
import com.todayedu.ebag.teacher.DataSource.DataObj.SP;
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
	
		Class<?>[] classes = { CEP.class, EClass.class, ES.class, Exam.class,
				Problem.class, SP.class, Student.class };
		TableHelper.createTablesByClasses(db, classes);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	
		Class<?>[] classes = { CEP.class, EClass.class, ES.class, Exam.class,
				Problem.class, SP.class, Student.class };
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
			Column column = (Column) field.getAnnotation(Column.class);
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
		String[] keys = ds.getAdapter().getzKeys();
		Cursor cursor = db.rawQuery(sql, selectionArgs);
		try {
			Field field;
			Data entity;
			int count = cursor.getCount();
			int num = 0;
			while (cursor.moveToNext()) {
				num++;
				entity = cl.newInstance();
				for (String key : keys) {
					field = cl.getDeclaredField(key);
					field.setAccessible(true);
					Class<?> fieldType = field.getType();
					int c = cursor.getColumnIndex(key);
					if (c < 0) {
						continue;
					} else if ((Integer.TYPE == fieldType)
							|| (Integer.class == fieldType)) {
						field.setInt(entity, cursor.getInt(c));
					} else if (String.class == fieldType) {
						field.set(entity, cursor.getString(c));
					} else if ((Long.TYPE == fieldType)
							|| (Long.class == fieldType)) {
						field.set(entity, Long.valueOf(cursor.getLong(c)));
					} else if ((Float.TYPE == fieldType)
							|| (Float.class == fieldType)) {
						field.set(entity, Float.valueOf(cursor.getFloat(c)));
					} else if ((Short.TYPE == fieldType)
							|| (Short.class == fieldType)) {
						field.set(entity, Short.valueOf(cursor.getShort(c)));
					} else if ((Double.TYPE == fieldType)
							|| (Double.class == fieldType)) {
						field.set(entity, Double.valueOf(cursor.getDouble(c)));
					} else if (Blob.class == fieldType) {
						field.set(entity, cursor.getBlob(c));
					} else if (Character.TYPE == fieldType) {
						String fieldValue = cursor.getString(c);
						
						if ((fieldValue != null) && (fieldValue.length() > 0)) {
							field.set(entity,
									Character.valueOf(fieldValue.charAt(0)));
						}
					}
				}
				list.add(entity);
				loader.onPublishProgress(num * 10 / count * 10);
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