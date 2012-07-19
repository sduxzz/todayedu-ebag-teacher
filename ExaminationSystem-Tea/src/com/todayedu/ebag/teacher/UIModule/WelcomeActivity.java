package com.todayedu.ebag.teacher.UIModule;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.todayedu.ebag.teacher.Parameters;
import com.todayedu.ebag.teacher.Parameters.ParaIndex;
import com.todayedu.ebag.teacher.R;

/**
 * the welcome activity
 * 
 * @author zhenzxie
 * 
 */
public class WelcomeActivity extends Activity implements OnItemSelectedListener {
	
	private Spinner spinner;
	private ArrayAdapter<String> adapter;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Log.i("WelcomeActivity", "main thread's id:"
				+ Thread.currentThread().getId());
		spinner = (Spinner) findViewById(R.id.main_sp);
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, new String[] { "1", "2",
						"3", "4", "5" });// TODO:set the name of all classes
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(this);
		spinner.setVisibility(View.VISIBLE);
	}
	
	private void start() {
	
		new Thread() {
			
			@Override
			public void run() {
			
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Intent intent = new Intent(WelcomeActivity.this,
				        FunctionActivity.class);
				startActivity(intent);
			}
		}.start();
	}
	
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
	
		int cid = 1;// TODO:get cid of selected class
		Parameters.add(String.valueOf(cid), ParaIndex.CID_INDEX);
		start();
	}
	
	@Override
	public void onNothingSelected(AdapterView<?> parent) {
	
	}
}