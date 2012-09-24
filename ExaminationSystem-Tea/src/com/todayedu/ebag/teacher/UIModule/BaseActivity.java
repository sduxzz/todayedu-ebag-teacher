/*
 * ExaminationSystem-Tea com.todayedu.examinationsystem.teacher.UIModule
 * 2012 2012-5-28 上午12:01:01
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.UIModule;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.todayedu.ebag.teacher.DataSource.DSCallback;

/**
 * the base activity i used on my application it supplies some useful method
 * that call safely through other thread.
 * 
 * @author zhenzxie
 * 
 */
public class BaseActivity extends Activity implements DSCallback {
	
	protected String TAG = this.getClass().getSimpleName();

	/**
	 * {@link #showToast(String, int)}
	 * 
	 * @param message
	 */
	public void showToast(String message) {
	
		showToast(message, 0);
	}
	
	/**
	 * show a toast with specific message and duration
	 * 
	 * @param message
	 * @param duration
	 */
	public void showToast(final String message, final int duration) {
		
		this.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
			
				Toast.makeText(BaseActivity.this, message, duration).show();
			}
		});
	}
	
	/**
	 * {@link #showToast(int, int)}
	 * 
	 * @param resourceId
	 */
	public void showToast(int resourceId) {
	
		showToast(resourceId, 0);
	}
	
	/**
	 * show a toast with specific message and duration
	 * 
	 * @param resourceId
	 * @param duration
	 */
	public void showToast(final int resourceId, final int duration) {
	
		this.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
			
				Toast.makeText(BaseActivity.this, resourceId, duration).show();
			}
		});
	}

	public void start(Class<?> cls) {
	
		Intent intent = new Intent(this, cls);
		startActivity(intent);
	}

	/**
	 * @see com.todayedu.ebag.teacher.DataSource.DSCallback#onLoadSuccess(java.lang.Object)
	 */
	@Override
	public void onLoadSuccess(Object object) {
	
		Log.i(TAG, "onLoadSuccess");
	}
	
	/**
	 * @see com.todayedu.ebag.teacher.DataSource.DSCallback#onLoadFailed(java.lang.Throwable)
	 */
	@Override
	public void onLoadFailed(Throwable throwable) {
	
		Log.i(TAG, "onLoadFailed");
		if (throwable != null) {
			Log.i(TAG, throwable.getMessage());
		}
		showToast("加载数据失败");
	}

}
