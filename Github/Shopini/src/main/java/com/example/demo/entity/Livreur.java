package com.example.demo.entity;


import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@SuppressWarnings("serial")
@DiscriminatorValue("LIVREUR")
@Entity
public class Livreur extends User implements Serializable {
	
	private int solde;
	private int pourcentage;

	@OneToOne(mappedBy="livreur",cascade=CascadeType.ALL)
	@JsonIgnore
	Delivery del ;

	public Livreur(int solde, int pourcentage, Delivery del) {
		super();
		this.solde = solde;
		this.pourcentage = pourcentage;
		this.del = del;
	}



	public Delivery getDel() {
		return del;
	}



	public void setDel(Delivery del) {
		this.del = del;
	}



	public Livreur() {
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

}
