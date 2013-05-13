package com.dbop.servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dbop.beans.*;
import com.dbop.db.DataService;

public class TablespacesManage extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DataService ds = new DataService();
		HttpSession httpSession = request.getSession();
		String myLogin = (String)httpSession.getAttribute("myLogin");
		String myPassword = (String)httpSession.getAttribute("myPassword");
		
		String Data = "";
		ResultSet rsTBS;
		try {
			rsTBS = ds
					.getResultSet(myLogin, myPassword, "	SELECT    a.tablespace_name, t.STATUS, t.CONTENTS, t.RETENTION ,  "
							+ "							a.file_name,    a.bytes allocated_bytes,    b.free_bytes "
							+ "							FROM dba_tablespaces t,   dba_data_files a,    ("
							+ "								SELECT file_id, SUM(bytes"
							+ "									) free_bytes    "
							+ "								 FROM dba_free_space b GROUP BY file_id) b "
							+ "							WHERE a.tablespace_name = t.tablespace_name "
							+ "							and  a.file_id=b.file_id " 
							+ " 						and  t.contents!='UNDO' "+
														"ORDER BY    a.tablespace_name");
			String LastTBS = "";
			String c1 = null, c2, c3 = null;
			while (rsTBS.next()) {
				c1 = "<h3>" + rsTBS.getString("tablespace_name")
						+ "</h3> <b>CONTENTS :</b> "
						+ rsTBS.getString("CONTENTS")
						+ "<br> <b>RETENTION : </b>"
						+ rsTBS.getString("RETENTION");
				c2 = rsTBS.getString("file_name")
						+ "<br>Allocated size (MB): "
						+ rsTBS.getString("allocated_bytes")
						+ "<br>Space used (MB): "
						+ (Integer.parseInt(rsTBS.getString("allocated_bytes")) - Integer
								.parseInt(rsTBS.getString("free_bytes")))
						+ "<br>"
						+

						"<div class=\"progress\" style=\"margin-top: 5px;\">"
						+ "<div class=\"bar bar-warning\" style=\"width: "
						+ ((Integer
								.parseInt(rsTBS.getString("allocated_bytes")) - Integer
								.parseInt(rsTBS.getString("free_bytes")))
								/ Float.parseFloat(rsTBS
										.getString("allocated_bytes")) * 100)
						+ "%;\"></div>" + "</div>";
				c3 = rsTBS.getString("STATUS");

				Data = Data + "<tr>";
				Data = Data + "<td>" + c1 + "</td>";
				Data = Data + "<td>" + c2 + "</td>";
				Data = Data + "<td>" + c3 + "</td>";
				Data = Data
						+ "<td><a href=\"tablespaceView?tablespaceID="+ rsTBS.getString("tablespace_name")+"\" class=\"btn btn-mini\"><i class=\"icon-book\"> </i> View</a> <a href=\"tablespaceEdit?tablespaceID="+ rsTBS.getString("tablespace_name")+"\" class=\"btn btn-mini\"><i class=\" icon-pencil icon-small\"> </i> Edit</a> <a href=\"tablespaceDelete?tablespaceID="+ rsTBS.getString("tablespace_name")+"\" class=\"btn btn-mini\"><i class=\"icon-remove\"> </i> Delete</a></td>";
				Data = Data + "</tr>";

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		request.setAttribute("tablespaces", Data);

		this.getServletContext()
				.getRequestDispatcher("/WEB-INF/tablespacesmanage.jsp")
				.forward(request, response);
	}
}
