package com.PotatoServer.Models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.sql.DataSource;

import org.joda.time.DateTime;

import com.PotatoServer.Stores.SymptomStore;
import com.mysql.jdbc.PreparedStatement;

public class SymptomModel {
	private DataSource _ds = null;

	public void setDatasource(DataSource _ds){
		this._ds=_ds;
		System.out.println("Set Data Source in Model"+_ds.toString());
	}

	public LinkedList<SymptomStore> getAllSymptoms() {
		LinkedList<SymptomStore> ssl = new LinkedList<SymptomStore>();
		Connection Conn;
		SymptomStore ss = null;
		ResultSet rs = null;
		try {
			Conn = _ds.getConnection();
		} catch (Exception et) {

			System.out.println("No Connection in Problem Model");
			return null;
		}

		PreparedStatement pmst = null;
		Statement stmt = null;
		String sqlQuery = "select * from symptoms";
		System.out.println("Potato Query " + sqlQuery);
		try {
			try {
				// pmst = Conn.prepareStatement(sqlQuery);
				stmt = Conn.createStatement();
			} catch (Exception et) {
				System.out.println("Can't create prepare statement");
				return null;
			}
			System.out.println("Created prepare");
			try {
				// rs=pmst.executeQuery();
				rs = stmt.executeQuery(sqlQuery);
			} catch (Exception et) {
				System.out.println("Can not execut query here " + et);
				return null;
			}
			System.out.println("Statement executed");
			if (rs.wasNull()) {
				System.out.println("result set was null");
			} else {
				System.out.println("Well it wasn't null");
			}
			while (rs.next()) {
				System.out.println("Getting RS");
				ss = new SymptomStore();
				ss.setId(rs.getInt("s_ID"));
				ss.setDescription(rs.getString("Description"));
				ss.setParentSymptom(rs.getInt("parent_symptom"));
				ss.setUpdateDate(new DateTime(rs.getDate("change_date")));
				ssl.add(ss);
			}
		} catch (Exception ex) {
			System.out.println("Opps, error in query " + ex);
			return null;
		}

		try {

			Conn.close();
		} catch (Exception ex) {
			return null;
		}
		return ssl;
	}


	public static SymptomStore getSymptomByID(Integer id, DataSource ds) {
		Connection Conn;
		SymptomStore ss = null;
		ResultSet rs = null;
		try {
			Conn = ds.getConnection();
		} catch (Exception et) {

			System.out.println("No Connection in Problem Model");
			return null;
		}

		PreparedStatement pmst = null;
		Statement stmt = null;
		String sqlQuery = "select * from symptoms where s_id ='" + id + "';";
		System.out.println("Potato Query " + sqlQuery);
		try {
			try {
				// pmst = Conn.prepareStatement(sqlQuery);
				stmt = Conn.createStatement();
			} catch (Exception et) {
				System.out.println("Can't create prepare statement");
				return null;
			}
			System.out.println("Created prepare");
			try {
				// rs=pmst.executeQuery();
				rs = stmt.executeQuery(sqlQuery);
			} catch (Exception et) {
				System.out.println("Can not execut query here " + et);
				return null;
			}
			System.out.println("Statement executed");
			if (rs.wasNull()) {
				System.out.println("result set was null");
			} else {
				System.out.println("Well it wasn't null");
			}
			while (rs.next()) {
				System.out.println("Getting RS");
				ss = new SymptomStore();
				ss.setId(rs.getInt("s_ID"));
				ss.setDescription(rs.getString("Description"));
				ss.setParentSymptom(rs.getInt("parent_symptom"));
				ss.setUpdateDate(new DateTime(rs.getDate("change_date")));
			}
		} catch (Exception ex) {
			System.out.println("Opps, error in query " + ex);
			return null;
		}

		try {

			Conn.close();
		} catch (Exception ex) {
			return null;
		}
		return ss;
	}

	public boolean updateSymptom(SymptomStore symptom, boolean isNew) {
		Connection Conn;
		try {
			Conn = _ds.getConnection();
		} catch (Exception et) {

			System.out.println("No Connection in Problem Model");
			return false;
		}

		PreparedStatement pmst = null;
		Statement stmt = null;
		String sqlQuery;
		Date date = new Date(System.currentTimeMillis());
		Time time = new Time(System.currentTimeMillis());
		String dateTime = date.toString() + " " + time.toString();
		
		if (isNew)
			sqlQuery = "INSERT IGNORE INTO symptoms SET `description` = '" + symptom.getDescription() + "', `parent_symptom` = '" + 
					symptom.getParentSymptom() + "', `change_date` = '" + dateTime + "', `type` = '" + symptom.getType() + "';";
		else 
			sqlQuery = "UPDATE symptoms SET `description` = '" + symptom.getDescription() + "', `parent_symptom` = '" + 
						symptom.getParentSymptom() + "', `change_date` = '" + dateTime + "', `type` = '" + symptom.getType() + "' where s_id ='" + symptom.getId() + "';";

		System.out.println("Potato Query " + sqlQuery);
		try {
			try {
				// pmst = Conn.prepareStatement(sqlQuery);
				stmt = Conn.createStatement();
			} catch (Exception et) {
				System.out.println("Can't create prepare statement");
				return false;
			}
			System.out.println("Created prepare");
			try {
				// rs=pmst.executeQuery();
				stmt.executeUpdate(sqlQuery);
			} catch (Exception et) {
				System.out.println("Can not execut query here " + et);
				return false;
			}
			System.out.println("Statement executed");

			return true;

		} catch (Exception ex) {
			System.out.println("Opps, error in query " + ex);
			return false;
		}
	}

