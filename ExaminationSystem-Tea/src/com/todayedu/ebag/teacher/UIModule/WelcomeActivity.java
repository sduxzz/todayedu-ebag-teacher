package com.todayedu.ebag.teacher.UIModule;

import java.util.ArrayList;

import org.ebag.net.response.LoginResponse;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.todayedu.ebag.teacher.Parameters;
import com.todayedu.ebag.teacher.Parameters.ParaIndex;
import com.todayedu.ebag.teacher.R;
import com.todayedu.ebag.teacher.DataSource.DataObj.EClass;
import com.todayedu.ebag.teacher.Network.LoginHandler;
import com.todayedu.ebag.teacher.Network.NetWorkUtil;
import com.todayedu.ebag.teacher.Network.NetworkCallBack;
import com.todayedu.ebag.teacher.Network.NetworkClient;
import com.todayedu.ebag.teacher.Network.ResponseParseUtil;

import ebag.pojo.Euser;

/**
 * the welcome activity,teacher should login
 * 
 * @author zhenzxie
 * 
 */
public class WelcomeActivity extends BaseActivity {
	
	private EditText et1;
	private EditText et2;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		System.setProperty("java.net.preferIPv6Addresses", "false");

		et1 = (EditText) findViewById(R.id.main_et1);
		et2 = (EditText) findViewById(R.id.main_et2);

	}
	
	public void onConfirm(View view) {
	
		Log.i(TAG, "onConfirm");
		if (NetWorkUtil.isConnected(this)) {
			
			String name = et1.getText().toString();
			String password = et2.getText().toString();

			final NetworkClient client = new NetworkClient();
			client.setHandler(new LoginHandler(this, name, password,
			        new NetworkCallBack() {
				        
				        @Override
				        public void success(Object response) {
				        
					        LoginResponse loginResponse = (LoginResponse) response;
					        Euser user = loginResponse.user;
					        Parameters.add(user.getId(), ParaIndex.UID_INDEX);//add uid to globle parameter

					        ArrayList<EClass> list = ResponseParseUtil
					                .parseLoginResponse(loginResponse);
					        start(list);
				        }
				        
				        @Override
				        public void failed(Throwable cause) {
				        
					        if (cause != null) {
						        Log.i("WelcomeActivity", cause.getMessage());
					        }
					        showToast("µÇÂ¼Ê§°Ü£¬ÇëÖØÐÂµÇÈë", Toast.LENGTH_SHORT);
				        }
			        }));
			client.connect();
		} else {
			Toast.makeText(WelcomeActivity.this, "Î´Á¬½ÓÍøÂç", Toast.LENGTH_SHORT)
			        .show();
		}

	}
	
	private void start(final ArrayList<EClass> list) {
	
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
				Bundle bundle = new Bundle();
				bundle.putSerializable("classIdList", list);
				intent.putExtras(bundle);
				startActivity(intent);
			}
		}.start();
	}
}