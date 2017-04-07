package org.imageloader.cache;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.imageloader.MyApplication;
import org.imageloader.utils.MD5;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by yan.qi on 2017/4/7.
 */

public class DiskCache implements IImageCache {
    private final String CACHE_DIR = MyApplication.getContext().getCacheDir().getAbsolutePath() + "/";

    @Override
    public Bitmap get(String url) {
        try {
            return BitmapFactory.decodeFile(CACHE_DIR + MD5.getMD5(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        FileOutputStream outputStream = null;
        try {
            File file = new File(CACHE_DIR + MD5.getMD5(url));
            if (!file.exists()) {
                file.createNewFile();
            }
            outputStream = new FileOutputStream(file);
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
