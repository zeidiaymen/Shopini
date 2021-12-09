package com.example.demo.controller;
import java.io.IOException;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entity.Delivery;
import com.example.demo.service.IServiceDelivery;
import com.maxmind.geoip2.exception.GeoIp2Exception;


@RestController
public class ContDelivery {

	@Autowired
	IServiceDelivery del ;

	@GetMapping(value="/getDeliveries")
	public List<Delivery> retrieveDeliveries()
	{
		return del.getDelivries();
	}
	@PostMapping(value="/PostDelivery/{id}")
	@ResponseBody
	public Delivery PostingDelivery(@PathVariable("id") String id ,@RequestBody Delivery delivery)
	{
	return del.addDelivery(delivery, id);
	}
	
@RequestMapping(path="/getLocation/{ip}",method = RequestMethod.GET)
	public ResponseEntity <String>  getLocation (@PathVariable("ip") String ip) throws IOException, GeoIp2Exception
	{

		return ResponseEntity.ok(del.getLocation(ip));
	}
	@GetMapping(value="/getDelivery/{id}")
	public Delivery getDeliveryById(@PathVariable ("id") int id )
	{
		return del.getDeliveryById(id);
	}
	@DeleteMapping (value="/deleteDelivery/{id}")
	public void deleteDelivery (@PathVariable ("id") int id )
	{
		del.deleteDelivery(id);
		System.out.println("delete with sucess");
	}
	@PutMapping(value="updateDelivery")
		@ResponseBody
		public Delivery updateDelivery (@RequestBody Delivery delivery)
	
	{
		return del.UpdateDelivery(delivery);
		}
	@PutMapping (value= "assossiateLivreur/{id}/{idDel}")
	@ResponseBody
	public Delivery assossiateLivreur (@PathVariable ("id") String id ,@PathVariable("idDel") int idD)
	{
		return del.AssociatedToDeliverman (id,idD);
	}
@GetMapping(value="getByIdLivreurForDelivery/{id}")
public int getByIdLivreurForDelivery (@PathVariable ("id") String id )
{
	
	return del.getDeliveryByIdLivreur(id);
}
@GetMapping(value="getDeliveryByIdClient/{id}")
public List<Delivery> getDeliveryByIdC (@PathVariable ("id") String id )
{
	
	return del.getDeliveryByIdClinet(id);
}
}
