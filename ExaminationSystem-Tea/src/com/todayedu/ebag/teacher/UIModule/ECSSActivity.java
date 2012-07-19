/*
 * ExaminationSystem-Tea com.todayedu.ebag.teacher.UIModule
 * 2012 2012-7-2 ����6:45:31
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.UIModule;

import org.ebag.net.obj.I.choice;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.todayedu.ebag.teacher.Constants.State;
import com.todayedu.ebag.teacher.Constants.StateStr;
import com.todayedu.ebag.teacher.R;
import com.todayedu.ebag.teacher.TempData;

/**
 * @author zhenzxie
 *
 */
public class ECSSActivity extends MonitoredActivity {
	
	private TextView ecss_tv2;
	private TextView ecss_tv4;
	private TextView ecss_tv6;
	private Button ecss_b1;
	private Button ecss_b3;
	
	private String number;
	private String state;
	private String point;

	/**
	 * @see com.todayedu.ebag.teacher.UIModule.MonitoredActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ecss);
		
		ecss_tv2 = (TextView) findViewById(R.id.ecss_tv2);
		ecss_tv4 = (TextView) findViewById(R.id.ecss_tv4);
		ecss_tv6 = (TextView) findViewById(R.id.ecss_tv6);
		ecss_b1 = (Button) findViewById(R.id.ecss_b1);
		ecss_b3 = (Button) findViewById(R.id.ecss_b3);

		getAndSet();
		setButton();

		// ListView1 elView = (ListView1) findViewById(R.id.el_examlistview);
		// elView.setHeaderView(R.array.analysis);
		//
		// BaseDataAdapter adapter = elView.bindAdapter(new PCommentDS(this));
		// addLifeCycleListener(adapter);
	}
	
	public void onPrevious(View view) {
	
		TempData.moveToPrevious();
		getAndSet();
		setButton();
	}
	
	public void onAll(View view) {
	
	}
	
	public void onNext(View view) {
	
		TempData.moveToNext();
		getAndSet();
		setButton();
	}
	
	private void getAndSet() {
	
		number = TempData.getCurrent("number");
		state = TempData.getCurrent("state");
		point = TempData.getCurrent("point");
		
		if (state.equals(String.valueOf(choice.answerState_waitComment))) {
			state = StateStr.COMMENT;
		} else if (state.equals(String.valueOf(State.COMMENTED))) {
			state = StateStr.COMMENTED;
		}
		
		ecss_tv2.setText(number);
		ecss_tv4.setText(state);
		ecss_tv6.setText(point);
	}
	
	public void setButton() {
	
		if (TempData.isFirst())
			ecss_b1.setEnabled(false);
		else
			ecss_b1.setEnabled(true);
		if (TempData.isLast())
			ecss_b3.setEnabled(false);
		else
			ecss_b3.setEnabled(true);
	}
}
