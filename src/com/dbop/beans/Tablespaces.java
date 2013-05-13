package com.dbop.beans;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.dbop.db.DataService;

public class Tablespaces {
	Tablespace tablespaces[] ;
	public int count ;
	
	public Tablespaces() {
		tablespaces = new Tablespace[100];
		count = 0;
	}
	
	public int getCount() {
		return count;
	}

	public void setTablespaces(Tablespace[] tablespaces) {
		this.tablespaces = tablespaces;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Tablespace[] getTablespaces(HttpServletRequest request) throws SQLException
	{
		DataService ds = new DataService();
		HttpSession httpSession = request.getSession();
		String myLogin = (String)httpSession.getAttribute("myLogin");
		String myPassword = (String)httpSession.getAttribute("myPassword");
		ResultSet rs = ds.getResultSet(myLogin, myPassword, "select * from dba_tablespaces".toUpperCase());
		int k = 0;
		while (rs.next()) 
		{
//			tablespaces[k] = new Tablespace(rs.getString("TABLESPACE_NAME"), optionsExtentManagment, optionsTablespaceType, 
//							SetPermenantTablespaceAsDefaultTablespace, SetTemporaryTablespaceAsDeafaultTemporaryTablespace, 
//							optionsTablespaceStatus, optionsStorage, files)  ;
			k++;
			count++;
		}
		
		return tablespaces;
		
	}
}
