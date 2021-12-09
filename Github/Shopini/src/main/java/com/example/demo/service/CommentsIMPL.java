package com.example.demo.service;

import java.util.Collections;
import java.util.List;

import org.omg.IOP.TAG_MULTIPLE_COMPONENTS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ClientCommentsDel;
import com.example.demo.repository.RepClient;
import com.example.demo.repository.RepComments;
@Service
public class CommentsIMPL implements ClientCommentsDelService{

		@Autowired
		RepClient client ;
		@Autowired
		RepComments comm ;
		
	@Override
	public ClientCommentsDel addComment(ClientCommentsDel d, String idClient) {
		// TODO Auto-generated method stub
			d.setC(client.findByIds(idClient));
			
		return comm.save(d);
	}

	public  ClientCommentsDel   IncreaseLike(int idComm,String id) {
		//// TODO Auto-generated method stub
	
		ClientCommentsDel comment= comm.findById(idComm).orElse(null);
		comment.getLikesPersons().add(id);
		comment.setLike(comment.getLike()+1);
		return comm.save(comment);
	}
	

	public int numberOfComments() {
		// TODO Auto-generated method stub
		return comm.getCommentsNumber();
	}

	public List<ClientCommentsDel> retComments() {
		// TODO Auto-generated method stub
		return comm.findAll();
	}

	public List<ClientCommentsDel> sortingComments(String sortName) {
		
		List<ClientCommentsDel> list =comm.findAll();
		if (sortName.equals("Popular"))
					{
					list.sort((a,b)->{return b.getLike()-a.getLike();});
					}
					else if (sortName.equals("Oldest"))
							{
						list.sort((a,b)->{return a.getDate().toInstant().compareTo(b.getDate().toInstant());});
							}
					else 
						list.sort((a,b)->{return b.getDate().toInstant().compareTo(a.getDate().toInstant());});
		return list ;
	}

	@Override
	public void deleteComment(int id) {
		comm.deleteById(id);
		
	}

	@Override
	public  ClientCommentsDel  DecreasLike(int idComm, String idClient) {
		// TODO Auto-generated method stub
		ClientCommentsDel comment= comm.findById(idComm).orElse(null);
		comment.getLikesPersons().remove(idClient);
		comment.setLike(comment.getLike()-1);	
		return comm.save(comment);
	}

}
