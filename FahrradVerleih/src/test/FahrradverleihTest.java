package test;


import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import junit.framework.Assert;
import model.Adresse;
import model.Benutzer;
import model.DBManager;
import model.Fahrrad;
import model.Verleih;

public class FahrradverleihTest {
	
	//

    static Connection conn;
   static  Statement stmt;
   static DBManager db;
   static int id;

   @BeforeClass
	public static void LoadDB(){
	   System.out.println("Verbindung zum Driver aufbauen");
		try {
			db = new DBManager();
			
		} catch (InstantiationException e) {
			fail("Failed to get Instance of Driver");
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			fail("Failed to load JDBC Driver");
			e.printStackTrace();
		}
		
	}
   
	@BeforeClass
	public static void TestConnection() {
		System.out.println("Verbindung zu DB testen");
		try{
			DBManager db= new DBManager();
			conn=db.getConnection();
		}catch (SQLException e) {
			//e.printStackTrace();
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
			fail("Connection failed");
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		assertTrue(conn!=null);
	}
	
    @AfterClass
    public static void closeConnection() throws SQLException {
    	System.out.println("Verbindung zu DB löschen");
    	
    	DBManager.releaseConnection(conn);
    	return;
    }    
        
	@Test
	public void TestSelectBenutzer()
	{
		System.out.println("Auslesen von Benutzerdaten prüfen.");
		try{
			DBManager db= new DBManager();
			Connection conn=db.getConnection();
			
			Benutzer b=db.readBenutzer(conn,1);
			
			assertEquals("Sandra",b.getVorname());
			assertEquals("Gurtner",b.getNachname());
			assertEquals(6406,b.getPLZ());
			assertEquals("verena.gu@aon.at",b.getEmail());
			assertEquals("1999-05-10",b.getGeburtstagsdatum());
			assertEquals(0,b.isFahrradführerschein());
			assertEquals("password",b.getPassword());
			assertEquals("Verena473",b.getBenutzername());
			
		}catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch(SQLException e){
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void TestInsertBenutzer(){
		Benutzer b = new Benutzer();
		b.setBenutzername("Alinchen");
		b.setEmail("alinchen.alinchen@alinchen.com");
		b.setFahrradführerschein(1);
		b.setGeburtstagsdatum("2000-06-24");
		b.setNachname("Schärmer");
		b.setPassword("1234");
		b.setPLZ(6401);
		b.setVorname("Alina");
		
		Benutzer b2 = new Benutzer();
		
		try {
			db = new DBManager();
			conn = db.getConnection();
			db.InsertBenutzer(conn,b);
			int id = db.letzerEintrag(conn,"Benutzer","benutzerid");
			b2 = db.readBenutzer(conn,id);
			
		} 
		catch (MySQLIntegrityConstraintViolationException e) {
			// Unique Constraint Violation --> Error ist OK
			assertTrue(true);
		}
		catch (SQLException e) {
			e.printStackTrace();
			fail();
		} catch (InstantiationException e) {
			e.printStackTrace();
			fail();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			fail();
		}
		assertEquals(b.getBenutzername(),b2.getBenutzername());
		assertEquals(b.getVorname(),b2.getVorname());
		assertEquals(b.getNachname(),b2.getNachname());
		assertEquals(b.getEmail(),b2.getEmail());
		assertEquals(b.getGeburtstagsdatum(),b2.getGeburtstagsdatum());
		assertEquals(b.getPassword(),b2.getPassword());
		assertEquals(b.getPLZ(),b2.getPLZ());
		
	}
	
	@Test
	public void TestUpdateBenutzer(){
		Benutzer b=new Benutzer();
		try {
			db = new DBManager();
			conn = db.getConnection();
			db.UpdateBenutzer(conn, "benutzername", "Verena473", 1);
			
			b=db.readBenutzer(conn,1);
			
		} 
		catch (MySQLIntegrityConstraintViolationException e) {
			// Unique Constraint Violation --> Error ist OK
			assertTrue(true);
		}
		catch (SQLException e) {
			e.printStackTrace();
			fail();
		} catch (InstantiationException e) {
			e.printStackTrace();
			fail();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			fail();
		}
		assertEquals(1,b.getBenutzerid());
		assertEquals("Verena473",b.getBenutzername());
		
	}
	
	@Test
	public void TestDeleteBenutzer()  {
		
			try {
				db = new DBManager();
				conn = db.getConnection();
				db.deleteBenutzer(conn,11);
				
				assertNull(db.readBenutzer(conn,11));
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
			catch (MySQLIntegrityConstraintViolationException e) {
				// Unique Constraint Violation --> Error ist OK
				assertTrue(true);
			}
			catch (SQLException e) {
				e.printStackTrace();
				fail();
			}
			
		
	}
	
	@Test
	public void TestSelectFahrrad()
	{
		int id=1;
		System.out.println("Auslesen von Benutzerdaten prüfen.");
		try{
			DBManager db= new DBManager();
			Connection conn=db.getConnection();
			
			Fahrrad f=db.readFahrrad(conn,id);
			
			assertEquals(id,f.getRadid());
			assertEquals("Alltagsrad",f.getArt());
			assertEquals("weiß-türkis",f.getFarbe());
			assertEquals("Trek",f.getMarke());
			assertEquals(26,f.getZoll());
			
		}catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch(SQLException e){
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void TestInsertFahrrad(){
		Fahrrad f = new Fahrrad();
		f.setArt("Rennfahrrad");
		f.setFarbe("blau");
		f.setMarke("Trek");
		f.setZoll(29);
		
		Fahrrad f2 = new Fahrrad();
		
		try {
			db = new DBManager();
			conn = db.getConnection();
			db.InsertFahrrad(conn,f);
			int id = db.letzerEintrag(conn,"Fahrrad","radid");
			f2 = db.readFahrrad(conn,id);
			
		} 
		catch (MySQLIntegrityConstraintViolationException e) {
			// Unique Constraint Violation --> Error ist OK
			assertTrue(true);
		}
		catch (SQLException e) {
			e.printStackTrace();
			fail();
		} catch (InstantiationException e) {
			e.printStackTrace();
			fail();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			fail();
		}
		assertEquals(f.getArt(),f2.getArt());
		assertEquals(f.getFarbe(),f2.getFarbe());
		assertEquals(f.getMarke(),f2.getMarke());
		assertEquals(f.getZoll(),f2.getZoll());
		
	}
	
	@Test
	public void TestUpdateFahrrad(){
		Fahrrad f=new Fahrrad();
		id=1;
		try {
			db = new DBManager();
			conn = db.getConnection();
			db.UpdateFahrrad(conn, "Art", "Alltagsrad", id);
			
			f=db.readFahrrad(conn,1);
			
		} 
		catch (MySQLIntegrityConstraintViolationException e) {
			// Unique Constraint Violation --> Error ist OK
			assertTrue(true);
		}
		catch (SQLException e) {
			e.printStackTrace();
			fail();
		} catch (InstantiationException e) {
			e.printStackTrace();
			fail();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			fail();
		}
		assertEquals(id,f.getRadid());
		assertEquals("Alltagsrad",f.getArt());
		
	}
	
	@Test
	public void TestDeleteFahrrad()  {
		
			try {
				db = new DBManager();
				conn = db.getConnection();
				db.deleteFahrrad(11,conn);
				
				assertNull(db.readFahrrad(conn,11));
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
			catch (MySQLIntegrityConstraintViolationException e) {
				// Unique Constraint Violation --> Error ist OK
				assertTrue(true);
			}
			catch (SQLException e) {
				e.printStackTrace();
				fail();
			}
			
		
	}
	

	@Test
	public void TestSelectAdresse()
	{
		Adresse a = null;
		int id=6406;
		System.out.println("Auslesen von Benutzerdaten prüfen.");
		try{
			DBManager db= new DBManager();
			Connection conn=db.getConnection();
			
			a=db.readAdresse(conn,id);
			
			assertEquals(id,a.getPlz());
			assertEquals("Oberhofen im Inntal",a.getOrt());
			assertEquals(8,a.getHausnummer());
			assertEquals("Hocheder Weg",a.getStraße());
			
		}catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch(SQLException e){
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void TestInsertAdresse(){
		Adresse a = new Adresse();
		a.setHausnummer(5);
		a.setOrt("Heiterwang");
		a.setPlz(6611);
		a.setStraße("StraßezurStraße");
		
		Adresse a2 = new Adresse();
		
		try {
			db = new DBManager();
			conn = db.getConnection();
			db.InsertAdresse(conn,a);
			int id = db.letzerEintrag(conn,"Adresse","plz");
			a2 = db.readAdresse(conn,id);
			
		} 
		catch (MySQLIntegrityConstraintViolationException e) {
			// Unique Constraint Violation --> Error ist OK
			assertTrue(true);
		}
		catch (SQLException e) {
			e.printStackTrace();
			fail();
		} catch (InstantiationException e) {
			e.printStackTrace();
			fail();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			fail();
		}
		assertEquals(a.getHausnummer(),a2.getHausnummer());
		assertEquals(a.getOrt(),a2.getOrt());
		assertEquals(a.getPlz(),a2.getPlz());
		assertEquals(a.getStraße(),a2.getStraße());
		
	}
	
	@Test
	public void TestUpdateAdresse(){
		Adresse a = new Adresse();
		id=6406;
		try {
			db = new DBManager();
			conn = db.getConnection();
			db.UpdateAdresse(conn, "Straße", "Hocheder Weg", 6406);
			
			a=db.readAdresse(conn,6406);
			
		} 
		catch (MySQLIntegrityConstraintViolationException e) {
			// Unique Constraint Violation --> Error ist OK
			assertTrue(true);
		}
		catch (SQLException e) {
			e.printStackTrace();
			fail();
		} catch (InstantiationException e) {
			e.printStackTrace();
			fail();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			fail();
		}
		assertEquals(id,a.getPlz());
		assertEquals("Hocheder Weg",a.getStraße());
		
	}
	
	@Test
	public void TestDeleteAdresse()  {
		int id=1;
		
			try {
				db = new DBManager();
				conn = db.getConnection();
				db.deleteAdresse(id,conn);
				
				assertNull(db.readAdresse(conn,id));
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
			catch (MySQLIntegrityConstraintViolationException e) {
				// Unique Constraint Violation --> Error ist OK
				assertTrue(true);
			}
			catch (SQLException e) {
				e.printStackTrace();
				fail();
			}
	}

	
	@Test
	public void TestSelectVerleih()
	{
		Adresse a = null;
		int id=6406;
		System.out.println("Auslesen von Benutzerdaten prüfen.");
		try{
			DBManager db= new DBManager();
			Connection conn=db.getConnection();
			
			a=db.readAdresse(conn,id);
			
			assertEquals(id,a.getPlz());
			assertEquals("Oberhofen im Inntal",a.getOrt());
			assertEquals(8,a.getHausnummer());
			assertEquals("Hocheder Weg",a.getStraße());
			
		}catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch(SQLException e){
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void TestInsertVerleih(){
		Verleih v = new Verleih();

		v.setBenutzerid(1);
		v.setDatumzurueck("2018-03-10");
		v.setRadid(1);
		
		Adresse a2 = new Adresse();
		
		try {
			db = new DBManager();
			conn = db.getConnection();
			db.InsertVerleih(conn,v);
			int id = db.letzerEintrag(conn,"Adresse","plz");
			a2 = db.readAdresse(conn,id);
			
		} 
		catch (MySQLIntegrityConstraintViolationException e) {
			// Unique Constraint Violation --> Error ist OK
			assertTrue(true);
		}
		catch (SQLException e) {
			e.printStackTrace();
			fail();
		} catch (InstantiationException e) {
			e.printStackTrace();
			fail();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			fail();
		}
		assertEquals(v.getBenutzerid(),v.getBenutzerid());
		assertEquals(v.getDatumaus(),v.getDatumaus());
		assertEquals(v.getDatumzurueck(),v.getDatumzurueck());
		assertEquals(v.getRadid(),v.getRadid());
		
	}
	
	@Test
	public void TestUpdateVerleih(){
		Verleih v = new Verleih();
		int rid=1;
		int bid=1;
		try {
			db = new DBManager();
			conn = db.getConnection();
			db.UpdateVerleih(conn, rid, bid,"DatumZurueck", "2018-03-10");
			
			v=db.readVerleih(conn,1,1);
			
		} 
		catch (MySQLIntegrityConstraintViolationException e) {
			// Unique Constraint Violation --> Error ist OK
			assertTrue(true);
		}
		catch (SQLException e) {
			e.printStackTrace();
			fail();
		} catch (InstantiationException e) {
			e.printStackTrace();
			fail();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			fail();
		}
		assertEquals(bid,v.getBenutzerid());
		assertEquals(rid,v.getRadid());
		assertEquals("2018-03-10",v.getDatumzurueck());
		
	}
	
	@Test
	public void TestDeleteVerleih()  {
		int rid=2;
		int bid=2;
		
			try {
				db = new DBManager();
				conn = db.getConnection();
				db.deleteVerleih(conn,rid,bid);
				
				assertNull(db.readVerleih(conn,rid, bid));
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
			catch (MySQLIntegrityConstraintViolationException e) {
				// Unique Constraint Violation --> Error ist OK
				assertTrue(true);
			}
			catch (SQLException e) {
				e.printStackTrace();
				fail();
			}
	}

}
