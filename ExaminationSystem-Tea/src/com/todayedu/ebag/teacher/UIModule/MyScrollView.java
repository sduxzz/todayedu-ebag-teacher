/*
 * ExaminationSystem-Tea com.todayedu.ebag.teacher.UIModule
 * 2012 2012-9-23 ÏÂÎç8:10:13
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.UIModule;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;


/**
 * @author <a href="zhenzxie.iteye.cn">zhenzxie</a>
 * @version 1.0
 * @since 1.0
 */
public class MyScrollView extends ScrollView {
	
	/**
	 * @param context
	 */
	public MyScrollView(Context context) {
	
		super(context);
	}
	
	/**
	 * @param context
	 * @param attrs
	 */
	public MyScrollView(Context context, AttributeSet attrs) {
	
		super(context, attrs);
	}
	
	/**
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public MyScrollView(Context context, AttributeSet attrs, int defStyle) {
	
		super(context, attrs, defStyle);
	}
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
	
		return false;
	}
	
}
