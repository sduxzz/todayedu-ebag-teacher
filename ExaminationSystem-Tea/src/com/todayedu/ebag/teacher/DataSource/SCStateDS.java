/*
 * ExaminationSystem-Tea com.todayedu.examinationsystem.teacher.DataSource
 * 2012 2012-5-31 ����6:49:40
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.DataSource;

import android.content.Context;



/**
 * @author zhenzxie
 * 
 */
public class SCStateDS extends BaseLocalDS {

	
	public SCStateDS(Class<Data> cl) {
	
		super(cl);
	}

	@Override
	public void localload(Context context) {
	
	}

	@Override
	public boolean save(Context context) {
	
		return false;
	}

	@Override
	public DataSource convert() {
	
		return null;
	}
	
	@Override
	public void download(Context context) {
	
	}
	

}
