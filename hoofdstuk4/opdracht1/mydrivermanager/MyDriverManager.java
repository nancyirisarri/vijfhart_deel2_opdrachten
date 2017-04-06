package mydrivermanager;

import java.sql.*;

public class MyDriverManager {

	public Connection connect() {
		Connection con = null;
		String url = "jdbc:mysql://localhost:3306/javacursus";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			con = DriverManager.getConnection(url, "cursist",
			  "vh_cursus");
			  
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return con;
	}
}