package com.todayedu.ebag.teacher;

import java.lang.ref.SoftReference;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;

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
