/*
 * ExaminationSystem-Tea com.todayedu.ebag.teacher.OBJModule.DBObj ES.java
 * 2012 2012-7-6 涓嬪崍2:17:23
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.DataSource.DataObj;

import java.util.Map;

import android.content.Context;

import com.todayedu.ebag.teacher.DataSource.Data;
import com.todayedu.ebag.teacher.Database.annotation.Column;
import com.todayedu.ebag.teacher.Database.annotation.Table;

/**
 * the concrete class stand for data which come from the database table ES(eid
 * integer,sid integer,state integer,score double) 试卷和学生的联系
 * 
 * @author zhenzxie
 * 
 */
@Table(name = "ES")
public class ES extends Data {
	
	@Column(name = "eid", foreignKey = "EXAM(eid)")
	private int eid;
	@Column(name = "sid", foreignKey = "STUDENT(sid)")
	private int sid;
	@Column(name = "state")
	private int state;/* 学生某套试卷的状态：是否交卷，是否批改 */
	@Column(name = "score")
	private double score;
	
	public ES() {
	
	}

	/**
	 * @param eid
	 * @param sid
	 * @param state
	 * @param score
	 */
	public ES(int eid, int sid, int state, double score) {
	
		this.eid = eid;
		this.sid = sid;
		this.state = state;
		this.score = score;
	}

	@Override
	public void save(Context context) {
	
	}
	
	@Override
	public Map<String, String> changeToMap(String[] keys) {
	
		return null;
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
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
	
		return "ES [eid=" + eid + ", sid=" + sid + ", state=" + state
				+ ", score=" + score + "]";
	}

}