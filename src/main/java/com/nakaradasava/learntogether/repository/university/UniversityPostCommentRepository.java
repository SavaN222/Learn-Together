package com.nakaradasava.learntogether.repository.university;

import com.nakaradasava.learntogether.entity.CommentStatus;
import com.nakaradasava.learntogether.entity.student.Student;
import com.nakaradasava.learntogether.entity.studyfield.QuestionComment;
import com.nakaradasava.learntogether.entity.university.UniversityPostComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UniversityPostCommentRepository extends JpaRepository<UniversityPostComment, Integer> {
    List<UniversityPostComment> findAllByStatusAndStudentNotOrderById(CommentStatus commentStatus, Student student);

}