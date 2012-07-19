/**
 * PhotoBox.com.photobox.local.operateSD 
 * 2012 2012-4-17 下午11:33:21
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.Database;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;

/**
 * @author zhenzxie
 * 
 */
public class SDReader {
	
	private static SDReader mReader = new SDReader();
	
	private SDReader() {
	
	}
	
	public static SDReader getReader() {
	
		return mReader;
	}
	
	/**
	 * We need to notify the MediaScanner when a new file is created. In this
	 * way all the gallery applications will be notified too.
	 * 
	 * @param file
	 */
	public static void updateMedia(Context context, String filepath) {
	
		MediaScannerConnection.scanFile(context.getApplicationContext(),
		        new String[] { filepath }, null, null);
	}
	
	/**
	 * get image by path
	 * 
	 * @param path
	 * @return
	 */
	public static Bitmap getImageByPath(String path) {
	
		Bitmap bitmap = BitmapFactory.decodeFile(path);
		return bitmap;
	}

}
