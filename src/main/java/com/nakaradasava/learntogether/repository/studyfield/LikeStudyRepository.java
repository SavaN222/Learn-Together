package com.nakaradasava.learntogether.repository.studyfield;

import com.nakaradasava.learntogether.entity.student.Student;
import com.nakaradasava.learntogether.entity.studyfield.LikeStudy;
import com.nakaradasava.learntogether.entity.studyfield.QuestionStudy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeStudyRepository extends JpaRepository<LikeStudy, Integer> {

    Optional<LikeStudy> findByQuestionStudyAndStudent(QuestionStudy questionStudy, Student student);
}
