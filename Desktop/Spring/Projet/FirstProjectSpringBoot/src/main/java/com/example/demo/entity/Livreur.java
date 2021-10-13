package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
@Entity
public class Livreur extends User {
	
	private int solde;
	private int pourcentage;
	private String ipAddress ;

	public Livreur() {
	}

	
	@OneToOne(mappedBy="livreur")
	private Delivery delivery ;

	public Livreur(int solde, int pourcentage, String ipAddress, Delivery delivery) {
		super();
		this.solde = solde;
		this.pourcentage = pourcentage;
		this.ipAddress = ipAddress;
		this.delivery = delivery;
	}

	public int getSolde() {
		return solde;
	}

	public void setSolde(int solde) {
		this.solde = solde;
	}

	public int getPourcentage() {
		return pourcentage;
	}

	public void setPourcentage(int pourcentage) {
		this.pourcentage = pourcentage;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public Delivery getDelivery() {
		return delivery;
	}

	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
	}

	@Override
	public String toString() {
		return "Livreur [solde=" + solde + ", pourcentage=" + pourcentage + ", ipAddress=" + ipAddress + ", delivery="
				+ delivery + "]";
	}

}
