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


/**
 * @author zhenzxie
 *
 */
public class LoginHandler extends BaseNetworkHandler {

	public String uname;
	public String upwd;
	public NetworkCallBack networkCallBack;
	
	public LoginHandler(Context context, String uname, String upwd,
	        NetworkCallBack networkCallBack) {
	
		super(context, networkCallBack);
		this.uname = uname;
		this.upwd = upwd;
		this.networkCallBack = networkCallBack;
	}

	/**
	 * @see org.apache.mina.core.service.IoHandler#sessionOpened(org.apache.mina.core.session.IoSession)
	 */
	@Override
	public void sessionOpened(IoSession session) throws Exception {
	
		super.sessionOpened(session);
		LoginRequest request = getRequest();
		session.write(request);
	}
	
	/**
	 * @see org.apache.mina.core.service.IoHandler#messageReceived(org.apache.mina.core.session.IoSession,
	 *      java.lang.Object)
	 */
	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {

		if (message instanceof LoginResponse) {
			LoginResponse response = (LoginResponse) message;
			if (response.result == signal.login_true) {
				networkCallBack.success(response);
			} else {
				networkCallBack.failed(new Throwable(
				        "The return isn't correct type"));
			}
		}
		super.messageReceived(session, message);
	}
	
	public LoginRequest getRequest() {
	
		LoginRequest request = new LoginRequest();
		request.setUid(1);
		request.setUname(uname);
		request.setUpwd(upwd);
		return request;
	}
}
