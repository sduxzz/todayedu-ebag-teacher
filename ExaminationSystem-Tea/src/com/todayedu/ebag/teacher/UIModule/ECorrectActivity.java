/*
 * ExaminationSystem-Tea com.todayedu.examinationsystem.teacher.UIModule
 * 2012 2012-5-31 锟斤拷锟斤拷4:13:06
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.UIModule;

import java.util.List;

import org.ebag.net.response.ClassExamactivityResponse;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.todayedu.ebag.teacher.Parameters;
import com.todayedu.ebag.teacher.Parameters.ParaIndex;
import com.todayedu.ebag.teacher.R;
import com.todayedu.ebag.teacher.DataAdapter.BaseDataAdapter;
import com.todayedu.ebag.teacher.DataSource.Data;
import com.todayedu.ebag.teacher.DataSource.ECorrectDS;
import com.todayedu.ebag.teacher.DataSource.DataObj.Student;
import com.todayedu.ebag.teacher.Network.ResponseParseUtil;

/**
 * 批改试卷的学生列表
 * 
 * @author zhenzxie
 * 
 */
public class ECorrectActivity extends BaseActivity {
	
	private BaseDataAdapter adapter;
	private ECorrectDS ds;
	private ListView lv;
	private final String[] keys = new String[] { "sid", "sname", "state" };

	/**
	 * @see com.todayedu.ebag.teacher.UIModule.MonitoredActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);
		Log.i(TAG, "onCreate");

		ds = new ECorrectDS(this);
		ds.load(this);
		addLifeCycleListener(ds);
		
		adapter = new BaseDataAdapter(this, ds, R.layout.lv_3, new int[] {
		        R.id.lv3_tv_1, R.id.lv3_tv_2, R.id.lv3_tv_3 }, keys);
		ds.addObserver(adapter);
		
		lv = (ListView) findViewById(R.id.lv);
		View headerView = HeaderViewFactory.createHeaderView3(this,
		        R.array.exam_corrent);
		initListView(lv, headerView, adapter);

	}
	
	/**
	 * @see com.todayedu.ebag.teacher.UIModule.BaseActivity#onItemClick(android.widget.AdapterView,
	 *      android.view.View, int, long)
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
	        long id) {
	
		Student student = (Student) ds.getList().get(position - 1);
		Parameters.add(student.getSid(), ParaIndex.SID_INDEX);
		start(ECSActivity.class);
	}
	
	@Override
	public void onLoadSuccess(Object object) {
	
		super.onLoadSuccess(object);
		ClassExamactivityResponse examResponse = (ClassExamactivityResponse) object;
		final List<Data> list = ResponseParseUtil
		        .paraClassExamActivityResponse(examResponse);
		ds.setList(list);
		ds.createMaps(keys);
		ECorrectActivity.this.runOnUiThread(new Runnable() {
			@Override
			public void run() {
			
				ds.notifyDataChange();
			}
		});
	}
}