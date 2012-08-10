/*
 * ExaminationSystem-Tea com.todayedu.examinationsystem.teacher.UIModule
 * 2012 2012-5-31 ����4:13:06
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.UIModule;

import android.app.AlertDialog;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;

import com.todayedu.ebag.teacher.R;

/**
 * @author zhenzxie
 * 
 */
public class SChooseActivity extends BaseActivity {
	
	/**
	 * @see com.todayedu.ebag.teacher.UIModule.MonitoredActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.schoose);
	}
	
	public void onState(View view) {
	
		start(SCStateActivity.class);
	}
	
	public void onExam(View view) {

		start(SCExamActivity.class);
	}
	
	public void onStart(View view) {
	
		showAlertDialog("Do you want to start this exam ?", null, null);
	}
	
	public void onEnd(View view) {
	
		showAlertDialog("Do you want to end this exam ?", null, null);
	}

	/**
     * 
     */
	private void showAlertDialog(CharSequence message,
	        OnClickListener positiveListener, OnClickListener negativeListener) {
	
		new AlertDialog.Builder(this).setMessage(message)
		        .setPositiveButton(R.string.comm_confirm, positiveListener)
		        .setNegativeButton(R.string.comm_cancel, negativeListener)
		        .show();
	}
}
