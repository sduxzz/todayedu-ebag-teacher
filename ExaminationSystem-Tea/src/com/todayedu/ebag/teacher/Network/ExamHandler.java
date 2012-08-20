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
	
	/**
	 * 
	 * @author zhenzxie
	 * 
	 */
	public interface ExamCallBack {
		
		public void examSuccess(ExamResponse examResponse);
		
		public void examError(Throwable cause);
	}

	/** 目标班级 */
	private int classId;
	private List<Integer> stateList;// 这个域如果是null，会返回所有状态的试卷
	/** 请求id，为null则返回全部对应未考 */
	private List<Integer> idList;// idList放的是试卷id
	/** 请求字段,为空则返回全部字段 */
	private List<String> fieldList;

	protected ExamCallBack examCallBack;
	/**
	 * @param context
	 * @param classId
	 * @param stateList
	 * @param idList
	 * @param fieldList
	 */
	public ExamHandler(Context context, ExamCallBack callBack, int classId,
	        List<Integer> stateList, List<Integer> idList,
	        List<String> fieldList) {
	
		super(context);
		this.examCallBack = callBack;
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
		Log.i(TAG, "sessionOpened:" + request);
	}
	
	/**
	 * @see org.apache.mina.core.service.IoHandler#sessionClosed(org.apache.mina.core.session.IoSession)
	 */
	@Override
	public void sessionClosed(IoSession session) throws Exception {
	
		super.sessionClosed(session);
	}
	
	/**
	 * @see org.apache.mina.core.service.IoHandler#exceptionCaught(org.apache.mina.core.session.IoSession, java.lang.Throwable)
	 */
	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
	
		super.exceptionCaught(session, cause);
		examCallBack.examError(cause);
	}
	
	/**
	 * @see org.apache.mina.core.service.IoHandler#messageReceived(org.apache.mina.core.session.IoSession, java.lang.Object)
	 */
	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
	
		super.messageReceived(session, message);
		if (message instanceof ExamResponse) {
			ExamResponse response = (ExamResponse) message;
			examCallBack.examSuccess(response);
		}
	}
}
