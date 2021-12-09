package com.example.demo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.ipAddressStore;
import com.example.demo.repository.RepDelivery;
import com.example.demo.repository.RepIpStore;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class IpAddressServiceIMPL implements IIpAdressService {
@Autowired
RepIpStore ipAd ;
@Autowired
RepDelivery del ;
	public ipAddressStore addIp(ipAddressStore ip, int idDel) {
		
		ip.setDel(del.findById(idDel).orElse(null));
		
		return ipAd.save(ip);
	}
	@Transactional
	public void  updateIp(String ip , int id)
	{
		ipAd.updateByIdDel(ip, id);
	}
	public void deleteIp(int idIp) {
		// TODO Auto-generated method stub
		ipAd.deleteById(idIp);
	}
	public ipAddressStore getByDelId(int idDel) {
		// TODO Auto-generated method stub
		return ipAd.getByDeliveryId(idDel);
	}
}
