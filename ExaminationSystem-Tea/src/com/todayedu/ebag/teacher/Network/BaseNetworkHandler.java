/*
 * ExaminationSystem-Tea com.todayedu.ebag.teacher.Network BaseAdapter.java
 * 2012 2012-7-13 œ¬ŒÁ4:19:26
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.Network;

import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;


/**
 * @author zhenzxie
 *
 */
public abstract class BaseNetworkHandler implements IoHandler {
	
	protected String TAG = this.getClass().getSimpleName();
	protected Context zContext;
	private ProgressDialog zDialog;
	protected NetworkCallBack networkCallBack;
	
	public BaseNetworkHandler(Context context, NetworkCallBack callBack) {
	
		this.zContext = context;
		this.networkCallBack = callBack;
	}

	/**
	 * @see org.apache.mina.core.service.IoHandler#sessionCreated(org.apache.mina.core.session.IoSession)
	 */
	@Override
	public void sessionCreated(IoSession session) throws Exception {
	
		Log.i(TAG, "sessionCreated");
		((Activity) zContext).runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
			
				zDialog = new ProgressDialog(zContext);
				zDialog.setTitle("∑√Œ Õ¯¬Á");
				zDialog.setMessage("loading,wait please...");
				zDialog.setCancelable(true);// TODO:change true to false
				zDialog.show();
			}
		});
	}
	
	/**
	 * @see org.apache.mina.core.service.IoHandler#sessionOpened(org.apache.mina.core.session.IoSession)
	 */
	@Override
	public void sessionOpened(IoSession arg0) throws Exception {
	
		Log.i(TAG, "sessionOpened");
	}
	
	/**
	 * @see org.apache.mina.core.service.IoHandler#sessionIdle(org.apache.mina.core.session.IoSession,
	 *      org.apache.mina.core.session.IdleStatus)
	 */
	@Override
	public void sessionIdle(IoSession arg0, IdleStatus arg1) throws Exception {
	
		Log.i(TAG, "sessionIdle");
	}
	
	/**
	 * @see org.apache.mina.core.service.IoHandler#sessionClosed(org.apache.mina.core.session.IoSession)
	 */
	@Override
	public void sessionClosed(IoSession session) throws Exception {
	
		Log.i(TAG, "sessionClosed");
		dismiss();
	}
	
	/**
	 * @see org.apache.mina.core.service.IoHandler#exceptionCaught(org.apache.mina.core.session.IoSession,
	 *      java.lang.Throwable)
	 */
	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
	
		Log.i(TAG, "exceptionCaught");
		cause.printStackTrace();
		networkCallBack.failed(cause);
		dismiss();
	}
	
	/**
	 * @see org.apache.mina.core.service.IoHandler#messageSent(org.apache.mina.core.session.IoSession,
	 *      java.lang.Object)
	 */
	@Override
	public void messageSent(IoSession arg0, Object arg1) throws Exception {
	
		Log.i(TAG, "messageSent");
	}
	
	/**
	 * @see org.apache.mina.core.service.IoHandler#messageReceived(org.apache.mina.core.session.IoSession,
	 *      java.lang.Object)
	 */
	@Override
	public void messageReceived(IoSession arg0, Object arg1) throws Exception {
	
		Log.i(TAG, "messageReceived");
		dismiss();
	}

	private void dismiss() {
	
		if (zDialog != null)
			((Activity) zContext).runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
				
					zDialog.dismiss();
				}
			});
	}
}
