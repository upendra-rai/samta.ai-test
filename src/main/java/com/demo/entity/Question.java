package com.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "question")
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long qid;
	private Long id;
	private String question;
	private String answer;
	private int value;
	private String airdata;
	private String created_at;
	private String updated_at;
	private int game_id;
	private int invalid_count;

}
