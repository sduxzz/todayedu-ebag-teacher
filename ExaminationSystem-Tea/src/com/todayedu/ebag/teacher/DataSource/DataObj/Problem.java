/*
 * ExaminationSystem-Tea com.todayedu.ebag.teacher.OBJModule.DBObj
 * 2012 2012-7-3 ����4:23:29
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.DataSource.DataObj;

import java.util.ArrayList;
import java.util.List;

import org.ebag.net.obj.exam.ExamObj;
import org.ebag.net.obj.exam.ProblemInfoObj;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.todayedu.ebag.teacher.Constants.StateStr;
import com.todayedu.ebag.teacher.Parameters;
import com.todayedu.ebag.teacher.Parameters.ParaIndex;
import com.todayedu.ebag.teacher.DataSource.Data;
import com.todayedu.ebag.teacher.Database.DataBaseHelper;
import com.todayedu.ebag.teacher.Database.annotation.Column;
import com.todayedu.ebag.teacher.Database.annotation.Id;
import com.todayedu.ebag.teacher.Database.annotation.Table;
import com.todayedu.ebag.teacher.Network.UrlBuilder;

/**
 * the entity of Problem
 * 
 * @author zhenzxie
 * 
 */
@Table(name = "PROBLEM")
public class Problem extends Data {
	
	@Id
	@Column(name = "pid")
	private int pid;
	@Column(name = "eid")
	private int eid = Parameters.get(ParaIndex.EID_INDEX);
	@Column(name = "cid")
	private int cid = Parameters.get(ParaIndex.CID_INDEX);
	@Column(name = "state")
	private String state;// COMMENT,COMMENTED
	private int number;// index in exam
	private int ptype;
	private double point;
	private String content;
	private String answer;
	private String analysis;
	
	static DataBaseHelper helper;

	public Problem() {

	}

	public Problem(int pid, int ptype, double point, String answer) {

		this.pid = pid;
		this.ptype = ptype;
		this.point = point;
		this.answer = answer;
	}
	/**
	 * get Problem List from ExamObj's problemInfoObj List
	 * 
	 * @param obj
	 * @return List<Problem>
	 */
	public static List<Data> parse2ProblemList(ExamObj obj, Context context) {
	
		List<ProblemInfoObj> infoObjList = obj.pInfoList;
		List<Data> problemList = new ArrayList<Data>();
		Problem problem = null;
		Log.i("Problem", "parse2ProblemList: pInfoList's length is "
		        + infoObjList.size());
		int index = 0;
		for (ProblemInfoObj info : infoObjList) {
			problem = new Problem();
			problem.setPid(info.id);
			problem.setPtype(info.type);
			problem.setPoint(info.point);
			problem.setAnswer(info.answer);
			problem.setNumber(++index);
			//problem.setState(getStateFromDB(info.id, context));//TODO:get the problem state from db
			problem.setState(StateStr.COMMENT);
			problem.setContent(UrlBuilder.problemContentUrl(info.id));
			problem.setAnswer(UrlBuilder.problemAnswerUrl(info.id));
			problem.setAnalysis(UrlBuilder.problemAnalysis(info.id));
			problemList.add(problem);
			Log.i("Problem", "parse2ProblemList:" + problem);
		}
		
		return problemList;
	}

	private static String getStateFromDB(int pid, Context context) {

		if (helper == null) {
			helper = new DataBaseHelper(context);
		}
		String state = helper.queryProState(pid);
		return state;
	}

	public void save(Context context) {
	
		if (helper == null) {
			helper = new DataBaseHelper(context);
		}
		ContentValues cv = new ContentValues();
		cv.put("pid", pid);
		cv.put("eid", eid);
		cv.put("cid", cid);
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
	 * @return the ptype
	 */
	public int getPtype() {
	
		return ptype;
	}
	
    /**
	 * @param ptype
	 *            the ptype to set
	 */
	public void setPtype(int ptype) {
	
		this.ptype = ptype;
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
	 * @return the eid
	 */
	public int getEid() {
	
		return eid;
	}
	
	/**
	 * @param eid
	 *            the eid to set
	 */
	public void setEid(int eid) {
	
		this.eid = eid;
	}
	
	/**
	 * @return the cid
	 */
	public int getCid() {
	
		return cid;
	}
	
	/**
	 * @param cid
	 *            the cid to set
	 */
	public void setCid(int cid) {
	
		this.cid = cid;
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
	
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
	
		return "Problem [pid=" + pid + ", eid=" + eid + ", cid=" + cid
		        + ", state=" + state + ", number=" + number + ", ptype="
		        + ptype + ", point=" + point + ", content=" + content
		        + ", answer=" + answer + ", analysis=" + analysis + "]";
	}
}
