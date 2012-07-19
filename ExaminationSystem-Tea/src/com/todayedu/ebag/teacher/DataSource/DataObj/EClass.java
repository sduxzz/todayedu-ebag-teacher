/*
 * ExaminationSystem-Tea com.todayedu.ebag.teacher.OBJModule.DBObj ClassObj.java
 * 2012 2012-7-6 ‰∏ãÂçà2:41:00
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.DataSource.DataObj;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.ebag.net.response.ClassInfoResponse;
import org.ebag.net.response.LoginResponse;

import android.content.Context;

import com.todayedu.ebag.teacher.Parameters;
import com.todayedu.ebag.teacher.Parameters.ParaIndex;
import com.todayedu.ebag.teacher.DataSource.Data;
import com.todayedu.ebag.teacher.Database.annotation.Column;
import com.todayedu.ebag.teacher.Database.annotation.Id;
import com.todayedu.ebag.teacher.Database.annotation.Table;

/**
 * the concrete class stand for the data come from the data base table
 * ECLASS(cid integer,cname text) ∞‡º∂ µÃÂ
 * 
 * @author zhenzxie
 * 
 */
@Table(name = "ECLASS")
public class EClass extends Data {
	
	@Id
	@Column(name = "cid")
	private int cid;
	@Column(name = "cname")
	private String cname;
	
	public EClass() {
	
	}
	
	/**
	 * 
	 * @param cid
	 * @param cname
	 */
	public EClass(int cid, String cname) {
	
		this.cid = cid;
		this.cname = cname;
	}
	
	@Override
	public void save(Context context) {
	
	}
	
	@Override
	public Map<String, String> changeToMap(String[] keys) {
	
		return null;
	}

	public static List<Data> parse(LoginResponse response) {
	
		List<Integer> classIdLst = response.getClassIdLst();
		List<Data> list = new ArrayList<Data>();
		EClass ec;
		
		for (Integer in : classIdLst) {
			ec = new EClass();
			ec.setCid(in);
			list.add(ec);
		}
		
		return list;
	}
	
	public static List<Data> parse(ClassInfoResponse response) {
	
		String className = response.className;
		EClass ec = new EClass(Parameters.get(ParaIndex.CID_INDEX), className);
		List<Data> list = new ArrayList<Data>();
		list.add(ec);
		return list;
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
	 * @return the cname
	 */
	public String getCname() {
	
		return cname;
	}
	
	/**
	 * @param cname
	 *            the cname to set
	 */
	public void setCname(String cname) {
	
		this.cname = cname;
	}
	
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
	
		return "ClassObj [cid=" + cid + ", cname=" + cname + "]";
	}
}