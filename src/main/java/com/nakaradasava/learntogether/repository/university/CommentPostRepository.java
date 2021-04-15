package com.nakaradasava.learntogether.repository.university;

import com.nakaradasava.learntogether.entity.university.CommentPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentPostRepository extends JpaRepository<CommentPost, Integer> {
}
