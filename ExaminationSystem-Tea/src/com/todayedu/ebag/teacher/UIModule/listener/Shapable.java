package com.todayedu.ebag.teacher.UIModule.listener;

import android.graphics.Path;

import com.todayedu.ebag.teacher.UIModule.pen.FirstCurrentPosition;

public interface Shapable {
	public Path getPath();

	public FirstCurrentPosition getFirstLastPoint();

	void setShap(ShapesInterface shape);
}
