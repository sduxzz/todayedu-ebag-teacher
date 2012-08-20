package org.ebag.net.response;

import java.io.Serializable;
import java.util.List;

import ebag.pojo.Euser;

public class UserInfoResponse implements Serializable{

	public List<Euser> uList;

	public List<Euser> getuList() {
		return uList;
	}

	public void setuList(List<Euser> uList) {
		this.uList = uList;
	}
	

}
