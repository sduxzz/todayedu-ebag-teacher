/*
 * ExaminationSystem-Tea com.todayedu.examinationsystem.teacher.UIModule
 * 2012 2012-5-31 ����4:13:06
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.UIModule;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.todayedu.ebag.teacher.Parameters;
import com.todayedu.ebag.teacher.Parameters.ParaIndex;
import com.todayedu.ebag.teacher.R;
import com.todayedu.ebag.teacher.DataAdapter.BaseDataAdapter;
import com.todayedu.ebag.teacher.Database.DataBaseHelper;

/**
 * @author zhenzxie
 * 
 */
public class SCExamActivity extends MonitoredActivity {
	
	private BaseDataAdapter adapter;
	private TextView tv_2;
	private TextView tv_4;
	private TextView tv_6;
	private ListView elView;

	/**
	 * @see com.todayedu.ebag.teacher.UIModule.MonitoredActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scexam);
		tv_2 = (TextView) findViewById(R.id.scexam_tv2);
		tv_4 = (TextView) findViewById(R.id.scexam_tv4);
		tv_6 = (TextView) findViewById(R.id.scexam_tv6);
		elView = (ListView) findViewById(R.id.scexam);
		elView.addHeaderView(HeaderViewFactory.createHeaderView2(this,
		        R.array.exam_preview));
		// adapter = new DataAdapter3(this, new SCExamDS(this),
		// elView.zLayout_ID, elView.zTextView_KEY, elView.zTextView_ID);
		// elView.bindAdapter(adapter);
		// addLifeCycleListener(adapter);
		
		DataBaseHelper helper = new DataBaseHelper(this);
		SQLiteDatabase database = helper.getReadableDatabase();
		Cursor cursor = database
				.rawQuery(
						"select ename,etotal,etime, from PROBLEM,EXAM,EP where PROBLEM.pid = EP.pid and EXAM.Eid = EP.eid and EXAM.eid = ?",
						new String[] { Parameters.getStr(ParaIndex.EID_INDEX) });
		if (cursor.moveToFirst()) {
			getAndSet(tv_2, "ename", cursor);
			getAndSet(tv_4, "etotal", cursor);
			getAndSet(tv_6, "etime", cursor);
		}
		
	}
	
	public void getAndSet(TextView tv, String column, Cursor cursor) {
	
		tv.setText(cursor.getString(cursor.getColumnIndex(column)));
	}
}
