package org.imageloader;

import android.app.Application;
import android.content.Context;

/**
 * Created by yan.qi on 2017/4/7.
 */

public class MyApplication extends Application {

    public static Context mContext;

    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        if (mContext == null) {
            mContext = this;
        }
    }

    public static Context getContext() {
        return mContext;
    }
}
