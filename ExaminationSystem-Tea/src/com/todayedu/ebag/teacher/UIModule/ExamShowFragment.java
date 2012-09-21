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

import com.todayedu.ebag.teacher.Parameters;
import com.todayedu.ebag.teacher.Parameters.ParaIndex;
import com.todayedu.ebag.teacher.R;
import com.todayedu.ebag.teacher.DataAdapter.BaseDataAdapter;
import com.todayedu.ebag.teacher.DataSource.BaseDataSource;
import com.todayedu.ebag.teacher.DataSource.DSCallback;
import com.todayedu.ebag.teacher.DataSource.Data;
import com.todayedu.ebag.teacher.DataSource.ExamListDS;
import com.todayedu.ebag.teacher.DataSource.DataObj.Exam;
import com.todayedu.ebag.teacher.Network.ResponseParseUtil;

/**
 * 五种不同模式的试卷列表，对应有着不同的headerview内容文本，跳转到不同的Activity
 * 
 * @author <a href="zhenzxie.iteye.cn">zhenzxie</a>
 * @version 1.0
 * @since 1.0
 */
public class ExamShowFragment extends ListFragment implements DSCallback {
	
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
		
		FunctionActivity activity = (FunctionActivity) getActivity();
		
		ds = new ExamListDS(this);
		activity.addLifeCycleListener(ds);

		adapter = new BaseDataAdapter(getActivity(), ds, R.layout.lv_1,
		        new int[] { R.id.lv1_tv_1 }, keys);
		ds.addObserver(adapter);

		addHeaderView(activity, R.array.appname);
		setListAdapter(adapter);
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
	
		Log.i("FunctionFragment", "onListItemClick: Item clicked " + id);
		if (position <= 0)
			return;
		Exam exam = (Exam) ds.getList().get(position - 1);
		int eid = exam.getEid();
		Parameters.add(eid, ParaIndex.EID_INDEX);
		Parameters.setExam(exam);
		((FunctionActivity) getActivity()).start();
	}
	
	public void load(FunctionActivity activity) {

		if (ds != null) {
			ds.load(activity);
		}
	}
	
	private View headerView;
	public void addHeaderView(Context context, int res) {
	
		headerView = HeaderViewFactory.createHeaderView1(context, res);
		getListView().addHeaderView(headerView, null, false);
	}
	
	public void changeHeaderView(Context context, int res) {
	
		getListView().removeHeaderView(headerView);
		addHeaderView(context, res);
	}

	@Override
	public void onLoadSuccess(Object object) {
	
		ExamResponse examResponse = (ExamResponse) object;
		final List<Data> list = ResponseParseUtil
		        .parseExamResponse(examResponse);
		ds.setList(list);
		ds.createMaps(keys);
		getActivity().runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
			
				ds.notifyDataChange();
			}
		});
	}
	
	@Override
	public void onLoadFailed(Throwable throwable) {
	
		FunctionActivity activity = (FunctionActivity) getActivity();
		String TAG = "ExamShowFragment";
		Log.i(TAG, "onLoadFailed");
		if (throwable != null) {
			Log.i(TAG, throwable.getMessage());
		}
		activity.showToast("加载数据失败");
	}

	private BaseDataAdapter adapter;
	private BaseDataSource ds;
	private final String[] keys = new String[] { "ename", "eid" };
}