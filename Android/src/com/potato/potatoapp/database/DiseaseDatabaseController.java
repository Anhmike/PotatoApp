package com.potato.potatoapp.database;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.potato.potatoapp.beans.Picture;
import com.potato.potatoapp.beans.Problem;
import com.potato.potatoapp.beans.Symptom;
import com.potato.potatoapp.beans.UserDecisionStore;

/**
 * Class to handle database creation and other functions. Will create the
 * database tables when the app is launched(if the tables aren't built yet) Also
 * handles the addition of new information which will be retrieved by the xml
 * parser.
 */
public class DiseaseDatabaseController extends SQLiteOpenHelper {

	// Variables to handle whole database information
	private static final int VERSION = 1;
	private static final String DATABASE_NAME = "potatoDiseases";

	// Variables to handle names of specific tables
	private static final String TABLE_PROBLEM = "problems";
	private static final String TABLE_SYMPTOM = "symptoms";
	private static final String TABLE_PROBLEM_PICTURE = "problem_picture";
	private static final String TABLE_SYMPTOM_PICTURE = "symptom_picture";
	private static final String TABLE_LINK = "link_symptoms";
	private static final String TABLE_VERSION = "database_version";

	// Variables to handle field names within disease table
	private static final String PROBLEM_ID = "P_ID";
	private static final String PROBLEM_NAME = "Name";
	private static final String PROBLEM_TYPE = "Type";
	private static final String PROBLEM_DESCRIPTION = "Description";
	private static final String PROBLEM_UPDATE_TIME = "Change_Date";

	// Variables to handle field names within symptom table
	private static final String SYMPTOM_ID = "S_ID";
	private static final String SYMPTOM_DESCRIPTION = "Description";
	private static final String SYMPTOM_PARENT = "Parent_Symptom";
	private static final String SYMPTOM_CHANGE_DATE = "Change_Date";
	private static final String SYMPTOM_TYPE = "Type";

	// Variables to handle the field names within the picture table
	private static final String PROBLEM_PICTURE_ID = "Picture_ID";
	private static final String PROBLEM_PICTURE_PROBLEM_ID = "P_ID";
	private static final String PROBLEM_PICTURE_URL = "URL";
	private static final String PROBLEM_PICTURE_CHANGE_DATE = "Change_Date";
	
	// Variables to handle the field names within the picture table
	private static final String SYMPTOM_PICTURE_ID = "Picture_ID";
	private static final String SYMPTOM_PICTURE_SYMPTOM_ID = "S_ID";
	private static final String SYMPTOM_PICTURE_URL = "URL";
	private static final String SYMPTOM_PICTURE_CHANGE_DATE = "Change_Date";

	// Variables to handle the field names within the disease/symptom link table
	private static final String LINK_ID = "LS_ID";
	private static final String LINK_DIS_ID = "P_ID";
	private static final String LINK_SYM_ID = "S_ID";
	
	// Variables to handle the field names within the version table
	private static final String VERSION_ID = "v_id";
	private static final String VERSION_NUMBER = "v_number";
	
	UserDecisionStore decisions;

	public DiseaseDatabaseController(Context context) {
		super(context, DATABASE_NAME, null, VERSION);
	}

