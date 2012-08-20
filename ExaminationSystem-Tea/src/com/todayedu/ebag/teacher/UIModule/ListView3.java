/*
 * ExaminationSystem-Tea com.todayedu.examinationsystem.teacher.UIModule
 * 2012 2012-5-31 обнГ2:56:12
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
}
