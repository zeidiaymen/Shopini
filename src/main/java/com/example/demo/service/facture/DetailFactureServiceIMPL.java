package com.example.demo.service.facture;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Facture;
import com.example.demo.entity.detailFacture;
import com.example.demo.repository.facture.*;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DetailFactureServiceIMPL implements IDetailFacutre {
	@Autowired
	RepDetailFacutre detFact;
	@Autowired
	RepFacture facture;
@Transactional
	public detailFacture addFact(detailFacture fact, int id) {

		
		
		Facture f = facture.findById(id).orElse(null);
		float subtotal = Float.parseFloat(f.getSubtotal()) + fact.getPrixTotal();
		f.setSubtotal(subtotal);
		f.setShipping(Float.parseFloat(f.getShipping())+4);
		
		
		
		f.setTotal(Float.parseFloat(f.getShipping() ) 
				+ (Float.parseFloat(f.getSubtotal())) 
				+ Float.parseFloat(f.getTax()));
		fact.setFacture(f);
		return detFact.save(fact);
	}

	public detailFacture updateFact(detailFacture fact) {

		return detFact.save(fact);
	}

	@Transactional
	public void deleteFacture(int id) {

		detailFacture e = this.getFactById(id);

		float subtotal = Float.parseFloat(e.getFacture().getSubtotal()) - e.getPrixTotal();
		
		e.getFacture().setShipping(Float.parseFloat(e.getFacture().getShipping()) - 4);
		e.getFacture().setSubtotal(subtotal);
		e.getFacture().setTotal((Float.parseFloat(e.getFacture().getShipping())) +

				(Float.parseFloat(e.getFacture().getSubtotal()) )
				+ Float.parseFloat(e.getFacture().getTax()));

		detFact.deleteById(id);

	}

	public List<detailFacture> getFacts() {

		return detFact.findAll();
	}

	public detailFacture getFactById(int id) {

		return detFact.findById(id).get();
	}

}
