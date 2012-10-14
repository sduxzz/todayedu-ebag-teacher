/*
 * ExaminationSystem-Tea com.todayedu.ebag.teacher.Network
 * 2012 2012-10-14 ÏÂÎç1:01:21
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.Network;

import java.util.ArrayList;
import java.util.List;

import org.apache.mina.core.session.IoSession;
import org.ebag.net.request.AnswerRequest;
import org.ebag.net.response.AnswerResponse;

import android.app.Activity;
import android.util.Log;

import com.todayedu.ebag.teacher.Parameters;
import com.todayedu.ebag.teacher.Parameters.ParaIndex;


/**
 * @author <a href="zhenzxie.iteye.cn">zhenzxie</a>
 * @version 1.0
 * @since 1.0
 */
public class StudentAnswerHandler extends BaseNetworkHandler {
	
	/**
	 * @param activity
	 * @param callBack
	 */
	public StudentAnswerHandler(Activity activity, NetworkCallBack callBack) {
	
		super(activity, callBack);
	}
	
	@Override
	public void sessionOpened(IoSession session) throws Exception {
	
		AnswerRequest request = new AnswerRequest();
		request.setUid(Parameters.get(ParaIndex.SID_INDEX));
		request.examId = Parameters.get(ParaIndex.EID_INDEX);
		List<Integer> idList = new ArrayList<Integer>();
		idList.add(Parameters.get(ParaIndex.PID_INDEX));
		request.setIdList(idList);
		List<String> fieldList = new ArrayList<String>();
		fieldList.add("picOfTeacherUrl");
		fieldList.add("textAnswer");
		request.setFieldList(fieldList);
		session.write(request);
		Log.i(TAG, "sessionOpened " + request.toString());
	}
	
	@Override
	public void messageReceived(IoSession session, Object message)
	        throws Exception {
	
		if (message instanceof AnswerResponse) {
			networkCallBack.success(message);
		} else {
			networkCallBack
			        .failed(new Throwable("the return isn't right type"));
		}
	}
}
