package com.todayedu.ebag.teacher.Database;

import java.lang.reflect.Field;
import java.sql.Blob;
import java.util.ArrayList;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.todayedu.ebag.teacher.Database.annotation.Column;
import com.todayedu.ebag.teacher.Database.annotation.Id;
import com.todayedu.ebag.teacher.Database.annotation.Table;

public class TableHelper {
	
	private static final String TAG = "AHibernate";
	
	public static <T> void createTablesByClasses(SQLiteDatabase db,
	        Class<?>[] clazzs) {
	
		for (Class<?> clazz : clazzs)
			createTable(db, clazz);
	}
	
	public static <T> void dropTablesByClasses(SQLiteDatabase db,
	        Class<?>[] clazzs) {
	
		for (Class<?> clazz : clazzs)
			dropTable(db, clazz);
	}
	
	public static <T> void createTable(SQLiteDatabase db, Class<T> clazz) {
	
		String tableName = "";
		if (clazz.isAnnotationPresent(Table.class)) {
			Table table = (Table) clazz.getAnnotation(Table.class);
			tableName = table.name();
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("CREATE TABLE ").append(tableName).append(" (");
		
		// List<Field> allFields = TableHelper
		// .joinFields(clazz.getDeclaredFields(), clazz.getSuperclass()
		// .getDeclaredFields());
		Field[] allFields = clazz.getDeclaredFields();
		ArrayList<Column> hasfk = new ArrayList<Column>();
		for (Field field : allFields) {
			if (!field.isAnnotationPresent(Column.class)) {
				continue;
			}
			
			Column column = (Column) field.getAnnotation(Column.class);
			
			String columnType = "";
			if (column.type().equals(""))
				columnType = getColumnType(field.getType());
			else {
				columnType = column.type();
			}
			sb.append(column.name() + " " + columnType);
			
			if (column.length() != 0) {
				sb.append("(" + column.length() + ")");
			}
			if (!column.foreignKey().equals(""))
				hasfk.add(column);
			
			if (field.isAnnotationPresent(Id.class)) {
				Id id = (Id) field.getAnnotation(Id.class);
				if (id.isAutoIncrement())
					sb.append(" primary key autoincrement");
				else
					sb.append(" primary key");
			}
			
			sb.append(", ");
		}
		
		for (Column fk : hasfk) {
			sb.append("FOREIGN KEY (").append(fk.name())
			        .append(") REFERENCES ")
			        .append(fk.foreignKey()).append(", ");
		}
		
		sb.delete(sb.length() - 2, sb.length() - 1);

		sb.append(")");
		
		String sql = sb.toString();
		
		Log.d(TAG, "create table [" + tableName + "]: " + sql);
		
		db.execSQL(sql);
	}
	
	public static <T> void dropTable(SQLiteDatabase db, Class<T> clazz) {
	
		String tableName = "";
		if (clazz.isAnnotationPresent(Table.class)) {
			Table table = (Table) clazz.getAnnotation(Table.class);
			tableName = table.name();
		}
		String sql = "DROP TABLE IF EXISTS " + tableName;
		Log.d(TAG, "dropTable[" + tableName + "]:" + sql);
		db.execSQL(sql);
	}
	
	private static String getColumnType(Class<?> fieldType) {
	
		if (String.class == fieldType) {
			return "TEXT";
		}
		if ((Integer.TYPE == fieldType) || (Integer.class == fieldType)) {
			return "INTEGER";
		}
		if ((Long.TYPE == fieldType) || (Long.class == fieldType)) {
			return "BIGINT";
		}
		if ((Float.TYPE == fieldType) || (Float.class == fieldType)) {
			return "FLOAT";
		}
		if ((Short.TYPE == fieldType) || (Short.class == fieldType)) {
			return "INT";
		}
		if ((Double.TYPE == fieldType) || (Double.class == fieldType)) {
			return "DOUBLE";
		}
		if (Blob.class == fieldType) {
			return "BLOB";
		}
		
		return "TEXT";
	}
}