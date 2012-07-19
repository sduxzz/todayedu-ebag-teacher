/*
 * ExaminationSystem-Tea com.todayedu.ebag.teacher.DataSource.DBObj CE.java
 * 2012 2012-7-17 上午10:53:29
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.DataSource.DataObj;

import android.content.Context;

import com.todayedu.ebag.teacher.DataSource.Data;
import com.todayedu.ebag.teacher.Database.annotation.Column;
import com.todayedu.ebag.teacher.Database.annotation.Table;


/**
 * the concrete class stand for the data which come from the database table
 * CE(eid integer,cid integer)班级和试卷的联系，表达语义：某个班有做某套试卷
 * 
 * @author zhenzxie
 * 
 */
@Table(name = "CE")
public class CE extends Data {
	
	@Column(name = "cid", foreignKey = "ECLASS(cid)")
	private int cid;
	@Column(name = "eid", foreignKey = "EXAM(eid)")
	private int eid;
	
	/**
	 * 
	 */
	public CE() {
	
	}
	
	/**
	 * @param cid
	 * @param eid
	 */
	public CE(int cid, int eid) {
	
		this.cid = cid;
		this.eid = eid;
	}

	/**
	 * @see com.todayedu.ebag.teacher.DataSource.Data#save(android.content.Context)
	 */
	@Override
	public void save(Context context) {
	
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
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
	
		return "CE [cid=" + cid + ", eid=" + eid + "]";
	}
	
}
