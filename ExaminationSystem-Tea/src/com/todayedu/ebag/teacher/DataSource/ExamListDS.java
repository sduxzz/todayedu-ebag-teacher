/*
 * ExaminationSystem-Tea com.todayedu.examinationsystem.teacher.DataSource
 * 2012 2012-5-31 ����6:49:40
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.DataSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.util.Log;

import com.todayedu.ebag.teacher.Parameters;
import com.todayedu.ebag.teacher.Parameters.ParaIndex;
import com.todayedu.ebag.teacher.DataSource.DataObj.Exam;
import com.todayedu.ebag.teacher.Network.ExamHandler;
import com.todayedu.ebag.teacher.Network.NetworkClient;

/**
 * this DataSource is used for ExamShowActivity
 * 
 * @author zhenzxie
 * 
 */
public class ExamListDS extends BaseDataSource {
	
	private NetworkClient client;

	public ExamListDS(DSCallback callback) {
	
		super(callback);
	}

	@Override
	public void localload(Context context) {
	
		String cid = Parameters.getStr(ParaIndex.CID_INDEX);
		int state = Parameters.get(ParaIndex.EXAMSTATE_INDEX);
		Log.i(TAG, cid + "   " + state);
		String sql = null;
		String[] selectArgs = null;
		if (state == 0) {// select all exams
			sql = "select eid ,ename from EXAM where cid = ? ";
			selectArgs = new String[] { cid };
		} else {
			sql = "select eid ,ename from EXAM where cid = ? and state = ?";
			selectArgs = new String[] { cid, String.valueOf(state) };
		}
		localload(context, sql, selectArgs);
	}

	@Override
	public void download(Context context) {
	
		int cid = Parameters.get(ParaIndex.CID_INDEX);
		int state = Parameters.get(ParaIndex.EXAMSTATE_INDEX);

		List<Integer> stateList = null;
		if (state != 0) {// when state is 0(request all exam),so stateList is
			             // null;
			stateList = new ArrayList<Integer>();
			stateList.add(state);
		}
		List<String> fieldList = new ArrayList<String>();
		fieldList.add("name");
		fieldList.add("id");// id,name and type is the field name of ExamObj
		fieldList.add("type");
		client = new NetworkClient();
		ExamHandler handler = new ExamHandler(context, this, cid, stateList,
		        null, fieldList);
		client.setHandler(handler);
		client.connect();
	}
	
	/**
	 * @see com.todayedu.ebag.teacher.DataSource.BaseDataSource#fillMaps()
	 */
	@Override
	public void createMaps(String[] keys) {
	
		List<? extends Data> list = this.getList();
		List<Map<String, String>> maps = this.getData();
		Map<String, String> map = null;
		Exam exam = null;
		for (Data data : list) {
			exam = (Exam) data;
			map = new HashMap<String, String>();
			map.put(keys[0], exam.getEname());
			map.put(keys[1], String.valueOf(exam.getEid()));
			maps.add(map);
		}
	}
	
	/**
	 * @see com.todayedu.ebag.teacher.DataSource.BaseDataSource#disconnect()
	 */
	@Override
	protected void disconnect() {
	
		disconnect(client);
	}
}