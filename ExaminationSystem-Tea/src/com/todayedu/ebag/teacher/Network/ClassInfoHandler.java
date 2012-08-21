/*
 * ExaminationSystem-Tea com.todayedu.ebag.teacher.Network ExamAdapter.java
 * 2012 2012-7-13 ÏÂÎç7:29:48
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.Network;

import org.apache.mina.core.session.IoSession;
import org.ebag.net.request.ClassInfoRequest;
import org.ebag.net.response.ClassInfoResponse;

import android.content.Context;


/**
 * @author zhenzxie
 *
 */
public class ClassInfoHandler extends BaseNetworkHandler {
	
	public int classId;
	
	
	/**
	 * @param context
	 * @param dataSource
	 * @param classId
	 */
	public ClassInfoHandler(Context context, NetworkCallBack callBack,
	        int classId) {
	
		super(context, callBack);
		this.classId = classId;
	}
	
	/**
	 * @see org.apache.mina.core.service.IoHandler#sessionOpened(org.apache.mina.core.session.IoSession)
	 */
	@Override
	public void sessionOpened(IoSession session) throws Exception {
	
		super.sessionOpened(session);
		ClassInfoRequest request = new ClassInfoRequest();
		request.classId = classId;
		session.write(request);
	}
	
	/**
	 * @see org.apache.mina.core.service.IoHandler#messageReceived(org.apache.mina.core.session.IoSession, java.lang.Object)
	 */
	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
	
		if (message instanceof ClassInfoResponse) {
			networkCallBack.success(message);
		} else {
			networkCallBack
			        .failed(new Throwable("The return isn't right type."));
		}
		super.messageReceived(session, message);
	}
}
