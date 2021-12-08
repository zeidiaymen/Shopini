package com.example.demo.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@SuppressWarnings("serial")
@Data
@Entity

@Table(name = "userPasswordRequest")
@FieldDefaults(level=AccessLevel.PRIVATE)
public class UserPasswordRequest implements Serializable {
	
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	  int id;
	

	@ManyToOne()
    @JoinColumn(name="userId", nullable=false)

	  User user;
	@Column(name="token")
	 String token;	
	
	@Column(columnDefinition="varchar(16)")
	 String createdAt;

}
