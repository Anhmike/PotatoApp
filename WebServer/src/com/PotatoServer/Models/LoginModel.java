package com.PotatoServer.Models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

import com.PotatoServer.Stores.SymptomStore;

public class LoginModel {
	private DataSource _ds = null;
	public LoginModel(){

	}

	public void setDatasource(DataSource _ds){
		this._ds=_ds;
		System.out.println("Set Data Source in Model"+_ds.toString());
	}

	
	public boolean checkLogin(String username, String password) {
		
		Connection Conn;
		ResultSet rs = null;
		boolean successFlag = false;
		try {
			Conn = _ds.getConnection();
		} catch (Exception et) {

			System.out.println("No Connection in Faults Model");
			return successFlag;
		}

		//PreparedStatement pmst = null;
		Statement stmt = null;
		String sqlQuery = "select count(username) as rowcount from admin where username =  '" + username + "' AND pword ='" + password + "'";
		System.out.println("Problem Query " + sqlQuery);
		try {
			try {
				// pmst = Conn.prepareStatement(sqlQuery);
				stmt = Conn.createStatement();
			} catch (Exception et) {
				System.out.println("Can't create prepare statement");
				return successFlag;
			}
			System.out.println("Created prepare");
			try {
				rs = stmt.executeQuery(sqlQuery);
			} catch (Exception et) {
				System.out.println("Can not execut query here " + et);
				return successFlag;
			}
			System.out.println("Statement executed");
			if (!rs.next()) {
				System.out.println("result set was null");
			} else {
				successFlag = (rs.getInt("rowcount")) == 1 ? true : false;
			}
		} catch (Exception ex) {
			System.out.println("Opps, error in query " + ex);
			return successFlag;
		}

		try {

			Conn.close();
		} catch (Exception ex) {
			return successFlag;
		}
		
		return successFlag;
		//return true;
	}
}
