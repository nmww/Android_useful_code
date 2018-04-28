package com.demo.touch2;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;

public abstract class MyButton extends Button {
    protected boolean myValue() {
        return false;
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        String myTag = this.getTag().toString();
        Log.v(myTag, "----------------------------onTouchEvent");
        Log.v(myTag, main.showEventInfo(this, event));
        Log.v(myTag, "super onTouchEvent() returns " + super.onTouchEvent(event));
       Log.v(myTag, "and I'm returning "+myValue());
         event.recycle();
        return(myValue());
        // return true;
    }
}
