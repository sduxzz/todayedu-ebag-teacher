package org.ebag.net.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ebag.pojo.Examactivity;
import ebag.pojo.ExamactivityId;

public class ClassExamactivityResponse implements Serializable{

	public List<Examactivity> lst;

	public List<Examactivity> getLst() {
		return lst;
	}

	public void setLst(List<Examactivity> list) {
		this.lst = list;
	}
	
}
