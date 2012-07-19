/*
 * ExaminationSystem-Tea com.todayedu.examinationsystem.teacher.UIModule
 * 2012 2012-5-31 ÏÂÎç2:56:12
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.UIModule;

import android.content.Context;
import android.util.AttributeSet;

import com.todayedu.ebag.teacher.R;
import com.todayedu.ebag.teacher.DataAdapter.BaseDataAdapter;
import com.todayedu.ebag.teacher.DataAdapter.DataAdapter3;
import com.todayedu.ebag.teacher.DataSource.DataSource;

/**
 * @author zhenzxie
 * 
 */
public class ListView3 extends BaseListView {
	
	/**
	 * @param context
	 * @param attrs
	 */
	public ListView3(Context context, AttributeSet attrs) {
	
		super(context, attrs);
		zTextView_ID = new int[] { R.id.lv3_tv_1, R.id.lv3_tv_2, R.id.lv3_tv_3 };
		zLayout_ID = R.layout.lv_3;
	}
	
	/**
	 * @see com.todayedu.ebag.teacher.UIModule.BaseListView#setHeaderView(int,
	 *      int, int)
	 */
	public void setHeaderView(int resource_id) {
	
		super.setHeaderView(R.layout.hv_3, resource_id,
		        HeaderViewFactory.HV_3_SIG);
	}
	
	@Override
	public BaseDataAdapter bindAdapter(DataSource dataSource, String[] keys) {
	
		zAdapter = new DataAdapter3(zContext, dataSource, zLayout_ID, keys,
				zTextView_ID);
		setAdapter(zAdapter);
		return zAdapter;
	}
	
}
