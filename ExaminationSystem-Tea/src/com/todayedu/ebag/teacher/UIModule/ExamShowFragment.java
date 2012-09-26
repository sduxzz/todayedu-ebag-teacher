/*
 * ExaminationSystem-Tea com.todayedu.ebag.teacher.UIModule
 * 2012 2012-9-20 ����9:35:15
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
 * ���ֲ�ͬģʽ���Ծ��б���Ӧ���Ų�ͬ��headerview�����ı�����ת����ͬ��Activity��
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
	 * �����Ծ�����
	 * 
	 * @param activity
	 */
	public void load(FunctionActivity activity) {

		if (ds != null) {
			ds.load(activity);
		}
	}
	
	/**
	 * �޸�HeaderView
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
		activity.showToast("��������ʧ��");
	}
	
	/**
	 * ����mode
	 * 
	 * @param mode
	 *            ��ֵע��Ҫ�ο�allHeadViewTestId��allTargetActivity������Ա���飬
	 *            �Ͳ���FunctionActivity�ж��徲̬��int����ʶÿ��mode�ˡ�
	 */
	public void setMode(int mode) {
	
		this.mode = mode;
	}

	/**
	 * ����HeaderView��ʹ�õ��ı���Դid����
	 */
	private static final int[] allHeadViewTestId = new int[] { R.array.start,
	        R.array.correct, R.array.comment, R.array.analysis };
	/**
	 * ���в�ͬ��Ŀ��Activity
	 */
	private static final Class<?>[] allTargetActivity = new Class[] {
	        SChooseActivity.class, ECorrectActivity.class,
	        PCommentActivity.class, AExpandableActivity.class };
	/**
	 * ExamShowFragment��ģʽ��0-->��ʼ����;1-->�����Ծ�;2-->�����Ծ�;3-->ͳ�Ʒ����Ծ�;
	 * <p>
	 * ʹ��mode������ʹ��allHeadViewTestId��allTargetActivity���������е�ֵ
	 * </p>
	 * Note: mode��ֵ��С������0��allHeadViewTestId.length(allTargetAcivity.length),
	 * ����δ���mode��ֵ�Ƿ�Ϸ���
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