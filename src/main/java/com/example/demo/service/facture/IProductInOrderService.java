package com.example.demo.service.facture;

import java.util.List;

import com.example.demo.entity.ProductInOrder;
import com.example.demo.entity.detailFacture;

public interface IProductInOrderService {
	
	
	public ProductInOrder addProd(ProductInOrder p,int idD);
	public void deleteProductInOrder(int id );
	public List<ProductInOrder>retreiveAllProductsInOrder();
	public void deleteAll(int id) ;
	public int getNbProdIn();
	public List<ProductInOrder>retreiveAllProdByidClient(String id );
}
