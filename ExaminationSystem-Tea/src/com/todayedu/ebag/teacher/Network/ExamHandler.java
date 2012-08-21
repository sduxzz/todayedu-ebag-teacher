/*
 * ExaminationSystem-Tea com.todayedu.ebag.teacher.Network ExamAdapter.java
 * 2012 2012-7-13 下午7:29:48
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.Network;

import java.util.List;

import org.apache.mina.core.session.IoSession;
import org.ebag.net.request.ExamRequet;
import org.ebag.net.response.ExamResponse;

import android.content.Context;
import android.util.Log;

import com.todayedu.ebag.teacher.Parameters;
import com.todayedu.ebag.teacher.Parameters.ParaIndex;


/**
 * @author zhenzxie
 *
 */
public class ExamHandler extends BaseNetworkHandler {
	
	/** 目标班级 */
	private int classId;
	private List<Integer> stateList;// 这个域如果是null，会返回所有状态的试卷
	/** 请求id，为null则返回全部对应未考 */
	private List<Integer> idList;// idList放的是试卷id
	/** 请求字段,为空则返回全部字段 */
	private List<String> fieldList;

	protected NetworkCallBack networkCallBack;
	/**
	 * @param context
	 * @param classId
	 * @param stateList
	 * @param idList
	 * @param fieldList
	 */
	public ExamHandler(Context context, NetworkCallBack networkCallBack, int classId,
	        List<Integer> stateList, List<Integer> idList,
	        List<String> fieldList) {
	
		super(context, networkCallBack);
		this.networkCallBack = networkCallBack;
		this.classId = classId;
		this.stateList = stateList;
		this.idList = idList;
		this.fieldList = fieldList;
	}
	
	/**
	 * @see org.apache.mina.core.service.IoHandler#sessionOpened(org.apache.mina.core.session.IoSession)
	 */
	@Override
	public void sessionOpened(IoSession session) throws Exception {
	
		super.sessionOpened(session);
		ExamRequet request = new ExamRequet();
		request.uid = Parameters.get(ParaIndex.UID_INDEX);
		request.classId = classId;
		request.isTeacher = true;
		request.idList = idList;
		request.fieldList = fieldList;
		request.stateList = stateList;
		session.write(request);
		Log.i(TAG, "sessionOpened" + request.toString());
	}
	
	/**
	 * @see org.apache.mina.core.service.IoHandler#messageReceived(org.apache.mina.core.session.IoSession, java.lang.Object)
	 */
	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {

		if (message instanceof ExamResponse) {
			networkCallBack.success(message);
		} else {
			networkCallBack
			        .failed(new Throwable("The return isn't right type."));
		}
		super.messageReceived(session, message);
	}
}
