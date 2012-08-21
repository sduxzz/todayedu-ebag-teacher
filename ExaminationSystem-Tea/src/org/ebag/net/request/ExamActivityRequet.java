package org.ebag.net.request;

import java.io.Serializable;
import java.util.List;

public class ExamActivityRequet implements Serializable{

	private static final long serialVersionUID = 6585994791277855983L;
	/**目标人*/
	public int uid;
	/**请求id，为null则返回全部对应未考*/
	public List<Integer> idList;
	/**请求字段,为空则返回全部字段*/
	public List<String> fieldList;
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
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

}
