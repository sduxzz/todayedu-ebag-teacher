/*
 * ExaminationSystem-Tea com.todayedu.ebag.teacher.OBJModule.DBObj
 * 2012 2012-7-3 涓嬪崍4:23:11
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.DataSource.DataObj;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.ebag.net.response.ClassInfoResponse;

import android.content.Context;

import com.todayedu.ebag.teacher.Parameters;
import com.todayedu.ebag.teacher.Parameters.ParaIndex;
import com.todayedu.ebag.teacher.DataSource.Data;
import com.todayedu.ebag.teacher.Database.annotation.Column;
import com.todayedu.ebag.teacher.Database.annotation.Id;
import com.todayedu.ebag.teacher.Database.annotation.Table;

/**
 * 学生实体
 * 
 * @author zhenzxie
 * 
 */
@Table(name = "STUDENT")
public class Student extends Data {
	
	@Id
	@Column(name = "sid")
	private int sid;
	@Column(name = "cid")
	private int cid;
	@Column(name = "sname")
	private String sname;
	
	public Student() {

	}

	public Student(int sid, int classid, String sname) {

		this.sid = sid;
		this.cid = classid;
		this.sname = sname;
	}
	
	@Override
	public boolean save(Context context) {
	
		return false;
	
	}

	@Override
	public Map<String, String> changeToMap(String[] keys) {
	
		return null;
	}
	
	public static List<Data> parse(ClassInfoResponse response){
		
		List<Integer> studentIdList = response.getStudentIdList();
		List<Data> list = new ArrayList<Data>();
		Student stu;
		
		int classid = Parameters.get(ParaIndex.CID_INDEX);
		for (Integer in : studentIdList) {
			stu = new Student();
			stu.cid = classid;
			stu.sid = in;
			list.add(stu);
		}
		
		return list;
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
	 * @return the classid
	 */
	public int getClassid() {
	
		return cid;
	}
	
	/**
	 * @param classid
	 *            the classid to set
	 */
	public void setClassid(int classid) {
	
		this.cid = classid;
	}
	
	/**
	 * @return the sname
	 */
	public String getSname() {
	
		return sname;
	}
	
	/**
	 * @param sname
	 *            the sname to set
	 */
	public void setSname(String sname) {
	
		this.sname = sname;
	}
	
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
	
		return "StudentObj [sid=" + sid + ", classid=" + cid + ", sname="
				+ sname + "]";
	}
}
