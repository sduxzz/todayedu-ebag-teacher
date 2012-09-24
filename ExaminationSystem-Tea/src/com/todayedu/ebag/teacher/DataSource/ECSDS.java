/*
 * ExaminationSystem-Tea com.todayedu.examinationsystem.teacher.DataSource
 * 2012 2012-5-31 ����6:49:40
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.DataSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.util.Log;

import com.todayedu.ebag.teacher.Parameters;
import com.todayedu.ebag.teacher.Parameters.ParaIndex;
import com.todayedu.ebag.teacher.DataSource.DataObj.Answer;
import com.todayedu.ebag.teacher.Network.AnswerHandler;


/**
 * @author zhenzxie
 * 
 */
public class ECSDS extends BaseDataSource {
	
	public ECSDS(DSCallback callback) {
	
		super(callback);
	}

	@Override
	public void download(Activity context) {
	
		int uid = Parameters.get(ParaIndex.SID_INDEX);
		int eid = Parameters.get(ParaIndex.EID_INDEX);
		connect(new AnswerHandler(context, this, uid, eid, null, null));
	}
	
	@Override
	public void createMaps(String[] keys) {
	
		Log.i(TAG, "createMaps");
		List<? extends Data> list = this.getList();
		List<Map<String, String>> maps = this.getData();
		Map<String, String> map = null;
		Answer answer = null;
		for (Data data : list) {
			answer = (Answer) data;
			map = new HashMap<String, String>();
			map.put(keys[0], String.valueOf(answer.getNumber()));
			map.put(keys[1], answer.getState());
			maps.add(map);
		}
	}
}
