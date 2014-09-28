package com.potato.potatoapp.database;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

public class GetDatabaseUpdatesRunnable implements Runnable{

	String returnString;
	String URL;

	public String getReturnString() {
		return returnString;
	}

	public void setURL(String URL) {
		this.URL = URL;
	}

	@Override
	public void run() {

		
	}

}
