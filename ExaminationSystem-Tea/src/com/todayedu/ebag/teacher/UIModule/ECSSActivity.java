/*
 * ExaminationSystem-Tea com.todayedu.ebag.teacher.UIModule
 * 2012 2012-7-2 锟斤拷锟斤拷6:45:31
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.UIModule;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.todayedu.ebag.teacher.R;
import com.todayedu.ebag.teacher.TempData;

/**
 * 学生某道题的批改界面
 * 
 * @author zhenzxie
 * @deprecated
 * @see ECSSFragment
 */
public class ECSSActivity extends BaseActivity {
	
	/**
	 * @see com.todayedu.ebag.teacher.UIModule.MonitoredActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ecss);
		
		findView();
		initPaintView();
		getAndSet();
		setButton();
	}
	
	/**
     * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    
	    return super.onCreateOptionsMenu(menu);
    }

	/**
     * @see android.app.Activity#onMenuItemSelected(int, android.view.MenuItem)
     */
    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
    
	    return super.onMenuItemSelected(featureId, item);
    }

	/**
     * @see android.app.Activity#onCreateContextMenu(android.view.ContextMenu, android.view.View, android.view.ContextMenu.ContextMenuInfo)
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
            ContextMenuInfo menuInfo) {
    
	    super.onCreateContextMenu(menu, v, menuInfo);
    }

	private void findView() {

		number_tv2 = (TextView) findViewById(R.id.ecss_tv2);
		score_tv4 = (TextView) findViewById(R.id.ecss_tv4);
		state_tv6 = (TextView) findViewById(R.id.ecss_tv6);
		previous_b1 = (Button) findViewById(R.id.ecss_b1);
		next_b3 = (Button) findViewById(R.id.ecss_b3);
		content_wv1 = (WebView) findViewById(R.id.ecss_wv1);
		answer_wv2 = (WebView) findViewById(R.id.ecss_wv2);
		answer_iv = (ImageView) findViewById(R.id.ecss_ll_iv);
		paint_ll = (LinearLayout) findViewById(R.id.ecss_ll_ll);
		layout = (LinearLayout) findViewById(R.id.ecss_ll);
		layout.setDrawingCacheQuality(LinearLayout.DRAWING_CACHE_QUALITY_HIGH);
	}
	
	private void initPaintView() {
	
		mPaintView = new PaintView(this);
		paint_ll.addView(mPaintView);
	}

	public void onComfire(View view) {
	
		String result = score_et1.getText().toString();
		double score = Double.parseDouble(result);
		Bitmap bitmap = layout.getDrawingCache();
	}

	public void onPrevious(View view) {
	
		TempData.moveToPrevious();
		getAndSet();
		setButton();
	}
	
	public void onAll(View view) {
	
		this.finish();
	}
	
	public void onNext(View view) {
	
		TempData.moveToNext();
		getAndSet();
		setButton();
	}
	
	private void getAndSet() {
	
		number = TempData.getCurrent("number");
		state = TempData.getCurrent("state");
		point = TempData.getCurrent("score");
		content = TempData.getCurrent("content");
		answerofSta = TempData.getCurrent("answerofSta");
		answerofStu = TempData.getCurrent("answerofStu");

		number_tv2.setText(number);
		score_tv4.setText(state);
		state_tv6.setText(point);
		content_wv1.loadUrl(content);
		answer_wv2.loadUrl(answerofSta);
		// answer_iv.setImageBitmap(BitMapUtils.loadFromSdCard(answerofStu));
		setPaintView();
	}
	
	private void setPaintView() {
	
	}

	public void setButton() {
	
		if (TempData.isFirst())
			previous_b1.setEnabled(false);
		else
			previous_b1.setEnabled(true);
		if (TempData.isLast())
			next_b3.setEnabled(false);
		else
			next_b3.setEnabled(true);
	}
	
	private TextView number_tv2;
	private TextView score_tv4;
	private TextView state_tv6;
	private Button previous_b1;
	private Button next_b3;
	private WebView content_wv1;
	private WebView answer_wv2;
	private EditText score_et1;
	private ImageView answer_iv;
	private LinearLayout paint_ll;
	private LinearLayout layout;
	
	private PaintView mPaintView = null;
	
	private String number;
	private String state;
	private String point;
	private String content;
	private String answerofSta;
	private String answerofStu;
}
