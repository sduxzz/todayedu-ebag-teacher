/*
 * ExaminationSystem-Tea com.todayedu.examinationsystem.teacher.UIModule
 * 2012 2012-5-31 ����4:13:06
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.UIModule;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.todayedu.ebag.teacher.Parameters;
import com.todayedu.ebag.teacher.Parameters.ParaIndex;
import com.todayedu.ebag.teacher.R;
import com.todayedu.ebag.teacher.Network.NetworkCallBack;
import com.todayedu.ebag.teacher.Network.NetworkClient;
import com.todayedu.ebag.teacher.Network.StartExamHandler;

/**
 * ����״̬����������������ʼ���ԣ��������Ե����Activity
 * 
 * @author zhenzxie
 * 
 */
public class SChooseActivity extends BaseActivity {
	
	/**
	 * @see com.todayedu.ebag.teacher.UIModule.MonitoredActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.schoose);
	}
	
	// ����״̬
	public void onState(View view) {
	
		start(SCStateActivity.class);
	}
	
	// ����������
	public void onExam(View view) {

		start(SCExamActivity.class);
	}
	
	// ��ʼ����
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
					                
						                showToast("��ʼ���Գɹ�");
					                }
					                
					                @Override
					                public void failed(Throwable throwable) {
					                
						                if (throwable != null) {
							                Log.i(TAG, throwable.getMessage());
						                }
						                showToast("���Կ�ʼʧ��");
					                }
				                }, cid,
				                eid));
			        	client.connect();
			        }
		        }, null);
	}
	
	// ��������
	public void onEnd(View view) {
	
		showAlertDialog("Do you want to end this exam ?",
		        new OnClickListener() {
			        
			        @Override
			        public void onClick(DialogInterface dialog, int which) {
			        
			        }
		        }, null);
	}

	/**
     * 
     */
	private void showAlertDialog(CharSequence message,
	        OnClickListener positiveListener, OnClickListener negativeListener) {
	
		new AlertDialog.Builder(this).setMessage(message)
		        .setPositiveButton(R.string.comm_confirm, positiveListener)
		        .setNegativeButton(R.string.comm_cancel, negativeListener)
		        .show();
	}
}
