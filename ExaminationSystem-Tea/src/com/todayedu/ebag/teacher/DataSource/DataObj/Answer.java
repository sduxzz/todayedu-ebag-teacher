/*
 * ExaminationSystem-Tea com.todayedu.ebag.teacher.DataSource.DataObj
 * 2012 2012-8-22 下午12:09:45
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.DataSource.DataObj;

import java.util.ArrayList;
import java.util.List;

import org.ebag.net.obj.I.choice;
import org.ebag.net.obj.answer.AnswerObj;

import android.util.Log;

import com.todayedu.ebag.teacher.Constants.StateStr;
import com.todayedu.ebag.teacher.DataSource.Data;
import com.todayedu.ebag.teacher.Network.UrlBuilder;


/**
 * @author <a href="zhenzxie.iteye.cn">zhenzxie</a>
 * @version 1.0
 * @since 1.0
 */
public class Answer extends Data {
	
	private int sid;// 学生id
	private int id;// 回答id
	private int pid;// 问题id
	private int number;
	private double score;// 题目的总分
	private double point;// 学生的成绩
	private String state;
	private String content;
	public String textAnswer;// 问题的文字回答
	public String textOfTeacher;// 问题的文字批阅
	private String answerofSta;// 标准答案的url
	private String answerofStu;// 考生答案的路径
	private String answerofTea;// 老师批改后答案的路径
	

	public Answer() {
	
	}
	
	public Answer(int sid, int eid, int pid, int number, double score,
	        String state, String content, String answerofSta,
	        String answerofStu, String answerofTea) {
	
		this.sid = sid;
		this.id = eid;
		this.pid = pid;
		this.number = number;
		this.score = score;
		this.state = state;
		this.content = content;
		this.answerofSta = answerofSta;
		this.answerofStu = answerofStu;
		this.answerofTea = answerofTea;
	}
	
	public static List<Answer> parse(List<AnswerObj> list) {
	
		List<Answer> aList = new ArrayList<Answer>();
		int i = 1;
		for (AnswerObj obj : list) {
			aList.add(parse(obj, i++));
		}
		return aList;
	}

	public static Answer parse(AnswerObj obj, int number) {
	
		Answer answer = new Answer();
		answer.id = obj.id;
		answer.pid = obj.problemId;
		answer.sid = obj.uid;
		answer.number = number;
		answer.textAnswer = obj.textAnswer;
		answer.answerofStu = UrlBuilder.problemAnswerPicUrl(obj.picAnswerUrl);
		answer.answerofSta = UrlBuilder.problemAnswerUrl(answer.pid);
		answer.content = UrlBuilder.problemContentUrl(answer.pid);
		answer.score = obj.score;
		switch (obj.state) {
			case choice.answerState_waitAnser:
			case choice.answerState_waitMark:
				answer.state = StateStr.CORRECT;
				break;
			case choice.answerState_finish:
			case choice.answerState_waitComment:
			default:
				answer.state = StateStr.CORRECTED;
		}
		Log.i(answer.TAG, "parse:" + answer.toString());
		return answer;
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
	
		return id;
	}
	
	/**
	 * @param eid
	 *            the eid to set
	 */
	public void setEid(int eid) {
	
		this.id = eid;
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
     * @return the point
     */
    public double getPoint() {
    
	    return point;
    }

	/**
     * @param point the point to set
     */
    public void setPoint(double point) {
    
	    this.point = point;
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
	
		return "Answer [sid=" + sid + ", id=" + id + ", pid=" + pid
		        + ", number=" + number + ", score=" + score + ", point="
		        + point + ", state=" + state + ", content=" + content
		        + ", textAnswer=" + textAnswer + ", textOfTeacher="
		        + textOfTeacher + ", answerofSta=" + answerofSta
		        + ", answerofStu=" + answerofStu + ", answerofTea="
		        + answerofTea + "]";
	}
}
