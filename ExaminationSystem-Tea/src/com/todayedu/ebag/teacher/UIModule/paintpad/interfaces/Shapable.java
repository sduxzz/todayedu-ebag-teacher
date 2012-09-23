package com.todayedu.ebag.teacher.UIModule.paintpad.interfaces;

import com.todayedu.ebag.teacher.UIModule.paintpad.painttools.FirstCurrentPosition;

import android.graphics.Path;

public interface Shapable {
	public Path getPath();

	public FirstCurrentPosition getFirstLastPoint();

	public void setShap(ShapesInterface shape);
}
