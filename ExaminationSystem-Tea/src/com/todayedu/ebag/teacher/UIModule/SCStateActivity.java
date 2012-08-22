/*
 * ExaminationSystem-Tea com.todayedu.examinationsystem.teacher.UIModule
 * 2012 2012-5-31 锟斤拷锟斤拷4:13:06
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.UIModule;

import java.util.List;

import org.ebag.net.obj.I.choice;
import org.ebag.net.response.ExamActivityResponse;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.todayedu.ebag.teacher.Parameters;
import com.todayedu.ebag.teacher.R;
import com.todayedu.ebag.teacher.DataAdapter.BaseDataAdapter;
import com.todayedu.ebag.teacher.DataSource.DSCallback;
import com.todayedu.ebag.teacher.DataSource.Data;
import com.todayedu.ebag.teacher.DataSource.SCStateDS;
import com.todayedu.ebag.teacher.Network.ResponseParseUtil;

/**
 * 考场状态(考试类型（家庭作业，学校考试），考生列表)
 * 
 * @author zhenzxie
 * 
 */
public class SCStateActivity extends BaseActivity {
	
	private BaseDataAdapter adapter;
	private SCStateDS ds;
	private TextView tv_2;
	private TextView tv_4;
	private ListView lv;

	/**
	 * @see com.todayedu.ebag.teacher.UIModule.MonitoredActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scstate);
		tv_2 = (TextView) findViewById(R.id.scstate_tv2);
		tv_4 = (TextView) findViewById(R.id.scstate_tv4);
		int etype = Parameters.getExam().getEtype();
		if (etype == choice.examType_exam) {
			tv_2.setText(R.string.comm_school);
		} else {
			tv_2.setText(R.string.comm_home);
		}
		tv_4.setText(Parameters.getClassName());
		
		final String[] keys = new String[] { "sname", "sid", "state" };
		ds = new SCStateDS(new DSCallback() {
			
			@Override
			public void onLoadSuccess(Object object) {
			
				Log.i(TAG, "onLoadSuccess");
				ExamActivityResponse examResponse = (ExamActivityResponse) object;
				final List<Data> list = ResponseParseUtil
				        .paraExamActivityResponse(examResponse);
				SCStateActivity.this.runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
					
						ds.setList(list);
						ds.createMaps(keys);
						ds.notifyDataChange();
					}
				});
			}
			
			@Override
			public void onLoadFailed(Throwable throwable) {
			
				if (throwable != null) {
					Log.i(TAG, throwable.getMessage());
				}
				showToast("加载数据失败");
			}
		});
		ds.load(this);
		addLifeCycleListener(ds);
		
		adapter = new BaseDataAdapter(this, ds, R.layout.lv_3, new int[] {
		        R.id.lv3_tv_1, R.id.lv3_tv_2, R.id.lv3_tv_3 }, keys);
		ds.addObserver(adapter);
		
		lv = (ListView) findViewById(R.id.scstate);
		View headerView = HeaderViewFactory.createHeaderView3(this,
		        R.array.exam_lookup_during);
		initListView(lv, headerView, adapter);
	}
}
