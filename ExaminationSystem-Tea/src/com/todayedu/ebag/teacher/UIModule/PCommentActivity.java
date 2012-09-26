/*
 * ExaminationSystem-Tea com.todayedu.ebag.teacher.UIModule
 * 2012 2012-7-2 锟斤拷锟斤拷6:43:21
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.UIModule;

import android.os.Bundle;
import android.view.View;

import com.todayedu.ebag.teacher.R;
import com.todayedu.ebag.teacher.DataSource.DataObj.Problem;

/**
 * 讲评试卷界面，包括一个题目列表界面和题目界面
 * 
 * @author zhenzxie
 * @see PCommentFragment
 * @see PCCFragment
 */
public class PCommentActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pcomment);
	}
	
	@Override
	public void onBackPressed() {
	
		super.onBackPressed();
		this.finish();
	}

	public void onPrevious(View view) {
	
		PCommentFragment fragment = getPCommentFragment();
		fragment.onPrevious();
	}
	
	public void onLabel(View view) {
	
		PCommentFragment fragment = getPCommentFragment();
		fragment.onLabel();
	}

	public void onNext(View view) {
	
		PCommentFragment fragment = getPCommentFragment();
		fragment.onNext();
	}
	
	/**
	 * 修改PCCommentFragment
	 * 
	 * @param problem
	 * @param canPrevious
	 * @param canNext
	 */
	public void changePCC(Problem problem, boolean canPrevious, boolean canNext) {
	
		PCCFragment fragment = (PCCFragment) this.getFragmentManager()
		        .findFragmentById(R.id.pc_pccf);
		if (fragment == null || problem == null)
			return;
		fragment.resetPCC(problem, canPrevious, canNext);
	}
	
	/**
	 * @return PCommentFragment
	 */
	private PCommentFragment getPCommentFragment() {
	
		PCommentFragment fragment = (PCommentFragment) this
		        .getFragmentManager().findFragmentById(R.id.pc_pcf);
		return fragment;
	}

}
