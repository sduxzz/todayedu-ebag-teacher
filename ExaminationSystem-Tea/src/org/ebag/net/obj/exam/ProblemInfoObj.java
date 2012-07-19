package org.ebag.net.obj.exam;

import java.io.Serializable;

public class ProblemInfoObj implements Serializable{

	private static final long serialVersionUID = 3734497378093120422L;

	public int id;
	public int type;
	public double point;
	public String answer;
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
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
	public double getPoint() {
		return point;
	}
	public void setPoint(double point) {
		this.point = point;
	}
	
	
}
