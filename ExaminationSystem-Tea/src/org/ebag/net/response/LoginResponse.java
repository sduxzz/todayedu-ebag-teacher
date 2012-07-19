package org.ebag.net.response;

import java.io.Serializable;
import java.util.List;

public class LoginResponse implements Serializable{

	private static final long serialVersionUID = 4051818634968197592L;
	public int result;
	public int userType;
	public List<Integer> classIdLst;
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	public List<Integer> getClassIdLst() {
		return classIdLst;
	}
	public void setClassIdLst(List<Integer> classIdLst) {
		this.classIdLst = classIdLst;
	}
}
