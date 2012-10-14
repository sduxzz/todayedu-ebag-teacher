/*
 * ExaminationSystem-Tea com.todayedu.examinationsystem.teacher.UIModule
 * 2012 2012-5-31 锟斤拷锟斤拷4:13:06
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.UIModule;

import java.util.List;

import org.ebag.net.obj.I.choice;
import org.ebag.net.obj.exam.ExamObj;
import org.ebag.net.obj.exam.ProblemInfoObj;
import org.ebag.net.response.ClassExamactivityResponse;
import org.ebag.net.response.ExamResponse;
import org.ebag.net.response.StartExamResponse;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.todayedu.ebag.teacher.Parameters;
import com.todayedu.ebag.teacher.Parameters.ParaIndex;
import com.todayedu.ebag.teacher.R;
import com.todayedu.ebag.teacher.DataAdapter.BaseDataAdapter;
import com.todayedu.ebag.teacher.DataSource.ClassStudentDS;
import com.todayedu.ebag.teacher.DataSource.DSCallback;
import com.todayedu.ebag.teacher.DataSource.SCExamDS;
import com.todayedu.ebag.teacher.Network.NetworkCallBack;
import com.todayedu.ebag.teacher.Network.NetworkClient;
import com.todayedu.ebag.teacher.Network.StartExamHandler;

/**
 * 考场状态，考卷基本情况，开始考试，结束考试的入口Activity
 * 
 * @author zhenzxie
 * 
 */
