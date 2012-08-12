package org.ebag.net.response;

import java.io.Serializable;
import java.util.List;

import ebag.pojo.Examactivity;

public class ExamActivityResponse implements Serializable{

	public List<Examactivity> res;

	public List<Examactivity> getRes() {
		return res;
	}

	public void setRes(List<Examactivity> res) {
		this.res = res;
	}
}
