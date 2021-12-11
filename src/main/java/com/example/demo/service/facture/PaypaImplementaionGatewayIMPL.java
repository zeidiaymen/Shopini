package com.example.demo.service.facture;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Client;
import com.example.demo.entity.Facture;
import com.example.demo.repository.facture.*;
import com.example.demo.repository.user.ClientRepository;
import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Details;
import com.paypal.api.payments.Item;
import com.paypal.api.payments.ItemList;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.PayerInfo;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PaypaImplementaionGatewayIMPL implements IPaypalImplementation{

@Autowired
RepFacture Facture ;
@Autowired
ClientRepository client ;
@Autowired
JavaMailSender javaMailSender;


    private static final String CLIENT_ID = "AZCGdaDcYnMc6oACxCadmTobdjEDzf1tBWac4GMTonuhA4UauCD1TEVnlvAKgkOJPnM1puDjOh0_QUtk";
    private static final String CLIENT_SECRET = "EP81lbPsmkEeUU_EMkDLVdkmn0S7dbQx4aiVYrLXn5u7bLF-lkLcdoprQoZdrTXeQ9YVM2NMfo3QSfsh";
    private static final String MODE = "sandbox";
 
    public String authorizePayment(int id )        
            throws PayPalRESTException {       
    	Facture orderDetail = Facture.findById(id).orElse(null);
    	orderDetail.setActive(true);
    	
       
    	Payer payer = getPayerInformation(orderDetail);
        RedirectUrls redirectUrls = getRedirectURLs();
        List<Transaction> listTransaction = getTransactionInformation(orderDetail);
         
        Payment requestPayment = new Payment();
        requestPayment.setTransactions(listTransaction);
        requestPayment.setRedirectUrls(redirectUrls);
        requestPayment.setPayer(payer);
        requestPayment.setIntent("authorize");
 
        APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
 
        Payment approvedPayment = requestPayment.create(apiContext);
      //  this.sendMail(orderDetail);
        return getApprovalLink(approvedPayment);
 
    }
    public  Payer getPayerInformation( Facture f) {
    	
        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");
         
        PayerInfo payerInfo = new PayerInfo();
        payerInfo.setFirstName(f.getClient().getFirstName())
                 .setLastName(f.getClient().getLastName())
                 .setEmail(f.getClient().getEmail());
         
        payer.setPayerInfo(payerInfo);
         
        return payer;
    }
   public  RedirectUrls getRedirectURLs() {
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl("http://localhost:4200/home");
        redirectUrls.setReturnUrl("http://localhost:4200/home");
         
        return redirectUrls;
    }
    public  List<Transaction> getTransactionInformation(Facture orderDetail) {
        Details details = new Details();
        details.setShipping(orderDetail.getShipping());
        details.setSubtotal(orderDetail.getSubtotal());
        details.setTax(orderDetail.getTax());
     
        Amount amount = new Amount();
        amount.setCurrency("USD");
        amount.setTotal(orderDetail.getTotal());
        amount.setDetails(details);
     
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDescription(orderDetail.getDescription());
         
        ItemList itemList = new ItemList();
        List<Item> items = new ArrayList<Item>();
         
        Item item = new Item();
        item.setCurrency("USD");
        item.setName(orderDetail.getDescription());
        item.setPrice(orderDetail.getSubtotal());
        item.setTax(orderDetail.getTax());
        item.setQuantity("1");
         
        items.add(item);
        itemList.setItems(items);
        transaction.setItemList(itemList);
     
        List<Transaction> listTransaction = new ArrayList<Transaction>();
        listTransaction.add(transaction);  
         
        return listTransaction;
    }
    public  String getApprovalLink(Payment approvedPayment) {
        List<Links> links = approvedPayment.getLinks();
        String approvalLink = null;
         
        for (Links link : links) {
            if (link.getRel().equalsIgnoreCase("approval_url")) {
                approvalLink = link.getHref();
                break;
            }
        }      
         
        return approvalLink;
    }
	public void sendMail(Facture f ) {
		
		
		
		Client c = f.getClient();
		SimpleMailMessage msg = new SimpleMailMessage();
		//f.getClient().getEmail();
        msg.setTo("aymen.zeidi@esprit.tn");

        msg.setSubject("PAYMENT PROCESS ALERT");
        msg.setText("YOU ARE ABOUT TO CHECKOUT IF IT WAS NOT YOU PLEASE CONTACT YOUR BANK TO CANCEL TRANSACTION");

        javaMailSender.send(msg);

		
	}
	public void sendSMS(int id) {
		String ACCOUNT_SID="AC1773045f2e3445f825b6ed4570712ccb";
		String AUTH_TOKEN ="f290767b13b72401ce753df5196fea27";
		String FROM_NUMBER ="+13526463413";
	 
	    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

	    Message.creator(new PhoneNumber("+21654155657"), new PhoneNumber("+13526463413"), "YOU ARE ABOUT TO CHECKOUT IF IT WAS NOT YOU PLEASE CONTACT YOUR BANK TO CANCEL TRANSACTION")
	            .create();
	    

    }
	
}


