/*
 * ExaminationSystem-Tea com.todayedu.examinationsystem.teacher.UIModule
 * 2012 2012-5-31 ÉÏÎç12:18:32
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.UIModule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.todayedu.ebag.teacher.R;

/**
 * the factory for HeaderView
 * 
 * @author zhenzxie
 * 
 */
public class HeaderViewFactory {

	/**
	 * create HeaderView with specific resource
	 * 
	 * @param layout_id
	 * @param resource_id
	 * @param which
	 * @return BaseHeaderView
	 */
	public static View createHeaderView1(Context context, int resource_id) {
	
		return getHeaderView(context, R.layout.hv_1, resource_id,
		        new int[] { R.id.hv1_tv_1 });
	}
	
	/**
	 * create BaseHeaderView with specific resource
	 * 
	 * @param layout_id
	 * @param resource_id
	 * @param which
	 * @return BaseHeaderView
	 */
	public static View createHeaderView2(Context context, int resource_id) {
	
		return getHeaderView(context, R.layout.hv_2, resource_id, new int[] {
		        R.id.hv2_tv_1, R.id.hv2_tv_2 });
	}
	
	/**
	 * create BaseHeaderView with specific resource
	 * 
	 * @param layout_id
	 * @param resource_id
	 * @param which
	 * @return BaseHeaderView
	 */
	public static View createHeaderView3(Context context, int resource_id) {
	
		return getHeaderView(context, R.layout.hv_3, resource_id, new int[] {
		        R.id.hv3_tv_1, R.id.hv3_tv_2, R.id.hv3_tv_3 });
	}

	/**
	 * 
	 * @param context
	 * @param layout_id
	 *            the layout resource id of header view
	 * @param resource_id
	 *            the text resource id of header view
	 * @param textView_id
	 *            the text views' id of this header view
	 * @return view
	 */
	private static View getHeaderView(Context context, int layout_id,
	        int resource_id, int[] textView_id) {
	
		LayoutInflater inflater = (LayoutInflater) context
		        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		String[] resource = context.getResources().getStringArray(resource_id);
		TextView tv = null;

		View view = inflater.inflate(layout_id, null);
		int index = 0;
		for (int id : textView_id) {
			tv = (TextView) view.findViewById(id);
			tv.setText(resource[index++]);
		}
		return view;
	}

}
