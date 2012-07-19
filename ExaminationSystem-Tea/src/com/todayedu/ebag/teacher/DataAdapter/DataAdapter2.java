/*
 * ExaminationSystem-Tea com.todayedu.ebag.teacher.DataAdapter PCommentAdapter.java
 * 2012 2012-7-6 下午7:13:39
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.DataAdapter;

import java.util.Map;

import org.ebag.net.obj.I.choice;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.todayedu.ebag.teacher.Constants.State;
import com.todayedu.ebag.teacher.Constants.StateStr;
import com.todayedu.ebag.teacher.DataSource.DataSource;

/**
 * @author zhenzxie
 * 
 */
public class DataAdapter2 extends BaseDataAdapter {
	
	public DataAdapter2(Context context, DataSource dataSource, int resource,
			String[] from, int[] to) {
	
		super(context, dataSource, resource, from, to);
	}
	
	/**
	 * @see com.todayedu.ebag.teacher.DataAdapter.BaseDataAdapter#bindView(int,
	 *      android.view.View)
	 */
	@Override
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

				if (i == 1) {// NOTE:change the string which present the state
								// of exams and the index of zIds is 1.
					if (text.equals(String
							.valueOf(choice.answerState_waitComment))) {
						text = StateStr.COMMENT;
					} else if (text.equals(String.valueOf(State.COMMENTED))) {
						text = StateStr.COMMENTED;
					} else if (text.equals(String
							.valueOf(choice.answerState_waitMark))) {
						text = StateStr.CORRECT;
					} else if (text.equals(String.valueOf(State.CORRECTED))) {
						text = StateStr.CORRECTED;
					}
				}
				setViewText((TextView) v, text);
			}
		}
	}
}
