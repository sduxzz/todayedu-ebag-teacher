/*
 * ExaminationSystem-Tea com.todayedu.examinationsystem.teacher.UIModule
 * 2012 2012-5-31 ÏÂÎç4:26:16
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.UIModule;

import android.content.Context;
import android.util.AttributeSet;

import com.todayedu.ebag.teacher.R;
import com.todayedu.ebag.teacher.DataAdapter.BaseDataAdapter;
import com.todayedu.ebag.teacher.DataSource.DataSource;

/**
 * @author zhenzxie
 * 
 */
public class ListView1 extends BaseListView {
	
	/**
	 * @param context
	 * @param attrs
	 */
	public ListView1(Context context, AttributeSet attrs) {
	
		super(context, attrs);
		zTextView_ID = new int[] { R.id.lv1_tv_1 };
		zLayout_ID = R.layout.lv_1;
	}
	
	/**
	 * @see com.todayedu.ebag.teacher.UIModule.BaseListView#setHeaderView(int,
	 *      int, int)
	 */
	public void setHeaderView(int resource_id) {
	
		super.setHeaderView(R.layout.hv_1, resource_id,
				HeaderViewFactory.HV_1_SIG);
	}
	
	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public BaseDataAdapter bindAdapter(DataSource dataSource, String[] keys) {
	
		zAdapter = new BaseDataAdapter(zContext, dataSource, zLayout_ID, keys,
				zTextView_ID);
		setAdapter(zAdapter);
		return zAdapter;
	}
}
