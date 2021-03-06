package com.PotatoServer.Models;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.servlet.ServletOutputStream;
import javax.sql.DataSource;

import org.joda.time.DateTime;

import com.PotatoServer.Stores.Picture;
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

	
	public boolean getUpdatedProblems (String userTime, ServletOutputStream output) throws IOException {
		LinkedList<ProblemStore> psl = new LinkedList<ProblemStore>();
		Connection Conn;
		ProblemStore ps = null;
		ResultSet rs = null;
		try {
			Conn = _ds.getConnection();
		} catch (Exception et) {

			System.out.println("No Connection in Faults Model");
			return false;
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
				System.out.println("Getting RS");
				ps = new ProblemStore();
				ps.setId(Integer.parseInt(rs.getString("p_id")));
				ps.setName(rs.getString("name"));
				ps.setDescription(rs.getString("description"));
				ps.setType(rs.getString("type"));
				ps.setUpdateDate(new DateTime((rs.getDate("change_date").getTime())));
				System.out.println("millis : " + (rs.getDate("change_date").getTime()));
				psl.add(ps);
			}
		} catch (Exception ex) {
			System.out.println("Opps, error in query " + ex);
			return false;
		}
		

		for(ProblemStore problem: psl) {
			//PreparedStatement pmst = null;
			stmt = null;
			sqlQuery = "select s_id from link_symptoms where p_id = '" + problem.getId() + "';";
			System.out.println("Problem Query " + sqlQuery);
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
					problem.getSymptoms().add(rs.getInt("s_id"));
				}
			} catch (Exception ex) {
				System.out.println("Opps, error in query " + ex);
				return false;
			}
		}

		try {

			Conn.close();
		} catch (Exception ex) {
			return false;
		}
		
		return parseProblems(psl, output);
		
	}
	
	public boolean getUpdatedSymptoms (String userTime, ServletOutputStream output) throws IOException {
		LinkedList<SymptomStore> psl = new LinkedList<SymptomStore>();
		Connection Conn;
		SymptomStore ss = null;
		ResultSet rs = null;
		try {
			Conn = _ds.getConnection();
		} catch (Exception et) {

			System.out.println("No Connection in Faults Model");
			return false;
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
				System.out.println("Getting RS");
				ss = new SymptomStore();
				ss.setId(Integer.parseInt(rs.getString("s_id")));
				ss.setDescription(rs.getString("description"));
				if(rs.getString("parent_symptom") != null ) { ss.setParentSymptom(Integer.parseInt(rs.getString("parent_symptom")));}
				ss.setUpdateDate(new DateTime((rs.getDate("change_date").getTime())));
				ss.setType(rs.getString("type"));
				psl.add(ss);
			}
		} catch (Exception ex) {
			System.out.println("Opps, error in query " + ex);
			return false;
		}

		try {

			Conn.close();
		} catch (Exception ex) {
			return false;
		}
		return parseSymptoms(psl, output);
		
	}
	public boolean getUpdatedImages(String lastUpdate, ServletOutputStream out) throws IOException {
		Connection Conn;
		ResultSet rs;
		ArrayList<Picture> pictures = new ArrayList<Picture>();

		try {
			Conn = _ds.getConnection();
		} catch (Exception et) {

			System.out.println("No Connection in Faults Model");
			return false;
		}

		//PreparedStatement pmst = null;
		Statement stmt = null;
		String sqlQuery = "select * from problem_pictures where change_date > " + lastUpdate;
		System.out.println("Problem Query " + sqlQuery);
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
				Picture picture = new Picture();
				picture.setId(rs.getInt("picture_id"));
				picture.setType("problem");
				picture.setEntityID(rs.getInt("p_id"));
				picture.setUrl(rs.getString("url"));
				picture.setUpdateTime(new DateTime((rs.getDate("change_date").getTime())));
				
				pictures.add(picture);
			}
		} catch (Exception ex) {
			System.out.println("Opps, error in query " + ex);
			return false;
		}
		
		//PreparedStatement pmst = null;
		stmt = null;
		sqlQuery = "select * from symptom_pictures where change_date > " + lastUpdate;
		System.out.println("Problem Query " + sqlQuery);
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
				Picture picture = new Picture();
				picture.setId(rs.getInt("picture_id"));
				picture.setType("symptom");
				picture.setEntityID(rs.getInt("s_id"));
				picture.setUrl(rs.getString("url"));
				picture.setUpdateTime(new DateTime((rs.getDate("change_date").getTime())));

				pictures.add(picture);
			}
		} catch (Exception ex) {
			System.out.println("Opps, error in query " + ex);
			return false;
		}

		try {

			Conn.close();
		} catch (Exception ex) {
			return false;
		}
		return parsePictures(pictures, out);

	}
	
	private boolean parsePictures(ArrayList<Picture> pictures, ServletOutputStream out) throws IOException {
		if(pictures == null) { return false; }
		
		out.println("<pictures>");
		for(Picture image: pictures) {
			out.println("<picture>");
			out.println("<id>" + image.getId() + "</id>");
			out.println("<entityID>" + image.getEntityID() + "</entityID>");
			out.println("<type>" + image.getType() + "</type>");
			out.println("<url>" + image.getUrl() + "</url>");
			out.println("<updateDate>" + image.getUpdateTime().getMillis() + "</updateDate>");
			out.println("</picture>");
		}
		
		out.println("</pictures>");
		
		return true;
	}

	private boolean parseProblems(LinkedList<ProblemStore> list, ServletOutputStream output) throws IOException {
		if(list == null) { return false; }
		
		
		
		output.println("<problems>");
		
		for(ProblemStore problem: list) {
			output.println("<problem>");
			output.println("<id>" + problem.getId() + "</id>");
			output.println("<name>" + problem.getName() + "</name>");
			output.println("<description>" + problem.getDescription() + "</description>");
			output.println("<type>" + problem.getType() + "</type>");
			output.println("<updateTime>" + problem.getUpdateDate().getMillis() + "</updateTime>");
			
			if(problem.getSymptoms() != null) {
				for(Integer i: problem.getSymptoms()){
					output.println("<symptoms>" + i + "</symptoms>");
				}
			}
			
			output.println("</problem>");
		}
		
		output.println("</problems>");
		return true;
	}
	
	private boolean parseSymptoms(LinkedList<SymptomStore> list, ServletOutputStream output) throws IOException {
		if(list == null) { return false; }
		
		
		output.println("<symptoms>");
		
		for (SymptomStore symptom: list) {
			output.println("<symptom>");
			output.println("<id>"+ symptom.getId() + "</id>");
			output.println("<description>" + symptom.getDescription() + "</description>");
			output.println("<updateTime>" + symptom.getUpdateDate().getMillis() + "</updateTime>");
			output.println("<parentSymptom>" + symptom.getParentSymptom() + "</parentSymptom>");
			output.println("<type>" + symptom.getType() + "</type>");
			output.println("</symptom>");
		}
		
		output.println("</symptoms>");
		
		return true;	
	}

	
}
