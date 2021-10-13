package com.example.demo.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Client extends User {

	private Profession profession;
	private CategorieClient categorieClient;
public Client (){}
	public Client(String firstName, String lastName, String email, String password,String sexe, String tel, String picture,
			String createdAt, Role role, Profession profession, CategorieClient categorieClient) {
		super(firstName, lastName, email, password,sexe, tel, picture, createdAt, role);
		this.profession = profession;
		this.categorieClient = categorieClient;
	}
	@OneToMany(mappedBy="client")
	private List<Facture> factures ;
	@OneToMany(mappedBy="clientDel")
	private List<Delivery> deliveries ;

	public Profession getProfession() {
		return profession;
	}

	public Client(Profession profession, CategorieClient categorieClient, List<Facture> factures,
			List<Delivery> deliveries) {
		super();
		this.profession = profession;
		this.categorieClient = categorieClient;
		this.factures = factures;
		this.deliveries = deliveries;
	}
	public List<Facture> getFactures() {
		return factures;
	}
	public void setFactures(List<Facture> factures) {
		this.factures = factures;
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
