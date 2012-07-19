/*
 * ExaminationSystem-Tea com.todayedu.ebag.teacher.DataSource DataSource.java
 * 2012 2012-7-15 ÏÂÎç6:22:32
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.DataSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import android.content.Context;

import com.todayedu.ebag.teacher.DataAdapter.BaseDataAdapter;

/**
 * @author zhenzxie
 * 
 */
public abstract class DataSource extends Observable {
	
	protected String TAG = this.getClass().getName();
	protected List<Data> zList = new ArrayList<Data>();
	protected Class<? extends Data> zClass;
	protected BaseDataAdapter zAdapter;

	public DataSource(Class<? extends Data> cl) {
	
		this.zClass = cl;
	}

	public abstract boolean save(Context context);

	public abstract DataSource convert();

	public void notifyDataChange() {
	
		setChanged();
		notifyObservers();
		clearChanged();
	}

	public void store(List<Data> list) {
	
		zList = list;
	}
	
	public List<Data> pick() {
	
		return zList;
	}

	/**
	 * @see java.util.Observable#addObserver(java.util.Observer)
	 */
	@Override
	public void addObserver(Observer observer) {
	
		super.addObserver(observer);
		zAdapter = (BaseDataAdapter) observer;
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