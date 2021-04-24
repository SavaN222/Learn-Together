package com.nakaradasava.learntogether.repository.quiz;

import com.nakaradasava.learntogether.entity.quiz.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizQuestionRepository extends JpaRepository<Question, Integer> {
}
