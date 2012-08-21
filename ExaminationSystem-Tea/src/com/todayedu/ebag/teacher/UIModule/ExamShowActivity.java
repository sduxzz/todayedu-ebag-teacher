/*
 * ExaminationSystem-Tea com.todayedu.ebag.teacher.UIModule
 * 2012 2012-8-10 上午8:46:34
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.UIModule;

import java.util.List;

import org.ebag.net.response.ExamResponse;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.todayedu.ebag.teacher.Parameters;
import com.todayedu.ebag.teacher.Parameters.ParaIndex;
import com.todayedu.ebag.teacher.R;
import com.todayedu.ebag.teacher.DataAdapter.BaseDataAdapter;
import com.todayedu.ebag.teacher.DataSource.BaseDataSource;
import com.todayedu.ebag.teacher.DataSource.DSCallback;
import com.todayedu.ebag.teacher.DataSource.Data;
import com.todayedu.ebag.teacher.DataSource.ExamListDS;
import com.todayedu.ebag.teacher.Network.ResponeParseUtil;

/**
 * Show exam list which exam is under special state.This activity has five modes
 * that stand for five state of exam.Each mode has its headViewTextId and next
 * target activity Class.
 * 
 * @author zhenzxie
 * 
 */
public class ExamShowActivity extends BaseActivity {
	
	/* target header view's text id */
	static final int[] allHeadViewTestId = new int[] { R.array.choose,
	        R.array.start, R.array.correct, R.array.comment, R.array.analysis };
	/* next target activity's Class */
	private static final Class<?>[] allTargetActivity = new Class[] {
	        CFunctionActivity.class, SChooseActivity.class,
	        ECorrectActivity.class, PCommentActivity.class,
	        AExpandableActivity.class };
	
	private ListView lv;
	private BaseDataAdapter adapter;
	private BaseDataSource ds;

	/*
	 * the mode of this ExamShowActivity;
	 * 0-->ChooseActivity:选择试卷;1-->StartActivity：开始考试的选择;2-->CorrectActivity:批改试卷的选择;
	 * 3-->CommentActivity:讲评试卷的选择;4-->AnalysisActivity：统计分析试卷的选择;
	 * 
	 * use the value of mode to select a headviewId from allHeadViewId 
	 * and target activity's Class from allTargetActivity.
	 * 
	 * Note:mode's value will be set using the value in the
	 * Parameters[ParaIndex.EXAMSHOWACTIVITYMODE],so the value in it must range
	 * from 0 to allHeadViewId.leng,and I didn't check mode's value.
	 */
	int mode = -100;
	
	/**
	 * @see com.todayedu.ebag.teacher.UIModule.MonitoredActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sp);
		mode = Parameters.get(ParaIndex.EXAMSHOWACTIVITYMODE_INDEX);
		/*
		 * TODO:check mode's value whether as the same as the previously when
		 * this activity is re-initialized after previously being shut down. if
		 * not,I must change this method which pass and save the value of mode
		 * from FunctionnActivity
		 */
		Log.i(TAG, "onCreate： mode is " + mode);
		init();
	}
	
	/**
	 * init the lv,adapter,ds
	 */
	protected void init() {

		final String[] keys = new String[] { "ename", "eid" };
		ds = new ExamListDS(new DSCallback() {
			
			@Override
			public void onLoadSuccess(Object object) {
			
				ExamResponse examResponse = (ExamResponse) object;
				final List<Data> list = ResponeParseUtil
				        .parseExamResponse(examResponse);
				ExamShowActivity.this.runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
					
						ds.setList(list);
						ds.createMaps(keys);
						ds.notifyDataChange();
					}
				});
			}
			
			@Override
			public void onLoadFailed(Throwable throwable) {
			
				if (throwable != null) {
					Log.i(TAG, throwable.getMessage());
				}
				showToast("加载数据失败");
			}
		});
		ds.load(this);
		addLifeCycleListener(ds);

		adapter = new BaseDataAdapter(this, ds, R.layout.lv_1,
		        new int[] { R.id.lv1_tv_1 }, keys);
		ds.addObserver(adapter);

		lv = (ListView) findViewById(R.id.el_examlistview);
		View headerView = HeaderViewFactory.createHeaderView1(this,
		        allHeadViewTestId[mode]);
		initListView(lv, headerView, adapter);
		
	}

	public void onExamSynch(View view) {
	
	}
	
	/**
	 * @see com.todayedu.ebag.teacher.UIModule.BaseActivity#onItemClick(android.widget.AdapterView,
	 *      android.view.View, int, long)
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
	        long id) {
	
		String eid = ds.getData().get(position-1).get("eid");
		Parameters.add(eid, ParaIndex.EID_INDEX);
		start(allTargetActivity[mode]);
	}
}