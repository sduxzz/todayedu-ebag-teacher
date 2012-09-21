/*
 * ExaminationSystem-Tea java.net
 * 2012 2012-8-10 ����9:35:44
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
 * ����ѡ����档��������ṩ�༶��ѡ���ܺ��ҵ��Ծ���ʼ���Եȵ������ͬ���ܵ����
 * </p>
 * <p>
 * �����Activity�����ʹ��{@link ParaIndex}����(cid,examstate)�������������õ� {@link Parameters}
 * ��ȥ����Щ�������汻����ʹ�õ���Ϊ�˷��㣬���Խ����Ƿŵ�ȫ�ֱ����С�
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
	 * ExamShowActivity��ģʽ�� 0-->�ҵ��Ծ�;1-->��ʼ����;2-->�����Ծ�;3-->�����Ծ�;4-->ͳ�Ʒ����Ծ�;
	 * <p>
	 * ʹ��mode������ʹ��allHeadViewTestId��allTargetActivity���������е�ֵ
	 * </p>
	 * Note: mode��ֵ��С������0��allHeadViewTestId.length(allTargetAcivity.length),
	 * ����δ���mode��ֵ�Ƿ�Ϸ���
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

		if (savedInstanceState != null) {// �༶���࣬���Էŵ����߳��������༶���ֽ�������
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
	 *            ��ֵע��Ҫ�ο�allHeadViewTestId��allTargetActivity������Ա���飬
	 *            �Ͳ���FunctionActivity�ж��徲̬��int����ʶÿ��mode�ˡ�
	 * 
	 */
	public void examShow(int examState, int mode) {
	
		if (classid == -100) {
			showToast("����ѡ��༶");
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
	 * ����Ŀ���Activity
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