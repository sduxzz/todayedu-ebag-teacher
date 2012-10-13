/*
 * ExaminationSystem-Tea com.todayedu.ebag.teacher.UIModule
 * 2012 2012-7-2 锟斤拷锟斤拷6:43:21
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.UIModule;

import java.util.List;

import org.ebag.net.obj.exam.ProblemInfoObj;
import org.ebag.net.response.ExamResponse;

import android.os.Bundle;
import android.view.View;

import com.todayedu.ebag.teacher.R;
import com.todayedu.ebag.teacher.DataAdapter.BaseDataAdapter;
import com.todayedu.ebag.teacher.DataSource.PCommentDS;
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
		
		ds = new PCommentDS(this);
		ds.load(this);
		adapter = new BaseDataAdapter(this, ds, R.layout.lv_2,
		        new int[] { R.id.lv2_tv_1, R.id.lv2_tv_2 }, keys);
		ds.addObserver(adapter);
	}
	
	@Override
	public void onResume() {
	
		super.onResume();
		PCommentFragment fragment = this.getPCommentFragment();
		fragment.initPCommentFragment(adapter);
	}
	
	@Override
	public void onBackPressed() {
	
		super.onBackPressed();
		this.finish();
	}

	@Override
	public void onLoadSuccess(Object object) {
	
		ExamResponse examResponse = (ExamResponse) object;
		if (examResponse == null || examResponse.examList.size() == 0)
			return;
		List<ProblemInfoObj> list = examResponse.examList.get(0).getpInfoList();
		if (list == null || list.size() == 0)
			return;
		ds.setpInfoList(list);
		ds.createMaps(this, keys);
		ds.notifyDataChange(this);
		this.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
			
				onPosition(0);// 跳到第一题
			}
		});
	}

	public void onPrevious(View view) {
	
		ds.moveToPrevious();
		changePCC(ds.getCurrentProblem(), ds.canPrevious(), ds.canNext());
	}
	
	public void onLabel(View view) {
	
		ds.onLable(this);
		ds.notifyDataChange();
		changePCC(ds.getCurrentProblem(), ds.canPrevious(), ds.canNext());
	}

	public void onNext(View view) {
	
		ds.moveToNext();
		changePCC(ds.getCurrentProblem(), ds.canPrevious(), ds.canNext());
	}
	
	public void onPosition(int position) {
	
		if (ds.moveTo(position))
			changePCC(ds.getCurrentProblem(), ds.canPrevious(), ds.canNext());
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
	
	private BaseDataAdapter adapter;
	private PCommentDS ds;
	private final String[] keys = new String[] { "number", "state" };

	/**
	 * @return PCommentFragment
	 */
	private PCommentFragment getPCommentFragment() {
	
		PCommentFragment fragment = (PCommentFragment) this
		        .getFragmentManager().findFragmentById(R.id.pc_pcf);
		return fragment;
	}

}