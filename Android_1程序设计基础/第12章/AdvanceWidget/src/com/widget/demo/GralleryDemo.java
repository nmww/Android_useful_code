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
        //��ʼ��Gallery��
        gallery=(Gallery)findViewById(R.id.gallery);
        try {
        	//����Gallery��Adapter��
			gallery.setAdapter(new ImageAdapter(GralleryDemo.this));
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//����¼���
        gallery.setOnItemClickListener(new OnItemClickListener(){


		public void onItemClick(AdapterView<?> arg0, View arg1, int itemid,
					long arg3) {
				// TODO Auto-generated method stub
				setTitle("������˵�"+String.valueOf(itemid+1)+"��");
						}
        	
        });
        
    }
    class ImageAdapter extends BaseAdapter {
    	//����Context����Activity
    	private Context context;
    	//������������ ��ͼƬԴ��
        private Integer image[]={R.drawable.android,R.drawable.monkey,
        		                R.drawable.panda,R.drawable.pig,
        		                R.drawable.niu,R.drawable.tiger};
    	
    	public ImageAdapter(Context c) throws IllegalArgumentException, IllegalAccessException{
    		context=c;
    	}


    
    	public int getCount() {
    		// TODO Auto-generated method stub
    		//��ȡͼƬ������
    		return Integer.MAX_VALUE;
    		//return image.length;
    	}


    
    	public Object getItem(int position) {
    		// TODO Auto-generated method stub
    		//��ȡͼƬ�ڿ��е�λ�á�
    		return position;
    	}


    	
    	public long getItemId(int position) {
    		// TODO Auto-generated method stub
    		//��ȡͼƬ�ڿ��е�λ�á�
    		return position;
    	}


    	
    	public View getView(int position, View convertView, ViewGroup parent) {
    		// TODO Auto-generated method stub
    		
    		ImageView imageview=new ImageView(context);
    		//��imageView����ͼƬ��Դ��
    		imageview.setImageResource(image[position%image.length]);
    		//imageview.setImageResource(image[position]);
    		//���ñ������͡�
    		imageview.setScaleType(ImageView.ScaleType.FIT_XY);
    		//����ͼƬ���ֺ���ʾ��С��
    		imageview.setLayoutParams(new Gallery.LayoutParams(100,100));
    		//����ͼƬ֮��ľ��롣
    		imageview.setPadding(15,0,15,0);
    		return imageview;
    	}


    }
}
