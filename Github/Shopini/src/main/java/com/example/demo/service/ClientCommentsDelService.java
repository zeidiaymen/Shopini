package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.ClientCommentsDel;

public interface ClientCommentsDelService {

	
	public ClientCommentsDel addComment(ClientCommentsDel d,String idClient );
	public ClientCommentsDel IncreaseLike(int idComm,String idClient);
	public  ClientCommentsDel  DecreasLike(int idComm,String idClient);
	public int numberOfComments();
	public List<ClientCommentsDel>retComments();
	public List<ClientCommentsDel> sortingComments(String sortName);
	public void deleteComment(int id );
	
	
	
	
}
