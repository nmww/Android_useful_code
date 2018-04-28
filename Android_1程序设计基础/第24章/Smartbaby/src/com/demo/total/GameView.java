package com.demo.total;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class GameView extends View {

	private String TAG;

	// сно╥дёй╫
	private int mMode;
	private static final int STATE_LOADING = 0; // loading for first time
	private static final int STATE_RUNNING = 1;
	private static final int STATE_PAUSED = 2;

	private int mLevel;

	private boolean movingObject;

	private int mBoardWidth = 1;
	private int mBoardHeight = 1;

	private final int WIDTH_RATIO = 7;

	private Rect squareRect;
	private Rect circleRect;
	private Rect triangleRect;
	private Rect centreRect; //
	private Rect currentShapeRect;
	private Rect pauseButtonRect;

	private Paint squarePaint;
	private Paint circlePaint;
	private Paint trianglePaint;
	private Paint pausePaint;

	private Path trianglePath;

	private int mCurrentShape;
	private static final int SQUARE = 0;
	private static final int CIRCLE = 1;
	private static final int TRIANGLE = 2;
	private static final int PAUSE = 3;

	private Handler mHandler;

	private Vibrator mVibrator;
	private int VIBRATE_DURATION = 100;
	private boolean willVibrate;

	public GameView(Context context, String loggingString, Handler handler,
			Vibrator vibrator, boolean returning) {
		super(context);
		TAG = loggingString;
		mHandler = handler;
		mVibrator = vibrator;

		if (returning) {
			mMode = STATE_RUNNING;
		} else {
			mMode = STATE_LOADING; // we're still loading the game up (not yet
									// running)
		}

		squarePaint = new Paint();
		squarePaint.setStyle(Paint.Style.FILL);
		squarePaint.setColor(Color.RED);

		circlePaint = new Paint();
		circlePaint.setStyle(Paint.Style.FILL);
		circlePaint.setColor(Color.BLUE);

		trianglePaint = new Paint();
		trianglePaint.setStyle(Paint.Style.FILL);
		trianglePaint.setColor(Color.YELLOW);

		pausePaint = new Paint();
		pausePaint.setColor(Color.WHITE);
		pausePaint.setStrokeWidth((float) 5);

		trianglePath = new Path();

	}

	public void startGame() {
		mLevel = 0;
		nextLevel();
		mMode = STATE_RUNNING;
		showBeginningAnimation();
	}

	private void showBeginningAnimation() {
		// TODO add in some animation showing multiple shapes (can block UI)
		invalidate();
	}

	// Move on to the next level
	private void nextLevel() {
		mLevel++;
		mCurrentShape = Math.round((float) Math.random() * 2);
		movingObject = false;
		currentShapeRect = new Rect(centreRect);
		sendLevel(mLevel);
	}

	private void sendLevel(int level) {
		Message msg = mHandler.obtainMessage();
		Bundle b = new Bundle();
		b.putInt("level", level);
		msg.setData(b);
		mHandler.sendMessage(msg);
	}

	// User has let go of the moveable shape on something other than any other
	// shape
	// so just move it back to the beginning location
	private void wrongShape() {
		movingObject = false;
		currentShapeRect.set(centreRect);
		// TODO: add in some animation moving shape back to centre
	}

	protected void onDraw(Canvas canvas) {
		drawShape(canvas, SQUARE, squareRect);
		drawShape(canvas, CIRCLE, circleRect);
		drawShape(canvas, TRIANGLE, triangleRect);
		drawShape(canvas, PAUSE, pauseButtonRect);

		if (mMode == STATE_RUNNING) {
			Log.d(TAG, "current shape = " + mCurrentShape);
			drawShape(canvas, mCurrentShape, currentShapeRect);
		}
	}

	// Draws "shape" on the canvas, in position rect
	private void drawShape(Canvas canvas, int shape, Rect rect) {
		switch (shape) {
		case SQUARE:
			canvas.drawRect(rect.left, rect.top, rect.right, rect.bottom,
					squarePaint);
			break;
		case CIRCLE:
			canvas.drawCircle(rect.centerX(), rect.centerY(), rect.width() / 2,
					circlePaint);
			break;
		case TRIANGLE:
			trianglePath.reset();
			trianglePath.moveTo(rect.left, rect.bottom);
			trianglePath.lineTo(rect.right, rect.bottom);
			trianglePath.lineTo(rect.centerX(), rect.top);
			trianglePath.lineTo(rect.left, rect.bottom);
			trianglePath.close();
			canvas.drawPath(trianglePath, trianglePaint);
			break;
		case PAUSE:
			int heightCoefficient = (rect.bottom - rect.top) / 5;
			int widthCoefficient = (rect.right - rect.left) / 3;
			canvas.drawLine(rect.left + widthCoefficient, rect.top
					+ heightCoefficient, rect.left + widthCoefficient,
					rect.bottom - heightCoefficient, pausePaint);
			canvas.drawLine(rect.right - widthCoefficient, rect.top
					+ heightCoefficient, rect.right - widthCoefficient,
					rect.bottom - heightCoefficient, pausePaint);
			break;
		default:
			Log.d(TAG, "Shape not found (shape = " + mCurrentShape);
		}
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);

		mBoardWidth = w;
		mBoardHeight = h;

		//
		// Create all the shapes here so that onDraw is as slim as possible
		//
		int shapeWidth, shapeHeight;

		if (mBoardHeight >= mBoardWidth) {
			shapeWidth = mBoardWidth / WIDTH_RATIO;
			shapeHeight = mBoardWidth / WIDTH_RATIO;
		} else {
			shapeWidth = mBoardHeight / WIDTH_RATIO;
			shapeHeight = mBoardHeight / WIDTH_RATIO;
		}

		// Position of the main shapes, each represented as a rectangle
		squareRect = new Rect(0, 0, shapeWidth, shapeHeight);
		circleRect = new Rect(0, mBoardHeight - shapeHeight, shapeWidth,
				mBoardHeight);
		triangleRect = new Rect(mBoardWidth - shapeWidth, mBoardHeight
				- shapeHeight, mBoardWidth, mBoardHeight);

		// Position of the shape which is moveable
		centreRect = new Rect(mBoardWidth / 2 - shapeWidth / 2, mBoardHeight
				/ 2 - shapeHeight / 2, mBoardWidth / 2 + shapeWidth / 2,
				mBoardHeight / 2 + shapeHeight / 2);
		currentShapeRect = new Rect(centreRect);

		// Position of pause "button"
		pauseButtonRect = new Rect(mBoardWidth - shapeWidth, 0, mBoardWidth,
				shapeHeight);

		if (mMode == STATE_LOADING) {
			startGame(); // loading up for first time so start game
							// automatically
		} else {
			invalidate(); // re-draw screen with changed dimensions
		}
	}

	// User has pressed down on the screen
	private void touchDown(int x, int y) {
		if (currentShapeRect.contains(x, y)) {
			movingObject = true;
			touchMove(x, y);
		}
	}

	// User is dragging finger against screen
	private void touchMove(int x, int y) {
		if (movingObject) {
			currentShapeRect.offsetTo(x - currentShapeRect.width() / 2, y
					- currentShapeRect.height() / 2);
		}
	}

	// User has removed finger from screen
	private void touchUp(int x, int y) {
		// if landed on pause button or clicked pause button
		if (mMode == STATE_RUNNING) {
			if (pauseButtonRect.contains(x, y)) {
				pause();
			}
		} else if (mMode == STATE_PAUSED) {
			unPause();
		}

		// if we are moving the shape then check if we have landed on another
		// shape
		if (movingObject) {
			if (mCurrentShape == SQUARE) {
				if (Rect.intersects(currentShapeRect, squareRect)) {
					nextLevel();
					vibratePhone(VIBRATE_DURATION);
					return;
				}
			} else if (mCurrentShape == CIRCLE) {
				if (Rect.intersects(currentShapeRect, circleRect)) {
					nextLevel();
					vibratePhone(VIBRATE_DURATION);
					return;
				}
			} else if (mCurrentShape == TRIANGLE) {
				if (Rect.intersects(currentShapeRect, triangleRect)) {
					nextLevel();
					vibratePhone(VIBRATE_DURATION);
					return;
				}
			}
			wrongShape();
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int x = (int) event.getX();
		int y = (int) event.getY();

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			touchDown(x, y);
			invalidate();
			break;
		case MotionEvent.ACTION_MOVE:
			touchMove(x, y);
			invalidate();
			break;
		case MotionEvent.ACTION_UP:
			touchUp(x, y);
			invalidate();
			break;
		}
		return true;
	}

	public void setWillVibrate(boolean willVibrate) {
		this.willVibrate = willVibrate;
	}

	private void vibratePhone(int duration) {
		if (willVibrate) {
			mVibrator.vibrate(duration);
		}
	}

	public void pause() {
		if (mMode == STATE_RUNNING) {
			mMode = STATE_PAUSED;
			movingObject = false;
			currentShapeRect = new Rect(centreRect);

			Message msg = mHandler.obtainMessage();
			Bundle b = new Bundle();
			b.putString("mode", "pause");
			msg.setData(b);
			mHandler.sendMessage(msg);
		}
	}

	public void unPause() {
		mMode = STATE_RUNNING;
		invalidate();
	}

	@Override
	public void onWindowFocusChanged(boolean hasWindowFocus) {
		// if (!hasWindowFocus) pause();
	}

	public Bundle saveState(Bundle map) {
		if (map != null) {
			map.putInt("mLevel", Integer.valueOf(mLevel));
			map.putInt("mCurrentShape", Integer.valueOf(mCurrentShape));
		}
		return map;
	}

	public void restoreState(Bundle savedState) {
		mLevel = savedState.getInt("mLevel");
		mCurrentShape = savedState.getInt("mCurrentShape");
		movingObject = false;
		mMode = STATE_RUNNING;
		sendLevel(mLevel);
		invalidate();
	}

}
