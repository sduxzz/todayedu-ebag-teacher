package org.ebag.net.request;

import java.io.Serializable;
import java.util.List;

public class ExamActivityRequet implements Serializable{

	private static final long serialVersionUID = 6585994791277855983L;
	/**Ŀ����*/
	public int uid;
	/**����id��Ϊnull�򷵻�ȫ����Ӧδ��*/
	public List<Integer> idList;
	/**�����ֶ�,Ϊ���򷵻�ȫ���ֶ�*/
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
