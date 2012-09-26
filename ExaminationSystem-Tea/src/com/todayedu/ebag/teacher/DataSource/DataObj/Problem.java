/*
 * ExaminationSystem-Tea com.todayedu.ebag.teacher.OBJModule.DBObj
 * 2012 2012-7-3 ����4:23:29
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.DataSource.DataObj;

import android.content.ContentValues;
import android.content.Context;

import com.todayedu.ebag.teacher.Parameters;
import com.todayedu.ebag.teacher.Parameters.ParaIndex;
import com.todayedu.ebag.teacher.Database.DataBaseHelper;
import com.todayedu.ebag.teacher.Database.annotation.Column;
import com.todayedu.ebag.teacher.Database.annotation.Id;
import com.todayedu.ebag.teacher.Database.annotation.Table;

/**
 * the entity of Problem
 * 
 * @author zhenzxie
 * 
 */
@Table(name = "PROBLEM")
public class Problem {
	
	@Id
	@Column(name = "pid")
	private int pid;
	@Column(name = "eid")
	private int eid;
	@Column(name = "cid")
	private int cid;
	@Column(name = "state")
	private String state;// COMMENT,COMMENTED
	private int number;// index in exam
	private double point;
	private String content;
	private String answer;
	private String analysis;
	
	static DataBaseHelper helper;

	public Problem() {

	}

	public static String getStateFromDB(int pid, Context context) {

		getDBHelper(context);
		String state = helper.queryProState(pid);
		return state;
	}

	public void save(Context context) {
	
		getDBHelper(context);
		ContentValues cv = new ContentValues();
		cv.put("pid", pid);
		cv.put("eid", Parameters.get(ParaIndex.EID_INDEX));
		cv.put("cid", Parameters.get(ParaIndex.CID_INDEX));
		cv.put("state", state);
		helper.insert(Problem.class, this, "PROBLEM", cv);
	}
	
	/**
	 * @return the pid
	 */
	public int getPid() {
	
		return pid;
	}

	
	/**
	 * @param pid
	 *            the pid to set
	 */
	public void setPid(int pid) {
	
		this.pid = pid;
	}
	
    /**
	 * @return the number
	 */
	public int getNumber() {
	
		return number;
	}
	
    /**
	 * @param number
	 *            the number to set
	 */
	public void setNumber(int number) {
	
		this.number = number;
	}
	
    /**
	 * @return the state
	 */
	public String getState() {
	
		return state;
	}
	
    /**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
	
		this.state = state;
	}
	
    /**
	 * @return the point
	 */
	public double getPoint() {
	
		return point;
	}
	
    /**
	 * @param point
	 *            the point to set
	 */
	public void setPoint(double point) {
	
		this.point = point;
	}
	
    /**
	 * @return the answer
	 */
	public String getAnswer() {
	
		return answer;
	}
	
    /**
	 * @param answer
	 *            the answer to set
	 */
	public void setAnswer(String answer) {
	
		this.answer = answer;
	}
	
	/**
	 * @return the content
	 */
	public String getContent() {
	
		return content;
	}
	
	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
	
		this.content = content;
	}
	
	/**
	 * @return the analysis
	 */
	public String getAnalysis() {
	
		return analysis;
	}
	
    /**
	 * @param analysis
	 *            the analysis to set
	 */
	public void setAnalysis(String analysis) {
	
		this.analysis = analysis;
	}
	
	@Override
	public String toString() {
	
		return "Problem [pid=" + pid + ", eid=" + eid + ", cid=" + cid
		        + ", state=" + state + ", number=" + number + ", point="
		        + point + ", content=" + content + ", answer=" + answer
		        + ", analysis=" + analysis + "]";
	}

	/**
	 * @param context
	 */
	private static void getDBHelper(Context context) {
	
		if (helper == null) {
			helper = new DataBaseHelper(context);
		}
	}
}
