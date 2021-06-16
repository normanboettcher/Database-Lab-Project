package model.data.bestellungen;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.data.filme.Film;
import model.data.managers.DoubleManager;
import model.data.users.Kunde;
import model.data.zahlmethoden.Klarna;
import model.data.zahlmethoden.Kreditkarte;
import model.data.zahlmethoden.PayPal;
import model.database.DatabaseConnection;

/**
 * Diese Klasse deifniert eine Rechnung.
 * 
 * @author Norman Böttcher
 *
 */
public class Rechnung {
	// Attribute
	private int rechnungs_id;
	private Kunde empfaenger;
	private int zahlungsfrist;
	private double betrag;
	private String bezahlstatus;
	private Date datum;
	private String produkte = "";
	private String pfad;

	// Konstruktor
	/**
	 * Konstruktor der Klasse Rechnung.
	 * 
	 * @param id         jede Rechnung bekommt eine id.
	 * @param empfaenger jede Rechnung hat einen Empfaenger
	 * @param datum      jede Rechnung hat ein Datum.
	 */
	public Rechnung(int id, Kunde empfaenger, Date datum) {
		this.rechnungs_id = id;
		this.empfaenger = empfaenger;
		this.datum = datum;
		if (empfaenger.getZahlmethode() != null) {
			this.zahlungsfrist = empfaenger.getZahlmethode().getZahlungsfrist();
		}
		this.betrag = empfaenger.getWarenkorb().getGesamtpreis();
		speicherProdukte(empfaenger.getWarenkorb().zeigeVollenWarenkorb());
	}

	/**
	 * Methode fuer den Zugriff auf das Rechnungsdatum.
	 * 
	 * @return datum das Rechnungsdatum.
	 */
	public Date getRechnungsdatum() {
		return datum;
	}

	/**
	 * Methode fuer Zugriff auf die id der Rechnung.
	 * 
	 * @return rechnungs_id die id der Rechnung.
	 */
	public int getRechnungsID() {
		return rechnungs_id;
	}

	/**
	 * Methode fuer den Zugriff auf den Empfaenger der Rechnung. Empfaenger ist
	 * immer ein Kunde.
	 * 
	 * @return empfaenger der Empfaenger der Rechnung.
	 */
	public Kunde getEmpfaenger() {
		return empfaenger;
	}

	/**
	 * Methode fuer den Zugriff auf die Zahlungsfrist.
	 * 
	 * @return zahlungsfrist die Zahlungsfrist der Rechnung.
	 */
	public int getZahlungsfrist() {
		return zahlungsfrist;
	}

	/**
	 * Methode fuer den Zugriff auf den Gesamtbetrag einer Rechnung.
	 * 
	 * @return betrag der Gesamtbetrag der Rechnung.
	 */
	public double getGesamtBetragRechnung() {
		return DoubleManager.round(betrag, 2);
	}

	/**
	 * Methode fuer den Zugriff auf den Bezahlstatus der Rechnung. 'offen' oder
	 * 'bezahlt' sind moegliche status einer Rechnung.
	 * 
	 * @return bezahlstatus der Bezahlstatus der Rechnung als String.
	 */
	public String getBezahlstatus() {
		return bezahlstatus;
	}

	/**
	 * Diese Methode speichert die Produkte einer Rechnung auf Basis der im Warekorb
	 * befindlichen Produkte.
	 * 
	 * @param f die im Warekorb befindlichen Filme.
	 */
	private void speicherProdukte(ArrayList<Film> f) {
		for (int i = 0; i < f.size(); i++) {
			this.produkte += f.get(i).filmtitel() + ", ";
		}
	}

	/**
	 * Ausgabe der Produkte, fuer die eine Rechnung erstellt wurde. Ausgegeben
	 * werden die Filmtitel als Zeichenkette.
	 * 
	 * @return Zeichenkette aller Produktnamen, fuer die eine Rechnung erstellt
	 *         wurde.
	 */
	public String getProdukte() {
		return produkte.substring(0, produkte.length() - 1);
	}

