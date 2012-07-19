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
import android.util.Log;


/**
 * @author zhenzxie
 *
 */
public abstract class Data {
	
	public String TAG = this.getClass().getName();

	public abstract void save(Context context);

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

}
