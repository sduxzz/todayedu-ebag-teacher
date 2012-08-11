/*
 * ExaminationSystem-Tea com.todayedu.ebag.teacher.Network ExamAdapter.java
 * 2012 2012-7-13 下午7:29:48
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.Network;

import java.lang.reflect.Field;
import java.util.List;

import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.ebag.net.request.ExamRequet;
import org.ebag.net.response.ExamResponse;

import android.content.Context;


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
	private List<Integer> stateList;// TODO:问鞠强，这个域如果是null，是否会返回所有状态的试卷
	/** 请求id，为null则返回全部对应未考 */
	private List<Integer> idList;
	/** 请求字段,为空则返回全部字段 */
	private List<Field> fieldList;

	protected ExamCallBack examCallBack;
	/**
	 * @param context
	 * @param classId
	 * @param stateList
	 * @param idList
	 * @param fieldList
	 */
	public ExamHandler(Context context, ExamCallBack callBack, int classId,
			List<Integer> stateList, List<Integer> idList, List<Field> fieldList) {
	
		super(context);
		this.examCallBack = callBack;
		this.classId = classId;
		this.stateList = stateList;
		this.idList = idList;
		this.fieldList = fieldList;
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
	
		ExamRequet request = new ExamRequet();
		request.classId = classId;
		request.setIdList(idList);
		request.setFieldList(fieldList);
		request.stateList = stateList;
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
	
		examCallBack.examError(cause);
	}
	
	/**
	 * @see org.apache.mina.core.service.IoHandler#messageReceived(org.apache.mina.core.session.IoSession, java.lang.Object)
	 */
	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
	
		if (message instanceof ExamResponse) {
			ExamResponse response = (ExamResponse) message;
			examCallBack.examSuccess(response);
		}
	}
	
	/**
	 * @see org.apache.mina.core.service.IoHandler#messageSent(org.apache.mina.core.session.IoSession, java.lang.Object)
	 */
	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
	
	}
	
}
