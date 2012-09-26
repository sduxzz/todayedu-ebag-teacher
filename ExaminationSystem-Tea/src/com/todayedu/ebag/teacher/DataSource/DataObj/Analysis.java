/*
 * ExaminationSystem-Tea com.todayedu.ebag.teacher.DataSource.DataObj
 * 2012 2012-9-3 ÏÂÎç12:55:01
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.DataSource.DataObj;



/**
 * @author <a href="zhenzxie.iteye.cn">zhenzxie</a>
 * @version 1.0
 * @since 1.0
 */
public class Analysis {
	
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
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
	
		return "Analysis [name=" + name + ", score=" + score + "]";
	}
}
