package com.potato.potatoapp.activities;

import com.potato.potatoapp.R;
import com.potato.potatoapp.R.id;
import com.potato.potatoapp.R.layout;
import com.potato.potatoapp.R.menu;

import android.support.v7.app.ActionBarActivity;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class VideoMenuActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_video_menu);
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
//		final Button fda = (Button) findViewById(R.id.button2);
//		fda.setOnClickListener(new View.OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				Intent intent = new Intent(VideoMenuActivity.this, VideoActivity.class);
//            	intent.putExtra("url", "http://vimeo.com/107448873");
//            	VideoMenuActivity.this.startActivity(intent);
//			}
//		});
//
//		final Button lfd = (Button) findViewById(R.id.button1);
//		lfd.setOnClickListener(new View.OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				Intent intent = new Intent(VideoMenuActivity.this, VideoActivity.class);
//            	intent.putExtra("url", "https://www.youtube.com/watch?v=2Wokc5_aqic&list=PLtZHIFR5osfCZAdtFVLleVh0H7kB5OGhC&index=49");
//            	VideoMenuActivity.this.startActivity(intent);
//			}
//		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.video_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		finish();
		return true;
	}
}
