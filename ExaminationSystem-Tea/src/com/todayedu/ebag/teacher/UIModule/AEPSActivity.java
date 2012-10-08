/**
 * SubActivity_Demo android.exercise
 * 2012 2012-3-29 锟斤拷锟斤拷7:55:07
 * @author xzz
 */
package com.todayedu.ebag.teacher.UIModule;

import java.util.ArrayList;
import java.util.List;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.ebag.net.request.AnswerRequest;
import org.ebag.net.response.AnswerResponse;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.todayedu.ebag.teacher.AsyncImageLoader;
import com.todayedu.ebag.teacher.AsyncImageLoader.ImageLoadListener;
import com.todayedu.ebag.teacher.Parameters;
import com.todayedu.ebag.teacher.Parameters.ParaIndex;
import com.todayedu.ebag.teacher.R;
import com.todayedu.ebag.teacher.Network.NetworkClient;
import com.todayedu.ebag.teacher.Network.UrlBuilder;

/**
 * 某道题的学生详细情况
 * 
 * @author zhenzxie
 * 
 */
public class AEPSActivity extends BaseActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.aeps);
		findView();
		loadContent();
	}
	
	@Override
	public void onBackPressed() {
	
		super.onBackPressed();
		this.finish();
	}

	private WebView problem_wv1;
	private WebView answerofSta_wv2;// 标准答案
	private ImageView answerofTea_iv3;// 学生的答案
	private TextView score_tv2;// 得分
	
	private AsyncImageLoader imageLoader = new AsyncImageLoader(
	        new ImageLoadListener() {
		        
		        @Override
		        public void onImageBitmapLoaded(Bitmap bitmap) {
		        
			        if (bitmap != null)
				        answerofTea_iv3.setImageBitmap(bitmap);
			        else
				        setDefault();
		        }
		        
		        @Override
		        public void onLoadFailed() {
		        
			        setDefault();
		        }
		        
	        });

	private void findView() {
	
		problem_wv1 = (WebView) findViewById(R.id.aeps_wv1);
		answerofSta_wv2 = (WebView) findViewById(R.id.aeps_wv2);
		answerofTea_iv3 = (ImageView) findViewById(R.id.aeps_iv3);
		score_tv2 = (TextView) findViewById(R.id.aeps_tv2);
	}
	
	private void loadContent() {
	
		int pid = Parameters.get(ParaIndex.PID_INDEX);
		problem_wv1.loadUrl(UrlBuilder.problemContentUrl(pid));
		answerofSta_wv2.loadUrl(UrlBuilder.problemAnswerUrl(pid));
		queryAnswerofTea();
		score_tv2.setText(getIntent().getDoubleExtra("Score", 0) + "");
	}
	
	/**
	 * 请求老师批改后的图片
	 */
	private void queryAnswerofTea() {
	
		final NetworkClient client = new NetworkClient();
		IoHandlerAdapter adapter = new IoHandlerAdapter() {
			
			@Override
			public void exceptionCaught(IoSession session, Throwable cause)
			        throws Exception {
			
				client.disconnect();
				Log.i(TAG, cause.getMessage());
				setDefault();
			}
			
			@Override
			public void messageReceived(IoSession session, Object message)
			        throws Exception {
			
				client.disconnect();
				if (message instanceof AnswerResponse) {
					AnswerResponse response = (AnswerResponse) message;
					if (response == null || response.examList.size() == 0) {
						setDefault();
						return;
					}
					String picOfTeacherUrl = response.getExamList().get(0).picOfTeacherUrl;
					if (picOfTeacherUrl == null) {
						setDefault();
						return;
					}
					imageLoader.loadImageBitmap(UrlBuilder
					        .problemAnswerPicUrl(picOfTeacherUrl));
				} else {
					Log.i(TAG, "the return isn't right type");
					setDefault();
				}
			}
			
			@Override
			public void sessionOpened(IoSession session) throws Exception {
			
				AnswerRequest request = new AnswerRequest();
				request.setUid(Parameters.get(ParaIndex.SID_INDEX));
				request.examId = Parameters.get(ParaIndex.EID_INDEX);
				List<Integer> idList = new ArrayList<Integer>();
				idList.add(Parameters.get(ParaIndex.PID_INDEX));
				request.setIdList(idList);
				List<String> fieldList = new ArrayList<String>();
				fieldList.add("picOfTeacherUrl");
				request.setFieldList(fieldList);
				session.write(request);
				Log.i(TAG, "sessionOpened " + request.toString());
			}
		};
		client.setHandler(adapter);
		client.connect();
	}
	
	private void setDefault() {
		// TODO:set a default image to answerofStu_iv3
		Log.i(TAG, "setDefalut set a default image");
		answerofTea_iv3.setImageResource(R.drawable.ic_launcher);
	}
}