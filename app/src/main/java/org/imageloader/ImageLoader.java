package org.imageloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.widget.ImageView;

import org.imageloader.cache.DiskCache;
import org.imageloader.cache.IImageCache;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by yan.qi on 2017/4/6.
 */

public class ImageLoader {
    private final static ImageLoader instance = new ImageLoader();
    private ExecutorService mExecutor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public void setImageCache(IImageCache imageCache) {
        this.mIImageCache = imageCache;
    }

    private IImageCache mIImageCache = new DiskCache();

    private ImageLoader() {
    }

    public static ImageLoader getInstance() {
        return instance;
    }

    public void displayImage(final ImageView imageView, final String url) {
        if (imageView == null || TextUtils.isEmpty(url)) {
            return;
        }
        //first, get image from cache
        Bitmap bitmap = mIImageCache.get(url);
        imageView.setTag(url);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
            return;
        }
        //second,get image from internet
        mExecutor.submit(new Runnable() {
            @Override
            public void run() {
                final Bitmap bitmap = downLoadImage(url);
                if (bitmap == null) {
                    return;
                }
                mIImageCache.put(url, bitmap);
                if (url.equals(imageView.getTag())) {
                    imageView.post(new Runnable() {
                        @Override
                        public void run() {
                            imageView.setImageBitmap(bitmap);
                        }
                    });
                }
            }
        });
    }

    private Bitmap downLoadImage(String url) {
        try {
            URL url1 = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
            Bitmap bitmap = BitmapFactory.decodeStream(connection.getInputStream());
            connection.disconnect();
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
