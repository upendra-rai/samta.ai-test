package com.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpcomingQuestionResponseDto {

	private String correctAnswer;
	private Long nextQuestionId;
	private String nextQuestion;

}
