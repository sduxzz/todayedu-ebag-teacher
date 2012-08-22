/*
 * ExaminationSystem-Tea com.todayedu.examinationsystem.teacher
 * 2012 2012-5-27 锟斤拷锟斤拷1:51:26
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher;

/**
 * contain some useful constants
 * 
 * @author zhenzxie
 * 
 */
public interface Constants {
	
	public static class State {
		
		public static final int HANDINED = 10010;
		public static final int CORRECTED = 10020;
	}

	public static class StateStr {
		
		public static final String COMMENT = "等待讲评";
		public static final String COMMENTED = "已讲评";
		public static final String CORRECT = "未批改";
		public static final String CORRECTED = "已批改";
		public static final String HANDIN = "未提交";
		public static final String HANDINED = "已提交";
	}
}
