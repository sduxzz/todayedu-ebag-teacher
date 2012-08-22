/*
 * ExaminationSystem-Tea com.todayedu.ebag.teacher.DataSource.DataObj
 * 2012 2012-8-22 下午12:09:45
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.DataSource.DataObj;

import com.todayedu.ebag.teacher.DataSource.Data;


/**
 * @author <a href="zhenzxie.iteye.cn">zhenzxie</a>
 * @version 1.0
 * @since 1.0
 */
public class Answer extends Data {
	
	private int sid;
	private int eid;
	private int pid;
	private int number;
	private double score;
	private String state;
	private String content;
	private String answerofSta;// 标准答案的url
	private String answerofStu;// 考生答案的路径
	private String answerofTea;// 老师批改后答案的路径
	
	public Answer() {
	
	}
	
	public Answer(int sid, int eid, int pid, int number, double score,
	        String state, String content, String answerofSta,
	        String answerofStu, String answerofTea) {
	
		this.sid = sid;
		this.eid = eid;
		this.pid = pid;
		this.number = number;
		this.score = score;
		this.state = state;
		this.content = content;
		this.answerofSta = answerofSta;
		this.answerofStu = answerofStu;
		this.answerofTea = answerofTea;
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
	 * @return the answerofSta
	 */
	public String getAnswerofSta() {
	
		return answerofSta;
	}
	
	/**
	 * @param answerofSta
	 *            the answerofSta to set
	 */
	public void setAnswerofSta(String answerofSta) {
	
		this.answerofSta = answerofSta;
	}
	
	/**
	 * @return the answerofStu
	 */
	public String getAnswerofStu() {
	
		return answerofStu;
	}
	
	/**
	 * @param answerofStu
	 *            the answerofStu to set
	 */
	public void setAnswerofStu(String answerofStu) {
	
		this.answerofStu = answerofStu;
	}
	
	/**
	 * @return the answerofTea
	 */
	public String getAnswerofTea() {
	
		return answerofTea;
	}
	
    /**
	 * @param answerofTea
	 *            the answerofTea to set
	 */
	public void setAnswerofTea(String answerofTea) {
	
		this.answerofTea = answerofTea;
	}
	
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
	
		return "Answer [sid=" + sid + ", eid=" + eid + ", pid=" + pid
		        + ", number=" + number + ", score=" + score + ", state="
		        + state + ", content=" + content + ", answerofSta="
		        + answerofSta + ", answerofStu=" + answerofStu
		        + ", answerofTea=" + answerofTea + "]";
	}
}
