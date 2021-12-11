package com.example.demo.controller.facture;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.ProductInOrder;
import com.example.demo.entity.detailFacture;
import com.example.demo.service.facture.*;

@RestController
public class ContProductInOrder {
	
	@Autowired
	IProductInOrderService prod ;
	
	@GetMapping(value="/getNbProdInOrder")
	@ResponseBody
	public int getNbProdIn()
	{
		return prod.getNbProdIn();
	}
	@DeleteMapping(value="/deleteAllProdInOrder/{id}")
	@ResponseBody
	public void deleteAllprodInOrder(@PathVariable("id") int id )
	{
		prod.deleteAll(id);
	}
	
	@GetMapping(value="/getProdInOrder")
	@ResponseBody
	public List<ProductInOrder> retrieveAllprods()
	{
		return prod.retreiveAllProductsInOrder();
	}
	
	@GetMapping(value="/getProdByIdClient/{id}")
	@ResponseBody
	public List<ProductInOrder> getProdByIdC(@PathVariable("id")String id )
	{
		return prod.retreiveAllProdByidClient(id);
	}
	
	@PostMapping(value="/postProdInOrder/{id}")
	@ResponseBody
	public ProductInOrder postProd(@PathVariable("id")int id ,@RequestBody ProductInOrder p )
	{
		return prod.addProd(p,id);
	}
	
	@DeleteMapping(value ="/deleteProdInOrder/{id}")
	public void delelte(@PathVariable("id") int id )
	{
		prod.deleteProductInOrder(id);
	}
	
}
