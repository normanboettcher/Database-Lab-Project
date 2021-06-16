package model.data.managers;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.data.bestellungen.Rechnung;
import model.data.filme.Film;

/**
 * Dieser PDFManager erstellt auf Basis eines uebergebene Rechnungsobjekts eine
 * Rechnung im PDF-Format.
 * 
 * @author Norman Böttcher
 *
 */
public class PDFManager {
	/**
	 * Metode zum schreiben einer Rechnung als PDF.
	 * 
	 * @param r die Rechnung, fuer die die PDF geschrieben wird.
	 */
	
	@SuppressWarnings("deprecation")
	public static void speicherRechnungAlsPDF(Rechnung r) {

		Document doc = new Document();
		String speicherPfad = "kunden/rechnungen/" +  r.getRechnungsID() + "_" + r.getEmpfaenger().getVorname() + "_" 
				+ r.getEmpfaenger().getNachname() + ".pdf";
		try {
			
			PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(speicherPfad));
			r.setPfad(speicherPfad);
			doc.open();
			doc.addTitle("Ihre Rechnung");
			
			doc.add(new Paragraph("Ihre Rechnung" , FontFactory.getFont(FontFactory.TIMES_ROMAN,18, Font.BOLD, BaseColor.BLACK)));
			doc.add(new Paragraph("\n" + "\n" + "\n"));
			
			doc.add(new Paragraph(r.getEmpfaenger().getVorname() + " " + r.getEmpfaenger().getNachname() + "\n"
					+ r.getEmpfaenger().getAdresse().getStrasse() + " " + r.getEmpfaenger().getAdresse().getHausnummer() + "\n"
					+ r.getEmpfaenger().getAdresse().getPLZ() + "\n" 
					+ r.getEmpfaenger().getAdresse().getOrt(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 13, BaseColor.BLACK)));
			
			doc.add(new Paragraph("\n" + "\n"));
			
			doc.add(new Paragraph("Ihre Bestellung bei Homestar", FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, Font.BOLD, BaseColor.BLACK)));
			doc.add(new Paragraph("\n"));
			doc.add(new Paragraph("Rechnungs_id: " + r.getRechnungsID() + ", Rechnungsdatum: " + 
			r.getRechnungsdatum().getDate() + "." + (r.getRechnungsdatum().getMonth() + 1) + "." + (r.getRechnungsdatum().getYear() + 1900) + "",
			FontFactory.getFont(FontFactory.TIMES_ROMAN, 13, Font.BOLD, BaseColor.BLACK)));
			doc.add(new Paragraph("\n" + "\n"));
			
			PdfPTable artikel = new PdfPTable(3);
			artikel.setWidthPercentage(100);
			artikel.setSpacingBefore(11f);
			artikel.setSpacingAfter(11f);
			
			float[] columnWidth = {2f, 2f, 2f};
			artikel.setWidths(columnWidth);
			PdfPCell spalte1 = new PdfPCell(new Paragraph("Film_ID"));
			PdfPCell spalte2 = new PdfPCell(new Paragraph("Artikel"));
			PdfPCell spalte3= new PdfPCell(new Paragraph("Preis"));
			
			PdfPCell film_id = null;
			PdfPCell filmtitel = null;
			PdfPCell preis = null;
			PdfPCell gesPreis = new PdfPCell(new Paragraph(String.valueOf(r.getGesamtBetragRechnung()) + " €"));
			
			artikel.addCell(spalte1);
			artikel.addCell(spalte2);
			artikel.addCell(spalte3);
			
			ArrayList<Film> artikelliste = r.getEmpfaenger().getWarenkorb().zeigeVollenWarenkorb();
			
			for(int i = 0; i < artikelliste.size(); i++) {
				film_id = new PdfPCell(new Paragraph(String.valueOf(artikelliste.get(i).getId())));
				filmtitel = new PdfPCell(new Paragraph(artikelliste.get(i).filmtitel()));
				preis = new PdfPCell(new Paragraph(String.valueOf(artikelliste.get(i).getPreis()) + "€"));
				artikel.addCell(film_id);
				artikel.addCell(filmtitel);
				artikel.addCell(preis);
			}
			
			artikel.addCell(new PdfPCell(new Paragraph("Gesamtpreis: ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD))));
			artikel.addCell("");
			artikel.addCell(gesPreis);
			
			doc.add(artikel);
			
			doc.add(new Paragraph("\n" + "\n"));
			Paragraph ppreis = new Paragraph(String.valueOf(r.getGesamtBetragRechnung()), FontFactory.getFont(FontFactory.TIMES_BOLD, 13, Font.BOLD));
			doc.add(new Paragraph("Bitte überweisen Sie den Gesamtbetrag von " + ppreis + " € innerhalb von " + 
					String.valueOf(r.getEmpfaenger().getZahlmethode().getZahlungsfrist())
					+ " Tagen auf das unten stehende Geschäftskonto."));
			doc.add(new Paragraph("Ihre Zahlmethode: " + r.getEmpfaenger().getZahlmethode().getZahlmethodeBezeichnung()));
			doc.add(new Paragraph("\n" + "\n"));
			
			doc.add(new Paragraph("Bankverbindung" + "\n", FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.BOLD)));
			doc.add(new Paragraph("Kontoinhaber: Homestar" + "\n", FontFactory.getFont(FontFactory.TIMES_ROMAN, 11)));
			doc.add(new Paragraph("Kreditinstitut: Fantasiebank" + "\n", FontFactory.getFont(FontFactory.TIMES_ROMAN, 11)));
			doc.add(new Paragraph("Verwedungszweck:" + r.getRechnungsID() + r.getEmpfaenger().getVorname().substring(0,2) 
					+ r.getEmpfaenger().getNachname().substring(0, 2) + "\n", FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.BOLD)));
			doc.add(new Paragraph("Betrag: " + String.valueOf(r.getGesamtBetragRechnung() + " €" + "\n"), FontFactory.getFont(FontFactory.TIMES_ROMAN, 11)));
			doc.add(new Paragraph("IBAN: DE00 9876 1234 5678 91" + "\n", FontFactory.getFont(FontFactory.TIMES_ROMAN, 11)));
			
			doc.add(new Paragraph("\n"));
			
			doc.add(new Paragraph("Wir bedanken uns für Ihren Einkauf bei Homestar" + "\n" + "\n"
					+"Mit filmreifen Grüßen" + "\n"
					+ "Die Geschäftsführung"));
			
			doc.close();
			writer.close();
		} catch(DocumentException e) {
			e.printStackTrace();
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
