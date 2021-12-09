package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;


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

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class ProductInOrder  implements Serializable{
	@Id
	@GeneratedValue (strategy =GenerationType.IDENTITY)
	 int id ;
	 String productName ;
	 int qte ;
	 float price ;
	 float discount ;
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	Date creationDate ;
	@OneToOne(mappedBy="prod")
	 Produit produit ;
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="fk_detail")
	 detailFacture detail ;
}
