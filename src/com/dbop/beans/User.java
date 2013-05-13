package com.dbop.beans;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.dbop.db.DataService;
import com.dbop.db.UsernameValidator;

public class User {
	private String username;
	private String profile;
	private String authentication;
	private String password1;
	private String password2;
	private String defaultTablespace;
	private String temporaryTablespace;
	private String status;
	private String expire;

	public User(String username, String profile, String authentication,
			String password1, String password2, String defaultTableSpace,
			String temporaryTableSpace, String status, String expire) {
		super();
		this.username = username;
		this.profile = profile;
		this.authentication = authentication;
		this.password1 = password1;
		this.password2 = password2;
		this.defaultTablespace = defaultTableSpace;
		this.temporaryTablespace = temporaryTableSpace;
		this.status = status;
		this.expire = expire;
	}

	public User() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getAuthentication() {
		return authentication;
	}

	public void setAuthentication(String authentication) {
		this.authentication = authentication;
	}

	public String getPassword1() {
		return password1;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public String getDefaultTableSpace() {
		return defaultTablespace;
	}

	public void setDefaultTableSpace(String defaultTableSpace) {
		this.defaultTablespace = defaultTableSpace;
	}

	public String getTemporaryTableSpace() {
		return temporaryTablespace;
	}

	public void setTemporaryTableSpace(String temporaryTableSpace) {
		this.temporaryTablespace = temporaryTableSpace;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getExpire() {
		return expire;
	}

	public void setExpire(String expire) {
		this.expire = expire;
	}

	public String userAdd(HttpServletRequest request) {
		DataService ds = new DataService();
		HttpSession httpSession = request.getSession();
		String myLogin = (String)httpSession.getAttribute("myLogin");
		String myPassword = (String)httpSession.getAttribute("myPassword");
		UsernameValidator UV = new UsernameValidator();
		String userErrors = "";
		String oracleErrors = "";

		if (!UV.validate(username))
			userErrors = "- Username is not valid<br>";
		if (username.length() <= 6 && username.length() > 15)
			userErrors = userErrors
					+ "- Username fields shoud contain 3 characters at least and 15 in max.<br>";
		try {
			if (ds.nbOfResultSet(myLogin, myPassword,
					"select count ( username) from dba_users where username like '"
							+ username + "'".toUpperCase(), "username") == 0)
				userErrors = userErrors
						+ "- The username you have chosen already exists. Please try another one.<br>";
		} catch (SQLException e) {
			e.printStackTrace();
			// oracleErrors = oracleErrors + "- " +e.getMessage()+"<br>";
		}

		if (password1.length() <= 6 && username.length() > 20)
			userErrors = userErrors
					+ "- Password fields shoud contain 6 characters at least and 20 in max.<br>";
		if (password1.length() != password2.length())
			userErrors = userErrors
					+ "- Password does not match the confirm password.<br>";
		try {
			if (ds.nbOfResultSet(myLogin, myPassword,
					"select count (tablespace_name) as defaulttablespace from dba_tablespaces where tablespace_name like '"
							+ defaultTablespace + "'", "defaulttablespace") == 0)
				userErrors = userErrors
						+ "- The given Default Tablespace name does not exist.<br>";
		} catch (SQLException e) {
			e.printStackTrace();
			// oracleErrors = oracleErrors + "- " +e.getMessage()+"<br>";
		}
		if (!UV.validate(defaultTablespace))
			userErrors = userErrors
					+ "- Default Tablespace name is not valid<br>";
		try {
			if (ds.nbOfResultSet(myLogin, myPassword,
					"select count (tablespace_name) as defaulttablespace from dba_tablespaces where tablespace_name like '"
							+ temporaryTablespace + "'", "temporarytablespace") == 0)
				userErrors = userErrors
						+ "- The given Temporary Tablespace name does not exist.<br>";
		} catch (SQLException e) {
			e.printStackTrace();
			// oracleErrors = oracleErrors + "- " +e.getMessage()+"<br>";
		}
		if (!UV.validate(temporaryTablespace))
			userErrors = userErrors
					+ "- Temporary Tablespace name is not valid<br>";

		if ((userErrors + oracleErrors).isEmpty()) {
			String query = "";
			query = " create user " + username;
			if (authentication.equals("default"))
				query = query + " identified by " + password1;
			else if (authentication.equals("externally"))
				query = query + " identified identified externally ";
			else if (authentication.equals("globally"))
				query = query + " identified globally as " + username;

			query = query + " profile " + profile;
			query = query + " default   tablespace " + defaultTablespace;
			query = query + " temporary    tablespace " + temporaryTablespace;
			if (status.equals("yes"))
				query = query + " account lock ";
			if (status.equals("no"))
				query = query + " account unlock ";
			if (expire.equals("yes"))
				query = query + " password expire ";
			System.out.println(query);

			try {
				int b = ds.runQuery(myLogin, myPassword,query);
				System.out.println(b);
			} catch (SQLException e) {
				e.printStackTrace();
				oracleErrors = "- " + e.getMessage() + "<br>";
				userErrors = "";
				System.err.println("SQLState: "
						+ ((SQLException) e).getSQLState());
				System.err.println("Error Code: "
						+ ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
			}

		}

		return userErrors + oracleErrors;
	}

	public User getUser(String userID, HttpServletRequest request) throws SQLException {
		DataService ds = new DataService();
		HttpSession httpSession = request.getSession();
		String myLogin = (String)httpSession.getAttribute("myLogin");
		String myPassword = (String)httpSession.getAttribute("myPassword");
		ResultSet rs = ds
				.getResultSet(myLogin, myPassword,"select * from dba_users where USERNAME like '"
						+ userID + "'".toUpperCase());

		while (rs.next()) {
			username = rs.getString("USERNAME");
			profile = rs.getString("PROFILE");
			authentication = rs.getString("PASSWORD");
			;
			password1 = rs.getString("PASSWORD");
			defaultTablespace = rs.getString("DEFAULT_TABLESPACE");
			temporaryTablespace = rs.getString("TEMPORARY_TABLESPACE");
			status = rs.getString("ACCOUNT_STATUS");
			expire = rs.getString("EXPIRY_DATE");

		}

		return this;
	}

	public String userUpdate(HttpServletRequest request) {
		DataService ds = new DataService();
		HttpSession httpSession = request.getSession();
		String myLogin = (String)httpSession.getAttribute("myLogin");
		String myPassword = (String)httpSession.getAttribute("myPassword");
		UsernameValidator UV = new UsernameValidator();
		String userErrors = "";
		String oracleErrors = "";

		if (!UV.validate(username))
			userErrors = "- Username is not valid<br>";
		if (username.length() <= 6 && username.length() > 15)
			userErrors = userErrors
					+ "- Username fields shoud contain 3 characters at least and 15 in max.<br>";
		try {
			if (ds.nbOfResultSet(myLogin, myPassword,
					"select count ( username) from dba_users where username like '"
							+ username + "'".toUpperCase(), "username") == 0)
				userErrors = userErrors
						+ "- The username you have chosen already exists. Please try another one.<br>";
		} catch (SQLException e) {
			e.printStackTrace();
			// oracleErrors = oracleErrors + "- " +e.getMessage()+"<br>";
		}

		if (password1.length() <= 6 && username.length() > 20)
			userErrors = userErrors
					+ "- Password fields shoud contain 6 characters at least and 20 in max.<br>";
		if (password1.length() != password2.length())
			userErrors = userErrors
					+ "- Password does not match the confirm password.<br>";
		try {
			if (ds.nbOfResultSet(myLogin, myPassword,
					"select count (tablespace_name) as defaulttablespace from dba_tablespaces where tablespace_name like '"
							+ defaultTablespace + "'", "defaulttablespace") == 0)
				userErrors = userErrors
						+ "- The given Default Tablespace name does not exist.<br>";
		} catch (SQLException e) {
			e.printStackTrace();
			// oracleErrors = oracleErrors + "- " +e.getMessage()+"<br>";
		}
		if (!UV.validate(defaultTablespace))
			userErrors = userErrors
					+ "- Default Tablespace name is not valid<br>";
		try {
			if (ds.nbOfResultSet(myLogin, myPassword,
					"select count (tablespace_name) as defaulttablespace from dba_tablespaces where tablespace_name like '"
							+ temporaryTablespace + "'", "temporarytablespace") == 0)
				userErrors = userErrors
						+ "- The given Temporary Tablespace name does not exist.<br>";
		} catch (SQLException e) {
			e.printStackTrace();
			// oracleErrors = oracleErrors + "- " +e.getMessage()+"<br>";
		}
		if (!UV.validate(temporaryTablespace))
			userErrors = userErrors
					+ "- Temporary Tablespace name is not valid<br>";

		if ((userErrors + oracleErrors).isEmpty()) {
			String query = "";
			query = " alter user " + username;
			if (authentication.equals("default"))
				query = query + " identified by " + password1;
			else if (authentication.equals("externally"))
				query = query + " identified  externally ";
			else if (authentication.equals("globally"))
				query = query + " identified globally as " + username;

			query = query + " profile " + profile;
			query = query + " default   tablespace " + defaultTablespace;
			query = query + " temporary    tablespace " + temporaryTablespace;
			if (status.equals("yes"))
				query = query + " account lock ";
			if (status.equals("no"))
				query = query + " account unlock ";
			if (expire.equals("yes"))
				query = query + " password expire ";

			System.out.println(query);

			try {
				int b = ds.runQuery(myLogin, myPassword,query);
				System.out.println(b);
			} catch (SQLException e) {
				e.printStackTrace();
				oracleErrors = "- " + e.getMessage() + "<br>";
				userErrors = "";
				System.err.println("SQLState: "
						+ ((SQLException) e).getSQLState());
				System.err.println("Error Code: "
						+ ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
			}

		}

		return userErrors + oracleErrors;
	}

	public String userDelete(String username, HttpServletRequest request) {
		String oracleErrors = "";
		try {
			DataService ds = new DataService();
			HttpSession httpSession = request.getSession();
			String myLogin = (String)httpSession.getAttribute("myLogin");
			String myPassword = (String)httpSession.getAttribute("myPassword");
			ds.runQuery(myLogin, myPassword,("drop user " + username).toUpperCase());
		} catch (SQLException e) {
			e.printStackTrace();
			oracleErrors = "- " + e.getMessage() + "<br>";
			System.err.println("SQLState: " + ((SQLException) e).getSQLState());
			System.err.println("Error Code: "
					+ ((SQLException) e).getErrorCode());
			System.err.println("Message: " + e.getMessage());
		}
		return oracleErrors;
	}

}
