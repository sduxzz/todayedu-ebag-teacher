/*
 * ExaminationSystem-Tea com.todayedu.examinationsystem.teacher.DataSource
 * 2012 2012-5-31 ����6:49:40
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.DataSource;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.ebag.net.obj.exam.ExamObj;
import org.ebag.net.response.ExamResponse;

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
public class ExamListDS extends BaseDS {

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
	public void download(Context context) {
	
		int cid = Parameters.get(ParaIndex.CID_INDEX);
		int state = Parameters.get(ParaIndex.EXAMSTATE_INDEX);
		List<Integer> stateList = null;
		if (state == 0) {
			// TODO:how to request all exam
		} else {
			stateList = new ArrayList<Integer>();
			stateList.add(state);
		}
		List<Field> fieldList = new ArrayList<Field>();
		Class<ExamObj> cl = ExamObj.class;
		try {
			fieldList.add(cl.getDeclaredField("name"));
			fieldList.add(cl.getDeclaredField("id"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		NetworkClient client = new NetworkClient();
		ExamCallBack callBack = new ExamCallBack() {
			
			@Override
			public void examSuccess(ExamResponse examResponse) {
			
				List<Data> list = ResponeParseUtil
				        .parseExamResponse(examResponse);
				ExamListDS.this.store(list);
				ExamListDS.this.notifyDataChange();
			}
			
			@Override
			public void examError(Throwable cause) {

				Log.i(TAG, cause.getMessage());
			}
		};
		client.setHandler(new ExamHandler(context, callBack, cid, stateList,
		        null, fieldList));
		client.connect();
	}
}