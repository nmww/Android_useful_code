package com.demo.intent;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

public class PlayMusic extends Activity {   
    
    @Override  
    public void onCreate(Bundle savedInstanceState) {   
        super.onCreate(savedInstanceState);   
        setContentView(R.layout.play);   
        this.setTitle("PlayMusic");   
           
        Intent i = this.getIntent();   
           
        Uri u = i.getData();   
           
        try {   
            playMusic(u);   
        } catch (IllegalArgumentException e) {   
            // TODO Auto-generated catch block   
            e.printStackTrace();   
        } catch (SecurityException e) {   
            // TODO Auto-generated catch block   
            e.printStackTrace();   
        } catch (IllegalStateException e) {   
            // TODO Auto-generated catch block   
            e.printStackTrace();   
        } catch (IOException e) {   
            // TODO Auto-generated catch block   
            e.printStackTrace();   
        }   
    }   
       
    public void playMusic(Uri uri) throws IllegalArgumentException, SecurityException, IllegalStateException, IOException{   
        MediaPlayer mp = new MediaPlayer();   
        mp.setDataSource(this, uri);   
        mp.prepare();   
        mp.start();   
    }   
}  

