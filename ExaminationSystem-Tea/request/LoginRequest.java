package org.ebag.net.request;

import java.io.Serializable;

public class LoginRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	//use uid or uname login
	public int uid;
	public String uname;
	public String upwd;
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUpwd() {
		return upwd;
	}
	public void setUpwd(String upwd) {
		this.upwd = upwd;
	}
	
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
	
		return "LoginRequest [uid=" + uid + ", uname=" + uname + ", upwd="
		        + upwd + "]";
	}
}
