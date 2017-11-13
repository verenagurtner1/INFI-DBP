package model;

import java.sql.Connection;
import java.sql.DriverManager;
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

	public static void main(String[] args) {
		VerbindungAufbauen();
		//Datenbanklöschen();
		//DatenbankErzeugung();
		//Datenlöschen();
		Datenherholen();
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
			sql = "CREATE TABLE Employee ("
					+ "id SERIAL primary key,"
					+ "first character varying not null," 
					+ "last character varying not null,"
					+ "age int)";
			ResultSet rs = stmt.executeQuery(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		}

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
			ResultSet rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return DBDaten;
		
	}
	
	// TODO pls help Dave
	public static void Datenhineinschreiben(/*new Array*/)
	{
		//Erstellen eines Arrays
		String[] SDaten = new String[4];
		SDaten[0] = "2";
		SDaten[1] = "Sara";
		SDaten[2] = "Hindelang";
		SDaten[3] = "18";
		
		System.out.println("Creating statement...");
		String sql;
		sql = "Insert Into Employee (id, first, last, age) values (?,?,?,?)";
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	//Löscht nur den Inhalt der Tabelle
	public static void Datenlöschen()
	{
		System.out.println("Creating statement...");
		String sql;
		sql = "DELETE FROM EMPLOYEE";
		
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	//Löscht die ganze Tabelle
	public static void Datenbanklöschen()
	{
		System.out.println("Creating statement...");
		String sql;
		sql = "DROP table Employee";
		
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("HI");
		
	}
}
