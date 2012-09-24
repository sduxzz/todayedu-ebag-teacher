/*
 * ExaminationSystem-Tea com.todayedu.ebag.teacher.Network
 * 2012 2012-8-21 ÏÂÎç11:02:52
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.Network;

import org.apache.mina.core.session.IoSession;
import org.ebag.net.request.StartExamRequest;
import org.ebag.net.response.StartExamResponse;

import android.app.Activity;

/**
 * @author <a href="zhenzxie.iteye.cn">zhenzxie</a>
 * @version 1.0
 * @since 1.0
 */
public class StartExamHandler extends BaseNetworkHandler {
	
	private int cid;
	private int eid;
	
	public StartExamHandler(Activity context, NetworkCallBack callBack,
	        int cid,
	        int eid) {
	
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
		StartExamRequest request = new StartExamRequest();
		request.aimClassId = cid;
		request.examId = eid;
		arg0.write(request);
	}
	
	@Override
	public void messageReceived(IoSession session, Object message)
	        throws Exception {
	
		if (message instanceof StartExamResponse) {
			networkCallBack.success(message);
		} else {
			networkCallBack
			        .failed(new Throwable("The return isn't right type."));
		}
		super.messageReceived(session, message);
	}

}
