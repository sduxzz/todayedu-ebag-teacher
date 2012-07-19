/*
 * ExaminationSystem-Tea com.todayedu.examinationsystem.teacher.UIModule
 * 2012 2012-5-31 ����4:13:06
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.UIModule;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.todayedu.ebag.teacher.Parameters;
import com.todayedu.ebag.teacher.Parameters.ParaIndex;
import com.todayedu.ebag.teacher.R;
import com.todayedu.ebag.teacher.DataAdapter.BaseDataAdapter;
import com.todayedu.ebag.teacher.DataSource.BaseLocalDS;
import com.todayedu.ebag.teacher.DataSource.ExamListDS;
import com.todayedu.ebag.teacher.DataSource.DataObj.Exam;

/**
 * @author zhenzxie
 * 
 */
public class ChooseActivity extends MonitoredActivity {
	
	private BaseDataAdapter adapter;
	/**
	 * @see com.todayedu.ebag.teacher.UIModule.MonitoredActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sp);

		ListView1 elView = (ListView1) findViewById(R.id.el_examlistview);
		elView.setHeaderView(R.array.choose);
		elView.setOnItemClickListener(this);
		
		String[] keys = new String[] { "ename", "eid" };
		BaseLocalDS ds = new ExamListDS(Exam.class);
		ds.localload(this);
		adapter = elView.bindAdapter(ds, keys);
		addLifeCycleListener(adapter);
	}
	
	public void onExamSynch(View view) {
	
	}
	
	/**
	 * @see com.todayedu.ebag.teacher.UIModule.BaseActivity#onItemClick(android.widget.AdapterView,
	 *      android.view.View, int, long)
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
	        long id) {
	

		String eid = adapter.getzDataSource().pick().get(position - 1)
				.valueOfKey("eid");
		Parameters.add(eid, ParaIndex.EID_INDEX);
		start(CFunctionActivity.class);
	}

}
