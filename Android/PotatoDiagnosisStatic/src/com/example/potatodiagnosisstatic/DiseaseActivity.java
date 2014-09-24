package com.example.potatodiagnosisstatic;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class DiseaseActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_disease);
		Intent intent = getIntent();
		int position = intent.getIntExtra("position", 0);
		String[] data_name = intent.getStringArrayExtra("array_names");
		String[] data_description = intent.getStringArrayExtra("array_descriptions");
		TextView name = (TextView)findViewById(R.id.editText1);
		TextView description = (TextView)findViewById(R.id.editText2);
		name.setText(data_name[position]);
		description.setText(data_description[position]);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.disease, menu);
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
