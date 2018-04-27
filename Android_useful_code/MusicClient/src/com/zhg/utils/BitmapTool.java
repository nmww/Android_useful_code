package com.zhg.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;

public class BitmapTool {
	public static Bitmap getScaleBitmap(byte[] bytes){
		//实现图像的缩略
		Bitmap bitmap = null;
		BitmapFactory.Options ops = new Options();
		ops.inJustDecodeBounds = true;
		bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, ops);
		int scaleX = ops.outWidth / 64;
		int scaleY = ops.outHeight / 64;
		int scale = scaleX>scaleY?scaleX:scaleY;
		ops.inSampleSize = scale;
		ops.inJustDecodeBounds = false;
		bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, ops);
		return bitmap ;
	}
}
