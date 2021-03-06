package com.example.potatodiagnosisstatic;

import android.support.v7.app.ActionBarActivity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class SymptomActivity extends ListActivity {

	String symptom_names[];
	String disease_names[];
	String disease_descriptions[];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_symptom);
		Intent button = getIntent();
		String part = button.getStringExtra("name");
		if(part.equals("pest")){
			setPests();
		}else if(part.equals("leaf")){
			setLeaves();
		}else{
			setTubors();
		}
		// view =
		// context.LayoutInflater.Inflate(Android.Resource.Layout.ActivityListItem,
		// null);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				R.layout.listviewlayout, symptom_names);
		ListView list = (ListView) findViewById(android.R.id.list);
		list.setAdapter(adapter);
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(SymptomActivity.this,
						DiseaseActivity.class);
				int pos = position;
				intent.putExtra("position", pos);
				intent.putExtra("array_names", disease_names);
				intent.putExtra("array_descriptions", disease_descriptions);
				startActivity(intent);
			}

		});

	}
	
	public void setPests(){
		symptom_names = new String[]{"Aphids", "Tubor Moth"};
		disease_names = new String[]{"Aphids", "Tubor Moth"};
		disease_descriptions = new String[]{"Apply insecticide treatments on emergence to control PLRV.  To avoid virus infection it is essential to grow seed crops in areas with low aphid prevalence", "Monitor populations during growing season with pheromone traps and apply insecticide treatment if needed; ensure proper ridging of plants to prevent exposure of tubers during growing season; protect tubers after harvest by storing under cover and ensure seed stores are clean and free of debris and old potatoes"};
	}
	
	public void setLeaves(){
		symptom_names = new String[]{"Yellowing", "Curling", "Black, Slimy Stem", "Brown Stem Discolouration", "Necrotic Lesions on Leaf", "Light Green Spots on Leaves"};
		disease_names = new String[]{"PVY", "Potato Leafroll Virus", "Blackleg", "Dickeya Solani", "Early Blight", "Late Blight"};
		disease_descriptions = new String[]{"If seed crop remove and destroy plant. If ware crop, mark the plants with a cane and harvest tubers for consumption. Tubers from infected plants should not be used for propagation. ","Spread can be controlled by application of systemic insecticide.  If seed crop, remove and destroy plant.  If ware crop, mark the plants with a cane and harvest tubers for consumption. Tubers from infected plants should not be used for propagation.","Dig up affected plants and place foliage and tubers in bags for destruction away from field.  Do not plant diseased tubers.  Tubers are a source of infection and should be washed in disinfectant before storage.  Clean and disinfect tools and equipment to avoid spreading infection. ","Dig up affected plants and place foliage and tubers in bags for destruction away from field.  Do not plant diseased tubers.  Do not plant diseased tubers. Tubers are a source of infection and should be washed in disinfectant before storage.  Clean and disinfect tools and equipment to avoid spreading infection. ","Application of fungicide to foliage; removal and destruction of infected crop debris after harvest.","If wet weather spray on emergence with protectant fungicide.  As soon as early symptoms observed spray immediately with recommended systemic fungicide (may depend on LB PCR genotype test). Dead foliage and tubers are reservoirs of infection and should be removed and destroyed. Tubers can be treated with fungicide pre-planting"};
	}
	
	public void setTubors(){
		symptom_names = new String[]{"Soft Rot", "Scab Lesions", "Black Patches", "Surface Pits on Tubor", "Blackened Tubors"};
		disease_names = new String[]{"Blackleg", "Common Scab", "Black Scurf", "Black Scurf", "Late Blight"};
		disease_descriptions = new String[]{"Dig up affected plants and place foliage and tubers in bags for destruction away from field.  Do not plant diseased tubers.  Tubers are a source of infection and should be washed in disinfectant before storage.  Clean and disinfect tools and equipment to avoid spreading infection. ","Use irrigation during tuber formation to control development and do not grow successive potato crops on same land.","Treat tubers with fungicide such as morceren before planting. Use crop rotation with 4-6 seasons between potato crops","Treat tubers with fungicide such as morceren before planting. Use crop rotation with 4-6 seasons between potato crops","If wet weather spray on emergence with protectant fungicide.  As soon as early symptoms observed spray immediately with recommended systemic fungicide (may depend on LB PCR genotype test). Dead foliage and tubers are reservoirs of infection and should be removed and destroyed. Tubers can be treated with fungicide pre-planting"};
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.symptom, menu);
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
