/*
 * ExaminationSystem-Tea com.todayedu.examinationsystem.teacher.DataSource
 * 2012 2012-5-31 锟斤拷锟斤拷6:49:40
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.DataSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ebag.net.obj.exam.ProblemInfoObj;

import android.app.Activity;
import android.content.Context;
import android.util.SparseArray;

import com.todayedu.ebag.teacher.Constants.StateStr;
import com.todayedu.ebag.teacher.Parameters;
import com.todayedu.ebag.teacher.Parameters.ParaIndex;
import com.todayedu.ebag.teacher.DataSource.DataObj.Problem;
import com.todayedu.ebag.teacher.Network.ExamHandler;
import com.todayedu.ebag.teacher.Network.UrlBuilder;

/**
 * @author zhenzxie
 * 
 */
public class PCommentDS extends BaseDataSource {

	private SparseArray<Problem> temp = new SparseArray<Problem>();
	private List<ProblemInfoObj> pInfoList;
	private int index = 0;

	public PCommentDS(DSCallback callback) {
	
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
		loadStart(new ExamHandler(context, this, cid, null, idList, fieldList));
	}

	@Override
	public void createMaps(String[] keys) {
	
		// createMaps(Context, String[]);
	}
	
	public void createMaps(Context context, String[] keys) {

		List<ProblemInfoObj> list = this.getpInfoList();
		List<Map<String, String>> maps = this.getListMap();
		maps.clear();
		Map<String, String> map = null;
		int i = 1;
		for (ProblemInfoObj obj : list) {
			map = new HashMap<String, String>();
			map.put(keys[0], "第" + (i++) + "题");
			map.put(keys[1], Problem.getStateFromDB(obj.id, context));// 可以优化
			maps.add(map);
		}
	}
	
	public void onLable(Context context) {
	
		Problem problem = (Problem) getCurrentProblem();
		problem.setState(StateStr.COMMENTED);
		problem.save(context);
		Map<String, String> map = getListMap().get(index);
		map.put("state", StateStr.COMMENTED);
	}
	
	/**
	 * 获得当前ProblemInfoObj所代表的Problem
	 * 
	 * @return Problem
	 */
	public Problem getCurrentProblem() {
	
		if (pInfoList == null)
			return null;
		ProblemInfoObj obj = pInfoList.get(index);
		int id = obj.id;
		Problem problem = temp.get(id);
		if (problem == null) {
			problem = new Problem();
			problem.setPid(id);
			problem.setNumber(index + 1);
			problem.setPoint(obj.point);
			problem.setState(getListMap().get(index).get("state"));
			problem.setContent(UrlBuilder.problemContentUrl(id));
			problem.setAnswer(UrlBuilder.problemAnswerUrl(id));
			problem.setAnalysis(UrlBuilder.problemAnalysis(id));
			temp.put(id, problem);
		}
		return problem;
	}

	/**
	 * 向后移动一位
	 */
	public void moveToNext() {
	
		if (canNext()) {
			index++;
		}
	}
	
	/**
	 * 是否能向后移动一位
	 * 
	 * @return boolean
	 */
	public boolean canNext() {
	
		if (getpInfoList() == null)
			return false;
		if (index >= getpInfoList().size() - 1) {
			return false;
		}
		return true;
	}

	/**
	 * 向前移动一位
	 */
	public void moveToPrevious() {
	
		if (canPrevious()) {
			index--;
		}
	}
	
	/**
	 * 是否能向前移动一位
	 * 
	 * @return boolean
	 */
	public boolean canPrevious() {
	
		if (index <= 0) {
			return false;
		}
		return true;
	}

	/**
	 * 移动到index位置
	 * 
	 * @param index
	 */
	public boolean moveTo(int index) {
	
		if (index >= 0 && index < getpInfoList().size()) {
			this.index = index;
			return true;
		}
		return false;
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
	
	/**
	 * @return the index
	 */
	public int getIndex() {
	
		return index;
	}
	
	/**
	 * @param index
	 *            the index to set
	 */
	public void setIndex(int index) {
	
		this.index = index;
	}
}
