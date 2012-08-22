/*
 * ExaminationSystem-Tea com.todayedu.ebag.teacher.UIModule
 * 2012 2012-7-2 ï¿½ï¿½ï¿½ï¿½6:43:21
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
import com.todayedu.ebag.teacher.TempData;
import com.todayedu.ebag.teacher.DataAdapter.BaseDataAdapter;
import com.todayedu.ebag.teacher.DataSource.DSCallback;
import com.todayedu.ebag.teacher.DataSource.Data;
import com.todayedu.ebag.teacher.DataSource.PCommentDS;
import com.todayedu.ebag.teacher.Network.ResponseParseUtil;

/**
 * ½²ÆÀÊÔ¾íµÄÌâÄ¿ÁÐ±í
 * 
 * @author zhenzxie
 * 
 */
public class PCommentActivity extends BaseActivity {
	
	private BaseDataAdapter adapter;
	private PCommentDS ds;
	private ListView lv;

	/**
	 * @see com.todayedu.ebag.teacher.UIModule.MonitoredActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);
		Log.i(TAG, "onCreate");

		final String[] keys = new String[] { "number", "state" };
		ds = new PCommentDS(new DSCallback() {
			
			@Override
			public void onLoadSuccess(Object object) {
			
				Log.i(TAG, "onLoadSuccess");
				ExamResponse examResponse = (ExamResponse) object;
				final List<Data> list = ResponseParseUtil
				        .parseExamResponse2ProblemList(examResponse,
				                PCommentActivity.this);
				PCommentActivity.this.runOnUiThread(new Runnable() {
					
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
				showToast("¼ÓÔØÊý¾ÝÊ§°Ü");
			}
		});
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
	 * @see com.todayedu.ebag.teacher.UIModule.BaseActivity#onResume()
	 */
	@Override
	protected void onResume() {
	
		super.onResume();
		Log.i(TAG, "onResume");
		if (ds != null && adapter != null) {
			ds.notifyDataChange();
			Log.i(TAG, "onResume notifyDataChange");
		}
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
