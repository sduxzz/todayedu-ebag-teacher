/*
 * ExaminationSystem-Tea com.todayedu.ebag.teacher.DataSource.DataObj
 * 2012 2012-9-3 下午12:55:01
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.DataSource.DataObj;

import com.todayedu.ebag.teacher.DataSource.Data;


/**
 * @author <a href="zhenzxie.iteye.cn">zhenzxie</a>
 * @version 1.0
 * @since 1.0
 */
public class Analysis extends Data {
	
	private String name;
	private double score;
	
	public Analysis() {
	
	}
	
	public Analysis(String name, double score) {
	
		this.name = name;
		this.score = score;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
	
		return name;
	}
	
	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
	
		this.name = name;
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
	
	public static Analysis para2Analysis() {// TODO：增加所需参数
	
		Analysis analysis = new Analysis();
		// TODO:获取数据，设置analysis
		
		return analysis;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
	
		return "Analysis [name=" + name + ", score=" + score + "]";
	}
}
