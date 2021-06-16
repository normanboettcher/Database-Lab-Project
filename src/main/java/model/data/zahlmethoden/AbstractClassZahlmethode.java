package model.data.zahlmethoden;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.database.DatabaseConnection;

/**
 * Diese Methode definiert die Grundeigenschaften einer Bezahlmethode.
 * Urspruenglich fuer jede Zahlmethode spezifische Eigenscaften geplant, daher
 * die Klassen PayPal, Klarna und Kreditkarte im folgenden.
 * 
 * @author Norman Böttcher
 *
 */
public abstract class AbstractClassZahlmethode  {
	//Attribute
	protected String zahlmethode;
	
	protected int zahlungsfrist;
	
	/**
	 * Methode um eine Zahlung mit der jeweiligen Zahlmethode ausfuehren zu koennen.
	 * Da diese Methode fuer jede Zahlungsmethode gleich ablaeuft, wird diese nur
	 * einmal implementiert.
	 * 
	 * @param rechnungs_id die Rechnung, die bezahlt werden soll.
	 */
	public void zahlungAusfuehren(int rechnungs_id) {
		Connection con = DatabaseConnection.getConnection();
		
		try {
			String filmAusRechnungQuery = "update rechnungen set bezahlstatus = ? where rechnungs_id = ?";
			
			PreparedStatement updateBezahltStmt = con.prepareStatement(filmAusRechnungQuery);
			updateBezahltStmt.setString(1, "bezahlt"); //Die Rechnung erhaelt den Status bezahlt.
			updateBezahltStmt.setInt(2, rechnungs_id);
			updateBezahltStmt.executeUpdate();
			
			PreparedStatement stmt = con.prepareStatement("select * from kunden inner join rechnungen "
					+ "on kunden.email = rechnungen.email "
					+ "where rechnungs_id = ?");
			stmt.setInt(1, rechnungs_id);
			ResultSet rset = stmt.executeQuery();
			
			double offene_rechnungen = 0;
			double betrag = 0;
			String email = "";
			
			while(rset.next()) {
				offene_rechnungen = rset.getDouble("offene_rechnungen");
				betrag = rset.getDouble("betrag");
				email = rset.getString("email");
			}
			
			double neuerOffenerBetrag = offene_rechnungen - betrag;
			
			PreparedStatement stmt_offene_rechnungen = con.prepareStatement("update kunden set offene_rechnungen = ? where email = ?");
			stmt_offene_rechnungen.setDouble(1,  neuerOffenerBetrag);
			stmt_offene_rechnungen.setString(2, email);
			stmt_offene_rechnungen.executeUpdate();
			stmt.close();
			stmt_offene_rechnungen.close();
			rset.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

	/**
	 * Methode zum Ausgeben der Zahlmethode als String.
	 * 
	 * @return zahlmethode die Zahlmethode.
	 */
	public String getZahlmethodeBezeichnung() {	
		return zahlmethode;
	}

	/**
	 * Methode zum Ausgeben der Zahlungsfrist.
	 * 
	 * @return zahlungsfrist die Zahlungsfrist.
	 */
	public int getZahlungsfrist() {
		return zahlungsfrist;
	}
}
