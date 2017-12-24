package com.example.sambovisal.swipe2;

import android.annotation.TargetApi;
import android.gesture.GestureOverlayView;
import android.os.Build;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener
{
    TextView tv;
    GestureDetectorCompat compat = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tw);
        compat = new GestureDetectorCompat(this,this);

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        compat.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        tv.setText("on single tap confirmed");
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent motionEvent) {
        tv.setText("on double tap");
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        tv.setText("on double tap event");
        return false;
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        tv.setText("on down");
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {
        tv.setText("on show press");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        tv.setText("on single tap up");
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        tv.setText("on scroll");
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {
        tv.setText("long press");
    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        tv.setText("on fling");
        return false;
    }
}
