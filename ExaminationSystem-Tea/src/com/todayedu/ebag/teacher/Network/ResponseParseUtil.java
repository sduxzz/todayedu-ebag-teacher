/*
 * ExaminationSystem-Tea com.todayedu.ebag.teacher.Network
 * 2012 2012-8-6 上午10:56:56
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.Network;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.ebag.net.obj.I.choice;
import org.ebag.net.obj.answer.AnswerAnalysis;
import org.ebag.net.obj.exam.ExamObj;
import org.ebag.net.response.AnswerAnalysisResponse;
import org.ebag.net.response.AnswerResponse;
import org.ebag.net.response.ClassExamactivityResponse;
import org.ebag.net.response.ClassInfoResponse;
import org.ebag.net.response.ExamResponse;
import org.ebag.net.response.LoginResponse;

import android.content.Context;
import android.util.Log;

import com.todayedu.ebag.teacher.Constants.StateStr;
import com.todayedu.ebag.teacher.Parameters;
import com.todayedu.ebag.teacher.Parameters.ParaIndex;
import com.todayedu.ebag.teacher.DataSource.Data;
import com.todayedu.ebag.teacher.DataSource.DataObj.Analysis;
import com.todayedu.ebag.teacher.DataSource.DataObj.Answer;
import com.todayedu.ebag.teacher.DataSource.DataObj.EClass;
import com.todayedu.ebag.teacher.DataSource.DataObj.Exam;
import com.todayedu.ebag.teacher.DataSource.DataObj.Problem;
import com.todayedu.ebag.teacher.DataSource.DataObj.Student;

import ebag.pojo.Eclass;
import ebag.pojo.Euser;
import ebag.pojo.Examactivity;
import ebag.pojo.ExamactivityId;

/**
 * @author zhenzxie
 *
 */
public class ResponseParseUtil {

	public static final String TAG = "ResponeParseUtil";

	public static ArrayList<EClass> parseLoginResponse(LoginResponse response) {
    
		Set<Eclass> classSet = response.getClassSet();
		ArrayList<EClass> list = new ArrayList<EClass>();
    	EClass ec;
    	
		for (Eclass cs : classSet) {
    		ec = new EClass();
			ec.setCid(cs.getId());
			ec.setCname(cs.getClassname());
    		list.add(ec);
			Log.i(TAG, "parseLoginResponse: " + ec.toString());
    	}
    	
    	return list;
    }

	public static List<Data> parseClassInfoResponse(ClassInfoResponse response) {
    
    	String className = response.className;
    	EClass ec = new EClass(Parameters.get(ParaIndex.CID_INDEX), className);
    	List<Data> list = new ArrayList<Data>();
    	list.add(ec);
    	return list;
    }

	public static List<Data> parseExamResponse(ExamResponse response) {
    
    	List<ExamObj> examList = response.getExamList();
    	List<Data> list = new ArrayList<Data>();
    	Exam exam;
    	
    	for (ExamObj obj : examList) {
    		exam = Exam.convert(obj);
    		list.add(exam);
			Log.i(TAG, "parseExamResponse:" + exam.toString());
    	}
    	
    	return list;
    }

	/**
	 * 
	 * @param response
	 * @param context
	 * @return
	 */
	public static List<Data> parseExamResponse2ProblemList(
	        ExamResponse response, Context context) {
    
    	List<ExamObj> examList = response.getExamList();
		ExamObj obj = examList.get(0);
		Log.i(TAG, "parseExamResponse2ProblemList: the examlist's length is "
		        + examList.size());
		if (obj == null) {
			Log.i(TAG,
			        "parseExamResponse2ProblemList: ExamObj is null");
			return null;
		}
		return Problem.parse2ProblemList(obj, context);
    }
	
	public static List<Data> paraClassExamActivityResponse(
	        ClassExamactivityResponse response) {
	
		List<Examactivity> examactivities = response.lst;
		List<Data> list = new ArrayList<Data>();
		Student student;
		ExamactivityId id;
		Euser euser;
		String state = null;
		
		for (Examactivity obj : examactivities) {
			id = obj.getId();
			euser = obj.getEuser();
			student = new Student();
			student.setCid(Parameters.get(ParaIndex.CID_INDEX));//set cid of student
			student.setSid(euser.getId());
			student.setSname(euser.getName());
			switch (id.getState().intValue()) {// set state of student's exam
				case choice.answerState_waitAnser:
					state = StateStr.HANDIN;
					break;
				case choice.answerState_waitMark:
					state = StateStr.CORRECT;
					break;
				case choice.answerState_waitComment:
					state = StateStr.COMMENT;
					break;
				case choice.answerState_finish:
					state = StateStr.COMMENTED;
					break;
				default:
					state = StateStr.HANDIN;
			}
			student.setState(state);
			list.add(student);
			Log.i(TAG, "paraClassExamActivityResponse:" + student.toString());
		}
		
		return list;
	}
	
	public static List<Data> paraAnswerAnalysisResponse(
	        AnswerAnalysisResponse response) {
	
		AnswerAnalysis analysis = response.res;
		List<Data> list = new ArrayList<Data>();
		Analysis a;
		// TODO:获取数据,加到list中
		return list;
	}
	
	public static Analysis paraAnswerAnalysisResponseGetMax(
	        AnswerAnalysisResponse response) {
	
		return Analysis.para2Analysis();
	}
	
	public static Analysis paraAnswerAnalysisResponseGetMin(
	        AnswerAnalysisResponse response) {
	
		return Analysis.para2Analysis();
	}
	
	public static List<Answer> parseAnswerResponse(AnswerResponse response) {
	
		return Answer.parse(response.examList);
	}

}
