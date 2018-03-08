package model;

import java.sql.Date;

import com.mysql.fabric.xmlrpc.base.Data;

public class Verleih {

	private int benutzerid;
	private int radid;
	private Date datumaus;
	private String datumzurueck;
	
	public int getBenutzerid() {
		return benutzerid;
	}
	
	public void setBenutzerid(int benutzerid) {
		this.benutzerid = benutzerid;
	}
	
	public int getRadid() {
		return radid;
	}
	
	public void setRadid(int radid) {
		this.radid = radid;
	}
	
	public Date getDatumaus() {
		return datumaus;
	}
	
	public void setDatumaus(Date datumaus) {
		this.datumaus = datumaus;
	}
	
	public String getDatumzurueck() {
		return datumzurueck;
	}
	
	public void setDatumzurueck(String datumzurueck) {
		this.datumzurueck = datumzurueck;
	}

	public Verleih(int benutzerid, int radid, Date datumaus, String datumzurueck) {
		super();
		this.benutzerid = benutzerid;
		this.radid = radid;
		this.datumaus = datumaus;
		this.datumzurueck = datumzurueck;
	}

	public Verleih() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Verleih [benutzerid=" + benutzerid + ", radid=" + radid + ", datumaus="
				+ datumaus + ", datumzurueck=" + datumzurueck + "]";
	}

	
	
	
}
