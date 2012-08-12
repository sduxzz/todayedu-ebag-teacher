package org.ebag.net.request;

import java.io.Serializable;
import java.util.List;

public class UserInfoRequest implements Serializable{

	private static final long serialVersionUID = -1497845214413772787L;

	public List<Integer> idList;

	public List<Integer> getIdList() {
		return idList;
	}

	public void setIdList(List<Integer> idList) {
		this.idList = idList;
	}
}
