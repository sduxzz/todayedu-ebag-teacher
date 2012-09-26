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
	protected List<Map<String, String>> map = new ArrayList<Map<String, String>>();
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
	
	/**
	 * 打开网络链接，设置处理器，开始下载数据
	 * 
	 * @param handler
	 *            处理器
	 */
	public void loadStart(BaseNetworkHandler handler) {
	
		client = new NetworkClient();
		client.setHandler(handler);
		client.connect();
	}

	/**
	 * 下载结束，断开网络连接
	 */
	protected void loadComplete() {
	
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
	 * 提醒观察者数据改变了
	 */
	public void notifyDataChange() {
	
		setChanged();
		notifyObservers();
		clearChanged();
	}
	
	/**
	 * 在UI线程中更新数据
	 * 
	 * @param activity
	 */
	public void notifyDataChange(Activity activity) {
	
		activity.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
			
				notifyDataChange();
			}
		});
	}
	
	/**
	 * @return the data
	 */
	public List<Map<String, String>> getListMap() {
	
		return map;
	}
	
	/**
	 * 
	 * @see com.todayedu.ebag.teacher.Network.NetworkCallBack#success(java.lang.Object)
	 */
	@Override
	public void success(Object response) {
	
		loadComplete();
		callback.onLoadSuccess(response);
	}

	/**
	 * 
	 * @see com.todayedu.ebag.teacher.Network.NetworkCallBack#failed(java.lang.Throwable)
	 */
	@Override
	public void failed(Throwable throwable) {
	
		loadComplete();
		callback.onLoadFailed(throwable);
	}

}