package com.example.demo.service;

import com.example.demo.entity.ipAddressStore;

public interface IIpAdressService {

	public ipAddressStore addIp(ipAddressStore ip , int idDel);
	public void deleteIp(int idIp);
	public ipAddressStore getByDelId(int idDel);
	public void updateIp(String ip , int id);
	
}
