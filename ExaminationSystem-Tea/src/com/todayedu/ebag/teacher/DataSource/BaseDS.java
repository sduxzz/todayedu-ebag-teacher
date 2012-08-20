/*
 * ExaminationSystem-Tea com.todayedu.examinationsystem.teacher.DataSource
 * 2012 2012-5-31 ÏÂÎç3:30:23
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.DataSource;

import java.util.Iterator;

import android.content.Context;
import android.widget.Toast;

import com.todayedu.ebag.teacher.R;
import com.todayedu.ebag.teacher.Network.NetWorkUtil;



/**
 * @author zhenzxie
 * 
 */
public abstract class BaseDS extends DataSource {
	
	public BaseDS(Class<? extends Data> cl) {
	
		super(cl);
	}

	/**
	 * 
	 * @see com.todayedu.ebag.teacher.DataSource.DataSource#save(android.content.Context)
	 */
	@Override
	public boolean save(Context context) {
	
		Iterator<Data> iterator = zList.iterator();
		boolean result = true;
		while (iterator.hasNext()) {
			if (result) {
				result = iterator.next().save(context);
			} else {
				iterator.next().save(context);
			}
		}
		return result;
	}
	
	/**
	 * load the data from the local database.
	 * 
	 * @param context
	 */
	public abstract void localload(Context context);
	
	/**
	 * down load the data from server
	 * 
	 * @param context
	 */
	public abstract void download(Context context);
	
	/**
	 * load the data from server if network is available or local database if
	 * there isn't network connection.
	 * 
	 * @param context
	 */
	public final void load(Context context) {
	
		if (NetWorkUtil.isConnected(context)) {
			download(context);
		} else {
			Toast.makeText(context, R.string.sp_networkalter, Toast.LENGTH_LONG)
					.show();
			localload(context);
		}
	}

	/**
	 * the subclass can call this method for loading the data from local
	 * database conveniently.
	 * 
	 * @param context
	 * @param sql
	 * @param selectArgs
	 */
	protected void localload(Context context, String sql, String[] selectArgs) {
	
		DataSourceLoader loader = new DataSourceLoader(this, context,
				sql, selectArgs);
		loader.execute(context);
	}
}