package model.data.general;

/**
 * Diese Klasse definiert eine Adresse.
 * @author Norman Böttcher
 *
 */
public class Adresse {
	
	//Attribute
	private String strasse;
	private String hausnummer;
	private String plz;
	private String ort;
	
	//Kontruktor
	/**
	 * Konstruktor der Klasse Adresse.
	 * 
	 * @param strasse    die Strasse der Adresse.
	 * @param hausnummer Hausnummer der Adresse.
	 * @param plz        Postleitzahl der Adresse.
	 * @param ort        der Ort der Adresse.
	 */
	public Adresse(String strasse, String hausnummer, String plz, String ort) {
		this.strasse = strasse;
		this.hausnummer = hausnummer;
		this.plz = plz;
		this.ort = ort;
	}
	
	//public Methoden
	/**
	 * Methode zum Ausgeben der Strasse.
	 * 
	 * @return strasse die Strasse der Adresse.
	 */
	public String getStrasse() {
		return strasse;
	}

	/**
	 * Methode zum Ausgeben der Hausnummer
	 * 
	 * @return hausnummer die Hausnummer einer Adresse.
	 */
	public String getHausnummer() {
		return hausnummer;
	}

	/**
	 * Methode zum Ausgeben der Postleitzahl einer Adresse.
	 * 
	 * @return plz die Postleitzahl der Adresse.
	 */
	public String getPLZ() {
		return plz;
	}

	/**
	 * Methode zum Ausgeben des Ortes der Adresse.
	 * 
	 * @return ort der Ort der Adresse.
	 */
	public String getOrt() {
		return ort;
	}

}
