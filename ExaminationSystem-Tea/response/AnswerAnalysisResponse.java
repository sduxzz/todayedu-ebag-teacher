package org.ebag.net.response;

import java.io.Serializable;

import org.ebag.net.obj.answer.AnswerAnalysis;

public class AnswerAnalysisResponse implements Serializable{

	private static final long serialVersionUID = 1L;

	public AnswerAnalysis res=null;

	public AnswerAnalysis getRes() {
		return res;
	}

	public void setRes(AnswerAnalysis res) {
		this.res = res;
	}
	
}
