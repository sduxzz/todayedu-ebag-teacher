/*
 * ExaminationSystem-Tea com.todayedu.examinationsystem.teacher.UIModule
 * 2012 2012-5-31 ÉÏÎç12:18:32
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.UIModule;

/**
 * the factory for BaseHeaderView
 * 
 * @author zhenzxie
 * 
 */
public class HeaderViewFactory {
	
	public static final int HV_1_SIG = 1;
	public static final int HV_2_SIG = 2;
	public static final int HV_3_SIG = 3;
	
	/**
	 * create BaseHeaderView with specific resource
	 * 
	 * @param layout_id
	 * @param resource_id
	 * @param which
	 * @return BaseHeaderView
	 */
	public static BaseHeaderView createHeaderView(int layout_id,
	        int resource_id, int which) {
	
		BaseHeaderView headerView = null;
		switch (which) {
			case HV_1_SIG:
				headerView = new HeaderView1(layout_id, resource_id);
				break;
			case HV_2_SIG:
				headerView = new HeaderView2(layout_id, resource_id);
				break;
			case HV_3_SIG:
				headerView = new HeaderView3(layout_id, resource_id);
				break;
		}
		return headerView;
	}
}
