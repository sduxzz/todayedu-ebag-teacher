/*
 * ExaminationSystem-Tea com.todayedu.ebag.teacher.Network ExamAdapter.java
 * 2012 2012-7-13 ÏÂÎç7:29:48
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.Network;

import org.apache.mina.core.session.IoSession;
import org.ebag.net.obj.I.signal;
import org.ebag.net.request.LoginRequest;
import org.ebag.net.response.LoginResponse;

import android.content.Context;
import android.util.Log;


/**
 * @author zhenzxie
 *
 */
public class LoginHandler extends BaseNetworkHandler {
	
	public interface LoginCallBack {
		
		public void loginSuccess(LoginResponse response);
		
		public void loginError(LoginResponse response, Throwable cause);
	}

	public String uname;
	public String upwd;
	public LoginCallBack callBack;
	
	public LoginHandler(Context context, String uname, String upwd,
	        LoginCallBack callBack) {
	
		super(context);
		this.uname = uname;
		this.upwd = upwd;
		this.callBack = callBack;
	}

	/**
	 * @see org.apache.mina.core.service.IoHandler#sessionOpened(org.apache.mina.core.session.IoSession)
	 */
	@Override
	public void sessionOpened(IoSession session) throws Exception {
	
		LoginRequest request = new LoginRequest();
		request.setUid(1);
		request.setUname(uname);
		request.setUpwd(upwd);
		session.write(request);
		Log.i(TAG, "sessionOpened:" + uname + "   " + upwd);
	}
	
	/**
	 * @see org.apache.mina.core.service.IoHandler#exceptionCaught(org.apache.mina.core.session.IoSession, java.lang.Throwable)
	 */
	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
	
		Log.i(TAG, "exceptionCaught");
		super.exceptionCaught(session, cause);
		callBack.loginError(null, cause);
	}
	
	/**
	 * @see org.apache.mina.core.service.IoHandler#messageReceived(org.apache.mina.core.session.IoSession,
	 *      java.lang.Object)
	 */
	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
	
		Log.i(TAG, "messageReceived");
		if (message instanceof LoginResponse) {
			LoginResponse response = (LoginResponse) message;
			if (response.result == signal.login_true) {
				callBack.loginSuccess(response);
			} else {
				callBack.loginError(response, null);
			}
		}
	}
	
	/**
	 * @see org.apache.mina.core.service.IoHandler#messageSent(org.apache.mina.core.session.IoSession, java.lang.Object)
	 */
	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
	
		Log.i(TAG, "messageSent");
	}
	
}
