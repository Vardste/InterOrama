package com.db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import org.zkoss.zul.Messagebox;

public class DBSQLConnection {


	public Connection openConnection() throws SQLException {
		String url = "jdbc:sqlserver://localhost:1433;databaseName=PUBS";

			Properties info = new Properties();
			info.put("user", "anothertester");
			info.put("password", "1234567890");
			Connection Conn = DriverManager.getConnection(url);
		      if (Conn != null) {
		          System.out.println("Successfully connected to MySQL database test");
		        }

	          System.out.println("Problem with" );


//			Context initContext = new InitialContext();
//			Context envContext = (Context)initContext.lookup("java:/comp/env");
//			DataSource ds = (DataSource)envContext.lookup("jdbc/HelloWorld!");
//			Conn = ds.getConnection();
		return Conn;
	}
}
