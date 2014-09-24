package com.PotatoServer.Models;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.util.LinkedList;

import javax.sql.DataSource;

import org.joda.time.DateTime;

import com.PotatoServer.Stores.ProblemStore;
import com.PotatoServer.Stores.SymptomStore;
import com.mysql.jdbc.PreparedStatement;

public class ProblemModel {

	
	private DataSource _ds = null;
	public ProblemModel(){

	}

	public void setDatasource(DataSource _ds){
		this._ds=_ds;
		System.out.println("Set Data Source in Model"+_ds.toString());
		
	}
	
	public boolean updateProblem(ProblemStore problem, boolean isNew) {
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
			sqlQuery = "INSERT IGNORE INTO problems SET `description` = '" + problem.getDescription() + "', `name` = '" + 
					problem.getName() + "', `change_date` = '" + dateTime + "', `type` = '" + problem.getType() + "';";
		else 
			sqlQuery = "UPDATE problems SET `description` = '" + problem.getDescription() + "', `name` = '" + 
						problem.getName() + "', `type` = '" + problem.getType() + "', `change_date` = '" + dateTime + "' where s_id ='" + problem.getId() + "';";

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
	
	
	public void deleteprob(String id)
	{
		Connection Conn = null;
		ProblemStore ps = null;
		ResultSet rs = null;
		Statement stmt = null;
		
		try {
				Conn = _ds.getConnection();
		} catch (Exception et) {

			System.out.println("No Connection in Problem Model");
		}
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		
			stmt = Conn.createStatement();
			PreparedStatement pmst = null;
			
			if(id !=null)
			{
			String sqlQuery = "call delete_problem("+ id +")";
			System.out.println("Potato Query " + sqlQuery);
			rs = stmt.executeQuery(sqlQuery);
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

    public LinkedList<ProblemStore> getDES(){
		LinkedList<ProblemStore> psl = new LinkedList<ProblemStore>();
		Connection Conn;
		ProblemStore ps = null;
		ResultSet rs = null;
		try {
				Conn = _ds.getConnection();
		} catch (Exception et) {

			System.out.println("No Connection in Problem Model");
			return null;
		}

		PreparedStatement pmst = null;
		Statement stmt = null;
		String sqlQuery = "select P_ID, name, description from problems";
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
				ps = new ProblemStore();
				ps.setId(Integer.parseInt(rs.getString("P_ID")));
				ps.setName(rs.getString("Name"));
				ps.setDescription(rs.getString("Description"));
				psl.add(ps);
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
		return psl;

	}
    
    public static ProblemStore getProblemByID(Integer id, DataSource ds) {
		Connection Conn;
		ProblemStore ps = null;
		ResultSet rs = null;
		try {
			Conn = ds.getConnection();
		} catch (Exception et) {

			System.out.println("No Connection in Problem Model");
			return null;
		}

		PreparedStatement pmst = null;
		Statement stmt = null;
		String sqlQuery = "select * from problems where P_ID ='" + id + "';";
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
				ps = new ProblemStore();
				ps.setId(rs.getInt("P_ID"));
				ps.setName(rs.getString("Name"));
				ps.setDescription(rs.getString("Description"));
				ps.setType(rs.getString("type"));
				ps.setUpdateDate(new DateTime(rs.getDate("change_date")));
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
		return ps;
	}
}
