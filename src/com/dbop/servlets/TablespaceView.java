package com.dbop.servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dbop.beans.Tablespace;
import com.dbop.beans.User;
import com.dbop.db.DataService;

public class TablespaceView extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String tablespaceName = request.getParameter("tablespaceID");
		
		new Tablespace().tablespaceGet( request,  tablespaceName );
		new Tablespace().tablespaceDatafilesGet(request, tablespaceName);
		
			

		this.getServletContext()
				.getRequestDispatcher("/WEB-INF/tablespacedisplay.jsp")
				.forward(request, response);
	}
}
