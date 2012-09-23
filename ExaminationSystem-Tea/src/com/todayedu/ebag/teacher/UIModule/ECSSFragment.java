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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.todayedu.ebag.teacher.R;
import com.todayedu.ebag.teacher.TempData;

/**
 * 学生某道题的批改界面
 * 
 * @author <a href="zhenzxie.iteye.cn">zhenzxie</a>
 * @version 1.0
 * @since 1.0
 */
public class ECSSFragment extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	        Bundle savedInstanceState) {
	
		return inflater.inflate(R.layout.ecss, container,
		        false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
	
		super.onActivityCreated(savedInstanceState);
		findView();
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
	
	public void getAndSet() {
	
		number = TempData.getCurrent("number");
		score = TempData.getCurrent("score");
		state = TempData.getCurrent("state");
		content = TempData.getCurrent("content");
		answerofSta = TempData.getCurrent("answerofSta");
		answerofStu = TempData.getCurrent("answerofStu");
		
		number_tv2.setText(number);
		score_tv4.setText(score);
		state_tv6.setText(state);
		content_wv1.loadUrl(content);
		answer_wv2.loadUrl(answerofSta);
		// answer_iv.setImageBitmap(BitMapUtils.loadFromSdCard(answerofStu));
		setPaintView();
	}
	
	public String getPoint() {
	
		return score_et1.getText().toString();// may be nullpointerexception
	}
	
	public String getPicOfTeacherUrl() {
	
		return null;
	}
	
	public String getTextOfTeacher() {
	
		return null;
	}


	private TextView number_tv2;
	private TextView score_tv4;
	private TextView state_tv6;
	private ImageButton previous_b1;
	private ImageButton next_b3;
	private WebView content_wv1;
	private WebView answer_wv2;
	private EditText score_et1;
	private ImageView answer_iv;
	private LinearLayout paint_ll;
	private LinearLayout layout;
	
	private PaintView mPaintView = null;
	
	private String number;
	private String state;
	private String score;
	private String content;
	private String answerofSta;
	private String answerofStu;
	
	private void findView() {
	
		Activity activity = getActivity();
		
		number_tv2 = (TextView) activity.findViewById(R.id.ecss_tv2);
		score_tv4 = (TextView) activity.findViewById(R.id.ecss_tv4);
		state_tv6 = (TextView) activity.findViewById(R.id.ecss_tv6);
		previous_b1 = (ImageButton) activity.findViewById(R.id.ecss_b1);
		next_b3 = (ImageButton) activity.findViewById(R.id.ecss_b3);
		content_wv1 = (WebView) activity.findViewById(R.id.ecss_wv1);
		answer_wv2 = (WebView) activity.findViewById(R.id.ecss_wv2);
		answer_iv = (ImageView) activity.findViewById(R.id.ecss_ll_iv);
		paint_ll = (LinearLayout) activity.findViewById(R.id.ecss_ll_ll);
		layout = (LinearLayout) activity.findViewById(R.id.ecss_ll);
		layout.setDrawingCacheQuality(LinearLayout.DRAWING_CACHE_QUALITY_HIGH);
	}
	
	private void setPaintView() {
		
	}
}