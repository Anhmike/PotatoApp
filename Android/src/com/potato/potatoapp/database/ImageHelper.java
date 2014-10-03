package com.potato.potatoapp.database;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.potato.potatoapp.beans.Picture;

public class ImageHelper {
	
	protected ImageHelper() {
		
	}
	
	public static void saveImage(Context context, Picture picture) {
		try {
			String urlString = "http://10.0.3.2:8080/PotatoServer/UpdatePhone/image/" + picture.getUrl();
		    URL url = new URL(urlString);
		    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		    conn.setDoInput(true);
		    conn.connect();
		    InputStream is = conn.getInputStream();
		    Bitmap bm = BitmapFactory.decodeStream(is);
		    File file = new File(picture.getUrl());
		    if(file.exists())
		    	file.delete();
		    
		    FileOutputStream fos = context.openFileOutput(picture.getUrl(), Context.MODE_PRIVATE);
		             
		    ByteArrayOutputStream outstream = new ByteArrayOutputStream();
		             
		    bm.compress(Bitmap.CompressFormat.PNG, 100, outstream);
		    byte[] byteArray = outstream.toByteArray();
		             
		    fos.write(byteArray);
		    fos.close();
		} catch(Exception e) {
			Log.e("image input", e.toString());
		}
		             
		
	}
	
	public static Bitmap loadImage(Context context, String filename) {
		Bitmap bitmap = null; 
		FileInputStream fis;
	    try { 
	    	fis = context.openFileInput(filename);
	        bitmap = BitmapFactory.decodeStream(fis); 
	        fis.close(); 

	    }  
	    catch (FileNotFoundException e) { 
	        Log.d("image loader", "file not found"); 
	        e.printStackTrace(); 
	    }  
	    catch (IOException e) { 
	        Log.d("image loader", "io exception"); 
	        e.printStackTrace(); 
	    }  
	    return bitmap; 
	}

}
