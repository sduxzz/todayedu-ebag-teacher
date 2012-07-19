/*
 * ExaminationSystem-Tea com.todayedu.examinationsystem.teacher.UIModule
 * 2012 2012-5-31 ÏÂÎç2:51:12
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.UIModule;

import android.content.Context;
import android.util.AttributeSet;

import com.todayedu.ebag.teacher.R;
import com.todayedu.ebag.teacher.DataAdapter.BaseDataAdapter;
import com.todayedu.ebag.teacher.DataAdapter.DataAdapter2;
import com.todayedu.ebag.teacher.DataSource.DataSource;

/**
 * @author zhenzxie
 * 
 */
public class ListView2 extends BaseListView {
	
	/**
	 * @param context
	 * @param attrs
	 */
	public ListView2(Context context, AttributeSet attrs) {
	
		super(context, attrs);
		zTextView_ID = new int[] { R.id.lv2_tv_1, R.id.lv2_tv_2 };
		zLayout_ID = R.layout.lv_2;
	}
	
	/**
	 * @see com.todayedu.ebag.teacher.UIModule.BaseListView#setHeaderView(int,
	 *      int, int)
	 */
	public void setHeaderView(int resource_id) {
	
		super.setHeaderView(R.layout.hv_2, resource_id,
		        HeaderViewFactory.HV_2_SIG);
	}
	
	@Override
	public BaseDataAdapter bindAdapter(DataSource dataSource, String[] keys) {
	
		zAdapter = new DataAdapter2(zContext, dataSource, zLayout_ID, keys,
				zTextView_ID);
		setAdapter(zAdapter);
		return zAdapter;
	}
}
