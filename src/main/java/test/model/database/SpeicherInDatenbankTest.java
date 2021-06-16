package test.model.database;

import org.junit.Test;

public class SpeicherInDatenbankTest {

	@Test
	public void vorgangEinerShoppingTourTest() {
		/*
		 * =========================ANMERKUNG==============================================
		 * Die folgenden Ablaeufe sind
		 * auskommentiert, da sie bei erneutem Ausfueren unerwuenschte Aenderungen
		 * ander Datenbank vornehmen wuerden. Der unten dargestellte Ablauf wurde
		 * bereits erfolgreich durchgefuehrt.
		 */
		//==========================ANMERKUNG==============================================
		/*
		 * Die Nutzung dieser Klasse fand in der Anfangsphase der Projektentwicklung statt. 
		 * Ihr ist keiner Bedeutung mehr anzurechnen. 
		 */
		// Der Administrator verwaltet brauchbare Funktionen

		/*
		 * Administrator admin = new Administrator("Test", "Admin", "admin@web-shop.de",
		 * new Date(90, 7, 1), new Adresse("Teststrasse", "7b", "34567", "Ort"));
		 * //admin.loescheFilm(14); admin.loescheProduzent(24);
		 * //admin.loescheYoutubeLink(4);
		 */
		// Lege Admin in Datenbank an.
		/*
		 * Gleichzeitig auch ein User. Also auch in Tabelle users. (letzteres innerhalb
		 * 'speicherAdminInDatenbank').
		 * //SpeicherInDatenbank.speicherAdminInDatenbank(admin); //Anmerkung: Zu diesem
		 * Zeitpunkt noch ohne Passwort. //Datenbank wird von Test-Objekten bereinigt,
		 * bevor Testvorgang starten kann. //admin.loescheFilm(0);
		 * //admin.loescheFilm(1); admin.loescheKunde("email1@beispiel.de");
		 * admin.loescheKunde("email2@beispiel.de"); admin.loescheRechnung(0);
		 * admin.loescheRechnung(1); admin.loescheRechnung(2);
		 * //admin.loescheRechnung(4); //admin.loescheRechnung(5);
		 * //admin.loescheProduzent(0);
		 * 
		 * //Objekte Kunde für Testzwecke werden erstellt. Kunde k = new Kunde("Tim",
		 * "Test", "email1@beispiel.de", new Date(100, 1, 1), new
		 * Adresse("Mustertrasse", "21", "30000", "Testburg")); Kunde k_2 = new
		 * Kunde("Max", "Meter", "email2@beispiel.de", new Date(96, 1, 1), new
		 * Adresse("Am Dorfende", "1", "55555", "Groß Aussendorf"));
		 * k.setZahlmethode(new Klarna()); k_2.setZahlmethode(new PayPal());
		 * 
		 * admin.fuegeKundeHinzu(k); admin.fuegeKundeHinzu(k_2);
		 */
		// Film Objekte fuer Testzwecke werden erstellt.

		/*
		 * Film f = new Film(0, "Gruseldusel", 6.79, "Horror", new Produzent(0, "John",
		 * "Johnson"), new Date(80, 1, 5)); Film f_2 = new Film(1,
		 * "Die Erde dreht sich", 10.99, "Dokumentation", new Produzent(1, "Alexander",
		 * "Hollywood"), new Date(120, 1, 20));
		 * f.aktiviereYoutubeLink("https://www.youtube.com/watch?v=vY01_RiJ6sU");
		 * f_2.aktiviereYoutubeLink("https://www.youtube.com/watch?v=Vqraoy4-GiI");
		 */

		/*
		 * f.setTitelbildQuelle("bilder/gruseldusel_plakat.jpg");
		 * f_2.setTitelbildQuelle("bilder/erde_plakat.jpg");
		 */
		/*
		 * //Kunde 1 kauft beide Filme und eine Rechnung fuer diesen Einkauf wird
		 * erstellt. k.getWarenkorb().addBestellung(0);
		 * k.getWarenkorb().addBestellung(1); k.erstelleRechnung(new Rechnung(0, k, new
		 * Date(120, 7, 13)));
		 * 
		 * //Kunde 2 kauft nur einen Film k_2.getWarenkorb().addBestellung(0);
		 * k.erstelleRechnung(new Rechnung(1, k_2, new Date(120, 7, 13)));
		 * 
		 * 
		 * Der erneute Login eines Kunden wird simuliert. Es soll auf Basis der
		 * Datenbank ein neues Kundenobjekt erzeugt werden. Dieses Kundenobjekt kauft
		 * einen weiteren Film. Im Ergebnis soll sich der Betrag in offene Rechnungen um
		 * den Rechnungsbetrag erhoehen und eine dritte Rechnung mit dem Rechnungsbetrag
		 * soll in der Datenbank vorhanden sein.
		 * 
		 * Connection con = DatabaseConnection.getConnection(); String email =
		 * "email1@beispiel.de"; Kunde kunde = null; try { PreparedStatement stmt =
		 * con.prepareStatement("select * from kunden where email = ?");
		 * stmt.setString(1, email); ResultSet r = stmt.executeQuery();
		 * 
		 * while(r.next()) { kunde = new Kunde(r.getString("vorname"),
		 * r.getString("nachname"), r.getString("email"), r.getDate("geburtsdatum"), new
		 * Adresse(r.getString("strasse"), r.getString("hausnummer"),
		 * r.getString("plz"), r.getString("ort"))); } } catch(SQLException e) {
		 * e.printStackTrace(); }
		 * 
		 * // assertTrue(kunde.getVorname().equals("Tim") == true); //
		 * assertTrue(kunde.getAdresse().getHausnummer().equals("21"));
		 * 
		 * //Der Kunde kauft den Film mit der ID 0 nochmal.
		 * kunde.getWarenkorb().addBestellung(0); kunde.getWarenkorb().addBestellung(1);
		 * 
		 * //assertTrue(kunde.getWarenkorb().zeigeVollenWarenkorb().size() == 1);
		 * //assertTrue(kunde.getWarenkorb().getGesamtpreis() == 6.79);
		 * 
		 * //Nachdem Testfälle fuer den Warenkorb die korrekten Werte ausgeben, wird die
		 * // Rechnung erstellt. kunde.setZahlmethode(new Kreditkarte());
		 * kunde.erstelleRechnung(new Rechnung(5, kunde, new Date(120, 6, 17)));
		 */
		/*
		 * 
		 * Nach dem die Rechnung erstellt wurde, soll ein erneuter LogIn des Kunden
		 * simuliert werden. Nach diesem Login fuehrt der Kunde die Zahlung fuer die
		 * zweite Rechnung aus. Als Ergebnis soll sich der Status von Rechnung mit der
		 * ID 2 auf 'bezahlt ' aendern sowie der Betrag in offene_rechnungen um diesen
		 * Betrag subtrahiert
		 * 
		 * 
		 * Connection conn = DatabaseConnection.getConnection(); String email_id =
		 * "email1@beispiel.de"; Kunde _kunde_ = null; try { PreparedStatement _stmt_ =
		 * conn.prepareStatement("select * from kunden where email = ?");
		 * _stmt_.setString(1, email_id); ResultSet result = _stmt_.executeQuery();
		 * 
		 * while(result.next()) { _kunde_ = new Kunde(result.getString("vorname"),
		 * result.getString("nachname"), result.getString("email"),
		 * result.getDate("geburtsdatum"), new Adresse(result.getString("strasse"),
		 * result.getString("hausnummer"), result.getString("plz"),
		 * result.getString("ort"))); }
		 * 
		 * Rechnung mit der id 2 soll bezahlt werden. Daher --> Zahlmethode dieser
		 * Rechnung holen und _kunde_ hinzufuegen, damit dieser die Bezahlung der
		 * Rechnung durchfuehren kann.
		 * 
		 * PreparedStatement stmt_rechnungen = con.
		 * prepareStatement("select zahlmethode from rechnungen where rechnungs_id = ?"
		 * ); stmt_rechnungen.setInt(1, 2); ResultSet result_rechnungen =
		 * stmt_rechnungen.executeQuery();
		 * 
		 * while(result_rechnungen.next()) {
		 * 
		 * if(result_rechnungen.getString("zahlmethode").equals("Kreditkarte")) {
		 * _kunde_.setZahlmethode(new Kreditkarte()); }
		 * 
		 * if(result_rechnungen.getString("zahlmethode").equals("PayPal")) {
		 * _kunde_.setZahlmethode(new PayPal()); }
		 * 
		 * if(result_rechnungen.getString("zahlmethode").equals("Klarna")) {
		 * _kunde_.setZahlmethode(new Klarna()); } }
		 * 
		 * } catch(SQLException e) { e.printStackTrace(); }
		 * 
		 * _kunde_.getZahlmethode().zahlungAusfuehren(2);
		 * 
		 * assertTrue(_kunde_.getZahlmethode().getZahlmethodeBezeichnung().equals(
		 * "Kreditkarte"));
		 */
		/*
		 * Testergebnis: Rechnung wurde erfolgreich bezahlt. Ebenso wurde erfolgreich
		 * der Betrag der offenen Rechnungen in der Tabelle Kunden angepasst.
		 * 
		 */

		/*
		 * Film batman = new Film(10, "Batman", 3.99, "Action", new Produzent(20, "Tom",
		 * "Burton"), new Date(1989 - 1900,2,1)); Film hangover = new Film(11,
		 * "Hangover", 3.99, "Comedy", new Produzent(21, "Todd", "Phillips"), new
		 * Date(2009-1900, 2, 1)); Film insid = new Film(12, "Insidious", 3.99,
		 * "Horror", new Produzent(22, "Steven" , "Schneider"), new Date(2010-1900, 4,
		 * 1)); Film shutterisland = new Film(13, "Shutter Island", 3.99, "Thriller",
		 */// new Produzent(23, "Martin", "Scorcese"), new Date(2010-1900, 7, 17));
		/*
		 * Administrator a = new Administrator("", "", "", null, null);
		 * a.loescheYoutubeLink(1); a.loescheYoutubeLink(2); a.loescheYoutubeLink(3);
		 */

		/*
		 * batman.aktiviereYoutubeLink("https://www.youtube.com/watch?v=9faSpvs44EA");
		 * hangover.aktiviereYoutubeLink("https://www.youtube.com/watch?v=_lGl1cn9AS4");
		 * insid.aktiviereYoutubeLink("https://www.youtube.com/watch?v=h-RI4NFJlao");
		 * shutterisland.aktiviereYoutubeLink(
		 * "https://www.youtube.com/watch?v=5iaYLCiq5RM");
		 */

		/*
		 * a.loescheFilm(10); a.loescheFilm(11); a.loescheFilm(12); a.loescheFilm(13);
		 * 
		 * batman.setAltersbeschraenkung(12); hangover.setAltersbeschraenkung(12);
		 * insid.setAltersbeschraenkung(16); shutterisland.setAltersbeschraenkung(18);
		 * 
		 * batman.
		 * speicherBeschreibung("Batman ist eine oscarprämierte Verfilmung des gleichnamigen Comics von Bob Kane unter der Regie von Tim Burton, die 1989 erschien."
		 * +
		 * " Batman war einer der bis dahin erfolgreichsten Filme und erwirtschaftete allein mit Merchandising Millionenbeträge. "
		 * + "Der Film startete am 26. Oktober 1989 in den deutschen Kinos." +
		 * "Quelle: Wikipedia"); hangover.
		 * speicherBeschreibung("Hangover  ist eine US-amerikanische Filmkomödie aus dem Jahr 2009. "
		 * + "Der Film bildet den Auftakt der Hangover-Trilogie. " +
		 * "In den Hauptrollen sind Bradley Cooper, Ed Helms und Zach Galifianakis zu sehen."
		 * + "Quelle: Wikipedia"); insid.
		 * speicherBeschreibung("Insidious ist ein US-amerikanischer Horrorfilm aus dem Jahr 2010 unter der Regie von James Wan. Das Drehbuch schrieb Leigh Whannell. "
		 * +
		 * "Die Premiere fand am 14. September 2010 auf dem Toronto International Film Festival statt. "
		 * + "Quelle: Wikipedia" +
		 * "In den Kinos erschien der Film am 1. April 2011 in den Vereinigten Staaten und am 21. Juli 2011 in Deutschland."
		 * ); shutterisland.
		 * speicherBeschreibung("Shutter Island ist ein US-amerikanischer Psychothriller "
		 * +
		 * "von Martin Scorsese mit Leonardo DiCaprio in der Hauptrolle. Der Film basiert auf dem 2003 erschienenen, "
		 * + "gleichnamigen Roman des US-amerikanischen Schriftstellers Dennis Lehane."
		 * + "Quelle: Wikipedia");
		 * 
		 * SpeicherInDatenbank.speicherFilmInDatenbank(batman);
		 * SpeicherInDatenbank.speicherFilmInDatenbank(hangover);
		 * SpeicherInDatenbank.speicherFilmInDatenbank(insid);
		 * SpeicherInDatenbank.speicherFilmInDatenbank(shutterisland);
		 * 
		 * SpeicherInDatenbank.speicherProduzentInDatenbank(batman.getProduzent());
		 * SpeicherInDatenbank.speicherProduzentInDatenbank(hangover.getProduzent());
		 * SpeicherInDatenbank.speicherProduzentInDatenbank(insid.getProduzent());
		 * SpeicherInDatenbank.speicherProduzentInDatenbank(shutterisland.getProduzent()
		 * );
		 */
		/*
		 * batman.setTitelbildQuelle("bilder/batman_plakat.jpg");
		 * hangover.setTitelbildQuelle("bilder/hangover_plakat.jpg");
		 * insid.setTitelbildQuelle("bilder/insidious_plakat.jpg");
		 * shutterisland.setTitelbildQuelle("bilder/shutterIsland_plakat.jpg");
		 */
	}
}