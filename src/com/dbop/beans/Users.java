package com.dbop.beans;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.dbop.db.DataService;

public class Users {
	User users[] ;
	public int count ;
	
	public Users() {
		users = new User[100];
		count = 0;
	}

	public User[] getUsers(HttpServletRequest request) throws SQLException
	{
		DataService ds = new DataService();
		HttpSession httpSession = request.getSession();
		String myLogin = (String)httpSession.getAttribute("myLogin");
		String myPassword = (String)httpSession.getAttribute("myPassword");
		ResultSet rs = ds.getResultSet(myLogin, myPassword, "select * from dba_users".toUpperCase());
		int k = 0;
		while (rs.next()) 
		{
			users[k] = new User(rs.getString("USERNAME"), rs.getString("PROFILE"), 
								rs.getString("PASSWORD"), "", 
								"", rs.getString("DEFAULT_TABLESPACE"), 
								rs.getString("TEMPORARY_TABLESPACE"), rs.getString("ACCOUNT_STATUS"), 
								rs.getString("EXPIRY_DATE"));
			k++;
			count++;
		}
		
		return users;
		
	}

	public int getCount() {
		return count;
	}

	public void setUsers(User[] users) {
		this.users = users;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	
}
