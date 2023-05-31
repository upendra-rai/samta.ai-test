package com.demo.service;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.demo.dto.AnswerRequestDto;
import com.demo.dto.QuestionResponseDto;
import com.demo.dto.UpcomingQuestionResponseDto;
import com.demo.entity.Answer;
import com.demo.entity.Question;
import com.demo.repositories.AnswerRepository;
import com.demo.repositories.QuestionRepository;

@Service
public class QuestionServiceImpl implements QuestionService {

	private static final String URL = "https://jservice.io/api/random?count=5";

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private AnswerRepository answerRepository;

	@Override
	public List<QuestionResponseDto> getAllQuestion() {
		// Fetch 5 Random Questions and Store them in database
		getAndSaveQuestions();
		List<Question> questions = questionRepository.findAll();
		return questions.stream().map(q -> {
			var questionResponseDto = new QuestionResponseDto();
			questionResponseDto.setId(q.getId());
			questionResponseDto.setQuestion(q.getQuestion());
			return questionResponseDto;
		}).toList();
	}

	@Override
	public UpcomingQuestionResponseDto saveAnswer(AnswerRequestDto answerRequestDto) {
		var currentQuestion = questionRepository.findByQuestionId(answerRequestDto.getQuestionId()).orElse(null);
		var nextQuestionResponseDto = new UpcomingQuestionResponseDto();
		if (currentQuestion != null) {
			// Create The Response and Save Answer in database
			nextQuestionResponseDto.setCorrectAnswer(answerRequestDto.getAnswer());
			Answer answer = new Answer();
			answer.setQuestionId(answerRequestDto.getQuestionId());
			answer.setAnswer(answerRequestDto.getAnswer());
			// Saving Answer in database
			answerRepository.save(answer);

			// Find The Next Question
			var nextQuestion = findNextQuestion(currentQuestion);
			//If we need to Get More Questions apart from this 5 Question for the next Question
			if (nextQuestion == null) {
				// If No Next Question Then Fetch Again new Questions From API and Store Them in
				// the Database
				getAndSaveQuestions();
				nextQuestion = findNextQuestion(currentQuestion);
			}
			if (nextQuestion != null) {
				nextQuestionResponseDto.setNextQuestionId(nextQuestion.getId());
				nextQuestionResponseDto.setNextQuestion(nextQuestion.getQuestion());
			}
		}
		return nextQuestionResponseDto;
	}

	// This Method functionality is Fetch 5 random questions and Save all Questions
	// in Database
	private void getAndSaveQuestions() {
		var restTemplate = new RestTemplate();
		Question[] questions = restTemplate.getForObject(URL, Question[].class);
		if (questions != null) {
			Arrays.stream(questions).forEach(questionRepository::save);
		}
	}

    //  Method functionality is Find Next Question  from database
	private Question findNextQuestion(Question nextQuestion) {
		return questionRepository.findByIdGreaterThanOrderByIdAsc(nextQuestion.getId()).stream().findFirst()
				.orElse(null);
	}

}
