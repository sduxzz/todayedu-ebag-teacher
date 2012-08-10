/*
 * ExaminationSystem-Tea com.todayedu.examinationsystem.teacher.DataSource
 * 2012 2012-5-31 ����6:49:40
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.DataSource;

import android.content.Context;

import com.todayedu.ebag.teacher.Parameters;
import com.todayedu.ebag.teacher.Parameters.ParaIndex;


/**
 * @author zhenzxie
 * 
 */
public class ECSDS extends BaseDS {
	
	public ECSDS(Class<? extends Data> cl) {
	
		super(cl);
	}

	@Override
	public void localload(Context context) {
	
		String cid = String.valueOf(Parameters.get(ParaIndex.CID_INDEX));
		String eid = String.valueOf(Parameters.get(ParaIndex.EID_INDEX));
		String sid = String.valueOf(Parameters.get(ParaIndex.SID_INDEX));
		
		String sql = "select number,SP.state,point,flag from PROBLEM,SP,CEP where SP.pid = CEP.pid and CEP.cid = ? and CEP.eid = ? and SP.sid = ?";
		String[] selectArgs = new String[] { cid, eid, sid };
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
