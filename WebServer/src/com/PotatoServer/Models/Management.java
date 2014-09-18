package com.PotatoServer.Models;

import javax.sql.DataSource;

public class Management {
	
	private DataSource _ds = null;

	public void setDatasource(DataSource _ds){
		this._ds=_ds;
		System.out.println("Set Data Source in Model"+_ds.toString());
		
	}
	
}
