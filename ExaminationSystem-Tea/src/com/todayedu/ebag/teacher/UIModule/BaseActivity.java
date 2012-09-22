/*
 * ExaminationSystem-Tea com.todayedu.examinationsystem.teacher.UIModule
 * 2012 2012-5-28 上午12:01:01
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.UIModule;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.todayedu.ebag.teacher.DataSource.DSCallback;

/**
 * the base activity i used on my application it supplies some useful method
 * that call safely through other thread. it also will notify the listeners
 * which are registered on it notification list
 * 
 * @author zhenzxie
 * 
 */
public class BaseActivity extends Activity implements OnItemClickListener, DSCallback {
	
	protected String TAG = this.getClass().getSimpleName();
	private ArrayList<LifeCycleListener> zListeners = new ArrayList<LifeCycleListener>();
	
	/**
     * the listener used during the activity life cycle.
     * 
     * @author zhenzxie
     * 
     */
	public interface LifeCycleListener {
    	
    	public void onActivityCreated(BaseActivity activity);
    	
    	public void onActivityStarted(BaseActivity activity);
    	
    	public void onActivityResumed(BaseActivity activity);
    	
    	public void onActivityPaused(BaseActivity activity);
    	
    	public void onActivityStopped(BaseActivity activity);
    	
    	public void onActivityDestroyed(BaseActivity activity);
    	
    }

	/**
	 * a simple adapter for LifeCycleListener
	 * 
	 * @author zhenzxie
	 * 
	 */
	public class LifeCycleAdapter implements LifeCycleListener {
    	
    	@Override
    	public void onActivityCreated(BaseActivity activity) {
    	
    	}
    	
    	@Override
    	public void onActivityStarted(BaseActivity activity) {
    	
    	}
    	
    	@Override
    	public void onActivityResumed(BaseActivity activity) {
    	
    	}
    	
    	@Override
    	public void onActivityPaused(BaseActivity activity) {
    	
    	}
    	
    	@Override
    	public void onActivityStopped(BaseActivity activity) {
    	
    	}
    	
    	@Override
    	public void onActivityDestroyed(BaseActivity activity) {
    	
    	}
    }

	/**
	 * {@link #showToast(String, int)}
	 * 
	 * @param message
	 */
	public void showToast(String message) {
	
		showToast(message, 0);
	}
	
	/**
	 * show a toast with specific message and duration
	 * 
	 * @param message
	 * @param duration
	 */
	public void showToast(final String message, final int duration) {
		
		this.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
			
				Toast.makeText(BaseActivity.this, message, duration).show();
			}
		});
	}
	
	/**
	 * {@link #showToast(int, int)}
	 * 
	 * @param resourceId
	 */
	public void showToast(int resourceId) {
	
		showToast(resourceId, 0);
	}
	
	/**
	 * show a toast with specific message and duration
	 * 
	 * @param resourceId
	 * @param duration
	 */
	public void showToast(final int resourceId, final int duration) {
	
		this.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
			
				Toast.makeText(BaseActivity.this, resourceId, duration).show();
			}
		});
	}

	public void start(Class<?> cls) {
	
		Intent intent = new Intent(this, cls);
		startActivity(intent);
	}

	@Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
            long id) {
    
    }

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

	/**
     * @param lv
     * @param headerView
     */
	protected void resetListView(ListView lv, View headerView,
	        BaseAdapter adapter) {
    
        lv.addHeaderView(headerView);
    	lv.setOnItemClickListener(this);
    	lv.setBackgroundColor(Color.WHITE);
    	lv.setCacheColorHint(Color.WHITE);
		lv.setAdapter(adapter);
    }

	/**
	 * @see com.todayedu.ebag.teacher.DataSource.DSCallback#onLoadSuccess(java.lang.Object)
	 */
	@Override
	public void onLoadSuccess(Object object) {
	
		Log.i(TAG, "onLoadSuccess");
	}
	
	/**
	 * @see com.todayedu.ebag.teacher.DataSource.DSCallback#onLoadFailed(java.lang.Throwable)
	 */
	@Override
	public void onLoadFailed(Throwable throwable) {
	
		Log.i(TAG, "onLoadFailed");
		if (throwable != null) {
			Log.i(TAG, throwable.getMessage());
		}
		showToast("加载数据失败");
	}

}
