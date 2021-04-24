package com.nakaradasava.learntogether.repository.quiz;

import com.nakaradasava.learntogether.entity.quiz.Quiz;
import com.nakaradasava.learntogether.entity.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer> {

    List<Quiz> findAllByStudent(Student student);
}
