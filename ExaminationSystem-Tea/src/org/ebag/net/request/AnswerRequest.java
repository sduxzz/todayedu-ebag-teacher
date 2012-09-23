package org.ebag.net.request;

import java.io.Serializable;
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
	
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
	
		return "AnswerRequest [uid=" + uid + ", examId=" + examId + ", idList="
		        + idList + ", fieldList=" + fieldList + "]";
	}
	
}
