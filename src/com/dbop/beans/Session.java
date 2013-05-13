package com.dbop.beans;

import java.sql.SQLException;

import com.dbop.db.DataService;

public class Session {
	String login;
	String password;
	public String getLogin() {
		return login;
	}
	public String getPassword() {
		return password;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Session(String login, String password) {
		super();
		this.login = login;
		this.password = password;
	}
	
	public int checkUser() throws SQLException
	{
		DataService ds = new DataService();
		int s = 0;
		if (login.equals("system") && password.equals("root"))
			s = 1;
		//return ds.nbOfResultSet("select count(username) as a from dba_users where username like '"+getLogin()+"' and password like '"+getPassword()+"'", "a");
		//System.out.println("select count(username) as a from dba_users where username like '"+getLogin()+"' and password like '"+getPassword()+"'");
		return s;
	}
	
	
}
