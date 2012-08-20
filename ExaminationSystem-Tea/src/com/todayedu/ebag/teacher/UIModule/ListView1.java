/*
 * ExaminationSystem-Tea com.todayedu.examinationsystem.teacher.UIModule
 * 2012 2012-5-31 обнГ4:26:16
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.UIModule;

import android.content.Context;
import android.util.AttributeSet;

import com.todayedu.ebag.teacher.R;

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
}
