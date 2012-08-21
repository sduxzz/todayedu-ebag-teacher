/*
 * ExaminationSystem-Tea com.todayedu.ebag.teacher.OBJModule.DBObj ExamObj.java
 * 2012 2012-7-6 涓嬪崍12:48:34
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.DataSource.DataObj;

import org.ebag.net.obj.exam.ExamObj;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.todayedu.ebag.teacher.Parameters;
import com.todayedu.ebag.teacher.Parameters.ParaIndex;
import com.todayedu.ebag.teacher.DataSource.Data;
import com.todayedu.ebag.teacher.Database.DataBaseHelper;
import com.todayedu.ebag.teacher.Database.annotation.Column;
import com.todayedu.ebag.teacher.Database.annotation.Id;
import com.todayedu.ebag.teacher.Database.annotation.Table;

/**
 * 试卷实体
 * 
 * @author zhenzxie
 * 
 */
@Table(name = "EXAM")
public class Exam extends Data {

	@Id
	@Column(name = "eid")
	private int eid;
	@Column(name = "cid")
	private int cid;
	@Column(name = "etype")
	private int etype;
	@Column(name = "ename")
	private String ename;
	@Column(name = "etotal")
	private double total;// ExamObj中总分字段为points
	@Column(name = "etime")
	private long etime;
	@Column(name = "state")
	private int state;/* 试卷的状态：要讲评的，要考试的，要批改的，完成的 */

	public Exam() {

	}
	
	/**
	 * convert ExamObj to Exam this method can rename the exam's name according
	 * to the count of the same of exam,but it will cost more time
	 * 
	 * @param context
	 * @param obj
	 * @return Exam
	 */
	public static Exam convert(Context context, ExamObj obj) {
	
		DataBaseHelper helper = new DataBaseHelper(context);
		SQLiteDatabase database = helper.getReadableDatabase();
		int id = obj.id;
		String ename = obj.name;
		
		Cursor cursor = database.rawQuery("select ename from EXAM where id = ",
		        new String[] { String.valueOf(id) });
		int count = cursor.getCount();
		if (count != 0)
			ename = ename + "[ " + count + " ]";
		
		Exam exam = new Exam();
		exam.eid = id;
		exam.ename = ename;
		exam.etype = obj.type;
		exam.etime = obj.time;
		exam.total = obj.points;
		
		return exam;
	}
	
	/**
	 * convert ExamObj to Exam
	 * 
	 * @param obj
	 * @return
	 */
	public static Exam convert(ExamObj obj) {
	
		Exam exam = new Exam();
		exam.eid = obj.id;
		exam.cid = Parameters.get(ParaIndex.CID_INDEX);
		exam.ename = obj.name;
		exam.etype = obj.type;
		exam.etime = obj.time;
		exam.total = obj.points;
		
		return exam;
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
	 * @return the cid
	 */
	public int getCid() {
	
		return cid;
	}
	
	/**
	 * @param cid
	 *            the cid to set
	 */
	public void setCid(int cid) {
	
		this.cid = cid;
	}

	/**
	 * @return the etype
	 */
	public int getEtype() {
	
		return etype;
	}

	/**
	 * @param etype
	 *            the etype to set
	 */
	public void setEtype(int etype) {
	
		this.etype = etype;
	}

	/**
	 * @return the ename
	 */
	public String getEname() {
	
		return ename;
	}

	/**
	 * @param ename
	 *            the ename to set
	 */
	public void setEname(String ename) {
	
		this.ename = ename;
	}

	/**
	 * @return the total
	 */
	public double getTotal() {

		return total;
	}

	/**
	 * @param total
	 *            the total to set
	 */
	public void setTotal(double total) {

		this.total = total;
	}

	/**
	 * @return the etime
	 */
	public long getEtime() {
	
		return etime;
	}

	/**
	 * @param etime
	 *            the etime to set
	 */
	public void setEtime(long etime) {
	
		this.etime = etime;
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
	
		return "Exam [eid=" + eid + ", cid=" + cid + ", etype=" + etype
		        + ", ename=" + ename + ", total=" + total + ", etime=" + etime
		        + ", state=" + state + "]";
	}

}