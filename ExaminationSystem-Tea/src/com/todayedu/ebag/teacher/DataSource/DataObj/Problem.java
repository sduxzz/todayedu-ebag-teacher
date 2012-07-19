/*
 * ExaminationSystem-Tea com.todayedu.ebag.teacher.OBJModule.DBObj
 * 2012 2012-7-3 锟斤拷锟斤拷4:23:29
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.DataSource.DataObj;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.ebag.net.obj.exam.ExamObj;
import org.ebag.net.obj.exam.ProblemInfoObj;
import org.ebag.net.response.ExamResponse;

import android.content.Context;

import com.todayedu.ebag.teacher.DataSource.Data;
import com.todayedu.ebag.teacher.Database.annotation.Column;
import com.todayedu.ebag.teacher.Database.annotation.Id;
import com.todayedu.ebag.teacher.Database.annotation.Table;

/**
 * the concrete class stand for the data which come from the database table
 * PROBLEM(pid integer,ptype integer,content text,hint text,answer text,analysis
 * text) 问题实体
 * 
 * @author zhenzxie
 * 
 */
@Table(name = "PROBLEM")
public class Problem extends Data {
	
	@Id
	@Column(name = "pid")
	private int pid;
	@Column(name = "ptype")
	private int ptype;
	@Column(name = "point")
	private double point;
	@Column(name = "content")
	private String content;
	@Column(name = "hint")
	private String hint;
	@Column(name = "answer")
	private String answer;
	@Column(name = "analysis")
	private String analysis;
	
	public Problem() {

	}

	public Problem(int pid, int ptype, double point, String content,
			String hint, String answer, String analysis) {

		this.pid = pid;
		this.ptype = ptype;
		this.point = point;
		this.content = content;
		this.hint = hint;
		this.answer = answer;
		this.analysis = analysis;
	}

	public static List<Data> parse(ExamResponse response, int position) {

		List<ExamObj> examList = response.getExamList();
		if(position >= examList.size()||position <0)
			return null;
		ExamObj obj = examList.get(position);
		return parseToExamObj(obj);
	}

	/**
	 * get Problem List from ExamObj's problemInfoObj List
	 * 
	 * @param obj
	 * @return List<Problem>
	 */
	public static List<Data> parseToExamObj(ExamObj obj) {
	
		List<ProblemInfoObj> infoObjList = obj.pInfoList;
		List<Data> problemList = new ArrayList<Data>();
		Problem problem = null;
		
		for (ProblemInfoObj info : infoObjList) {
			problem = new Problem();
			problem.setPid(info.id);
			problem.setPtype(info.type);
			problem.setPoint(info.point);
			problem.setAnswer(info.answer);
			problemList.add(problem);
		}
		
		return problemList;
	}

	@Override
	public void save(Context context) {
	
	}
	
	@Override
	public Map<String, String> changeToMap(String[] keys) {
	
		return null;
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
	 * @return the hint
	 */
	public String getHint() {
	
		return hint;
	}
	
	/**
	 * @param hint
	 *            the hint to set
	 */
	public void setHint(String hint) {
	
		this.hint = hint;
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
	
		return "ProblemObj [pid=" + pid + ", ptype=" + ptype + ", point="
				+ point + ", content=" + content + ", hint=" + hint
				+ ", answer=" + answer + ", analysis=" + analysis + "]";
	}
}
