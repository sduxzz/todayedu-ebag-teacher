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
import android.widget.AdapterView;
import android.widget.ListView;

import com.todayedu.ebag.teacher.R;
import com.todayedu.ebag.teacher.TempData;
import com.todayedu.ebag.teacher.DataAdapter.BaseDataAdapter;
import com.todayedu.ebag.teacher.DataSource.Data;
import com.todayedu.ebag.teacher.DataSource.ECSDS;
import com.todayedu.ebag.teacher.Network.ResponseParseUtil;

/**
 * 某个学生的要批改试卷的题目列表
 * 
 * @author zhenzxie
 * 
 */
public class ECSActivity extends BaseActivity {
	
	private BaseDataAdapter adapter;
	private ECSDS ds;
	private ListView lv;
	private final String[] keys = new String[] { "number", "state" };
	/**
	 * @see com.todayedu.ebag.teacher.UIModule.MonitoredActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);

		ds = new ECSDS(this);
		ds.load(this);
		addLifeCycleListener(ds);
		
		adapter = new BaseDataAdapter(this, ds, R.layout.lv_2, new int[] {
		        R.id.lv2_tv_1, R.id.lv2_tv_2 }, keys);
		ds.addObserver(adapter);
		
		lv = (ListView) findViewById(R.id.lv);
		View headerView = HeaderViewFactory.createHeaderView2(this,
		        R.array.pro_id_state);
		initListView(lv, headerView, adapter);

	}
	
	/**
	 * @see com.todayedu.ebag.teacher.UIModule.BaseActivity#onItemClick(android.widget.AdapterView,
	 *      android.view.View, int, long)
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
	
		TempData.storeData(ds, position - 1);
		start(ECSSActivity.class);
	}
	
	@Override
	public void onLoadSuccess(Object object) {
	
		super.onLoadSuccess(object);
		ExamResponse examResponse = (ExamResponse) object;
		final List<Data> list = ResponseParseUtil
		        .parseExamResponse2ProblemList(examResponse, ECSActivity.this);
		ECSActivity.this.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
			
				ds.setList(list);
				ds.createMaps(keys);
				ds.notifyDataChange();
			}
		});
	}
}
