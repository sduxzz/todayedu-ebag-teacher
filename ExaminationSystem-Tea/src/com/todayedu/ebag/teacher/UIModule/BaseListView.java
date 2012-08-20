/*
 * ExaminationSystem-Tea com.todayedu.examinationsystem.teacher.UIModule
 * 2012 2012-5-28 ����12:00:11
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.UIModule;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.ListView;

import com.todayedu.ebag.teacher.DataAdapter.BaseDataAdapter;
import com.todayedu.ebag.teacher.DataSource.Data;
import com.todayedu.ebag.teacher.DataSource.DataSource;

/**
 * the base list view
 * 
 * @author zhenzxie
 * 
 */
public abstract class BaseListView extends ListView {
	
	protected Context zContext;
	protected BaseDataAdapter zAdapter;
	
	protected int[] zTextView_ID;/* text view's id */
	protected int zLayout_ID;/* list view's layout id */

	public BaseListView(Context context, AttributeSet attrs) {
	
		super(context, attrs);
		this.zContext = context;
		setBackgroundColor(Color.WHITE);
		setCacheColorHint(Color.WHITE);
	}
	
	/**
	 * @param zLayout_ID
	 *            the zLayout_ID to set
	 */
	public void setzLayout_ID(int zLayout_ID) {
	
		this.zLayout_ID = zLayout_ID;
	}
	
	/**
	 * @param zTextView_ID
	 *            the zTextView_ID to set
	 */
	public void setzTextView_ID(int[] zTextView_ID) {
	
		this.zTextView_ID = zTextView_ID;
	}

	// /**
	// * the subclass may call this method to set a header for itself, more
	// detail
	// * see {@link ListView#addHeaderView(View)}
	// *
	// * @param headerView
	// */
	// public void setHeaderView(int resource_id, int which) {
	//
	// BaseHeaderView headerView = HeaderViewFactory.createHeaderView(
	// resource_id, which);
	// addHeaderView(headerView.getHeaderView(zContext), null, false);
	// }
	
	/**
	 * 
	 * @param dataSource
	 * @param keys
	 *            the values of keys must be the field of {@link Data} used in
	 *            dataSource
	 * @return
	 */
	public BaseDataAdapter bindAdapter(DataSource dataSource, String[] keys) {
	
		zAdapter = new BaseDataAdapter(zContext, dataSource, zLayout_ID, keys,
		        zTextView_ID);
		setAdapter(zAdapter);
		return zAdapter;
	}
}
