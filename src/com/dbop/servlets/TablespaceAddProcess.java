package com.dbop.servlets;

import java.io.*;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;

import com.dbop.beans.*;
import com.dbop.db.DataService;

public class TablespaceAddProcess extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        
        String tablespaceName = request.getParameter("tablespaceName");
        String optionsExtentManagment = request.getParameter("optionsExtentManagment");
        String optionsTablespaceType = request.getParameter("optionsTablespaceType");
        String SetPermenantTablespaceAsDefaultTablespace = request.getParameter("SetPermenantTablespaceAsDefaultTablespace");
        String SetTemporaryTablespaceAsDeafaultTemporaryTablespace = request.getParameter("SetTemporaryTablespaceAsDeafaultTemporaryTablespace");
        String optionsTablespaceStatus = request.getParameter("optionsTablespaceStatus");
        String optionsStorage = request.getParameter("optionsStorage");

        Tablespace tbs = new Tablespace(tablespaceName, optionsExtentManagment, optionsTablespaceType, SetPermenantTablespaceAsDefaultTablespace, SetTemporaryTablespaceAsDeafaultTemporaryTablespace, optionsTablespaceStatus, optionsStorage, null);


        String smallFileNames[] = request.getParameterValues("smallFileName");
        String smallFileSizes[] = request.getParameterValues("smallFileSize");
        String smallFileSizeUnits[] = request.getParameterValues("smallFileSizeUnit");
        String reuses[] = request.getParameterValues("reuse");
        String autoextends[] = request.getParameterValues("autoextend");

        TBSFiles tbsFiles = new TBSFiles();
        tbsFiles.insertFiles(smallFileNames, smallFileSizes, smallFileSizeUnits, reuses, autoextends);


        out.println(tbs.toString());
        out.println("<br>");
        for (int i = 0; i < smallFileNames.length; i++) {
            out.println(tbsFiles.getTbsFiles()[i].toString() + "<br>");           
        }
        //out.println(tbsFiles.getCount()+"---");
        int nbOfFiles = smallFileNames.length;
        String query = "create ";
        
        String datafileOrTempfile = "";
        
        if (optionsTablespaceType.equals("temporary"))
        {
        	query = query + " temporary ";
        	datafileOrTempfile = " tempfile ";
        }
        else
        	datafileOrTempfile = " datafile ";
        	
        
        if (tbsFiles.getCount()==1 && tbs.getOptionsStorage().equals("UseBigFiles"))
        	query = query + " bigfile tablespace ";
        else if (tbsFiles.getCount()>=1 && tbs.getOptionsStorage().equals("UseSmallFiles"))
        	query = query + " tablespace " ;
        else if (tbsFiles.getCount()>=1 && tbs.getOptionsStorage().equals("UseBigFiles"))
        {
        	query = query + " bigfile tablespace ";
        	nbOfFiles = 1;
        }
        
        query = query + tablespaceName + datafileOrTempfile;
        
        for (int i = 0; i < nbOfFiles; i++) {
	        query = query + " 'D:\\Oracle\\oradata\\XE\\"+tbsFiles.getTbsFiles()[i].getSmallfileName() + ".dbf' " + 
	        				" size " +tbsFiles.getTbsFiles()[i].getSmallFileSize() + 
	        				tbsFiles.getTbsFiles()[i].getSmallfileSizeUnit();
	        if (tbsFiles.getTbsFiles()[i].getAutoextend().equals("on"))
	        	query = query + " autoextend on ";
	        else if (tbsFiles.getTbsFiles()[i].getAutoextend().equals("off"))
	        	query = query + " autoextend off ";
	        
	        if (tbsFiles.getTbsFiles()[i].getReuse().equals("reuse"))
	        	query = query + " reuse ";
	        
	        if (i==nbOfFiles-1)
	        	query = query + " ";
	        else
	        	query = query + ", ";
        
        }
        
        System.out.println("<br><br><br><br>"+query.toUpperCase());
        
        
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
        	new Tablespace().tablespaceGet( request,  tablespaceName );
    		new Tablespace().tablespaceDatafilesGet(request, tablespaceName);
	       	request.setAttribute( "success", "<div class=\"alert alert-warning\" ><button class=\"close\" data-dismiss=\"alert\" type=\"button\"></button>Tablespace added sucessfully</div>" );
	       	this.getServletContext().getRequestDispatcher( "/WEB-INF/tablespacedisplay.jsp" ).forward( request, response );
    		
    	}
    	else
    	{
    		request.setAttribute( "errors", "<div class=\"alert alert-warning\" ><button class=\"close\" data-dismiss=\"alert\" type=\"button\"></button>"+oracleErrors+"</div>" );
	       	this.getServletContext().getRequestDispatcher("/WEB-INF/tablespaceadd.jsp" ).forward( request, response );
    	}
        




    }
}