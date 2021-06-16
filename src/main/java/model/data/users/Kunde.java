package model.data.users;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import model.data.bestellungen.Rechnung;
import model.data.bestellungen.Warenkorb;
import model.data.filme.Film;
import model.data.general.Adresse;
import model.data.managers.DoubleManager;
import model.data.managers.PDFManager;
import model.data.zahlmethoden.AbstractClassZahlmethode;
import model.database.DatabaseConnection;
import model.database.transfer.SpeicherInDatenbank;
/**
 * Diese Klasse definiert einen Kunden.
 * @author Norman Böttcher
 *
 */
public class Kunde extends AbstractClassUser {
	//Attribute
	private AbstractClassZahlmethode zahlmethode;
	
	private Warenkorb warenkorb;
	
	private Rechnung rechnung;
	
	private double offenerBetrag;
	//Konstruktor
	/**
	 * Konstruktor der Klasse Kunde.
	 * 
	 * @param vorname       der Vorname des Kunden.
	 * @param nachname      der Nachname des Kunden.
	 * @param email         die Email des Kunden.
	 * @param geburtstdatum das Geburtsdatum des Kunden.
	 * @param adrs          die Adresse des Kunden.
	 */
	public Kunde(String vorname, String nachname,String email, Date geburtstdatum, Adresse adrs) {
		super(vorname, nachname, email, geburtstdatum, adrs);
		this.rolle = "Kunde";
		this.warenkorb = new Warenkorb();
	}
	
	//public Methoden
	/**
	 * Methode fuer den Zugriff auf den Warenkorb eines Kunden.
	 * 
	 * @return warenkorb der Warenkorb des Kunden.
	 */
	public Warenkorb getWarenkorb() {
		return warenkorb;
	}

	/**
	 * Methode fuer das Erstellen der Rechnung eines Kunden.
	 * 
	 * @param r die neue Rechnung des Kunden.
	 */
	public void erstelleRechnung(Rechnung r) {
		this.rechnung = r;
		SpeicherInDatenbank.speicherRechnungInDatenbank(getRechnung());
		getRechnung().setNochOffen(); //Zu Beginn ist eine Rechnung nicht bezahlt.
	}

	/**
	 * Methode fuer den Zugriff auf die Rechnung eines Kunden.
	 * 
	 * @return rechnung die Rechnung des Kunden.
	 */
	public Rechnung getRechnung() {
		return rechnung;
	}
	/**
	 * Diese Methode holt den aktuellen offenen Betrag aus der Datenbank.
	 * 
	 * @return offenerBetrag der aktuelle offene Betrag.
	 */
	public double getOffenerBetrag() {
		Connection con = DatabaseConnection.getConnection();
		double betrag = 0;
		try {
			PreparedStatement stmt = con.prepareStatement("select offene_rechnungen from kunden where email = ?");
			stmt.setString(1, getEmail());
			ResultSet r = stmt.executeQuery();
			
			while(r.next()) {
				betrag = r.getDouble("offene_rechnungen");
			}
			this.offenerBetrag = betrag;
			stmt.close();
			r.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return DoubleManager.round(offenerBetrag, 2);
	}

	/**
	 * Mit dieser Methode kann ein Kunde seine Rechnung(en) als PDF herunterladen.
	 * Siehe auch: {@link PDFManager#speicherRechnungAlsPDF(Rechnung) speicherRechnungAlsPDF}
	 * 
	 * @param rechnungs_id die id der Rechnung, die heruntergeladen werden soll.
	 * @param context      der ServletContext.
	 * @param res          ServletResponse.
	 */
	public void rechnungAlsPDFHerunterladen(int rechnungs_id, ServletContext context, HttpServletResponse res) {
		Connection con = DatabaseConnection.getConnection();
		String pfad = "";
		try {
			PreparedStatement stmt = con.prepareStatement("select pfad from rechnungen where rechnungs_id = ?");
			stmt.setInt(1, rechnungs_id);
			ResultSet r = stmt.executeQuery();
			
			while(r.next()) {
				pfad = r.getString("pfad");
			}
			
			System.out.println(pfad);
			
			try {
				File f = new File(pfad);
				FileInputStream inStream = new FileInputStream(f);
				System.out.println(f.getAbsoluteFile() + f.getName());
				
				res.setContentType("application/pdf");
				res.setContentLength((int) f.length());
				
				res.addHeader("Content-Disposition", "attachement; filename=" + pfad);
				
				OutputStream out = res.getOutputStream();
				
				int bytes;
				
				while((bytes = inStream.read()) != -1) {
					out.write(bytes);
				}
				
				inStream.close();
				out.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
			r.close();
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Fuer einen Kunden muss das Alter berechnet werden koennen. Grund ist die
	 * Alterseschraenkung eines Films.
	 * 
	 * @param heutigesDatum das heutige Datum auf desses Basis das Alter berechnet
	 *                      wird.
	 * @return alter das berechnete Alter des Kunden.
	 */
	@SuppressWarnings("deprecation")
	public int berechnetesAlter(Date heutigesDatum) {
		int alter = 0;
		
		if(heutigesDatum.getMonth() > getGeburtsdatum().getMonth()) {
			alter = heutigesDatum.getYear() - getGeburtsdatum().getYear();
		}
		
		if(heutigesDatum.getMonth() < getGeburtsdatum().getMonth()) {
			alter = heutigesDatum.getYear() - getGeburtsdatum().getYear() - 1;
		}
		
		if(heutigesDatum.getMonth() == getGeburtsdatum().getMonth()) {
			if(heutigesDatum.getDate() >= getGeburtsdatum().getDate()) {
				alter = heutigesDatum.getYear() - getGeburtsdatum().getYear();
			} else {
				alter = heutigesDatum.getYear() - getGeburtsdatum().getYear() - 1;
			}
		}
		return alter;
	}

	/**
	 * Methode um die Zahlmethode des Kunden festzulegen.
	 * 
	 * @param zahlmethode die Zahlmethode des Kunden.
	 */
	public void setZahlmethode(AbstractClassZahlmethode zahlmethode) {
		this.zahlmethode = zahlmethode;
	}	

	/**
	 * Methode fuer den Zugriff auf die Zahlmethode des Benutzers.
	 * 
	 * @return zahlmethode die Zahlmethode des Benutzers.
	 */
	public AbstractClassZahlmethode getZahlmethode() {
		return zahlmethode;
	}
	
	/**
	 * Methode, um einen bereits angesehenen Film bewerten zu koennen.
	 * 
	 * @param f         der zu bewertende Film.
	 * @param bewertung die Bewertung des Films.
	 */
	public void bewerteFilm(Film f, double bewertung) {
		f.speicherBewertung(bewertung);
	}

	@Override
	public void bearbeiteEigenesKonto(String wert, String spalte) {
		Connection con = DatabaseConnection.getConnection();
		String query = "update kunden set column = ? where email = ?";
		
		String[] str = query.split("\\s");
		query = query.replace(str[3], spalte);
		try {
			PreparedStatement stmt = con.prepareStatement(query);
			updateUser(wert, spalte, getEmail());
			stmt.setString(1, wert);
			stmt.setString(2, getEmail());
			stmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}	
	}
}
