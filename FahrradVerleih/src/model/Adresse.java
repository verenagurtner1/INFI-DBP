package model;

public class Adresse {

	private String stra�e;
	private String ort;
	private int hausnummer;
	private int plz;
	
	public String getStra�e() {
		return stra�e;
	}
	
	public void setStra�e(String stra�e) {
		this.stra�e = stra�e;
	}
	
	public String getOrt() {
		return ort;
	}
	
	public void setOrt(String ort) {
		this.ort = ort;
	}
	
	public int getHausnummer() {
		return hausnummer;
	}
	
	public void setHausnummer(int hausnummer) {
		this.hausnummer = hausnummer;
	}
	
	public int getPlz() {
		return plz;
	}
	
	public void setPlz(int plz) {
		this.plz = plz;
	}

	public Adresse(String stra�e, String ort, int hausnummer, int plz) {
		super();
		this.stra�e = stra�e;
		this.ort = ort;
		this.hausnummer = hausnummer;
		this.plz = plz;
	}

	public Adresse() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Adresse [stra�e=" + stra�e + ", ort=" + ort + ", hausnummer=" + hausnummer + ", plz=" + plz + "]";
	}
	
	
	
	
}
