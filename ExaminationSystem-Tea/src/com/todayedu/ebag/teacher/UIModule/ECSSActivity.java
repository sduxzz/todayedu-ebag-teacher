/*
 * ExaminationSystem-Tea com.todayedu.ebag.teacher.UIModule
 * 2012 2012-7-2 锟斤拷锟斤拷6:45:31
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.UIModule;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.todayedu.ebag.teacher.R;
import com.todayedu.ebag.teacher.TempData;

/**
 * 学生某道题的批改界面
 * 
 * @author zhenzxie
 * 
 */
public class ECSSActivity extends BaseActivity {
	
	private TextView number_tv2;
	private TextView score_tv4;
	private TextView state_tv6;
	private Button previous_b1;
	private Button next_b3;
	private WebView content_wv1;
	private WebView answer_wv2;
	// private WebView ecss_wv3;
	private EditText score_et1;
	
	private String number;
	private String state;
	private String point;
	private String content;
	private String answer;

	/**
	 * @see com.todayedu.ebag.teacher.UIModule.MonitoredActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ecss);
		
		number_tv2 = (TextView) findViewById(R.id.ecss_tv2);
		score_tv4 = (TextView) findViewById(R.id.ecss_tv4);
		state_tv6 = (TextView) findViewById(R.id.ecss_tv6);
		previous_b1 = (Button) findViewById(R.id.ecss_b1);
		next_b3 = (Button) findViewById(R.id.ecss_b3);
		content_wv1 = (WebView) findViewById(R.id.ecss_wv1);
		answer_wv2 = (WebView) findViewById(R.id.ecss_wv2);
		// ecss_wv3 = (WebView) findViewById(R.id.ecss_wv3);

		getAndSet();
		setButton();

		// ListView1 elView = (ListView1) findViewById(R.id.el_examlistview);
		// elView.setHeaderView(R.array.analysis);
		//
		// BaseDataAdapter adapter = elView.bindAdapter(new PCommentDS(this));
		// addLifeCycleListener(adapter);
	}
	
	public void onComfire(View view) {
	
		String result = score_et1.getText().toString();
		double score = Double.parseDouble(result);
	}

	public void onPrevious(View view) {
	
		TempData.moveToPrevious();
		getAndSet();
		setButton();
	}
	
	public void onAll(View view) {
	
		this.finish();
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
		content = TempData.getCurrent("content");
		answer = TempData.getCurrent("answer");
		
		number_tv2.setText(number);
		score_tv4.setText(state);
		state_tv6.setText(point);
		content_wv1.loadUrl(content);
		answer_wv2.loadUrl(answer);
	}
	
	public void setButton() {
	
		if (TempData.isFirst())
			previous_b1.setEnabled(false);
		else
			previous_b1.setEnabled(true);
		if (TempData.isLast())
			next_b3.setEnabled(false);
		else
			next_b3.setEnabled(true);
	}
}
