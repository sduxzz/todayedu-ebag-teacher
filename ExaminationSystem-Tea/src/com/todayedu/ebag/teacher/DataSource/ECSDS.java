/*
 * ExaminationSystem-Tea com.todayedu.examinationsystem.teacher.DataSource
 * 2012 2012-5-31 锟斤拷锟斤拷6:49:40
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.DataSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ebag.net.obj.I.choice;
import org.ebag.net.obj.answer.AnswerObj;

import android.app.Activity;
import android.content.Context;
import android.util.SparseArray;

import com.todayedu.ebag.teacher.Constants.StateStr;
import com.todayedu.ebag.teacher.Parameters;
import com.todayedu.ebag.teacher.Parameters.ParaIndex;
import com.todayedu.ebag.teacher.DataSource.DataObj.Answer;
import com.todayedu.ebag.teacher.Network.AnswerHandler;
import com.todayedu.ebag.teacher.Network.UrlBuilder;

/**
 * @author zhenzxie
 * 
 */
public class ECSDS extends BaseDataSource {
	
	private class InnerThread extends Thread {
		
		Activity activity;
		
		public InnerThread(Activity activity) {
		
			this.activity = activity;
		}

		public void run() {
		
			List<AnswerObj> list = getExamList();
			List<Map<String, String>> maps = getListMap();
			Map<String, String> map = null;
			Answer answer = null;
			int i = 0;
			for (AnswerObj obj : list) {
				if ((answer = Answer.query(activity, obj.id, obj.uid,
				        obj.problemId)) != null) {// 可以优化哦
					map = maps.get(i);
					map.put("state", answer.getState());
					obj.setPoint(answer.getPoint());
					obj.setTextOfTeacher(answer.getTextOfTeacher());
					obj.setPicOfTeacherUrl(answer.getAnswerofTea());
				}
				i++;
			}
			notifyDataChange(activity);
		}
	}

	public ECSDS(DSCallback callback) {
	
		super(callback);
	}

	@Override
	public void download(Activity context) {
	
		int uid = Parameters.get(ParaIndex.SID_INDEX);
		int eid = Parameters.get(ParaIndex.EID_INDEX);
		loadStart(new AnswerHandler(context, this, uid, eid, null, null));
	}
	
	@Override
	public void createMaps(String[] keys) {
	
		// createMaps(Context, String[])
	}
	
	public void createMaps(Activity context, String[] keys) {

		List<AnswerObj> list = this.getExamList();
		List<Map<String, String>> maps = this.getListMap();
		maps.clear();
		Map<String, String> map = null;
		int i = 1;
		for (AnswerObj obj : list) {
			map = new HashMap<String, String>();
			map.put(keys[0], "第" + (i++) + "题");
			switch (obj.state) {
				case choice.answerState_waitAnser:
				case choice.answerState_waitMark:
					map.put(keys[1], StateStr.CORRECT);
					break;
				case choice.answerState_finish:
				case choice.answerState_waitComment:
				default:
					map.put(keys[1], StateStr.CORRECTED);
			}
			maps.add(map);
		}
		InnerThread thread = new InnerThread(context);
		thread.start();
	}
	
	/**
	 * 获取当前AnswerObj对应的Answer对象。 只有createMaps方法调用后这个方法才不会返回null。
	 * 
	 * @return
	 */
	public Answer getCurrentAnswer() {
	
		if (examList == null || getListMap().size() == 0)
			return null;
		AnswerObj obj = examList.get(index);
		int id = obj.id;
		Answer answer = temp.get(id);
		if (answer == null) {
			answer = createAnswer(obj);
			temp.put(id, answer);
		}
		return answer;
	}

	public void onComfirm(Context context, String answerofTea,
	        String textOfTeacher, double point) {
	
		Answer answer = (Answer) getCurrentAnswer();
		if (answer == null)
			return;
		answer.setAnswerofTea(answerofTea);
		answer.setTextOfTeacher(textOfTeacher);
		answer.setPoint(point);
		answer.setState(StateStr.CORRECTED);
		answer.save(context);
		
		Map<String, String> map = this.getListMap().get(index);
		map.put("state", StateStr.COMMENTED);
	}

	/**
	 * 向后移动一位
	 */
	public void moveToNext() {
	
		if (canNext()) {
			index++;
		}
	}
	
	/**
	 * 是否能向后移动一位
	 * 
	 * @return boolean
	 */
	public boolean canNext() {
	
		if (getExamList() == null)
			return false;
		if (index >= getExamList().size() - 1) {
			return false;
		}
		return true;
	}
	
	/**
	 * 向前移动一位
	 */
	public void moveToPrevious() {
	
		if (canPrevious()) {
			index--;
		}
	}
	
	/**
	 * 是否能向前移动一位
	 * 
	 * @return boolean
	 */
	public boolean canPrevious() {
	
		if (index <= 0) {
			return false;
		}
		return true;
	}
	
	/**
	 * 移动到index位置
	 * 
	 * @param index
	 */
	public boolean moveTo(int index) {
	
		if (index >= 0 && index < getExamList().size()) {
			this.index = index;
			return true;
		}
		return false;
	}
	
	/**
	 * @return the examList
	 */
	public List<AnswerObj> getExamList() {
	
		return examList;
	}
	
	/**
	 * @param examList
	 *            the examList to set
	 */
	public void setExamList(List<AnswerObj> examList) {
	
		this.examList = examList;
	}
	
	private SparseArray<Answer> temp = new SparseArray<Answer>();
	private List<AnswerObj> examList;
	private int index = 0;

	/**
	 * @param obj
	 * @return
	 */
	private Answer createAnswer(AnswerObj obj) {
	
		Answer answer = new Answer();
		answer.setId(obj.id);
		answer.setPid(obj.problemId);
		answer.setNumber(index + 1);
		answer.setPoint(obj.point);
		answer.setScore(obj.score);
		answer.setState(getListMap().get(index).get("state"));// 这里要注意呀，state是map中的key
		answer.setTextAnswer(obj.textAnswer == null ? StateStr.NOANSWER
		        : obj.textAnswer);
		answer.setTextOfTeacher(obj.textOfTeacher == null ? StateStr.NOCORRECT
		        : obj.textOfTeacher);
		answer.setContent(UrlBuilder.problemContentUrl(obj.problemId));
		answer.setAnswerofSta(UrlBuilder.problemAnswerUrl(obj.problemId));
		answer.setAnswerofStu(UrlBuilder.problemAnswerPicUrl(obj.picAnswerUrl));
		answer.setAnswerofTea(obj.picOfTeacherUrl);
		return answer;
	}
}
