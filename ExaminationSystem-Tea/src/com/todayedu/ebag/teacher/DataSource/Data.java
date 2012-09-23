/*
 * ExaminationSystem-Tea com.todayedu.ebag.teacher.DataSource.DBObj Data.java
 * 2012 2012-7-16 ÉÏÎç9:56:42
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.DataSource;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

/**
 * @author zhenzxie
 * 
 */
public abstract class Data {
	
	public String TAG = this.getClass().getSimpleName();

	public void save(Context context) {
	
	}
	
	public void fillData(final Cursor cursor) {
	
		// Class<? extends Data> cl = this.getClass();
		// Field field = null;
		//
		// for (String key : keys) {
		// try {
		// field = cl.getDeclaredField(key);
		// field.setAccessible(true);
		// Class<?> fieldType = field.getType();
		// int c = cursor.getColumnIndex(key);
		// if (c < 0) {
		// continue;
		// } else if ((Integer.TYPE == fieldType)
		// || (Integer.class == fieldType)) {
		// field.setInt(this, cursor.getInt(c));
		// } else if (String.class == fieldType) {
		// field.set(this, cursor.getString(c));
		// } else if ((Long.TYPE == fieldType)
		// || (Long.class == fieldType)) {
		// field.set(this, Long.valueOf(cursor.getLong(c)));
		// } else if ((Double.TYPE == fieldType)
		// || (Double.class == fieldType)) {
		// field.set(this, Double.valueOf(cursor.getDouble(c)));
		// }// this case only can appear the situation listed above,if you
		// // want to add some different situations,please add here with
		// // "else if"
		// } catch (SecurityException e) {
		// e.printStackTrace();
		// } catch (IllegalArgumentException e) {
		// e.printStackTrace();
		// } catch (NoSuchFieldException e) {
		// e.printStackTrace();
		// } catch (IllegalAccessException e) {
		// e.printStackTrace();
		// }
		// }
	}

	/**
	 * key must be a field of data
	 * 
	 * @param keys
	 * @return
	 */
	public Map<String, String> changeToMap(String[] keys) {
	
		Map<String, String> map = new HashMap<String, String>();
		
		for (String key : keys) {
			map.put(key, valueOfKey(key));
		}
		
		return map;
	}
	
	/**
	 * key must be a field of Data
	 * 
	 * @param key
	 * @return
	 */
	public String valueOfKey(String key) {
	
		Class<? extends Data> cl = this.getClass();
		String value = null;
		try {
			Field field = cl.getDeclaredField(key);
			field.setAccessible(true);
			value = field.get(this).toString();
		} catch (Exception e) {
			Log.i(TAG, e.getMessage());
		}
		return value;
	}
	
	// /** for {@link Problem} to inherite,it would be called at {@link
	// TempData} */
	// public void setState(String state) {
	//
	// }
}
