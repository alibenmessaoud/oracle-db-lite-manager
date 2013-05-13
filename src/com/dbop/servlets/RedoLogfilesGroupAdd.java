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

import com.dbop.db.DataService;

public class RedoLogfilesGroupAdd extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 
		this.getServletContext()
				.getRequestDispatcher("/WEB-INF/redologfilesgroupadd.jsp")
				.forward(request, response);

	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String file_name = request.getParameter("file_name");
		String file_size = request.getParameter("file_size");
		String file_size_unit = request.getParameter("file_size_unit");
		
		HttpSession httpSession = request.getSession();
		DataService ds = new DataService();
		String oracleErrors = "";
		//if (!file_name.isEmpty() && ! group.isEmpty())
		{
			try {
				ds.runQuery((String)httpSession.getAttribute("myLogin"),(String)httpSession.getAttribute("myPassword"),"ALTER DATABASE ADD LOGFILE '"+file_name+"' SIZE "+file_size+" "+file_size_unit);
				 
			}
			catch (SQLException e) {
				e.printStackTrace();
				oracleErrors = e.getMessage();
			}
			
			if (!oracleErrors.equals(""))
			{
				request.setAttribute( "success", "<div class=\"alert alert-success\" ><button class=\"close\" data-dismiss=\"alert\" type=\"button\"></button>Member added sucessfully</div>" );
		       	this.getServletContext().getRequestDispatcher( "/WEB-INF/redologfilesgroupadd.jsp" ).forward( request, response );
	    		
			}
			else
			{
				request.setAttribute( "errors", "<div class=\"alert alert-warning\" ><button class=\"close\" data-dismiss=\"alert\" type=\"button\"></button>"+oracleErrors+"</div>" );
				this.getServletContext()
				.getRequestDispatcher("/WEB-INF/redologfilesgroupadd.jsp")
				.forward(request, response);
			}
			
		}
//		else
//		{
//			request.setAttribute( "errors", "<div class=\"alert alert-warning\" ><button class=\"close\" data-dismiss=\"alert\" type=\"button\"></button>Empty fields!</div>" );
//			this.getServletContext()
//			.getRequestDispatcher("/WEB-INF/redologfilesmemberadd.jsp")
//			.forward(request, response);
//		}
			
		
		 
		

	}
}
