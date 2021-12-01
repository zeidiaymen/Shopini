package com.example.demo.models;
import lombok.*;



@Data
@Getter
@Setter
@AllArgsConstructor
public class MailRequest {
	
	private String name;
	private String to;
	private String subject;
	private String body;
	private String buttonTitle;
	private String buttonHref;
	
	
	
	
	
}
