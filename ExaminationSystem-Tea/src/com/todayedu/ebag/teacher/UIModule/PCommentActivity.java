/*
 * ExaminationSystem-Tea com.todayedu.ebag.teacher.UIModule
 * 2012 2012-7-2 ����6:43:21
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.UIModule;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.todayedu.ebag.teacher.R;
import com.todayedu.ebag.teacher.TempData;
import com.todayedu.ebag.teacher.DataAdapter.BaseDataAdapter;
import com.todayedu.ebag.teacher.DataSource.PCommentDS;
import com.todayedu.ebag.teacher.DataSource.DataObj.Problem;

/**
 * @author zhenzxie
 * 
 */
public class PCommentActivity extends MonitoredActivity {
	
	private BaseDataAdapter adapter;
	private PCommentDS ds;

	/**
	 * @see com.todayedu.ebag.teacher.UIModule.MonitoredActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pcomment);

		ListView2 elView = (ListView2) findViewById(R.id.pstate);
		elView.setHeaderView(R.array.pro_id_state);
		elView.setOnItemClickListener(this);

		String[] keys = new String[] { "number", "state" };
		ds = new PCommentDS(Problem.class);
		ds.load(this);

		adapter = elView.bindAdapter(ds, keys);
		addLifeCycleListener(adapter);
	}
	
	/**
	 * @see com.todayedu.ebag.teacher.UIModule.BaseActivity#onItemClick(android.widget.AdapterView,
	 *      android.view.View, int, long)
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
	
		TempData.storeData(ds, position - 1);
		start(PCCActivity.class);
	}
}
