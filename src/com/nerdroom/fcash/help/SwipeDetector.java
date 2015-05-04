package com.nerdroom.fcash.help;

import com.nerdroom.funy.WorkActivity;

import android.app.Activity;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;

public class SwipeDetector extends SimpleOnGestureListener {
	
	private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    private static final int RIGHT = 1;
    private static final int LEFT = 2;
    private static final int NONE = 0;
    public int ACTION=NONE;
    
    public SwipeDetector(Activity Activity, SwipeDetector swipeDetector) {
		// TODO Auto-generated constructor stub
	}

	@Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        try {
            if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
                return false;
            // right to left swipe
            if(e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                ACTION=LEFT;
            }  else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                ACTION=RIGHT;
            }
        } catch (Exception e) {
            // nothing
        }
        return false;
    }
}