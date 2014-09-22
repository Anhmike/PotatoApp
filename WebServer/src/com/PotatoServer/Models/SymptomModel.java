package com.PotatoServer.Models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;

import javax.sql.DataSource;

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
				ss.setId(rs.getInt("P_ID"));
				ss.setDescription(rs.getString("Description"));
				ss.setParentSymptom(rs.getInt("parent_symptom"));
				//ss.setUpdateDate(rs.getString("change_date"));
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
}
