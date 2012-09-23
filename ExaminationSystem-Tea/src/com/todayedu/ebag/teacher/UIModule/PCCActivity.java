/*
 * ExaminationSystem-Tea com.todayedu.ebag.teacher.UIModule
 * 2012 2012-7-2 ����6:45:31
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.UIModule;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import com.todayedu.ebag.teacher.R;
import com.todayedu.ebag.teacher.TempData;

/**
 * @author zhenzxie
 * @deprecated
 * @see PCCFragment
 * 
 */
public class PCCActivity extends BaseActivity {
	
	private TextView pcc_tv2;
	private TextView pcc_tv4;
	private TextView pcc_tv6;
	private Button pcc_b1;
	private Button pcc_b4;
	private WebView pcc_wv1;
	private WebView pcc_wv2;
	private WebView pcc_wv3;
	
	private String number;
	private String state;
	private String point;
	private String content;
	private String answer;
	private String analysis;

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
		pcc_wv1 = (WebView) findViewById(R.id.pcc_wv1);
		pcc_wv2 = (WebView) findViewById(R.id.pcc_wv2);
		pcc_wv3 = (WebView) findViewById(R.id.pcc_wv3);
		
		getAndSet();
		setButton();
		
	}
	
	public void onPrevious(View view) {

		TempData.moveToPrevious();
		getAndSet();
		setButton();
	}
	
	public void onAll(View view) {
	
		this.finish();
	}
	
	public void onLabel(View view) {
	
		// TempData.setState(StateStr.COMMENTED);
		// if (!TempData.isLast()) {
		// onNext(pcc_b4);
		// }
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
		point = TempData.getCurrent("point");
		state = TempData.getCurrent("state");
		content = TempData.getCurrent("content");
		answer = TempData.getCurrent("answer");
		analysis = TempData.getCurrent("analysis");

		pcc_tv2.setText(number);
		pcc_tv4.setText(point);
		pcc_tv6.setText(state);
		pcc_wv1.loadUrl(content);
		pcc_wv2.loadUrl(answer);
		pcc_wv3.loadUrl(analysis);
	}
}
