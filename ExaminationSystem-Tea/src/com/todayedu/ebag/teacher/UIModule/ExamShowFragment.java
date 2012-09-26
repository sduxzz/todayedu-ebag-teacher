/*
 * ExaminationSystem-Tea com.todayedu.ebag.teacher.UIModule
 * 2012 2012-9-20 下午9:35:15
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.UIModule;

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
import com.todayedu.ebag.teacher.DataSource.ExamListDS;

/**
 * 四种不同模式的试卷列表，对应有着不同的headerview内容文本，跳转到不同的Activity。
 * 
 * @author <a href="zhenzxie.iteye.cn">zhenzxie</a>
 * @version 1.0
 * @since 1.0
 */
public class ExamShowFragment extends ListFragment implements DSCallback {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	        Bundle savedInstanceState) {
	
		return inflater.inflate(R.layout.listfragment, container,
		        false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
	
		super.onActivityCreated(savedInstanceState);
		
		ds = new ExamListDS(this);
		adapter = new BaseDataAdapter(getActivity(), ds, R.layout.lv_1,
		        new int[] { R.id.lv1_tv_1 }, keys);
		ds.addObserver(adapter);
		addHeaderView(getActivity(), R.array.appname);
		setListAdapter(adapter);
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
	
		Log.i("FunctionFragment", "onListItemClick: Item clicked " + position);
		if (position <= 0)
			return;
		ds.onListItemClick(position);
		((FunctionActivity) getActivity()).start(allTargetActivity[mode]);
	}

	/**
	 * 载入试卷数据
	 * 
	 * @param activity
	 */
	public void load(FunctionActivity activity) {

		if (ds != null) {
			ds.load(activity);
		}
	}
	
	/**
	 * 修改HeaderView
	 * 
	 * @param context
	 */
	public void changeHeaderView(Context context) {
	
		getListView().removeHeaderView(headerView);
		addHeaderView(context, allHeadViewTestId[mode]);
	}

	@Override
	public void onLoadSuccess(Object object) {
	
		ExamResponse examResponse = ((ExamResponse) object);
		if (examResponse == null || examResponse.examList.size() == 0)
			return;
		ds.setExamList(examResponse.examList);
		ds.createMaps(keys);
		ds.notifyDataChange(getActivity());
	}
	
	@Override
	public void onLoadFailed(Throwable throwable) {
	
		FunctionActivity activity = (FunctionActivity) getActivity();
		String TAG = "ExamShowFragment";
		Log.i(TAG, "onLoadFailed");
		if (throwable != null) {
			Log.i(TAG, throwable.getMessage() + "");
		}
		activity.showToast("加载数据失败");
	}
	
	/**
	 * 设置mode
	 * 
	 * @param mode
	 *            的值注意要参考allHeadViewTestId和allTargetActivity两个成员数组，
	 *            就不在FunctionActivity中定义静态的int来标识每种mode了。
	 */
	public void setMode(int mode) {
	
		this.mode = mode;
	}

	/**
	 * 四种HeaderView所使用的文本资源id数组
	 */
	private static final int[] allHeadViewTestId = new int[] { R.array.start,
	        R.array.correct, R.array.comment, R.array.analysis };
	/**
	 * 四中不同的目标Activity
	 */
	private static final Class<?>[] allTargetActivity = new Class[] {
	        SChooseActivity.class, ECorrectActivity.class,
	        PCommentActivity.class, AExpandableActivity.class };
	/**
	 * ExamShowFragment的模式：0-->开始考试;1-->批改试卷;2-->讲评试卷;3-->统计分析试卷;
	 * <p>
	 * 使用mode来决定使用allHeadViewTestId和allTargetActivity两个数组中的值
	 * </p>
	 * Note: mode的值大小必须在0到allHeadViewTestId.length(allTargetAcivity.length),
	 * 程序并未检查mode的值是否合法！
	 * 
	 */
	private int mode = -100;
	private BaseDataAdapter adapter;
	private ExamListDS ds;
	private final String[] keys = new String[] { "ename" };
	private View headerView;
	
	private void addHeaderView(Context context, int res) {
	
		headerView = HeaderViewFactory.createHeaderView1(context, res);
		getListView().addHeaderView(headerView, null, false);
	}
}