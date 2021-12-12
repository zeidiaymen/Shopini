package tn.esprit.magasin.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.stream.Stream;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import tn.esprit.magasin.entity.Facture;

public class GeneratePdf {

	public static ByteArrayInputStream Report(List<Facture> list) throws MalformedURLException, IOException {
		Document document = new Document();
		document.setMargins(40, 40, 40, 40);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		try {

			PdfWriter.getInstance(document, out);
			document.open();
			// Add Text to PDF file ->
						Font font = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK);
						Paragraph para = new Paragraph( "Liste des Factures", font);
						para.setAlignment(Element.ALIGN_CENTER);
						document.add(para);
						document.add(Chunk.NEWLINE);
			        	
			        	PdfPTable table = new PdfPTable(5);
						Stream.of("ID", "Nom et Prenom", "Montant Facture","Montant Remise","Date")
						    .forEach(headerTitle -> {
						          PdfPCell header = new PdfPCell();
						          Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
						          header.setBackgroundColor(BaseColor.LIGHT_GRAY);
						          header.setHorizontalAlignment(Element.ALIGN_CENTER);
						          header.setBorderWidth(2);
						          header.setPhrase(new Phrase(headerTitle, headFont));
						          table.addCell(header);
						    });
			            
			            for (Facture factures : list) {
			            	PdfPCell idCell = new PdfPCell(new Phrase(factures.getIdFacture().toString()));
			            	idCell.setPaddingLeft(4);
			            	idCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			            	idCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			                table.addCell(idCell);

			                PdfPCell nomPrenomCell = new PdfPCell(new Phrase(factures.getClient().getNom()+" "+factures.getClient().getPrenom()));
			                nomPrenomCell.setPaddingLeft(4);
			                nomPrenomCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			                nomPrenomCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			                table.addCell(nomPrenomCell);

			                PdfPCell montantCell = new PdfPCell(new Phrase(String.valueOf(factures.getMontantFacture())));
			                montantCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			                montantCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			                montantCell.setPaddingRight(4);
			                table.addCell(montantCell);
			                
			                PdfPCell montantRemiseCell = new PdfPCell(new Phrase(String.valueOf(factures.getMontantRemise())));
			                montantRemiseCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			                montantRemiseCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			                montantRemiseCell.setPaddingRight(4);
			                table.addCell(montantRemiseCell);
			                
			                PdfPCell dateCell = new PdfPCell(new Phrase(String.valueOf(factures.getDateFacture())));
			                dateCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			                dateCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			                dateCell.setPaddingRight(4);
			                table.addCell(dateCell);
			            }
			            document.add(table);
         
  
			
			document.close();

		} catch (DocumentException ex) {
					System.out.println(ex.getMessage());
		}
		return new ByteArrayInputStream(out.toByteArray());
	}
}
