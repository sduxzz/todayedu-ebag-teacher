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
import java.util.Set;

import org.ebag.net.obj.answer.GradeObj;

import android.app.ExpandableListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

import com.todayedu.ebag.teacher.Parameters;
import com.todayedu.ebag.teacher.Parameters.ParaIndex;
import com.todayedu.ebag.teacher.R;

/**
 * ÿ����Ŀ��ѧ���÷���ϸ�б�
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
	public void onGroupExpand(int groupPosition) {// groupPosition�Ǵ�1��ʼ����0�������ȵ���1�ˡ�

		Log.i("AEPActivity", groupPosition + "--------------------");
		if (childrenList.get(groupPosition - 1).size() != 0)// �Ѿ��ӹ�����
			return;
		addList2ChildrenList(groupPosition);
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
	
	private HashMap<Integer, ArrayList<GradeObj>> detailMap = Parameters.detailMap;
	private String[] groupFrom = new String[] { "group" };
	private int[] groupTo = new int[] { R.id.aexpandable_group };
	private String[] childFrom = new String[] { "sid", "score" };
	private int[] childTo = new int[] { R.id.aexpandable_child_sid,
	        R.id.aexpandable_child_score };
	private SimpleExpandableListAdapter adapter;
	private List<List<Map<String, String>>> childrenList;
	private SparseArray<Integer> idMap = new SparseArray<Integer>();// ��Ŀ˳����Ŀid��ӳ��, ��Ŵ�1��ʼ
	
	private List<Map<String, String>> createParentList() {
	
		List<Map<String, String>> pList = new ArrayList<Map<String, String>>();
		int size = detailMap.size();
		Map<String, String> pMap;
		for (int i = 0; i < size; i++) {
			pMap = new HashMap<String, String>();
			pMap.put(groupFrom[0], "��" + (i + 1) + "��");
			pList.add(pMap);
		}
		return pList;
	}
	
	private List<List<Map<String, String>>> createChildrenList() {

		int size = detailMap.size();
		Set<Integer> ids = detailMap.keySet();
		List<List<Map<String, String>>> cList = new ArrayList<List<Map<String, String>>>(
		        size);
		int i = 1;
		for (Integer integer : ids) {
			cList.add(new ArrayList<Map<String, String>>());// childrenList,�����ӳٵ�����㿪��ż���,���Բ���map
			idMap.put(i++, integer);
		}
		return cList;
	}
	
	/**
	 * ��ĳ��������ѧ���Ĵ���������ӵ�childrenList��
	 * 
	 * @param groupPosition
	 */
	private void addList2ChildrenList(int groupPosition) {
	
		Integer id = idMap.get(groupPosition);
		final ArrayList<GradeObj> gradeObjs = this.detailMap.get(id);
		List<Map<String, String>> list = childrenList.get(groupPosition - 1);
		Map<String, String> childrenMap;
		for (GradeObj gradeObj : gradeObjs) {
			childrenMap = new HashMap<String, String>();
			childrenMap.put(childFrom[0], gradeObj.u_name);
			childrenMap.put(childFrom[1], String.valueOf(gradeObj.p_grade));
			list.add(childrenMap);
		}
	}
	
	private Intent createIntent(int groupPosition, int childPosition) {
	
		int pid = idMap.get(groupPosition);
		GradeObj obj = detailMap.get(pid).get(childPosition - 1);
		int sid = obj.u_id;
		Parameters.add(pid, ParaIndex.PID_INDEX);
		Parameters.add(sid, ParaIndex.SID_INDEX);
		Intent intent = new Intent(this, AEPSActivity.class);
		intent.putExtra("Score", obj.p_grade);
		return intent;
	}
}