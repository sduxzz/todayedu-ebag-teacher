/*
 * ExaminationSystem-Tea com.todayedu.examinationsystem.teacher.UIModule
 * 2012 2012-5-29 ÏÂÎç11:20:16
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.UIModule;

import org.ebag.net.obj.I.choice;

import android.os.Bundle;
import android.view.View;

import com.todayedu.ebag.teacher.Parameters;
import com.todayedu.ebag.teacher.Parameters.ParaIndex;
import com.todayedu.ebag.teacher.R;

/**
 * this is the activity supply function list for user
 * 
 * @author zhenzxie
 * 
 */
public class FunctionActivity extends BaseActivity {
	
	/**
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.function);
	}
	
	public void onChoose(View view) {
	
		Parameters.add(0,
				ParaIndex.EXAMSTATE_INDEX);// TODO:select all Exams
		start(ChooseActivity.class);
	}
	
	public void onStart(View view) {
	
		Parameters.add(choice.answerState_waitAnser,
				ParaIndex.EXAMSTATE_INDEX);
		start(StartActivity.class);
	}
	
	public void onAnalysis(View view) {
	
		Parameters.add(choice.answerState_finish,
				ParaIndex.EXAMSTATE_INDEX);
		start(AnalysisActivity.class);
	}
	
	public void onCorrect(View view) {
	
		Parameters.add(choice.answerState_waitMark,
				ParaIndex.EXAMSTATE_INDEX);
		start(CorrectActivity.class);
	}
	
	public void onComment(View view) {
	
		Parameters.add(choice.answerState_waitComment,
				ParaIndex.EXAMSTATE_INDEX);
		start(CommentActivity.class);
	}
}