	public boolean deleteSymptom(int id) {
		Connection Conn;
		try {
			Conn = _ds.getConnection();
		} catch (Exception et) {

			System.out.println("No Connection in Problem Model");
			return false;
		}

		PreparedStatement pmst = null;
		Statement stmt = null;
		String sqlQuery = "DELETE FROM SYMPTOMS WHERE S_ID = '" + id +"';";

		System.out.println("Potato Query " + sqlQuery);
		try {
			try {
				// pmst = Conn.prepareStatement(sqlQuery);
				stmt = Conn.createStatement();
			} catch (Exception et) {
				System.out.println("Can't create prepare statement");
				return false;
			}
			System.out.println("Created prepare");
			try {
				// rs=pmst.executeQuery();
				stmt.executeUpdate(sqlQuery);
			} catch (Exception et) {
				System.out.println("Can not execut query here " + et);
				return false;
			}
			System.out.println("Statement executed");

			return true;

		} catch (Exception ex) {
			System.out.println("Opps, error in query " + ex);
			return false;
		}
	}
	
	public boolean updateImageURLs(String url, String id, String name) {
		if(url == null) { return false; }
		Connection Conn;
		int symptomID = 0;

		ResultSet rs = null;
		try {
			Conn = _ds.getConnection();
		} catch (Exception et) {

			System.out.println("No Connection in Problem Model");
			return false;
		}

		PreparedStatement pmst = null;
		Statement stmt = null;

		if(id == null) {
			String sqlQuery = "select s_id from symptoms where description ='" + name + "';";
			System.out.println("Potato Query " + sqlQuery);
			try {
				try {
					// pmst = Conn.prepareStatement(sqlQuery);
					stmt = Conn.createStatement();
				} catch (Exception et) {
					System.out.println("Can't create prepare statement");
					return false;
				}
				System.out.println("Created prepare");
				try {
					// rs=pmst.executeQuery();
					rs = stmt.executeQuery(sqlQuery);
				} catch (Exception et) {
					System.out.println("Can not execut query here " + et);
					return false;
				}
				System.out.println("Statement executed");
				if (rs.wasNull()) {
					System.out.println("result set was null");
				} else {
					System.out.println("Well it wasn't null");
				}
				while (rs.next()) {
					symptomID = rs.getInt("s_id");
				}
			} catch (Exception ex) {
				System.out.println("Opps, error in query " + ex);
				return false;
			}
		} else {
			symptomID = Integer.parseInt(id);
		}

		stmt = null;
		String sqlQuery;
		Date date = new Date(System.currentTimeMillis());
		Time time = new Time(System.currentTimeMillis());
		String dateTime = date.toString() + " " + time.toString();

		sqlQuery = "INSERT IGNORE INTO symptom_pictures (`s_id`, `url`, `change_date`) VALUES ('" + symptomID + "', '" + url + "', '" + dateTime + "'),";
		
		sqlQuery = sqlQuery.substring(0, sqlQuery.length() - 1) + ";";

		System.out.println("Potato Query " + sqlQuery);
		try {
			try {
				// pmst = Conn.prepareStatement(sqlQuery);
				stmt = Conn.createStatement();
			} catch (Exception et) {
				System.out.println("Can't create prepare statement");
				return false;
			}
			System.out.println("Created prepare");
			try {
				// rs=pmst.executeQuery();
				stmt.executeUpdate(sqlQuery);
			} catch (Exception et) {
				System.out.println("Can not execut query here " + et);
				return false;
			}
			System.out.println("Statement executed");

		} catch (Exception ex) {
			System.out.println("Opps, error in query " + ex);
			return false;
		}

		try {

			Conn.close();
		} catch (Exception ex) {
			return false;
		}
		return true;
	}
	public ArrayList<Integer> getAllSymptomsForProblem(int problemID) {
		ArrayList<Integer> ssl = new ArrayList<Integer>();
		Connection Conn;
		SymptomStore ss = null;
		ResultSet rs = null;
		try {
			Conn = _ds.getConnection();
		} catch (Exception et) {

			System.out.println("No Connection in Problem Model");
			return null;
		}

		PreparedStatement pmst = null;
		Statement stmt = null;
		String sqlQuery = "SELECT symptoms.s_id FROM Symptoms INNER JOIN link_symptoms ON symptoms.s_id = link_symptoms.s_id WHERE link_symptoms.p_id ='" + problemID + "';" ;
		System.out.println("Potato Query " + sqlQuery);
		try {
			try {
				// pmst = Conn.prepareStatement(sqlQuery);
				stmt = Conn.createStatement();
			} catch (Exception et) {
				System.out.println("Can't create prepare statement");
				return null;
			}
			System.out.println("Created prepare");
			try {
				// rs=pmst.executeQuery();
				rs = stmt.executeQuery(sqlQuery);
			} catch (Exception et) {
				System.out.println("Can not execut query here " + et);
				return null;
			}
			System.out.println("Statement executed");
			if (rs.wasNull()) {
				System.out.println("result set was null");
			} else {
				System.out.println("Well it wasn't null");
			}
			while (rs.next()) {
				ssl.add(rs.getInt("s_id"));
			}
		} catch (Exception ex) {
			System.out.println("Opps, error in query " + ex);
			return null;
		}

		try {

			Conn.close();
		} catch (Exception ex) {
			return null;
		}
		return ssl;
	}
}
