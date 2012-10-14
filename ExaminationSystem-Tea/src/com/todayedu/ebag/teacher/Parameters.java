/*
 * ExaminationSystem-Tea com.todayedu.ebag.teacher
 * 2012 2012-7-4 锟斤拷锟斤拷9:38:11
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher;

import java.util.ArrayList;

import org.ebag.net.obj.answer.GradeDetail;
import org.ebag.net.obj.exam.ExamObj;

/**
 * The stack that is used in delivering parameters. You must be careful when you
 * use it.
 * 
 * @author zhenzxie
 * 
 */
public class Parameters {
	
	public static class ParaIndex {
		
		public static final int UID_INDEX = 0;
		public static final int CID_INDEX = 1;
		public static final int EID_INDEX = 2;
		public static final int SID_INDEX = 3;
		public static final int PID_INDEX = 4;
		public static final int ESTATE_INDEX = 5;
	}

	private static int[] parameters = new int[6];
	private static String className;
	private static ExamObj examObj;
	// key为题目id,value为本题目所有学生得分情况
	public static ArrayList<GradeDetail> detailMap;
	
	public static synchronized void add(int parameter, int index) {
	
		parameters[index] = parameter;
	}
	
	public static synchronized void add(String parameter, int index) {
	
		try {
			parameters[index] = Integer.parseInt(parameter);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}

	public static synchronized int get(int index) {
	
		return parameters[index];
	}
	
	public static synchronized String getStr(int index) {
	
		return String.valueOf(parameters[index]);
	}

	public static synchronized void clean(int index) {

		parameters[index] = 0;
	}
	
	public static synchronized void clean() {

		for (int pointer = 0; pointer < 10; pointer++)
			parameters[pointer] = 0;
	}
	
	/**
	 * @return the className
	 */
	public static String getClassName() {
	
		return className;
	}
	
	/**
	 * @param className
	 *            the className to set
	 */
	public static void setClassName(String className) {
	
		Parameters.className = className;
	}

	/**
	 * @return the examObj
	 */
	public static ExamObj getExamObj() {
	
		return examObj;
	}
	
    /**
	 * @param examObj
	 *            the examObj to set
	 */
	public static void setExamObj(ExamObj examObj) {
	
		Parameters.examObj = examObj;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
	
		return "Parameters[ uid:" + parameters[ParaIndex.UID_INDEX] + " cid:"
		        + parameters[ParaIndex.CID_INDEX] + " eid:"
		        + parameters[ParaIndex.EID_INDEX] + " sid:"
		        + parameters[ParaIndex.SID_INDEX] + " pid:"
		        + parameters[ParaIndex.PID_INDEX] + " exam state:"
		        + parameters[ParaIndex.ESTATE_INDEX] + "class name:"
		        + className + "exam:" + examObj;
	}
}