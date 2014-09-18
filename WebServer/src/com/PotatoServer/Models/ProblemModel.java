package com.PotatoServer.Models;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.LinkedList;


import java.sql.Connection;


import com.PotatoServer.Stores.ProblemDes;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.StatementImpl;

import java.sql.Statement;

public class ProblemModel {

	
	private DataSource _ds = null;
	public ProblemModel(){

	}

	public void setDatasource(DataSource _ds){
		this._ds=_ds;
		System.out.println("Set Data Source in Model"+_ds.toString());
	}

    public LinkedList<ProblemDes> getDES(){
		LinkedList<ProblemDes> psl = new LinkedList<ProblemDes>();
		Connection Conn;
		ProblemDes ps = null;
		ResultSet rs = null;
		try {
				Conn = _ds.getConnection();
		} catch (Exception et) {

			System.out.println("No Connection in Faults Model");
			return null;
		}

		PreparedStatement pmst = null;
		Statement stmt = null;
		String sqlQuery = "select summary,idfault from fault";
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
				ps = new ProblemDes();
				ps.setP_ID(rs.getString("P_ID "));
				ps.SetDescription(rs.getString("Description "));
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
}
