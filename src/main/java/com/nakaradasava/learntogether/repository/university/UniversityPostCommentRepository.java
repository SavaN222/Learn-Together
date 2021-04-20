package com.nakaradasava.learntogether.repository.university;

import com.nakaradasava.learntogether.entity.university.UniversityPostComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniversityPostCommentRepository extends JpaRepository<UniversityPostComment, Integer> {
}