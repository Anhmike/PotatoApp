package com.PotatoServer.Models;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
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
	
	public void editprob(String editN, String editT, String editD)
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
			
			if(editN !=null)
			{
			String sqlQuery = "call edit_problem("+ editN + editT + editD + ")";
			System.out.println("Potato Query " + sqlQuery);
			rs = stmt.executeQuery(sqlQuery);
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
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
		ProblemStore ss = null;
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
				ss = new ProblemStore();
				ss.setId(rs.getInt("P_ID"));
				ss.setName(rs.getString("Name"));
				ss.setDescription(rs.getString("Description"));
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
}
