package org.imageloader.cache;

import android.graphics.Bitmap;

/**
 * Created by yan.qi on 2017/4/6.
 */

public interface IImageCache {
    Bitmap get(String url);

    void put(String url, Bitmap bitmap);
}
