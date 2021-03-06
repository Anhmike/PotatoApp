package com.potato.potatoapp.database;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.potato.potatoapp.beans.MutableString;
import com.potato.potatoapp.beans.Picture;
import com.potato.potatoapp.beans.Problem;
import com.potato.potatoapp.beans.Symptom;
import com.potato.potatoapp.beans.XMLReturn;

public class GetUpdates {

	public GetUpdates() {

	}

	public static boolean getUpdates(final String URL, DiseaseDatabaseController db, Context context) {

		final MutableString xmlString = new MutableString();
		Thread getDetails = new Thread(new Runnable() {
			public void run() {
				xmlString.setStringVal(sendGetRequest(URL));
			}
		});
		
		getDetails.start();
		try {
			getDetails.join();
		} catch (InterruptedException e1) {
			Log.e("Thread error", e1.toString());
			e1.printStackTrace();
			return false;
		}
		
		XMLReturn xml = null;
		try {
			xml = XMLParser.parseXML(xmlString.getStringVal());
		} catch (Exception e) {
			Log.e("XMLParser", e.toString());
			e.printStackTrace();
			return false;
		}
		
		if(xml != null ) {
			placeDataInDatabase(db, xml);
			
			GetImagesRunnable imagesRunnable = new GetImagesRunnable();
			imagesRunnable.setContext(context);
			imagesRunnable.setDb(db);
			Thread getImages = new Thread(imagesRunnable);
			
			getImages.start();
			try {
				getImages.join();
			} catch (InterruptedException e1) {
				Log.e("Thread error", e1.toString());
				e1.printStackTrace();
				return false;
			}
			
			
			
			
			return true;
		}
		
		
		return false;
		
		
		

	}

	private static String sendGetRequest(String URL) {
		String returnString = null;
		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpResponse response = httpclient.execute(new HttpGet(URL));
			StatusLine statusLine = response.getStatusLine();
			if(statusLine.getStatusCode() == HttpStatus.SC_OK){
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				response.getEntity().writeTo(out);
				
				out.close();
				returnString = out.toString();
			} else{
				//Closes the connection.
				response.getEntity().getContent().close();
				throw new IOException(statusLine.getReasonPhrase());
			}
		} catch (Exception e) {
			Log.e("HTTP Request", e.toString());
		}

		return returnString;
	}
	
	private static void placeDataInDatabase(DiseaseDatabaseController db, XMLReturn xml) {
		ArrayList<Problem> problems = xml.getProblems();
		ArrayList<Symptom> symptoms = xml.getSymptoms();
		ArrayList<Picture> images = xml.getImages();
		
		for(Problem problem: problems) {
			db.addProblem(problem);
		}
		for(Symptom symptom: symptoms) {
			db.addSymptom(symptom);
		}
		for(Picture image: images) {
			if(image.getType().equals("problem"))
				db.updateProblemPicture(image);
			else
				db.updateSymptomPictures(image);
		}
	}
}
