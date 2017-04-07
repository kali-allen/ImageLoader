package org.imageloader.cache;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by yan.qi on 2017/4/7.
 */

public class DiskCache implements IImageCache {
    private final String CACHE_DIR = "/sdcard/data";

    @Override
    public Bitmap get(String url) {
        try {
            return BitmapFactory.decodeFile(CACHE_DIR + url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(CACHE_DIR + url);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
