package org.ebag.net.servermsg;

import java.io.Serializable;

import org.ebag.net.obj.exam.ExamObj;

public class StartExamMessage implements Serializable{

	private static final long serialVersionUID = 5638127270718663026L;

	public ExamObj exam;

	public ExamObj getExam() {
		return exam;
	}

	public void setExam(ExamObj exam) {
		this.exam = exam;
	}
}
