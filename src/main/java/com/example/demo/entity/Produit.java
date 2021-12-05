package com.example.demo.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table( name = "Produit")
public class Produit implements Serializable {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="idProduit")
	private Long idProduit; 
	private String code;
	private String libelle;
	private String picture;
	private float prixUnitaire;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="prod")
	private ProductInOrder prod ;
	public ProductInOrder getProd() {
		return prod;
	}

	public void setProd(ProductInOrder prod) {
		this.prod = prod;
	}

	@OneToOne
	@JoinColumn(name="FK_DetailProduit")
	private DetailProduit detailProduit;
	
	
	

	public Produit(Long idProduit, String code, String libelle, String picture, float prixUnitaire, ProductInOrder prod,
			DetailProduit detailProduit) {
		super();
		this.idProduit = idProduit;
		this.code = code;
		this.libelle = libelle;
		this.picture = picture;
		this.prixUnitaire = prixUnitaire;
		this.prod = prod;
		this.detailProduit = detailProduit;
	}
	
	public Produit() {
	}

	public Long getIdProduit() {
		return idProduit;
	}

	public void setIdProduit(Long idProduit) {
		this.idProduit = idProduit;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public float getPrixUnitaire() {
		return prixUnitaire;
	}

	public void setPrixUnitaire(float prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}
	
	

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public DetailProduit getDetailProduit() {
		return detailProduit;
	}

	public void setDetailProduit(DetailProduit detailProduit) {
		this.detailProduit = detailProduit;
	}

	@Override
	public String toString() {
		return "Produit [idProduit=" + idProduit + ", code=" + code + ", libelle=" + libelle + ", prixUnitaire="
				+ prixUnitaire + "]";
	}
	
	
	
}
