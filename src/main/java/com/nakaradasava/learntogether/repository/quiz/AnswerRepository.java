package com.nakaradasava.learntogether.repository.quiz;

import com.nakaradasava.learntogether.entity.quiz.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer> {
}
