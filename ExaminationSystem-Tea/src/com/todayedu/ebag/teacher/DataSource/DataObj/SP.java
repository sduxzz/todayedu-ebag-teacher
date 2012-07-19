/*
 * ExaminationSystem-Tea com.todayedu.ebag.teacher.OBJModule.DBObj SP.java
 * 2012 2012-7-6 涓嬪崍2:23:22
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.DataSource.DataObj;

import java.util.Map;

import android.content.Context;

import com.todayedu.ebag.teacher.DataSource.Data;
import com.todayedu.ebag.teacher.Database.annotation.Column;
import com.todayedu.ebag.teacher.Database.annotation.Table;


/**
 * the concrete class stand for data which come from the database table SP(sid
 * integer,pid integer,state integer,score double,flag integer,answer1
 * text,answer2 text,)学生和问题的联系
 * 
 * 
 * @author zhenzxie
 * 
 */
@Table(name = "SP")
public class SP extends Data {
	

	@Column(name = "sid", foreignKey = "STUDENT(sid)")
	private int sid;
	@Column(name = "pid", foreignKey = "PROBLEM(pid)")
	private int pid;
	@Column(name = "state")
	private int state;/* 题的状态：是否批改完 */
	@Column(name = "score")
	private double score;
	@Column(name = "flag")
	private int flag;
	@Column(name = "answer1")
	private String answer1;
	@Column(name = "answer2")
	private String answer2;
	
	public SP() {
	
	}

	
	/**
	 * @param sid
	 * @param pid
	 * @param state
	 * @param score
	 * @param flag
	 * @param answer1
	 * @param answer2
	 */
	public SP(int sid, int pid, int state, double score, int flag,
			String answer1, String answer2) {
	
		this.sid = sid;
		this.pid = pid;
		this.state = state;
		this.score = score;
		this.flag = flag;
		this.answer1 = answer1;
		this.answer2 = answer2;
	}
	
	@Override
	public void save(Context context) {
	
	}
	
	@Override
	public Map<String, String> changeToMap(String[] keys) {
	
		return null;
	}
	/**
	 * @return the sid
	 */
	public int getSid() {
	
		return sid;
	}

	
	/**
	 * @param sid
	 *            the sid to set
	 */
	public void setSid(int sid) {
	
		this.sid = sid;
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
	 * @return the state
	 */
	public int getState() {
	
		return state;
	}

	
	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(int state) {
	
		this.state = state;
	}

	
	/**
	 * @return the score
	 */
	public double getScore() {
	
		return score;
	}

	
	/**
	 * @param score
	 *            the score to set
	 */
	public void setScore(double score) {
	
		this.score = score;
	}

	
	/**
	 * @return the flag
	 */
	public int getFlag() {
	
		return flag;
	}

	
	/**
	 * @param flag
	 *            the flag to set
	 */
	public void setFlag(int flag) {
	
		this.flag = flag;
	}

	
	/**
	 * @return the answer1
	 */
	public String getAnswer1() {
	
		return answer1;
	}

	
	/**
	 * @param answer1
	 *            the answer1 to set
	 */
	public void setAnswer1(String answer1) {
	
		this.answer1 = answer1;
	}

	
	/**
	 * @return the answer2
	 */
	public String getAnswer2() {
	
		return answer2;
	}

	
	/**
	 * @param answer2
	 *            the answer2 to set
	 */
	public void setAnswer2(String answer2) {
	
		this.answer2 = answer2;
	}


	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
	
		return "SP [sid=" + sid + ", pid=" + pid + ", state=" + state
				+ ", score=" + score + ", flag=" + flag + ", answer1="
				+ answer1 + ", answer2=" + answer2 + "]";
	}
}
