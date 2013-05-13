package com.dbop.servlets;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.dbop.beans.User;

public class UserEdit extends HttpServlet {
public void doGet( HttpServletRequest request, HttpServletResponse
		response ) throws ServletException, IOException{
		
		
	User user = new User() ;
	String userID = request.getParameter("userID");
	System.out.println(userID+"----");
	try {
		user.getUser(userID, request);
	} catch (SQLException e) {
		e.printStackTrace();
	}
	request.setAttribute( "user", user );   	
	request.setAttribute( "success", "" );
	
		this.getServletContext().getRequestDispatcher( "/WEB-INF/useredit.jsp").forward( request, response );
	}
}
