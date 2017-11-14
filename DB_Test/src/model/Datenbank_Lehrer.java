package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Datenbank_Lehrer {

	// JDBC driver name and database URL
		static final String JDBC_DRIVER = "org.postgresql.Driver";  
		static final String DB_URL = "jdbc:postgresql://localhost/DBP";

		//  Database credentials
		static final String USER = "postgres";
		static final String PASS = "password";

		public static void main(String[] args) {
			Connection conn = null;
			Statement stmt = null;
			try{
				//STEP 2: Register JDBC driver
				Class.forName(JDBC_DRIVER);

				//STEP 3: Open a connection
				System.out.println("Connecting to database...");
				conn = DriverManager.getConnection(DB_URL,USER,PASS);

				//STEP 4: Execute a query
				System.out.println("Creating statement...");
				stmt = conn.createStatement();
				String sql;
				//sql = "SELECT id, first, last, age FROM Employee";
					      sql = "CREATE TABLE IF NOT EXISTS Employee ("
					    		+ "id SERIAL primary key,"
					      		+ "first character varying not null," 
					      		+ "last character varying not null,"
					      		+ "age int)";
				ResultSet rs = stmt.executeQuery(sql);

				//STEP 5: Extract data from result set
				while(rs.next()){
					//Retrieve by column name
					int id  = rs.getInt("id");
					int age = rs.getInt("age");
					String first = rs.getString("first");
					String last = rs.getString("last");

					//Display values
					System.out.print("ID: " + id);
					System.out.print(", Age: " + age);
					System.out.print(", First: " + first);
					System.out.println(", Last: " + last);
				}
				//STEP 6: Clean-up environment
				rs.close();
				stmt.close();
				conn.close();
			}catch(SQLException se){
				//Handle errors for JDBC
				se.printStackTrace();
			}catch(Exception e){
				//Handle errors for Class.forName
				e.printStackTrace();
			}finally{
				//finally block used to close resources
				try{
					if(stmt!=null)
						stmt.close();
				}catch(SQLException se2){
				}// nothing we can do
				try{
					if(conn!=null)
						conn.close();
				}catch(SQLException se){
					se.printStackTrace();
				}//end finally try
			}//end try
			System.out.println("Goodbye!");
		}//end main
		
		public void Datenbankverbindung(){
			
		}
		
		public void CreateDatenbank()
		{
			
		}

}
