/**
 * SubActivity_Demo android.exercise
 * 2012 2012-3-29 锟斤拷锟斤拷7:55:07
 * @author xzz
 */
package com.todayedu.ebag.teacher.UIModule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ebag.net.obj.answer.GradeDetail;
import org.ebag.net.obj.answer.GradeObj;

import android.app.ExpandableListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

import com.todayedu.ebag.teacher.Parameters;
import com.todayedu.ebag.teacher.Parameters.ParaIndex;
import com.todayedu.ebag.teacher.R;

/**
 * 每道题目的学生得分详细列表
 * 
 * @author zhenzxie
 * 
 */
public class AEPActivity extends ExpandableListActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.expandable);
		adapter = createAdapter();
		setListAdapter(adapter);
	}

	private SimpleExpandableListAdapter createAdapter() {

		List<Map<String, String>> groupList = createParentList();
		childrenList = createChildrenList();
		return new SimpleExpandableListAdapter(this, groupList,
		        R.layout.expandable_group, R.layout.expandable_group,
		        groupFrom, groupTo, childrenList, R.layout.expandable_children,
		        childFrom, childTo);
	}
	
	@Override
	public void onGroupExpand(int groupPosition) {

		Log.i("AEPActivity", groupPosition + "--------------------");
		if (childrenList.get(groupPosition).size() != 0)// 已经加过数据
			return;
		addInfo2ChildrenList(groupPosition);
		adapter.notifyDataSetChanged();
	}

	@Override
	public boolean onChildClick(ExpandableListView parent, View v,
	        int groupPosition, int childPosition, long id) {
	
		Log.i("AERACtivity", "onChildClick: groupPosition " + groupPosition
		        + "childPosition " + childPosition);
		startActivity(createIntent(groupPosition, childPosition));
		return true;
	}
	
	private ArrayList<GradeDetail> detailMap = Parameters.detailMap;
	private String[] groupFrom = new String[] { "group" };
	private int[] groupTo = new int[] { R.id.aexpandable_group };
	private String[] childFrom = new String[] { "sid", "score" };
	private int[] childTo = new int[] { R.id.aexpandable_child_sid,
	        R.id.aexpandable_child_score };
	private SimpleExpandableListAdapter adapter;
	private List<List<Map<String, String>>> childrenList;
	
	private List<Map<String, String>> createParentList() {
	
		List<Map<String, String>> pList = new ArrayList<Map<String, String>>();
		int size = detailMap.size();
		Map<String, String> pMap;
		for (int i = 0; i < size; i++) {
			pMap = new HashMap<String, String>();
			pMap.put(groupFrom[0], "第" + (i + 1) + "题");
			pList.add(pMap);
		}
		return pList;
	}
	
	private List<List<Map<String, String>>> createChildrenList() {

		int size = detailMap.size();
		List<List<Map<String, String>>> cList = new ArrayList<List<Map<String, String>>>(
		        size);
		for (int i = 0; i < size; i++) {
			cList.add(new ArrayList<Map<String, String>>());// childrenList,数据延迟到父项被点开后才加载,所以不加map
		}
		return cList;
	}
	
	/**
	 * 把某个题所有学生的答题情况，加到childrenList中
	 * 
	 * @param groupPosition
	 */
	private void addInfo2ChildrenList(int groupPosition) {
	
		final ArrayList<GradeObj> sourceGradeObjList = this.detailMap
		        .get(groupPosition).glst;
		List<Map<String, String>> targetList = childrenList.get(groupPosition);
		Map<String, String> childrenMap;
		for (GradeObj gradeObj : sourceGradeObjList) {
			childrenMap = new HashMap<String, String>();
			childrenMap.put(childFrom[0], gradeObj.u_name);
			childrenMap.put(childFrom[1], String.valueOf(gradeObj.p_grade));
			targetList.add(childrenMap);
		}
	}
	
	private Intent createIntent(int groupPosition, int childPosition) {
	
		GradeObj obj = detailMap.get(groupPosition).glst.get(childPosition);
		Parameters.add(obj.p_id, ParaIndex.PID_INDEX);
		Parameters.add(obj.u_id, ParaIndex.SID_INDEX);
		Intent intent = new Intent(this, AEPSActivity.class);
		intent.putExtra("Score", obj.p_grade);
		return intent;
	}
}