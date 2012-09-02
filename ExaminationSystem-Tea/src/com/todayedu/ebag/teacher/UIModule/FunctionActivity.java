/*
 * ExaminationSystem-Tea java.net
 * 2012 2012-8-10 上午9:35:44
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.UIModule;

import java.util.ArrayList;
import java.util.Arrays;

import org.ebag.net.obj.I.choice;

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
 * the activity suply a spinner and five buttons for teacher to select the name
 * of class and the function she will be use later. NOTO:this activity must
 * store the parameters(cid,examstate,mode) to {@link Parameters} using the
 * specified index on {@link ParaIndex},and these Parameters will be used in
 * {@link ExamShowActivity}
 * 
 * @author zhenzxie
 * 
 */
public class FunctionActivity extends BaseActivity implements OnItemSelectedListener {

	private String[] names = null;
	/**
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.function);

		if (savedInstanceState != null) {
			names = getNames(savedInstanceState);
		} else {
			names = getNames(getIntent().getExtras());
		}
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
	 * @see android.app.Activity#onRestoreInstanceState(android.os.Bundle)
	 */
	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
	
		if (savedInstanceState == null) {
			Log.i(TAG, "onRestoreInstanceState savedInstanceState is null");
			return;
		} else {
			// TODO:restore the classes' name
			Log.i(TAG, "onRestoreInstanceState restore the classes' name");
		}
		classid = -1000;
		super.onRestoreInstanceState(savedInstanceState);
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
	        long id) {
	
		Object object = spinner.getItemAtPosition(position);
		
		if (object != null) {
			final int cid = list.get(position).getCid();
			classid = cid;
			Log.i(TAG, "onItemSelected: cid is " + cid + " position is "
			        + position);
			// add cid to Parameters
			Parameters.add(cid, ParaIndex.CID_INDEX);
			Parameters.setClassName(names[position]);
		}

	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
	
	}

	public void onChoose(View view) {
	
		/* 0 stand for that all exam will be selected,not matter the state of exam */
		jumpTo(0, 0);
	}

	public void onStart(View view) {
    
		jumpTo(choice.answerState_waitAnser, 1);
    }

	public void onCorrect(View view) {
    
		jumpTo(choice.answerState_waitMark, 2);
    }

	public void onComment(View view) {
    
		jumpTo(choice.answerState_waitComment, 3);
    }

	public void onAnalysis(View view) {
    
		jumpTo(choice.answerState_finish, 4);
    }

	private int classid = -100;
	private ArrayList<EClass> list;
	private ArrayAdapter<String> adapter;
	private Spinner spinner;
	
	/**
	 * @param names
	 */
	private void initSpinner(String[] names) {
	
		spinner = (Spinner) findViewById(R.id.fl_sp);
		if (names.length != 0) {
			spinner.setSelection(0);// defalut select
		}
		adapter = new ArrayAdapter<String>(this,
		        android.R.layout.simple_spinner_item, names);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(this);
		spinner.setVisibility(View.VISIBLE);
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
	
	/**
	 * @param examState
	 * @param mode
	 * 
	 */
	private void jumpTo(int examState, int mode) {
	
		if (classid == -100) {
			showToast("请先选择班级");
			return;
		}
		Parameters.add(examState, ParaIndex.EXAMSTATE_INDEX);
		Parameters.add(mode, ParaIndex.EXAMSHOWACTIVITYMODE_INDEX);
		start(ExamShowActivity.class);
		Log.i(TAG,
		        "jumpTo the class id is:" + Parameters.get(ParaIndex.CID_INDEX)
		                + " exam's state is:"
		                + Parameters.get(ParaIndex.EXAMSTATE_INDEX)
		                + " mode's values is:"
		                + Parameters.get(ParaIndex.EXAMSHOWACTIVITYMODE_INDEX));
	}
}