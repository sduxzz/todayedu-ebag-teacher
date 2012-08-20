package com.todayedu.ebag.teacher.Network;

import org.ebag.net.obj.I.url;

import android.util.Log;

public class UrlBuilder {
	
	public static final String TAG = "UrlBuilder";
	private static final String URL = "http://211.87.227.10:8080/ServerOfEbag/index.jsp?pid=";
	private static final String TYPE = "&type=";
	
	// ��Ŀ����
	public static String problemContentUrl(int pid) {
	
		return problemUrl(pid, url.problem);
	}
	
	// ��ʾ
	public static String problemhintUrl(int pid) {
	
		return problemUrl(pid, url.hint);
	}
	
	// ��
	public static String problemAnswerUrl(int pid) {
	
		return problemUrl(pid, url.ans);
	}
	
	// ����
	public static String problemAnalysis(int pid) {
	
		return problemUrl(pid, url.analysis);
	}
	
	// �Ѷ�����
	public static String problemDifficultyUrl(int pid) {
	
		return problemUrl(pid, url.difficulty);
	}
	
	// ���췽��
	public static String problemAspectUrl(int pid) {
	
		return problemUrl(pid, url.aspect);
	}
	
	// ��Ŀ���ճ̶�Ҫ��
	public static String problemRequestUrl(int pid) {
	
		return problemUrl(pid, url.request);
	}

	private static String problemUrl(int pid, String type) {
	
		String result = null;
		StringBuffer buffer = new StringBuffer(URL);
		buffer.append(String.valueOf(pid));
		buffer.append(TYPE);
		buffer.append(type);
		result = buffer.toString();
		Log.i(TAG, "problemUrl: url " + result);
		return result;
	}

}