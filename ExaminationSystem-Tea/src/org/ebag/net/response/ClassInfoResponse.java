package org.ebag.net.response;

import java.io.Serializable;
import java.util.List;

public class ClassInfoResponse implements Serializable {

	private static final long serialVersionUID = 5477754702005982381L;
	
	public List<Integer> teacherIdList;
	public List<Integer> studentIdList;
	public String className;

	public List<Integer> getTeacherIdList() {

		return teacherIdList;
	}

	public void setTeacherIdList(List<Integer> teacherIdList) {

		this.teacherIdList = teacherIdList;
	}

	public List<Integer> getStudentIdList() {

		return studentIdList;
	}

	public void setStudentIdList(List<Integer> studentIdList) {

		this.studentIdList = studentIdList;
	}
}
