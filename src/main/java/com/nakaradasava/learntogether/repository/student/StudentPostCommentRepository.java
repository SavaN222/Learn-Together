package com.nakaradasava.learntogether.repository.student;

import com.nakaradasava.learntogether.entity.CommentStatus;
import com.nakaradasava.learntogether.entity.student.Student;
import com.nakaradasava.learntogether.entity.student.StudentPostComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentPostCommentRepository extends JpaRepository<StudentPostComment, Integer> {

    List<StudentPostComment> findAllByStatusAndStudentNot(CommentStatus commentStatus, Student student);
}
