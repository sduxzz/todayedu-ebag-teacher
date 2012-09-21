/*
 * ExaminationSystem-Tea com.todayedu.ebag.teacher TempData.java
 * 2012 2012-7-7 涓嬪崍8:16:39
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher;

import java.lang.reflect.Field;
import java.util.Map;

import com.todayedu.ebag.teacher.DataSource.BaseDataSource;
import com.todayedu.ebag.teacher.DataSource.Data;
import com.todayedu.ebag.teacher.DataSource.PCommentDS;

/**
 * this class use to store {@link PCommentDS} momentarily
 * 
 * @author zhenzxie
 * 
 */
public class TempData {
	
	private static BaseDataSource zDataSource;
	private static int zIndex;/* rang from 0 to zDataSource.getData().size()-1 */
	
	public static void storeData(BaseDataSource dataSource, int index) {
	
		zDataSource = dataSource;
		zIndex = index;
	}
	
	public static void setIndex(int index) {
	
		if (index >= zDataSource.getList().size())
			return;
		zIndex = index;
	}

	public static String getCurrent(String key) {
	
		return get(zIndex, key);
	}
	
	/*
	 * because this method was only called when app want to reset the state of
	 * problem,so here the type of value is String.
	 */
	public static void setState(String value) {
	
		set(zIndex, value);
		zDataSource.notifyDataChange();
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
	
		return zIndex == zDataSource.getList().size() - 1;
	}
	
	// 发现这个方法真心是吃饱着撑着。。历史问题了。。
	private static String get(int index, String key) {
	
		Data data = zDataSource.getList().get(index);
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
	
	private static void set(int index, String value) {
	
		Data data = zDataSource.getList().get(index);
		data.setState(value);
		
		Map<String, String> map = zDataSource.getData().get(index);
		map.put("state", value);
	}
}