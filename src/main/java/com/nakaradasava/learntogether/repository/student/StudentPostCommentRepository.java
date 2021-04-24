package com.nakaradasava.learntogether.repository.student;

import com.nakaradasava.learntogether.entity.student.StudentPostComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentPostCommentRepository extends JpaRepository<StudentPostComment, Integer> {

    @Query("SELECT comments FROM StudentPostComment comments LEFT JOIN StudentPost sp ON comments.studentPost.id = sp.id" +
            " WHERE comments.status = 'UNSEEN' AND comments.student.id <> ?1 AND sp.student.id = ?1")
    List<StudentPostComment> findCommentsNotification(int studentId);
}
