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

import android.app.Activity;

import com.todayedu.ebag.teacher.Parameters;
import com.todayedu.ebag.teacher.Parameters.ParaIndex;
import com.todayedu.ebag.teacher.DataSource.DataObj.Problem;
import com.todayedu.ebag.teacher.Network.ExamHandler;



/**
 * @author zhenzxie
 * 
 */
public class SCExamDS extends BaseDataSource {
	
	public SCExamDS(DSCallback callback) {
	
		super(callback);
	}
	
	@Override
	public void download(Activity context) {
		int cid = Parameters.get(ParaIndex.CID_INDEX);
		int eid = Parameters.get(ParaIndex.EID_INDEX);
		
		List<Integer> idList = new ArrayList<Integer>();
		idList.add(eid);
		List<String> fieldList = new ArrayList<String>();
		fieldList.add("pInfoList");// pInfoList is the field name of ExamObj
		connect(new ExamHandler(context, this, cid, null, idList,
		        fieldList));
	}
	
	@Override
	public void createMaps(String[] keys) {
	
		List<? extends Data> list = this.getList();
		List<Map<String, String>> maps = this.getData();
		Map<String, String> map = null;
		Problem problem = null;
		for (Data data : list) {
			problem = (Problem) data;
			map = new HashMap<String, String>();
			map.put(keys[0], String.valueOf(problem.getNumber()));
			map.put(keys[1], String.valueOf(problem.getPoint()));
			maps.add(map);
		}
	}
}
