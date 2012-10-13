/*
 * ExaminationSystem-Tea com.todayedu.ebag.teacher.Network BaseAdapter.java
 * 2012 2012-7-13 ����4:19:26
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.Network;

import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Log;

/**
 * @author zhenzxie
 * 
 */
public abstract class BaseNetworkHandler implements IoHandler {
	
	protected String TAG = this.getClass().getSimpleName();
	protected Activity zContext;
	private ProgressDialog zDialog;
	protected NetworkCallBack networkCallBack;
	
	public BaseNetworkHandler(Activity activity, NetworkCallBack callBack) {
	
		this.zContext = activity;
		this.networkCallBack = callBack;
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
	
		Log.i(TAG, "sessionCreated");
		zContext.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
			
				zDialog = new ProgressDialog(zContext);
				zDialog.setTitle("��������");
				zDialog.setMessage("loading,wait please...");
				zDialog.setCancelable(false);
				zDialog.show();
			}
		});
	}
	
	@Override
	public void sessionOpened(IoSession arg0) throws Exception {
	
		Log.i(TAG, "sessionOpened");
	}
	
	@Override
	public void sessionIdle(IoSession arg0, IdleStatus arg1) throws Exception {
	
		Log.i(TAG, "sessionIdle");
	}
	
	@Override
	public void sessionClosed(IoSession session) throws Exception {
	
		Log.i(TAG, "sessionClosed");
	}
	
	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
	        throws Exception {
	
		Log.i(TAG, "exceptionCaught");
		cause.printStackTrace();
		networkCallBack.failed(cause);
	}
	
	@Override
	public void messageSent(IoSession arg0, Object arg1) throws Exception {
	
		Log.i(TAG, "messageSent");
	}
	
	@Override
	public void messageReceived(IoSession arg0, Object arg1) throws Exception {
	
		Log.i(TAG, "messageReceived");
	}

	public void dismiss() {
	
		Log.i(TAG, "-----------------dismiss-----------------");
		zContext.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
			
				if (zDialog != null) {
					Log.i(TAG, "----------------------------------------");
					zDialog.dismiss();
				}
			}
		});
	}
}
