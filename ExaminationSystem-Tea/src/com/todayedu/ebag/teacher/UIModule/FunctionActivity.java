/*
 * ExaminationSystem-Tea java.net
 * 2012 2012-8-10 ����9:35:44
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
 * ����ѡ����档��������ṩ�༶��ѡ���ܺ��ҵ��Ծ���ʼ���Եȵ������ͬ���ܵ���ں��Ծ�ѡ�����
 * </p>
 * <p>
 * �����Activity�����ʹ��{@link ParaIndex}����(cid,examstate,classname)�������������õ�
 * {@link Parameters} ��ȥ����Щ�������汻����ʹ�õ���Ϊ�˷��㣬���Խ����Ƿŵ�ȫ�ֱ����С�
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
		if (savedInstanceState != null) {// �༶���࣬���Էŵ����߳��������༶���ֽ�������
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
	 * ����һ���н鷽��
	 * </p>
	 * ֪ͨExamShowFragmentת����modeָ����ģʽ����ʾ�ض�״̬���Ծ�
	 * 
	 * @param examState
	 * @param mode
	 * 
	 */
	public void examShow(int examState, int mode) {
	
		if (classid <= -1) {
			showToast("����ѡ��༶");
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