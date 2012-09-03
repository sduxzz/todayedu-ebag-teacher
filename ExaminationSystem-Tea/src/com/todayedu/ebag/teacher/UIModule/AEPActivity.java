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

import android.app.ExpandableListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

import com.todayedu.ebag.teacher.R;

/**
 * @author zhenzxie
 * 
 */
public class AEPActivity extends ExpandableListActivity {
	
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
		String[] group = getResources().getStringArray(R.array.exam_analysis);
		Map<String, String> groupMap;
		for (String name : group) {
			groupMap = new HashMap<String, String>();
			groupMap.put(groupFrom[0], name);
			groupList.add(groupMap);
		}

		// childrenList
		List<List<Map<String, String>>> childrenList = new ArrayList<List<Map<String, String>>>();
		List<Map<String, String>> list;
		Map<String, String> childrenMap;
		for (int j = 0; j < group.length; j++) {
			list = new ArrayList<Map<String, String>>();
			for (int i = 0; i < 2; i++) {
				childrenMap = new HashMap<String, String>();
				childrenMap.put(childFrom[0], String.valueOf(i));
				childrenMap.put(childFrom[1], String.valueOf(i));
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
	
		return true;
	}

}
