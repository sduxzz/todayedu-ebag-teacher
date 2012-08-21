/*
 * ExaminationSystem-Tea com.todayedu.ebag.teacher.DataSource
 * 2012 2012-8-21 ионГ9:44:35
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.DataSource;


/**
 * @author zhenzxie
 *
 */
public interface DSCallback {
	
	public abstract void onLoadSuccess(Object object);
	
	public abstract void onLoadFailed(Throwable throwable);
	
}
