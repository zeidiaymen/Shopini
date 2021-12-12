package com.example.demo.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.http.client.utils.DateUtils;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/**
*
* @author ZEIDI AYMEN
*/
@Data
@FieldDefaults(level=AccessLevel.PRIVATE)
@Entity
@Table(name="delivery")
public class Delivery implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	 int ID ;
	@Column (name ="ClientName")
	 String Name ;
	 String CellPhone ;
	 String Address ;
     String status ;
	@Enumerated(EnumType.STRING)
	@Column(name="weight")
	 DeliveryWeightCategory Weight;

	@Column (name ="EstimatedTime")
	  LocalDateTime EstimatedTime =LocalDateTime.now().plusDays(2);
	@OneToOne(mappedBy="del")
	ipAddressStore store ;
	
	@ManyToOne
	@JoinColumn(name="id_client")
	 Client clientDel ;
	
	@OneToOne
	@JoinColumn(name="id_livreur")
	 Livreur livreur ;
}