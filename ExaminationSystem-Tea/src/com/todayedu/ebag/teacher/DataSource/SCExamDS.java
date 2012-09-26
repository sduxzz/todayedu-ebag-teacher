/*
 * ExaminationSystem-Tea com.todayedu.examinationsystem.teacher.DataSource
 * 2012 2012-5-31 ï¿½ï¿½ï¿½ï¿½6:49:40
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.DataSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ebag.net.obj.exam.ProblemInfoObj;

import android.app.Activity;

import com.todayedu.ebag.teacher.Parameters;
import com.todayedu.ebag.teacher.Parameters.ParaIndex;
import com.todayedu.ebag.teacher.Network.ExamHandler;



/**
 * @author zhenzxie
 * 
 */
public class SCExamDS extends BaseDataSource {
	
	public List<ProblemInfoObj> pInfoList;
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
		loadStart(new ExamHandler(context, this, cid, null, idList,
		        fieldList));
	}
	
	@Override
	public void createMaps(String[] keys) {
	
		List<ProblemInfoObj> list = this.getpInfoList();
		List<Map<String, String>> maps = this.getListMap();
		maps.clear();
		Map<String, String> map = null;
		int i = 1;
		for (ProblemInfoObj obj : list) {
			map = new HashMap<String, String>();
			map.put(keys[0], "µÚ" + (i++) + "Ìâ");
			map.put(keys[1], String.valueOf(obj.getPoint()));
			maps.add(map);
		}
	}

	
	/**
	 * @return the pInfoList
	 */
	public List<ProblemInfoObj> getpInfoList() {
	
		return pInfoList;
	}
	
    /**
	 * @param pInfoList
	 *            the pInfoList to set
	 */
	public void setpInfoList(List<ProblemInfoObj> pInfoList) {
	
		this.pInfoList = pInfoList;
	}
}
