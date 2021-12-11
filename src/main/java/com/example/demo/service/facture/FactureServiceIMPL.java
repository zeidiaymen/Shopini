package com.example.demo.service.facture;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.List;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Facture;
import com.example.demo.repository.facture.*;
import com.example.demo.repository.user.ClientRepository;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.jayway.jsonpath.internal.Path;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class FactureServiceIMPL implements IFacture{
@Autowired
RepFacture fact ;
@Autowired
ClientRepository client ;

@Override
	public byte[] addFacture(Facture p,String id) throws WriterException, IOException  {
		
		p.setClient(client.findByIds(id));
		fact.save(p);
		return this.getQRCodeImage(p.getCodeQrAuthentif());
		
	}
/*
public Facture addFacture (Facture p , String id ) throws WriterException,IOException{
		
	
	p.setClient(client.findByIds(id));
	p.setTax(5);
	
	p.setTotal(Float.parseFloat(p.getShipping())+Float.parseFloat(p.getSubtotal())+Float.parseFloat(p.getTax()));
	p.setActive(false);
	this.generateQRCodeImage(p.getCodeQrAuthentif());

	return fact.save(p);
}

	public void deleteFacture(int id) {
		fact.deleteById(id);
		
	}
*/
	public List<Facture> getFacture() {
		// TODO Auto-generated method stub
		return fact.findAll();
	}

	public Facture updateFacture(Facture f) {
		// TODO Auto-generated method stub
		return fact.save(f);
	}
	
	public void generateQRCodeImage(String text )
          
			throws WriterException, IOException {
		 log.info("text Check"+text);
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 200, 200);

        java.nio.file.Path path =  FileSystems.getDefault().getPath("./src/main/resources/QRCode.png");
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG",  path);
      
    }
	
	public  byte[] getQRCodeImage(String text) throws WriterException, IOException {
	    QRCodeWriter qrCodeWriter = new QRCodeWriter();
	    BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 200, 200);
	    
	    ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
	    MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
	    byte[] pngData = pngOutputStream.toByteArray(); 
	    return pngData;
	}
	

	public Facture findById(String id) {
		// TODO Auto-generated method stub
		return fact.getFactureByIdClient(id);
	}

	public String payement(Facture f) {
		// TODO Auto-generated method stub
		return null;
	}

	public Facture getFactureByQRCode(String code) {
		// TODO Auto-generated method stub
		return fact.getFactureByCodeQrAuthentif(code);
	}

	
public int counterClient (String id)
{
return fact.clientFactureCounter(id);	
}
@Override
public void deleteFacture(int id) {
	// TODO Auto-generated method stub
	
}
@Override
public float getBillOfTheDay() {
	// TODO Auto-generated method stub
	return fact.getBillOfTheDay();
}

}
