package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.ImagesProduit;

public interface IImagesProduitRepository  extends JpaRepository<ImagesProduit,Long> {

}
