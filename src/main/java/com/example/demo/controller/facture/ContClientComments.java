package com.example.demo.controller.facture;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.ClientCommentsDel;
import com.example.demo.service.facture.*;

@RestController
public class ContClientComments {
@Autowired
CommentsIMPL cm ;
@Autowired
emlService serv ;
@PostMapping(value="/postComment/{id}")
@ResponseBody
public ClientCommentsDel postComment(@PathVariable("id") String id , @RequestBody ClientCommentsDel comm )
{
	return cm.addComment(comm, id);
}
@GetMapping(value="/sortList/{sort}")
@ResponseBody
public List<ClientCommentsDel> sortComment(@PathVariable("sort") String x)
{
	return cm.sortingComments(x);
}
@GetMapping(value="/nbComments")
@ResponseBody
public int  sortComment()
{
	return cm.numberOfComments();
}
@GetMapping(value="/getComments")
@ResponseBody
public List<ClientCommentsDel>   getComm()
{
	return cm.retComments();
}
@DeleteMapping(value="/deleteComment/{id}")
@ResponseBody
public void deleteComment(@PathVariable("id") int id )
{
	cm.deleteComment(id);
}
@PostMapping(value="increaseLike/{id}/{idC}")
	@ResponseBody
	public  ClientCommentsDel  IncreaseLike(@PathVariable("id")int id,@PathVariable("idC")String idC)
	{
	return 	cm.IncreaseLike(id, idC);
	}

@PostMapping(value="decreaseLike/{id}/{idC}")
@ResponseBody
public  ClientCommentsDel  DecreaseLike(@PathVariable("id")int id,@PathVariable("idC")String idC)
{
	return cm.DecreasLike(id, idC);
}
@PostMapping(value="postMail/{to}/{subject}/{body}")
@ResponseBody
public void sendMail(@PathVariable("to") String to,@PathVariable("subject") String subject,@PathVariable("body") String body,@RequestBody String fileToAttach)throws MailException, MessagingException
{
serv.sendMailWithAttachment(to, subject, body, fileToAttach);
}

}
