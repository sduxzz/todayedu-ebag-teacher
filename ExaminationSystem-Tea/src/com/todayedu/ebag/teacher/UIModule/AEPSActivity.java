/**
 * SubActivity_Demo android.exercise
 * 2012 2012-3-29 锟斤拷锟斤拷7:55:07
 * @author xzz
 */
package com.todayedu.ebag.teacher.UIModule;

import org.ebag.net.obj.answer.AnswerObj;
import org.ebag.net.response.AnswerResponse;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.todayedu.ebag.teacher.AsyncImageLoader;
import com.todayedu.ebag.teacher.AsyncImageLoader.ImageLoadListener;
import com.todayedu.ebag.teacher.Parameters;
import com.todayedu.ebag.teacher.Parameters.ParaIndex;
import com.todayedu.ebag.teacher.R;
import com.todayedu.ebag.teacher.Network.NetworkCallBack;
import com.todayedu.ebag.teacher.Network.NetworkClient;
import com.todayedu.ebag.teacher.Network.StudentAnswerHandler;
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
	
	private AsyncImageLoader imageLoader = new AsyncImageLoader(
	        new ImageLoadListener() {
		        
		        @Override
		        public void onImageBitmapLoaded(Bitmap bitmap) {
		        
			        Message msg = Message.obtain(processor, LOADED, bitmap);
			        processor.sendMessage(msg);
		        }
		        @Override
		        public void onLoadFailed() {
		        
			        processor.sendEmptyMessage(FAILED);
		        }
	        });
	
	/**
	 * 最后归结于这个processor来处理
	 */
	private Handler processor = new Handler() {
		
		@Override
		public void handleMessage(Message msg) {
			switch(msg.what){
				case SUCCESS:
					AnswerResponse response = (AnswerResponse) msg.obj;
					if (response == null || response.examList.size() == 0) {
						Log.i(TAG, "size is 0");
						return;
					}
					AnswerObj answer = response.getExamList().get(0);
					if (answer.picOfTeacherUrl == null) {
						Log.i(TAG, "picofTeacher is null");
						return;
					}
					imageLoader.loadImageBitmap(UrlBuilder
					        .problemAnswerPicUrl(answer.picOfTeacherUrl));
					if (answer.textAnswer != null)
						setText(answer.textAnswer);
					else
						setText("没有文字回答哦。");
					break;
				case FAILED:
					setDefaultImage(R.drawable.ic_launcher);
					break;
				case LOADED:
					setImage((Bitmap) msg.obj);
					break;
				default:
					Log.i(TAG, "the msg'what value isn't right");
			}
		}
	};

	private WebView problem_wv1;
	private WebView answerofSta_wv2;// 标准答案
	private ImageView answerofTea_iv3;// 老师批改后的答案
	private TextView textofStu;// 学生的文字回答
	private TextView score_tv2;// 得分

	private static final int SUCCESS = 0;// 成功访问了answerrequest
	private static final int FAILED = 1;// 失败
	private static final int LOADED = 2;// 下载好图片
	
	private void findView() {
	
		problem_wv1 = (WebView) findViewById(R.id.aeps_wv1);
		answerofSta_wv2 = (WebView) findViewById(R.id.aeps_wv2);
		answerofTea_iv3 = (ImageView) findViewById(R.id.aeps_iv3);
		textofStu = (TextView) findViewById(R.id.aeps_tv3);
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
	 * 请求老师批改后的图片，和学生文字回答
	 */
	private void queryAnswerofTea() {
	
		final NetworkClient client = new NetworkClient();
		StudentAnswerHandler handler = new StudentAnswerHandler(this,
		        new NetworkCallBack() {
			        
			        @Override
			        public void success(Object message) {
			        
				        client.disconnect();
				        Message msg = Message.obtain(processor, SUCCESS,
				                message);
				        processor.sendMessage(msg);// 处理成功下载answerobj的情况
			        }
			        
			        @Override
			        public void failed(Throwable throwable) {
			        
				        client.disconnect();
				        throwable.printStackTrace();
				        processor.sendEmptyMessage(FAILED);
			        }
		        });
		client.setHandler(handler);
		client.connect();
	}
	
	private void setDefaultImage(int res) {
		answerofTea_iv3.setImageResource(res);
	}
	
	/**
	 * 设置老师批改后的图片
	 * 
	 * @param bitmap
	 */
	private void setImage(final Bitmap bitmap) {
	
		if (bitmap != null)
			answerofTea_iv3.setImageBitmap(bitmap);
		else
			setDefaultImage(R.drawable.ic_launcher);
	}
	
	/**
	 * 设置学生回答的文字信息
	 * 
	 * @param mes
	 */
	private void setText(String mes) {
	
		textofStu.setText(mes);
	}
}