/**
 * PhotoBox.com.photobox.local.operateSD 
 * 2012 2012-4-17 下午11:33:39
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.Database;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.net.Uri;
import android.os.Environment;
import android.util.Log;

/**
 * @author zhenzxie
 * 
 */
public class SDWriter {

	public static Uri getOutputMediaFileUri() {
	
		File file = getOutputMediaTempFile();
		if (file == null)
			return null;
		else
			return Uri.fromFile(file);
	}
	
	private static File getOutputMediaTempFile() {
	
		return getOutputMediaFile("todayedutemp");
	}
	
	public static File getOutputMediaFile() {
	
		return getOutputMediaFile("todayedu");
	}
	
	private static File getOutputMediaFile(String path) {
	
		File mediaStorageDir = new File(
				Environment
						.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
				path);
		if (!mediaStorageDir.exists()) {
			if (!mediaStorageDir.mkdirs()) {
				Log.d("PhotoboxTemp", "failed to create directory");
				return null;
			}
		}
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
				.format(new Date());
		File mediaFile = new File(mediaStorageDir.getPath() + File.separator
				+ "IMG_" + timeStamp + ".jpg");
		return mediaFile;
	}
}