	/**
	 * Methode zum aendern des Status einer Rechnung. true = 'bezahlt' false =
	 * 'offen'
	 * 
	 */
	public void setNochOffen() {
		Connection con = DatabaseConnection.getConnection();
		try {

			PreparedStatement rechnung_stmt = con
					.prepareStatement("update rechnungen set bezahlstatus = ? where rechnungs_id = ?");
			rechnung_stmt.setString(1, "offen");
			rechnung_stmt.setInt(2, getRechnungsID());

			PreparedStatement stmt_holeOffeneRechnungenBetrag = con
					.prepareStatement("select offene_rechnungen from kunden where email = ?");
			stmt_holeOffeneRechnungenBetrag.setString(1, getEmpfaenger().getEmail());
			ResultSet r_offeneRechnungen = stmt_holeOffeneRechnungenBetrag.executeQuery();

			double offene_rechnungen = 0.0;

			while (r_offeneRechnungen.next()) {
				offene_rechnungen = r_offeneRechnungen.getDouble("offene_rechnungen");
			}

			PreparedStatement stmt_offeneRechnungen = con
					.prepareStatement("update kunden set offene_rechnungen = ? where email = ?");
			stmt_offeneRechnungen.setDouble(1, offene_rechnungen += getGesamtBetragRechnung());
			stmt_offeneRechnungen.setString(2, getEmpfaenger().getEmail());

			rechnung_stmt.executeUpdate();
			stmt_offeneRechnungen.executeUpdate();

			rechnung_stmt.close();
			stmt_offeneRechnungen.close();
			r_offeneRechnungen.close();
			stmt_holeOffeneRechnungenBetrag.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Jede Recung besitzt einen Pfad, unter dem sie als PDF abgespeichert wird.
	 * 
	 * @param pfad der Pfad der Rechnung.
	 */
	public void setPfad(String pfad) {
		this.pfad = pfad;
	}

	/**
	 * Zugriff auf den Pfad der Rechnung.
	 * 
	 * @return pfad der Pfad, unter dem die Rechnung zu finden ist.
	 */
	public String getPfad() {
		return pfad;
	}
	/*
	 * Die folgenden Methoden dienen der Uebergabe zwischen dem jeweilige Wert aus
	 * der Datenbank und des Objektes Rechnung. Die Metoden werden benoetigt, um
	 * interne Berechnungen mit den Werten aus der Datenbank zu taetigen.
	 */
	/**
	 * Methode um Zalugsfrist aus der Datenbank dem Objekt Rechnung zuzuweisen.
	 * 
	 * @param tage die Zahlungsfrist in Tagen.
	 */
	public void holeZahlungsfrist(int tage) {
		this.zahlungsfrist = tage;
	}

	/**
	 * Methode um den Betrag der Rechnung aus der Datenbank dem Objekt Rechung
	 * zuweisen zu koennen.
	 * 
	 * @param betrag der Betrag der Rechnung.
	 */
	public void holeBetrag(double betrag) {
		this.betrag = betrag;
	}

	/**
	 * Methode, um die Zalmethode aus der Datenbank einem Recnungsojekt zuweisen zu
	 * koennen.
	 * 
	 * @param zahlmethode die Zahlmethode aus der Datenbank.
	 */
	public void holeZahlungsmethode(String zahlmethode) {
		if (zahlmethode.equals("PayPal")) {
			getEmpfaenger().setZahlmethode(new PayPal());
		}
		if (zahlmethode.equals("Klarna")) {
			getEmpfaenger().setZahlmethode(new Klarna());
		}
		if (zahlmethode.equals("Kreditkarte")) {
			getEmpfaenger().setZahlmethode(new Kreditkarte());
		}
	}

	/**
	 * Methode um de Status der Rechnung aus der Datebank einem Rechnungsobjekt
	 * zuweisen zu koennen.
	 * 
	 * @param status der 'Status der Rechnung 'offen' oder 'bezahlt'
	 * 
	 */
	public void holeZahlstatus(String status) {
		this.bezahlstatus = status;
	}

	/**
	 * Methode um die Produkte als String aus der Datenbank einem Rechnungsobjekt
	 * zuzuweisen.
	 * 
	 * @param str der String mit alle Filmtiteln der Rechnung.
	 */
	public void holeProdukte(String str) {
		this.produkte = str;
	}

}
