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
			Log.i("SDCard", "SdCard_isFileExisted:" + fullFilePath);
			File file = new File(fullFilePath);
			result = file.exists();
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
			dir.mkdirs();
		}

		return dir;
	}

	/**
	 * 在SD卡上创建文件
	 * 
	 * @param path
	 *            要创建的文件相对路径,以 / 开头
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
	 * 将流中的数据写到SD卡中
	 * 
	 * @param filePath
	 *            创建的文件的路径,以 / 开头
	 * @param fileName
	 *            创建的文件名
	 * @param inputStream
	 *            要从中读取数据的输入流
	 * @return 创建的文件
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
