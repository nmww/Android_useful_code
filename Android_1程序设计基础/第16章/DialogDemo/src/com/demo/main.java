package com.demo;



import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class main extends Activity {
	private static final String DIALOG_DemoApp = "Dialog Demo app";
	private static final int DIALOG_SIMPLE = 1;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button SimpleButtons = (Button) findViewById(R.id.SimpleDialog);
        SimpleButtons.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                showDialog(DIALOG_SIMPLE);
                Log.i(DIALOG_DemoApp,"Dialog has been showned!....");
                int temp=0;
                for(int i=0;i<10;i++)temp+=i;
                Log.i(DIALOG_DemoApp,"temp="+temp);
            }
        });
    }
    @Override
    protected Dialog onCreateDialog(int id) {
    	Log.i(DIALOG_DemoApp, "onCreateDialog has been called");
    	 switch (id) {
         case DIALOG_SIMPLE:
            return new AlertDialog.Builder(main.this)
                 .setTitle(R.string.DialogTitle)
                 .setMessage(R.string.Dialog_Message)
                 .setPositiveButton(R.string.Dialog_OK, new DialogInterface.OnClickListener() {
                     public void onClick(DialogInterface dialog, int whichButton) {

                         /* User clicked OK so do some stuff */
                     }
                 })
                 .setNegativeButton(R.string.Dialog_Cancle, new DialogInterface.OnClickListener() {
                     public void onClick(DialogInterface dialog, int whichButton) {

                         /* User clicked Cancel so do some stuff */
                     }
                 })
                 .create();
             
    	 }
    	 return null;
    }
    @Override
    public void onPrepareDialog(int id, Dialog dialog){
    	Log.i(DIALOG_DemoApp, "onPrepareDialog has been called");
    	switch(id) {
        case (DIALOG_SIMPLE) :
           SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
           sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));

           Date currentTime = new Date(java.lang.System.currentTimeMillis());
           String dateString = sdf.format(currentTime);
           AlertDialog m_Dialog = (AlertDialog)dialog;
           m_Dialog.setMessage(dateString);
           break;
        }
    }
    
}