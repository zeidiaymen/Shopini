package com.example.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entity.Client;
import com.example.demo.repository.RepClient;
import com.example.demo.service.IaddClientForTest;

@RestController
public class ClientTestController {
@Autowired
IaddClientForTest client ;
@Autowired
RepClient c;

@DeleteMapping (value ="d/{id}")
public void delete (@PathVariable ("id") int id )
{
	c.deleteById(id);
	System.out.println("ok");
}
@PostMapping(value ="/postClient")
@ResponseBody
public Client addClient (@RequestBody Client c )
{
	return client.addClient(c);
}
@GetMapping(value="/get/{id}")
public Client getclient(@PathVariable ("id") String id )
{
	return client.getClient(id);
}
}
