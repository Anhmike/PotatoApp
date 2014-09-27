package com.potato.potatoapp.database;

import com.potato.potatoapp.beans.XMLReturn;

import android.util.Log;

public class GetUpdates {

	public GetUpdates() {
		
	}
	
	public static XMLReturn getUpdates(String URL) {
		
		String xmlString = sendGetRequest(URL);
		try {
			return XMLParser.parseXML(xmlString);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	private static String sendGetRequest(String URL) {
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
