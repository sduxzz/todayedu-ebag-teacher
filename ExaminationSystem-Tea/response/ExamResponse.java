package org.ebag.net.response;

import java.io.Serializable;
import java.util.List;

import org.ebag.net.obj.exam.ExamObj;

public class ExamResponse implements Serializable{

	private static final long serialVersionUID = -9190778011163752268L;
	
	public List<ExamObj> examList;

	public List<ExamObj> getExamList() {
		return examList;
	}

	public void setExamList(List<ExamObj> examList) {
		this.examList = examList;
	}
}
