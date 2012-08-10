/*
 * ExaminationSystem-Tea com.todayedu.ebag.teacher.OBJModule.DBObj ClassObj.java
 * 2012 2012-7-6 ‰∏ãÂçà2:41:00
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.DataSource.DataObj;

import java.io.Serializable;

import android.content.Context;

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
public class EClass extends Data implements Serializable {
	
	/**
     * 
     */
	private static final long serialVersionUID = -5608430130436462422L;
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
	public boolean save(Context context) {
	
		return false;
	
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
	
		return "EClass [cid=" + cid + ", cname=" + cname + "]";
	}
}