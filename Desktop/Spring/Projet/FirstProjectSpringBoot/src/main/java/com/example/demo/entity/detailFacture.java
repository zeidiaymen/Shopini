package com.example.demo.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
/**
*
* @author ZEIDI AYMEN
*/

@Entity
public class detailFacture implements Serializable{	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long idDetailFacture;
	private int qte;
	private Float prixTotal;
	private int pourcentageRemise;
	private Float montanRemise;
	 @ManyToOne(cascade=CascadeType.ALL)
	  @JoinColumn(name="Fk_facture")
	  private Facture facture;
	@OneToMany(mappedBy="detail")
	private List<ProductInOrder> prods ;

	public Long getIdDetailFacture() {
		return idDetailFacture;
	}

	public detailFacture(Long idDetailFacture, int qte, Float prixTotal, int pourcentageRemise, Float montanRemise,
			Facture facture, List<ProductInOrder> prods) {
		super();
		this.idDetailFacture = idDetailFacture;
		this.qte = qte;
		this.prixTotal = prixTotal;
		this.pourcentageRemise = pourcentageRemise;
		this.montanRemise = montanRemise;
		this.facture = facture;
		this.prods = prods;
	}

	public void setIdDetailFacture(Long idDetailFacture) {
		this.idDetailFacture = idDetailFacture;
	}

	public Facture getFacture() {
		return facture;
	}

	public void setFacture(Facture facture) {
		this.facture = facture;
	}

	
	public detailFacture() {
		super();
		// TODO Auto-generated constructor stub
	}
	public List<ProductInOrder> getProds() {
		return prods;
	}

	public void setProds(List<ProductInOrder> prods) {
		this.prods = prods;
	}

	public int getQte() {
		return qte;
	}

	public void setQte(int qte) {
		this.qte = qte;
	}

	public Float getPrixTotal() {
		return prixTotal;
	}

	public void setPrixTotal(Float prixTotal) {
		this.prixTotal = prixTotal;
	}

	public int getPourcentageRemise() {
		return pourcentageRemise;
	}

	public void setPourcentageRemise(int pourcentageRemise) {
		this.pourcentageRemise = pourcentageRemise;
	}

	public Float getMontanRemise() {
		return montanRemise;
	}

	public void setMontanRemise(Float montanRemise) {
		this.montanRemise = montanRemise;
	}

	@Override
	public String toString() {
		return "detailFacture [idDetailFacture=" + idDetailFacture + ", qte=" + qte + ", prixTotal=" + prixTotal
				+ ", pourcentageRemise=" + pourcentageRemise + ", montanRemise=" + montanRemise + "]";
	}
	
}
