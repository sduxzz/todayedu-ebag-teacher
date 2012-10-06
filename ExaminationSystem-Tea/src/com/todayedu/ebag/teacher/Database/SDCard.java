package com.todayedu.ebag.teacher.Database;

import java.io.File;

import android.os.Environment;
import android.util.Log;

import com.todayedu.ebag.teacher.DataSource.DataObj.Answer;

/**
 * SD卡处理类
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
	 * 检测SD卡得读写状态
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
	 * 判断文件或目录是否存在
	 * 
	 * @param relativeFilePath
	 *            需要判断的相对文件或目录名 ,以 / 开头
	 * @return 文件或目录存在时，返回 true；否则返回 false
	 */
	public boolean isFileExisted(String relativeFilePath) {

		if (relativeFilePath == null || "".equals(relativeFilePath))
			return false;
		return isFileExistedWithFullPath(SDPATH + relativeFilePath);
	}

	/**
	 * 判断文件或目录是否存在
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
	 * 在SD卡上创建目录
	 * 
	 * @param fileDir
	 *            目录名以 / 开头
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
	 * 在SD卡上创建文件
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
