package com.example.demo.service;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Client;
import com.example.demo.entity.Delivery;
import com.example.demo.entity.Livreur;
import com.example.demo.repository.RepClient;
import com.example.demo.repository.RepDelivery;
import com.example.demo.repository.RepLivreur;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class ServiceDeliveryImpl implements IServiceDelivery{
@Autowired
RepDelivery del ;
@Autowired
RepClient client ;
@Autowired 
RepLivreur livreur ;

private DatabaseReader dbReader;	


public int getDeliveryByIdLivreur (String id )
{
return del.getBydDeliveryID(id);
}

	public ServiceDeliveryImpl() throws IOException {
		File database = new File("your-mmdb-location");
		if (database.equals(null))
			log.error("file is not existing");
		dbReader = new DatabaseReader.Builder(database).build();
}
	public String getLocation (String ip) throws IOException, GeoIp2Exception {

		InetAddress IpAddress = InetAddress.getByName(ip);

		CityResponse response = dbReader.city(IpAddress);

	
		
		String countryName = response.getCountry().getName();

		String latitude = response.getLocation().getLatitude().toString();
		
		String longitude = response.getLocation().getLongitude().toString();
	
		return "{Country:"+countryName+  "," +"latitude:" +latitude+"," +"longitude:" +longitude +"}";
	}
	
	public Delivery addDelivery(Delivery delivery,String idClient) {
	
		Client c = (Client)client.findByIds(idClient);
		delivery.setClientDel(c);
		delivery.setStatus("free");
		delivery.setLivreur(null);
		
		return del.save(delivery) ;
	}
	public Delivery AssociatedToDeliverman(String idLiv,int idDel )
	{
		Delivery d = del .findById(idDel).orElse(null);
		Livreur liv = livreur.findByIds(idLiv);
		d.setLivreur(liv);
		d.setStatus("taken");

		return 	del.save(d);
		 
	}
	public void deleteDelivery(int id) {
		del.deleteById(id);
	}

	public List<Delivery> getDelivries() {
		// TODO Auto-generated method stub
		return del.findAll();
	}

	public Delivery getDeliveryById(int id ) {
		// TODO Auto-generated method stub
		return del.findById(id).get();
	}

	public Delivery UpdateDelivery(Delivery delivery) {
	
		return del.save(delivery);
	}

	@Override
	public List<Delivery> getDeliveryByIdClinet(String id) {
		// TODO Auto-generated method stub
		return del.getDeliveryByIDClient(id);
	}

}
