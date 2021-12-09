package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.detailFacture;

public interface IDetailFacutre {
	public detailFacture addFact(detailFacture fact,int id);
	public detailFacture updateFact (detailFacture fact );
	public void deleteFacture (int id );
	public List<detailFacture> getFacts();
	public detailFacture getFactById(int id );
		

}
