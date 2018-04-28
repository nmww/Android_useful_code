package com.demo.touch2;

import android.app.Activity;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.FloatMath;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class MultiTouchDemo extends Activity implements OnTouchListener {
	
	Matrix matrix = new Matrix();
	Matrix eventMatrix = new Matrix();
	
	final static int NONE = 0;
	final static int DRAG = 1;
	final static int ZOOM = 2;
	int touchState = NONE;
	@Override
	public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.picture);
	ImageView view = (ImageView) findViewById(R.id.imageView);
	view.setOnTouchListener(this);
	}
	final static float MIN_DIST = 50;
	static float eventDistance = 0;
	static float centerX =0, centerY = 0;
	@Override
	public boolean onTouch(View v, MotionEvent event) {
	ImageView view = (ImageView) v;
	switch (event.getAction() & MotionEvent.ACTION_MASK) {
	case MotionEvent.ACTION_DOWN:
	
	touchState = DRAG;
	centerX = event.getX(0);
	centerY = event.getY(0);
	eventMatrix.set(matrix);
	break;
	case MotionEvent.ACTION_POINTER_DOWN:
	
	eventDistance = getDistance(event);
	getMidpoint(centerX, centerY, event);
	if (eventDistance > MIN_DIST) {
	eventMatrix.set(matrix);
	touchState = ZOOM;
	}
	break;
	
	case MotionEvent.ACTION_MOVE:
	if (touchState == DRAG) {
	
	matrix.set(eventMatrix);
	matrix.setTranslate(event.getX(0) - centerX,
	event.getY(0) - centerY);
	} else if (touchState == ZOOM) {
	
	float dist = getDistance(event);
	if (dist > MIN_DIST) {
	matrix.set(eventMatrix);
	float scale = dist / eventDistance;
	matrix.postScale(scale, scale, centerX, centerY);
	}
	}
	
	view.setImageMatrix(matrix);
	break;
	case MotionEvent.ACTION_UP:
	case MotionEvent.ACTION_POINTER_UP:
	touchState = NONE;
	break;
	}
	return true;
	}
	private float getDistance(MotionEvent event) {
	float x = event.getX(0) - event.getX(1);
	float y = event.getY(0) - event.getY(1);
	return FloatMath.sqrt(x * x + y * y);
	}
	private void getMidpoint(float centerX, float centerY,
	MotionEvent event) {
	centerX = (event.getX(0) + event.getX(1))/2;
	centerY = (event.getY(0) + event.getY(1))/2;
	}
	}
