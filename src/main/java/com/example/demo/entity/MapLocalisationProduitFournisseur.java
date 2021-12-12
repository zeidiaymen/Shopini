package com.example.demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Entity
@Table(name = "MapLocalisationProduitFournisseur")
public class MapLocalisationProduitFournisseur  implements Serializable {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="idGeoLocalisation")
	private Long idGeoLocalisation;
	@NotNull
	private float lat;	
	@NotNull
	private float lng;
	
	@JsonIgnore
	@OneToOne(mappedBy="mapLocalisation")
	private Produit produit;
	
	
	public MapLocalisationProduitFournisseur( float lat, float lng, Produit produit) {
		super();
		this.lat = lat;
		this.lng = lng;
		this.produit = produit;
	}
	
	
}