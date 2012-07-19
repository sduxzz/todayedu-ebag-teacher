/*
 * ExaminationSystem-Tea com.todayedu.examinationsystem.teacher.DataSource
 * 2012 2012-5-31 ����6:49:40
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.DataSource;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.ebag.net.obj.exam.ExamObj;

import android.content.Context;
import android.util.Log;

import com.todayedu.ebag.teacher.Parameters;
import com.todayedu.ebag.teacher.Parameters.ParaIndex;
import com.todayedu.ebag.teacher.Network.ExamHandler;
import com.todayedu.ebag.teacher.Network.NetworkClient;

/**
 * @author zhenzxie
 * 
 */
public class ExamListDS extends BaseLocalDS {

	public ExamListDS(Class<? extends Data> cl) {
	
		super(cl);
	}

	@Override
	public void localload(Context context) {
	
		String cid = String.valueOf(Parameters.get(ParaIndex.CID_INDEX));
		String state = String
				.valueOf(Parameters.get(ParaIndex.EXAMSTATE_INDEX));
		Log.i(TAG, cid + "   " + state);
		if (state.equals("0")) {// select all exams
			String sql = "select EXAM.eid ,ename from EXAM,CE where EXAM.eid = CE.eid and CE.cid = ? ";
			String[] selectArgs = new String[] { cid };
			localload(context, sql, selectArgs);
		} else {
			String sql = "select EXAM.eid ,ename from EXAM,CE where EXAM.eid = CE.eid and CE.cid = ? and EXAM.state = ?";
			String[] selectArgs = new String[] { cid, state };
			localload(context, sql, selectArgs);
		}
	}

	@Override
	public void download(Context context) {
	
		int cid = Parameters.get(ParaIndex.CID_INDEX);
		int state = Parameters.get(ParaIndex.EXAMSTATE_INDEX);
		List<Integer> stateList = new ArrayList<Integer>();
		stateList.add(state);
		List<Field> fieldList = new ArrayList<Field>();
		Class<ExamObj> cl = ExamObj.class;
		try {
			fieldList.add(cl.getDeclaredField("name"));
			fieldList.add(cl.getDeclaredField("id"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		NetworkClient client = new NetworkClient();
		client.setHandler(new ExamHandler(context, this, cid, stateList, null,
				fieldList));
		client.connect();
	}
	
	@Override
	public boolean save(Context context) {
	
		return false;
	}

	@Override
	public DataSource convert() {
	
		return null;
	}
	
}
