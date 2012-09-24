package com.todayedu.ebag.teacher.Database;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.os.Environment;
import android.util.Log;

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
			Log.i("SDCard", "SdCard_isFileExisted:" + fullFilePath);
			File file = new File(fullFilePath);
			result = file.exists();
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
			dir.mkdirs();
		}

		return dir;
	}

	/**
	 * ��SD���ϴ����ļ�
	 * 
	 * @param path
	 *            Ҫ�������ļ����·��,�� / ��ͷ
	 * @param fileName
	 * @return
	 * @throws Throwable
	 */
	public File createSDFile(String path, String fileName) throws Throwable {
		File file = null;
		if (SDCardWriteable == true) {
			File dir = createSDDir(path);
			if (dir != null) {
				if (isFileExisted(path + File.separator + fileName)) {
					new File(SDPATH + path + File.separator + fileName)
							.delete();
				}
				file = new File(SDPATH + path + File.separator + fileName);
				file.createNewFile();
			}
		}

		return file;
	}

	/**
	 * �����е�����д��SD����
	 * 
	 * @param filePath
	 *            �������ļ���·��,�� / ��ͷ
	 * @param fileName
	 *            �������ļ���
	 * @param inputStream
	 *            Ҫ���ж�ȡ���ݵ�������
	 * @return �������ļ�
	 */
	public File writeStream2SDCard(String filePath, String fileName,
			InputStream inputStream) throws Throwable {
		File resultFile = null;
		byte[] buffer = new byte[4 * 1024];

		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		resultFile = createSDFile(filePath, fileName);
		if (resultFile != null) {
			try {
				bos = new BufferedOutputStream(new FileOutputStream(resultFile));
				bis = new BufferedInputStream(inputStream);
				int len = 0;
				while ((len = bis.read(buffer)) != -1) {
					bos.write(buffer, 0, len);
				}
				bos.flush();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (bos != null) {
						bos.close();
						bos = null;
					}
					if (bis != null) {
						bis.close();
						bis = null;
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

		return resultFile;
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
