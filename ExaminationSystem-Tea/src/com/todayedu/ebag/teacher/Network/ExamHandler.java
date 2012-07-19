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

import com.todayedu.ebag.teacher.DataSource.Data;
import com.todayedu.ebag.teacher.DataSource.DataSource;
import com.todayedu.ebag.teacher.DataSource.DataObj.Exam;


/**
 * @author zhenzxie
 *
 */
public class ExamHandler extends BaseNetworkHandler {
	
	/** 目标班级 */
	private int classId;
	private List<Integer> stateList;
	/** 请求id，为null则返回全部对应未考 */
	private List<Integer> idList;
	/** 请求字段,为空则返回全部字段 */
	private List<Field> fieldList;

	/**
	 * @param context
	 * @param dataSource
	 * @param classId
	 * @param stateList
	 * @param idList
	 * @param fieldList
	 */
	public ExamHandler(Context context, DataSource dataSource, int classId,
			List<Integer> stateList, List<Integer> idList, List<Field> fieldList) {
	
		super(context, dataSource);
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
		request.setUid(classId);
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
	
	}
	
	/**
	 * @see org.apache.mina.core.service.IoHandler#messageReceived(org.apache.mina.core.session.IoSession, java.lang.Object)
	 */
	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
	
		if (message instanceof ExamResponse) {
			ExamResponse response = (ExamResponse) message;
			List<Data> list = Exam.parse(response);
			zDataSource.store(list);
			zDataSource.notifyDataChange();
		}
	}
	
	/**
	 * @see org.apache.mina.core.service.IoHandler#messageSent(org.apache.mina.core.session.IoSession, java.lang.Object)
	 */
	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
	
	}
	
}
