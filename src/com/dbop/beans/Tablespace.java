package com.dbop.beans;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.dbop.db.DataService;
import com.dbop.db.UsernameValidator;

public class Tablespace {
	public String tablespaceName;
	public String optionsExtentManagment; // doctionnary | local
	public String optionsTablespaceType; // permanent | temporary
	public String SetPermenantTablespaceAsDefaultTablespace;
	public String SetTemporaryTablespaceAsDeafaultTemporaryTablespace;
	public String optionsTablespaceStatus; // rw | ro | offline
	public String optionsStorage; // UseBigFiles | UseSmallFiles
	public TBSFiles files;
	

	public Tablespace(String tablespaceName, String optionsExtentManagment,
			String optionsTablespaceType,
			String SetPermenantTablespaceAsDefaultTablespace,
			String SetTemporaryTablespaceAsDeafaultTemporaryTablespace,
			String optionsTablespaceStatus, String optionsStorage,
			TBSFiles files) {
		super();
		this.tablespaceName = tablespaceName;
		this.optionsExtentManagment = optionsExtentManagment;
		this.optionsTablespaceType = optionsTablespaceType;
		this.SetPermenantTablespaceAsDefaultTablespace = SetPermenantTablespaceAsDefaultTablespace;
		this.SetTemporaryTablespaceAsDeafaultTemporaryTablespace = SetTemporaryTablespaceAsDeafaultTemporaryTablespace;
		this.optionsTablespaceStatus = optionsTablespaceStatus;
		this.optionsStorage = optionsStorage;
		this.files = files;
	}

	public Tablespace() {
		// TODO Auto-generated constructor stub
	}

	public String getTablespaceName() {
		return tablespaceName;
	}

	public String getOptionsExtentManagment() {
		return optionsExtentManagment;
	}

	public String getOptionsTablespaceType() {
		return optionsTablespaceType;
	}

	public String getSetPermenantTablespaceAsDefaultTablespace() {
		return SetPermenantTablespaceAsDefaultTablespace;
	}

	public String getSetTemporaryTablespaceAsDeafaultTemporaryTablespace() {
		return SetTemporaryTablespaceAsDeafaultTemporaryTablespace;
	}

	public String getOptionsTablespaceStatus() {
		return optionsTablespaceStatus;
	}

	public String getOptionsStorage() {
		return optionsStorage;
	}

	public TBSFiles getFiles() {
		return files;
	}

	public void setTablespaceName(String tablespaceName) {
		this.tablespaceName = tablespaceName;
	}

	public void setOptionsExtentManagment(String optionsExtentManagment) {
		this.optionsExtentManagment = optionsExtentManagment;
	}

	public void setOptionsTablespaceType(String optionsTablespaceType) {
		this.optionsTablespaceType = optionsTablespaceType;
	}

	public void setSetPermenantTablespaceAsDefaultTablespace(
			String setPermenantTablespaceAsDefaultTablespace) {
		SetPermenantTablespaceAsDefaultTablespace = setPermenantTablespaceAsDefaultTablespace;
	}

	public void setSetTemporaryTablespaceAsDeafaultTemporaryTablespace(
			String setTemporaryTablespaceAsDeafaultTemporaryTablespace) {
		SetTemporaryTablespaceAsDeafaultTemporaryTablespace = setTemporaryTablespaceAsDeafaultTemporaryTablespace;
	}

	public void setOptionsTablespaceStatus(String optionsTablespaceStatus) {
		this.optionsTablespaceStatus = optionsTablespaceStatus;
	}

	public void setOptionsStorage(String optionsStorage) {
		this.optionsStorage = optionsStorage;
	}

	public void setFiles(TBSFiles files) {
		this.files = files;
	}

	@Override
	public String toString() {
		return "Tablespace [tablespaceName=" + tablespaceName
				+ ", optionsExtentManagment=" + optionsExtentManagment
				+ ", optionsTablespaceType=" + optionsTablespaceType
				+ ", SetPermenantTablespaceAsDefaultTablespace="
				+ SetPermenantTablespaceAsDefaultTablespace
				+ ", SetTemporaryTablespaceAsDeafaultTemporaryTablespace="
				+ SetTemporaryTablespaceAsDeafaultTemporaryTablespace
				+ ", optionsTablespaceStatus=" + optionsTablespaceStatus
				+ ", optionsStorage=" + optionsStorage + " --files-- " + "]";
	}

