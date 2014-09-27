package com.potato.potatoapp;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
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
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
		}
		return super.onOptionsItemSelected(item);
	}
}
