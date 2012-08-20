/*
 * ExaminationSystem-Tea com.todayedu.examinationsystem.teacher.UIModule
 * 2012 2012-6-1 ����2:45:32
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.DataAdapter;

import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.todayedu.ebag.teacher.DataSource.Data;
import com.todayedu.ebag.teacher.DataSource.DataSource;

/**
 * @author zhenzxie
 * 
 */
public class BaseDataAdapter extends BaseAdapter implements Observer {
	
	protected DataSource zDataSource;
	
	protected int[] zIds;
	protected String[] zKeys;
	protected int mResource;
	protected int mDropDownResource;
	protected LayoutInflater mInflater;
	
	/**
	 * 
	 * @param context
	 * @param dataSource
	 * @param resource
	 * @param keys
	 *            the values of keys must be the field of {@link Data} used in
	 *            dataSource
	 * @param ids
	 */
	public BaseDataAdapter(Context context, DataSource dataSource,
			int resource, String[] keys, int[] ids) {
	
		zDataSource = dataSource;
		mResource = mDropDownResource = resource;
		zKeys = keys;
		zIds = ids;
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		dataSource.addObserver(this);
	}
	
	/**
	 * @see android.widget.Adapter#getCount()
	 */
	public int getCount() {
	
		return zDataSource.pick().size();
	}
	
	/**
	 * @see android.widget.Adapter#getItem(int)
	 */
	public Object getItem(int position) {
	
		return zDataSource.pick().get(position);
	}
	
	/**
	 * @see android.widget.Adapter#getItemId(int)
	 */
	public long getItemId(int position) {
	
		return position;
	}
	
	/**
	 * @see android.widget.Adapter#getView(int, View, ViewGroup)
	 */
	public View getView(int position, View convertView, ViewGroup parent) {
	
		return createViewFromResource(position, convertView, parent, mResource);
	}
	
	private View createViewFromResource(int position, View convertView,
			ViewGroup parent, int resource) {
	
		View v;
		if (convertView == null) {
			v = mInflater.inflate(resource, parent, false);
		} else {
			v = convertView;
		}
		
		bindView(position, v);
		
		return v;
	}
	
	/**
	 * <p>
	 * Sets the layout resource to create the drop down views.
	 * </p>
	 * 
	 * @param resource
	 *            the layout resource defining the drop down views
	 * @see #getDropDownView(int, android.view.View, android.view.ViewGroup)
	 */
	public void setDropDownViewResource(int resource) {
	
		this.mDropDownResource = resource;
	}
	
	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
	
		return createViewFromResource(position, convertView, parent,
				mDropDownResource);
	}
	
	protected void bindView(int position, View view) {
	
		final Map<String, String> dataSet = zDataSource.pick().get(position)
				.changeToMap(zKeys);
		if (dataSet == null) {
			return;
		}
		
		final String[] from = zKeys;
		final int[] to = zIds;
		final int count = to.length;
		
		for (int i = 0; i < count; i++) {
			final View v = view.findViewById(to[i]);
			if (v != null) {
				final Object data = dataSet.get(from[i]);
				String text = data == null ? "" : data.toString();
				
				// Note:in this case,v only can be TextView,but these follow code can be useful,so I retain them.
				setViewText((TextView) v, text);
				
				// if (v instanceof Checkable) {
				// if (data instanceof Boolean) {
				// ((Checkable) v).setChecked((Boolean) data);
				// } else if (v instanceof TextView) {
				// // Note: keep the instanceof TextView check at the
				// // bottom of these
				// // ifs since a lot of views are TextViews (e.g.
				// // CheckBoxes).
				// setViewText((TextView) v, text);
				// } else {
				// throw new IllegalStateException(v.getClass().getName()
				// + " should be bound to a Boolean, not a "
				// + (data == null ? "<unknown type>"
				// : data.getClass()));
				// }
				// } else if (v instanceof TextView) {
				// // Note: keep the instanceof TextView check at the
				// // bottom of these
				// // ifs since a lot of views are TextViews (e.g.
				// // CheckBoxes).
				// setViewText((TextView) v, text);
				// } else if (v instanceof ImageView) {
				// if (data instanceof Integer) {
				// setViewImage((ImageView) v, (Integer) data);
				// } else {
				// setViewImage((ImageView) v, text);
				// }
				// } else {
				// throw new IllegalStateException(v.getClass().getName()
				// + " is not a "
				// + " view that can be bounds by this SimpleAdapter");
				// }

			}
		}
	}
	
	/**
	 * Called by bindView() to set the image for an ImageView but only if there
	 * is no existing ViewBinder or if the existing ViewBinder cannot handle
	 * binding to an ImageView.
	 * 
	 * This method is called instead of {@link #setViewImage(ImageView, String)}
	 * if the supplied data is an int or Integer.
	 * 
	 * @param v
	 *            ImageView to receive an image
	 * @param value
	 *            the value retrieved from the data set
	 * 
	 * @see #setViewImage(ImageView, String)
	 */
	public void setViewImage(ImageView v, int value) {
	
		v.setImageResource(value);
	}
	
	/**
	 * Called by bindView() to set the image for an ImageView but only if there
	 * is no existing ViewBinder or if the existing ViewBinder cannot handle
	 * binding to an ImageView.
	 * 
	 * By default, the value will be treated as an image resource. If the value
	 * cannot be used as an image resource, the value is used as an image Uri.
	 * 
	 * This method is called instead of {@link #setViewImage(ImageView, int)} if
	 * the supplied data is not an int or Integer.
	 * 
	 * @param v
	 *            ImageView to receive an image
	 * @param value
	 *            the value retrieved from the data set
	 * 
	 * @see #setViewImage(ImageView, int)
	 */
	public void setViewImage(ImageView v, String value) {
	
		try {
			v.setImageResource(Integer.parseInt(value));
		} catch (NumberFormatException nfe) {
			v.setImageURI(Uri.parse(value));
		}
	}
	
	/**
	 * Called by bindView() to set the text for a TextView but only if there is
	 * no existing ViewBinder or if the existing ViewBinder cannot handle
	 * binding to an TextView.
	 * 
	 * @param v
	 *            TextView to receive text
	 * @param text
	 *            the text to be set for the TextView
	 */
	public void setViewText(TextView v, String text) {

		v.setText(text);
	}
	
	@Override
	public void update(Observable observable, Object data) {
	
		notifyDataSetChanged();
	}
	
	/**
	 * @return the zDataSource
	 */
	public DataSource getzDataSource() {
	
		return zDataSource;
	}
	
	/**
	 * @param zDataSource
	 *            the zDataSource to set
	 */
	public void setzDataSource(DataSource zDataSource) {
	
		this.zDataSource = zDataSource;
	}
	
	/**
	 * @return the zKeys
	 */
	public String[] getzKeys() {
	
		return zKeys;
	}
	
	/**
	 * @param zKeys
	 *            the zKeys to set
	 */
	public void setzKeys(String[] zKeys) {
	
		this.zKeys = zKeys;
	}
}
