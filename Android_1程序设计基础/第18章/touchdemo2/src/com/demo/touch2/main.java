package com.demo.touch2;

import android.app.Activity;
import android.os.Bundle;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class main extends Activity implements OnTouchListener {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button trueBtn1 = (Button)findViewById(R.id.trueBtn1);
        trueBtn1.setOnTouchListener(this);
        Button falseBtn1 = (Button)findViewById(R.id.falseBtn1);
        falseBtn1.setOnTouchListener(this);

      }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        String myTag = v.getTag().toString();
        Log.v(myTag, "-----------------------------");
        Log.v(myTag, "Got view " + myTag + " in onTouch");
        Log.v(myTag, showEventInfo(v, event));
        if( "true".equals(myTag.substring(0, 4))) {
            Log.v(myTag, "·µ»Ø true");
            return true;
        }
        else {
            Log.v(myTag, "·µ»Ø false");
            return false;
        }
    }
    
    protected static String showEventInfo(View view, MotionEvent event) {
        StringBuilder result = new StringBuilder(300);
        result.append("Action: ").append(event.getAction()).append("\n");
        result.append("Location: ").append(event.getX()).append(" x ").append(event.getY()).append("\n");
        if(    event.getX() < 0 || event.getX() > view.getWidth() ||
                event.getY() < 0 || event.getY() > view.getHeight()) {
            result.append(">>> Touch has left the view <<<\n");
        }
        result.append("Edge flags: ").append(event.getEdgeFlags()).append("\n");
        result.append("Pressure: ").append(event.getPressure()).append("   ");
        result.append("Size: ").append(event.getSize()).append("\n");
        result.append("Down time: ").append(event.getDownTime()).append("ms\n");
        result.append("Event time: ").append(event.getEventTime()).append("ms");
        result.append("Elapsed: ").append(event.getEventTime()-event.getDownTime());
        result.append(" ms\n");
        return result.toString();
    }
}
