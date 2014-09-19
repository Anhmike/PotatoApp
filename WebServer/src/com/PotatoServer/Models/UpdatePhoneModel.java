package com.PotatoServer.Models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;

import javax.sql.DataSource;

import org.joda.time.DateTime;

import com.PotatoServer.Stores.ProblemStore;
import com.PotatoServer.Stores.SymptomStore;

public class UpdatePhoneModel {
	private DataSource _ds = null;
	
	public UpdatePhoneModel(){

	}

	public void setDatasource(DataSource _ds){
		this._ds=_ds;
		System.out.println("Set Data Source in Model"+_ds.toString());
	}

	
	public String getUpdatedProblems (String userTime) {
		LinkedList<ProblemStore> psl = new LinkedList<ProblemStore>();
		Connection Conn;
		ProblemStore ps = null;
		ResultSet rs = null;
		try {
			Conn = _ds.getConnection();
		} catch (Exception et) {

			System.out.println("No Connection in Faults Model");
			return null;
		}

		//PreparedStatement pmst = null;
		Statement stmt = null;
		String sqlQuery = "select * from problems where change_date > " + userTime;
		System.out.println("Problem Query " + sqlQuery);
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
				ps.setId(Integer.parseInt(rs.getString("p_id")));
				ps.setName(rs.getString("name"));
				ps.setDescription(rs.getString("description"));
				ps.setType(rs.getString("type"));
				ps.setUpdateDate(DateTime.parse(rs.getString("change_date")));
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
		
		return parseProblems(psl);
		
	}
	
	public String getUpdatesSymptoms (String userTime) {
		LinkedList<SymptomStore> psl = new LinkedList<SymptomStore>();
		Connection Conn;
		SymptomStore ss = null;
		ResultSet rs = null;
		try {
			Conn = _ds.getConnection();
		} catch (Exception et) {

			System.out.println("No Connection in Faults Model");
			return null;
		}

		//PreparedStatement pmst = null;
		Statement stmt = null;
		String sqlQuery = "select * from symptoms where change_date > " + userTime;
		System.out.println("Problem Query " + sqlQuery);
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
				ss.setId(Integer.parseInt(rs.getString("p_id")));
				ss.setDescription(rs.getString("description"));
				ss.setParentSymptom(Integer.parseInt(rs.getString("parent_symptom")));
				psl.add(ss);
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
		return parseSymptoms(psl);
		
	}
	
	private String parseProblems(LinkedList<ProblemStore> list) {
		if(list == null) { return null;}
		
		StringBuilder xml = new StringBuilder();
		
		xml.append("<problem>");
		
		for(ProblemStore problem: list) {
			xml.append("<problem id=" + problem.getId() + ">");
			xml.append("<name>" + problem.getName() + "</name>");
			xml.append("<description>" + problem.getDescription() + "</description>");
			xml.append("<type>" + problem.getType() + "</type>");
			xml.append("<updateTime>" + problem.getUpdateDate() + "</updateTime>");
			
			for(Integer i: problem.getSymptoms()){
				xml.append("<symptoms>" + i + "</symptoms>");
			}
			
			xml.append("</problem>");
		}
		
		xml.append("</problems>");
		return xml.toString();
	}
	
	private String parseSymptoms(LinkedList<SymptomStore> list) {
		if(list == null) { return null; }
		
		StringBuilder xml = new StringBuilder();
		
		xml.append("<symptoms>");
		
		for (SymptomStore symptom: list) {
			xml.append("<symptom id=" + symptom.getId() + ">");
			xml.append("<description>" + symptom.getDescription() + "</description>");
			xml.append("<updateTime>" + symptom.getUpdateDate() + "</updateTime>");
			xml.append("<parent>" + symptom.getParentSymptom() + "</parent>");
			xml.append("</symptom>");
		}
		
		xml.append("</symptoms>");
		
		return xml.toString();	
	}
}
