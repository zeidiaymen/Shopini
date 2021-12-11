package com.example.demo.repository.facture;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.detailFacture;

public interface RepDetailFacutre extends JpaRepository<detailFacture, Integer> {

}
