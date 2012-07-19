/*
 * ExaminationSystem-Tea com.todayedu.examinationsystem.teacher.UIModule
 * 2012 2012-5-28 ÉÏÎç12:01:01
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.UIModule;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

/**
 * the base activity i used on my application it supplies some useful method
 * that call safely through other thread.
 * 
 * @author zhenzxie
 * 
 */
public class BaseActivity extends Activity implements OnItemClickListener {
	
	protected String TAG = this.getClass().getSimpleName();

	private class HandlerCallback implements Handler.Callback {
		
		@Override
		public boolean handleMessage(Message msg) {
		
			switch (msg.what) {
				case SHOWTOAST_SIG:
					Toast.makeText(BaseActivity.this, msg.obj.toString(),
					        msg.arg1).show();
					break;
				case DISMISSDIALOG_SIG:
					zDialog.dismiss();
					break;
			}
			return false;
		}
	}
	
	private static final int SHOWTOAST_SIG = 0;
	private static final int DISMISSDIALOG_SIG = 1;
	
	/**
     */
	protected Handler zHandler = new Handler(new HandlerCallback());
	
	/**
     */
	private ProgressDialog zDialog;
	
	/**
	 * {@link #showDialog(String, String)}
	 * 
	 * @param message
	 */
	public void showDialog(String message) {
	
		showDialog(null, message);
	}
	
	/**
	 * show a dialog with specific title and message
	 * 
	 * @param title
	 * @param message
	 */
	public void showDialog(final String title, final String message) {
	
		dissDialog();
		zHandler.post(new Runnable() {
			
			@Override
			public void run() {
			
				zDialog = ProgressDialog
				        .show(BaseActivity.this, title, message);
			}
		});
	}
	
	/**
	 * dismiss a dialog
	 */
	public void dissDialog() {
	
		if (zDialog != null && zDialog.isShowing())
			zHandler.sendEmptyMessage(DISMISSDIALOG_SIG);
	}
	
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
	public void showToast(String message, int duration) {
	
		Message msg = zHandler.obtainMessage(SHOWTOAST_SIG, message);
		msg.arg1 = duration;
		zHandler.sendMessage(msg);
	}
	
	public void start(Class<?> cls) {
	
		Intent intent = new Intent(this, cls);
		startActivity(intent);
	}

	@Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
            long id) {
    
    }
}
