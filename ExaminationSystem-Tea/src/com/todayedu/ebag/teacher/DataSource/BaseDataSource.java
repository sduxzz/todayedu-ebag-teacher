/*
 * ExaminationSystem-Tea com.todayedu.examinationsystem.teacher.DataSource
 * 2012 2012-5-31 下午3:30:23
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.DataSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.todayedu.ebag.teacher.R;
import com.todayedu.ebag.teacher.Network.BaseNetworkHandler;
import com.todayedu.ebag.teacher.Network.NetWorkUtil;
import com.todayedu.ebag.teacher.Network.NetworkCallBack;
import com.todayedu.ebag.teacher.Network.NetworkClient;
import com.todayedu.ebag.teacher.UIModule.BaseActivity;

/**
 * @author zhenzxie
 * 
 */
public abstract class BaseDataSource extends Observable implements NetworkCallBack {
	
	protected String TAG = this.getClass().getSimpleName();
	protected List<? extends Data> list = new ArrayList<Data>();
	protected List<Map<String, String>> data = new ArrayList<Map<String, String>>();
	protected DSCallback callback;
	protected NetworkClient client;

	public BaseDataSource(DSCallback callback) {
	
		this.callback = callback;
	}

	/**
	 * fill List<Map<String,String> with specified keys
	 */
	public abstract void createMaps(String[] keys);
	
	/**
	 * down load the data from server
	 * 
	 * @param context
	 * 
	 */
	protected abstract void download(Activity context);
	
	public void connect(BaseNetworkHandler handler) {
	
		client = new NetworkClient();
		client.setHandler(handler);
		client.connect();
	}

	protected void disconnect() {
	
		client.disconnect();
	}

	/**
	 * load the data from server if network is available or show toast if there
	 * isn't network connection.
	 * 
	 * @param context
	 * 
	 */
	public final void load(Activity context) {
	
		if (NetWorkUtil.isConnected(context)) {
			download(context);
		} else {
			// TODO:showToast肯能不需要
			((BaseActivity)context).showToast(R.string.sp_networkalter,Toast.LENGTH_LONG);
		}
	}

	/**
	 * 
	 * @see com.todayedu.ebag.teacher.DataSource.DataSource#save(android.content.Context)
	 */
	public void save(Context context) {
	
		for (Data d : list) {
			d.save(context);
		}
	}

	public void notifyDataChange() {
	
		setChanged();
		notifyObservers();
		clearChanged();
	}
	
	/**
	 * @return the list
	 */
	public List<? extends Data> getList() {
	
		return list;
	}
	
	/**
	 * set the list and fill the Maps
	 * 
	 * @param list
	 *            the list to set
	 */
	public void setList(List<? extends Data> list) {
	
		this.list = list;
	}
	
	/**
	 * @return the data
	 */
	public List<Map<String, String>> getData() {
	
		return data;
	}
	
	/**
	 * 
	 * @see com.todayedu.ebag.teacher.Network.NetworkCallBack#success(java.lang.Object)
	 */
	@Override
	public void success(Object response) {
	
		disconnect();
		callback.onLoadSuccess(response);
	}

	/**
	 * 
	 * @see com.todayedu.ebag.teacher.Network.NetworkCallBack#failed(java.lang.Throwable)
	 */
	@Override
	public void failed(Throwable throwable) {
	
		disconnect();
		callback.onLoadFailed(throwable);
	}

}