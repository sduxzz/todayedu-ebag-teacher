/*
 * ExaminationSystem-Tea com.todayedu.examinationsystem.teacher.UIModule
 * 2012 2012-5-31 锟斤拷锟斤拷4:13:06
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.UIModule;

import org.ebag.net.response.ClassExamactivityResponse;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.todayedu.ebag.teacher.Parameters;
import com.todayedu.ebag.teacher.Parameters.ParaIndex;
import com.todayedu.ebag.teacher.R;
import com.todayedu.ebag.teacher.DataAdapter.BaseDataAdapter;
import com.todayedu.ebag.teacher.DataSource.ClassStudentDS;

/**
 * 批改试卷的学生列表
 * 
 * @author zhenzxie
 * 
 */
public class ECorrectActivity extends BaseActivity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);

		ds = new ClassStudentDS(this);
		ds.load(this);
		BaseDataAdapter adapter = new BaseDataAdapter(this, ds, R.layout.lv_3,
		        new int[] { R.id.lv3_tv_1, R.id.lv3_tv_2, R.id.lv3_tv_3 }, keys);
		ds.addObserver(adapter);
		ListView lv = (ListView) findViewById(R.id.lv);
		View headerView = HeaderViewFactory.createHeaderView3(this,
		        R.array.exam_corrent);
		resetListView(lv, headerView, adapter);
	}
	
	@Override
	public void onLoadSuccess(Object object) {
	
		super.onLoadSuccess(object);

		ClassExamactivityResponse examResponse = (ClassExamactivityResponse) object;
		if (examResponse == null)
			return;
		ds.setExamActivitiesList(examResponse.lst);
		ds.createMaps(keys);
		ds.notifyDataChange(this);
	}
	
	private ClassStudentDS ds;
	private final String[] keys = new String[] { "sid", "sname", "state" };

	/**
	 * @param lv
	 * @param headerView
	 */
	private void resetListView(ListView lv, View headerView,
	        BaseAdapter adapter) {
	
		lv.addHeaderView(headerView);
		lv.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
			        int position, long id) {
			
				if (position <= 0)
					return;

				Parameters.add(ds.getExamActivitiesList().get(position - 1)
				        .getEuser().getId(), ParaIndex.SID_INDEX);
				start(ECSActivity.class);
			}
		});
		lv.setBackgroundColor(Color.WHITE);
		lv.setCacheColorHint(Color.WHITE);
		lv.setAdapter(adapter);
	}
}