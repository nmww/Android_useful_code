package com.widget.demo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class GralleryDemo extends Activity {
    /** Called when the activity is first created. */
	
	private Gallery gallery;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grallery);
        //初始化Gallery。
        gallery=(Gallery)findViewById(R.id.gallery);
        try {
        	//设置Gallery的Adapter。
			gallery.setAdapter(new ImageAdapter(GralleryDemo.this));
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//点击事件。
        gallery.setOnItemClickListener(new OnItemClickListener(){


		public void onItemClick(AdapterView<?> arg0, View arg1, int itemid,
					long arg3) {
				// TODO Auto-generated method stub
				setTitle("您点击了第"+String.valueOf(itemid+1)+"项");
						}
        	
        });
        
    }
    class ImageAdapter extends BaseAdapter {
    	//定义Context，即Activity
    	private Context context;
    	//定义整型数组 即图片源。
        private Integer image[]={R.drawable.android,R.drawable.monkey,
        		                R.drawable.panda,R.drawable.pig,
        		                R.drawable.niu,R.drawable.tiger};
    	
    	public ImageAdapter(Context c) throws IllegalArgumentException, IllegalAccessException{
    		context=c;
    	}


    
    	public int getCount() {
    		// TODO Auto-generated method stub
    		//获取图片个数。
    		return Integer.MAX_VALUE;
    		//return image.length;
    	}


    
    	public Object getItem(int position) {
    		// TODO Auto-generated method stub
    		//获取图片在库中的位置。
    		return position;
    	}


    	
    	public long getItemId(int position) {
    		// TODO Auto-generated method stub
    		//获取图片在库中的位置。
    		return position;
    	}


    	
    	public View getView(int position, View convertView, ViewGroup parent) {
    		// TODO Auto-generated method stub
    		
    		ImageView imageview=new ImageView(context);
    		//给imageView设置图片资源。
    		imageview.setImageResource(image[position%image.length]);
    		//imageview.setImageResource(image[position]);
    		//设置比例类型。
    		imageview.setScaleType(ImageView.ScaleType.FIT_XY);
    		//设置图片布局和显示大小。
    		imageview.setLayoutParams(new Gallery.LayoutParams(100,100));
    		//设置图片之间的距离。
    		imageview.setPadding(15,0,15,0);
    		return imageview;
    	}


    }
}
