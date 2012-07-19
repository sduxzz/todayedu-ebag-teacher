/**
 * PhotoBox.com.photobox.local.operateSD 
 * 2012 2012-4-27 下午2:22:23
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.Database;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;


/**
 * @author zhenzxie
 * 
 */
public class SDCheckerProxy {

	public static final int STORAGE_READY = 0;
	public static final int STORAGE_FAILURE = 1;
	private static final int CHECK_STORAGE = 2;
	private static final int SEND_RESULT = 3;
	private static int NUM_STORAGE_CHECKS = 25;
	
	private int mNumRetries = 0;
	private boolean mImageManagerHasStorageAfterDelay = false;
	private boolean mRequireWriteAccess = false;
	private HandlerThread mSDThread = null;
	private Handler mSDHandler = null;
	private Handler mCallbackHandler = null;
	
	public SDCheckerProxy() {
	
	}
	
	/**
	 * @param nUM_STORAGE_CHECKS
	 *            the nUM_STORAGE_CHECKS to set
	 */
	public static void setNUM_STORAGE_CHECKS(int nUM_STORAGE_CHECKS) {
	
		NUM_STORAGE_CHECKS = nUM_STORAGE_CHECKS;
	}
	
	/**
	 * check whether the SD card can be read or not after delay
	 * 
	 * @see SDCheckerProxy#hasStorageWithoutWriteAfterDelay(Handler)
	 */
	public void hasStorageWithoutWriteAfterDelay(Handler callbackHandler) {
	
		init();
		this.mCallbackHandler = callbackHandler;
		this.mRequireWriteAccess = false;
		sendInitialMessage();
	}
	
	/**
	 * check whether the SD card can be read an write or not after delay
	 * 
	 * @return true if SD card can be read,false otherwise.
	 * @see SDCheckerProxy#hasStorageWithWriteAfterDelay(Handler)
	 */
	public void hasStorageWithWriteAfterDelay(Handler callbackHandler) {
	
		init();
		this.mCallbackHandler = callbackHandler;
		this.mRequireWriteAccess = true;
		sendInitialMessage();
	}
	
	private void init() {
	
		mSDThread = new HandlerThread("SDMonitor");
		mSDThread.start();
		mSDHandler = new Handler(mSDThread.getLooper()) {
			
			/**
			 * @see android.os.Handler#handleMessage(android.os.Message)
			 */
			@Override
			public void handleMessage(Message msg) {
			
				switch (msg.what) {
					case CHECK_STORAGE:
						checkStorage();
						break;
					case SEND_RESULT:
						sendResult(msg.arg1);
						break;
				}
			}
		};
	}
	
	private void sendInitialMessage() {
	
		mNumRetries = 0;
		Message checkStorage = Message.obtain();
		checkStorage.what = CHECK_STORAGE;
		mSDHandler.sendMessage(checkStorage);
	}
	
	private void sendResult(int result) {
	
		Message msg = Message.obtain();
		msg.what = result;
		mCallbackHandler.sendMessage(msg);
	}
	
	private void checkStorage() {
	
		mNumRetries++;
		mImageManagerHasStorageAfterDelay = SDChecker
				.hasStorage(mRequireWriteAccess);
		if (!mImageManagerHasStorageAfterDelay
				&& mNumRetries < NUM_STORAGE_CHECKS) {
			mSDHandler.sendEmptyMessageDelayed(CHECK_STORAGE, 1000);
		} else {
			int result;
			if (mImageManagerHasStorageAfterDelay) {
				result = STORAGE_READY;
			} else {
				result = STORAGE_FAILURE;
			}
			Message msg = Message.obtain();
			msg.arg1 = result;
			msg.what = SEND_RESULT;
			mSDHandler.sendMessage(msg);
		}
	}

}
