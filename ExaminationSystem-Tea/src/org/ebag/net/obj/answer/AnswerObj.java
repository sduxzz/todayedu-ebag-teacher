package org.ebag.net.obj.answer;

import java.io.Serializable;

public class AnswerObj implements Serializable{

	private static final long serialVersionUID = 5010565192402842407L;

	public int reuqestType;
	/**�ش�id*/
	public int id;
	/**������id*/
	public int uid;
	/**����id*/
	public int problemId;
	/**��������ֻش�*/
	public String textAnswer;
	/**�������������*/
	public String textOfTeacher;
	/**�����ͼƬ���ϴ��ļ���������PicaUploadDemo/PicUpload.java�еķ���ȡ��Ψһ�ļ�����*/
	public String picAnswerUrl;
	@Deprecated
	public byte[] picAnswerByte;
	/**��ʦ��עͼƬ���ϴ��ļ���������PicaUploadDemo/PicUpload.java�еķ���ȡ��Ψһ�ļ�����*/
	public String picOfTeacherUrl;
	@Deprecated
	public byte[] picOfTeacherByte;
	/**��ǰ״̬���ȴ��ش𡢻ش�ȴ���ע����ע�ȴ�������ȫ�����ˣ�I.java�У�*/
	public int state;
	/**�÷֣�ѡ�����ϴ�ʱӦ��д���ֶ�*/
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
