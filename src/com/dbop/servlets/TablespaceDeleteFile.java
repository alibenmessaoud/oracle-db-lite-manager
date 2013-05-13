package com.dbop.servlets;

import com.dbop.beans.Tablespace;
import com.dbop.db.*;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class TablespaceDeleteFile extends HttpServlet {
	public void doGet( HttpServletRequest request, HttpServletResponse
			response ) throws ServletException, IOException{
			 DataService ds = new DataService();
			 HttpSession httpSession = request.getSession();
				String myLogin = (String)httpSession.getAttribute("myLogin");
				String myPassword = (String)httpSession.getAttribute("myPassword");
				System.out.println(myLogin+myPassword);
			 String errors = "";
			 try {
				ds.runQuery(myLogin, myPassword,"alter tablespace "+request.getParameter("tablespaceID")+" drop datafile '"+request.getParameter("fileID")+"'");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				errors = e.getMessage();
				
			}
			 
			 new Tablespace().tablespaceGet( request,  request.getParameter("tablespaceID") );
				new Tablespace().tablespaceDatafilesGet(request, request.getParameter("tablespaceID"));
				
					

				this.getServletContext()
						.getRequestDispatcher("/WEB-INF/tablespacedisplay.jsp")
						.forward(request, response);
			 
			}
}
