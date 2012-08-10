/*
 * ExaminationSystem-Tea com.todayedu.examinationsystem.teacher.DataSource
 * 2012 2012-5-31 ����6:49:40
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.DataSource;

import android.content.Context;
import android.util.Log;

import com.todayedu.ebag.teacher.Parameters;
import com.todayedu.ebag.teacher.Parameters.ParaIndex;

/**
 * @author zhenzxie
 * 
 */
public class PCommentDS extends BaseDS {

	public PCommentDS(Class<? extends Data> cl) {
	
		super(cl);
	}

	@Override
	public void localload(Context context) {

		String cid = String.valueOf(Parameters.get(ParaIndex.CID_INDEX));
		String eid = String.valueOf(Parameters.get(ParaIndex.EID_INDEX));
		Log.i(TAG, cid + "   " + eid);
		String sql = "select number,state,point from CEP,PROBLEM where CEP.pid = PROBLEM.pid and cid = ? and eid = ?";
		String[] selectArgs = new String[] { cid, eid };
		localload(context, sql, selectArgs);
	}

	@Override
	public boolean save(Context context) {
	
		return false;
	}
	
	@Override
	public void download(Context context) {
	
	}
	

}
