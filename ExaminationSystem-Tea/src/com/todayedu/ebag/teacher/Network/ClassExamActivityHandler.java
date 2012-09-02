/*
 * ExaminationSystem-Tea com.todayedu.ebag.teacher.Network
 * 2012 2012-8-21 ÏÂÎç4:47:30
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.Network;

import org.apache.mina.core.session.IoSession;
import org.ebag.net.request.ClassExamactivityRequest;
import org.ebag.net.response.ClassExamactivityResponse;

import android.content.Context;
import android.util.Log;

/**
 * @author <a href="zhenzxie.iteye.cn">zhenzxie</a>
 * @version 1.0
 * @since 1.0
 */
public class ClassExamActivityHandler extends BaseNetworkHandler {
	
	private int cid;
	private int eid;
	
	public ClassExamActivityHandler(Context context, NetworkCallBack callBack,
	        int cid, int eid) {
	
		super(context, callBack);
		this.cid = cid;
		this.eid = eid;
	}
	
	/**
	 * @see com.todayedu.ebag.teacher.Network.BaseNetworkHandler#sessionOpened(org.apache.mina.core.session.IoSession)
	 */
	@Override
	public void sessionOpened(IoSession arg0) throws Exception {
	
		super.sessionOpened(arg0);
		ClassExamactivityRequest request = new ClassExamactivityRequest();
		request.setClassId(cid);
		request.setExamId(eid);
		arg0.write(request);
		Log.i(TAG, "sessionOpened " + request.toString());
	}
	
	/**
	 * @see com.todayedu.ebag.teacher.Network.BaseNetworkHandler#messageReceived(org.apache.mina.core.session.IoSession,
	 *      java.lang.Object)
	 */
	@Override
	public void messageReceived(IoSession session, Object message)
	        throws Exception {
	
		if (message instanceof ClassExamactivityResponse) {
			networkCallBack.success(message);
		} else {
			networkCallBack
			        .failed(new Throwable("The return isn't right type."));
		}
		super.messageReceived(session, message);
	}
}
