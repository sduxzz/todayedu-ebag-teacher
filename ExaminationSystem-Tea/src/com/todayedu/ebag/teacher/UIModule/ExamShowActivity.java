/*
 * ExaminationSystem-Tea com.todayedu.ebag.teacher.UIModule
 * 2012 2012-8-10 上午8:46:34
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.UIModule;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.todayedu.ebag.teacher.Parameters;
import com.todayedu.ebag.teacher.Parameters.ParaIndex;
import com.todayedu.ebag.teacher.R;
import com.todayedu.ebag.teacher.DataAdapter.BaseDataAdapter;
import com.todayedu.ebag.teacher.DataSource.BaseDS;
import com.todayedu.ebag.teacher.DataSource.ExamListDS;
import com.todayedu.ebag.teacher.DataSource.DataObj.Exam;

/**
 * Show exam list which exam is under special state.This activity has five modes
 * that stand for five state of exam.Each mode has its headViewTextId and next
 * target activity Class.
 * 
 * @author zhenzxie
 * 
 */
public class ExamShowActivity extends MonitoredActivity {
	
	/* target header view's text id */
	private static final int[] allHeadViewTestId = new int[] { R.array.choose,
	        R.array.start, R.array.correct, R.array.comment, R.array.analysis };
	/* next target activity's Class */
	private static final Class<?>[] allTargetActivity = new Class[] {
	        CFunctionActivity.class, SChooseActivity.class,
	        ECorrectActivity.class, PCommentActivity.class,
	        AExpandableActivity.class };
	
	private BaseDataAdapter adapter;

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
	private int mode;
	
	/**
	 * @see com.todayedu.ebag.teacher.UIModule.MonitoredActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sp);
		mode = Parameters.get(ParaIndex.EXAMSHOWACTIVITYMODE);
		/*
		 * TODO:check mode's value whether as the same as the previously when
		 * this activity is re-initialized after previously being shut down. if
		 * not,I must change this method which pass and save the value of mode
		 * from FunctionnActivity
		 */
		Log.i(TAG, "onCreate mode is " + mode);
		init();
	}
	
	/**
	 * init the listView1,adapter,ds
	 */
	private void init() {
	
		ListView1 elView = (ListView1) findViewById(R.id.el_examlistview);
		elView.setHeaderView(allHeadViewTestId[mode]);
		elView.setOnItemClickListener(this);
		
		String[] keys = new String[] { "ename", "eid" };
		BaseDS ds = new ExamListDS(Exam.class);
		ds.load(this);
		adapter = elView.bindAdapter(ds, keys);
		addLifeCycleListener(adapter);
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
	
		String eid = adapter.getzDataSource().pick().get(position - 1)
		        .valueOfKey("eid");
		Parameters.add(eid, ParaIndex.EID_INDEX);
		start(allTargetActivity[mode]);
	}
}