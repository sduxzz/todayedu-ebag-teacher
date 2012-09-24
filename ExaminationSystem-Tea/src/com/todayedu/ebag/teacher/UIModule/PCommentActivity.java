/*
 * ExaminationSystem-Tea com.todayedu.ebag.teacher.UIModule
 * 2012 2012-7-2 ï¿½ï¿½ï¿½ï¿½6:43:21
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.UIModule;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.todayedu.ebag.teacher.R;
import com.todayedu.ebag.teacher.TempData;

/**
 * ½²ÆÀÊÔ¾íµÄÌâÄ¿ÁÐ±í
 * 
 * @author zhenzxie
 * 
 */
public class PCommentActivity extends BaseActivity {

	/**
	 * @see com.todayedu.ebag.teacher.UIModule.MonitoredActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pcomment);
		Log.i(TAG, "onCreate");
	}
	
	/**
	 * @see android.app.Activity#onBackPressed()
	 */
	@Override
	public void onBackPressed() {
	
		super.onBackPressed();
		this.finish();
		TempData.clear();
	}

	public void onPrevious(View view) {
	
		TempData.moveToPrevious();
		changePCC();
	}
	
	public void onLabel(View view) {
	
		PCommentFragment fragment = (PCommentFragment) this
		        .getFragmentManager().findFragmentById(R.id.pc_pcf);
		fragment.onLabel();
		if (!TempData.isLast()) {
			onNext(null);
		} else {
			changePCC();
		}
	}
	
	public void onNext(View view) {
	
		TempData.moveToNext();
		changePCC();
	}
	
	public void changePCC() {
	
		PCCFragment fragment = (PCCFragment) this.getFragmentManager()
		        .findFragmentById(R.id.pc_pccf);
		if (fragment == null)
			return;
		fragment.getAndSet();
		fragment.setButton();
	}
}
