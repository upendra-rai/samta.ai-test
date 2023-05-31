package com.demo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AnswerRequestDto {

	private Long questionId;
	private String answer;

}
