package com.dbop.servlets;

import com.dbop.beans.User;
import com.dbop.beans.Users;
import com.dbop.db.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserDelete extends HttpServlet {
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void doGet( HttpServletRequest request, HttpServletResponse
			response ) throws ServletException, IOException{
			 
		User user = new User( ) ;
		String userID = request.getParameter( "userID" );
		String errors = user.userDelete(userID, request);
		System.out.println(errors);
		
    	if (errors.isEmpty())
    	{
    		request.setAttribute( "success", "<div class=\"alert alert-success\" ><button class=\"close\" data-dismiss=\"alert\" type=\"button\"></button>"+"User "+userID+" deleted sucessfully"+"</div>" );
    	}
    	else
    	{
    		request.setAttribute( "errors", "<div class=\"alert alert-warning\" ><button class=\"close\" data-dismiss=\"alert\" type=\"button\"></button>"+errors+"</div>" );
    	}
    	
		Users users = new Users();
		
		try {
			request.setAttribute( "users", users.getUsers(request) );
			request.setAttribute( "count", users.count);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.getServletContext().getRequestDispatcher( "/WEB-INF/usersmanage.jsp").forward( request, response );
		
	}
}
