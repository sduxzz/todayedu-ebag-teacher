package org.ebag.net.response;

import java.io.Serializable;
import java.util.Set;

import ebag.pojo.Eclass;
import ebag.pojo.Euser;

public class LoginResponse implements Serializable{

	private static final long serialVersionUID = 4051818634968197592L;
	public int result;
	public Euser user;
	public Set<Eclass> classSet;

	public Euser getUser() {
		return user;
	}
	public void setUser(Euser user) {
		this.user = user;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public Set<Eclass> getClassSet() {
		return classSet;
	}
	public void setClassSet(Set<Eclass> classSet) {
		this.classSet = classSet;
	}

	
}
