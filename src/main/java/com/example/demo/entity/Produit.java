package com.example.demo.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table( name = "Produit")
public class Produit implements Serializable {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idProduit")
	private Long idProduit;
	private String code;
	private String libelle;
	private float prixUnitaire;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_DetailProduit")
	private DetailProduit detailProduit;
	
	@OneToMany(cascade = CascadeType.ALL)
	private Set<ImagesProduit> imagesProduit;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_GeoLocalisation")
	private MapLocalisationProduitFournisseur mapLocalisation;
	
	@ManyToOne
	private Fournisseur fournisseur;


	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "prod")
	private ProductInOrder prod;
	public Produit(){
		super();
	}
	
	public Produit(Long idProduit, String code, String libelle, float prixUnitaire, ProductInOrder prod,
			DetailProduit detailProduit) {
		super();
		this.idProduit = idProduit;
		this.code = code;
		this.libelle = libelle;
		this.prixUnitaire = prixUnitaire;
		this.prod = prod;
		this.detailProduit = detailProduit;
	}

	public Produit(Long idProduit, String code, String libelle, float prixUnitaire,
			DetailProduit detailProduit, Fournisseur fournisseur, ProductInOrder prod) {
		super();
		this.idProduit = idProduit;
		this.code = code;
		this.libelle = libelle;
		this.prixUnitaire = prixUnitaire;
		this.detailProduit = detailProduit;
		this.fournisseur = fournisseur;
		this.prod = prod;
	}
	public Produit( String code, String libelle, float prixUnitaire,
			DetailProduit detailProduit, Fournisseur fournisseur) {
		super();
		this.code = code;
		this.libelle = libelle;
		this.prixUnitaire = prixUnitaire;
		this.detailProduit = detailProduit;
		this.fournisseur = fournisseur;
	}

	public Produit(String code, String libelle, float prixUnitaire, DetailProduit detailProduit,
			Set<ImagesProduit> imagesProduit, Fournisseur fournisseur) {
		super();
		this.code = code;
		this.libelle = libelle;
		this.prixUnitaire = prixUnitaire;
		this.detailProduit = detailProduit;
		this.imagesProduit = imagesProduit;
		this.fournisseur = fournisseur;
	}
	
	public Produit(String code, String libelle, float prixUnitaire, DetailProduit detailProduit,
			Set<ImagesProduit> imagesProduit, MapLocalisationProduitFournisseur mapLocalisation,
			Fournisseur fournisseur) {
		super();
		this.code = code;
		this.libelle = libelle;
		this.prixUnitaire = prixUnitaire;
		this.detailProduit = detailProduit;
		this.imagesProduit = imagesProduit;
		this.mapLocalisation = mapLocalisation;
		this.fournisseur = fournisseur;
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

	

	public DetailProduit getDetailProduit() {
		return detailProduit;
	}

	public void setDetailProduit(DetailProduit detailProduit) {
		this.detailProduit = detailProduit;
	}

	public ProductInOrder getProd() {
		return prod;
	}

	public void setProd(ProductInOrder prod) {
		this.prod = prod;
	}

	public Fournisseur getFournisseur() {
		return fournisseur;
	}

	public void setFournisseur(Fournisseur fournisseur) {
		this.fournisseur = fournisseur;
	}
	public Set<ImagesProduit> getImagesProduit() {
		return imagesProduit;
	}
	public void setImagesProduit(Set<ImagesProduit> imagesProduit) {
		this.imagesProduit = imagesProduit;
	}
	
	public MapLocalisationProduitFournisseur getMapLocalisation() {
		return mapLocalisation;
	}
	public void setMapLocalisation(MapLocalisationProduitFournisseur mapLocalisation) {
		this.mapLocalisation = mapLocalisation;
	}
	@Override
	public String toString() {
		return "Produit [idProduit=" + idProduit + ", code=" + code + ", libelle=" + libelle + ", prixUnitaire="
				+ prixUnitaire + ", detailProduit=" + detailProduit + ", imagesProduit=" + imagesProduit
				+ ", mapLocalisation=" + mapLocalisation + ", fournisseur=" + fournisseur + ", prod=" + prod + "]";
	}
	
	
}
