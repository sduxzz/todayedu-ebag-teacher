/*
 * ExaminationSystem-Tea com.todayedu.ebag.teacher.Network ExamAdapter.java
 * 2012 2012-7-13 ����7:29:48
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.Network;

import java.util.List;

import org.apache.mina.core.session.IoSession;
import org.ebag.net.request.AnswerRequest;

import android.content.Context;
import android.util.Log;

/**
 * @author zhenzxie
 * 
 */
public class AnswerHandler extends BaseNetworkHandler {
	
	/** Ŀ���� */
	public int uid;
	/** Ŀ�꿼�� */
	public int examId;
	/** ������������id,Ϊnull�򷵻�ȫ��id�Ĵ� */
	public List<Integer> idList;
	/** �����ֶ�,Ϊnull�򷵻�ȫ���ֶ� */
	public List<String> fieldList;

	/**
	 * @param context
	 * @param dataSource
	 * @param uid
	 * @param examId
	 * @param idList
	 * @param fieldList
	 */
	public AnswerHandler(Context context, NetworkCallBack callBack, int uid,
	        int examId, List<Integer> idList, List<String> fieldList) {
	
		super(context, callBack);
		this.uid = uid;
		this.examId = examId;
		this.idList = idList;
		this.fieldList = fieldList;
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
		Log.i(TAG, "sessionOpened " + request.toString());
	}
	

	
	/**
	 * @see org.apache.mina.core.service.IoHandler#messageReceived(org.apache.mina.core.session.IoSession,
	 *      java.lang.Object)
	 */
	@Override
	public void messageReceived(IoSession session, Object message)
	        throws Exception {
	
	}
	
}
