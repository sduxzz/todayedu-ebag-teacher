/*
 * ExaminationSystem-Tea com.todayedu.examinationsystem.teacher.DataSource
 * 2012 2012-5-31 锟斤拷锟斤拷6:49:40
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.DataSource;

import java.io.Serializable;
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
 * @author zhenzxie
 * 
 */
public class PCommentDS extends BaseDS implements Serializable {

	/**
     * 
     */
	private static final long serialVersionUID = -8223593166299776033L;

	public PCommentDS(Class<? extends Data> cl) {
	
		super(cl);
	}

	@Override
	public void localload(Context context) {

		String cid = Parameters.getStr(ParaIndex.CID_INDEX);
		String eid = Parameters.getStr(ParaIndex.EID_INDEX);
		Log.i(TAG, cid + "   " + eid);
		String sql = "select number,state,point from CEP,PROBLEM where CEP.pid = PROBLEM.pid and cid = ? and eid = ?";
		String[] selectArgs = new String[] { cid, eid };
		localload(context, sql, selectArgs);
	}

	@Override
	public void download(Context context) {
	
		int cid = Parameters.get(ParaIndex.CID_INDEX);
		int eid = Parameters.get(ParaIndex.EID_INDEX);
		int state = Parameters.get(ParaIndex.EXAMSTATE_INDEX);
		
		List<Integer> idList = new ArrayList<Integer>();
		idList.add(eid);// only get a exam from server
		List<Integer> stateList = new ArrayList<Integer>();
		stateList.add(state);
		List<Field> fieldList = new ArrayList<Field>();
		Class<ExamObj> cl = ExamObj.class;
		try {
			fieldList.add(cl.getDeclaredField("pInfoList"));// 怎么获取题目信息，题目解析，题目状态，我没找到呀。。
		} catch (Exception e) {
			e.printStackTrace();
		}
		NetworkClient client = new NetworkClient();
		ExamCallBack callBack = new ExamCallBack() {
			
			@Override
			public void examSuccess(ExamResponse examResponse) {
			
				List<Data> list = ResponeParseUtil
				        .parseExamResponse(examResponse);
				PCommentDS.this.store(list);
				PCommentDS.this.notifyDataChange();
			}
			
			@Override
			public void examError(Throwable cause) {
			
				Log.i(TAG, cause.getMessage());
			}
		};
		client.setHandler(new ExamHandler(context, callBack, cid, stateList,
		        idList, fieldList));
		client.connect();
	}
}
