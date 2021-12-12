package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

<<<<<<< HEAD
=======
import com.fasterxml.jackson.annotation.JsonIgnore;

>>>>>>> 37037f1230dc29126282fa3406a9087c60f38c84

@Entity
@Table( name = "DetailProduit")
public class DetailProduit implements Serializable {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="idDetailProduit")
	private Long idDetailProduit;
	@Temporal (TemporalType.DATE)
	private Date dateCreation;
	@Temporal (TemporalType.DATE)
	private Date dateLimiteConsommation;
	@Temporal (TemporalType.DATE)
	private Date dateDerniereModification;
	@Enumerated(EnumType.STRING)
	private CategorieProduit categorieProduit;
	private int quantite;
	
<<<<<<< HEAD
	@OneToOne(mappedBy="detailProduit")
	private Produit produit;
	
=======
	@JsonIgnore
	@OneToOne(mappedBy="detailProduit")
	private Produit produit;
	public DetailProduit(){
		super();
	}
>>>>>>> 37037f1230dc29126282fa3406a9087c60f38c84
	public DetailProduit(Long idDetailProduit, Date dateCreation, Date dateDerniereModification,
			CategorieProduit categorieProduit) {
		super();
		this.idDetailProduit = idDetailProduit;
		this.dateCreation = dateCreation;
		this.dateDerniereModification = dateDerniereModification;
		this.categorieProduit = categorieProduit;
	}

<<<<<<< HEAD
=======
	public DetailProduit(Long idDetailProduit, Date dateCreation, Date dateLimiteConsommation,
			Date dateDerniereModification, CategorieProduit categorieProduit, int quantite, Produit produit) {
		super();
		this.idDetailProduit = idDetailProduit;
		this.dateCreation = dateCreation;
		this.dateLimiteConsommation = dateLimiteConsommation;
		this.dateDerniereModification = dateDerniereModification;
		this.categorieProduit = categorieProduit;
		this.quantite = quantite;
		this.produit = produit;
	}
	public DetailProduit( Date dateCreation, Date dateLimiteConsommation,
			Date dateDerniereModification, CategorieProduit categorieProduit, int quantite, Produit produit) {
		super();
		this.dateCreation = dateCreation;
		this.dateLimiteConsommation = dateLimiteConsommation;
		this.dateDerniereModification = dateDerniereModification;
		this.categorieProduit = categorieProduit;
		this.quantite = quantite;
		this.produit = produit;
	}

>>>>>>> 37037f1230dc29126282fa3406a9087c60f38c84
	@Override
	public String toString() {
		return "DetailProduit [idDetailProduit=" + idDetailProduit + ", dateCreation=" + dateCreation
				+ ", dateLimiteConsommation=" + dateLimiteConsommation + ", dateDerniereModification="
				+ dateDerniereModification + ", categorieProduit=" + categorieProduit + ", quantite=" + quantite
				+ ", produit=" + produit + "]";
	}
<<<<<<< HEAD
	public DetailProduit(){
		
		
	}
=======
>>>>>>> 37037f1230dc29126282fa3406a9087c60f38c84

	public Long getIdDetailProduit() {
		return idDetailProduit;
	}

	public void setIdDetailProduit(Long idDetailProduit) {
		this.idDetailProduit = idDetailProduit;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Date getDateDerniereModification() {
		return dateDerniereModification;
	}

	public void setDateDerniereModification(Date dateDerniereModification) {
		this.dateDerniereModification = dateDerniereModification;
	}

	public CategorieProduit getCategorieProduit() {
		return categorieProduit;
	}

	public void setCategorieProduit(CategorieProduit categorieProduit) {
		this.categorieProduit = categorieProduit;
	}

	public Date getDateLimiteConsommation() {
		return dateLimiteConsommation;
	}

	public void setDateLimiteConsommation(Date dateLimiteConsommation) {
		this.dateLimiteConsommation = dateLimiteConsommation;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}
	
	
	
<<<<<<< HEAD
}
=======
}
>>>>>>> 37037f1230dc29126282fa3406a9087c60f38c84
