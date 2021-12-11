package com.example.demo.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy.Strategy;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@FieldDefaults(level=AccessLevel.PRIVATE)

public class ipAddressStore implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	 int Id ;
	String ipaddress ;
	@OneToOne
	@JsonIgnore
	@JoinColumn(name="mapper")
	Delivery del;	

}
