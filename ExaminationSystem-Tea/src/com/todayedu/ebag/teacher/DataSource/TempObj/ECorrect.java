/*
 * ExaminationSystem-Tea com.todayedu.ebag.teacher.DataSource.TempObj ECorrect.java
 * 2012 2012-7-17 ÏÂÎç4:59:54
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.DataSource.TempObj;

import android.content.Context;

import com.todayedu.ebag.teacher.DataSource.Data;


/**
 * @author zhenzxie
 *
 */
public class ECorrect extends Data {
	
	private int sid;
	private String sname;
	private int state;

	public ECorrect() {
	
	}
	
	/**
	 * @param sid
	 * @param sname
	 * @param state
	 */
	public ECorrect(int sid, String sname, int state) {
	
		this.sid = sid;
		this.sname = sname;
		this.state = state;
	}
	
	/**
	 * @see com.todayedu.ebag.teacher.DataSource.Data#save(android.content.Context)
	 */
	@Override
	public void save(Context context) {
	
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
	 * @return the sname
	 */
	public String getSname() {
	
		return sname;
	}
	
	/**
	 * @param sname
	 *            the sname to set
	 */
	public void setSname(String sname) {
	
		this.sname = sname;
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
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
	
		return "ECorrect [sid=" + sid + ", sname=" + sname + ", state=" + state
				+ "]";
	}
	
}
