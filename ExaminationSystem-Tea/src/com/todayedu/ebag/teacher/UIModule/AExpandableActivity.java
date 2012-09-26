/**
 * SubActivity_Demo android.exercise
 * 2012 2012-3-29 ����7:55:07
 * @author xzz
 */
package com.todayedu.ebag.teacher.UIModule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ebag.net.response.AnswerAnalysisResponse;

import android.app.ExpandableListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.Toast;

import com.todayedu.ebag.teacher.Parameters;
import com.todayedu.ebag.teacher.R;
import com.todayedu.ebag.teacher.DataSource.AnswerAnalysisDS;
import com.todayedu.ebag.teacher.DataSource.DSCallback;

/**
 * �Ծ�����ĸ�����ڣ��ܷ����У����ٵ÷���Ŀ�����÷���Ŀ����Ŀ��ϸ�б�
 * 
 * @author zhenzxie
 * 
 */
public class AExpandableActivity extends ExpandableListActivity implements DSCallback {
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.expandable);
		adapter = createAdapter();
		setListAdapter(adapter);
	}

	private SimpleExpandableListAdapter createAdapter() {

		// groupList
		List<Map<String, String>> groupList = new ArrayList<Map<String, String>>();
		String[] group = getResources().getStringArray(R.array.exam_analysis);
		Map<String, String> groupMap;
		for (String name : group) {
			groupMap = new HashMap<String, String>();
			groupMap.put(groupFrom[0], name);
			groupList.add(groupMap);
		}

		// childrenList
		List<List<Map<String, String>>> childrenList = new ArrayList<List<Map<String, String>>>();
		ds = new AnswerAnalysisDS(this, childrenList);
		ds.load(this);

		SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(
				this, groupList, R.layout.expandable_group,
				R.layout.expandable_group, groupFrom, groupTo, childrenList,
				R.layout.expandable_children, childFrom, childTo);
		return adapter;
	}

	@Override
	public boolean onChildClick(ExpandableListView parent, View v,
	        int groupPosition, int childPosition, long id) {

		Log.i(TAG, String.valueOf(groupPosition));
		if (groupPosition == 3) {
			startActivity(new Intent(this, AEPActivity.class));
			return true;
		}
		return false;
	}
	
	@Override
	public void onLoadSuccess(Object object) {
	
		AnswerAnalysisResponse response = (AnswerAnalysisResponse) object;
		if (response == null || response.res == null)
			return;
		Parameters.detailMap = response.res.detailMap;// ����������ϸ�б�
		ds.setRes(response.res);
		ds.createMaps(childFrom);
		adapter.notifyDataSetChanged();
	}
	
	@Override
	public void onLoadFailed(Throwable throwable) {
	
		if (throwable != null) {
			Log.i(TAG, throwable.getMessage() + "");
		}
		AExpandableActivity.this.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
			
				Toast.makeText(AExpandableActivity.this, "��������ʧ��",
				        Toast.LENGTH_LONG).show();
			}
		});
	}
	
	private static final String TAG = "AExpandableActivity";
	private String[] groupFrom = new String[] { "group" };
	private int[] groupTo = new int[] { R.id.aexpandable_group };
	private String[] childFrom = new String[] { "sid", "score" };
	private int[] childTo = new int[] { R.id.aexpandable_child_sid,
	        R.id.aexpandable_child_score };
	private AnswerAnalysisDS ds;
	private SimpleExpandableListAdapter adapter;

}
