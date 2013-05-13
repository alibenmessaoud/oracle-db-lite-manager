package com.dbop.servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dbop.beans.Tablespace;
import com.dbop.beans.User;
import com.dbop.db.DataService;

public class TablespaceEditFile extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String tablespaceName = request.getParameter("tablespaceID");
		String FileName = request.getParameter("fileID");
		
		request.setAttribute("tablespaceName", tablespaceName);
		
		DataService ds = new DataService();
		HttpSession httpSession = request.getSession();
		String myLogin = (String)httpSession.getAttribute("myLogin");
		String myPassword = (String)httpSession.getAttribute("myPassword");
		System.out.println(myLogin+myPassword);
		try {
			ResultSet rsQuery = ds.getResultSet(myLogin, myPassword, "select FILE_NAME, BYTES, STATUS, AUTOEXTENSIBLE, " +
												 "USER_BYTES , ONLINE_STATUS from dba_data_files " +
												 "where tablespace_name like '"+tablespaceName+"' and " +
												 		"file_name like '"+FileName+"'");
			while (rsQuery.next()) 
			{			
				request.setAttribute("FILE_NAME", rsQuery.getString("FILE_NAME"));
				request.setAttribute("AUTOEXTENSIBLE", rsQuery.getString("AUTOEXTENSIBLE"));
				request.setAttribute("FILE_NAME", rsQuery.getString("FILE_NAME"));
				request.setAttribute("FILE_NAME", rsQuery.getString("FILE_NAME"));
				
				 
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

		this.getServletContext()
				.getRequestDispatcher("/WEB-INF/tablespaceeditfile.jsp")
				.forward(request, response);
	}
}
