package com.example.demo.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
/**
*
* @author ZEIDI AYMEN
*/
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class detailFacture implements Serializable{	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	 int idDetailFacture;
	 int qte;
	 Float prixTotal;
	 float  pourcentageRemise;
	 Float montanRemise;
	 @ManyToOne
	 @JsonIgnore
	  @JoinColumn(name="Fk_facture")
	   Facture facture;
	@OneToOne(mappedBy="detail")
	 ProductInOrder prods ;
}