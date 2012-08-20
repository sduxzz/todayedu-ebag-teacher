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

/**
 * @author zhenzxie
 * 
 */
public class ECorrectActivity extends MonitoredActivity {
	
	private BaseDataAdapter adapter;

	/**
	 * @see com.todayedu.ebag.teacher.UIModule.MonitoredActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ecorrect);

		ListView3 elView = (ListView3) findViewById(R.id.ecorrect);
		elView.setOnItemClickListener(this);
		elView.addHeaderView(HeaderViewFactory.createHeaderView3(this,
		        R.array.exam_corrent));
		String[] keys = new String[] { "sname", "sid", "state" };
		// ECorrectDS ds = new ECorrectDS(ECorrect.class);
		// ds.load(this);
		// adapter = elView.bindAdapter(ds, keys);
		// addLifeCycleListener(adapter);
	}
	
	/**
	 * @see com.todayedu.ebag.teacher.UIModule.BaseActivity#onItemClick(android.widget.AdapterView,
	 *      android.view.View, int, long)
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
	
		String sid = adapter.getzDataSource().pick().get(position - 1)
				.valueOfKey("sid");
		Parameters.add(sid, ParaIndex.SID_INDEX);
		start(ECSActivity.class);
	}

}