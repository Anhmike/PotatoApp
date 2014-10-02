package com.potato.potatoapp.activities;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.potato.potatoapp.R;
import com.potato.potatoapp.beans.XMLReturn;
import com.potato.potatoapp.database.DiseaseDatabaseController;
import com.potato.potatoapp.database.GetUpdates;

public class MainActivity extends ActionBarActivity {

	DiseaseDatabaseController db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//creates a db to be used by the whole application
		db = new DiseaseDatabaseController(this);
		//Remove after testing
		db.rebuildDatabase();
		
		//start xmlParser test
		//genymotion ip
		//GetUpdates.getUpdates("http://10.0.3.2:8080/PotatoServer/UpdatePhone?t=00000000000000", db);
		//IP for Tomcat Server
		GetUpdates.getUpdates("http://134.36.36.188:8080/PotatoServer/UpdatePhone?t=00000000000000", db);
		
		setContentView(R.layout.activity_main);
		final Button pestButton = (Button) findViewById(R.id.button1);
        pestButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
            	Intent intent = new Intent(MainActivity.this, SymptomActivity.class);
            	intent.putExtra("name", "pest");
            	MainActivity.this.startActivity(intent);
            	//Intent intent = getIntent();
            	//String disease = intent.getStringExtra("text");
            }
        });
        
        final Button leafButton = (Button) findViewById(R.id.Button01);
        leafButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
            	Intent intent = new Intent(MainActivity.this, SymptomActivity.class);
            	intent.putExtra("name", "leaf");
            	MainActivity.this.startActivity(intent);
            	//Intent intent = getIntent();
            	//String disease = intent.getStringExtra("text");
            }
        });
        
        final Button tuborButton = (Button) findViewById(R.id.Button02);
        tuborButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
            	Intent intent = new Intent(MainActivity.this, SymptomActivity.class);
            	intent.putExtra("name", "tubor");
            	MainActivity.this.startActivity(intent);
            	//Intent intent = getIntent();
            	//String disease = intent.getStringExtra("text");
            }
        });
        
        final Button testButton = (Button) findViewById(R.id.Button03);
        testButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, VideoMenuActivity.class);
				MainActivity.this.startActivity(intent);
				
			}
		});
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		} else if (id == R.id.update_database) {
			Context context = getApplicationContext();
			CharSequence text = "Updating application";
			int duration = Toast.LENGTH_LONG;

			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
			
			String versionString = db.getVersionNumber();
			String version;
			if(versionString != null){
			DateTime date =  new DateTime(Long.parseLong(db.getVersionNumber()));
			DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyyMMddkkmmss");
			version = fmt.print(date);
			} else {
				version = "00000000000000";
			}
			if(GetUpdates.getUpdates("http://134.36.36.188:8080/PotatoServer/UpdatePhone?t=" + version, db)) {
				db.setNewVersion();
				text = "Application Updated";
				toast = Toast.makeText(context, text, duration);
				toast.show();
				
			} else {
				text = "No updates found";
				toast = Toast.makeText(context, text, duration);
				toast.show();
			}
		}
		return super.onOptionsItemSelected(item);
	}
}
