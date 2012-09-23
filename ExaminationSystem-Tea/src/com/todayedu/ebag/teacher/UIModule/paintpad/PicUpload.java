package com.todayedu.ebag.teacher.UIModule.paintpad;

import it.sauronsoftware.ftp4j.FTPClient;

import java.io.File;
import java.util.Date;

import org.ebag.net.obj.I;

public class PicUpload {
	/**
	 * step1��ȡ��һ���Ϸ����ϴ��ļ���
	 * @param uid
	 * @return
	 */
	public static String getFileName(int uid){
		String file_name = uid+ "_"+ new Date().getTime()+ ".png";
		return file_name;
	}
	/**
	 * step2���ϴ�һ���������õ��ļ�
	 * @param f
	 */
	public static void upload(File f) {	
		try {
			FTPClient client = new FTPClient();
			client.connect(I.tupload.ftp_site, I.tupload.ftp_port);
			client.login("picup", I.tupload.ftp_pwd);
			client.upload(f);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}

