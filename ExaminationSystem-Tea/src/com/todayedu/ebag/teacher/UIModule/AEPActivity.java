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
import java.util.Set;

import org.ebag.net.obj.answer.GradeObj;

import android.app.ExpandableListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

import com.todayedu.ebag.teacher.Parameters;
import com.todayedu.ebag.teacher.R;

/**
 * @author zhenzxie
 * 
 */
public class AEPActivity extends ExpandableListActivity {
	
	private HashMap<Integer, ArrayList<GradeObj>> detailMap = Parameters.detailMap;

	private String[] groupFrom = new String[] { "group" };

	private int[] groupTo = new int[] { R.id.aexpandable_group };
	
	private String[] childFrom = new String[] { "sid", "score" };

	private int[] childTo = new int[] { R.id.aexpandable_child_sid,
			R.id.aexpandable_child_score };

	/**
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.expandable);
		SimpleExpandableListAdapter adapter = createAdapter();
		setListAdapter(adapter);
	}

	private SimpleExpandableListAdapter createAdapter() {

		// groupList
		List<Map<String, String>> groupList = new ArrayList<Map<String, String>>();
		int size = detailMap.size();
		Map<String, String> groupMap;
		for (int i = 0; i < size; i++) {
			groupMap = new HashMap<String, String>();
			groupMap.put(groupFrom[0], "第" + i + "题");
			groupList.add(groupMap);
		}

		// childrenList,如果数据很大，可以延迟绑定数据到list中
		List<List<Map<String, String>>> childrenList = new ArrayList<List<Map<String, String>>>();
		final HashMap<Integer, ArrayList<GradeObj>> detailMap = this.detailMap;
		final Set<Integer> ids = detailMap.keySet();
		ArrayList<GradeObj> gradeObjs;
		List<Map<String, String>> list;
		Map<String, String> childrenMap;
		for (Integer integer : ids) {
			gradeObjs = detailMap.get(integer);
			list = new ArrayList<Map<String, String>>();
			for (GradeObj gradeObj : gradeObjs) {
				childrenMap = new HashMap<String, String>();
				childrenMap.put(childFrom[0], gradeObj.u_name);
				childrenMap.put(childFrom[1], String.valueOf(gradeObj.p_grade));
				list.add(childrenMap);
			}
			childrenList.add(list);
		}

		SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(
				this, groupList, R.layout.expandable_group,
				R.layout.expandable_group, groupFrom, groupTo, childrenList,
				R.layout.expandable_children, childFrom, childTo);
		return adapter;
	}
	
	/**
	 * @see android.app.ExpandableListActivity#onChildClick(android.widget.ExpandableListView,
	 *      android.view.View, int, int, long)
	 */
	@Override
	public boolean onChildClick(ExpandableListView parent, View v,
	        int groupPosition, int childPosition, long id) {
	
		Log.i("AERACtivity", "onChildClick: groupPosition " + groupPosition
		        + "childPosition " + childPosition);
		return true;
	}
}