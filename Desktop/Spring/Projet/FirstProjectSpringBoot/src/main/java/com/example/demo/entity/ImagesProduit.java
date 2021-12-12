package com.example.demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ImagesProduit")
public class ImagesProduit implements Serializable  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idImagesProduit")
	private Long idImagesProduit;
	private String ImageName;
	private String url;
	
	
	public ImagesProduit() {
		super();
	}
	
	public ImagesProduit(String imageName, String url) {
		super();
		ImageName = imageName;
		this.url = url;
	}
	public ImagesProduit(Long idImagesProduit, String imageName, String url) {
		super();
		this.idImagesProduit = idImagesProduit;
		ImageName = imageName;
		this.url = url;
	}
	public Long getIdImagesProduit() {
		return idImagesProduit;
	}
	public void setIdImagesProduit(Long idImagesProduit) {
		this.idImagesProduit = idImagesProduit;
	}
	public String getImageName() {
		return ImageName;
	}
	public void setImageName(String imageName) {
		ImageName = imageName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "ImagesProduit [idImagesProduit=" + idImagesProduit + ", ImageName=" + ImageName + ", url=" + url + "]";
	}
	
}
