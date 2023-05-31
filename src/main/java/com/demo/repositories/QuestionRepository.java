package com.demo.repositories;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.entity.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

	Collection<Question> findByIdGreaterThanOrderByIdAsc(Long id);

	@Query(" SELECT q From Question q where q.id=:questionId")
	Optional<Question> findByQuestionId(Long questionId);

}
