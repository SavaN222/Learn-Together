package com.nakaradasava.learntogether.repository.university;

import com.nakaradasava.learntogether.entity.CommentStatus;
import com.nakaradasava.learntogether.entity.student.Student;
import com.nakaradasava.learntogether.entity.studyfield.QuestionComment;
import com.nakaradasava.learntogether.entity.university.UniversityPostComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UniversityPostCommentRepository extends JpaRepository<UniversityPostComment, Integer> {

    @Query("SELECT comments FROM UniversityPostComment comments LEFT JOIN UniversityPost up ON comments.universityPost.id = up.id" +
            " WHERE comments.status = 'UNSEEN' AND comments.student.id <> ?1 AND up.student.id = ?1")
    List<UniversityPostComment> findComments(int studentId);
}