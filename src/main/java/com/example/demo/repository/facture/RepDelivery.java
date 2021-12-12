package com.example.demo.repository.facture;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Client;
import com.example.demo.entity.Delivery;
import com.example.demo.entity.Livreur;

public interface RepDelivery extends JpaRepository<Delivery, Integer> {
@Query("SELECT c.id FROM Delivery c WHERE c.livreur.id=?1")
public int getBydDeliveryID(String id);

@Query ("SELECT c FROM Delivery c WHERE c.clientDel.id = ?1")
public List<Delivery> getDeliveryByIDClient(String id );

}
