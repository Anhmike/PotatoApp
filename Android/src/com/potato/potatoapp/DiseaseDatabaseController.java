package com.potato.potatoapp;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Class to handle database creation and other functions.
 * Will create the database tables when the app is launched(if the tables aren't built yet)
 * Also handles the addition of new information which will be retrieved by the xml parser.
 */
public class DiseaseDatabaseController extends SQLiteOpenHelper{
	
	//Variables to handle whole database information
	public static final int VERSION = 1;
	public static final String DATABASE_NAME = "potatoDiseases";
	
	//Variables to handle names of specific tables
	public static final String TABLE_DISEASE = "disease";
	public static final String TABLE_SYMPTOM = "symptom";
	public static final String TABLE_PICTURE = "picture";
	public static final String TABLE_LINK = "link_symptoms";
	
	//Variables to handle field names within disease table
	public static final String DISEASE_ID = "id";
	public static final String DISEASE_NAME = "diseaseName";
	public static final String DISEASE_PART = "problemType";
	public static final String DISEASE_DESCRIPTION = "diseaseDescription";
	public static final String DISEASE_UPDATE_TIME = "updateTime";
	
	//Variables to handle field names within symptom table
	public static final String SYMPTOM_ID = "symID";
	public static final String SYMPTOM_NAME = "symName";
	public static final String SYMPTOM_PARENT = "symParent";
	public static final String SYMPTOM_CHANGE_DATE = "symChangeDate";
	
	//Variables to handle the field names within the picture table
	public static final String PICTURE_ID = "picID";
	public static final String PICTURE_SYM_ID = "picSymID";
	public static final String PICTURE_URL = "picUrl";
	public static final String PICTURE_CHANGE_DATE = "picChangeDate";
	
	//Variables to handle the field names within the disease/symptom link table
	public static final String LINK_ID = "linkID";
	public static final String LINK_DIS_ID = "linkDisID";
	public static final String LINK_SYM_ID = "linkSymID";
	
	public DiseaseDatabaseController(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * Method to handle creation of the database on launch
	 * @author Stephanie Lee
	 * @see android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite.SQLiteDatabase)
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String CREATE_DISEASE_TABLE = "CREATE_TABLE "+TABLE_DISEASE+"("+DISEASE_ID+" INTEGER PRIMARY KEY, "+DISEASE_NAME+" TEXT, "+DISEASE_PART+" TEXT, "+DISEASE_DESCRIPTION+" TEXT, "+DISEASE_UPDATE_TIME+" TEXT"+")";
		db.execSQL(CREATE_DISEASE_TABLE);
		
		String CREATE_SYMPTOM_TABLE = "CREATE_TABLE "+TABLE_SYMPTOM+"("+SYMPTOM_ID+" INTEGER PRIMARY KEY, "+SYMPTOM_NAME+" TEXT, "+SYMPTOM_PARENT+" INTEGER, "+SYMPTOM_CHANGE_DATE+" TEXT"+")";
		db.execSQL(CREATE_SYMPTOM_TABLE);
		
		String CREATE_PICTURE_TABLE = "CREATE_TABLE "+TABLE_PICTURE+"("+PICTURE_ID+" INTEGER PRIMARY KEY, "+PICTURE_SYM_ID+" INTEGER, "+PICTURE_URL+" TEXT, "+PICTURE_CHANGE_DATE+" TEXT"+")";
		db.execSQL(CREATE_PICTURE_TABLE);
		
		String CREATE_LINK_TABLE = "CREATE_TABLE "+TABLE_LINK+"("+LINK_ID+" INTEGER PRIMARY KEY, "+LINK_DIS_ID+" INTEGER, "+LINK_SYM_ID+" INTEGER"+")";
		db.execSQL(CREATE_LINK_TABLE);
	}
	
	/*
	 * Method to handle update the table when any structural changes are made
	 * @author Stephanie Lee
	 * @see android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite.SQLiteDatabase, int, int)
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_DISEASE);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_SYMPTOM);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PICTURE);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_LINK);
		
		onCreate(db);
	}
	
	/*
	 * Method to add a new disease to the list
	 * @author Stephanie Lee
	 */
	public void addDisease(Disease disease)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(DISEASE_NAME, disease.getDiseaseName());
		values.put(DISEASE_PART, disease.getDiseasePart());
		values.put(DISEASE_DESCRIPTION, disease.getDiseaseDescription());
		
		db.insert(TABLE_DISEASE, null, values);
		db.close();
	}
	
	public void addSymptom(Symptom sym)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(SYMPTOM_NAME, sym.getSymName());
		values.put(SYMPTOM_PARENT, sym.getSymParent());
		
		db.insert(TABLE_DISEASE, null, values);
		db.close();
	}
	
	public Disease getDisease(int id)
	{
		SQLiteDatabase db = this.getReadableDatabase();
		
		Cursor cursor = db.query(TABLE_DISEASE, new String[] { DISEASE_ID, DISEASE_NAME, DISEASE_PART, DISEASE_DESCRIPTION, DISEASE_UPDATE_TIME }, null, null, null, null, null);
		
		if(cursor != null){
			cursor.moveToFirst();
		}
		
		Disease disease = new Disease();
		
		return disease;
	}
	
	public Symptom getSymptom(int id)
	{
		SQLiteDatabase db = this.getReadableDatabase();
		
		Cursor cursor = db.query(TABLE_SYMPTOM, new String[] { SYMPTOM_ID, SYMPTOM_NAME, SYMPTOM_PARENT }, null, null, null, null, null);
		
		if(cursor != null){
			cursor.moveToFirst();
		}
		
		Symptom symptom = new Symptom();
		
		return symptom;
	}
	
	public List<Disease> getAllDiseases()
	{
		List<Disease> diseaseList = new ArrayList<Disease>();
		String query = "SELECT * FROM "+TABLE_DISEASE;
		
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		
		if(cursor.moveToFirst()){
			do{
				Disease disease = new Disease();
				disease.setDiseaseId(Integer.parseInt(cursor.getString(0)));
				disease.setDiseaseName(cursor.getString(1));
				disease.setDiseasePart(cursor.getString(2));
				disease.setDiseaseDescription(cursor.getString(3));
				//disease.setUpdateTime(cursor.getString(4));
				
				diseaseList.add(disease);
			}while(cursor.moveToNext());
		}
		return diseaseList;
	}
	
	public List<Symptom> getAllSymptoms()
	{
		List<Symptom> symptomList = new ArrayList<Symptom>();
		String query = "SELECT * FROM "+TABLE_SYMPTOM;
		
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		
		if(cursor.moveToFirst()){
			do{
				Symptom symptom = new Symptom();
				symptom.setSymID(Integer.parseInt(cursor.getString(0)));
				symptom.setSymName(cursor.getString(1));
				symptom.setSymParent(Integer.parseInt(cursor.getString(2)));
				//disease.setUpdateTime(cursor.getString(4));
				
				symptomList.add(symptom);
			}while(cursor.moveToNext());
		}
		return symptomList;
	}
	
	public int getDiseaseCount()
	{
		String query = "SELECT * FROM "+TABLE_DISEASE;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		cursor.close();
		
		return cursor.getCount();
	}
	
	public int getSymptomCount()
	{
		String query = "SELECT * FROM "+TABLE_SYMPTOM;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		cursor.close();
		
		return cursor.getCount();
	}
	
	public int updateDisease(Disease disease)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(DISEASE_NAME, disease.getDiseaseName());
		values.put(DISEASE_PART, disease.getDiseasePart());
		values.put(DISEASE_DESCRIPTION, disease.getDiseaseDescription());
		
		return db.update(TABLE_DISEASE, values, DISEASE_ID + " =?", new String[] {String.valueOf(disease.getDiseaseId())});
	}
	
	public int updateSymptom(Symptom symptom)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(SYMPTOM_NAME, symptom.getSymName());
		values.put(SYMPTOM_PARENT, symptom.getSymParent());
		
		return db.update(TABLE_SYMPTOM, values, SYMPTOM_ID + " =?", new String[] {String.valueOf(symptom.getSymID())});
	}
	
	public void deleteDisease(Disease disease)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_DISEASE, DISEASE_ID + " =?", new String[] {String.valueOf(disease.getDiseaseId())});
		db.close();
	}
}
