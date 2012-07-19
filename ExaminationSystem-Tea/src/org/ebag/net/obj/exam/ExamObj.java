package org.ebag.net.obj.exam;

import java.io.Serializable;
import java.util.List;

public class ExamObj implements Serializable{

	private static final long serialVersionUID = -1555257068727844609L;

	public int id;
	public int type;
	public String name;
	public long time;
	
	public List<ProblemInfoObj> pInfoList;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public List<ProblemInfoObj> getpInfoList() {
		return pInfoList;
	}

	public void setpInfoList(List<ProblemInfoObj> pInfoList) {
		this.pInfoList = pInfoList;
	}
	
}
