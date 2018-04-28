package com.demo.touch2;

import android.content.Context;
import android.util.AttributeSet;

public class MyTrueButton extends MyButton {
    protected boolean myValue() {
        return true;
    }
    
    public MyTrueButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
}
