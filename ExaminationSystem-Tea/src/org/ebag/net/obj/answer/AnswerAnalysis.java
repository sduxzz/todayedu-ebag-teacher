package org.ebag.net.obj.answer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AnswerAnalysis implements Serializable{

	private static final long serialVersionUID = 1450336001542836446L;

	public int examId;
	
	public List<Double> rightPercent;

	//考试学生总分链表
	public List<GradeObj> allGradeList;
	//最低、最高成绩
	public GradeObj minGrade;
	public GradeObj maxGrade;
	//key为题目id,value为本题目所有学生得分情况
	public ArrayList<GradeDetail> detailMap;
	
	public int getExamId() {
		return examId;
	}

	public void setExamId(int examId) {
		this.examId = examId;
	}

	public List<Double> getRightPercent() {
		return rightPercent;
	}

	public void setRightPercent(List<Double> rightPercent) {
		this.rightPercent = rightPercent;
	}
}
