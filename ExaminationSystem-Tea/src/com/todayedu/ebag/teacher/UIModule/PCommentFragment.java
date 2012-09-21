/*
 * ExaminationSystem-Tea com.todayedu.ebag.teacher.UIModule
 * 2012 2012-9-20 下午9:35:15
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.UIModule;

import java.util.List;

import org.ebag.net.response.ExamResponse;

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
import com.todayedu.ebag.teacher.DataSource.Data;
import com.todayedu.ebag.teacher.DataSource.PCommentDS;
import com.todayedu.ebag.teacher.Network.ResponseParseUtil;

/**
 * 讲评试卷界面中的题目列表
 * 
 * @author <a href="zhenzxie.iteye.cn">zhenzxie</a>
 * @version 1.0
 * @since 1.0
 */
public class PCommentFragment extends ListFragment implements DSCallback {
	
	/**
	 * @see android.app.ListFragment#onCreateView(android.view.LayoutInflater,
	 *      android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	        Bundle savedInstanceState) {
	
		return inflater.inflate(R.layout.fuctionchoosefragment, container,
		        false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
	
		super.onActivityCreated(savedInstanceState);

		PCommentActivity activity = (PCommentActivity) getActivity();

		ds = new PCommentDS(this);
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
		String TAG = "PCommentFragment";
		Log.i(TAG, "onResume");
		if (ds != null && adapter != null) {
			ds.notifyDataChange();
			Log.i(TAG, "onResume notifyDataChange");
		}
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
	
		Log.i("FunctionFragment", "onListItemClick: Item clicked " + id);
		TempData.setIndex(position - 1);
		changePCC();
	}
	
	@Override
	public void onLoadSuccess(Object object) {
	
		ExamResponse examResponse = (ExamResponse) object;
		final List<Data> list = ResponseParseUtil
		        .parseExamResponse2ProblemList(examResponse, getActivity());// TODO:出来list长度为的0的时候
		ds.setList(list);
		ds.createMaps(keys);
		getActivity().runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
			
				ds.notifyDataChange();
				TempData.storeData(ds, 0);
				changePCC();
			}
		});
	}

	/**
	 * @see com.todayedu.ebag.teacher.DataSource.DSCallback#onLoadFailed(java.lang.Throwable)
	 */
	@Override
	public void onLoadFailed(Throwable throwable) {
	
		PCommentActivity activity = (PCommentActivity) getActivity();
		String TAG = "ExamShowFragment";
		Log.i(TAG, "onLoadFailed");
		if (throwable != null) {
			Log.i(TAG, throwable.getMessage());
		}
		activity.showToast("加载数据失败");
	}
	
	private BaseDataAdapter adapter;
	private PCommentDS ds;
	private final String[] keys = new String[] { "number", "state" };
	
	private void addHeaderView(Context context, int res) {
	
		View headerView = HeaderViewFactory.createHeaderView2(context, res);
		getListView().addHeaderView(headerView, null, false);
	}
	
	/**
	 * @see PCommentActivity#changePCC()
	 */
	private void changePCC() {
	
		((PCommentActivity) getActivity()).changePCC();
	}
}