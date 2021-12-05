package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class ProductInOrder  implements Serializable{
	@Id
	@GeneratedValue (strategy =GenerationType.IDENTITY)
	private int id ;
	private String productName ;
	private int qte ;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getQte() {
		return qte;
	}
	public void setQte(int qte) {
		this.qte = qte;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public float getDiscount() {
		return discount;
	}
	public void setDiscount(float discount) {
		this.discount = discount;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Produit getProduit() {
		return produit;
	}
	public void setProduit(Produit produit) {
		this.produit = produit;
	}
	public DetailProduit getDetail() {
		return detail;
	}
	public void setDetail(DetailProduit detail) {
		this.detail = detail;
	}
	public ProductInOrder(int id, String productName, int qte, float price, float discount, Date creationDate,
			Produit produit, DetailProduit detail) {
		super();
		this.id = id;
		this.productName = productName;
		this.qte = qte;
		this.price = price;
		this.discount = discount;
		this.creationDate = creationDate;
		this.produit = produit;
		this.detail = detail;
	}
	public ProductInOrder() {
		super();
	}
	private float price ;
	private float discount ;
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	Date creationDate ;
	@OneToOne(mappedBy="prod")
	private Produit produit ;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="fk_detail")
	private DetailProduit detail;
}
