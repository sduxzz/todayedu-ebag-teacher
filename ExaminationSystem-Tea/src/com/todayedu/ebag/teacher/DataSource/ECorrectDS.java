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

import com.todayedu.ebag.teacher.Parameters;
import com.todayedu.ebag.teacher.Parameters.ParaIndex;
import com.todayedu.ebag.teacher.DataSource.DataObj.Student;
import com.todayedu.ebag.teacher.Network.ClassExamActivityHandler;

/**
 * @author zhenzxie
 * 
 */
public class ECorrectDS extends BaseDataSource {
	
	public ECorrectDS(DSCallback callback) {
	
		super(callback);
	}
	
	@Override
	public void download(Activity context) {
	
		int cid = Parameters.get(ParaIndex.CID_INDEX);
		int eid = Parameters.get(ParaIndex.EID_INDEX);
		connect(new ClassExamActivityHandler(context, this, cid, eid));
	}
	
	@Override
	public void createMaps(String[] keys) {
	
		List<? extends Data> list = this.getList();
		List<Map<String, String>> maps = this.getData();
		Map<String, String> map = null;
		Student student = null;
		for (Data data : list) {
			student = (Student) data;
			map = new HashMap<String, String>();
			map.put(keys[0], String.valueOf(student.getSid()));
			map.put(keys[1], student.getSname());
			map.put(keys[2], student.getState());
			maps.add(map);
		}
	}
	
}