public class SChooseActivity extends BaseActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.schoose);
		
		tv_1 = (TextView) findViewById(R.id.scexam_tv1);
		tv_2 = (TextView) findViewById(R.id.scexam_tv2);
		tv_3 = (TextView) findViewById(R.id.scexam_tv3);
		tv_4 = (TextView) findViewById(R.id.scexam_tv4);
		tv_5 = (TextView) findViewById(R.id.scexam_tv5);
		tv_6 = (TextView) findViewById(R.id.scexam_tv6);

		lv = (ListView) findViewById(R.id.scexam);
		lv.setBackgroundColor(Color.WHITE);
		lv.setCacheColorHint(Color.WHITE);

		onState(null);
	}
	
	// 考场状态
	public void onState(View view) {
	
		setTv13Text(R.string.sp_scstate_address, R.string.sp_scstate_people);
		int etype = Parameters.getExamObj().getType();
		if (etype == choice.examType_exam) {
			tv_2.setText(R.string.comm_school);
		} else {
			tv_2.setText(R.string.comm_home);
		}
		tv_4.setText(Parameters.getClassName());
		setTv56Visibility(View.INVISIBLE);
		
		if (scStateDs == null) {
			scStateDs = new ClassStudentDS(new SCStateDSCallback());
			scStateDs.load(this);
		}
		if (scStateAdapter == null) {
			scStateAdapter = new BaseDataAdapter(this, scStateDs,
			        R.layout.lv_3, new int[] { R.id.lv3_tv_1, R.id.lv3_tv_2,
			                R.id.lv3_tv_3 }, scStateKeys);
			scStateDs.addObserver(scStateAdapter);
		}
		if (scStateHeaderView == null) {
			scStateHeaderView = HeaderViewFactory.createHeaderView3(this,
			        R.array.exam_lookup_during);
		}
		removeAllHeaderView();
		resetListView(lv, scStateHeaderView, scStateAdapter);
		scStateAdapter.notifyDataSetChanged();
	}
	
	// 考卷基本情况
	public void onExam(View view) {

		ExamObj exam = Parameters.getExamObj();
		setTv13Text(R.string.comm_exam_name_colon,
		        R.string.comm_exam_totalscore_colon);
		tv_2.setText(exam.getName());
		tv_4.setText(String.valueOf(exam.getPoints()) + "分");
		setTv56Visibility(View.VISIBLE);
		tv_5.setText(R.string.comm_exam_totaltime_colon);
		tv_6.setText(String.valueOf(exam.getTime() / 3600000) + "小时");
		
		if (scExamDs == null) {
			scExamDs = new SCExamDS(new SCExamDSCallback());
			scExamDs.load(this);
		}
		if (scExamAdapter == null) {
			scExamAdapter = new BaseDataAdapter(this, scExamDs, R.layout.lv_2,
			        new int[] { R.id.lv2_tv_1, R.id.lv2_tv_2, }, scExamKeys);
			scExamDs.addObserver(scExamAdapter);
		}
		if (scExamHeaderView == null) {
			scExamHeaderView = HeaderViewFactory.createHeaderView2(this,
			        R.array.exam_preview);
		}
		removeAllHeaderView();
		resetListView(lv, scExamHeaderView, scExamAdapter);
		scExamAdapter.notifyDataSetChanged();
	}
	
	// 开始考试
	public void onStart(View view) {

		showAlertDialog("Do you want to start this exam ?",
		        new OnClickListener() {
			        
			        @Override
			        public void onClick(DialogInterface dialog, int which) {
			        
				        int cid = Parameters.get(ParaIndex.CID_INDEX);
				        int eid = Parameters.get(ParaIndex.EID_INDEX);
				        NetworkClient client = new NetworkClient();
				        client.setHandler(new StartExamHandler(
				                SChooseActivity.this, new NetworkCallBack() {
					                
					                @Override
					                public void success(Object response) {
					                
						                StartExamResponse response2 = (StartExamResponse) response;
						                if (response2.isStarted) {
							                showToast("开始考试成功");
						                } else {
							                showToast(response2.message);
						                }

					                }
					                
					                @Override
					                public void failed(Throwable throwable) {
					                
						                if (throwable != null) {
							                Log.i(TAG, throwable.getMessage());
						                }
						                showToast("考试开始失败");
					                }
				                }, cid, eid));
				        client.connect();
			        }
		        }, null);
	}
	
	// 结束考试
	public void onEnd(View view) {
	
		showAlertDialog("Do you want to end this exam ?",
		        new OnClickListener() {
			        @Override
			        public void onClick(DialogInterface dialog, int which) {
			        
				        showToast("暂不支持");
			        }
		        }, null);
	}

	private TextView tv_1;
	private TextView tv_2;
	private TextView tv_3;
	private TextView tv_4;
	private TextView tv_5;
	private TextView tv_6;
	private ListView lv;
	private View scExamHeaderView;
	private View scStateHeaderView;
	private BaseDataAdapter scExamAdapter;
	private BaseDataAdapter scStateAdapter;
	private final String[] scExamKeys = new String[] { "number", "point" };
	private final String[] scStateKeys = new String[] { "sid", "sname", "state" };
	private SCExamDS scExamDs;
	private ClassStudentDS scStateDs;

	/**
	 * 展示一个dialog
	 */
	private void showAlertDialog(CharSequence message,
	        OnClickListener positiveListener, OnClickListener negativeListener) {
	
		new AlertDialog.Builder(this).setMessage(message)
		        .setPositiveButton(R.string.comm_confirm, positiveListener)
		        .setNegativeButton(R.string.comm_cancel, negativeListener)
		        .show();
	}
	
	/**
	 * @param lv
	 * @param headerView
	 */
	private void resetListView(ListView lv, View headerView, BaseAdapter adapter) {
	
		lv.addHeaderView(headerView);
		lv.setAdapter(adapter);
	}

	private void setTv13Text(int tv1Text, int tv3Text) {
	
		tv_1.setText(tv1Text);
		tv_3.setText(tv3Text);
	}

	private void setTv56Visibility(int visibility) {
	
		tv_5.setVisibility(visibility);
		tv_6.setVisibility(visibility);
	}
	
	private void removeAllHeaderView() {
	
		lv.removeHeaderView(scExamHeaderView);
		lv.removeHeaderView(scStateHeaderView);
	}

	private class SCExamDSCallback implements DSCallback {
		
		@Override
		public void onLoadSuccess(Object object) {
		
			ExamResponse examResponse = (ExamResponse) object;
			if (examResponse == null || examResponse.getExamList().size() == 0)
				return;
			List<ProblemInfoObj> list = examResponse.getExamList().get(0).pInfoList;
			if (list == null || list.size() == 0)
				return;
			scExamDs.setpInfoList(list);
			scExamDs.createMaps(scExamKeys);
			scExamDs.notifyDataChange(SChooseActivity.this);
		}
		
		@Override
		public void onLoadFailed(Throwable throwable) {
		
			SChooseActivity.this.onLoadFailed(throwable);
		}
	}
	
	private class SCStateDSCallback implements DSCallback {
		
		@Override
		public void onLoadSuccess(Object object) {
		
			ClassExamactivityResponse examResponse = (ClassExamactivityResponse) object;
			if (examResponse == null || examResponse.lst.size() == 0)
				return;
			scStateDs.setExamActivitiesList(examResponse.lst);
			scStateDs.createMaps(scStateKeys);
			scStateDs.notifyDataChange(SChooseActivity.this);
		}

		@Override
		public void onLoadFailed(Throwable throwable) {
		
			SChooseActivity.this.onLoadFailed(throwable);
		}
	}
}
