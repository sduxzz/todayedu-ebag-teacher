/*
 * ExaminationSystem-Tea com.todayedu.ebg.teacher.DataSource
 * 2012 2012-7-2 ����12:09:09
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.DataSource;

import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.todayedu.ebag.teacher.Database.DataBaseHelper;

/**
 * @author zhenzxie
 * @param <T>
 * 
 */
public class DataSourceLoader extends AsyncTask<Context, Integer, List<Data>> {
	
	public static final String TAG = "DataSourceLoader";
	
	private ProgressDialog zDialog;
	protected BaseLocalDS zDataSource;
	protected String zSql;
	protected String[] zSA;

	public DataSourceLoader(BaseLocalDS dataSource, Context context,
			String sql, String[] selectionArgs) {
	
		zDataSource = dataSource;
		zDialog = new ProgressDialog(context);
		zDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		zDialog.setTitle("loading");
		zDialog.setMessage("loading,wait please...");
		zDialog.setMax(100);
		zDialog.setIndeterminate(false);
		zDialog.setCancelable(true);
		zDialog.show();
		zSql = sql;
		zSA = selectionArgs;
	}


	/**
	 * @see android.os.AsyncTask#doInBackground(Params[])
	 */
	@Override
	protected List<Data> doInBackground(Context... params) {
	
		Context context = params[0];
		DataBaseHelper helper = new DataBaseHelper(context);
		return helper.query2MapList(zSql, zSA, this);
	}

	/**
	 * @see android.os.AsyncTask#onProgressUpdate(Progress[])
	 */
	@Override
	protected void onProgressUpdate(Integer... values) {
	
		zDialog.setProgress(values[0]);
	}
	
	public void onPublishProgress(int value) {
	
		publishProgress(value);
	}

	/**
	 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
	 */
	@Override
	protected void onPostExecute(List<Data> result) {
	
		zDialog.dismiss();

		zDataSource.store(result);
		zDataSource.notifyDataChange();
	}

	/**
	 * @return the zDataSource
	 */
	public BaseLocalDS getzDataSource() {
	
		return zDataSource;
	}
	
	/**
	 * @param zDataSource
	 *            the zDataSource to set
	 */
	public void setzDataSource(BaseLocalDS zDataSource) {
	
		this.zDataSource = zDataSource;
	}

}