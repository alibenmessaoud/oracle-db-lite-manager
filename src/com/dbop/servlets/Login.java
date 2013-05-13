package com.dbop.servlets;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dbop.beans.Session;
import com.dbop.db.DataService;

public class Login extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
				.forward(request, response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		DataService ds = new DataService();
		
		try {
			if (ds.loadDriver(request.getParameter("login"), request.getParameter("password")) != null)
			{
				HttpSession httpSession = request.getSession();
				httpSession.setAttribute( "loggedInUser", request.getParameter("login") );
				httpSession.setAttribute("myLogin", request.getParameter("login"));
				httpSession.setAttribute("myPassword", request.getParameter("password"));
				System.out.println(request.getParameter("login")+ request.getParameter("password"));
				this.getServletContext().getRequestDispatcher("/WEB-INF/home.jsp")
				.forward(request, response);
			}
			else
			{
				request.setAttribute(
						"error",
						"<tr class=\"error\"> <td align=\"center\">Invalid username/password, logon denied</td></tr>");
				request.setAttribute("inputError", "inputError");
				this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
						.forward(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 

	}
}
