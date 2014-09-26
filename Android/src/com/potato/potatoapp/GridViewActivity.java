package com.potato.potatoapp;

import com.potato.potatoapp.ImageAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;

public class GridViewActivity extends Activity {

	GridView gridView;

	static final String[] MOBILE_OS = new String[] { "Android", "iOS",
			"Windows", "Blackberry" };

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.symptom_menu);

		gridView = (GridView) findViewById(R.id.gridView1);

		gridView.setAdapter(new ImageAdapter(this, MOBILE_OS)); //add label text to gridview

		gridView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				Toast.makeText(
						getApplicationContext(),
						((TextView) v.findViewById(R.id.title_page_gridview))
								.getText(), Toast.LENGTH_SHORT).show();
			}
		});
	}
}