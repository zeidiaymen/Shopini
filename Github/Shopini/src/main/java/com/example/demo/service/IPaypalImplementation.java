package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Facture;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.PayPalRESTException;

public interface IPaypalImplementation  {
	   public String authorizePayment(int id )        
	            throws PayPalRESTException ;

	   public Payer getPayerInformation( Facture f);
	   public  RedirectUrls getRedirectURLs();
	   public  List<Transaction> getTransactionInformation(Facture orderDetail);
	   public  String getApprovalLink(Payment approvedPayment) ;
	   public void sendMail (Facture f  );
	   public void sendSMS(int id);
	   
}
