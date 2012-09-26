/*
 * ExaminationSystem-Tea java.net
 * 2012 2012-8-10 上午9:35:44
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.UIModule;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.todayedu.ebag.teacher.Parameters;
import com.todayedu.ebag.teacher.Parameters.ParaIndex;
import com.todayedu.ebag.teacher.R;

import ebag.pojo.Eclass;

/**
 * <p>
 * 功能选择界面。这个界面提供班级的选择功能和我的试卷，开始考试等等五个不同功能的入口和试卷选择界面
 * </p>
 * <p>
 * 在这个Activity里必须使用{@link ParaIndex}来将(cid,examstate,classname)这三个参数设置到
 * {@link Parameters} 中去，这些参数后面被大量使用到，为了方便，所以将他们放到全局变量中。
 * </p>
 * 
 * @author zhenzxie
 * @see FunctionChooseFragment
 * @see ExamShowFragment
 * 
 */
public class FunctionActivity extends BaseActivity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.function);
		String[] names = null;
		if (savedInstanceState != null) {// 班级不多，所以放到主线程中来做班级名字解析工作
			names = getNames(savedInstanceState);
		} else {
			names = getNames(getIntent().getExtras());
		}
		initSpinner(names);
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
	
		outState.putSerializable("classList", list);
		super.onSaveInstanceState(outState);
	}
	
	/**
	 * <p>
	 * 这是一个中介方法
	 * </p>
	 * 通知ExamShowFragment转换到mode指定的模式，显示特定状态的试卷
	 * 
	 * @param examState
	 * @param mode
	 * 
	 */
	public void examShow(int examState, int mode) {
	
		if (classid <= -1) {
			showToast("请先选择班级");
			return;
		}
		Parameters.add(examState, ParaIndex.ESTATE_INDEX);
		ExamShowFragment fragment = (ExamShowFragment) getFragmentManager()
		        .findFragmentById(R.id.fl_examshow);
		fragment.setMode(mode);
		fragment.changeHeaderView(this);
		fragment.load(this);
	}
	
	private int classid = -100;
	private ArrayList<Eclass> list = null;

	/**
	 * @param names
	 */
	private void initSpinner(final String[] names) {
	
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		        android.R.layout.simple_spinner_item, names);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		Spinner spinner = (Spinner) findViewById(R.id.fl_sp);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
			        int position, long id) {
				
				if (position >= 0) {
					classid = list.get(position).getId();
					Parameters.add(classid, ParaIndex.CID_INDEX);
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

		String[] names = null;
		try {
			list = (ArrayList<Eclass>) bundle.getSerializable("classList");
			names = new String[list.size()];
			int i = 0;
			for (Eclass ec : list) {
				names[i++] = ec.getClassname();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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