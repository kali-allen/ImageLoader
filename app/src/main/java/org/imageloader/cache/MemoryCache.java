package org.imageloader.cache;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by yan.qi on 2017/4/7.
 */

public class MemoryCache implements IImageCache {
    private LruCache<String, Bitmap> mBitmapLruCache;

    public MemoryCache() {
        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        int cacheSize = maxMemory / 4;
        mBitmapLruCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getRowBytes() * bitmap.getHeight() / 1024;
            }
        };
    }


    @Override
    public Bitmap get(String url) {
        return mBitmapLruCache.get(url);
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        mBitmapLruCache.put(url, bitmap);
    }
}
