package com.dbop.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.dbop.beans.User;


public class test {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	/**
	 * @param args
	 * @throws SQLException
	 */
	@SuppressWarnings("static-access")
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		DataService ds = new DataService();
		
		
		ResultSet rsTBS = ds.getResultSet
				(
					"system","root","select TABLESPACE_NAME, STATUS, CONTENTS, LOGGING, EXTENT_MANAGEMENT, ALLOCATION_TYPE,  RETENTION, BIGFILE  from dba_tablespaces where tablespace_name like 'alibenmessaoud'"	
				);
		while (rsTBS.next()) 
		{			
			
			
			System.out.println(rsTBS.getString("tablespace_name"));
		}
		
		rsTBS = ds.getResultSet
				(
					"system", "root", "select FILE_NAME, BYTES, STATUS, AUTOEXTENSIBLE, USER_BYTES , ONLINE_STATUS from dba_data_files where tablespace_name like 'alibenmessaoud'"	
				);
		while (rsTBS.next()) 
		{			
			
			
			System.out.println(rsTBS.getString("file_name"));
		}
		
//		User user = new User();
//		try {
//			user.getUser("SYS");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		System.out.println(user.getStatus());
		
	}

}
