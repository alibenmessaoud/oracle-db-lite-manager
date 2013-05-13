package com.dbop.servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dbop.db.DataService;

public class TablespaceAdd extends HttpServlet {
	public void doGet( HttpServletRequest request, HttpServletResponse
			response ) throws ServletException, IOException{
			 
			 this.getServletContext().getRequestDispatcher( "/WEB-INF/tablespaceadd.jsp").forward( request, response );
			}
}
