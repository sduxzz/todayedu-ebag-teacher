/*
 * ExaminationSystem-Tea com.todayedu.ebag.teacher.Network
 * 2012 2012-9-2 ÏÂÎç10:20:53
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.Network;

import org.apache.mina.core.session.IoSession;
import org.ebag.net.request.AnswerAnalysisRequest;
import org.ebag.net.response.AnswerAnalysisResponse;

import android.app.Activity;
import android.util.Log;


/**
 * @author <a href="zhenzxie.iteye.cn">zhenzxie</a>
 * @version 1.0
 * @since 1.0
 */
public class AnswerAnalysisHandler extends BaseNetworkHandler {
	
	private int cid;
	private int eid;

	/**
	 * @param context
	 * @param callBack
	 */
	public AnswerAnalysisHandler(Activity context, NetworkCallBack callBack,
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
		AnswerAnalysisRequest request = new AnswerAnalysisRequest();
		request.classId = cid;
		request.examId = eid;
		arg0.write(request);
		Log.i(TAG, "sessionOpened " + request);
	}
	
	/**
	 * @see com.todayedu.ebag.teacher.Network.BaseNetworkHandler#messageReceived(org.apache.mina.core.session.IoSession,
	 *      java.lang.Object)
	 */
	@Override
	public void messageReceived(IoSession arg0, Object arg1) throws Exception {
	
		if (arg1 instanceof AnswerAnalysisResponse) {
			networkCallBack.success(arg1);
		} else {
			networkCallBack
			        .failed(new Throwable("The return isn't right type."));
		}
		super.messageReceived(arg0, arg1);
	}
}
