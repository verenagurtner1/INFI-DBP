package model;

public class Adresse {

	private String straße;
	private String ort;
	private int hausnummer;
	private int plz;
	
	public String getStraße() {
		return straße;
	}
	
	public void setStraße(String straße) {
		this.straße = straße;
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

	public Adresse(String straße, String ort, int hausnummer, int plz) {
		super();
		this.straße = straße;
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
		return "Adresse [straße=" + straße + ", ort=" + ort + ", hausnummer=" + hausnummer + ", plz=" + plz + "]";
	}
	
	
	
	
}
