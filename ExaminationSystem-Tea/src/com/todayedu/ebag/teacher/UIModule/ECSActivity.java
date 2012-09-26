/*
 * ExaminationSystem-Tea com.todayedu.examinationsystem.teacher.UIModule
 * 2012 2012-5-31 锟斤拷锟斤拷4:13:06
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.UIModule;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;

import com.todayedu.ebag.teacher.R;
import com.todayedu.ebag.teacher.DataSource.DataObj.Answer;

/**
 * 某个学生的要批改试卷的界面,包括题目列表界面和题目界面
 * 
 * @author zhenzxie
 * @see ECSFragment
 * @see ECSSFragment
 * 
 */
public class ECSActivity extends BaseActivity {
	
	private boolean issave = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ecs);
	}
	
	@Override
	public void onBackPressed() {
	
		super.onBackPressed();
		this.finish();
	}

	public void onComfirm(View view) {
	
		ECSSFragment fragment2 = (ECSSFragment) this.getFragmentManager()
		        .findFragmentById(R.id.ecs_eccsf);
		if (fragment2.onConfirm()) {
			String picOfTeacherUrl = fragment2.getPicOfTeacherUrl();
			String textOfTeacher = fragment2.getTextOfTeacher();
			String pointStr = fragment2.getPoint();
			double point = 0;
			try {
				point = Double.parseDouble(pointStr);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				showToast("请输入正确得分");
				return;
			}
			ECSFragment fragment = (ECSFragment) this.getFragmentManager()
			        .findFragmentById(R.id.ecs_ecsf);
			fragment.onConfirm(picOfTeacherUrl, textOfTeacher, point);
			showToast("成功保存");
			issave = true;
		}
	}
	
	public void onPrevious(View view) {
	
		if (!issave) {
			new AlertDialog.Builder(this)
			        .setMessage("没保存就要离开吗？")
			        .setPositiveButton(R.string.comm_confirm,
			                new OnClickListener() {
				                
				                @Override
				                public void onClick(DialogInterface dialog,
				                        int which) {
				                
					                previous();
				                }
			                }).setNegativeButton(R.string.comm_cancel, null)
			        .show();
		} else {
			issave = false;
			previous();
		}
	}
	
	public void onNext(View view) {

		if (!issave) {
			new AlertDialog.Builder(this)
			        .setMessage("没保存就要离开吗？")
			        .setPositiveButton(R.string.comm_confirm,
			                new OnClickListener() {
				                
				                @Override
				                public void onClick(DialogInterface dialog,
				                        int which) {
				                
					                next();
				                }
			                }).setNegativeButton(R.string.comm_cancel, null)
			        .show();
		} else {
			issave = false;
			next();
		}
	}
	
	public void changeECSS(Answer answer, boolean canPrevious, boolean canNext) {
	
		ECSSFragment fragment = (ECSSFragment) this.getFragmentManager()
		        .findFragmentById(R.id.ecs_eccsf);
		if (fragment == null || answer == null)
			return;
		fragment.resetECSS(answer, canPrevious, canNext);
	}

	private void previous() {
	
		ECSFragment fragment = getEcsFragment();
		fragment.onPrevious();
	}

	private void next() {
	
		ECSFragment fragment = getEcsFragment();
		fragment.onNext();
	}
	
	private ECSFragment getEcsFragment() {
	
		ECSFragment fragment = (ECSFragment) this.getFragmentManager()
		        .findFragmentById(R.id.ecs_ecsf);
		return fragment;
	}
}
