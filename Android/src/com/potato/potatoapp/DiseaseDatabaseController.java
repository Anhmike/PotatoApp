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
	public static final String TABLE_PROBLEM = "problems";
	public static final String TABLE_SYMPTOM = "symptoms";
	public static final String TABLE_PICTURE = "picture";
	public static final String TABLE_LINK = "link_symptoms";
	
	//Variables to handle field names within disease table
	public static final String PROBLEM_ID = "P_ID";
	public static final String PROBLEM_NAME = "Name";
	public static final String PROBLEM_TYPE = "Type";
	public static final String PROBLEM_DESCRIPTION = "Description";
	public static final String PROBLEM_UPDATE_TIME = "Change_Date";
	
	//Variables to handle field names within symptom table
	public static final String SYMPTOM_ID = "S_ID";
	public static final String SYMPTOM_DESCRIPTION = "Description";
	public static final String SYMPTOM_PARENT = "Parent_Symptom";
	public static final String SYMPTOM_CHANGE_DATE = "Change_Date";
	public static final String SYMPTOM_TYPE = "Type";
	
	//Variables to handle the field names within the picture table
	public static final String PICTURE_ID = "Picture_ID";
	public static final String PICTURE_SYM_ID = "S_ID";
	public static final String PICTURE_URL = "URL";
	public static final String PICTURE_CHANGE_DATE = "Change_Date";
	
	//Variables to handle the field names within the disease/symptom link table
	public static final String LINK_ID = "LS_ID";
	public static final String LINK_DIS_ID = "P_ID";
	public static final String LINK_SYM_ID = "S_ID";
	
	public DiseaseDatabaseController(Context context) {
		super(context, DATABASE_NAME, null, VERSION);
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
		String CREATE_PROBLEM_TABLE = "CREATE_TABLE "+TABLE_PROBLEM+"("+PROBLEM_ID+" INTEGER PRIMARY KEY, "+PROBLEM_NAME+" TEXT, "+PROBLEM_TYPE+" TEXT, "+PROBLEM_DESCRIPTION+" TEXT, "+PROBLEM_UPDATE_TIME+" TEXT"+")";
		db.execSQL(CREATE_PROBLEM_TABLE);
		
		String CREATE_SYMPTOM_TABLE = "CREATE_TABLE "+TABLE_SYMPTOM+"("+SYMPTOM_ID+" INTEGER PRIMARY KEY, "+SYMPTOM_DESCRIPTION+" TEXT, "+SYMPTOM_PARENT+" INTEGER, "+ SYMPTOM_TYPE+" TEXT,"+SYMPTOM_CHANGE_DATE+" TEXT"+")";
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
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROBLEM);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_SYMPTOM);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PICTURE);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_LINK);
		
		onCreate(db);
	}
	
	/*
	 * Method to add a new disease to the list
	 * @author Stephanie Lee
	 */
	public void addProblem(Problem problem)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(PROBLEM_NAME, problem.getName());
		values.put(PROBLEM_TYPE, problem.getType());
		values.put(PROBLEM_DESCRIPTION, problem.getDescription());
		
		db.insert(TABLE_PROBLEM, null, values);
		db.close();
	}
	
	public void addSymptom(Symptom sym)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(SYMPTOM_DESCRIPTION, sym.getDescription());
		values.put(SYMPTOM_PARENT, sym.getParent());
		values.put(SYMPTOM_TYPE, sym.getPart());
		
		db.insert(TABLE_PROBLEM, null, values);
		db.close();
	}
	
	public Problem getDisease(int id)
	{
		SQLiteDatabase db = this.getReadableDatabase();
		
		Cursor cursor = db.query(TABLE_PROBLEM, new String[] { PROBLEM_ID, PROBLEM_NAME, PROBLEM_TYPE, PROBLEM_DESCRIPTION, PROBLEM_UPDATE_TIME }, null, null, null, null, null);
		
		if(cursor != null){
			cursor.moveToFirst();
		}
		
		Problem Problem = new Problem();
		
		return Problem;
	}
	
	public List<Symptom> getSymptom(String part)
	{
		List<Symptom> symList = new ArrayList<Symptom>();
		String query = "SELECT * FROM "+TABLE_SYMPTOM + " WHERE " + SYMPTOM_TYPE + "='"+part+"'";
		
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		
		if(cursor.moveToFirst()){
			do{
				Symptom symptom = new Symptom();
				symptom.setId(Integer.parseInt(cursor.getString(0)));
				symptom.setDescription(cursor.getString(1));
				symptom.setPart(cursor.getString(2));
				//symptom.setSymParent(Integer.parseInt(cursor.getString(3)));
				//disease.setUpdateTime(cursor.getString(4));
				
				symList.add(symptom);
			}while(cursor.moveToNext());
		}
		return symList;
	}
	
	public List<Problem> getAllProblems()
	{
		List<Problem> ProblemList = new ArrayList<Problem>();
		String query = "SELECT * FROM "+TABLE_PROBLEM;
		
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		
		if(cursor.moveToFirst()){
			do{
				Problem problem = new Problem();
				problem.setId(Integer.parseInt(cursor.getString(0)));
				problem.setName(cursor.getString(1));
				problem.setType(cursor.getString(2));
				problem.setDescription(cursor.getString(3));
				//disease.setUpdateTime(cursor.getString(4));
				
				ProblemList.add(problem);
			}while(cursor.moveToNext());
		}
		return ProblemList;
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
				symptom.setId(Integer.parseInt(cursor.getString(0)));
				symptom.setDescription(cursor.getString(1));
				symptom.setParent(Integer.parseInt(cursor.getString(2)));
				//disease.setUpdateTime(cursor.getString(4));
				
				symptomList.add(symptom);
			}while(cursor.moveToNext());
		}
		return symptomList;
	}
	
	public int getDiseaseCount()
	{
		String query = "SELECT * FROM "+TABLE_PROBLEM;
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
	
	public int updateProblem(Problem Problem)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(PROBLEM_NAME, Problem.getName());
		values.put(PROBLEM_TYPE, Problem.getType());
		values.put(PROBLEM_DESCRIPTION, Problem.getDescription());
		
		return db.update(TABLE_PROBLEM, values, PROBLEM_ID + " =?", new String[] {String.valueOf(Problem.getId())});
	}
	
	public int updateSymptom(Symptom symptom)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(SYMPTOM_DESCRIPTION, symptom.getDescription());
		values.put(SYMPTOM_PARENT, symptom.getParent());
		
		return db.update(TABLE_SYMPTOM, values, SYMPTOM_ID + " =?", new String[] {String.valueOf(symptom.getId())});
	}
	
	public void deleteDisease(Problem disease)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_PROBLEM, PROBLEM_ID + " =?", new String[] {String.valueOf(disease.getId())});
		db.close();
	}
}
