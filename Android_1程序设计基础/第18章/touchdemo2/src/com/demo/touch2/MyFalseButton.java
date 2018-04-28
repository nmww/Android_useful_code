package com.demo.touch2;

import android.content.Context;
import android.util.AttributeSet;

public class MyFalseButton extends MyButton {
    protected boolean myValue() {
        return false;
    }
    
    public MyFalseButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
}
