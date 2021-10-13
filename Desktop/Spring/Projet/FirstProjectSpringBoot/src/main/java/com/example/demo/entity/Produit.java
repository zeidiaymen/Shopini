package com.example.demo.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
	
	@OneToOne
	@JoinColumn(name="FK_DetailProduit")
	private DetailProduit detailProduit;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id_detailFacture")
	private detailFacture detail;
	
	public Produit(Long idProduit, String code, String libelle, float prixUnitaire) {
		super();
		this.idProduit = idProduit;
		this.code = code;
		this.libelle = libelle;
		this.prixUnitaire = prixUnitaire;
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
