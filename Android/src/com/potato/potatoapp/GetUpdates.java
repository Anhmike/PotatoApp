package com.potato.potatoapp;

import android.util.Log;

public class GetUpdates {
	static String URL = "";

	public GetUpdates() {
		
	}
	
	public static XMLReturn getUpdates() {
		
		String xmlString = sendGetRequest();
		try {
			return XMLParser.parseXML(xmlString);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	private static String sendGetRequest() {
		GetDatabaseUpdatesRunnable runnable = new GetDatabaseUpdatesRunnable();
		runnable.setURL(URL);
			
		
		Thread thread = new Thread(runnable);
		
		thread.run();
		
		try{
			thread.join();
		} catch (Exception e) {
			Log.e("thread", "network thread failed");
		}
		
		return runnable.getReturnString();
	}
}