	/*
	 * Method to handle creation of the database on launch
	 * 
	 * @author Stephanie Lee
	 * 
	 * @see
	 * android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite
	 * .SQLiteDatabase)
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {

		// TODO Auto-generated method stub
		String CREATE_PROBLEM_TABLE = "CREATE TABLE IF NOT EXISTS "
				+ TABLE_PROBLEM + "(" + PROBLEM_ID + " INTEGER PRIMARY KEY, "
				+ PROBLEM_NAME + " TEXT, " + PROBLEM_TYPE + " TEXT, "
				+ PROBLEM_DESCRIPTION + " TEXT, " + PROBLEM_UPDATE_TIME
				+ " TEXT" + ");";
		db.execSQL(CREATE_PROBLEM_TABLE);

		String CREATE_SYMPTOM_TABLE = "CREATE TABLE IF NOT EXISTS "
				+ TABLE_SYMPTOM + "(" + SYMPTOM_ID + " INTEGER PRIMARY KEY, "
				+ SYMPTOM_DESCRIPTION + " TEXT, " + SYMPTOM_PARENT
				+ " INTEGER, " + SYMPTOM_TYPE + " TEXT," + SYMPTOM_CHANGE_DATE
				+ " TEXT" + ")";
		db.execSQL(CREATE_SYMPTOM_TABLE);

		String CREATE_PROBLEM_PICTURE_TABLE = "CREATE TABLE IF NOT EXISTS "
				+ TABLE_PROBLEM_PICTURE + "(" + PROBLEM_PICTURE_ID + " INTEGER PRIMARY KEY, "
				+ PROBLEM_PICTURE_PROBLEM_ID + " INTEGER, " + PROBLEM_PICTURE_URL + " TEXT, "
				+ PROBLEM_PICTURE_CHANGE_DATE + " LONG" + ")";
		db.execSQL(CREATE_PROBLEM_PICTURE_TABLE);
		
		String CREATE_SYMPTOM_PICTURE_TABLE = "CREATE TABLE IF NOT EXISTS "
				+ TABLE_SYMPTOM_PICTURE + "(" + SYMPTOM_PICTURE_ID + " INTEGER PRIMARY KEY, "
				+ SYMPTOM_PICTURE_SYMPTOM_ID + " INTEGER, " + SYMPTOM_PICTURE_URL + " TEXT, "
				+ SYMPTOM_PICTURE_CHANGE_DATE + " LONG" + ")";
		db.execSQL(CREATE_SYMPTOM_PICTURE_TABLE);

		String CREATE_LINK_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_LINK
				+ "(" + LINK_ID + " INTEGER PRIMARY KEY, " + LINK_DIS_ID
				+ " INTEGER, " + LINK_SYM_ID + " INTEGER" + ")";
		db.execSQL(CREATE_LINK_TABLE);
		
		String CREATE_VERSION_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_VERSION
				+ "(" + VERSION_ID + " INTEGER PRIMARY KEY, " + VERSION_NUMBER 
				+ " TEXT )";
		db.execSQL(CREATE_VERSION_TABLE);
	}

	/*
	 * Method to handle update the table when any structural changes are made
	 * 
	 * @author Stephanie Lee
	 * 
	 * @see
	 * android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite
	 * .SQLiteDatabase, int, int)
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROBLEM);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_SYMPTOM);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROBLEM_PICTURE);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_SYMPTOM_PICTURE);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_LINK);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_VERSION);

		onCreate(db);
	}

	public void rebuildDatabase() {
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROBLEM);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_SYMPTOM);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROBLEM_PICTURE);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_SYMPTOM_PICTURE);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_LINK);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_VERSION);

		onCreate(db);
	}

	/*
	 * Method to add a new disease to the list
	 * 
	 * @author Stephanie Lee
	 */
	public void addProblem(Problem problem) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL("INSERT OR REPLACE INTO " + TABLE_PROBLEM + " VALUES ( '"+ problem.getId() 
				+ "' ,'" + problem.getName() + "', '" + problem.getType() + "', '" + problem.getDescription()
				+ "', '" + problem.getUpdateTime() + "')");

