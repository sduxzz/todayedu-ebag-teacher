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
import org.ebag.net.request.AnswerRequest;

import android.content.Context;

import com.todayedu.ebag.teacher.DataSource.DataSource;

/**
 * @author zhenzxie
 * 
 */
public class AnswerHandler extends BaseNetworkHandler {
	
	/** 目标人 */
	public int uid;
	/** 目标考试 */
	public int examId;
	/** 请求答题详情的id,为null则返回全部id的答案 */
	public List<Integer> idList;
	/** 请求字段,为null则返回全部字段 */
	public List<Field> fieldList;


	/**
	 * @param context
	 * @param dataSource
	 * @param uid
	 * @param examId
	 * @param idList
	 * @param fieldList
	 */
	public AnswerHandler(Context context, DataSource dataSource, int uid,
			int examId, List<Integer> idList, List<Field> fieldList) {
	
		super(context, dataSource);
		this.uid = uid;
		this.examId = examId;
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
	
		AnswerRequest request = new AnswerRequest();
		request.setUid(uid);
		request.examId = examId;
		request.setIdList(idList);
		request.setFieldList(fieldList);
		session.write(request);
	}
	
	/**
	 * @see org.apache.mina.core.service.IoHandler#sessionClosed(org.apache.mina.core.session.IoSession)
	 */
	@Override
	public void sessionClosed(IoSession session) throws Exception {
	
	}
	
	/**
	 * @see org.apache.mina.core.service.IoHandler#sessionIdle(org.apache.mina.core.session.IoSession,
	 *      org.apache.mina.core.session.IdleStatus)
	 */
	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
	
	}
	
	/**
	 * @see org.apache.mina.core.service.IoHandler#exceptionCaught(org.apache.mina.core.session.IoSession,
	 *      java.lang.Throwable)
	 */
	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
	
	}
	
	/**
	 * @see org.apache.mina.core.service.IoHandler#messageReceived(org.apache.mina.core.session.IoSession,
	 *      java.lang.Object)
	 */
	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
	
	}
	
	/**
	 * @see org.apache.mina.core.service.IoHandler#messageSent(org.apache.mina.core.session.IoSession,
	 *      java.lang.Object)
	 */
	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
	
	}
	
}
