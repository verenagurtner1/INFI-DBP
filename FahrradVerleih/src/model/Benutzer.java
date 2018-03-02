package model;

import java.sql.Date;

import com.mysql.fabric.xmlrpc.base.Data;

public class Benutzer {
	
	private int benutzerid;
	private String vorname;
	private String nachname;
	private int PLZ;
	private String email;
	private String geburtstagsdatum;
	private int fahrradführerschein;
	private String password;
	private String benutzername;
	
	public int getBenutzerid() {
		return benutzerid;
	}
	
	public void setBenutzerid(int benutzerid) {
		this.benutzerid = benutzerid;
	}
	
	public String getVorname() {
		return vorname;
	}
	
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	
	public String getNachname() {
		return nachname;
	}
	
	public void setNachname(String nachname) {
		this.nachname = nachname;
	}
	
	public int getPLZ() {
		return PLZ;
	}
	
	public void setPLZ(int pLZ) {
		PLZ = pLZ;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getGeburtstagsdatum() {
		return geburtstagsdatum;
	}
	
	public void setGeburtstagsdatum(String geburtstagsdatum) {
		this.geburtstagsdatum = geburtstagsdatum;
	}
	
	public int isFahrradführerschein() {
		return fahrradführerschein;
	}
	
	public void setFahrradführerschein(int i) {
		this.fahrradführerschein = i;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getBenutzername() {
		return benutzername;
	}
	
	public void setBenutzername(String benutzername) {
		this.benutzername = benutzername;
	}

	public Benutzer(int benutzerid, String vorname, String nachname, int pLZ, String email, String geburtstagsdatum,
			int fahrradführerschein, String password, String benutzername) {
		super();
		this.benutzerid = benutzerid;
		this.vorname = vorname;
		this.nachname = nachname;
		PLZ = pLZ;
		this.email = email;
		this.geburtstagsdatum = geburtstagsdatum;
		this.fahrradführerschein = fahrradführerschein;
		this.password = password;
		this.benutzername = benutzername;
	}

	public Benutzer() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Benutzer [benutzerid=" + benutzerid + ", vorname=" + vorname + ", nachname=" + nachname + ", PLZ=" + PLZ
				+ ", email=" + email + ", geburtstagsdatum=" + geburtstagsdatum + ", fahrradführerschein="
				+ fahrradführerschein + ", password=" + password + ", benutzername=" + benutzername + "]";
	}
	
	
	
	

}
