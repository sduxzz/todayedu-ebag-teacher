package org.ebag.net.request;

import java.io.Serializable;

public class StartExamRequest implements Serializable {
	
	private static final long serialVersionUID = 101746534953323176L;
	public int examId;
	public int aimClassId;

	public int getExamId() {
		return examId;
	}

	public void setExamId(int examId) {
		this.examId = examId;
	}

	public int getAimClassId() {
		return aimClassId;
	}

	public void setAimClassId(int aimClassId) {
		this.aimClassId = aimClassId;
	}

}
