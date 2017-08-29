package com.charles.myfirstslideshow;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.R.id;

import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.List;

import static android.graphics.Color.GREEN;
import static android.graphics.Color.GRAY;

/**
 *  MainActivity.java
 *  Copyright © 2017 Yoti. All rights reserved.
 *
 *  This code is for demonstration purposes only. It should not be assumed to represent best
 *  practices or patterns employed at Yoti.
 */
public class MainActivity extends AppCompatActivity implements Serializable {

    // Images under Creative Commons
    // Images attributed to (in order) https://www.flickr.com/photos/_torne/
    // https://www.flickr.com/photos/chrisyarzab/
    // https://www.reddit.com/user/lalien42/
    // http://www.kapstadt.de/

    public static List images = Arrays.asList(new String[]{"https://c1.staticflickr.com/6/5615/15570202337_0e64f5046e_k.jpg",
            "https://c1.staticflickr.com/4/3169/2846544061_cb7c04b46f_b.jpg",
            "https://i.redd.it/d8q1wkgu1awy.jpg",
            "http://www.kapstadt.de/webcam.jpg"});

    static int i = 0;
    static int j = 0;

    volatile WeakReference<ImageView> image1;
    volatile WeakReference<ImageView> image2;

    static View[] dots1;
    static View[] dots2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            dots1 = new View[4];
            dots1[0] = getWindow().findViewById(R.id.dots1dot1);
            dots1[1] = getWindow().findViewById(R.id.dots1dot2);
            dots1[2] = getWindow().findViewById(R.id.dots1dot3);
            dots1[3] = getWindow().findViewById(R.id.dots1dot4);
            dots2 = new View[4];
            dots2[0] = getWindow().findViewById(R.id.dots2dot1);
            dots2[1] = getWindow().findViewById(R.id.dots2dot2);
            dots2[2] = getWindow().findViewById(R.id.dots2dot3);
            dots2[3] = getWindow().findViewById(R.id.dots2dot4);
        }

        super.onCreate(savedInstanceState);

        image1 = new WeakReference<ImageView>((ImageView) getWindow().findViewById(R.id.image1));
        image2 = new WeakReference<ImageView>((ImageView) getWindow().findViewById(R.id.image2));
    }

    public synchronized void onButtonOneClicked(View button) {
        String currentImage = "" + images.get(i % 4);
        i += 1;
        // the last url of the array changes every ~ 15 minutes
        Bitmap b;
        try {
            URL url = new URL(currentImage);
            URLConnection c = url.openConnection();
            b = BitmapFactory.decodeStream(c.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error decoding picture");
        }

        image1.get().setImageBitmap(b);

        //Update dots
        if (i % 4 == 0) {
            dots1[0].setBackgroundColor(GREEN);
            dots1[1].setBackgroundColor(GRAY);
            dots1[2].setBackgroundColor(GRAY);
            dots1[3].setBackgroundColor(GRAY);
        }
        if (i % 4 == 1) {
            dots1[1].setBackgroundColor(GREEN);
            dots1[0].setBackgroundColor(GRAY);
            dots1[2].setBackgroundColor(GRAY);
            dots1[3].setBackgroundColor(GRAY);
        }
        if (i % 4 == 2) {
            dots1[2].setBackgroundColor(GREEN);
            dots1[1].setBackgroundColor(GRAY);
            dots1[0].setBackgroundColor(GRAY);
            dots1[3].setBackgroundColor(GRAY);
        }
        if (i % 4 == 3) {
            dots1[3].setBackgroundColor(GREEN);
            dots1[1].setBackgroundColor(GRAY);
            dots1[2].setBackgroundColor(GRAY);
            dots1[0].setBackgroundColor(GRAY);
        }
    }

    public synchronized void onButtonTwoClicked(View button) {
        String currentImage = "" + images.get(j % 4);
        j += 1;
        // the last url of the array changes every ~ 15 minutes
        Bitmap b;
        try {
            URL url = new URL(currentImage);
            URLConnection c = url.openConnection();
            b = BitmapFactory.decodeStream(c.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error decoding picture");
        }

        image2.get().setImageBitmap(b);

        //Update dots
        if (j % 4 == 0) {
            dots2[0].setBackgroundColor(GREEN);
            dots2[1].setBackgroundColor(GRAY);
            dots2[2].setBackgroundColor(GRAY);
            dots2[3].setBackgroundColor(GRAY);
        }
        if (j % 4 == 1) {
            dots2[1].setBackgroundColor(GREEN);
            dots2[0].setBackgroundColor(GRAY);
            dots2[2].setBackgroundColor(GRAY);
            dots2[3].setBackgroundColor(GRAY);
        }
        if (j % 4 == 2) {
            dots2[2].setBackgroundColor(GREEN);
            dots2[1].setBackgroundColor(GRAY);
            dots2[0].setBackgroundColor(GRAY);
            dots2[3].setBackgroundColor(GRAY);
        }
        if (j % 4 == 3) {
            dots2[3].setBackgroundColor(GREEN);
            dots2[1].setBackgroundColor(GRAY);
            dots2[2].setBackgroundColor(GRAY);
            dots2[0].setBackgroundColor(GRAY);
        }
    }

    public void onImageTapped(View image) {
    }
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }
}
