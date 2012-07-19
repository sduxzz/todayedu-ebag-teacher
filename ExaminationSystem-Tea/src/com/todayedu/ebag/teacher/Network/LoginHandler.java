/*
 * ExaminationSystem-Tea com.todayedu.ebag.teacher.Network ExamAdapter.java
 * 2012 2012-7-13 ÏÂÎç7:29:48
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.Network;

import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.ebag.net.request.LoginRequest;
import org.ebag.net.response.LoginResponse;

import android.content.Context;
import android.util.Log;

import com.todayedu.ebag.teacher.DataSource.DataSource;


/**
 * @author zhenzxie
 *
 */
public class LoginHandler extends BaseNetworkHandler {
	
	public int uid;
	public String uname;
	public String upwd;
	

	/**
	 * @param context
	 * @param dataSource
	 * @param uid
	 */
	public LoginHandler(Context context, DataSource dataSource, int uid) {
	
		super(context, dataSource);
		this.uid = uid;
	}

	/**
	 * @param context
	 * @param dataSource
	 * @param uname
	 * @param upwd
	 */
	public LoginHandler(Context context, DataSource dataSource, String uname,
			String upwd) {
	
		super(context, dataSource);
		this.uname = uname;
		this.upwd = upwd;
	}

	/**
	 * @param context
	 * @param dataSource
	 * @param uid
	 * @param uname
	 * @param upwd
	 */
	public LoginHandler(Context context, DataSource dataSource, int uid,
			String uname, String upwd) {
	
		super(context, dataSource);
		this.uid = uid;
		this.uname = uname;
		this.upwd = upwd;
	}

	/**
	 * @see org.apache.mina.core.service.IoHandler#sessionCreated(org.apache.mina.core.session.IoSession)
	 */
	@Override
	public void sessionCreated(IoSession session) throws Exception {

	}
	
	/**
	 * @see org.apache.mina.core.service.IoHandler#sessionOpened(org.apache.mina.core.session.IoSession)
	 */
	@Override
	public void sessionOpened(IoSession session) throws Exception {
	
		Log.i(TAG, "current thread's id:" + Thread.currentThread().getId());
		LoginRequest request = new LoginRequest();
		request.setUid(uid);
		request.setUname(uname);
		request.setUpwd(upwd);
		session.write(request);
	}
	
	/**
	 * @see org.apache.mina.core.service.IoHandler#sessionClosed(org.apache.mina.core.session.IoSession)
	 */
	@Override
	public void sessionClosed(IoSession session) throws Exception {
	
	}
	
	/**
	 * @see org.apache.mina.core.service.IoHandler#sessionIdle(org.apache.mina.core.session.IoSession, org.apache.mina.core.session.IdleStatus)
	 */
	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
	
	}
	
	/**
	 * @see org.apache.mina.core.service.IoHandler#exceptionCaught(org.apache.mina.core.session.IoSession, java.lang.Throwable)
	 */
	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
	
	}
	
	/**
	 * @see org.apache.mina.core.service.IoHandler#messageReceived(org.apache.mina.core.session.IoSession,
	 *      java.lang.Object)
	 */
	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
	
		Log.i(TAG, "current thread's id:" + Thread.currentThread().getId());
		if (message instanceof LoginResponse) {
			// LoginResponse response = (LoginResponse) message;
			// List<Data> list = EClass.parse(response);
			// BaseNetDS bns = new CNetDS(EClass.class);
			// bns.store(list);
			// bns.save(zContext);
		}
	}
	
	/**
	 * @see org.apache.mina.core.service.IoHandler#messageSent(org.apache.mina.core.session.IoSession, java.lang.Object)
	 */
	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
	
	}
	
}
