package com.dbop.servlets;
import com.dbop.db.*;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dbop.beans.User;

public class UserAddProcess extends HttpServlet {
    @SuppressWarnings("static-access")
	public void doPost( HttpServletRequest request,
	HttpServletResponse response ) throws ServletException, IOException
	{
        
    	String username = request.getParameter( "username" );
    	String userProfile = request.getParameter( "userProfile" );
    	String userAuthentication = request.getParameter( "userAuthentication" );
    	String password1 = request.getParameter( "password1" );
    	String password2 = request.getParameter( "password2" );
    	String defaulttablespace = request.getParameter( "defaulttablespace" );
    	String temporarytablespace = request.getParameter( "temporarytablespace" );
    	String userlocked = request.getParameter( "userlocked" );
    	String passwordexpire = request.getParameter( "passwordexpire" );
    	    	
    	User user = new User( username,  userProfile,  userAuthentication,
				password1,  password2,  defaulttablespace,
					temporarytablespace,  userlocked,  passwordexpire) ;
		
		String errors = user.userAdd(request);
		System.out.println(errors);
    	if (errors.isEmpty())
    	{
	       	request.setAttribute( "user", user );
	       	request.setAttribute( "success", "User added successfully" );
	       	this.getServletContext().getRequestDispatcher( "/WEB-INF/userdisplay.jsp" ).forward( request, response );
    		
    	}
    	else
    	{
    		request.setAttribute( "errors", "<div class=\"alert alert-warning\" ><button class=\"close\" data-dismiss=\"alert\" type=\"button\"></button>"+errors+"</div>" );
	       	this.getServletContext().getRequestDispatcher("/WEB-INF/useradd.jsp" ).forward( request, response );
    	}
    		
    	
    	
    	
    	
}
}
