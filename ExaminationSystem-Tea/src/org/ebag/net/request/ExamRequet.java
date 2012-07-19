package org.ebag.net.request;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

public class ExamRequet implements Serializable{

	private static final long serialVersionUID = 6585994791277855983L;
	/**目标班级*/
	public int classId;
	public List<Integer> stateList;
	/**请求id，为null则返回全部对应未考*/
	public List<Integer> idList;
	/**请求字段,为空则返回全部字段*/
	public List<Field> fieldList;
	
	public int getUid() {
		return classId;
	}
	public void setUid(int classId) {
		this.classId = classId;
	}
	public List<Integer> getIdList() {
		return idList;
	}
	public void setIdList(List<Integer> idList) {
		this.idList = idList;
	}
	public List<Field> getFieldList() {
		return fieldList;
	}
	public void setFieldList(List<Field> fieldList) {
		this.fieldList = fieldList;
	}

}
