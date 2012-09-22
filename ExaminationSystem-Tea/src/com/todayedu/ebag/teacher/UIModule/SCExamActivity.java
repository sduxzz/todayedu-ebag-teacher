/*
 * ExaminationSystem-Tea com.todayedu.examinationsystem.teacher.UIModule
 * 2012 2012-5-31 锟斤拷锟斤拷4:13:06
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.UIModule;

import java.util.List;

import org.ebag.net.response.ExamResponse;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.todayedu.ebag.teacher.Parameters;
import com.todayedu.ebag.teacher.R;
import com.todayedu.ebag.teacher.DataAdapter.BaseDataAdapter;
import com.todayedu.ebag.teacher.DataSource.Data;
import com.todayedu.ebag.teacher.DataSource.SCExamDS;
import com.todayedu.ebag.teacher.DataSource.DataObj.Exam;
import com.todayedu.ebag.teacher.Network.ResponseParseUtil;

/**
 * 考试情况（试卷名，试卷总分，试卷时间，题目列表）
 * 
 * @author zhenzxie
 * @deprecated
 * @see SChooseActivity
 */
public class SCExamActivity extends BaseActivity {
	
	private BaseDataAdapter adapter;
	private SCExamDS ds;
	private TextView tv_2;
	private TextView tv_4;
	private TextView tv_6;
	private ListView lv;
	private final String[] keys = new String[] { "number", "point" };

	/**
	 * @see com.todayedu.ebag.teacher.UIModule.MonitoredActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scexam);
		tv_2 = (TextView) findViewById(R.id.scexam_tv2);
		tv_4 = (TextView) findViewById(R.id.scexam_tv4);
		tv_6 = (TextView) findViewById(R.id.scexam_tv6);
		Exam exam = Parameters.getExam();
		tv_2.setText(exam.getEname());
		tv_4.setText(String.valueOf(exam.getTotal()) + "分");
		tv_6.setText(String.valueOf(exam.getEtime()));
		

		ds = new SCExamDS(this);
		ds.load(this);
		addLifeCycleListener(ds);
		
		adapter = new BaseDataAdapter(this, ds, R.layout.lv_2, new int[] {
		        R.id.lv2_tv_1, R.id.lv2_tv_2, }, keys);
		ds.addObserver(adapter);
		
		lv = (ListView) findViewById(R.id.scexam);
		View headerView = HeaderViewFactory.createHeaderView2(this,
				 R.array.exam_preview);
		resetListView(lv, headerView, adapter);
	}
	
	@Override
	public void onLoadSuccess(Object object) {
	
		super.onLoadSuccess(object);
		ExamResponse examResponse = (ExamResponse) object;
		final List<Data> list = ResponseParseUtil
		        .parseExamResponse2ProblemList(examResponse,
		                SCExamActivity.this);
		ds.setList(list);
		ds.createMaps(keys);
		SCExamActivity.this.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
			
				ds.notifyDataChange();
			}
		});
	}
}
