/*
 * ExaminationSystem-Tea com.todayedu.ebag.teacher.UIModule
 * 2012 2012-7-2 ����6:45:31
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.UIModule;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.todayedu.ebag.teacher.Constants.StateStr;
import com.todayedu.ebag.teacher.R;
import com.todayedu.ebag.teacher.TempData;

/**
 * @author zhenzxie
 *
 */
public class PCCActivity extends MonitoredActivity {
	
	private TextView pcc_tv2;
	private TextView pcc_tv4;
	private TextView pcc_tv6;
	private Button pcc_b1;
	private Button pcc_b4;
	
	private String number;
	private String state;
	private String point;

	/**
	 * @see com.todayedu.ebag.teacher.UIModule.MonitoredActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pcc);
		
		pcc_tv2 = (TextView) findViewById(R.id.pcc_tv2);
		pcc_tv4 = (TextView) findViewById(R.id.pcc_tv4);
		pcc_tv6 = (TextView) findViewById(R.id.pcc_tv6);
		pcc_b1 = (Button) findViewById(R.id.pcc_b1);
		pcc_b4 = (Button) findViewById(R.id.pcc_b4);
		getAndSet();
		setButton();
		
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
	
	public void onLabel(View view) {
	
		TempData.setState(StateStr.COMMENTED);
		if (!TempData.isLast()) {
			onNext(pcc_b4);
		}
	}
	
	public void onNext(View view) {
	
		TempData.moveToNext();
		getAndSet();
		setButton();
	}
	
	public void setButton() {
	
		if (TempData.isFirst())
			pcc_b1.setEnabled(false);
		else
			pcc_b1.setEnabled(true);
		if (TempData.isLast())
			pcc_b4.setEnabled(false);
		else
			pcc_b4.setEnabled(true);
	}
	
	private void getAndSet() {
	
		number = TempData.getCurrent("number");
		state = TempData.getCurrent("state");
		point = TempData.getCurrent("point");

		pcc_tv2.setText(number);
		pcc_tv4.setText(state);
		pcc_tv6.setText(point);
	}
}