		for (Integer id : problem.getSymptoms()) {
			db.execSQL("INSERT OR REPLACE INTO " + TABLE_LINK + " (" + LINK_DIS_ID + ", " + LINK_SYM_ID + ") VALUES ('" + problem.getId()
					+ "', '" + id + "')");
		}
		db.close();
	}

	public void addSymptom(Symptom sym) {
		SQLiteDatabase db = this.getWritableDatabase();
		
		db.execSQL("INSERT OR REPLACE INTO " + TABLE_SYMPTOM + " VALUES ('" + sym.getId() + "', '" + sym.getDescription()
				+ "', '" + sym.getParent() + "', '" + sym.getPart() + "', '" + sym.getUpdateTime() + "')");
		
		db.close();
	}

	public int getProblemId(int id) {
		decisions = UserDecisionStore.getInstance();
		String query;
		ArrayList<Integer> ids = decisions.getSelectedSymptoms();
		int problem = 0;
		String csv = ids.toString().replace("[", "").replace("]", "");
		if (ids.size() == 0) {
			query = "SELECT * FROM " + TABLE_LINK + " WHERE " + SYMPTOM_ID+"="+id;
		} else {
			query = "SELECT "+ PROBLEM_ID +" from "+ TABLE_LINK +" where "+ SYMPTOM_ID + " in ("+csv+") group by "+PROBLEM_ID+" having count(distinct "+SYMPTOM_ID+") = "+ids.size();
		}
		Log.v("query", query);
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		if (cursor.moveToFirst()) {
			do {
				problem = (Integer.parseInt(cursor.getString(1)));
			} while (cursor.moveToNext());
		}
		return problem;
	}

	public Problem getProblem(int id) {
		Problem problem = new Problem();
		String query = "SELECT * FROM " + TABLE_PROBLEM + " WHERE "
				+ PROBLEM_ID + "=" + id;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		if (cursor.moveToFirst()) {
			do {
				problem.setId(Integer.parseInt(cursor.getString(0)));
				problem.setDescription(cursor.getString(3));
				problem.setName(cursor.getString(1));
				// disease.setUpdateTime(cursor.getString(4));
			} while (cursor.moveToNext());
		}
		return problem;
	}

	public Problem getDisease(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_PROBLEM, new String[] { PROBLEM_ID,
				PROBLEM_NAME, PROBLEM_TYPE, PROBLEM_DESCRIPTION,
				PROBLEM_UPDATE_TIME }, null, null, null, null, null);

		if (cursor != null) {
			cursor.moveToFirst();
		}

		Problem Problem = new Problem();

		return Problem;
	}

	public List<Symptom> getSymptom(String part) {
		List<Symptom> symList = new ArrayList<Symptom>();
		String query = "SELECT * FROM " + TABLE_SYMPTOM + " WHERE "
				+ SYMPTOM_TYPE + "='" + part + "'";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);

		if (cursor.moveToFirst()) {
			do {
				Symptom symptom = new Symptom();
				symptom.setId(Integer.parseInt(cursor.getString(0)));
				symptom.setDescription(cursor.getString(1));
				symptom.setParent(Integer.parseInt(cursor.getString(2)));
				// disease.setUpdateTime(cursor.getString(4));

				symList.add(symptom);
			} while (cursor.moveToNext());
		}
		return symList;
	}

	public List<Problem> getAllProblems() {
		List<Problem> ProblemList = new ArrayList<Problem>();
		String query = "SELECT * FROM " + TABLE_PROBLEM;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);

		if (cursor.moveToFirst()) {
			do {
				Problem problem = new Problem();
				problem.setId(Integer.parseInt(cursor.getString(0)));
				problem.setName(cursor.getString(1));
				problem.setType(cursor.getString(2));
				problem.setDescription(cursor.getString(3));
				// disease.setUpdateTime(cursor.getString(4));

				ProblemList.add(problem);
			} while (cursor.moveToNext());
		}
		return ProblemList;
	}

	public List<Symptom> getAllSymptoms() {
		List<Symptom> symptomList = new ArrayList<Symptom>();
		String query = "SELECT * FROM " + TABLE_SYMPTOM;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);

		if (cursor.moveToFirst()) {
			do {
				Symptom symptom = new Symptom();
				symptom.setId(Integer.parseInt(cursor.getString(0)));
				symptom.setDescription(cursor.getString(1));
				symptom.setParent(Integer.parseInt(cursor.getString(2)));
				// disease.setUpdateTime(cursor.getString(4));

				symptomList.add(symptom);
			} while (cursor.moveToNext());
		}
		return symptomList;
	}

	public List<Symptom> getSymptomFromParent(int parent) {
		List<Symptom> symptomList = new ArrayList<Symptom>();
		String query = "SELECT * FROM " + TABLE_SYMPTOM + " WHERE "
				+ SYMPTOM_PARENT + "=" + parent;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);

		if (cursor.moveToFirst()) {
			do {
				Symptom symptom = new Symptom();
				symptom.setId(Integer.parseInt(cursor.getString(0)));
				symptom.setDescription(cursor.getString(1));
				symptom.setParent(Integer.parseInt(cursor.getString(2)));
				// disease.setUpdateTime(cursor.getString(4));

				symptomList.add(symptom);
			} while (cursor.moveToNext());
		}
		return symptomList;
	}

	public int getDiseaseCount() {
		String query = "SELECT * FROM " + TABLE_PROBLEM;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		cursor.close();

		return cursor.getCount();
	}

	public int getSymptomCount() {
		String query = "SELECT * FROM " + TABLE_SYMPTOM;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		cursor.close();

		return cursor.getCount();
	}

	public int updateProblem(Problem Problem) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(PROBLEM_NAME, Problem.getName());
		values.put(PROBLEM_TYPE, Problem.getType());
		values.put(PROBLEM_DESCRIPTION, Problem.getDescription());

		return db.update(TABLE_PROBLEM, values, PROBLEM_ID + " =?",
				new String[] { String.valueOf(Problem.getId()) });
	}

	public int updateSymptom(Symptom symptom) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(SYMPTOM_DESCRIPTION, symptom.getDescription());
		values.put(SYMPTOM_PARENT, symptom.getParent());

		return db.update(TABLE_SYMPTOM, values, SYMPTOM_ID + " =?",
				new String[] { String.valueOf(symptom.getId()) });
	}

	public void deleteDisease(Problem disease) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_PROBLEM, PROBLEM_ID + " =?",
				new String[] { String.valueOf(disease.getId()) });
		db.close();
	}
	
	public void setNewVersion() {
		DateTime date = new DateTime();
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL("INSERT OR REPLACE INTO " + TABLE_VERSION + " VALUES ( '1' ," +String.valueOf(date.getMillis()) +
				")");
		db.close();
	}
	
	public String getVersionNumber() {
		String query = "SELECT " + VERSION_NUMBER + " FROM " + TABLE_VERSION + " WHERE " + VERSION_ID + " = 1 ";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		String version = null;
		if (cursor.moveToFirst()) {
				version = cursor.getString(0);
		}
		else {
			db.close();
			return null;
		}
		db.close();
		return version;
	}
	
	public void updateProblemPicture(Picture picture) {
		String query = "INSERT OR REPLACE INTO " + TABLE_PROBLEM_PICTURE + "(" + PROBLEM_PICTURE_PROBLEM_ID
				+ ", " + PROBLEM_PICTURE_URL + ", " + PROBLEM_PICTURE_CHANGE_DATE + ") " +
				"VALUES ('" + picture.getEntityID() + "', '" + picture.getUrl() + "', '" + picture.getUpdateTime() + "')";
		
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL(query);
		
		db.close();
	}
	
	public ArrayList<Picture> getProblemPicture(int id) {
		String query = "SELECT * FROM " + TABLE_PROBLEM_PICTURE + " WHERE " + PROBLEM_PICTURE_PROBLEM_ID + " = '" + id + "'";
		ArrayList<Picture> pictures = new ArrayList<Picture>();
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		if (cursor.moveToFirst()) {
			do{
				Picture picture = new Picture();
				picture.setType("problem");
				picture.setId(cursor.getInt(0));
				picture.setEntityID(cursor.getInt(1));
				picture.setUrl(cursor.getString(2));
				picture.setUpdateTime(new DateTime(Long.parseLong(cursor.getString(3))));
				
				pictures.add(picture);
			} while (cursor.moveToNext());
		}
		else {
			db.close();
			return null;
		}
		db.close();
		return pictures;
	}
	
	public void updateSymptomPictures(Picture picture) {
		String query = "INSERT OR REPLACE INTO " + TABLE_SYMPTOM_PICTURE + "(" + SYMPTOM_PICTURE_SYMPTOM_ID
				+ ", " + SYMPTOM_PICTURE_URL + ", " + SYMPTOM_PICTURE_CHANGE_DATE + ") " +
				"VALUES ('" + picture.getEntityID() + "', '" + picture.getUrl() + "', '" + picture.getUpdateTime().getMillis() + "')";
		
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL(query);
		
		db.close();
	}
	
	public Picture getSymptomPictures(int id) {
		String query = "SELECT * FROM " + TABLE_SYMPTOM_PICTURE + " WHERE " + SYMPTOM_PICTURE_SYMPTOM_ID + " = '" + id + "'";
		ArrayList<Picture> pictures = new ArrayList<Picture>();
		SQLiteDatabase db = this.getWritableDatabase();

		Picture picture = new Picture();
		Cursor cursor = db.rawQuery(query, null);
		if (cursor.moveToFirst()) {
				picture.setType("symptom");
				picture.setId(cursor.getInt(0));
				picture.setEntityID(cursor.getInt(1));
				picture.setUrl(cursor.getString(2));
				picture.setUpdateTime(new DateTime(Long.parseLong(cursor.getString(3))));
		}
		else {
			db.close();
			return null;
		}
		db.close();
		return picture;
	}
	
	public ArrayList<Picture> getNewPictures(String lastUpdate) {
		String query = "SELECT * FROM " + TABLE_SYMPTOM_PICTURE + " WHERE " + SYMPTOM_PICTURE_CHANGE_DATE + " > '" + lastUpdate + "'";
		Log.d("query", query);
		ArrayList<Picture> pictures = new ArrayList<Picture>();
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		if (cursor.moveToFirst()) {
			do{
				Picture picture = new Picture();
				picture.setType("symptom");
				picture.setId(cursor.getInt(0));
				picture.setEntityID(cursor.getInt(1));
				picture.setUrl(cursor.getString(2));
				picture.setUpdateTime(new DateTime(Long.parseLong(cursor.getString(3))));
				
				pictures.add(picture);
			} while (cursor.moveToNext());
		}
		query = "SELECT * FROM " + TABLE_PROBLEM_PICTURE + " WHERE " + PROBLEM_PICTURE_CHANGE_DATE + " > '" + lastUpdate + "'";
		cursor = db.rawQuery(query, null);
		if (cursor.moveToFirst()) {
			do{
				Picture picture = new Picture();
				picture.setType("problem");
				picture.setId(cursor.getInt(0));
				picture.setEntityID(cursor.getInt(1));
				picture.setUrl(cursor.getString(2));
				picture.setUpdateTime(new DateTime(cursor.getString(3)));
				
				pictures.add(picture);
			} while (cursor.moveToNext());
		}
		else {
			db.close();
		}
		
		db.close();
		return pictures;
	}
}
