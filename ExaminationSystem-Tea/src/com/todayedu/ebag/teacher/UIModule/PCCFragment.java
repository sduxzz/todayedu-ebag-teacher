/*
 * ExaminationSystem-Tea com.todayedu.ebag.teacher.UIModule
 * 2012 2012-9-20 下午9:35:15
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.UIModule;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.todayedu.ebag.teacher.R;
import com.todayedu.ebag.teacher.TempData;
import com.todayedu.ebag.teacher.DataSource.DataObj.Problem;

/**
 * 讲评试卷界面中的题目界面
 * 
 * @author <a href="zhenzxie.iteye.cn">zhenzxie</a>
 * @version 1.0
 * @since 1.0
 */
public class PCCFragment extends Fragment {
	
	/**
	 * @see android.app.ListFragment#onCreateView(android.view.LayoutInflater,
	 *      android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	        Bundle savedInstanceState) {
	
		return inflater.inflate(R.layout.pcc, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
	
		super.onActivityCreated(savedInstanceState);
		
		Activity activity = getActivity();
		
		pcc_tv2 = (TextView) activity.findViewById(R.id.pcc_tv2);
		pcc_tv4 = (TextView) activity.findViewById(R.id.pcc_tv4);
		pcc_tv6 = (TextView) activity.findViewById(R.id.pcc_tv6);
		pcc_b1 = (ImageButton) activity.findViewById(R.id.pcc_b1);
		pcc_b4 = (ImageButton) activity.findViewById(R.id.pcc_b4);
		pcc_wv1 = (WebView) activity.findViewById(R.id.pcc_wv1);
		pcc_wv2 = (WebView) activity.findViewById(R.id.pcc_wv2);
		pcc_wv3 = (WebView) activity.findViewById(R.id.pcc_wv3);
		
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
	
	public void getAndSet() {
		
		Problem problem = (Problem) TempData.getCurrentData();
		if (problem == null)
			return;

		number = problem.getNumber() + "";
		point = problem.getPoint() + "";
		state = problem.getState();
		content = problem.getContent();
		answer = problem.getAnswer();
		analysis = problem.getAnalysis();

		pcc_tv2.setText(number);
		pcc_tv4.setText(point);
		pcc_tv6.setText(state);
		pcc_wv1.loadUrl(content);
		pcc_wv2.loadUrl(answer);
		pcc_wv3.loadUrl(analysis);
	}
	
	private TextView pcc_tv2;
	private TextView pcc_tv4;
	private TextView pcc_tv6;
	private ImageButton pcc_b1;
	private ImageButton pcc_b4;
	private WebView pcc_wv1;
	private WebView pcc_wv2;
	private WebView pcc_wv3;
	
	private String number;
	private String state;
	private String point;
	private String content;
	private String answer;
	private String analysis;
	
}