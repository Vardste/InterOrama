package com.apps;


import com.db.DBSQLConnection;
import java.sql.*;
import org.zkoss.zul.*;

public class customer extends Window{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String SQL;
	
	public void saveitem(String cid, String cname, String caddress) throws Exception{
		SQL="INSERT INTO CUSTOMER VALUES (?,?,?)";
		Connection Conn = new DBSQLConnection().openConnection();
		PreparedStatement prep = Conn.prepareStatement(SQL);
		prep.setString(1,cid);
		prep.setString(2,cname);
		prep.setString(3,caddress);
		prep.executeUpdate();
		Conn.close();
	}
}
