package com.potato.potatoapp.database;

import java.util.ArrayList;

import com.potato.potatoapp.beans.Picture;

import android.content.Context;


public class GetImagesRunnable implements Runnable{

	Context context;
	DiseaseDatabaseController db;


	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public DiseaseDatabaseController getDb() {
		return db;
	}

	public void setDb(DiseaseDatabaseController db) {
		this.db = db;
	}

	@Override
	public void run() {
		downloadNewImages(db, context);
	}
	
	private boolean downloadNewImages(DiseaseDatabaseController db, Context context) {
		String lastUpdate = db.getVersionNumber();
		if(lastUpdate == null)
			lastUpdate = "00000000000000";
		ArrayList<Picture> pictures = db.getNewPictures(lastUpdate);
		
		for(Picture picture: pictures) {
			ImageHelper.saveImage(context, picture);
		}
		return true;
	}

}
