/**
 * SubActivity_Demo android.exercise
 * 2012 2012-3-29 ����7:55:07
 * @author xzz
 */
package com.todayedu.ebag.teacher.UIModule;

import android.os.Bundle;

import com.todayedu.ebag.teacher.R;

/**
 * @author zhenzxie
 * 
 */
public class AEPSActivity extends BaseActivity {
	
	/**
	 * @see com.todayedu.ebag.teacher.UIModule.MonitoredActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.aeps);
		// ListView1 elView = (ListView1) findViewById(R.id.el_examlistview);
		// elView.setHeaderView(R.array.analysis);

		// BaseDataAdapter adapter = elView.bindAdapter(new PCommentDS(this));
		// addLifeCycleListener(adapter);
	}
}

