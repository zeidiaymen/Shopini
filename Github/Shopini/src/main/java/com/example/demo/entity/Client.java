package com.example.demo.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;


import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
@Data
@FieldDefaults(level=AccessLevel.PRIVATE)
@SuppressWarnings("serial")
@DiscriminatorValue("CLIENT")
@Entity
public class Client extends User implements Serializable {

	 Profession profession;
	 CategorieClient categorieClient;
	
	@OneToMany(mappedBy="client",cascade=CascadeType.ALL)
@JsonIgnore
	 List<Facture>facture ;
	
	 
	@OneToMany(mappedBy="clientDel",cascade=CascadeType.ALL)
	@JsonIgnore
	List<Delivery>deliveries;
	@OneToMany (mappedBy="c",cascade=CascadeType.ALL)
	@JsonIgnore
	List<ClientCommentsDel> comments ;

}
