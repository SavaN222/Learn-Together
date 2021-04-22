package com.nakaradasava.learntogether.repository.student;

import com.nakaradasava.learntogether.entity.student.StudentPostComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentPostCommentRepository extends JpaRepository<StudentPostComment, Integer> {
}
