package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.ClientCommentsDel;

public interface RepComments extends JpaRepository<ClientCommentsDel, Integer>{

	@Query("SELECT count(*) FROM ClientCommentsDel c")
	public int getCommentsNumber ( );
}
