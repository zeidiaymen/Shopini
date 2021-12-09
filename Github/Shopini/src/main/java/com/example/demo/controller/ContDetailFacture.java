package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.detailFacture;
import com.example.demo.service.IDetailFacutre;

@RestController
public class ContDetailFacture {
@Autowired
IDetailFacutre detail ;


@GetMapping(value="/getDetailFacture")
public List<detailFacture>getListDetailFacutre()
{
	return detail.getFacts();
}
@PostMapping(value="/postDetailFacture/{id}")
@ResponseBody
public detailFacture postDetailFact(@RequestBody detailFacture fact , @PathVariable("id") int id )
{
	return detail.addFact(fact, id);	
}
@DeleteMapping(value="/deleteDetailFacture/{id}")
public void deleteDetailFacture (@PathVariable("id") int id )
{
	detail.deleteFacture(id);
	System.out.println("deleted with success ! test message");
}
@PutMapping(value="/updateDetailFacture")
@ResponseBody
public detailFacture updateDetailFacture (@RequestBody detailFacture fact)
{
	return detail.updateFact(fact);
}
@GetMapping(value="/getDetailFact/{id}")
public detailFacture getDetailFactById(@PathVariable("id") int id )
{
	return detail.getFactById(id);
	
}
}
