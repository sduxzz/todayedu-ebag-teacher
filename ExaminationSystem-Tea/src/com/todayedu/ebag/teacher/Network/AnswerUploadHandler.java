/*
 * ExaminationSystem-Tea com.todayedu.ebag.teacher.Network
 * 2012 2012-10-6 ����7:11:11
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.Network;

import java.io.File;
import java.util.ArrayList;

import org.apache.mina.core.session.IoSession;
import org.ebag.net.obj.answer.AnswerObj;
import org.ebag.net.request.AnswerUpload;
import org.ebag.net.response.AnswerUploadResponse;

import android.app.Activity;
import android.util.SparseArray;

import com.todayedu.ebag.teacher.DataSource.DataObj.Answer;
import com.todayedu.ebag.teacher.UIModule.paintpad.PicUpload;


/**
 * @author <a href="zhenzxie.iteye.cn">zhenzxie</a>
 * @version 1.0
 * @since 1.0
 */
public class AnswerUploadHandler extends BaseNetworkHandler {
	
	private class InnerThread extends Thread {
		
		@Override
		public void run() {
		
			final SparseArray<Answer> array = AnswerUploadHandler.this.array;// ���꣬�����û���ṩ�����ķ���,������ͼ��ͼ����
			final ArrayList<AnswerObj> ansList = AnswerUploadHandler.this.ansList;
			Answer answer = null;
			File file = null;
			for (AnswerObj answerObj : ansList) {
				answer = array.get(answerObj.id);// id�� key,�ο�ECSDS�е�getCurrentAnswer����
				if (answer != null) {
					file = new File(answer.getAnswerofTea());
					if (file.exists())
						PicUpload.upload(file);
				}
			}
		}
	}

	private ArrayList<AnswerObj> ansList;
	private SparseArray<Answer> array;
	/**
	 * @param activity
	 * @param callBack
	 */
	public AnswerUploadHandler(Activity activity, NetworkCallBack callBack,
	        ArrayList<AnswerObj> ansList, SparseArray<Answer> array) {
	
		super(activity, callBack);
		this.ansList = ansList;
		this.array = array;
	}
	
	@Override
	public void sessionOpened(IoSession arg0) throws Exception {
	
		super.sessionOpened(arg0);
		AnswerUpload request = new AnswerUpload();
		request.ansList = ansList;
		request.isTeacher = true;
		arg0.write(request);// �ϴ�������
		InnerThread thread = new InnerThread();
		thread.start();// �ϴ���ʦ���ĵ�ͼƬ
	}
	
	@Override
	public void messageReceived(IoSession arg0, Object message)
	        throws Exception {
	
		if (message instanceof AnswerUploadResponse) {
			networkCallBack.success(message);
		} else {
			networkCallBack
			        .failed(new Throwable("The return isn't right type."));
		}
		super.messageReceived(arg0, message);
	}
}
