/*
 * ExaminationSystem-Tea com.todayedu.examinationsystem.teacher.UIModule
 * 2012 2012-5-31 锟斤拷锟斤拷4:13:06
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.UIModule;

import java.util.List;

import org.ebag.net.response.ExamResponse;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.todayedu.ebag.teacher.R;
import com.todayedu.ebag.teacher.DataAdapter.BaseDataAdapter;
import com.todayedu.ebag.teacher.DataSource.DSCallback;
import com.todayedu.ebag.teacher.DataSource.Data;
import com.todayedu.ebag.teacher.DataSource.ECorrectDS;
import com.todayedu.ebag.teacher.Network.ResponeParseUtil;

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

	/**
	 * @see com.todayedu.ebag.teacher.UIModule.MonitoredActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);
		Log.i(TAG, "onCreate");

		final String[] keys = new String[] { "sname", "sid", "state" };
		
		ds = new ECorrectDS(new DSCallback() {
			
			@Override
			public void onLoadSuccess(Object object) {
			
				Log.i(TAG, "onLoadSuccess");
				ExamResponse examResponse = (ExamResponse) object;
				final List<Data> list = ResponeParseUtil
				        .parseExamResponse2ProblemList(examResponse,
				                ECorrectActivity.this);
				ECorrectActivity.this.runOnUiThread(new Runnable() {
					
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
	
		// String sid = adapter.getzDataSource().getList().get(position - 1)
		// .valueOfKey("sid");
		// Parameters.add(sid, ParaIndex.SID_INDEX);
		// start(ECSActivity.class);
	}

}