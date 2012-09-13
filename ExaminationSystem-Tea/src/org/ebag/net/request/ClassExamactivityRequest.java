package org.ebag.net.request;

import java.io.Serializable;

public class ClassExamactivityRequest implements Serializable{
	
	public int classId;
	public int examId;
	
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}
	public int getExamId() {
		return examId;
	}
	public void setExamId(int examId) {
		this.examId = examId;
	}

}