	public String tablespaceAdd() {
		DataService ds = new DataService();
		UsernameValidator UV = new UsernameValidator();
		String userErrors = "";
		String oracleErrors = "";
		if (files.getTbsFilesList().size() == 0)
			System.out.println(" is empty");
		// if (!UV.validate(tablespaceName))
		// userErrors = "- Tablespace name is not valid<br>";
		// if (tablespaceName.length()<=10 && tablespaceName.length()>100)
		// userErrors = userErrors +
		// "- Tablespace name field shoud contain 10 characters at least and 100 in max.<br>";
		// try {
		// if
		// (ds.nbOfResultSet("select count (tablespace_name) from dba_tablespaces where tablespace_name like '"+tablespaceName+"'".toUpperCase(),
		// "username")==0)
		// userErrors = userErrors +
		// "- The Tablespace name you have chosen already exists. Please try another one.<br>";
		// } catch (SQLException e) {
		// e.printStackTrace();
		// oracleErrors = oracleErrors + "- " +e.getMessage()+"<br>";
		// }

		// for (int i = 0; i < files.getCount(); i++) {
		// if (!UV.validate(files.getTbsFiles()[i].getSmallfileName()))
		// userErrors =
		// "- The file :\""+files.getTbsFiles()[i].getSmallfileName()+"  name is not valid<br>";
		// if (files.getTbsFiles()[i].getSmallfileName().length()<=10 &&
		// files.getTbsFiles()[i].getSmallfileName().length()>20)
		// userErrors = userErrors +
		// "- The file :\""+files.getTbsFiles()[i].getSmallfileName()+" name field shoud contain 10 characters at least and 20 in max.<br>";
		// }

		return userErrors + oracleErrors;

	}
	
	
	public void tablespaceGet(HttpServletRequest request, String tablespaceName )
	{
		DataService ds = new DataService();
		HttpSession httpSession = request.getSession();
		String myLogin = (String)httpSession.getAttribute("myLogin");
		String myPassword = (String)httpSession.getAttribute("myPassword");
		String Data = "";
		
		ResultSet rsTBS;
		try {
			rsTBS = ds.getResultSet
					(
						myLogin, myPassword, "select TABLESPACE_NAME, STATUS, CONTENTS, LOGGING, EXTENT_MANAGEMENT, ALLOCATION_TYPE,  RETENTION, BIGFILE  from dba_tablespaces where tablespace_name like '"+tablespaceName+"'"	
					);
			while (rsTBS.next()) 
			{			
				
				Data = Data + "<tr>  	<td width=\"230\" align=\"right\" valign=\"top\">" +
							  "	<label   style=\"padding-right: 10px; padding-top: 4px;\">" +
							  "Tablespace name" +
							  "</label></td>" +
							  "<td width=\"200\" align=\"left\">" +
							  rsTBS.getString("tablespace_name") +
							  "</td> <td valign=\"top\"  width=\"528\" rowspan=\"9\" align=\"left\">" +
							  "<h2>Stats</h2>"+
							  tabespaceStat(tablespaceName, request)+							  
							  "</td>  </tr>";
				Data = Data + "<tr>  	<td width=\"230\" align=\"right\" valign=\"top\">" +
						  "	<label   style=\"padding-right: 10px; padding-top: 4px;\">" +
						  "Status" +
						  "</label></td>" +
						  "<td align=\"left\">" +
						  rsTBS.getString("STATUS") +
						  "</td> <td width=\"43\" align=\"left\">&nbsp;</td>  </tr>";
				Data = Data + "<tr>  	<td width=\"230\" align=\"right\" valign=\"top\">" +
						  "	<label   style=\"padding-right: 10px; padding-top: 4px;\">" +
						  "Contents" +
						  "</label></td>" +
						  "<td width=\"685\" align=\"left\">" +
						  rsTBS.getString("CONTENTS") +
						  "</td> <td width=\"43\" align=\"left\">&nbsp;</td>  </tr>";
				Data = Data + "<tr>  	<td width=\"230\" align=\"right\" valign=\"top\">" +
						  "	<label   style=\"padding-right: 10px; padding-top: 4px;\">" +
						  "Extent managment" +
						  "</label></td>" +
						  "<td width=\"685\" align=\"left\">" +
						  rsTBS.getString("EXTENT_MANAGEMENT") +
						  "</td> <td width=\"43\" align=\"left\">&nbsp;</td>  </tr>";
				Data = Data + "<tr>  	<td width=\"230\" align=\"right\" valign=\"top\">" +
						  "	<label   style=\"padding-right: 10px; padding-top: 4px;\">" +
						  "Allocation type" +
						  "</label></td>" +
						  "<td width=\"685\" align=\"left\">" +
						  rsTBS.getString("ALLOCATION_TYPE") +
						  "</td> <td width=\"43\" align=\"left\">&nbsp;</td>  </tr>";
				Data = Data + "<tr>  	<td width=\"230\" align=\"right\" valign=\"top\">" +
						  "	<label   style=\"padding-right: 10px; padding-top: 4px;\">" +
						  "Retention gaurantee" +
						  "</label></td>" +
						  "<td width=\"685\" align=\"left\">" +
						  rsTBS.getString("RETENTION") +
						  "</td> <td width=\"43\" align=\"left\">&nbsp;</td>  </tr>";
				Data = Data + "<tr>  	<td width=\"230\" align=\"right\" valign=\"top\">" +
						  "	<label   style=\"padding-right: 10px; padding-top: 4px;\">" +
						  "Use Bigfile" +
						  "</label></td>" +
						  "<td width=\"685\" align=\"left\">" +
						  rsTBS.getString("BIGFILE") +
						  "</td> <td width=\"43\" align=\"left\">&nbsp;</td>  </tr>";
				
				System.out.println(rsTBS.getString("RETENTION"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("tablespace", Data);
		request.setAttribute("tablespaceName", tablespaceName);
	}
	
	public void tablespaceDatafilesGet (HttpServletRequest request, String tablespaceName )
	{
		DataService ds = new DataService();
		HttpSession httpSession = request.getSession();
		String myLogin = (String)httpSession.getAttribute("myLogin");
		String myPassword = (String)httpSession.getAttribute("myPassword");
		String header = "";
		String data = "";
		String errors = "";
		int rowCount = 0;
		try {
			ResultSet rsQuery = ds.getResultSet(myLogin, myPassword, "select FILE_NAME, BYTES, STATUS, AUTOEXTENSIBLE, USER_BYTES , ONLINE_STATUS from dba_data_files where tablespace_name like '"+tablespaceName+"'");
			ResultSetMetaData rsQueryMd = rsQuery.getMetaData();
			int columnCount = rsQueryMd.getColumnCount();
			for (int i = 1; i < columnCount + 1; i++)
				header = header + "<th> " + rsQueryMd.getColumnName(i)
						+ "</th>";
				header = header + "<th> Action </th>";
			while (rsQuery.next()) {
				rowCount++;
				data = data + "<tr> ";
				for (int i = 1; i < columnCount + 1; i++)
					data = data + "<td> " + rsQuery.getString(i) + "</td>";
					data = data + "<td>  <a class=\"btn btn-mini\" href=\"tablespaceDeleteFile?tablespaceID="+tablespaceName+"&fileID="+rsQuery.getString("FILE_NAME")+"\"><i class=\"icon-remove\"> </i> Delete</a> <a class=\"btn btn-mini\" href=\"tablespaceEditFile?tablespaceID="+tablespaceName+"&fileID="+rsQuery.getString("FILE_NAME")+"\"><i class=\"icon-pencil\"> </i> Edit</a></td></tr> ";
	 
			}
		} catch (SQLException e) {	}
		request.setAttribute("TableHeader", header);
		request.setAttribute("TableData", data);
		
	}
	
	public String tabespaceStat(String tablespaceName, HttpServletRequest request)
	{
		DataService ds = new DataService();
		HttpSession httpSession = request.getSession();
		String myLogin = (String)httpSession.getAttribute("myLogin");
		String myPassword = (String)httpSession.getAttribute("myPassword");
		ResultSet rsTBS;
		String stats = "";
		int allocated_bytes = 0;
		int free_bytes = 0;
		try {
			rsTBS = ds.getResultSet
				(	myLogin, myPassword, 
					"SELECT   a.bytes allocated_bytes,   b.free_bytes FROM    dba_data_files a,    " +
					"(SELECT file_id, SUM(bytes) free_bytes     FROM dba_free_space b GROUP BY file_id) b " +
					"WHERE    a.file_id=b.file_id  and tablespace_name ='"+tablespaceName+"'" 
				);
			
			while (rsTBS.next()) 
			{			
				allocated_bytes += Integer.parseInt(rsTBS.getString("allocated_bytes"));
				free_bytes += Integer.parseInt(rsTBS.getString("free_bytes"));			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		stats =  
				"<b>Allocated Bytes : </b>" + allocated_bytes/1024/1024 + " MB"
				+"<br><b>Free Bytes : </b>" + free_bytes/1024/1024 + " MB"
				+"<br><b>Used Bytes : </b>" + (float)(((allocated_bytes - free_bytes))/1024/1024) + " MB"
				+"<br><b>Used Bytes in percent : </b>" + ((float)(allocated_bytes - free_bytes) / allocated_bytes *100)+"%"
				+"<div class=\"progress\" style=\"margin-top: 5px;\">"				
				+ "<div class=\"bar bar-warning\" style=\"width: "
				+ ((float)(allocated_bytes - free_bytes) / allocated_bytes * 100)
				+ "%;\"></div>" + "</div>";
		
		return stats;
	}

}
