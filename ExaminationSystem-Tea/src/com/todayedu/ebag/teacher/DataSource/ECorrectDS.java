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
public class ECorrectDS extends BaseDataSource {

	public ECorrectDS(DSCallback callback) {
	
		super(callback);
	}

	@Override
	public void localload(Context context) {
	
		String cid = String.valueOf(Parameters.get(ParaIndex.CID_INDEX));
		String eid = String.valueOf(Parameters.get(ParaIndex.EID_INDEX));

		String sql = "select sname,STUDENT.sid,state from STUDENT,ES where STUDENT.sid = ES.sid and STUDENT.cid = ? and eid = ?";
		String[] selectArgs = new String[] { cid, eid };
		localload(context, sql, selectArgs);
	}
	
	@Override
	public void download(Context context) {
	
		// NetworkClient client = new NetworkClient();
		// client.setHandler(new ExamHandler(context, this, cid, stateList,
		// null,
		// fieldList));
		// client.connect();
	}
	
	@Override
	public void createMaps(String[] keys) {
	
	}
}
