package com.ityang.learnmoudle;

import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class XFerModeActivity extends AppCompatActivity {

    private RoundImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xfer_mode);
        img = (RoundImageView)findViewById(R.id.img);
        //img.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.p1));
    }
}
