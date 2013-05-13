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

import com.dbop.beans.User;

public class UserEditProcess extends HttpServlet {
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
    	
    	System.out.println(userAuthentication+"------------");
    	///////////////////////////////////
    	String html="";
		ResultSet rsTBS;
		try {
			DataService ds = new DataService();
			HttpSession httpSession = request.getSession();
			String myLogin = (String)httpSession.getAttribute("myLogin");
			String myPassword = (String)httpSession.getAttribute("myPassword");
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
		////////////////////////////////////////
    	User user = new User( username,  userProfile,  userAuthentication,
				password1,  password2,  defaulttablespace,
					temporarytablespace,  userlocked,  passwordexpire) ;
		
		String errors = user.userUpdate(request);
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
