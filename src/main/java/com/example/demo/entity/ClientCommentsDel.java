package com.example.demo.entity;

import java.io.Serializable;
import java.util.*;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
@Data
@FieldDefaults (level=AccessLevel.PRIVATE)
@Entity
@Table(name="comments")
public class ClientCommentsDel implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idComments")
	int id ;
	@Column(name="comments")
	String comments ;
	@Column(name="likes")
	int like ;
	
	@ElementCollection(targetClass=String.class)
	// a changer vers String !! 
	Set<String>likesPersons;
	
	@ManyToOne
	@JoinColumn(name="ClientID")
	Client c ;
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	Date date ;
	

}
