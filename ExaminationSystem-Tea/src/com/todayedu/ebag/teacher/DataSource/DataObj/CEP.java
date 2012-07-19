/*
 * ExaminationSystem-Tea com.todayedu.ebg.teacher.OBJModule.DBObj
 * 2012 2012-7-2 锟斤拷锟斤拷10:06:44
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.DataSource.DataObj;

import java.util.ArrayList;
import java.util.List;

import org.ebag.net.obj.exam.ExamObj;
import org.ebag.net.obj.exam.ProblemInfoObj;
import org.ebag.net.response.ExamResponse;

import android.content.Context;

import com.todayedu.ebag.teacher.Parameters;
import com.todayedu.ebag.teacher.Parameters.ParaIndex;
import com.todayedu.ebag.teacher.DataSource.Data;
import com.todayedu.ebag.teacher.Database.annotation.Column;
import com.todayedu.ebag.teacher.Database.annotation.Table;

/**
 * the concrete class stand for the data which come from the database table
 * CEP(eid integer,cid integer...) 班级，试卷，问题的联系，表达的语义：某个班级里的某套试卷中的某道题
 * 
 * @author zhenzxie
 * 
 */
@Table(name = "CEP")
public class CEP extends Data {
	
	@Column(name = "cid",foreignKey="ECLASS(cid)")
	private int cid;
	@Column(name = "eid",foreignKey="EXAM(eid)")
	private int eid;
	@Column(name = "pid", foreignKey = "PROBLEM(eid)")
	private int pid;
	@Column(name = "number")
	private int number;
	@Column(name = "state")
	private int state;/* 题的状态：是否讲评 */
	@Column(name = "rightpercent")
	private double rightpercent;
	
	public CEP() {

	}

	
	/**
	 * @param cid
	 * @param eid
	 * @param pid
	 * @param number
	 * @param state
	 * @param rightpercent
	 */
	public CEP(int cid, int eid, int pid, int number, int state,
			double rightpercent) {
	
		this.cid = cid;
		this.eid = eid;
		this.pid = pid;
		this.number = number;
		this.state = state;
		this.rightpercent = rightpercent;
	}
	
	public static List<Data> parse(ExamResponse response, int position) {
	
		List<ExamObj> examObjList = response.getExamList();
		if (position >= examObjList.size() || position < 0)
			return null;
		ExamObj obj = examObjList.get(position);
		return parseToCEP(obj);
	}
	
	public static List<Data> parseToCEP(ExamObj obj) {
	
		List<ProblemInfoObj> infoObjList = obj.pInfoList;
		List<Data> list = new ArrayList<Data>();
		int cId = Parameters.get(ParaIndex.CID_INDEX);
		int eId = Parameters.get(ParaIndex.EID_INDEX);
		
		CEP cep;
		int index = 0;
		for (ProblemInfoObj info : infoObjList) {
			cep = new CEP();
			cep.cid = cId;
			cep.eid = eId;
			cep.number = index++;
			cep.pid = info.getId();
			cep.state = 0;// TODO:set the state of CEP
			list.add(cep);
		}

		return list;
	}


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
	 * @return the rightpercent
	 */
	public double getRightpercent() {
	
		return rightpercent;
	}

	/**
	 * @param rightpercent
	 *            the rightpercent to set
	 */
	public void setRightpercent(double rightpercent) {
	
		this.rightpercent = rightpercent;
	}


	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
	
		return "CEP [cid=" + cid + ", eid=" + eid + ", pid=" + pid
				+ ", number=" + number + ", state=" + state + ", rightpercent="
				+ rightpercent + "]";
	}
}