package com.example.demo.entity;

import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@ToString
public class PasswordHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "passwordHistoryId_generator")
	Integer id;

	@ManyToOne(cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
	@NonNull
	User user;
	@Column
	@NonNull
	String password;
	@Column
	@NonNull
	String createdAt;

}
