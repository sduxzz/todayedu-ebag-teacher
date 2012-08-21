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
import com.todayedu.ebag.teacher.DataSource.SCStateDS;
import com.todayedu.ebag.teacher.Database.DataBaseHelper;

/**
 * @author zhenzxie
 * 
 */
public class SCStateActivity extends BaseActivity {
	
	private BaseDataAdapter adapter;
	private TextView tv_2;
	private TextView tv_4;
	private ListView elView;

	/**
	 * @see com.todayedu.ebag.teacher.UIModule.MonitoredActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scstate);
		tv_2 = (TextView) findViewById(R.id.scstate_tv2);
		tv_4 = (TextView) findViewById(R.id.scstate_tv4);
		elView = (ListView) findViewById(R.id.scstate);
		elView.addHeaderView(HeaderViewFactory.createHeaderView2(this,
		        R.array.exam_lookup_during));
		
		int[] zTextView_ID = new int[] { R.id.lv3_tv_1, R.id.lv3_tv_2,
		        R.id.lv3_tv_3 };
		int zLayout_ID = R.layout.lv_3;
		
		adapter = new BaseDataAdapter(this, new SCStateDS(null), zLayout_ID,
		        zTextView_ID, null);
		elView.setAdapter(adapter);
		// addLifeCycleListener(adapter);
		
		DataBaseHelper helper = new DataBaseHelper(this);
		SQLiteDatabase database = helper.getReadableDatabase();
		Cursor cursor = database
		        .rawQuery(
		                "select sname,STUDENT.sid,state from STUDENT,ES where STUDENT.sid = ES.sid and eid = ?",
		                new String[] { Parameters.getStr(ParaIndex.EID_INDEX) });// TODO:how
		                                                                         // to
		                                                                         // get
		                                                                         // information
		                                                                         // about
		                                                                         // exam's
		                                                                         // address
		                                                                         // and
		                                                                         // exam's
		                                                                         // people
		if (cursor.moveToFirst()) {
			getAndSet(tv_2, "", cursor);
			getAndSet(tv_4, "", cursor);
		}

	}
	
	public void getAndSet(TextView tv, String column, Cursor cursor) {
	
		tv.setText(cursor.getString(cursor.getColumnIndex(column)));
	}
	
}
