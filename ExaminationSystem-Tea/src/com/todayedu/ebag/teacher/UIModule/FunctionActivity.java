/*
 * ExaminationSystem-Tea java.net
 * 2012 2012-8-10 上午9:35:44
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.UIModule;

import java.util.ArrayList;
import java.util.Arrays;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.todayedu.ebag.teacher.Parameters;
import com.todayedu.ebag.teacher.Parameters.ParaIndex;
import com.todayedu.ebag.teacher.R;
import com.todayedu.ebag.teacher.DataSource.DataObj.EClass;

/**
 * <p>
 * 功能选择界面。这个界面提供班级的选择功能和我的试卷，开始考试等等五个不同功能的入口
 * </p>
 * <p>
 * 在这个Activity里必须使用{@link ParaIndex}来将(cid,examstate)这两个参数设置到 {@link Parameters}
 * 中去，这些参数后面被大量使用到，为了方便，所以将他们放到全局变量中。
 * </p>
 * 
 * @author zhenzxie
 * 
 */
public class FunctionActivity extends BaseActivity {
	
	/* target header view's text id */
	private static final int[] allHeadViewTestId = new int[] { R.array.choose,
	        R.array.start, R.array.correct, R.array.comment, R.array.analysis };
	/* next target activity's Class */
	private static final Class<?>[] allTargetActivity = new Class[] {
	        CFunctionActivity.class, SChooseActivity.class,
	        ECorrectActivity.class, PCommentActivity.class,
	        AExpandableActivity.class };
	
	/**
	 * ExamShowActivity的模式： 0-->我的试卷;1-->开始考试;2-->批改试卷;3-->讲评试卷;4-->统计分析试卷;
	 * <p>
	 * 使用mode来决定使用allHeadViewTestId和allTargetActivity两个数组中的值
	 * </p>
	 * Note: mode的值大小必须在0到allHeadViewTestId.length(allTargetAcivity.length),
	 * 程序并未检查mode的值是否合法！
	 * 
	 */
	private int mode = -100;

	/**
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.function);

		if (savedInstanceState != null) {// 班级不多，所以放到主线程中来做班级名字解析工作
			names = getNames(savedInstanceState);
		} else {
			names = getNames(getIntent().getExtras());
		}
		classid = -1000;
		initSpinner(names);
		Log.i(TAG, "onCreate:" + Arrays.toString(names));
	}
	
	/**
	 * @see android.app.Activity#onSaveInstanceState(android.os.Bundle)
	 */
	@Override
	public void onSaveInstanceState(Bundle outState) {
	
		outState.putSerializable("classIdList", list);
		Log.i(TAG, "onSaveInstanceState save the list");
		super.onSaveInstanceState(outState);
	}
	
	/**
	 * @param examState
	 * @param mode
	 *            的值注意要参考allHeadViewTestId和allTargetActivity两个成员数组，
	 *            就不在FunctionActivity中定义静态的int来标识每种mode了。
	 * 
	 */
	public void examShow(int examState, int mode) {
	
		if (classid == -100) {
			showToast("请先选择班级");
			return;
		}
		Parameters.add(examState, ParaIndex.EXAMSTATE_INDEX);
		this.mode = mode;
		ExamShowFragment fragment = (ExamShowFragment) getFragmentManager()
		        .findFragmentById(R.id.fl_examshow);
		fragment.changeHeaderView(this, allHeadViewTestId[mode]);
		fragment.load(this);
	}
	
	/**
	 * 开启目标的Activity
	 */
	public void start() {
	
		start(allTargetActivity[mode]);
	}

	private String[] names = null;
	private int classid = -100;
	private ArrayList<EClass> list;
	private ArrayAdapter<String> adapter;
	private Spinner spinner;

	/**
	 * @param names
	 */
	private void initSpinner(final String[] names) {
	
		adapter = new ArrayAdapter<String>(this,
		        android.R.layout.simple_spinner_item, names);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner = (Spinner) findViewById(R.id.fl_sp);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
			        int position, long id) {
			
				Object object = spinner.getItemAtPosition(position);
				
				if (object != null) {
					final int cid = list.get(position).getCid();
					classid = cid;
					Log.i(TAG, "onItemSelected: cid is " + cid
					        + " position is " + position);
					Parameters.add(cid, ParaIndex.CID_INDEX);
					Parameters.setClassName(names[position]);
				}
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			
			}
		});
		spinner.setVisibility(View.VISIBLE);
		if (names.length != 0) {
			spinner.setSelection(0);// defalut select
		}
	}
	
	/**
	 * get the all classes name from bundle
	 * 
	 * @param bundle
	 * @return name array
	 */
	@SuppressWarnings("unchecked")
	private String[] getNames(Bundle bundle) {
	
		if (bundle == null)
			throw new IllegalArgumentException();
		ArrayList<EClass> list = null;
		String[] names = null;
		try {
			list = (ArrayList<EClass>) bundle.getSerializable("classIdList");
			names = new String[list.size()];
			int i = 0;
			for (EClass ec : list) {
				names[i++] = ec.getCname();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.list = list;
		return names;
	}
	
	// /**
	// * @see android.app.Activity#onRestoreInstanceState(android.os.Bundle)
	// */
	// @Override
	// public void onRestoreInstanceState(Bundle savedInstanceState) {
	//
	// if (savedInstanceState == null) {
	// Log.i(TAG, "onRestoreInstanceState savedInstanceState is null");
	// return;
	// } else {
	// // TODO:restore the classes' name
	// Log.i(TAG, "onRestoreInstanceState restore the classes' name");
	// }
	// classid = -1000;
	// super.onRestoreInstanceState(savedInstanceState);
	// }
	
}