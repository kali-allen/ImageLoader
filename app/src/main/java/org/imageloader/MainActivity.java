package org.imageloader;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.imageloader.cache.MemoryCache;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = (TextView) findViewById(R.id.sample_text);
        final ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.setImageCache(new MemoryCache());
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView imageView = (ImageView) findViewById(R.id.image);
                imageLoader.displayImage(imageView, "https://imgs.qunarzz.com/p/tts9/1611/9b/aa52e42e4e32a702.jpg_r_390x260x90_ecfc33e8.jpg");
            }
        });

    }
}
