/*
 * ExaminationSystem-Tea com.todayedu.examinationsystem.teacher.UIModule
 * 2012 2012-5-31 ����4:13:06
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.UIModule;

import org.ebag.net.response.AnswerResponse;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;

import com.todayedu.ebag.teacher.R;
import com.todayedu.ebag.teacher.DataAdapter.BaseDataAdapter;
import com.todayedu.ebag.teacher.DataSource.ECSDS;
import com.todayedu.ebag.teacher.DataSource.DataObj.Answer;

/**
 * ĳ��ѧ����Ҫ�����Ծ�Ľ���,������Ŀ�б�������Ŀ����
 * 
 * @author zhenzxie
 * @see ECSFragment
 * @see ECSSFragment
 * 
 */
public class ECSActivity extends BaseActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ecs);
		
		ds = new ECSDS(this);
		ds.load(this);
		adapter = new BaseDataAdapter(this, ds, R.layout.lv_2, new int[] {
		        R.id.lv2_tv_1, R.id.lv2_tv_2 }, keys);
		ds.addObserver(adapter);
	}
	
	@Override
	protected void onResume() {
	
		super.onResume();
		ECSFragment fragment = this.getEcsFragment();
		fragment.initECSFragment(adapter);
	}
	
	@Override
	public void onBackPressed() {
	
		super.onBackPressed();
		this.finish();
	}
	
	@Override
	public void onLoadSuccess(Object object) {
	
		AnswerResponse response = (AnswerResponse) object;
		if (response == null || response.examList.size() == 0)
			return;
		ds.setExamList(response.examList);
		ds.createMaps(this, keys);
		ds.notifyDataChange(this);
	}

	/**
	 * ��Ļ����ȷ����,ֻ���ڵ���ECSSFragment��onConfirm������ͼƬ����ɹ��󣬲ű�����������Ϣ
	 * 
	 * @param view
	 */
	public void onComfirm(View view) {
	
		ECSSFragment fragment2 = this.getECSSFragment();
		if (fragment2.onConfirm()) {
			String answerofTea = fragment2.getAnswerofTea();
			String textOfTeacher = fragment2.getTextOfTeacher();
			String pointStr = fragment2.getPoint();
			double point = 0;// ��ô�ж�һ���ַ����ǲ���double����ֵ
			try {
				point = Double.parseDouble(pointStr);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				showToast("��������ȷ�÷�");
				return;
			}
			ds.onComfirm(this, answerofTea, textOfTeacher, point);
			ds.notifyDataChange(this);
			showToast("�ɹ�����");
			issave = true;
		}
	}
	
	/**
	 * ��Ļ������һ���
	 * 
	 * @param view
	 */
	public void onPrevious(View view) {
	
		if (!issave) {
			alert(new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
				
					previous();
				}
			});
		} else {
			issave = false;
			previous();
		}
	}
	
	/**
	 * ��Ļ������һ���
	 * 
	 * @param view
	 */
	public void onNext(View view) {

		if (!issave) {
			alert(new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
				
					next();
				}
			});
		} else {
			issave = false;
			next();
		}
	}
	
	/**
	 * ��Ŀ�б�����е�ĳһ����
	 */
	public void onPosition(final int position) {
	
		if (!issave) {
			alert(new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
				
					position(position);
				}
			});
		} else {
			issave = false;
			position(position);
		}
	}

	private BaseDataAdapter adapter;
	private ECSDS ds;
	private final String[] keys = new String[] { "number", "state" };
	
	private boolean issave = false;
	
	private void previous() {
	
		ds.moveToPrevious();
		changeECSS(ds.getCurrentAnswer(), ds.canPrevious(), ds.canNext());
	}

	private void next() {
	
		ds.moveToNext();
		changeECSS(ds.getCurrentAnswer(), ds.canPrevious(), ds.canNext());
	}

	private void position(int position) {
	
		if (ds.moveTo(position))
			changeECSS(ds.getCurrentAnswer(), ds.canPrevious(), ds.canNext());
	}

	private void changeECSS(Answer answer, boolean canPrevious, boolean canNext) {
	
		ECSSFragment fragment = this.getECSSFragment();
		if (fragment == null)
			return;
		fragment.resetECSS(answer, canPrevious, canNext);
	}

	private ECSFragment getEcsFragment() {
	
		ECSFragment fragment = (ECSFragment) this.getFragmentManager()
		        .findFragmentById(R.id.ecs_ecsf);
		return fragment;
	}
	
	private ECSSFragment getECSSFragment() {
	
		ECSSFragment fragment2 = (ECSSFragment) this.getFragmentManager()
		        .findFragmentById(R.id.ecs_eccsf);
		return fragment2;
	}
	
	private void alert(DialogInterface.OnClickListener listener) {
	
		new AlertDialog.Builder(this).setMessage("û�����Ҫ�뿪��")
		        .setPositiveButton(R.string.comm_confirm, listener)
		        .setNegativeButton(R.string.comm_cancel, null).show();
	}
}
