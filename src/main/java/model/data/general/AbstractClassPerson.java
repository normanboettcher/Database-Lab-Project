package model.data.general;

import java.sql.Date;

/**
 * Diese Klasse definiert allgemeine Eigenschaften einer Person.
 * @author Norman Böttcher
 *
 */
public abstract class AbstractClassPerson {
	//Attribute
	private String vorname;
	
	private String nachname;
	
	private Adresse adrs;
	
	private Date geburtsdatum;
	
	//Konstruktor
	/**
	 * Konstruktor der Klasse AbstractClassPerson. Dieser Konstruktor ist fuer das
	 * Erstellen eines Users noetig, da jeder User eine Adresse sowie ein
	 * Geburtsdatum angeben muss.
	 * 
	 * @param vorname      der Vorname einer Person.
	 * @param nachname     der Nachname einer Person.
	 * @param geburtsdatum das Geburtsdatum einer Person.
	 * @param adrs         die Adresse einer Person.
	 */
	public AbstractClassPerson(String vorname, String nachname, Date geburtsdatum, Adresse adrs) {
		this.vorname = vorname;
		this.nachname = nachname;
		this.geburtsdatum = geburtsdatum;
		this.adrs = adrs;
	}

	/**
	 * Konstruktor der Klasse AbstractClassPerson. Dieser Konstruktor wird fuer
	 * einen Regisseur benoetigt, da fuer diesen nur der Vorname sowie der Nachname
	 * gespeichert werden muessen.
	 * 
	 * @param vorname  der Vorname der Person.
	 * @param nachname der Nachname der Person.
	 */
	public AbstractClassPerson(String vorname, String nachname) {
		this.vorname = vorname;
		this.nachname = nachname;
	}
	//Abstrakte Methoden
	/**
	 * Methode zum ausgeben des Vornamens einer PErson.
	 * 
	 * @return vorname der Vorname der Person.
	 */
	public  String getVorname() {
		return vorname;
	}

	/**
	 * Methode zum Ausgeben des Nachnamens einer Person.
	 * 
	 * @return nachname der Nachname der Person.
	 */
	public String getNachname() {
		return nachname;
	}

	/**
	 * Methode fuer den Zugriff auf das Geburtsdatum einer Person.
	 * 
	 * @return geburtsdatum das Geburtsdatum einer Person.
	 */
	public Date getGeburtsdatum() {
		return geburtsdatum;
	}

	/**
	 * Methode fuer den Zugriff auf die Adresse einer Person.
	 * 
	 * @return adrs die Adresse.
	 */
	public Adresse getAdresse() {
		return adrs;
	}
}
