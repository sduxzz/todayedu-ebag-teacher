/*
 * ExaminationSystem-Tea com.todayedu.examinationsystem.teacher.UIModule
 * 2012 2012-5-29 ÏÂÎç10:32:08
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.UIModule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/**
 * the base header view,it will be used in a base list view
 * 
 * @author zhenzxie
 * 
 */
public class BaseHeaderView {
	
	/**
	 * the layout resource id of this object
	 */
	protected int zLayout_ID;
	/**
	 * the text resource id of this object
	 */
	protected int zResource_ID;
	/**
	 * the text view's id of this header view
	 */
	protected int[] zTextView_ID;
	
	public BaseHeaderView(int layout_id, int resource_id) {
	
		this.zLayout_ID = layout_id;
		this.zResource_ID = resource_id;
	}
	
	/**
	 * 
	 * @param context
	 * @return view
	 */
	public View getHeaderView(Context context) {
	
		LayoutInflater inflater = (LayoutInflater) context
		        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		String[] resource = context.getResources().getStringArray(zResource_ID);
		View view = inflater.inflate(zLayout_ID, null);
		TextView tv = null;
		int index = 0;
		for (int id : zTextView_ID) {
			tv = (TextView) view.findViewById(id);
			tv.setText(resource[index++]);
		}
		return view;
	}
}
