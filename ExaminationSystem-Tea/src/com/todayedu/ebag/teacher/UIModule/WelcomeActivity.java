package com.todayedu.ebag.teacher.UIModule;

import java.util.ArrayList;
import java.util.Set;

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
import com.todayedu.ebag.teacher.Network.LoginHandler;
import com.todayedu.ebag.teacher.Network.NetWorkUtil;
import com.todayedu.ebag.teacher.Network.NetworkCallBack;
import com.todayedu.ebag.teacher.Network.NetworkClient;

import ebag.pojo.Eclass;

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
	
	/**
	 * @see android.app.Activity#onBackPressed()
	 */
	@Override
	public void onBackPressed() {
	
		super.onBackPressed();
		this.finish();
		System.exit(0);
	}

	public void onConfirm(View view) {
	
		if (NetWorkUtil.isConnected(this)) {
			String name = et1.getText().toString();
			String password = et2.getText().toString();

			final NetworkClient client = new NetworkClient();
			LoginHandler handler = new LoginHandler(this, name, password,
			        new NetworkCallBack() {
				        
				        @Override
				        public void success(Object response) {
					        client.disconnect();
					        LoginResponse loginResponse = (LoginResponse) response;
					        // add uid to globle parameter
					        Parameters.add(loginResponse.user.getId(),
					                ParaIndex.UID_INDEX);
					        ArrayList<Eclass> list = parseLoginResponse(loginResponse);
					        start(list);
				        }
				        
				        @Override
				        public void failed(Throwable cause) {
				        
					        client.disconnect();
					        if (cause != null) {
						        Log.i("WelcomeActivity", cause.getMessage());
					        }
					        showToast("登录失败，请重新登入", Toast.LENGTH_SHORT);
				        }
			        });
			client.setHandler(handler);
			client.connect();
		} else {
			Toast.makeText(WelcomeActivity.this, "未连接网络", Toast.LENGTH_SHORT)
			        .show();
		}

	}
	
	private void start(final ArrayList<Eclass> list) {
	
		new Thread() {
			
			@Override
			public void run() {
			
				Intent intent = new Intent(WelcomeActivity.this,
				        FunctionActivity.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("classList", list);
				intent.putExtras(bundle);
				startActivity(intent);
			}
		}.start();
	}

	/**
     * 将LoginResponse中的set转化为list
     * 
     * @param response
     * @return
     */
	public ArrayList<Eclass> parseLoginResponse(LoginResponse response) {
    
    	Set<Eclass> classSet = response.getClassSet();
    	ArrayList<Eclass> list = new ArrayList<Eclass>();
    
    	for (Eclass cs : classSet) {
    		list.add(cs);
			Log.i(TAG, "parseLoginResponse: " + cs);
    	}
    	return list;
    }
}