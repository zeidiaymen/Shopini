package com.example.demo.entity;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;
import lombok.experimental.FieldDefaults;

@SuppressWarnings("serial")
@Entity
@Table(name = "reclamation")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Reclamation implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	int id;
	@NonNull
	@Column(columnDefinition = " varchar(50) ", nullable = false)
	String category;
	@NonNull
	@Column(columnDefinition = " varchar(50) ", nullable = false)
	String content;
	@Column(columnDefinition = " varchar(50) ")
	String createdAt;
	@Column(columnDefinition = " varchar(255) ")
	String modifiedAt;
	@Column(columnDefinition = " varchar(255) ")
	String status;
	@NonNull
	//@Column(columnDefinition = " varchar(255) ", nullable = false)
	@ManyToOne(cascade = CascadeType.ALL)
	//@JoinColumn(name="produit")
	@JsonIgnore
	Produit produit;
	@NonNull
	//@Column(columnDefinition = " varchar(255) ", nullable = false)
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	@JsonIgnore
	User user;
}
