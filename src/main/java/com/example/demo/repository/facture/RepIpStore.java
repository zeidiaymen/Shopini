package com.example.demo.repository.facture;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.ipAddressStore;

public interface RepIpStore extends JpaRepository<ipAddressStore, Integer>{
@Query("SELECT c FROM ipAddressStore c WHERE c.del.ID =?1")
public ipAddressStore getByDeliveryId(int id );
@Modifying
@Query("UPDATE ipAddressStore c SET c.ipaddress = ?1 WHERE del.id = ?2")
public void updateByIdDel(String ip , int id );
}
