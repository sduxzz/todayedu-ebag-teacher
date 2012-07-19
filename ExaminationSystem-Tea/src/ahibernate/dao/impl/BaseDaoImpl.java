package ahibernate.dao.impl;

import java.lang.reflect.Field;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.todayedu.ebag.teacher.DataSource.DataSourceLoader;
import com.todayedu.ebag.teacher.Database.annotation.Column;
import com.todayedu.ebag.teacher.Database.annotation.Id;
import com.todayedu.ebag.teacher.Database.annotation.Table;

public class BaseDaoImpl<T> {
	
	private static final String TAG = "AHibernate";

	private SQLiteOpenHelper dbHelper;

	private String tableName;
	private String idColumn;

	private Class<T> clazz;
	private Field[] allFields;
	
	public BaseDaoImpl(SQLiteOpenHelper dbHelper, Class<T> c) {
	
		this.dbHelper = dbHelper;
		this.clazz = c;
		
		if (this.clazz.isAnnotationPresent(Table.class)) {
			Table table = (Table) this.clazz.getAnnotation(Table.class);
			this.tableName = table.name();
		}

		this.allFields = this.clazz.getDeclaredFields();
		for (Field field : this.allFields) {
			if (field.isAnnotationPresent(Id.class)) {
				Column column = (Column) field.getAnnotation(Column.class);
				this.idColumn = column.name();
				break;
			}
		}
		
		Log.d(TAG, "clazz:" + this.clazz + " tableName:" + this.tableName
		        + " idColumn:" + this.idColumn);
	}
	
	/**
	 * query a row by a special id
	 */
	public T get(int id) {
	
		String selection = this.idColumn + " = ?";
		String[] selectionArgs = { Integer.toString(id) };
		Log.d(TAG, "[get]: select * from " + this.tableName + " where "
		        + this.idColumn + " = '" + id + "'");
		List<T> list = find(null, selection, selectionArgs, null, null, null,
		        null);
		if ((list != null) && (list.size() > 0)) {
			return (T) list.get(0);
		}
		return null;
	}
	
