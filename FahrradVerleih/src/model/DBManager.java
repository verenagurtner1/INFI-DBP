package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.fabric.xmlrpc.base.Data;
import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;

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

	public Connection getConnection() throws SQLException
	{
		Connection conn = null;
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
			System.out.println("Fehler beim schließen der Verbindung:");
			System.out.println("Meldung: "+e.getMessage());
			e.printStackTrace();
		}
	}


	public static void closeStatement(Statement stmt) {
		try {
			stmt.close();
		} catch (SQLException e) {
			System.out.println("Fehler beim schließen der Verbindung:");
			System.out.println("Meldung: "+e.getMessage());
			e.printStackTrace();
		}

	}


	public ArrayList<Benutzer> readBenutzer(Connection conn) {

		ArrayList<Benutzer> benutzer = new ArrayList<>();
		String SQL="select * from benutzer;";
		System.out.println(SQL);
		try {
			pstmt=conn.prepareStatement(SQL);
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				int benutzerid=rs.getInt(1);
				String vorname=rs.getString(2);
				String nachname=rs.getString(3);
				int PLZ=rs.getInt(4);
				String email=rs.getString(5);
				String geburtstagsdatum=rs.getString(6);
				int fahrradführerschein=rs.getInt(7);
				String password=rs.getString(8);
				String benutzername=rs.getString(9);

				Benutzer zeile = new Benutzer(benutzerid,vorname, nachname, PLZ, email, geburtstagsdatum, fahrradführerschein, password, benutzername);
				benutzer.add(zeile);
			}
			System.out.println(benutzer);
			rs.close(); rs=null;
			pstmt.close(); pstmt=null;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return benutzer;

	}


	public boolean InsertBenutzer(Connection conn, Benutzer b) {
		//public static boolean writegeloeschteDaten(Connection conn,Daten testzeile2,String tabelle){

		boolean erfolg = true;
		//SQL-Abfrag zum hineinschreiben neuer Daten
		String INSERT_DATA_SQL = "INSERT INTO benutzer (benutzerid, vorname, nachname, plz, email, geburtsdatum, fahrradfuehrerschein, passwort, benutzername) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?)";

		//connection Aufbau
		try {
			pstmt = conn.prepareStatement(INSERT_DATA_SQL);
			pstmt.setInt(1, b.getBenutzerid());
			pstmt.setString(2, b.getVorname());
			pstmt.setString(3, b.getNachname());
			pstmt.setInt(4, b.getPLZ());
			pstmt.setString(5, b.getEmail());
			pstmt.setString(6, b.getGeburtstagsdatum());
			pstmt.setInt(7, b.isFahrradführerschein());
			pstmt.setString(8, b.getPassword());
			pstmt.setString(9, b.getBenutzername());

			pstmt.executeUpdate();

			pstmt.close();pstmt=null;
		} catch (SQLException e) {
			e.printStackTrace();
			erfolg = false;
		}
		return erfolg;

	}


	public Benutzer readBenutzer(Connection conn, int id) {
		Benutzer benutzer = null;
		
		String SQL="select * from benutzer where benutzerid='"+id+"';";
		System.out.println(SQL);
		try {
			pstmt=conn.prepareStatement(SQL);
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				int benutzerid=rs.getInt(1);
				String vorname=rs.getString(2);
				String nachname=rs.getString(3);
				int PLZ=rs.getInt(4);
				String email=rs.getString(5);
				String geburtstagsdatum=rs.getString(6);
				int fahrradführerschein=rs.getInt(7);
				String password=rs.getString(8);
				String benutzername=rs.getString(9);

				benutzer = new Benutzer(benutzerid,vorname, nachname, PLZ, email, geburtstagsdatum, fahrradführerschein, password, benutzername);
			}
			System.out.println(benutzer);
			rs.close(); rs=null;
			pstmt.close(); pstmt=null;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return benutzer;

	}

	public void UpdateBenutzer(Connection conn,String spalte,String tabelleninhalt, int id) {

		String Insert_Hash="UPDATE benutzer set "+spalte+" ='"+tabelleninhalt+"' WHERE benutzerid ='"+id+"';";

		//connection Aufbau
		try {
			pstmt = conn.prepareStatement(Insert_Hash);
			pstmt.executeUpdate();

			pstmt.close();pstmt=null;
		}catch (MySQLSyntaxErrorException m){
			System.out.println("Spaltenname wurde falsch geschrieben");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void deleteBenutzer(Connection conn,int id) {
		{
			String SQL="delete from benutzer where benutzerid='"+id+"';";

			try {
				pstmt = conn.prepareStatement(SQL);
				pstmt.executeUpdate();

				pstmt.close(); pstmt=null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public List<Fahrrad> readFahrrad(Connection conn) {

		ArrayList<Fahrrad> fahrrad = new ArrayList<>();
		String SQL="select * from fahrrad;";
		System.out.println(SQL);
		try {
			pstmt=conn.prepareStatement(SQL);
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				int radid=rs.getInt(1);
				String marke=rs.getString(2);
				String art=rs.getString(3);
				String farbe=rs.getString(4);
				int zoll=rs.getInt(5);

				Fahrrad zeile = new Fahrrad(radid,marke,art,farbe,zoll);
				fahrrad.add(zeile);
			}
			System.out.println(fahrrad);
			rs.close(); rs=null;
			pstmt.close(); pstmt=null;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fahrrad;
	}
	
	public Fahrrad readFahrrad(Connection conn, int id) {

		Fahrrad fahrrad=null;
		String SQL="select * from fahrrad where radid='"+id+"';";
		System.out.println(SQL);
		try {
			pstmt=conn.prepareStatement(SQL);
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				int radid=rs.getInt(1);
				String marke=rs.getString(2);
				String art=rs.getString(3);
				String farbe=rs.getString(4);
				int zoll=rs.getInt(5);

				fahrrad = new Fahrrad(radid,marke,art,farbe,zoll);
			}
			System.out.println(fahrrad);
			rs.close(); rs=null;
			pstmt.close(); pstmt=null;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fahrrad;
	}

	public boolean InsertFahrrad(Connection conn,Fahrrad fahrrad){

		boolean erfolg = true;
		//SQL-Abfrag zum hineinschreiben neuer Daten
		String INSERT_DATA_SQL = "INSERT INTO fahrrad (Marke, Art, Farbe, Zoll) VALUES (?, ?, ?, ?)";

		//connection Aufbau
		try {
			pstmt = conn.prepareStatement(INSERT_DATA_SQL);

			pstmt.setString(1, fahrrad.getMarke());
			pstmt.setString(2, fahrrad.getArt());
			pstmt.setString(3, fahrrad.getFarbe());
			pstmt.setInt(4, fahrrad.getZoll());

			pstmt.executeUpdate();

			pstmt.close();pstmt=null;
		} catch (SQLException e) {
			e.printStackTrace();
			erfolg = false;
		}
		return erfolg;

	}

	public void UpdateFahrrad(Connection conn,String spalte,String spalteninhalt, int id) {

		String Insert_Hash="UPDATE fahrrad set "+spalte+" ='"+spalteninhalt+"' WHERE radid ='"+id+"';";

		//connection Aufbau
		try {
			pstmt = conn.prepareStatement(Insert_Hash);
			pstmt.executeUpdate();

			pstmt.close();pstmt=null;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public void deleteFahrrad(int id, Connection conn) {
		{
			String SQL="delete from fahrrad where radid='"+id+"';";

			try {
				pstmt = conn.prepareStatement(SQL);
				pstmt.executeUpdate();

				pstmt.close(); pstmt=null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public ArrayList<Verleih> readVerleih(Connection conn) {

		ArrayList<Verleih> verleih = new ArrayList<>();
		String SQL="select * from verleih;";
		System.out.println(SQL);
		try {
			pstmt=conn.prepareStatement(SQL);
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				int benutzerid=rs.getInt(1);
				int radid=rs.getInt(2);
				Date datumaus=rs.getDate(3);
				String datumzurueck=rs.getString(4);

				Verleih zeile = new Verleih(benutzerid,radid,datumaus,datumzurueck);
				verleih.add(zeile);
			}
			System.out.println(verleih);
			rs.close(); rs=null;
			pstmt.close(); pstmt=null;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return verleih;

	}
	
	public Verleih readVerleih(Connection conn, int rid, int bid) {

		Verleih verleih = null;
		String SQL="select * from verleih where radid='"+rid+"' and benutzerid='"+bid+"';";
		System.out.println(SQL);
		try {
			pstmt=conn.prepareStatement(SQL);
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				int benutzerid=rs.getInt(1);
				int radid=rs.getInt(2);
				Date datumaus=rs.getDate(3);
				String datumzurueck=rs.getString(4);

				verleih = new Verleih(benutzerid,radid,datumaus,datumzurueck);
			}
			System.out.println(verleih);
			rs.close(); rs=null;
			pstmt.close(); pstmt=null;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return verleih;

	}

	public boolean InsertVerleih(Connection conn, Verleih v) {
		//public static boolean writegeloeschteDaten(Connection conn,Daten testzeile2,String tabelle){

		boolean erfolg = true;
		//SQL-Abfrag zum hineinschreiben neuer Daten
		String INSERT_DATA_SQL = "INSERT INTO verleih (BenutzerID, RadID, DatumAUS, DatumZURUECK) VALUES (?, ?, ?, ?)";

		//connection Aufbau
		try {
			pstmt = conn.prepareStatement(INSERT_DATA_SQL);
			pstmt.setInt(1, v.getBenutzerid());
			pstmt.setInt(2, v.getRadid());
			pstmt.setDate(3, v.getDatumaus());
			pstmt.setString(4, v.getDatumzurueck());

			pstmt.executeUpdate();

			pstmt.close();pstmt=null;
		} catch (SQLException e) {
			e.printStackTrace();
			erfolg = false;
		}
		return erfolg;

	}

	public void UpdateVerleih(Connection conn,int bid, int rid,String spalte,String datum) {

		String SQL="UPDATE verleih set "+spalte+" ='"+datum+"' WHERE RadID ='"+rid+"'&& BenutzerID='"+bid+"';";

		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.executeUpdate();

			pstmt.close();pstmt=null;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public void deleteVerleih(Connection conn,int bid,int rid) {
		{
			String SQL="delete from verleih where BenutzerID='"+bid+"' && RadID='"+rid+"';";

			try {
				pstmt = conn.prepareStatement(SQL);
				pstmt.executeUpdate();

				pstmt.close(); pstmt=null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<Adresse> readAdresse(Connection conn) {

		ArrayList<Adresse> adresse = new ArrayList<>();
		String SQL="select * from adresse;";
		System.out.println(SQL);
		try {
			pstmt=conn.prepareStatement(SQL);
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				String straße=rs.getString(1);
				String ort=rs.getString(2);
				int plz=rs.getInt(3);
				int hausnummer=rs.getInt(4);

				Adresse zeile = new Adresse(straße,ort,plz,hausnummer);
				adresse.add(zeile);
			}
			System.out.println(adresse);
			rs.close(); rs=null;
			pstmt.close(); pstmt=null;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return adresse;
	}
	
	public Adresse readAdresse(Connection conn,int id) {

		Adresse adresse=null;
		String SQL="select * from adresse where plz="+id+";";
		System.out.println(SQL);
		try {
			pstmt=conn.prepareStatement(SQL);
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				String straße=rs.getString(1);
				String ort=rs.getString(2);
				int plz=rs.getInt(3);
				int hausnummer=rs.getInt(4);

				adresse = new Adresse(straße,ort,plz,hausnummer);
			}
			System.out.println(adresse);
			rs.close(); rs=null;
			pstmt.close(); pstmt=null;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return adresse;
	}

	public boolean InsertAdresse(Connection conn,Adresse adresse){

		boolean erfolg = true;
		//SQL-Abfrag zum hineinschreiben neuer Daten
		String INSERT_DATA_SQL = "INSERT INTO adresse (Straße, Ort, PLZ, Hausnummer) VALUES (?, ?, ?, ?)";

		//connection Aufbau
		try {
			pstmt = conn.prepareStatement(INSERT_DATA_SQL);

			pstmt.setString(1, adresse.getStraße());
			pstmt.setString(2, adresse.getOrt());
			pstmt.setInt(3, adresse.getPlz());
			pstmt.setInt(4, adresse.getHausnummer());

			pstmt.executeUpdate();

			pstmt.close();pstmt=null;
		} catch (SQLException e) {
			e.printStackTrace();
			erfolg = false;
		}
		return erfolg;

	}

	public void UpdateAdresse(Connection conn,String spalte,String spalteninhalt, int id) {

		String Insert_Hash="UPDATE adresse set "+spalte+" ='"+spalteninhalt+"' WHERE PLZ ='"+id+"';";

		//connection Aufbau
		try {
			pstmt = conn.prepareStatement(Insert_Hash);
			pstmt.executeUpdate();

			pstmt.close();pstmt=null;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public void deleteAdresse(int id, Connection conn) {
		{
			String SQL="delete from adresse where PLZ='"+id+"';";

			try {
				pstmt = conn.prepareStatement(SQL);
				pstmt.executeUpdate();

				pstmt.close(); pstmt=null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public int letzerEintrag (Connection conn,String tabelle,String maxparameter) {

		int id=0;
		String SQL="select max("+maxparameter+") from "+tabelle+";";
		System.out.println(SQL);
		try {
			pstmt=conn.prepareStatement(SQL);
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				id=rs.getInt(1);
			}
			rs.close(); rs=null;
			pstmt.close(); pstmt=null;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

}
