package com.example.demo.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@SuppressWarnings("serial")
@DiscriminatorValue("CLIENT")
@Entity
public class Client extends User implements Serializable {

	private Profession profession;
	private CategorieClient categorieClient;
@OneToMany(mappedBy="client")
private List<Facture>facture ;
@OneToMany(mappedBy="clientDel")
private List<Delivery>deliveries;
public Client (){}
	public Profession getProfession() {
		return profession;
	}

	public Client(Profession profession, CategorieClient categorieClient, List<Facture> facture,
			List<Delivery> deliveries) {
		super();
		this.profession = profession;
		this.categorieClient = categorieClient;
		this.facture = facture;
		this.deliveries = deliveries;
	}

	public List<Facture> getFacture() {
		return facture;
	}

	public void setFacture(List<Facture> facture) {
		this.facture = facture;
	}

	public List<Delivery> getDeliveries() {
		return deliveries;
	}

	public void setDeliveries(List<Delivery> deliveries) {
		this.deliveries = deliveries;
	}

	public void setProfession(Profession profession) {
		this.profession = profession;
	}

	public CategorieClient getCategorieClient() {
		return categorieClient;
	}

	public void setCategorieClient(CategorieClient categorieClient) {
		this.categorieClient = categorieClient;
	}

}
