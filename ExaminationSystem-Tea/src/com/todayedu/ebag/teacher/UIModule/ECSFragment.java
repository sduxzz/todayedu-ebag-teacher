/*
 * ExaminationSystem-Tea com.todayedu.ebag.teacher.UIModule
 * 2012 2012-9-20 下午9:35:15
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.UIModule;

import java.util.List;

import org.ebag.net.response.AnswerResponse;

import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.todayedu.ebag.teacher.R;
import com.todayedu.ebag.teacher.TempData;
import com.todayedu.ebag.teacher.DataAdapter.BaseDataAdapter;
import com.todayedu.ebag.teacher.DataSource.DSCallback;
import com.todayedu.ebag.teacher.DataSource.ECSDS;
import com.todayedu.ebag.teacher.DataSource.DataObj.Answer;
import com.todayedu.ebag.teacher.Network.ResponseParseUtil;

/**
 * 某个学生的要批改试卷的界面中的题目列表
 * 
 * @author <a href="zhenzxie.iteye.cn">zhenzxie</a>
 * @version 1.0
 * @since 1.0
 */
public class ECSFragment extends ListFragment implements DSCallback {
	
	/**
	 * @see android.app.ListFragment#onCreateView(android.view.LayoutInflater,
	 *      android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	        Bundle savedInstanceState) {
	
		return inflater.inflate(R.layout.listfragment, container,
		        false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
	
		super.onActivityCreated(savedInstanceState);

		ECSActivity activity = (ECSActivity) getActivity();
		
		ds = new ECSDS(this);
		ds.load(activity);
		activity.addLifeCycleListener(ds);
		
		adapter = new BaseDataAdapter(activity, ds, R.layout.lv_2, new int[] {
		        R.id.lv2_tv_1, R.id.lv2_tv_2 }, keys);
		ds.addObserver(adapter);
		
		addHeaderView(activity, R.array.pro_id_state);
		setListAdapter(adapter);
	}
	
	@Override
	public void onResume() {
	
		super.onResume();
		String TAG = "ECSFragment";
		Log.i(TAG, "onResume");
		if (ds != null && adapter != null) {
			ds.notifyDataChange();
			changeECSS();
			Log.i(TAG, "onResume notifyDataChange");
		}
	}
	
	public void onConfirm() {
	
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
	
		Log.i("ECSFragment", "onListItemClick: Item clicked " + id);
		TempData.setCurrentIndex(position - 1);
		changeECSS();
	}
	
	@Override
	public void onLoadSuccess(Object object) {
	
		AnswerResponse response = (AnswerResponse) object;
		final List<Answer> list = ResponseParseUtil
		        .parseAnswerResponse(response);
		ds.setList(list);
		ds.createMaps(keys);
		getActivity().runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
			
				ds.notifyDataChange();
				TempData.storeData(ds, 0);
				changeECSS();
			}
		});
	}
	
	/**
	 * @see com.todayedu.ebag.teacher.DataSource.DSCallback#onLoadFailed(java.lang.Throwable)
	 */
	@Override
	public void onLoadFailed(Throwable throwable) {
	
		SChooseActivity activity = (SChooseActivity) getActivity();
		String TAG = "ECSFragment";
		Log.i(TAG, "onLoadFailed");
		if (throwable != null) {
			Log.i(TAG, throwable.getMessage());
		}
		activity.showToast("加载数据失败");
	}
	
	private BaseDataAdapter adapter;
	private ECSDS ds;
	private final String[] keys = new String[] { "number", "state" };
	
	private void addHeaderView(Context context, int res) {
	
		View headerView = HeaderViewFactory.createHeaderView2(context, res);
		getListView().addHeaderView(headerView, null, false);
	}
	
	/**
	 * @see ECSActivity#changeECSS()
	 */
	private void changeECSS() {
	
		((ECSActivity) getActivity()).changeECSS();
	}
}