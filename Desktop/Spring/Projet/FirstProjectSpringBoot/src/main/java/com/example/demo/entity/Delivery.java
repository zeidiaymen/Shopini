package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
*
* @author ZEIDI AYMEN
*/


@Entity
@Table(name="delivery")
public class Delivery implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int ID ;
	@Column (name ="ClientName")
	private String Name ;
	
	private String CellPhone ;

	private String Address ;
private String status ;

	public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}
	private DeliveryWeightCategory Weight;
	@Temporal(TemporalType.DATE)
	@Column (name ="EstimatedTime")
	private Date EstimatedTime ;	

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id_client")
	private Client clientDel ;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id_livreur")
	private Livreur livreur ;
	
	public Livreur getLivreur() {
		return livreur;
	}

	public void setLivreur(Livreur livreur) {
		this.livreur = livreur;
	}
	@Enumerated(EnumType.STRING)
	public DeliveryWeightCategory getWeight() {
		return Weight;
	}
	public String getAddress() {
		return Address;
	}



	public void setAddress(String address) {
		Address = address;
	}

	
	public void setWeight(DeliveryWeightCategory weight) {
		Weight = weight;
	}






	public Delivery(int iD, String name, String cellPhone, String address, String status, DeliveryWeightCategory weight,
			Date estimatedTime, Client client, Livreur livreur) {
		super();
		ID = iD;
		Name = name;
		CellPhone = cellPhone;
		Address = address;
		this.status = status;
		Weight = weight;
		EstimatedTime = estimatedTime;
		this.clientDel = client;
		this.livreur = livreur;
	}

	public Client getClient() {
		return clientDel;
	}

	public void setClient(Client client) {
		this.clientDel = client;
	}

	public Delivery() {
		
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getCellPhone() {
		return CellPhone;
	}

	public void setCellPhone(String cellPhone) {
		CellPhone = cellPhone;
	}


	public Date getEstimatedTime() {
		return EstimatedTime;
	}

	public void setEstimatedTime(Date estimatedTime) {
		EstimatedTime = estimatedTime;
	}

	
	
	@Override
	public String toString() {
		return "DeliveryDetails [ID=" + ID + ", Name=" + Name + ", CellPhone=" + CellPhone + ", Adress=" + Address
				+ ", Weight=" + Weight + ", EstimatedTime=" + EstimatedTime + "]";
	}

}

