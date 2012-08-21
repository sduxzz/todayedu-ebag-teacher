/*
 * ExaminationSystem-Tea com.todayedu.ebag.teacher
 * 2012 2012-7-4 ����9:38:11
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher;

import com.todayedu.ebag.teacher.DataSource.DataObj.Exam;

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
		public static final int EXAMSTATE_INDEX = 5;
		public static final int PROBLEMSTATE_INDEX = 6;
		public static final int PROBLEMNUMBER_INDEX = 7;
		public static final int EXAMSHOWACTIVITYMODE_INDEX = 8;
	}

	private static int[] parameters = new int[10];
	private static String className;
	private static Exam exam;
	
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
     * @return the exam
     */
    public static Exam getExam() {
    
	    return exam;
    }

	/**
     * @param exam the exam to set
     */
    public static void setExam(Exam exam) {
    
	    Parameters.exam = exam;
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
		        + parameters[ParaIndex.EXAMSTATE_INDEX] + "problem state:"
		        + parameters[ParaIndex.PROBLEMSTATE_INDEX] + "problem number:"
		        + parameters[ParaIndex.PROBLEMNUMBER_INDEX]
		        + "exam show acitivity mode:"
		        + parameters[ParaIndex.EXAMSHOWACTIVITYMODE_INDEX]
		        + "class name:" + className + "exam:" + exam;
	}
}