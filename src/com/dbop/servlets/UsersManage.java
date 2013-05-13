package com.dbop.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dbop.beans.Users;

public class UsersManage extends HttpServlet {
	public void doGet( HttpServletRequest request, HttpServletResponse
		response ) throws ServletException, IOException{
		
		Users users = new Users();
		
		try {
			request.setAttribute( "users", users.getUsers(request) );
			request.setAttribute( "count", users.getCount());
			System.out.println(users.count);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.getServletContext().getRequestDispatcher( "/WEB-INF/usersmanage.jsp" ).forward( request, response );
		}
}
