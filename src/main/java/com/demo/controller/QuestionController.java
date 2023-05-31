package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dto.AnswerRequestDto;
import com.demo.dto.QuestionResponseDto;
import com.demo.dto.UpcomingQuestionResponseDto;
import com.demo.service.QuestionService;

@RestController
@RequestMapping("/v1/Question")
public class QuestionController {

	@Autowired
	private QuestionService questionService;

	// This Method Functionality Work is Getting 5 Question from Given Api
	@GetMapping("/list")
	public ResponseEntity<List<QuestionResponseDto>> getAllQuestion() {
		return ResponseEntity.ok(questionService.getAllQuestion());
	}

	// This Method Functionality Work is Saving Answer in database and Give Next Question
	@PostMapping("/answer/next")
	public ResponseEntity<UpcomingQuestionResponseDto> saveAnswer(@RequestBody AnswerRequestDto answerRequestDto) {
		return ResponseEntity.ok(questionService.saveAnswer(answerRequestDto));
	}
}
