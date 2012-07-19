/*
 * ExaminationSystem-Tea com.todayedu.ebag.teacher.Network BaseAdapter.java
 * 2012 2012-7-13 ����4:19:26
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.Network;

import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IoSession;

import android.app.ProgressDialog;
import android.content.Context;

import com.todayedu.ebag.teacher.DataSource.DataSource;


/**
 * @author zhenzxie
 *
 */
public abstract class BaseNetworkHandler implements IoHandler {
	
	protected String TAG = this.getClass().getName();
	protected Context zContext;
	protected DataSource zDataSource;
	private ProgressDialog zDialog;

	public BaseNetworkHandler(Context context, DataSource dataSource) {
	
		this.zContext = context;
		this.zDataSource = dataSource;
	}

	/**
	 * @see org.apache.mina.core.service.IoHandler#sessionCreated(org.apache.mina.core.session.IoSession)
	 */
	@Override
	public void sessionCreated(IoSession session) throws Exception {
	
		zDialog = new ProgressDialog(zContext);
		zDialog.setTitle("��������");
		zDialog.setMessage("loading,wait please...");
		zDialog.setCancelable(true);
		zDialog.show();
	}
	
	/**
	 * @see org.apache.mina.core.service.IoHandler#sessionClosed(org.apache.mina.core.session.IoSession)
	 */
	@Override
	public void sessionClosed(IoSession session) throws Exception {
	
		dismiss();
	}
	
	/**
	 * @see org.apache.mina.core.service.IoHandler#exceptionCaught(org.apache.mina.core.session.IoSession,
	 *      java.lang.Throwable)
	 */
	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
	
		dismiss();
	}
	
	private void dismiss() {
	
		if (zDialog != null && zDialog.isShowing())
			zDialog.dismiss();
	}
}
