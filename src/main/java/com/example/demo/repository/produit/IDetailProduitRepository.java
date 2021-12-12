package com.example.demo.repository.produit;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.DetailProduit;


public interface IDetailProduitRepository extends JpaRepository<DetailProduit,Long>  {

}
