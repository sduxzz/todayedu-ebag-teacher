/*
 * ExaminationSystem-Tea com.todayedu.examinationsystem.teacher.DataSource
 * 2012 2012-5-31 ÏÂÎç3:30:23
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.DataSource;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import android.content.Context;
import android.widget.Toast;

import com.todayedu.ebag.teacher.R;
import com.todayedu.ebag.teacher.DataAdapter.BaseDataAdapter;
import com.todayedu.ebag.teacher.Network.NetWorkUtil;



/**
 * @author zhenzxie
 * 
 */
public abstract class BaseDataSource extends Observable {
	
	protected String TAG = this.getClass().getSimpleName();
	protected List<Data> zList = new ArrayList<Data>();
	protected Class<? extends Data> zClass;
	protected BaseDataAdapter zAdapter;

	public BaseDataSource(Class<? extends Data> cl) {
	
		this.zClass = cl;
	}

	/**
	 * 
	 * @see com.todayedu.ebag.teacher.DataSource.DataSource#save(android.content.Context)
	 */
	public boolean save(Context context) {
	
		Iterator<Data> iterator = zList.iterator();
		boolean result = true;
		while (iterator.hasNext()) {
			if (result) {
				result = iterator.next().save(context);
			} else {
				iterator.next().save(context);
			}
		}
		return result;
	}
	
	/**
	 * load the data from the local database.
	 * 
	 * @param context
	 */
	public abstract void localload(Context context);
	
	/**
	 * down load the data from server
	 * 
	 * @param context
	 */
	public abstract void download(Context context);
	
	/**
	 * load the data from server if network is available or local database if
	 * there isn't network connection.
	 * 
	 * @param context
	 */
	public final void load(Context context) {
	
		if (NetWorkUtil.isConnected(context)) {
			download(context);
		} else {
			Toast.makeText(context, R.string.sp_networkalter, Toast.LENGTH_LONG)
					.show();
			localload(context);
		}
	}

	/**
	 * the subclass can call this method for loading the data from local
	 * database conveniently.
	 * 
	 * @param context
	 * @param sql
	 * @param selectArgs
	 */
	protected void localload(Context context, String sql, String[] selectArgs) {
	
		DataSourceLoader loader = new DataSourceLoader(this, context,
				sql, selectArgs);
		loader.execute(context);
	}

	public void notifyDataChange() {
    
    	setChanged();
    	notifyObservers();
    	clearChanged();
    }

	/**
     * @see java.util.Observable#addObserver(java.util.Observer)
     */
    @Override
    public void addObserver(Observer observer) {
    
    	super.addObserver(observer);
    	zAdapter = (BaseDataAdapter) observer;
    }

	public void store(List<Data> list) {
    
    	zList = list;
    }

	public List<Data> pick() {
    
    	return zList;
    }

	public BaseDataAdapter getAdapter() {
    
    	return zAdapter;
    }

	/**
     * @return the zClass
     */
    public Class<? extends Data> getzClass() {
    
    	return zClass;
    }

	/**
     * @param zClass
     *            the zClass to set
     */
    public void setzClass(Class<? extends Data> zClass) {
    
    	this.zClass = zClass;
    }

}