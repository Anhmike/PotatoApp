package com.potato.potatoapp.activities;

import com.potato.potatoapp.R;
import com.potato.potatoapp.R.id;
import com.potato.potatoapp.R.layout;
import com.potato.potatoapp.R.menu;
import com.potato.potatoapp.beans.Problem;
import com.potato.potatoapp.database.DiseaseDatabaseController;

import android.support.v7.app.ActionBarActivity;
import android.text.method.ScrollingMovementMethod;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class DiseaseActivity extends ActionBarActivity {
	DiseaseDatabaseController db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_disease);
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		Intent intent = getIntent();
		int position = intent.getIntExtra("problem", 0);
		db = new DiseaseDatabaseController(this);
		Problem problem = new Problem();
		problem = db.getProblem(position);
		TextView name = (TextView)findViewById(R.id.problem_title_label);
		TextView description = (TextView)findViewById(R.id.description_text_label);
		description.setMovementMethod(new ScrollingMovementMethod());
		description.setScrollbarFadingEnabled(false);
		name.setText(problem.getName());
		description.setText(problem.getDescription());
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
		finish();
		return true;
	}
}
