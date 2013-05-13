package com.dbop.servlets;

import com.dbop.db.*;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserAdd extends HttpServlet {
	public void doGet( HttpServletRequest request, HttpServletResponse
			response ) throws ServletException, IOException{
			String message = "Transmission de variables : OK !";
			DataService ds = new DataService();
			HttpSession httpSession = request.getSession();
			String myLogin = (String)httpSession.getAttribute("myLogin");
			String myPassword = (String)httpSession.getAttribute("myPassword");
			
			String html="";
			ResultSet rsTBS;
			try {
				rsTBS = ds.getResultSet(myLogin, myPassword, " select tablespace_name from dba_tablespaces");
				while (rsTBS.next()) 
				{			
					String tablespace_name = rsTBS.getString("tablespace_name");
					html=html+"\""+tablespace_name+"\",";
					//System.out.println(tablespace_name);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			 
			 request.setAttribute( "tbs", html );
			 this.getServletContext().getRequestDispatcher( "/WEB-INF/useradd.jsp").forward( request, response );
			}
}
