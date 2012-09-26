/*
 * ExaminationSystem-Tea com.todayedu.ebag.teacher.UIModule
 * 2012 2012-9-20 下午9:35:15
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.UIModule;

import java.util.List;

import org.ebag.net.obj.exam.ProblemInfoObj;
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
import com.todayedu.ebag.teacher.DataAdapter.BaseDataAdapter;
import com.todayedu.ebag.teacher.DataSource.DSCallback;
import com.todayedu.ebag.teacher.DataSource.PCommentDS;
import com.todayedu.ebag.teacher.DataSource.DataObj.Problem;

/**
 * 讲评试卷界面中的题目列表界面
 * 
 * @author <a href="zhenzxie.iteye.cn">zhenzxie</a>
 * @version 1.0
 * @since 1.0
 */
public class PCommentFragment extends ListFragment implements DSCallback {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	        Bundle savedInstanceState) {
	
		return inflater.inflate(R.layout.listfragment, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
	
		super.onActivityCreated(savedInstanceState);

		ds = new PCommentDS(this);
		ds.load(getActivity());
		adapter = new BaseDataAdapter(getActivity(), ds, R.layout.lv_2,
		        new int[] { R.id.lv2_tv_1, R.id.lv2_tv_2 }, keys);
		ds.addObserver(adapter);
		addHeaderView(getActivity(), R.array.pro_id_state);
		setListAdapter(adapter);
	}
	
	@Override
	public void onResume() {
	
		super.onResume();
		if (ds != null && adapter != null) {
			ds.notifyDataChange();
			changePCC();
			Log.i("PCommentFragment", "onResume notifyDataChange");
		}
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
	
		Log.i("FunctionFragment", "onListItemClick: Item clicked " + id);
		if (ds.moveTo(position - 1))
			changePCC();
	}

	public void onPrevious() {

		ds.moveToPrevious();
		changePCC();
	}

	public void onLabel() {
	
		ds.onLable(getActivity());
		ds.notifyDataChange();
		onNext();
	}
	
	public void onNext() {
	
		ds.moveToNext();
		changePCC();
	}

	@Override
	public void onLoadSuccess(Object object) {
	
		ExamResponse examResponse = (ExamResponse) object;
		if (examResponse == null || examResponse.examList.size() == 0)
			return;
		List<ProblemInfoObj> list = examResponse.examList.get(0).getpInfoList();
		if (list == null || list.size() == 0)
			return;
		ds.setpInfoList(list);
		ds.createMaps(getActivity(), keys);
		ds.notifyDataChange(getActivity());
	}

	@Override
	public void onLoadFailed(Throwable throwable) {
	
		PCommentActivity activity = (PCommentActivity) getActivity();
		if (throwable != null) {
			Log.i("PCommentFragment", throwable.getMessage());
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
	 * @see PCommentActivity#changePCC(Problem, boolean, boolean)
	 */
	private void changePCC() {
	
		((PCommentActivity) getActivity()).changePCC(ds.getCurrentProblem(),
		        ds.canPrevious(), ds.canNext());
	}
}