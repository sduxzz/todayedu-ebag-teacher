/*
 * ExaminationSystem-Tea com.todayedu.ebag.teacher.Network ExamAdapter.java
 * 2012 2012-7-13 ÏÂÎç7:29:48
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.Network;

import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.ebag.net.request.ClassInfoRequest;
import org.ebag.net.response.ClassInfoResponse;

import android.content.Context;

import com.todayedu.ebag.teacher.DataSource.DataSource;


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
	public ClassInfoHandler(Context context, DataSource dataSource, int classId) {
	
		super(context, dataSource);
		this.classId = classId;
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
	
		ClassInfoRequest request = new ClassInfoRequest();
		request.classId = classId;
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
	 * @see org.apache.mina.core.service.IoHandler#messageReceived(org.apache.mina.core.session.IoSession, java.lang.Object)
	 */
	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
	
		if (message instanceof ClassInfoResponse) {
			
		}
	}
	
	/**
	 * @see org.apache.mina.core.service.IoHandler#messageSent(org.apache.mina.core.session.IoSession, java.lang.Object)
	 */
	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
	
	}
	
}
