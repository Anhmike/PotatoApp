package com.potato.potatoapp.database;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.potato.potatoapp.beans.MutableString;
import com.potato.potatoapp.beans.XMLReturn;

import android.util.Log;

public class GetUpdates {

	public GetUpdates() {

	}

	public static XMLReturn getUpdates(final String URL) {

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
		}
		
		try {
			return XMLParser.parseXML(xmlString.getStringVal());
		} catch (Exception e) {
			Log.e("XMLParser", e.toString());
			e.printStackTrace();
		}

		return null;

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
}
