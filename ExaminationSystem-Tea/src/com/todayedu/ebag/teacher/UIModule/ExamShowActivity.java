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
import com.todayedu.ebag.teacher.DataSource.Data;
import com.todayedu.ebag.teacher.DataSource.ExamListDS;
import com.todayedu.ebag.teacher.DataSource.DataObj.Exam;
import com.todayedu.ebag.teacher.Network.ResponseParseUtil;

/**
 * 五种不同模式的试卷列表，对应有着不同的headerview内容文本，跳转到不同的Activity
 * 
 * @author zhenzxie
 * @deprecated
 * @see ExamShowFragment
 */
public class ExamShowActivity extends BaseActivity {
	
	/* target header view's text id */
	private static final int[] allHeadViewTestId = new int[] { R.array.choose,
	        R.array.start, R.array.correct, R.array.comment, R.array.analysis };
	/* next target activity's Class */
	private static final Class<?>[] allTargetActivity = new Class[] {
	        CFunctionActivity.class, SChooseActivity.class,
	        ECorrectActivity.class, PCommentActivity.class,
	        AExpandableActivity.class };
	
	private ListView lv;
	private BaseDataAdapter adapter;
	private BaseDataSource ds;
	private final String[] keys = new String[] { "ename", "eid" };

	/**
	 * ExamShowActivity的模式： 0-->我的试卷;1-->开始考试;2-->批改试卷;3-->讲评试卷;4-->统计分析试卷;
	 * <p>
	 * 使用mode来决定使用allHeadViewTestId和allTargetActivity两个数组中的值
	 * </p>
	 * Note: mode的值使用Parameters[ParaIndex.EXAMSHOWACTIVITYMODE]里的值来设置,
	 * 并且大小必须在0到allHeadViewTestId
	 * .length(allTargetAcivity.length),程序并未检查mode的值是否合法！
	 * 
	 * @see Parameters
	 * @see ParaIndex
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
		Log.i(TAG, "onCreate： mode is " + mode);// TODO：检查mode的值在Activity重启的时候是否有一致性
		init();
	}
	
	/**
	 * init the lv,adapter,ds
	 */
	protected void init() {

		ds = new ExamListDS(this);
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
	
		Exam exam = (Exam) ds.getList().get(position - 1);
		int eid = exam.getEid();
		Parameters.add(eid, ParaIndex.EID_INDEX);
		Parameters.setExam(exam);
		start(allTargetActivity[mode]);
	}
	
	@Override
	public void onLoadSuccess(Object object) {
	
		super.onLoadSuccess(object);
		ExamResponse examResponse = (ExamResponse) object;
		final List<Data> list = ResponseParseUtil
		        .parseExamResponse(examResponse);
		ds.setList(list);
		ds.createMaps(keys);
		ExamShowActivity.this.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
			
				ds.notifyDataChange();
			}
		});
	}
}