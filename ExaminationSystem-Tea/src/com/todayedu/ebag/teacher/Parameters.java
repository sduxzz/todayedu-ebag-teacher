/*
 * ExaminationSystem-Tea com.todayedu.ebag.teacher
 * 2012 2012-7-4 ����9:38:11
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher;



/**
 * The stack that is used in delivering parameters. You must be careful when you
 * use it.
 * 
 * @author zhenzxie
 * 
 */
public class Parameters {
	
	public static class ParaIndex {
		
		public static final int CID_INDEX = 0;
		public static final int EID_INDEX = 1;
		public static final int SID_INDEX = 2;
		public static final int PID_INDEX = 3;
		public static final int EXAMSTATE_INDEX = 4;
		public static final int PROBLEMSTATE_INDEX = 5;
		public static final int PROBLEMNUMBER_INDEX = 6;
	}

	private static int[] parameters = new int[10];
	private static int pointer = 0;
	
	public static synchronized void add(int parameter, int index) {
	
		parameters[index] = parameter;
	}
	
	public static synchronized void add(String parameter, int index) {
	
		parameters[index] = Integer.parseInt(parameter);
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
	
		for (; pointer >= 0; pointer--)
			parameters[pointer] = 0;
	}

}
