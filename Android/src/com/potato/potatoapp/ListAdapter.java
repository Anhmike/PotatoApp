package com.potato.potatoapp;

import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.potato.potatoapp.beans.Picture;
import com.potato.potatoapp.beans.Symptom;
import com.potato.potatoapp.database.DiseaseDatabaseController;
import com.potato.potatoapp.database.ImageHelper;

public class ListAdapter extends ArrayAdapter<String> {
	private final Activity context;
	private final List<Symptom> symptoms;
	private final int[] imageId;
	private DiseaseDatabaseController db;

	public ListAdapter(Activity context, String[] web, int[] symptom_images, List<Symptom> symptoms) {
		super(context, R.layout.listviewlayout, web);
		this.context = context;
		this.symptoms = symptoms;
		this.imageId = symptom_images;
		db = new DiseaseDatabaseController(context);
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		LayoutInflater inflater = context.getLayoutInflater();
		View rowView = inflater.inflate(R.layout.listviewlayout, null, true);
		TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
		txtTitle.setText(symptoms.get(position).getDescription());
		Picture picture = db.getSymptomPictures(symptoms.get(position).getId());
		Bitmap image = null;
		if(picture != null)
			image = ImageHelper.loadImage(context, picture.getUrl());
		if(image == null)
			imageView.setImageResource(R.drawable.apterousaphid);
		else
			imageView.setImageBitmap(image);
		return rowView;
	}
}