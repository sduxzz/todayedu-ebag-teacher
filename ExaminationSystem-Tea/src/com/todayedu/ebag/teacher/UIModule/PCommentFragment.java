/*
 * ExaminationSystem-Tea com.todayedu.ebag.teacher.UIModule
 * 2012 2012-9-20 下午9:35:15
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.UIModule;

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

/**
 * 讲评试卷界面中的题目列表界面
 * 
 * @author <a href="zhenzxie.iteye.cn">zhenzxie</a>
 * @version 1.0
 * @since 1.0
 */
public class PCommentFragment extends ListFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	        Bundle savedInstanceState) {
	
		return inflater.inflate(R.layout.listfragment, container, false);
	}
	
	public void initPCommentFragment(BaseDataAdapter adapter) {
	
		removeHeaderView();
		addHeaderView(getActivity(), R.array.pro_id_state);
		setListAdapter(adapter);
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
	
		Log.i("FunctionFragment", "onListItemClick: Item clicked " + id);
		if (position <= 0)
			return;
		PCommentActivity activity = (PCommentActivity) getActivity();
		activity.onPosition(position - 1);
	}
	
	private View headerView;
	
	private void removeHeaderView() {
	
		getListView().removeHeaderView(headerView);
	}

	private void addHeaderView(Context context, int res) {
	
		headerView = HeaderViewFactory.createHeaderView2(context, res);
		getListView().addHeaderView(headerView, null, false);
	}
}