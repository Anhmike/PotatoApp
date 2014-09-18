package com.PotatoServer.Models;

import javax.sql.DataSource;

public class LoginModel {
	private DataSource _ds = null;
	public LoginModel(){

	}

	public void setDatasource(DataSource _ds){
		this._ds=_ds;
		System.out.println("Set Data Source in Model"+_ds.toString());
	}

	
	public boolean checkLogin(String username, String Passsword) {
		return true;
	}
}
