package com.example.demo.service.facture;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

import com.example.demo.entity.Delivery;
import com.maxmind.geoip2.exception.GeoIp2Exception;


public interface IServiceDelivery {
	public String getLocation (String ip) throws  IOException, GeoIp2Exception ;
	public Delivery  addDelivery (Delivery delivery,String idClient);
	public void deleteDelivery (int id );
	public List <Delivery> getDelivries ();
	public Delivery getDeliveryById(int id );
	public Delivery UpdateDelivery (Delivery delivery);
	public Delivery AssociatedToDeliverman(String idLiv,int idDel );
	public int getDeliveryByIdLivreur (String id );
	public List<Delivery>getDeliveryByIdClinet(String id );
}
