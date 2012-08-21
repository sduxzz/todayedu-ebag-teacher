package com.todayedu.ebag.teacher.Network;


/**
 * 
 * @author zhenzxie
 * 
 */
public interface NetworkCallBack {
	
	public abstract void success(Object response);
	
	public abstract void failed(Throwable throwable);
}
