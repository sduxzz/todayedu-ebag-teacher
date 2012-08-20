package org.ebag.net.response;

import java.io.Serializable;
import java.util.List;

import org.ebag.net.obj.answer.AnswerObj;

public class AnswerResponse implements Serializable{

	private static final long serialVersionUID = -4248183493657350575L;
	public List<AnswerObj> examList;
	public List<AnswerObj> getExamList() {
		return examList;
	}
	public void setExamList(List<AnswerObj> examList) {
		this.examList = examList;
	}
}
