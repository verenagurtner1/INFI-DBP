package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManager {
	
	// JDBC driver name and database URL 
		static final String DB_URL = "jdbc:mysql://localhost:3306/fahrradverleih?user=root&password=&useSSL=false";

		static Connection conn = null;
		static Statement stmt = null;
		static PreparedStatement pstmt = null;
		static ResultSet rs = null;
		
		public static void main(String[] args) {
			
		}
		
		
		public DBManager() throws InstantiationException, IllegalAccessException{
			super();
			// DB Driver init
			try {
				Class.forName("com.mysql.jdbc.Driver");

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}

		public static Connection getConnection() throws SQLException
		{
			Connection conn = null;
			//neuen Connection holen
			try {
				conn=DriverManager.getConnection(DB_URL);


			} catch (SQLException e) {
				//e.printStackTrace();
				System.out.println("SQLException: " + e.getMessage());
				System.out.println("SQLState: " + e.getSQLState());
				System.out.println("VendorError: " + e.getErrorCode());
			}
			return conn;
		}

		public static void releaseConnection(Connection conn)
		{
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println("Fehler beim schlieﬂen der Verbindung:");
				System.out.println("Meldung: "+e.getMessage());
				e.printStackTrace();
			}
		}


		public static void closeStatement(Statement stmt) {
			try {
				stmt.close();
			} catch (SQLException e) {
				System.out.println("Fehler beim schlieﬂen der Verbindung:");
				System.out.println("Meldung: "+e.getMessage());
				e.printStackTrace();
			}
			
		}
		
		

}
