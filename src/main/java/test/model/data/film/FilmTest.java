package test.model.data.film;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Date;

import org.junit.Before;
import org.junit.Test;

import model.data.filme.Film;
import model.data.filme.Produzent;

public class FilmTest {
	
	//Attribute
	private Film film;
	
	/**
	 * Diese Metode definiert die Eigenschaften des Testobjektes.
	 */
	@SuppressWarnings("deprecation")
	@Before
	public void setUp() {
		film = new Film(1, "Musterfilm", 4.99, "Horror", new Produzent(1, "Felix", "Filmmacher"), new Date(2000, 1, 1));
	}
	/**
	 * Pruefung der korrekten ID des Films.
	 */
	@Test
	public void getIdTest() {
		assertTrue(film.getId() == 1);
	}
	/**
	 * Der Film muss den korrekten Filmtitel ausgeben.
	 */
	@Test
	public void filmtitelTest() {
		assertTrue(film.filmtitel().equals("Musterfilm"));
		
		assertFalse(film.filmtitel().equals("musterfilm"));
	}
	/**
	 * Dieser Testfall prueft die Korrektheit eines Produzenten. 
	 * Es wird geprueft, ob die Methoden getVorname() und getNachname() 
	 * die richtigen Werte zurueck geben.
	 *
	 */
	@Test
	public void getProduzentTest() {
		assertTrue(film.getProduzent().getVorname().equals("Felix") == true);
		assertTrue(film.getProduzent().getNachname().equals("Filmmacher") == true);
	}
	/**
	 * Diese Methode testet die getGenre() Methode auf die korrekte Ausgabe fuer unterschiedliche Film-Objekte.
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void getGenreTest() {
		Film film1 = new Film(1, "Movie", 15.49, "Comedy", new Produzent(1, "Felix", "Filmmacher"), new Date(2000, 1, 1));
		Film film2 = new Film(1, "Movie 1", 17.99, "Doku", new Produzent(1, "Felix", "Filmmacher"), new Date(2000, 1, 1));
		Film film3 = new Film(1, "Movie 2", 4.49, "Drama", new Produzent(1, "Felix", "Filmmacher"), new Date(2000, 1, 1));
		Film film4 = new Film(1, "Movie 3", 4.79, "Horror", new Produzent(1, "Felix", "Filmmacher"), new Date(2000, 1, 1));
		
		assertTrue(film1.getGenre().equals("Comedy"));
		assertTrue(film2.getGenre().equals("Doku"));
		assertTrue(film3.getGenre().equals("Drama"));
		assertTrue(film4.getGenre().equals("Horror"));
	}
	/**
	 * Getestet werden hier die Eigenschaften des Datums eines Filmobjektes.
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void veroeffentlichtAmTest() {
		System.out.println("====================Veroeffentlicht===================");
		System.out.println(film.veroeffentlichtAm().getDate());
		System.out.println(film.veroeffentlichtAm().getMonth());
		System.out.println(film.veroeffentlichtAm().getYear());
		
		assertTrue(film.veroeffentlichtAm().getDate() == 1);
		assertTrue(film.veroeffentlichtAm().getMonth() == 1);
		assertTrue(film.veroeffentlichtAm().getYear() == 2000);
	}
	/**
	 * Der Film muss ueber die getPreis()- Methode den korrekten Preis als double ausgeben.
	 */
	@Test
	public void getPreisTest() {
		assertTrue(film.getPreis() == 4.99);
	}
	
	//====================================================ANMERKUNG===========================================================
	/*
	 *Die Nachfolgenden Methoden weisen jeweils die uebergebenen Werte einem Objekt zu.
	 *Das Objekt sollte anschliessend in der Lage sein, die uebergebenen Parameter ueber seine getter-Methoden auszugeben.
	 *Im Programm finden diese Methoden Anwendung, um Parameter aus der Datenbank einem lokalen Objekt innerhalb von Java
	 *zuweisen zu koennen.   
	 */
	/**
	 * Korrekte Ausgabe der Altersbeschraenkung.
	 */
	@Test
	public void holeAlterbeschraenkungAusDBTest() {
		film.holeAltersbeschraenkungAusDB(18);
		assertTrue(film.getAltersbeschraenkung() == 18);
	}
	/**
	 * Test auf die korrekte Zuweisung eines Pfades.
	 * Methode wird genutzt, um Attribute aus der Datenbank einen Java-Objekt zuweisen zu koennen.
	 */
	@Test
	public void holeTitelbildQuelleAusDBTest() {
		film.holeTitelbildQuelleAusDB("bilderpfad/einBild.jpg");
		assertTrue(film.getTitelbildQuelle().equals("bilderpfad/einBild.jpg"));
	}
	/**
	 * Test, ob die korrekte Beschreibung an einen Film uebergeben werden kann.
	 * Methode wird genutzt, um Attribute aus der Datenbank einen Java-Objekt zuweisen zu koennen.
	 */
	@Test
	public void holeBeschreibungAusDBTest() {
		film.holeBeschreibungAusDB("Eine Beschreibung des Films");
		System.out.println("==============Filmbeschreibung===============");
		System.out.println(film.printBeschreibung());
		assertTrue(film.printBeschreibung().equals("Eine Beschreibung des Films"));
	}
	/**
	 * Die korrekte Bewertung des Films als double.
	 * Methode wird genutzt, um Attribute aus der Datenbank einen Java-Objekt zuweisen zu koennen.
	 */
	@Test 
	public void holeBewertungAusDBTest() {
		film.holeBewertungAusDB(5);
		assertTrue(film.getBewertung() == 5.0);
	}
	/**
	 * Die korrekte Uebergabe eines Youtube-Links als String an ein Filmobjekt.
	 * Methode wird genutzt, um Attribute aus der Datenbank einen Java-Objekt zuweisen zu koennen.
	 */
	@Test
	public void holeYoutubelinkAusDBTest() {
		film.holeYoutubeLinkAusDB("www.youtube.com");
		assertTrue(film.getYoutubeLink().equals("www.youtube.com"));
	}
}
