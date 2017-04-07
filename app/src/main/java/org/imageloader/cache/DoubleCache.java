package org.imageloader.cache;

import android.graphics.Bitmap;

/**
 * Created by yan.qi on 2017/4/7.
 */

public class DoubleCache implements IImageCache {
    private MemoryCache memoryCache = new MemoryCache();
    private DiskCache diskCache = new DiskCache();

    @Override
    public Bitmap get(String url) {
        Bitmap bitmap = memoryCache.get(url);
        return bitmap == null ? diskCache.get(url) : bitmap;
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        memoryCache.put(url, bitmap);
        diskCache.put(url, bitmap);
    }
}
