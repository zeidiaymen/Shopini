package com.example.demo.repository.facture;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.ProductInOrder;

public interface RepProductInOrder extends JpaRepository<ProductInOrder, Integer> {

	@Query ("SELECT count(*) FROM ProductInOrder c" )
	public int getNbProdInOrder();
	@Query("SELECT c FROM ProductInOrder c WHERE c.detail.facture.client.id = ?1")
	
	public List<ProductInOrder>getProdByIdClient(String id );
	
}
