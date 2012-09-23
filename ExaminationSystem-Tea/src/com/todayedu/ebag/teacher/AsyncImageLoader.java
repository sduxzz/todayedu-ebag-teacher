package com.todayedu.ebag.teacher;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.http.AndroidHttpClient;
import android.os.Handler;
import android.util.Log;

public class AsyncImageLoader {
	
	private Map<String, SoftReference<Bitmap>> imageCache = new HashMap<String, SoftReference<Bitmap>>();
	private ExecutorService executorService = Executors.newFixedThreadPool(2);
	private final Handler handler = new Handler();
	
	public void loadImageBitmap(final String imageUrl,
	        final ImageLoadListener listener) {
	
		if (imageCache.containsKey(imageUrl)) {
			SoftReference<Bitmap> softReference = imageCache.get(imageUrl);
			if (softReference.get() != null) {
				listener.onImageBitmapLoaded(softReference.get());
			}
		}
		
		executorService.submit(new Runnable() {
			
			public void run() {
			
				try {
					final Bitmap bitmap = BitmapFactory.decodeStream(new URL(
					        imageUrl).openStream());
					
					imageCache.put(imageUrl, new SoftReference<Bitmap>(bitmap));
					
					handler.post(new Runnable() {
						
						public void run() {
						
							listener.onImageBitmapLoaded(bitmap);
						}
					});
				} catch (Exception e) {
					e.printStackTrace();
					listener.onLoadFailed();
				}
			}
		});
	}
	
	protected Bitmap getBitmap(final String imageUrl) {
	
		final String TAG = "AsynImageLoader";
		final AndroidHttpClient client = AndroidHttpClient
		        .newInstance("Android");
		final HttpGet getRequest = new HttpGet(imageUrl);
		try {
			HttpResponse response = client.execute(getRequest);
			final int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != HttpStatus.SC_OK) {
				Log.w(TAG, "从" + imageUrl + "中下载图片时出错!,错误码:"
				        + statusCode);
				return null;
			}
			final HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream inputStream = null;
				try {
					inputStream = entity.getContent();
					
					final Bitmap bitmap = BitmapFactory
					        .decodeStream(inputStream);
					return bitmap;
				} finally {
					if (inputStream != null) {
						inputStream.close();
					}
					entity.consumeContent();
				}
			}
		} catch (IOException e) {
			getRequest.abort();
			Log.w(TAG, "I/O errorwhile retrieving bitmap from " + imageUrl, e);
		} catch (IllegalStateException e) {
			getRequest.abort();
			Log.w(TAG, "Incorrect URL:" + imageUrl);
		} catch (Exception e) {
			getRequest.abort();
			Log.w(TAG, "Error whileretrieving bitmap from " + imageUrl, e);
		} finally {
			if (client != null) {
				client.close();
			}
		}
		return null;
	}

	public Map<String, SoftReference<Bitmap>> getImageCache() {
	
		return imageCache;
	}
	
	public void postAsyncJob(Runnable r) {
	
		executorService.submit(r);
	}
	
	public interface ImageLoadListener {
		
		public void onImageBitmapLoaded(Bitmap bitmap);
		
		public void onLoadFailed();
	}
	
}
