package model;

public class Fahrrad {
	private int radid;
	private String Marke;
	private String Art;
	private String Farbe;
	private int Zoll;
	
	
	public int getRadid() {
		return radid;
	}
	
	public void setRadid(int radid) {
		this.radid = radid;
	}
	
	public String getMarke() {
		return Marke;
	}
	
	public void setMarke(String marke) {
		Marke = marke;
	}
	
	public String getArt() {
		return Art;
	}
	
	public void setArt(String art) {
		Art = art;
	}
	
	public String getFarbe() {
		return Farbe;
	}
	
	public void setFarbe(String farbe) {
		Farbe = farbe;
	}
	
	public int getZoll() {
		return Zoll;
	}
	
	public void setZoll(int zoll) {
		Zoll = zoll;
	}

	public Fahrrad(int radid, String marke, String art, String farbe, int zoll) {
		super();
		this.radid = radid;
		Marke = marke;
		Art = art;
		Farbe = farbe;
		Zoll = zoll;
	}

	public Fahrrad() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Fahrrad [radid=" + radid + ", Marke=" + Marke + ", Art=" + Art + ", Farbe=" + Farbe + ", Zoll=" + Zoll
				+ "]";
	}
	
	
	

}
