/*
 * ExaminationSystem-Tea com.todayedu.examinationsystem.teacher.UIModule
 * 2012 2012-5-28 ����12:00:11
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.UIModule;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;

import com.todayedu.ebag.teacher.DataAdapter.BaseDataAdapter;
import com.todayedu.ebag.teacher.DataSource.DataSource;

/**
 * the base list view
 * 
 * @author zhenzxie
 * @param <T>
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
	 * the subclass may call this method to set a header for itself, more detail
	 * see {@link ListView#addHeaderView(View)}
	 * 
	 * @param headerView
	 */
	public void setHeaderView(int layout_id, int resource_id, int which) {
	
		BaseHeaderView headerView = HeaderViewFactory.createHeaderView(
				layout_id, resource_id, which);
		addHeaderView(headerView.getHeaderView(zContext), null, false);
	}
	
	public abstract BaseDataAdapter bindAdapter(DataSource dataSource,
			String[] keys);
}
