/*
 * ExaminationSystem-Tea com.todayedu.ebag.teacher.UIModule
 * 2012 2012-9-20 下午9:35:15
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.UIModule;

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
import com.todayedu.ebag.teacher.DataAdapter.BaseDataAdapter;
import com.todayedu.ebag.teacher.DataSource.DSCallback;
import com.todayedu.ebag.teacher.DataSource.ECSDS;
import com.todayedu.ebag.teacher.DataSource.DataObj.Answer;

/**
 * 某个学生的要批改试卷的界面中的题目列表界面
 * 
 * @author <a href="zhenzxie.iteye.cn">zhenzxie</a>
 * @version 1.0
 * @since 1.0
 */
public class ECSFragment extends ListFragment implements DSCallback {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	        Bundle savedInstanceState) {
	
		return inflater.inflate(R.layout.listfragment, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
	
		super.onActivityCreated(savedInstanceState);

		ds = new ECSDS(this);
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
			changeECSS();
			Log.i("ECSFragment", "onResume notifyDataChange");
		}
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
	
		Log.i("ECSFragment", "onListItemClick: Item clicked " + position);
		if (ds.moveTo(position - 1))
			changeECSS();
	}
	
	@Override
	public void onLoadSuccess(Object object) {
	
		AnswerResponse response = (AnswerResponse) object;
		if (response == null || response.examList.size() == 0)
			return;
		ds.setExamList(response.examList);
		ds.createMaps(getActivity(), keys);
		ds.notifyDataChange(getActivity());
		getActivity().runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
			
				changeECSS();
			}
		});
	}
	
	@Override
	public void onLoadFailed(Throwable throwable) {
	
		SChooseActivity activity = (SChooseActivity) getActivity();
		if (throwable != null) {
			Log.i("ECSFragment", throwable.getMessage());
		}
		activity.showToast("加载数据失败");
	}
	
	/**
	 * 
	 * @param picOfTeacherUrl
	 * @param textOfTeacher
	 * @param point
	 */
	public void onConfirm(String answerofTea, String textOfTeacher, double point) {
	
		ds.onComfirm(getActivity(), answerofTea, textOfTeacher, point);
		ds.notifyDataChange();
	}

	public void onNext() {
	
		ds.moveToNext();
		changeECSS();
	}
	
	public void onPrevious() {
	
		ds.moveToPrevious();
		changeECSS();
	}

	private BaseDataAdapter adapter;
	private ECSDS ds;
	private final String[] keys = new String[] { "number", "state" };
	
	private void addHeaderView(Context context, int res) {
	
		View headerView = HeaderViewFactory.createHeaderView2(context, res);
		getListView().addHeaderView(headerView, null, false);
	}
	
	/**
	 * @see ECSActivity#changeECSS(Answer, boolean, boolean)
	 */
	private void changeECSS() {
	
		((ECSActivity) getActivity()).changeECSS(ds.getCurrentAnswer(),
		        ds.canPrevious(), ds.canNext());
	}
}