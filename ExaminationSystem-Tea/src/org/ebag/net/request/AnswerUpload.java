package org.ebag.net.request;

import java.io.Serializable;
import java.util.ArrayList;

import org.ebag.net.obj.answer.AnswerObj;

public class AnswerUpload implements Serializable{

	public ArrayList<AnswerObj> ansList;

	public ArrayList<AnswerObj> getAnsList() {
		return ansList;
	}
	public void setAnsList(ArrayList<AnswerObj> ansList) {
		this.ansList = ansList;
	}
	private static final long serialVersionUID = 6292923140336431884L;
}
