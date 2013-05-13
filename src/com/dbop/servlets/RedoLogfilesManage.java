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

public class RedoLogfilesManage extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String header = "";
		String data = "";
		String errors = "";
		HttpSession httpSession = request.getSession();
		DataService ds = new DataService();
		try {
			ResultSet rsQuery = ds.getResultSet((String)httpSession.getAttribute("myLogin"),(String)httpSession.getAttribute("myPassword"),"select *  from V$LOG");
			ResultSetMetaData rsQueryMd = rsQuery.getMetaData();
			int columnCount = rsQueryMd.getColumnCount();
			for (int i = 1; i < columnCount + 1; i++)
				header = header + "<th> " + rsQueryMd.getColumnName(i)
						+ "</th>";
			while (rsQuery.next()) {
				data = data + "<tr> ";
				for (int i = 1; i < columnCount + 1; i++)
					data = data + "<td> " + rsQuery.getString(i) + "</td>";
				data = data + "</tr> ";
			}
		} catch (SQLException e) {
			errors = e.getMessage();
		}
		System.out.println(data);
		request.setAttribute( "TableHeader", header);
		request.setAttribute( "TableData", data);
		this.getServletContext()
				.getRequestDispatcher("/WEB-INF/redologfilesmanage.jsp")
				.forward(request, response);

	}
}
