/*
 * ExaminationSystem-Tea com.todayedu.examinationsystem.teacher.UIModule
 * 2012 2012-5-31 ÏÂÎç3:44:11
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.UIModule;

import java.util.ArrayList;

import android.os.Bundle;

/**
 * it will notify the listeners which are registered on it notification list
 * 
 * @author zhenzxie
 * 
 */
public class MonitoredActivity extends BaseActivity {
	
	/**
	 * the listener used during the activity life cycle.
	 * 
	 * @author zhenzxie
	 * 
	 */
	public static interface LifeCycleListener {
		
		public void onActivityCreated(MonitoredActivity activity);
		
		public void onActivityStarted(MonitoredActivity activity);
		
		public void onActivityResumed(MonitoredActivity activity);
		
		public void onActivityPaused(MonitoredActivity activity);
		
		public void onActivityStopped(MonitoredActivity activity);
		
		public void onActivityDestroyed(MonitoredActivity activity);
		
	}
	
	/**
	 * a simple adapter for BaseActivityCallback
	 * 
	 * @author zhenzxie
	 * 
	 */
	public static class LifeCycleAdapter implements LifeCycleListener {
		
		@Override
		public void onActivityCreated(MonitoredActivity activity) {
		
		}
		
		@Override
		public void onActivityStarted(MonitoredActivity activity) {
		
		}
		
		@Override
		public void onActivityResumed(MonitoredActivity activity) {
		
		}
		
		@Override
		public void onActivityPaused(MonitoredActivity activity) {
		
		}
		
		@Override
		public void onActivityStopped(MonitoredActivity activity) {
		
		}
		
		@Override
		public void onActivityDestroyed(MonitoredActivity activity) {
		
		}
	}
	
	/**
     */
	private final ArrayList<LifeCycleListener> zListeners = new ArrayList<LifeCycleListener>();
	
	/**
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		for (LifeCycleListener listener : zListeners) {
			listener.onActivityCreated(this);
		}
	}
	
	/**
	 * @see android.app.Activity#onStart()
	 */
	@Override
	protected void onStart() {
	
		for (LifeCycleListener listener : zListeners) {
			listener.onActivityStarted(this);
		}
		super.onStart();
	}
	
	/**
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
	
		for (LifeCycleListener listener : zListeners) {
			listener.onActivityResumed(this);
		}
		super.onResume();
	}
	
	/**
	 * @see android.app.Activity#onPause()
	 */
	@Override
	protected void onPause() {
	
		for (LifeCycleListener listener : zListeners) {
			listener.onActivityPaused(this);
		}
		super.onPause();
	}
	
	/**
	 * @see android.app.Activity#onStop()
	 */
	@Override
	protected void onStop() {
	
		for (LifeCycleListener listener : zListeners) {
			listener.onActivityStopped(this);
		}
		super.onStop();
	}
	
	/**
	 * @see android.app.Activity#onDestroy()
	 */
	@Override
	protected void onDestroy() {
	
		for (LifeCycleListener listener : zListeners) {
			listener.onActivityDestroyed(this);
		}
		super.onDestroy();
	}
	
	/**
	 * add life cycle listener to the list
	 * 
	 * @param listener
	 */
	public void addLifeCycleListener(LifeCycleListener listener) {
	
		if (zListeners.contains(listener))
			return;
		zListeners.add(listener);
	}
	
	/**
	 * remove life cycle listener from the list
	 * 
	 * @param listener
	 */
	public void removeLifeCycleListener(LifeCycleListener listener) {
	
		zListeners.remove(listener);
	}
}
