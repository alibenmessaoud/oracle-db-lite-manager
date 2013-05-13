package com.dbop.servlets;

import java.io.*;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;

import com.dbop.beans.*;
import com.dbop.db.DataService;

public class TablespaceEditFileProcess extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        String tablespace_name = request.getParameter("tablespace_name");
        String smallFileName = request.getParameter("smallFileName");
        String smallFileSize = request.getParameter("smallFileSize");
        String smallFileSizeUnit = request.getParameter("smallFileSizeUnit");
        System.out.println(smallFileSizeUnit);
        
        String reuse = request.getParameter("reuse");
        String autoextend = request.getParameter("autoextend");
        
        
        String smallFileSizeNEXT = request.getParameter("smallFileSizeNEXT");
        String smallFileSizeNEXTUnit = request.getParameter("smallFileSizeNEXTUnit");
        
        String smallFileSizeMAXSIZE = request.getParameter("smallFileSizeMAXSIZE");
        String smallFileSizeMAXSIZEUnit = request.getParameter("smallFileSizeMAXSIZEUnit");
        
        

        String query = 	"ALTER TABLESPACE "+ tablespace_name +
        				" ADD DATAFILE 'D:\\Oracle\\oradata\\XE\\"+smallFileName+".dbf' " +
        				" SIZE "+smallFileSize+" "+smallFileSize ;
        
        if (autoextend.equals("on"))
        	query += " AUTOEXTEND ON ";
        else
        	query += " AUTOEXTEND OFF ";
        
        if (!smallFileSizeNEXT.equals(""))
        	query += " Next " + smallFileSizeNEXT + " " + smallFileSizeNEXTUnit;
        
//        if (!smallFileSizeNEXT.equals(""))
//        	query += " MAXSIZE " + smallFileSizeNEXT + " " + smallFileSizeNEXTUnit;
//         
        
        //System.out.println("<br><br><br><br>"+query.toUpperCase());
        
        
        DataService ds = new DataService();
        HttpSession httpSession = request.getSession();
		String myLogin = (String)httpSession.getAttribute("myLogin");
		String myPassword = (String)httpSession.getAttribute("myPassword");
        String oracleErrors = "";
        try {
			ds.runQuery(myLogin, myPassword, query.toUpperCase());
		} catch (SQLException e) {
			e.printStackTrace();
			oracleErrors = "- " + e.getMessage() + "<br>";
		}
        	
        if (oracleErrors.isEmpty())
    	{
        	new Tablespace().tablespaceGet( request,  tablespace_name );
    		new Tablespace().tablespaceDatafilesGet(request, tablespace_name);
	       	request.setAttribute( "success", "<div class=\"alert alert-warning\" ><button class=\"close\" data-dismiss=\"alert\" type=\"button\"></button>Tablespace datafiles edited sucessfully</div>" );
	       	this.getServletContext().getRequestDispatcher( "/WEB-INF/tablespacedisplay.jsp" ).forward( request, response );
    		
    	}
    	else
    	{
    		request.setAttribute( "errors", "<div class=\"alert alert-warning\" ><button class=\"close\" data-dismiss=\"alert\" type=\"button\"></button>"+oracleErrors+"</div>" );
	       	this.getServletContext().getRequestDispatcher("/WEB-INF/tablespaceeditfile.jsp" ).forward( request, response );
    	}
        




    }
}