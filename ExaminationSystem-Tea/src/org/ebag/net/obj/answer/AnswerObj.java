package org.ebag.net.obj.answer;

import java.io.Serializable;

public class AnswerObj implements Serializable{

	private static final long serialVersionUID = 5010565192402842407L;

	public int reuqestType;
	
	public int id;
	public int uid;
	public int problemId;
	public String textAnswer;
	public String picAnswerUrl;
	public byte[] picAnswerByte;
	public String picOfTeacherUrl;
	public byte[] picOfTeacherByte;
	public int state;
	public double point;
	
	public int getReuqestType() {
		return reuqestType;
	}
	public void setReuqestType(int reuqestType) {
		this.reuqestType = reuqestType;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getProblemId() {
		return problemId;
	}
	public void setProblemId(int problemId) {
		this.problemId = problemId;
	}
	public String getTextAnswer() {
		return textAnswer;
	}
	public void setTextAnswer(String textAnswer) {
		this.textAnswer = textAnswer;
	}
	public String getPicAnswerUrl() {
		return picAnswerUrl;
	}
	public void setPicAnswerUrl(String picAnswerUrl) {
		this.picAnswerUrl = picAnswerUrl;
	}
	public byte[] getPicAnswerByte() {
		return picAnswerByte;
	}
	public void setPicAnswerByte(byte[] picAnswerByte) {
		this.picAnswerByte = picAnswerByte;
	}
	public String getPicOfTeacherUrl() {
		return picOfTeacherUrl;
	}
	public void setPicOfTeacherUrl(String picOfTeacherUrl) {
		this.picOfTeacherUrl = picOfTeacherUrl;
	}
	public byte[] getPicOfTeacherByte() {
		return picOfTeacherByte;
	}
	public void setPicOfTeacherByte(byte[] picOfTeacherByte) {
		this.picOfTeacherByte = picOfTeacherByte;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public double getPoint() {
		return point;
	}
	public void setPoint(double point) {
		this.point = point;
	}
}
