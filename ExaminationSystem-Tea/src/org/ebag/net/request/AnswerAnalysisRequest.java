package org.ebag.net.request;

import java.io.Serializable;

public class AnswerAnalysisRequest implements Serializable{

	private static final long serialVersionUID = 7450920684692682788L;

	public int examId;
	public int classId;
	public int getexamId() {
		return examId;
	}
	public void setexamActivityId(int examId) {
		this.examId = examId;
	}
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}
	
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
	
		return "AnswerAnalysisRequest [examId=" + examId + ", classId="
		        + classId + "]";
	}
}
