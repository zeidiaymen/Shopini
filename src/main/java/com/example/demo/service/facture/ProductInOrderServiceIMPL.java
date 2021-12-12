package com.example.demo.service.facture;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Facture;
import com.example.demo.entity.ProductInOrder;
import com.example.demo.entity.Produit;
import com.example.demo.entity.detailFacture;
import com.example.demo.repository.facture.*;
import com.example.demo.repository.produit.IProduitRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductInOrderServiceIMPL implements IProductInOrderService {

	@Autowired
	RepProductInOrder prod;
	@Autowired
	IDetailFacutre facture;
	@Autowired
	RepDetailFacutre fact;
	@Autowired
	RepFacture fp;
	@Autowired
	IProduitRepository ps;

	@Transactional
	public ProductInOrder addProd(ProductInOrder p, int idFact,Long idproduit) {
		Produit produit = ps.findById(idproduit).orElse(null);
		produit.setProd(p);
		ps.save(produit);
		detailFacture f = new detailFacture();
		f.setMontanRemise(p.getDiscount());
		f.setPrixTotal(p.getPrice() * p.getQte() - p.getDiscount() * p.getPrice() * p.getQte());
		f.setMontanRemise(p.getPrice() * p.getDiscount() * f.getQte());
		f.setPourcentageRemise((p.getDiscount() * 100));
		f.setQte(p.getQte());
		f.setFacture(fp.findById(idFact).orElse(null));
		facture.addFact(f, idFact);
		p.setDetail(f);
		return prod.save(p);
	}

	public void deleteProductInOrder(int id) {
		// TODO Auto-generated method stub
		ProductInOrder p = prod.findById(id).orElse(null);
		detailFacture f = p.getDetail();
		facture.deleteFacture(f.getIdDetailFacture());
		prod.deleteById(id);

	}

	public List<ProductInOrder> retreiveAllProductsInOrder() {
		// TODO Auto-generated method stub
		return prod.findAll();
	}

	public void deleteAll(int id) {
		Facture fds = fp.findById(id).orElse(null);
		fds.setSubtotal(0);

		fds.setTotal(0);
		fds.setShipping(0);
		fds.setTax(0);

		fp.save(fds);
	
		for ( detailFacture i : fds.getDetailFactures() )
		{
			prod.delete(i.getProds());
		}
		
	}
public int getNbProdIn()
{
return prod.getNbProdInOrder();
}

@Override
public List<ProductInOrder> retreiveAllProdByidClient(String id) {
	// TODO Auto-generated method stub
	return prod.getProdByIdClient(id);
}
}
