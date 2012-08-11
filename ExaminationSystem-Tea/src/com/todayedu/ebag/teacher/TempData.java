/*
 * ExaminationSystem-Tea com.todayedu.ebag.teacher TempData.java
 * 2012 2012-7-7 下午8:16:39
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher;

import java.lang.reflect.Field;

import com.todayedu.ebag.teacher.DataSource.Data;
import com.todayedu.ebag.teacher.DataSource.DataSource;

/**
 * @author zhenzxie
 * 
 */
public class TempData {
	
	private static DataSource zDataSource;
	private static int zIndex;/* rang from 0 to zDataSource.getData().size()-1 */
	
	public static void storeData(DataSource dataSource, int index) {
	
		zDataSource = dataSource;
		zIndex = index;
	}

	public static String getCurrent(String key) {
	
		return get(zIndex, key);
	}
	
	/*
	 * because this method was only called when app want to reset the state of
	 * problem and state is presented as int in the database,so the type of
	 * value is int.
	 */
	public static void setState(int value) {
	
		set(zIndex, value);
	}

	public static void moveToNext() {
	
		zIndex++;
	}
	
	public static void moveToPrevious() {
	
		zIndex--;
	}

	public static boolean isFirst() {
	
		return zIndex == 0;
	}

	public static boolean isLast() {
	
		return zIndex == zDataSource.pick().size() - 1;
	}
	
	private static String get(int index, String key) {
	
		Data data = zDataSource.pick().get(index);
		Class<? extends Data> cl = data.getClass();
		Field field = null;
		String value = null;
		try {
			field = cl.getDeclaredField(key);
			field.setAccessible(true);
			value = field.get(data).toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}
	
	private static void set(int index, int value) {
	
		Data data = zDataSource.pick().get(index);
		Class<? extends Data> cl = data.getClass();
		Field field = null;
		try {
			field = cl.getDeclaredField("state");
			field.setAccessible(true);
			field.setInt(data, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}