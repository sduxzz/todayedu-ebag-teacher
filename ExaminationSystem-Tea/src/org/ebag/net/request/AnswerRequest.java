package org.ebag.net.request;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

public class AnswerRequest implements Serializable{

	private static final long serialVersionUID = -6885383837161170032L;
	/**Ŀ����*/
	public int uid;
	/**Ŀ�꿼��*/
	public int examId;
	/**������������id,Ϊnull�򷵻�ȫ��id�Ĵ�*/
	public List<Integer> idList;
	/**�����ֶ�,Ϊnull�򷵻�ȫ���ֶ�*/
	public List<Field> fieldList;
	
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
	public List<Field> getFieldList() {
		return fieldList;
	}
	public void setFieldList(List<Field> fieldList) {
		this.fieldList = fieldList;
	}
	
}