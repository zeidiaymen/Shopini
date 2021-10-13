package com.example.demo.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
public class Facture {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id ;
	
	private String description;
    private float subtotal;
    private float shipping;
    private float tax;
    private float total;
	@Temporal (TemporalType.DATE)
	private Date dateFacture;
	private Boolean active;
	
	@Override
	public String toString() {
		return "Facture [id=" + id + ", description=" + description + ", subtotal=" + subtotal + ", shipping="
				+ shipping + ", tax=" + tax + ", total=" + total + ", dateFacture=" + dateFacture + ", active=" + active
				+ ", client=" + client + ", detailFactures=" + detailFactures + "]";
	}

	//relation client et facture
	 @ManyToOne(cascade=CascadeType.ALL)
	  @JoinColumn(name="Fk_client")
	  private Client client;	 
	 //relation facture  et details facture
	 @OneToMany(mappedBy = "facture")
		private  List <detailFacture> detailFactures;
	
	public Facture(int id, String description, float subtotal, float shipping, float tax, float total, Date dateFacture,
			Boolean active, Client client, List<detailFacture> detailFactures) {
		super();
		this.id = id;
		this.description = description;
		this.subtotal = subtotal;
		this.shipping = shipping;
		this.tax = tax;
		this.total = total;
		this.dateFacture = dateFacture;
		this.active = active;
		this.client = client;
		this.detailFactures = detailFactures;
	}

	public Facture() {
		super();
		// TODO Auto-generated constructor stub
	}
	
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
