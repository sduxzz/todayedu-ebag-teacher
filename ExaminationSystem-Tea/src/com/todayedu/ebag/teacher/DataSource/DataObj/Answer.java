/*
 * ExaminationSystem-Tea com.todayedu.ebag.teacher.DataSource.DataObj
 * 2012 2012-8-22 ����12:09:45
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.DataSource.DataObj;

import android.content.ContentValues;
import android.content.Context;
import android.os.Environment;

import com.todayedu.ebag.teacher.Parameters;
import com.todayedu.ebag.teacher.Parameters.ParaIndex;
import com.todayedu.ebag.teacher.Database.DataBaseHelper;
import com.todayedu.ebag.teacher.Database.annotation.Column;
import com.todayedu.ebag.teacher.Database.annotation.Id;
import com.todayedu.ebag.teacher.Database.annotation.Table;
import com.todayedu.ebag.teacher.UIModule.BaseActivity;

/**
 * @author <a href="zhenzxie.iteye.cn">zhenzxie</a>
 * @version 1.0
 * @since 1.0
 */
@Table(name = "ANSWER")
public class Answer {

	public static class PATH {
		
		public static final String SUBDIR = "/ebag";

		public static final String DIR = Environment
		        .getExternalStorageDirectory().getPath() + SUBDIR;
	}

	@Id
	@Column(name = "ID")
	private int id;// �ش�id
	@Column(name = "SID")
	private int sid;// ѧ��id
	@Column(name = "PID")
	private int pid;// ����id
	@Column(name = "POINT")
	private double point;// ѧ���ĳɼ�
	@Column(name = "STATE")
	private String state;
	@Column(name = "ANSWEROFTEA")
	private String answerofTea;// ��ʦ���ĺ�𰸵�·��
	private int number;
	private double score;// ��Ŀ���ܷ�
	private String content;
	private String textAnswer;// ��������ֻش�
	@Column(name = "TEXTOFTEACHER")
	private String textOfTeacher;// �������������
	private String answerofSta;// ��׼�𰸵�url
	private String answerofStu;// �����𰸵�·��
	
	static DataBaseHelper helper;

	public Answer() {
	
	}
	
	public void save(Context context) {
	
		getDBHelper(context);
		ContentValues cv = new ContentValues();
		cv.put("SID", Parameters.get(ParaIndex.SID_INDEX));
		cv.put("PID", pid);
		cv.put("ID", id);
		cv.put("STATE", state);
		cv.put("POINT", point);
		cv.put("TEXTOFTEACHER", textOfTeacher);
		cv.put("ANSWEROFTEA", answerofTea);
		if (helper.isAnswerExist(id)) {
			if (!helper.updateAnswer(cv, id))
				((BaseActivity) context).showToast("���±���ʧ�ܡ�������");
		} else {
			if (helper.insert(Answer.class, this, "ANSWER", cv) == 0L)
				((BaseActivity) context).showToast("����ʧ�ܡ�������");
		}
	}
	
	public static Answer query(Context context, int id, int sid, int pid) {
	
		getDBHelper(context);
		return helper.queryAnswer(id, sid, pid);
	}

	/**
	 * @param context
	 */
	private static void getDBHelper(Context context) {
	
		if (helper == null) {
			helper = new DataBaseHelper(context);
		}
	}

	/**
	 * @return the id
	 */
	public int getId() {
	
		return id;
	}
	
	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
	
		this.id = id;
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
	 * @param point
	 *            the point to set
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
	 * @return the textAnswer
	 */
	public String getTextAnswer() {
	
		if (textAnswer == null)
			return "û�����ֻش�";
		return textAnswer;
	}
	
	/**
	 * @param textAnswer
	 *            the textAnswer to set
	 */
	public void setTextAnswer(String textAnswer) {
	
		this.textAnswer = textAnswer;
	}
	
	/**
	 * @return the textOfTeacher
	 */
	public String getTextOfTeacher() {
	
		return textOfTeacher;
	}
	
	/**
	 * @param textOfTeacher
	 *            the textOfTeacher to set
	 */
	public void setTextOfTeacher(String textOfTeacher) {
	
		this.textOfTeacher = textOfTeacher;
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
	 * ��ȡ��ʦ���ĺ�ͼƬ��ȫ·��
	 * 
	 * @return the answerofTea
	 */
	public String getAnswerofTea() {
	
		return answerofTea;
	}
	
	/**
	 * ��ȡ��ʦ���ĺ�ͼƬ��Ӧ���е�ȫ·��
	 * 
	 * @param fileName
	 */
	public static String getAnswerofTeaFullPath(String fileName) {
	
		return PATH.DIR + "/" + fileName;
	}

	/**
	 * @param answerofTea
	 *            the answerofTea to set
	 */
	public void setAnswerofTea(String answerofTea) {
	
		this.answerofTea = answerofTea;
	}

	@Override
	public String toString() {
	
		return "Answer [id=" + id + ", sid=" + sid + ", pid=" + pid
		        + ", point=" + point + ", state=" + state + ", answerofTea="
		        + answerofTea + ", number=" + number + ", score=" + score
		        + ", content=" + content + ", textAnswer=" + textAnswer
		        + ", textOfTeacher=" + textOfTeacher + ", answerofSta="
		        + answerofSta + ", answerofStu=" + answerofStu + "]";
	}
}
