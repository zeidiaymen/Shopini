package com.example.demo.entity;
import java.io.Serializable;
/**
*
* @author ZEIDI AYMEN
*/
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
@NoArgsConstructor
@ToString
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Facture implements Serializable {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	 int id ;
	
	 String description;
     float subtotal;
     float shipping;
     float tax;
     float total;
     String codeQrAuthentif ;
	@Temporal (TemporalType.DATE)
	 Date dateFacture;
	 Boolean active;
	public void setCodeQrAuthentif(String s)
	{
		this.codeQrAuthentif =  s ;
	}
public String getCodeQrAuthentif()
{return this.codeQrAuthentif;
	}
	//relation client et facture
	 @ManyToOne
	 @JsonIgnore
	  @JoinColumn(name="Fk_client")
	   Client client;	 
	 //relation facture  et details facture
	 @OneToMany(mappedBy = "facture", cascade = CascadeType.ALL)
	 @JsonIgnore
	
		  List <detailFacture> detailFactures;
	

	
	
	  public Facture(String desc, String subtotal,
	            String shipping, String tax, String total,Date date ,Boolean active) {
	        this.description = desc;
	        this.subtotal = Float.parseFloat(subtotal);
	        this.shipping = Float.parseFloat(shipping);
	        this.tax = Float.parseFloat(tax);
	        this.total = Float.parseFloat(total);
	        this.dateFacture = date ;
	        this.active = active ;
	    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateFacture() {
		return dateFacture;
	}

	public void setDateFacture(Date dateFacture) {
		this.dateFacture = dateFacture;
	}

	public List<detailFacture> getDetailFactures() {
		return detailFactures;
	}

	public void setDetailFactures(List<detailFacture> detailFactures) {
		this.detailFactures = detailFactures;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setSubtotal(float subtotal) {
		this.subtotal = subtotal;
	}

	public void setShipping(float shipping) {
		this.shipping = shipping;
	}

	public void setTax(float tax) {
		this.tax = tax;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	//
	  public String getDescription() {
	        return description;
	    }
	 
	    public String getSubtotal() {
	        return String.valueOf(subtotal);
	    }
	 
	    public String getShipping() {
	        return String.valueOf( shipping);
	    }
	 
	    public String getTax() {
	        return String.valueOf(tax);
	    }
	     
	    public String getTotal() {
	        return String.valueOf(total);
	    }
	
	

}
