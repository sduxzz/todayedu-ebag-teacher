package org.ebag.net.request;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

public class ExamRequet implements Serializable{

	private static final long serialVersionUID = 6585994791277855983L;
	/**用户id*/
	public int uid;
	/**是否老师*/
	public boolean isTeacher;
	/**目标班级*/
	public int classId;
	public List<Integer> stateList;
	/**请求id，为null则返回全部对应未考*/
	public List<Integer> idList;
	/**请求字段,为空则返回全部字段*/
	public List<String> fieldList;
	
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}
	public List<Integer> getIdList() {
		return idList;
	}
	public void setIdList(List<Integer> idList) {
		this.idList = idList;
	}
	public List<String> getFieldList() {
		return fieldList;
	}
	public void setFieldList(List<String> fieldList) {
		this.fieldList = fieldList;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public List<Integer> getStateList() {
		return stateList;
	}
	public void setStateList(List<Integer> stateList) {
		this.stateList = stateList;
	}
	public boolean isTeacher() {
		return isTeacher;
	}
	public void setTeacher(boolean isTeacher) {
		this.isTeacher = isTeacher;
	}

}
