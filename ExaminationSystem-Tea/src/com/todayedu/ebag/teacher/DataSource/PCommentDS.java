/*
 * ExaminationSystem-Tea com.todayedu.examinationsystem.teacher.DataSource
 * 2012 2012-5-31 ����6:49:40
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.DataSource;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.util.Log;

import com.todayedu.ebag.teacher.Parameters;
import com.todayedu.ebag.teacher.Parameters.ParaIndex;
import com.todayedu.ebag.teacher.DataSource.DataObj.Problem;
import com.todayedu.ebag.teacher.Network.ExamHandler;
import com.todayedu.ebag.teacher.Network.NetworkCallBack;

/**
 * @author zhenzxie
 * 
 */
public class PCommentDS extends BaseDataSource implements Serializable, NetworkCallBack {

	/**
     * 
     */
	private static final long serialVersionUID = -8223593166299776033L;
	public PCommentDS(DSCallback callback) {
	
		super(callback);
	}

	@Override
	public void download(Activity context) {
	
		int cid = Parameters.get(ParaIndex.CID_INDEX);
		int eid = Parameters.get(ParaIndex.EID_INDEX);
		
		List<Integer> idList = new ArrayList<Integer>();
		idList.add(eid);
		List<String> fieldList = new ArrayList<String>();
		fieldList.add("pInfoList");// pInfoList is the field name of ExamObj
		connect(new ExamHandler(context, this, cid, null,
		        idList, fieldList));
	}

	@Override
	public void createMaps(String[] keys) {
	
		Log.i(TAG, "createMaps");
		List<? extends Data> list = this.getList();
		List<Map<String, String>> maps = this.getData();
		Map<String, String> map = null;
		Problem problem = null;
		for (Data data : list) {
			problem = (Problem) data;
			map = new HashMap<String, String>();
			map.put(keys[0], String.valueOf(problem.getNumber()));
			map.put(keys[1], problem.getState());
			maps.add(map);
		}
	}
}