	public List<T> rawQuery(String sql, String[] selectionArgs) {
	
		Log.d(TAG, "[rawQuery]: " + sql);
		
		List<T> list = new ArrayList<T>();
		SQLiteDatabase db = null;
		Cursor cursor = null;
		try {
			db = this.dbHelper.getReadableDatabase();
			cursor = db.rawQuery(sql, selectionArgs);
			
			getListFromCursor(list, cursor);
		} catch (Exception e) {
			Log.e(TAG, "[rawQuery] from DB Exception.");
			e.printStackTrace();
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
	
	public boolean isExist(String sql, String[] selectionArgs) {
	
		Log.d(TAG, "[isExist]: " + sql);
		
		SQLiteDatabase db = null;
		Cursor cursor = null;
		try {
			db = this.dbHelper.getReadableDatabase();
			cursor = db.rawQuery(sql, selectionArgs);
			if (cursor.getCount() > 0) {
				return true;
			}
		} catch (Exception e) {
			Log.e(TAG, "[isExist] from DB Exception.");
			e.printStackTrace();
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			if (db != null) {
				db.close();
			}
		}
		return false;
	}
	
	public List<T> find() {
	
		return find(null, null, null, null, null, null, null);
	}
	
	public List<T> find(String[] columns, String selection,
	        String[] selectionArgs, String groupBy, String having,
	        String orderBy, String limit) {
	
		Log.d(TAG, "[find]");
		
		List<T> list = new ArrayList<T>();
		SQLiteDatabase db = null;
		Cursor cursor = null;
		try {
			db = this.dbHelper.getReadableDatabase();
			cursor = db.query(this.tableName, columns, selection,
			        selectionArgs, groupBy, having, orderBy, limit);
			
			getListFromCursor(list, cursor);
		} catch (Exception e) {
			Log.e(TAG, "[find] from DB Exception");
			e.printStackTrace();
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
	
	private void getListFromCursor(List<T> list, Cursor cursor)
	        throws IllegalAccessException, InstantiationException {
	
		while (cursor.moveToNext()) {
			T entity = this.clazz.newInstance();
			
			for (Field field : this.allFields) {
				Column column = null;
				if (field.isAnnotationPresent(Column.class)) {
					column = (Column) field.getAnnotation(Column.class);
					
					field.setAccessible(true);
					Class<?> fieldType = field.getType();
					
					int c = cursor.getColumnIndex(column.name());
					if (c < 0) {
						continue; // ������ѭ���¸�����ֵ
					} else if ((Integer.TYPE == fieldType)
					        || (Integer.class == fieldType)) {
						field.set(entity, cursor.getInt(c));
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
			}
			
			list.add((T) entity);
		}
	}
	
	public long insert(T entity) {
	
		Log.d(TAG,
		        "[insert]: inset into " + this.tableName + " "
		                + entity.toString());
		SQLiteDatabase db = null;
		try {
			db = this.dbHelper.getWritableDatabase();
			ContentValues cv = new ContentValues();
			setContentValues(entity, cv, "create");
			long row = db.insert(this.tableName, null, cv);
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
	
	public void delete(int id) {
	
		SQLiteDatabase db = this.dbHelper.getWritableDatabase();
		String where = this.idColumn + " = ?";
		String[] whereValue = { Integer.toString(id) };
		
		Log.d(TAG, "[delete]: delelte from " + this.tableName + " where "
		        + where.replace("?", String.valueOf(id)));
		
		db.delete(this.tableName, where, whereValue);
		db.close();
	}
	
	public void delete(Integer... ids) {
	
		if (ids.length > 0) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < ids.length; i++) {
				sb.append('?').append(',');
			}
			sb.deleteCharAt(sb.length() - 1);
			SQLiteDatabase db = this.dbHelper.getWritableDatabase();
			String sql = "delete from " + this.tableName + " where "
			        + this.idColumn + " in (" + sb + ")";
			
			Log.d(TAG, "[delete]: " + sql);
			
			db.execSQL(sql, (Object[]) ids);
			db.close();
		}
	}
	
	public void update(T entity) {
	
		SQLiteDatabase db = null;
		try {
			db = this.dbHelper.getWritableDatabase();
			ContentValues cv = new ContentValues();
			
			setContentValues(entity, cv, "update");
			
			String where = this.idColumn + " = ?";
			int id = Integer.parseInt(cv.get(this.idColumn).toString());
			cv.remove(this.idColumn);
			
			Log.d(TAG,
			        "[update]: update " + this.tableName + " where "
			                + where.replace("?", String.valueOf(id)));
			
			String[] whereValue = { Integer.toString(id) };
			db.update(this.tableName, cv, where, whereValue);
		} catch (Exception e) {
			Log.d(TAG, "[update] DB Exception.");
			e.printStackTrace();
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	private void setContentValues(T entity, ContentValues cv, String type)
	        throws IllegalAccessException {
	
		for (Field field : this.allFields) {
			if (!field.isAnnotationPresent(Column.class)) {
				continue;
			}
			Column column = (Column) field.getAnnotation(Column.class);
			field.setAccessible(true);
			Object fieldValue = field.get(entity);
			if (fieldValue == null)
				continue;
			if (("create".equals(type))
			        && (field.isAnnotationPresent(Id.class))) {
				Id id = (Id) field.getAnnotation(Id.class);
				if (id.isAutoIncrement())
					continue;
			}
			cv.put(column.name(), fieldValue.toString());
		}
	}
	

	public List<Map<String, String>> query2MapList(String sql,
	        String[] selectionArgs, String[] columnName, String[] keys,
			DataSourceLoader loader) {
	
		Log.d(TAG, "[query2MapList]: " + sql);
		SQLiteDatabase db = null;
		Cursor cursor = null;
		List<Map<String, String>> retList = new ArrayList<Map<String, String>>();
		try {
			db = this.dbHelper.getReadableDatabase();
			cursor = db.rawQuery(sql, selectionArgs);
			int count = cursor.getCount();
			int num = 0;
			while (cursor.moveToNext()) {
				num++;
				Map<String, String> map = new HashMap<String, String>();
				int index = 0;
				for (String name : columnName) {
					map.put(keys[index++],
					        cursor.getString(cursor.getColumnIndex(name)));
				}
				retList.add(map);
				loader.onPublishProgress(num * 10 / count * 10);
			}
		} catch (Exception e) {
			Log.e(TAG, "[query2MapList] from DB exception");
			e.printStackTrace();
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			if (db != null) {
				db.close();
			}
		}
		
		return retList;
	}
	
	/**
	 * ��װִ��sql����.
	 * 
	 * @param sql
	 * @param selectionArgs
	 */
	public void execSql(String sql, Object[] selectionArgs) {
	
		SQLiteDatabase db = null;
		Log.d(TAG, "[execSql]: " + sql);
		try {
			db = this.dbHelper.getWritableDatabase();
			if (selectionArgs == null) {
				db.execSQL(sql);
			} else {
				db.execSQL(sql, selectionArgs);
			}
		} catch (Exception e) {
			Log.e(TAG, "[execSql] DB exception.");
			e.printStackTrace();
		} finally {
			if (db != null) {
				db.close();
			}
		}
	}
}