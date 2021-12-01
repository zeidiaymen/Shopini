package com.example.demo.entity;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Parameter;
import lombok.*;
import lombok.experimental.FieldDefaults;

import org.hibernate.annotations.GenericGenerator;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role")
@DiscriminatorValue("USER")
@RequiredArgsConstructor
@NoArgsConstructor
@FieldDefaults(level=AccessLevel.PRIVATE)



public class User implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_generator")
	@GenericGenerator(name = "id_generator", strategy = "com.example.demo.entity.IdSequenceGenerator", parameters = {
			@Parameter(name = IdSequenceGenerator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = IdSequenceGenerator.VALUE_PREFIX_PARAMETER, value = "ERROR"),
			@Parameter(name = IdSequenceGenerator.NUMBER_FORMAT_PARAMETER, value = "%04d") })
	  String id;
	@NonNull
	@Column(columnDefinition = " varchar(20) ",nullable=false)
	  String firstName;
	@NonNull
	@Column(columnDefinition = " varchar(20) ",nullable=false)
	 String lastName;
	@NonNull
	@Column(columnDefinition = " varchar(50) ",nullable=false,unique=true)
	 String email;
	@NonNull
	@Column(columnDefinition = " varchar(255) ",nullable=false)
	 String password;
	@NonNull
	@Column(columnDefinition = " varchar(8) ",nullable=false)
	 String sexe;
	@NonNull
	@Column(columnDefinition = " varchar(8) ",nullable=false)
	 String tel;
	@NonNull
	@Column(columnDefinition = " varchar(255) ",nullable=false)
	 String picture;
	@Column(columnDefinition="varchar(16)")
	 String createdAt;
	@Column(columnDefinition = " varchar(20) ",nullable=false)
	 String address;
	@Column(columnDefinition = " varchar(25) default 'NOT_VERIFIED'",nullable=false)
	 String accountStatus="NOT_VERIFIED";
	@Column(columnDefinition = " varchar(255) ",nullable=true)
	 String activationToken;
	@Column(columnDefinition = "boolean default false",nullable=false)
	 Boolean twoFactorAuthentication=false;
	@Column(name = "role", insertable = false, updatable = false,nullable=false)
	 String role;
	@Transient
	String confirmPassword;
	@Transient
	String reCaptcha;

	

	

	

	


}
