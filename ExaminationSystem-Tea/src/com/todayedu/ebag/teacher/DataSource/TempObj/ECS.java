/*
 * ExaminationSystem-Tea com.todayedu.ebag.teacher.DataSource.TempObj ECS.java
 * 2012 2012-7-17 ÏÂÎç7:29:26
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.DataSource.TempObj;

import android.content.Context;

import com.todayedu.ebag.teacher.DataSource.Data;


/**
 * @author zhenzxie
 *
 */
public class ECS extends Data {
	
	private int number;
	private int state;
	private int point;
	private int flag;
	
	public ECS() {
	
	}


	/**
	 * @param number
	 * @param state
	 * @param point
	 * @param flag
	 */
	public ECS(int number, int state, int point, int flag) {
	
		this.number = number;
		this.state = state;
		this.point = point;
		this.flag = flag;
	}


	/**
	 * @see com.todayedu.ebag.teacher.DataSource.Data#save(android.content.Context)
	 */
	@Override
	public boolean save(Context context) {
	
		return false;
	
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
	 * @return the point
	 */
	public int getPoint() {
	
		return point;
	}
	
	/**
	 * @param point
	 *            the point to set
	 */
	public void setPoint(int point) {
	
		this.point = point;
	}
	
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
	
		return "ECS [number=" + number + ", state=" + state + ", point="
				+ point + ", flag=" + flag + "]";
	}
	
}
