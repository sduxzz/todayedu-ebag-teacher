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

import org.ebag.net.obj.exam.ExamObj;

import android.app.Activity;
import android.util.Log;

import com.todayedu.ebag.teacher.Parameters;
import com.todayedu.ebag.teacher.Parameters.ParaIndex;
import com.todayedu.ebag.teacher.Network.ExamHandler;

/**
 * this DataSource is used for ExamShowActivity
 * 
 * @author zhenzxie
 * 
 */
public class ExamListDS extends BaseDataSource {
	
	private List<ExamObj> examList;
	
	public ExamListDS(DSCallback callback) {
	
		super(callback);
	}

	@Override
	public void download(Activity context) {
	
		int cid = Parameters.get(ParaIndex.CID_INDEX);
		int state = Parameters.get(ParaIndex.ESTATE_INDEX);
		
		List<Integer> stateList = new ArrayList<Integer>();
		stateList.add(state);
		List<String> fieldList = new ArrayList<String>();
		/* name,id and type is the field name of ExamObj */
		fieldList.add("name");
		fieldList.add("id");
		fieldList.add("type");
		loadStart(new ExamHandler(context, this, cid, stateList, null,
		        fieldList));
	}
	
	@Override
	public void createMaps(String[] keys) {
	
		List<ExamObj> list = this.getExamList();
		List<Map<String, String>> maps = this.getListMap();
		maps.clear();
		Map<String, String> map = null;
		for (ExamObj obj : list) {
			map = new HashMap<String, String>();
			map.put(keys[0], obj.name);
			maps.add(map);
			Log.i(TAG, "createMaps: " + obj);
		}
	}
	
	/**
	 * @param position
	 */
	public void onListItemClick(int position) {
	
		ExamObj exam = getExamList().get(position - 1);
		Parameters.add(exam.id, ParaIndex.EID_INDEX);
		Parameters.setExamObj(exam);
	}
	
	/**
	 * @return the examList
	 */
	public List<ExamObj> getExamList() {
	
		return examList;
	}
	
	/**
	 * @param examList
	 *            the examList to set
	 */
	public void setExamList(List<ExamObj> examList) {
	
		this.examList = examList;
	}
}