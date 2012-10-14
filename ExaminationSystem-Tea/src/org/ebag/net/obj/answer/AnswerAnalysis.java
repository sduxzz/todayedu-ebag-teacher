package org.ebag.net.obj.answer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AnswerAnalysis implements Serializable{

	private static final long serialVersionUID = 1450336001542836446L;

	public int examId;
	
	public List<Double> rightPercent;

	//����ѧ���ܷ�����
	public List<GradeObj> allGradeList;
	//��͡���߳ɼ�
	public GradeObj minGrade;
	public GradeObj maxGrade;
	//keyΪ��Ŀid,valueΪ����Ŀ����ѧ���÷����
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
