/*
 * ExaminationSystem-Tea com.todayedu.ebag.teacher.UIModule
 * 2012 2012-9-20 ����9:35:15
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.UIModule;

import java.util.ArrayList;
import java.util.List;

import org.ebag.net.obj.I.choice;

import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.todayedu.ebag.teacher.R;

/**
 * �ṩ����ҵ��Ծ���ʼ���Եȵ������ͬ���ܵ����
 * 
 * @author <a href="zhenzxie.iteye.cn">zhenzxie</a>
 * @version 1.0
 * @since 1.0
 */
public class FunctionChooseFragment extends ListFragment {
	
	/**
	 * @see android.app.ListFragment#onCreateView(android.view.LayoutInflater,
	 *      android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	        Bundle savedInstanceState) {
	
		return inflater.inflate(R.layout.fuctionchoosefragment, container,
		        false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
	
		super.onActivityCreated(savedInstanceState);
		List<String> list = new ArrayList<String>();
		list.add("�ҵ��Ծ�");
		list.add("׼������");
		list.add("�����Ծ�");
		list.add("�����Ծ�");
		list.add("ͳ�Ʒ���");
		setListAdapter(new ArrayAdapter<String>(getActivity(), R.layout.lv_1,
		        R.id.lv1_tv_1, list));
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
	
		Log.i("FunctionFragment", "onListItemClick: Item clicked " + id);
		switch (position) {
			case 0:
				examShow(0, 0);/* 0 ����ȫ�����Ծ������Ծ��״̬ */
				break;
			case 1:
				examShow(choice.answerState_waitAnser, 1);
				break;
			case 2:
				examShow(choice.answerState_waitMark, 2);
				break;
			case 3:
				examShow(choice.answerState_waitComment, 3);
				break;
			case 4:
				examShow(choice.answerState_finish, 4);
				break;
			default:
				return;
		}
	}
	
	/**
	 * {@link FunctionActivity#examShow(int, int)}
	 * 
	 */
	private void examShow(int examState, int mode) {
	
		((FunctionActivity) getActivity()).examShow(examState, mode);
	}
}