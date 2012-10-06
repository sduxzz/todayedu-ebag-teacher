package com.todayedu.ebag.teacher.Database;

import java.io.File;

import android.os.Environment;
import android.util.Log;

import com.todayedu.ebag.teacher.DataSource.DataObj.Answer;

/**
 * SD��������
 * 
 * @author Craig Lee
 * 
 */
public class SDCard {

	private String SDPATH = "";
	private boolean SDCardAvailable = false;
	private boolean SDCardWriteable = false;

	public SDCard() {

		updateSDCardState();
		SDPATH = Environment.getExternalStorageDirectory().getPath();
	}

	/**
	 * ���SD���ö�д״̬
	 */
	public void updateSDCardState() {

		String state = Environment.getExternalStorageState();

		if (Environment.MEDIA_MOUNTED.equals(state))
			SDCardAvailable = SDCardWriteable = true;
		else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
			SDCardAvailable = true;
			SDCardWriteable = false;
		} else
			SDCardAvailable = SDCardWriteable = false;
	}

	public File getOutputMediaFile(String path) {
	
		File mediaStorageDir = new File(
		        Environment
		                .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
		        path);
		Log.i("SDCard", mediaStorageDir.getAbsolutePath());
		if (!mediaStorageDir.exists()) {
			if (!mediaStorageDir.mkdirs()) {
				Log.d("SDCard", "failed to create directory");
				return null;
			}
		}
		
		return mediaStorageDir;
	}

	/**
	 * �ж��ļ���Ŀ¼�Ƿ����
	 * 
	 * @param relativeFilePath
	 *            ��Ҫ�жϵ�����ļ���Ŀ¼�� ,�� / ��ͷ
	 * @return �ļ���Ŀ¼����ʱ������ true�����򷵻� false
	 */
	public boolean isFileExisted(String relativeFilePath) {

		if (relativeFilePath == null || "".equals(relativeFilePath))
			return false;
		return isFileExistedWithFullPath(SDPATH + relativeFilePath);
	}

	/**
	 * �ж��ļ���Ŀ¼�Ƿ����
	 * 
	 * @param fullFilePath
	 * @return
	 */
	public boolean isFileExistedWithFullPath(String fullFilePath) {

		boolean result = false;
		if (fullFilePath == null || "".equals(fullFilePath))
			return result;
		if (SDCardWriteable == true) {
			File file = new File(fullFilePath);
			result = file.exists();
			Log.i("SDCard", "SdCard_isFileExisted:" + fullFilePath + "    "
			        + result);
		}
		return result;
	}

	/**
	 * ��SD���ϴ���Ŀ¼
	 * 
	 * @param fileDir
	 *            Ŀ¼���� / ��ͷ
	 * @return
	 */
	public File createSDDir(String fileDir) {

		File dir = null;
		if (SDCardWriteable == true) {
			dir = new File(SDPATH + fileDir);
			if (isFileExisted(fileDir)) {
				return dir;
			}
			boolean result = dir.mkdirs();
			Log.i("SDCard", "createSDDir: result " + result);
		}

		return dir;
	}

	/**
	 * ��SD���ϴ����ļ�
	 * 
	 * @param fileName
	 * @return
	 * @throws Throwable
	 */
	public File createSDFile(String fileName) throws Throwable {

		File file = null;
		if (SDCardWriteable == true) {
			File dir = createSDDir(Answer.PATH.SUBDIR);
			if (dir != null) {
				if (isFileExisted(fileName)) {
					new File(fileName).delete();
				}
				file = new File(fileName);
				file.createNewFile();
			}
		}

		return file;
	}

	public String getSDPATH() {

		return SDPATH;
	}

	public boolean isSDCardAvailable() {

		return SDCardAvailable;
	}

	public boolean isSDCardWriteable() {

		return SDCardWriteable;
	}

}
