package com.example.demo.controller.facture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.ipAddressStore;
import com.example.demo.service.facture.*;

@RestController
public class ContIpAddressStore {

	@Autowired
	IIpAdressService serv ;
	
	@PostMapping(value="/postIp/{id}")
	@ResponseBody
	public ipAddressStore addIp(@RequestBody ipAddressStore ip,@PathVariable("id") int id )
	{
		return serv.addIp(ip, id);
	}
	@GetMapping(value="/getIp/{id}")
	public ipAddressStore getIp(@PathVariable("id") int id )
	{
		return serv.getByDelId(id);
	}
	@GetMapping(value ="/updateIp/{ip}/{id}")
	public void updByIdDel(@PathVariable("ip") String ip,@PathVariable("id") int id  )
	{
		serv.updateIp(ip, id);
	}
}
