/**
 * PhotoBox.com.photobox.local.operateSD 
 * 2012 2012-4-26 下午8:05:38
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.Database;

import java.io.File;

import android.os.Environment;
import android.util.Log;


/**
 * @author zhenzxie
 *
 */
public class SDChecker {

	private final static String TAG = "SDChecker";

	private SDChecker() {

	}

	/**
	 * check whether the SD card can be read or not.
	 * 
	 * @return true if SD card can be read,false otherwise.
	 * @see SDChecker#hasStorageWithWrite()
	 */
	public static boolean hasStorageWithoutWrite() {

		return hasStorage(false);
	}

	/**
	 * check whether the SD card can be read an write or not.
	 * 
	 * @return true if SD card can be read,false otherwise.
	 * @see SDChecker#hasStorageWithoutWrite()
	 */
	public static boolean hasStorageWithWrite() {

		return hasStorage(true);
	}

	/**
	 * 
	 * @param requireWriteAccess
	 * @return
	 */
	protected static boolean hasStorage(boolean requireWriteAccess) {

		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state)) {
			if (requireWriteAccess) {
				boolean writable = checkFsWritable();
				return writable;
			} else {
				Log.i(TAG, "SD card can be read.");
				return true;
			}
		} else if (!requireWriteAccess
				&& Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
			Log.i(TAG, "SD card can be read.");
			return true;
		}
		Log.i(TAG, "SD card can't be read.");
		return false;
	}

	/**
	 * Create a temporary file to see whether a volume is really writable. It's
	 * important not to put it in the root directory which may have a limit on
	 * the number of files.
	 * 
	 * @return
	 */
	private static boolean checkFsWritable() {

		String directoryName = Environment.getExternalStorageDirectory()
				.toString() + "/DCIM";
		File directory = new File(directoryName);
		if (!directory.isDirectory()) {
			if (!directory.mkdirs()) {
				return false;
			}
		}
		return directory.canWrite();
	}

}
