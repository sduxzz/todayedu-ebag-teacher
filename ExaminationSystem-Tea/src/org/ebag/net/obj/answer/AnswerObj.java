package org.ebag.net.obj.answer;

import java.io.Serializable;

public class AnswerObj implements Serializable{

	private static final long serialVersionUID = 5010565192402842407L;

	public int reuqestType;
	/**回答id*/
	public int id;
	/**答题人id*/
	public int uid;
	/**问题id*/
	public int problemId;
	/**问题的文字回答*/
	public String textAnswer;
	/**问题的文字批阅*/
	public String textOfTeacher;
	/**问题答案图片的上传文件名（按照PicaUploadDemo/PicUpload.java中的方法取得唯一文件名）*/
	public String picAnswerUrl;
	@Deprecated
	public byte[] picAnswerByte;
	/**老师批注图片的上传文件名（按照PicaUploadDemo/PicUpload.java中的方法取得唯一文件名）*/
	public String picOfTeacherUrl;
	@Deprecated
	public byte[] picOfTeacherByte;
	/**当前状态，等待回答、回答等待批注、批注等待讲评、全做完了（I.java中）*/
	public int state;
	/**得分，选择题上传时应填写此字段*/
	public double point;
	/**score*/
	public double score;
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
	public String getTextOfTeacher() {
		return textOfTeacher;
	}
	public void setTextOfTeacher(String textOfTeacher) {
		this.textOfTeacher = textOfTeacher;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
}
