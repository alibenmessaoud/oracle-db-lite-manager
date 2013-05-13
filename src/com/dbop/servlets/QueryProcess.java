package com.dbop.servlets;

import com.dbop.db.*;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class QueryProcess extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DataService ds = new DataService();
		HttpSession httpSession = request.getSession();
		String myLogin = (String)httpSession.getAttribute("myLogin");
		String myPassword = (String)httpSession.getAttribute("myPassword");
		System.out.println(myLogin+myPassword);
		String query = request.getParameter("query");
		String header = "";
		String data = "";
		String errors = "";
		int rowCount = 0;
		if (query.toLowerCase().startsWith("select")) {
			try {
				ResultSet rsQuery = ds.getResultSet(myLogin, myPassword, query);
				ResultSetMetaData rsQueryMd = rsQuery.getMetaData();
				int columnCount = rsQueryMd.getColumnCount();
				for (int i = 1; i < columnCount + 1; i++)
					header = header + "<th> " + rsQueryMd.getColumnName(i)
							+ "</th>";
				while (rsQuery.next()) {
					rowCount++;
					data = data + "<tr> ";
					for (int i = 1; i < columnCount + 1; i++)
						data = data + "<td> " + rsQuery.getString(i) + "</td>";
					data = data + "</tr> ";
				}
			} catch (SQLException e) {
				errors = e.getMessage();
			}
		} else {
			try {
				ds.runQuery(myLogin, myPassword,query);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		System.out.println(header);
		System.out.println(errors);
		if (errors.isEmpty()) {
			request.setAttribute(
					"success",
					"<div class=\"alert alert-success\" ><button class=\"close\" data-dismiss=\"alert\" type=\"button\"></button>Query executed successfully : returned "
							+ rowCount
							+ " results"
							+ " deleted sucessfully"
							+ "</div>");
			request.setAttribute("TableHeader", header);
			request.setAttribute("TableData", data);
			request.setAttribute("query", query);
			this.getServletContext()
					.getRequestDispatcher("/WEB-INF/querymanage.jsp")
					.forward(request, response);

		} else {
			request.setAttribute("query", query);
			request.setAttribute(
					"errors",
					"<div class=\"alert alert-warning\" ><button class=\"close\" data-dismiss=\"alert\" type=\"button\"></button>"
							+ errors + "</div>");
			this.getServletContext()
					.getRequestDispatcher("/WEB-INF/querymanage.jsp")
					.forward(request, response);
		}

	}
}
