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

import android.app.Activity;

import com.todayedu.ebag.teacher.Parameters;
import com.todayedu.ebag.teacher.Parameters.ParaIndex;
import com.todayedu.ebag.teacher.DataSource.DataObj.Analysis;
import com.todayedu.ebag.teacher.Network.AnswerAnalysisHandler;

/**
 * @author <a href="zhenzxie.iteye.cn">zhenzxie</a>
 * @version 1.0
 * @since 1.0
 */
public class AnswerAnalysisDS extends BaseDataSource {
	
	private List<List<Map<String, String>>> childrenList;
	private Analysis max;
	private Analysis min;
	/**
	 * @param callback
	 */
	public AnswerAnalysisDS(DSCallback callback,
	        List<List<Map<String, String>>> childrenList) {
	
		super(callback);
		this.childrenList = childrenList;
	}
	
	/**
	 * @see com.todayedu.ebag.teacher.DataSource.BaseDataSource#createMaps(java.lang.String[])
	 */
	@Override
	public void createMaps(String[] keys) {
	
		List<List<Map<String, String>>> lists = this.childrenList;
		
		List<Map<String, String>> list;

		list = createTotalTable(keys);
		lists.add(list);
		
		list = createMax(keys);
		lists.add(list);
		
		list = createMin(keys);
		lists.add(list);
		
	}
	
	/**
	 * @see com.todayedu.ebag.teacher.DataSource.BaseDataSource#download(android.content.Context)
	 */
	@Override
	protected void download(Activity context) {
	
		int cid = Parameters.get(ParaIndex.CID_INDEX);
		int eid = Parameters.get(ParaIndex.CID_INDEX);
		connect(new AnswerAnalysisHandler(context, this, cid, eid));
	}
	
	/**
	 * @return the max
	 */
	public Analysis getMax() {
	
		return max;
	}
	
	/**
	 * @param max
	 *            the max to set
	 */
	public void setMax(Analysis max) {
	
		this.max = max;
	}
	
	/**
	 * @return the min
	 */
	public Analysis getMin() {
	
		return min;
	}
	
	/**
	 * @param min
	 *            the min to set
	 */
	public void setMin(Analysis min) {
	
		this.min = min;
	}

	// 返回总分分排行榜的列表
	private List<Map<String, String>> createTotalTable(String[] keys) {
	
		List<? extends Data> data = this.getList();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map;
		Analysis analysis;
		
		for (Data d : data) {
			analysis = (Analysis) d;
			map = createMap(keys, analysis);
			list.add(map);
		}
		return list;
	}

	// 返回最少得分题目列表。列表中只有一项数据
	private List<Map<String, String>> createMax(String[] keys) {
	
		Analysis data = this.getMax();
		return create(data, keys);
	}
	
	// 返回最多得分题目列表。列表中只有一项数据
	private List<Map<String, String>> createMin(String[] keys) {
	
		Analysis data = this.getMax();
		return create(data, keys);
	}
	
	private List<Map<String, String>> create(Analysis data, String[] keys) {
	
		final Analysis analysis = data;
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map = createMap(keys, analysis);
		list.add(map);
		return list;
	}
	
	/**
	 * @param keys
	 * @param analysis
	 * @return
	 */
	private Map<String, String> createMap(String[] keys, Analysis analysis) {
	
		Map<String, String> map = new HashMap<String, String>();
		map.put(keys[0], analysis.getName());
		map.put(keys[1], String.valueOf(analysis.getScore()));
		return map;
	}
	
}
