/*
 * ExaminationSystem-Tea com.todayedu.examinationsystem.teacher.Util
 * 2012 2012-5-29 ����10:35:42
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.Network;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;

/**
 * @author zhenzxie
 * 
 */
public class NetWorkUtil {
	
	public static ConnectivityManager getConManager(Context context) {
	
		return (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
	}

	public static boolean isNetworkAvailable(Context context) {
	
		ConnectivityManager manager = getConManager(context);
		NetworkInfo[] infos = manager.getAllNetworkInfo();
		for (NetworkInfo info : infos) {
			if (info != null)
				if (info.isAvailable())
					return true;
		}
		return false;
	}
	
	public static boolean isConnected(Context context) {
	
		ConnectivityManager manager = getConManager(context);
		NetworkInfo info = manager.getActiveNetworkInfo();
		if (info == null)
			return false;
		return info.isConnected();
	}
	
	public static boolean isConnectedOrConnecting(Context context) {
	
		ConnectivityManager manager = getConManager(context);
		NetworkInfo info = manager.getActiveNetworkInfo();
		return info.isConnectedOrConnecting();
	}

	public static void setWireless(Context context) {
	
		context.startActivity(createIntent(Settings.ACTION_WIRELESS_SETTINGS));
	}
	
	public static void setWirelessForResult(Activity activity, int requestCode) {

		activity.startActivityForResult(
				createIntent(Settings.ACTION_WIRELESS_SETTINGS), requestCode);
	}
	
	public static void setWIFI(Context context) {
	
		context.startActivity(createIntent(Settings.ACTION_WIFI_SETTINGS));
	}

	public static void setWIFIForResult(Activity activity, int requestCode) {
	
		activity.startActivityForResult(
				createIntent(Settings.ACTION_WIFI_SETTINGS), requestCode);
	}

	private static Intent createIntent(String str) {
	
		return new Intent(str);
	}

}
