/*
 * ExaminationSystem-Tea com.todayedu.examinationsystem.teacher
 * 2012 2012-5-27 ����1:51:26
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
		
		public static final String COMMENT = "�ȴ�����";
		public static final String COMMENTED = "�ѽ���";
		public static final String CORRECT = "δ����";
		public static final String CORRECTED = "������";
		public static final String HANDIN = "δ�ύ";
		public static final String HANDINED = "���ύ";
	}
}
