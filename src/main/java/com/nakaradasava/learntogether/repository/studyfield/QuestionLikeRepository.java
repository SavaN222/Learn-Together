package com.nakaradasava.learntogether.repository.studyfield;

import com.nakaradasava.learntogether.entity.student.Student;
import com.nakaradasava.learntogether.entity.studyfield.QuestionLike;
import com.nakaradasava.learntogether.entity.studyfield.QuestionStudy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface QuestionLikeRepository extends JpaRepository<QuestionLike, Integer> {

    Optional<QuestionLike> findByQuestionStudyAndStudent(QuestionStudy questionStudy, Student student);

   List<QuestionLike> findAllByStudent(Student student);
}