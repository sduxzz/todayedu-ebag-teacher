/*
 * ExaminationSystem-Tea com.todayedu.examinationsystem.teacher.UIModule
 * 2012 2012-5-31 ����4:13:06
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.UIModule;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.todayedu.ebag.teacher.R;
import com.todayedu.ebag.teacher.TempData;
import com.todayedu.ebag.teacher.DataAdapter.BaseDataAdapter;

/**
 * @author zhenzxie
 * 
 */
public class ECSActivity extends MonitoredActivity {
	
	private BaseDataAdapter adapter;
	/**
	 * @see com.todayedu.ebag.teacher.UIModule.MonitoredActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ecs);

		ListView2 elView = (ListView2) findViewById(R.id.ecs);
		elView.addHeaderView(HeaderViewFactory.createHeaderView2(this,
		        R.array.pro_id_state));
		elView.setOnItemClickListener(this);
		
		String[] keys = new String[] { "number", "state", "point", "flag" };
		// ECSDS ds = new ECSDS(ECS.class);
		// ds.localload(this);
		// adapter = elView.bindAdapter(ds, keys);
		// addLifeCycleListener(adapter);
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
	
		TempData.storeData(adapter.getzDataSource(), position - 1);
		start(ECSSActivity.class);
	}
}
