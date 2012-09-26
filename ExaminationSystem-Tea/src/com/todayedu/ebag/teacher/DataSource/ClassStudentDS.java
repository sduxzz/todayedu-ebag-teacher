/*
 * ExaminationSystem-Tea com.todayedu.examinationsystem.teacher.DataSource
 * 2012 2012-5-31 ����6:49:40
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.DataSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ebag.net.obj.I.choice;

import android.app.Activity;

import com.todayedu.ebag.teacher.Constants.StateStr;
import com.todayedu.ebag.teacher.Parameters;
import com.todayedu.ebag.teacher.Parameters.ParaIndex;
import com.todayedu.ebag.teacher.Network.ClassExamActivityHandler;

import ebag.pojo.Examactivity;

/**
 * load the students' name,id,state
 * 
 * @author zhenzxie
 * 
 */
public class ClassStudentDS extends BaseDataSource {
	
	private List<Examactivity> examActivitiesList;
	public ClassStudentDS(DSCallback callback) {
	
		super(callback);
	}

	@Override
	public void download(Activity context) {
	
		int cid = Parameters.get(ParaIndex.CID_INDEX);
		int eid = Parameters.get(ParaIndex.EID_INDEX);
		loadStart(new ClassExamActivityHandler(context, this, cid, eid));
	}

	@Override
	public void createMaps(String[] keys) {
	
		List<Examactivity> list = this.getExamActivitiesList();
		List<Map<String, String>> maps = this.getListMap();
		maps.clear();
		Map<String, String> map = null;
		String state = null;
		for (Examactivity obj : list) {
			map = new HashMap<String, String>();
			map.put(keys[0], String.valueOf(obj.getEuser().getId()));
			map.put(keys[1], obj.getEuser().getName());
			switch (obj.getId().getState().intValue()) {
				case choice.answerState_waitAnser:
					state = StateStr.HANDIN;
				case choice.answerState_waitMark:
					state = StateStr.CORRECT;
					break;
				case choice.answerState_waitComment:
				case choice.answerState_finish:
				default:
					state = StateStr.CORRECTED;
			}
			map.put(keys[2], state);
			maps.add(map);
		}
	}

	/**
     * @return the examActivitiesList
     */
    public List<Examactivity> getExamActivitiesList() {
    
	    return examActivitiesList;
    }

	/**
     * @param examActivitiesList the examActivitiesList to set
     */
    public void setExamActivitiesList(List<Examactivity> examActivitiesList) {
    
	    this.examActivitiesList = examActivitiesList;
    }
}
