package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Datenbank {

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "org.postgresql.Driver";  
	static final String DB_URL = "jdbc:postgresql://localhost/DBP";

	//  Database credentials
	static final String USER = "postgres";
	static final String PASS = "password";

	static Connection conn = null;
	static Statement stmt = null;
	static PreparedStatement pstmt = null;
	static ResultSet rs = null;
	static String id;

	public static void main(String[] args) {
		VerbindungAufbauen();
		//Datenbanklöschen();
		//DatenbankErzeugung();
		//Datenlöschen();
		//Datenherholen();
	}

	public static void VerbindungAufbauen()
	{
		System.out.println("Connecting to database...");
		try {
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void DatenbankErzeugung()
	{
		System.out.println("Creating database...");
		try {
			stmt = conn.createStatement();
			String sql;
			sql = "CREATE TABLE if not exists Employee ("
					+ "id SERIAL primary key,"
					+ "first character varying not null," 
					+ "last character varying not null,"
					+ "age int)";
			stmt.execute(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Tabelle wurde erstellt");

	}

	// TODO pls help Dave
	public static ArrayList<String[]> Datenherholen()
	{
		ArrayList<String[]> DBDaten = new ArrayList<String[]>();

		System.out.println("Creating statement...");
		String sql;
		sql = "SELECT id, first, last, age FROM Employee";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while(rs.next()){
				/*//Retrieve by column name
				int id  = rs.getInt("id");
				int age = rs.getInt("age");
				String first = rs.getString("first");
				String last = rs.getString("last");
				 */
				String[] zeile = new String[4];
				System.out.print("Gelesen wurde: ");
				for (int i = 0; i < 4; i++) {
					zeile[i] = rs.getString(i+1);
					System.out.println(" '" + zeile[i] + "'");	//zur Kontrolle

				}
				DBDaten.add(zeile);
				/*
				//Display values
				System.out.print("ID: " + ArrayList<String[0]> DBDaten);
				System.out.print(", Age: " + age);
				System.out.print(", First: " + first);
				System.out.println(", Last: " + last);

				 */
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return DBDaten;

	}

	
	public static void Datenhineinschreiben(String[] SDaten)
	{
		/*
		//Erstellen eines Arrays
		SDaten = new String[4];
		SDaten[0] = "2";
		SDaten[1] = "Sara";
		SDaten[2] = "Hindelang";
		SDaten[3] = "18";
		 */

		System.out.println("Creating statement...");

		try {
			//PreparedStatement lohnt sich erst von mans erst verwendet wenn sersch null, also nit immer wieder neu preparen, und sql braucht man erst wenn man pstmt braucht
			if(pstmt==null)
			{
				String sql;
				sql = "Insert Into Employee (id, first, last, age) values (?,?,?,?)";
				//sql = "Insert Into Employee (id, first, last, age) values ("+SDaten[0]+",'"+SDaten[1]+"','"+SDaten[1]+"',"+SDaten[0]+")";
				pstmt = conn.prepareStatement(sql);
			}

			//Integer.parseInt(SDaten[0]) wandelt STring in integer
			pstmt.setInt(1, Integer.parseInt(SDaten[0]));
			pstmt.setString(2, SDaten[1]);
			pstmt.setString(3, SDaten[2]);
			pstmt.setInt(4, Integer.parseInt(SDaten[3]));

			pstmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("Daten wurden in Datenbank eingetragen");
		/*
		sql = "Insert Into Employee (id, first, last, age) values ("+SDaten[0]+",'"+SDaten[1]+"','"+SDaten[1]+"',"+SDaten[0]+")";
		normales Statement verwenden

		 */
	}

	//Löscht nur den Inhalt der Tabelle
	public static void Datenlöschen()
	{
		System.out.println("Creating statement...");
		String sql;
		sql = "DELETE FROM EMPLOYEE";

		try {
			stmt = conn.createStatement();
			stmt.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("Daten in Datenbank in Tabelle wurden gelöscht");

	}

	//Löscht die ganze Tabelle
	public static void Datenbanklöschen()
	{
		System.out.println("Creating statement...");
		String sql;
		sql = "DROP table Employee";

		try {
			stmt = conn.createStatement();
			stmt.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("Tabelle in Datenbank wurde gelöscht");


	}
	
	// TODO fertig machen
	public static void Updatefunktion()
	{
		
		System.out.println("Creating Updatestatement...");

		try {
			//PreparedStatement lohnt sich erst von mans erst verwendet wenn sersch null, also nit immer wieder neu preparen, und sql braucht man erst wenn man pstmt braucht
			if(pstmt==null)
			{
				String sql;
				
				sql = "Update Employee SET vorname='Gustav', nachname='Günther',alter='52' where id=\'"+id+"\'";
				//sql = "Insert Into Employee (id, first, last, age) values ("+SDaten[0]+",'"+SDaten[1]+"','"+SDaten[1]+"',"+SDaten[0]+")";
				pstmt = conn.prepareStatement(sql);
			}

			//Integer.parseInt(SDaten[0]) wandelt STring in integer
			//pstmt.setInt(1, Integer.parseInt(SDaten[0]));
			//pstmt.setString(2, SDaten[1]);
			//pstmt.setString(3, SDaten[2]);
			//pstmt.setInt(4, Integer.parseInt(SDaten[3]));

			pstmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("Daten wurden in Datenbank upgedatet");
		
	}
}
