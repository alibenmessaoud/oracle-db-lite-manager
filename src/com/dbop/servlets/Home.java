package com.dbop.servlets;

import com.dbop.db.*;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Home extends HttpServlet {
	public void doGet( HttpServletRequest request, HttpServletResponse
			response ) throws ServletException, IOException{
			 DataService ds = new DataService();
			 
			 this.getServletContext().getRequestDispatcher( "/WEB-INF/home.jsp").forward( request, response );
			 
			}
}
