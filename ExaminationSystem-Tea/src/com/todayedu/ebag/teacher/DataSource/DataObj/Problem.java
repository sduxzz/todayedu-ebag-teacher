/*
 * ExaminationSystem-Tea com.todayedu.ebag.teacher.OBJModule.DBObj
 * 2012 2012-7-3 锟斤拷锟斤拷4:23:29
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.DataSource.DataObj;

import java.util.ArrayList;
import java.util.List;

import org.ebag.net.obj.I.choice;
import org.ebag.net.obj.exam.ExamObj;
import org.ebag.net.obj.exam.ProblemInfoObj;

import android.content.Context;

import com.todayedu.ebag.teacher.DataSource.Data;
import com.todayedu.ebag.teacher.Database.annotation.Column;
import com.todayedu.ebag.teacher.Database.annotation.Id;
import com.todayedu.ebag.teacher.Database.annotation.Table;

/**
 * 问题实体
 * 
 * @author zhenzxie
 * 
 */
@Table(name = "PROBLEM")
public class Problem extends Data {
	
	@Id
	@Column(name = "pid")
	private int pid;
	@Column(name = "number")
	private int number;// index in exam
	@Column(name = "state")
	private int state = choice.answerState_waitComment;//defalut state is waitComment
	@Column(name = "ptype")
	private int ptype;
	@Column(name = "point")
	private double point;
	@Column(name = "answer")
	private String answer;
	

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
	public static List<Data> parseToProblemList(ExamObj obj) {
	
		List<ProblemInfoObj> infoObjList = obj.pInfoList;
		List<Data> problemList = new ArrayList<Data>();
		Problem problem = null;
		
		int index = 0;
		for (ProblemInfoObj info : infoObjList) {
			problem = new Problem();
			problem.setPid(info.id);
			problem.setPtype(info.type);
			problem.setPoint(info.point);
			problem.setAnswer(info.answer);
			problem.setNumber(++index);
			problemList.add(problem);
		}
		
		return problemList;
	}

	@Override
	public boolean save(Context context) {
	
		return false;
	
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
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
	
		return "Problem [pid=" + pid + ", number=" + number + ", state="
		        + state + ", ptype=" + ptype + ", point=" + point + ", answer="
		        + answer + "]";
	}
	
}
