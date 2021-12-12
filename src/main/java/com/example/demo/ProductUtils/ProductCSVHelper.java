package com.example.demo.ProductUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;

import com.example.demo.entity.MapLocalisationProduitFournisseur;

public class ProductCSVHelper {

	  public static ByteArrayInputStream GeoLocationsProductsToCSV(List<MapLocalisationProduitFournisseur> maplocations ) {
		    final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

		    try (ByteArrayOutputStream out = new ByteArrayOutputStream();
		        CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
		      List<String> data1 = Arrays.asList(
			             // String.valueOf(maploc.getIdGeoLocalisation()),
			              "lat" ,
			              "lng"
			            );
		        csvPrinter.printRecord(data1);
		      for (MapLocalisationProduitFournisseur maploc : maplocations) {
		    	  List<String> data = Arrays.asList(
				             // String.valueOf(maploc.getIdGeoLocalisation()),
		    			  	  String.valueOf(maploc.getLat()) ,
				              String.valueOf(maploc.getLng())
				            );
		    	  
		        csvPrinter.printRecord(data);
		      }

		      csvPrinter.flush();
		      return new ByteArrayInputStream(out.toByteArray());
		    } catch (IOException e) {
		      throw new RuntimeException("fail to import GEO MAP LOCATIONS data to CSV file: " + e.getMessage());
		    }
		  }
	
}
