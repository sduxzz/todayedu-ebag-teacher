/*
 * ExaminationSystem-Tea com.todayedu.ebag.teacher.UIModule
 * 2012 2012-9-20 下午9:35:15
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.UIModule;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.todayedu.ebag.teacher.AsyncImageLoader;
import com.todayedu.ebag.teacher.AsyncImageLoader.ImageLoadListener;
import com.todayedu.ebag.teacher.Constants.PATH;
import com.todayedu.ebag.teacher.Parameters;
import com.todayedu.ebag.teacher.Parameters.ParaIndex;
import com.todayedu.ebag.teacher.R;
import com.todayedu.ebag.teacher.DataSource.DataObj.Answer;
import com.todayedu.ebag.teacher.UIModule.paintpad.PaintPadViewCreator;
import com.todayedu.ebag.teacher.UIModule.paintpad.PicUpload;

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
	
		return inflater.inflate(R.layout.ecss, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
	
		super.onActivityCreated(savedInstanceState);
		findView();
		initPaintView();
	}
	
	public boolean onConfirm() {
	
		picOfTeacherUrl = PicUpload.getFileName(Parameters
		        .get(ParaIndex.SID_INDEX));// 获取文件名
		return creator.onClickButtonSave("/ebag", picOfTeacherUrl);
	}

	public void resetECSS(Answer answer, boolean canPrevious, boolean canNext) {
	
		if (answer == null)
			return;

		number = String.valueOf(answer.getNumber());
		score = String.valueOf(answer.getScore());
		state = answer.getState();
		content = answer.getContent();
		answerofSta = answer.getAnswerofSta();
		picOfTeacherUrl = answer.getAnswerofTea();
		answerofStu = answer.getAnswerofStu();
		textAnswer = answer.getTextAnswer();
		point = String.valueOf(answer.getPoint());
		textOfTeacher = answer.getTextOfTeacher() + "";
		
		number_tv2.setText(number);
		score_tv4.setText(score);
		state_tv6.setText(state);
		content_wv1.loadUrl(content);
		answer_wv2.loadUrl(answerofSta);
		answer_tv10.setText(textAnswer);
		score_et1.setText(point);
		textofTeacher_et2.setText(textOfTeacher);
		setButton(canPrevious, canNext);

		if (answerofStu != null && !answerofStu.equals("")) {
			container_ll.setVisibility(View.VISIBLE);
			if (picOfTeacherUrl != null) {
				creator.loadExsitedImage(PATH.DIR_PATH + "/" + picOfTeacherUrl);
			} else {
				imageLoader.loadImageBitmap(answerofStu,
				        new ImageLoadListener() {
					        
					        @Override
					        public void onImageBitmapLoaded(Bitmap bitmap) {
					        
						        Log.i("ECSSFragment", answerofStu);
						        creator.setbg(bitmap);
					        }
					        
					        @Override
					        public void onLoadFailed() {
					        
						        container_ll.setVisibility(View.GONE);
					        }
				        });
			}
		} else {
			container_ll.setVisibility(View.GONE);
		}
	}
	
	public String getPoint() {
	
		return score_et1.getText().toString();
	}
	
	public String getPicOfTeacherUrl() {
	
		return picOfTeacherUrl;
	}
	
	public String getTextOfTeacher() {
	
		return textofTeacher_et2.getText().toString();
	}

	private TextView number_tv2;
	private TextView score_tv4;
	private TextView state_tv6;
	private TextView answer_tv10;
	private ImageButton previous_b1;
	private ImageButton next_b3;
	private WebView content_wv1;
	private WebView answer_wv2;
	private EditText score_et1;
	private EditText textofTeacher_et2;
	private LinearLayout container_ll;
	
	private PaintPadViewCreator creator;

	private String number;
	private String state;
	private String score;// 题目总分
	private String point;// 学生的得分
	private String content;
	private String answerofSta;
	private String answerofStu;
	private String textAnswer;// 学生的文字回答
	private String textOfTeacher;// 老师的批改
	private String picOfTeacherUrl;
	
	private AsyncImageLoader imageLoader = new AsyncImageLoader();

	private void findView() {
	
		Activity activity = getActivity();
		number_tv2 = (TextView) activity.findViewById(R.id.ecss_tv2);
		score_tv4 = (TextView) activity.findViewById(R.id.ecss_tv4);
		state_tv6 = (TextView) activity.findViewById(R.id.ecss_tv6);
		answer_tv10 = (TextView) activity.findViewById(R.id.ecss_tv10);
		previous_b1 = (ImageButton) activity.findViewById(R.id.ecss_b1);
		next_b3 = (ImageButton) activity.findViewById(R.id.ecss_b3);
		content_wv1 = (WebView) activity.findViewById(R.id.ecss_wv1);
		answer_wv2 = (WebView) activity.findViewById(R.id.ecss_wv2);
		score_et1 = (EditText) activity.findViewById(R.id.ecss_et1);
		textofTeacher_et2 = (EditText) activity.findViewById(R.id.ecss_et2);
		container_ll = (LinearLayout) activity.findViewById(R.id.ecss_ll);
	}
	
	private void initPaintView() {
	
		creator = new PaintPadViewCreator((BaseActivity) getActivity(),
		        container_ll);
		View v = creator.getView();
		v.setFocusable(true);
		v.setFocusableInTouchMode(true);
	}
	
	private void setButton(boolean canPrevious, boolean canNext) {
	
		if (canPrevious)
			previous_b1.setEnabled(true);
		else
			previous_b1.setEnabled(false);
		if (canNext)
			next_b3.setEnabled(true);
		else
			next_b3.setEnabled(false);
	}
}