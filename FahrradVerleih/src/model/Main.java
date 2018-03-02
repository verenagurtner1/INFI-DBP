package model;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class Main {
	
	

	public static void main(String[] args) {
		
//		Benutzer b = new Benutzer();
//		b.setVorname(textField_Vorname.getText());
//		b.setNachname(textField_Nachname.getText());
//		b.setBenutzername(textField_Benutzername.getText());
//		b.setEmail(textField_EMail.getText());
//		b.setPLZ(Integer.parseInt(textField_PLZ.getText()));
//		b.setGeburtstagsdatum(textField_Geburtstag.getText());
//		b.setPassword(textField_Passwort.getText());
//		b.setFahrradführerschein(1);
		
		Fahrrad f=new Fahrrad();
		//f=(1,1,2014-12-04,2018-05-09);
		
		try {
			DBManager db = new DBManager();
			Connection conn = db.getConnection();
			
			
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
