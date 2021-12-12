package com.example.demo.service.facture;

import java.io.IOException;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Delivery;
import com.example.demo.entity.Facture;
import com.google.zxing.WriterException;


public interface IFacture {
	public byte[] addFacture (Facture p,String id ) throws WriterException, IOException;
	//public Facture addFacture (Facture p,int id ) throws WriterException, IOException;
	public void deleteFacture (int id );
	public List<Facture>getFacture ();
	public Facture updateFacture (Facture f);
	public  void generateQRCodeImage(String text) throws WriterException, IOException ;
	public Facture findById(String id );
	public Facture getFactureByQRCode(String code );
	public int counterClient (String id);
	public float getBillOfTheDay();
}
