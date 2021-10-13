package com.example.demo.entity;

import javax.persistence.*;
import org.hibernate.annotations.Parameter;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_generator")
	@GenericGenerator(name = "id_generator", strategy = "com.example.demo.entity.IdSequenceGenerator", parameters = {
			@Parameter(name = IdSequenceGenerator.INCREMENT_PARAM,value="1"),
			@Parameter(name = IdSequenceGenerator.VALUE_PREFIX_PARAMETER,value="ERROR"),
			@Parameter(name = IdSequenceGenerator.NUMBER_FORMAT_PARAMETER,value="%04d")

	})
	private String id;

	@Column
	private String firstName;
	@Column
	private String lastName;

	@Column
	private String email;

	@Column
	private String password;
	@Column
	private String sexe;

	@Column
	private String Tel;

	@Column
	private String picture;
	@Column
	private String createdAt;

	@Enumerated(EnumType.STRING)
	private Role role;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getTel() {
		return Tel;
	}

	public void setTel(String tel) {
		Tel = tel;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}	

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", sexe=" + sexe + ", Tel=" + Tel + ", picture=" + picture + ", createdAt="
				+ createdAt + ", role=" + role + "]";
	}

	public User() {

	}

	public User(String firstName, String lastName, String email, String password,String sexe, String tel, String picture,
			String createdAt, Role role) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.sexe=sexe;
		Tel = tel;
		this.picture = picture;
		this.createdAt = createdAt;
		this.role = role;
	}

}
