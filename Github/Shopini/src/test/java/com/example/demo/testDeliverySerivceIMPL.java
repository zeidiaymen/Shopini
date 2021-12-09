package com.example.demo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.entity.Client;
import com.example.demo.service.IaddClientForTest;
import com.example.demo.service.addClientForTestIMPL;
@SpringBootTest
@RunWith(SpringRunner.class)
public class testDeliverySerivceIMPL {
	@Autowired
	IaddClientForTest client ;	
@Test
public void addClienttest()
{


Client c = new Client();
c.setId(1);
client.addClient(c);
assertEquals(1, client.getClient(1).getId());


}
}
