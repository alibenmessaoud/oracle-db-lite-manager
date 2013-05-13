package com.dbop.servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dbop.beans.Tablespace;
import com.dbop.db.DataService;

public class UndoTablespaceAdd extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.getServletContext()
				.getRequestDispatcher("/WEB-INF/undotablespaceadd.jsp")
				.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String sbfile = request.getParameter("sbfile");
		String tablespace_name = request.getParameter("tablespace_name");
		String datafile_name = request.getParameter("datafile_name");
		String datafile_size = request.getParameter("datafile_size");
		String datafile_size_int = request.getParameter("datafile_size_int");
		String datafile_autoextend = request
				.getParameter("datafile_autoextend");
		String datafile_next = request.getParameter("datafile_next");
		String datafile_next_int = request.getParameter("datafile_next_int");
		String datafile_maxsize = request.getParameter("datafile_maxsize");
		String datafile_maxsize_int = request
				.getParameter("datafile_maxsize_int");
		String extent = request.getParameter("extent");
		String extent_allocate = request.getParameter("extent_allocate");
		String UNIFORM = request.getParameter("UNIFORM");
		String uniform_size = request.getParameter("uniform_size");
		String uniform_int = request.getParameter("uniform_int");
		String segment = request.getParameter("segment");
		String segment_int = request.getParameter("segment_int");
		String accesibilite = request.getParameter("accesibilite");

		if (datafile_autoextend != "")
			datafile_autoextend = "AUTOEXTEND " + datafile_autoextend;
		if (datafile_next != "")
			datafile_next = "NEXT " + datafile_next + " " + datafile_next_int;

		if (datafile_maxsize != "")
			datafile_maxsize = "MAXSIZE " + datafile_maxsize + " "
					+ datafile_maxsize_int;

		if (extent == null)
			extent = "";
		else
			extent = extent + " " + extent_allocate + " " + uniform_size + " "
					+ uniform_int;

		if (segment == null)
			segment = "";
		else
			segment = segment + " " + segment_int;
		String retention = "";
		String log = "";
		
		String query = "CREATE UNDO TABLESPACE " + sbfile + " "
				+ tablespace_name + " DATAFILE '" + datafile_name + "' SIZE "
				+ datafile_size + " " + datafile_size_int + " "
				+ datafile_autoextend + " " + datafile_next + " "
				+ datafile_maxsize + " " + retention + " " + extent + " "
				+ segment + " " + log + " " + accesibilite;

		DataService ds = new DataService();
		HttpSession httpSession = request.getSession();
		String myLogin = (String) httpSession.getAttribute("myLogin");
		String myPassword = (String) httpSession.getAttribute("myPassword");
		DataService dbc = new DataService();
		String errors = "";
		try {
			dbc.runQuery(myLogin, myPassword, query);
		} catch (SQLException e) {
			e.printStackTrace();
			errors = e.getMessage();
		}

		if (errors.isEmpty()) {
			new Tablespace().tablespaceGet(request, tablespace_name);
			new Tablespace().tablespaceDatafilesGet(request, tablespace_name);
			request.setAttribute(
					"success",
					"<div class=\"alert alert-warning\" ><button class=\"close\" data-dismiss=\"alert\" type=\"button\"></button>Tablespace added sucessfully</div>");
			this.getServletContext()
					.getRequestDispatcher("/WEB-INF/tablespacedisplay.jsp")
					.forward(request, response);

		} else {
			request.setAttribute(
					"errors",
					"<div class=\"alert alert-warning\" ><button class=\"close\" data-dismiss=\"alert\" type=\"button\"></button>"
							+ errors + "</div>");
			this.getServletContext()
					.getRequestDispatcher("/WEB-INF/undotablespaceadd.jsp")
					.forward(request, response);
		}

	}
}
