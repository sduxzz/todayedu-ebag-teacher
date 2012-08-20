/*
 * ExaminationSystem-Tea com.todayedu.examinationsystem.teacher.DataSource
 * 2012 2012-5-31 ����6:49:40
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.DataSource;

import java.util.ArrayList;
import java.util.List;

import org.ebag.net.response.ExamResponse;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.todayedu.ebag.teacher.Parameters;
import com.todayedu.ebag.teacher.Parameters.ParaIndex;
import com.todayedu.ebag.teacher.Network.ExamHandler;
import com.todayedu.ebag.teacher.Network.ExamHandler.ExamCallBack;
import com.todayedu.ebag.teacher.Network.NetworkClient;
import com.todayedu.ebag.teacher.Network.ResponeParseUtil;

/**
 * this DataSource is used for ExamShowActivity
 * 
 * @author zhenzxie
 * 
 */
public class ExamListDS extends BaseDataSource {

	public ExamListDS(Class<? extends Data> cl) {
	
		super(cl);
	}

	@Override
	public void localload(Context context) {
	
		String cid = Parameters.getStr(ParaIndex.CID_INDEX);
		int state = Parameters.get(ParaIndex.EXAMSTATE_INDEX);
		Log.i(TAG, cid + "   " + state);
		String sql = null;
		String[] selectArgs = null;
		if (state == 0) {// select all exams
			sql = "select eid ,ename from EXAM where cid = ? ";
			selectArgs = new String[] { cid };
		} else {
			sql = "select eid ,ename from EXAM where cid = ? and state = ?";
			selectArgs = new String[] { cid, String.valueOf(state) };
		}
		localload(context, sql, selectArgs);
	}

	@Override
	public void download(final Context context) {
	
		int cid = Parameters.get(ParaIndex.CID_INDEX);
		int state = Parameters.get(ParaIndex.EXAMSTATE_INDEX);

		List<Integer> stateList = null;
		if (state != 0) {// when state is 0(request all exam),so stateList is null;
			stateList = new ArrayList<Integer>();
			stateList.add(state);
		}
		List<String> fieldList = new ArrayList<String>();
		try {
			fieldList.add("name");
			fieldList.add("id");
		} catch (Exception e) {
			e.printStackTrace();
		}

		ExamCallBack callBack = new ExamCallBack() {
			
			@Override
			public void examSuccess(ExamResponse examResponse) {
			
				final List<Data> list = ResponeParseUtil
				        .parseExamResponse(examResponse);
				((Activity) context).runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
					
						ExamListDS.this.store(list);
						ExamListDS.this.notifyDataChange();
					}
				});
			}
			
			@Override
			public void examError(Throwable cause) {

				Log.i(TAG, cause.getMessage());
			}
		};
		NetworkClient client = new NetworkClient();
		client.setHandler(new ExamHandler(context, callBack, cid, stateList,
		        null, fieldList));
		client.connect();
	}
}