package com.example.demo.controller.facture;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Facture;
import com.example.demo.entity.Produit;
import com.example.demo.repository.facture.*;
import com.example.demo.service.facture.*;
import com.google.zxing.WriterException;
import com.paypal.base.rest.PayPalRESTException;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


@RestController
public class ContFacture {
	
@Autowired
IFacture fact ;
@Autowired
SmsFactService sms ;
@Autowired
IPaypalImplementation paypal ;
@GetMapping(value="/getFactureByID/{id}")
public Facture getFactureById(@PathVariable("id") String id)
{
	return fact.findById(id);
	}



@GetMapping(value="/getCountFact/{id}")
public int CounterForFacturesByClient(@PathVariable("id") String id)
{
	return fact.counterClient(id);
	}



@GetMapping(value ="/getFacture")
public List<Facture> getFacture()
{
return fact.getFacture();	
}
@PostMapping (value ="/postFacture/{id}")
@ResponseBody
public byte[] addProd(@PathVariable ("id")String id,@RequestBody Facture p ) throws WriterException, IOException
{
return fact.addFacture(p,id);	
}

@GetMapping (value ="/Payement/{id}")
@ResponseBody
public String getPayementLink (@PathVariable("id") int id ) throws PayPalRESTException
{
	paypal.sendSMS(id);
return 	paypal.authorizePayment(id);
}

@DeleteMapping(value="/deleteFacture/{id}")
public void deletevar(@PathVariable("id") int id)
{
fact.deleteFacture(id);	
}
@PutMapping(value ="/updateFacture")
@ResponseBody
public Facture updateFacture (@RequestBody Facture f )
{
return fact.updateFacture(f);
}
@GetMapping(value="/QRCodeINFO/{id}")
public Facture getByQrCode (@PathVariable("id") String s )
{
	return fact.getFactureByQRCode(s);
	}

@Scheduled(cron ="0 06 12 * * *")
public void getBillOfTheDay()
{
sms.sendSMS("The Bill of the day is :" +fact.getBillOfTheDay());	
System.out.print( "The Bill of the day is :" +fact.getBillOfTheDay());	
}

}
