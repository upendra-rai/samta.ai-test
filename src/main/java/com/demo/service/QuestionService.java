package com.demo.service;

import java.util.List;

import com.demo.dto.AnswerRequestDto;
import com.demo.dto.QuestionResponseDto;
import com.demo.dto.UpcomingQuestionResponseDto;

public interface QuestionService {

	List<QuestionResponseDto> getAllQuestion();

	UpcomingQuestionResponseDto saveAnswer(AnswerRequestDto answerRequestDto);

}
