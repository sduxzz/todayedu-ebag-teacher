/*
 * ExaminationSystem-Tea com.todayedu.ebag.teacher.DataSource
 * 2012 2012-9-3 上午10:38:48
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.DataSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ebag.net.obj.answer.AnswerAnalysis;
import org.ebag.net.obj.answer.GradeObj;

import android.app.Activity;

import com.todayedu.ebag.teacher.Parameters;
import com.todayedu.ebag.teacher.Parameters.ParaIndex;
import com.todayedu.ebag.teacher.Network.AnswerAnalysisHandler;

/**
 * @author <a href="zhenzxie.iteye.cn">zhenzxie</a>
 * @version 1.0
 * @since 1.0
 */
public class AnswerAnalysisDS extends BaseDataSource {
	
	private List<List<Map<String, String>>> childrenList;
	private AnswerAnalysis res=null;
	/**
	 * @param callback
	 */
	public AnswerAnalysisDS(DSCallback callback,
	        List<List<Map<String, String>>> childrenList) {
	
		super(callback);
		this.childrenList = childrenList;
	}
	
	@Override
	public void createMaps(String[] keys) {
	
		List<List<Map<String, String>>> lists = this.childrenList;
		List<Map<String, String>> list;

		if (res == null)
			return;

		list = createTotalTable(keys);
		lists.add(list);
		
		list = createMin(keys);
		lists.add(list);
		
		list = createMax(keys);
		lists.add(list);
		
	}
	
	@Override
	public void download(Activity context) {
	
		int cid = Parameters.get(ParaIndex.CID_INDEX);
		int eid = Parameters.get(ParaIndex.CID_INDEX);
		loadStart(new AnswerAnalysisHandler(context, this, cid, eid));
	}
	
	/**
	 * @return the res
	 */
	public AnswerAnalysis getRes() {
	
		return res;
	}
	
	/**
	 * @param res
	 *            the res to set
	 */
	public void setRes(AnswerAnalysis res) {
	
		this.res = res;
	}

	// 返回总分分排行榜的列表
	private List<Map<String, String>> createTotalTable(String[] keys) {

		if (res.allGradeList == null)
			return null;
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map;
		for (GradeObj gradeObj : res.allGradeList) {
			map = createMap(keys, gradeObj);
			list.add(map);
		}
		return list;
	}

	// 返回最少得分题目列表。列表中只有一项数据
	private List<Map<String, String>> createMax(String[] keys) {
	
		if (res.maxGrade == null)
			return null;
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map = createMap(keys, res.maxGrade);
		list.add(map);
		return list;
	}

	// 返回最多得分题目列表。列表中只有一项数据
	private List<Map<String, String>> createMin(String[] keys) {
	
		if (res.minGrade == null)
			return null;
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map = createMap(keys, res.minGrade);
		list.add(map);
		return list;
	}
	
	/**
	 * @param keys
	 * @return
	 */
	private Map<String, String> createMap(String[] keys, GradeObj gradeObj) {
	
		Map<String, String> map = new HashMap<String, String>();
		map.put(keys[0], gradeObj.u_name);
		map.put(keys[1], gradeObj.p_grade + "");
		return map;
	}
}
