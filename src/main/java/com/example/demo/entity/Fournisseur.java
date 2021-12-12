package com.example.demo.entity;
import java.io.Serializable;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@SuppressWarnings("serial")
@DiscriminatorValue("FOURNISSEUR")
@Entity
public class Fournisseur extends User implements Serializable  {

	@OneToMany(mappedBy="fournisseur")
	private Set<Produit> produits;
	
	public Fournisseur() {
		// TODO Auto-generated constructor stub
	}

	
	
	
	

	
}
