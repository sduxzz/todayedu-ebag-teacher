/*
 * ExaminationSystem-Tea com.todayedu.ebag.teacher.DataSource BaseNetDS.java
 * 2012 2012-7-15 обнГ11:47:18
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.DataSource;


import android.content.Context;



/**
 * @author zhenzxie
 *
 */
public abstract class BaseNetDS extends DataSource {
	
	public BaseNetDS(Class<? extends Data> cl) {
	
		super(cl);
	}
	
	@Override
	public abstract boolean save(Context context);
	
	@Override
	public abstract DataSource convert();

}