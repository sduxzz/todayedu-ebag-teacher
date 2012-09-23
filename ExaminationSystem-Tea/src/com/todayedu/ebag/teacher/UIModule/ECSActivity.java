/*
 * ExaminationSystem-Tea com.todayedu.examinationsystem.teacher.UIModule
 * 2012 2012-5-31 锟斤拷锟斤拷4:13:06
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.UIModule;

import android.os.Bundle;
import android.view.View;

import com.todayedu.ebag.teacher.R;
import com.todayedu.ebag.teacher.TempData;

/**
 * 某个学生的要批改试卷的界面
 * 
 * @author zhenzxie
 * 
 */
public class ECSActivity extends BaseActivity {
	
	/**
	 * @see com.todayedu.ebag.teacher.UIModule.MonitoredActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ecs);
	}
	

	public void onComfirm(View view) {
	
		ECSFragment fragment = (ECSFragment) this.getFragmentManager()
		        .findFragmentById(R.id.ecs_ecsf);
		if (fragment == null)
			return;
		fragment.onConfirm();
		if (!TempData.isLast()) {
			onNext(null);
		} else {
			changeECSS();
		}
	}
	
	public void onPrevious(View view) {
	
		TempData.moveToPrevious();
		changeECSS();
	}
	
	public void onNext(View view) {
	
		TempData.moveToNext();
		changeECSS();
	}
	
	public void changeECSS() {
	
		ECSSFragment fragment = (ECSSFragment) this.getFragmentManager()
		        .findFragmentById(R.id.ecs_eccsf);
		if (fragment == null)
			return;
		fragment.getAndSet();
		fragment.setButton();
	}
}
