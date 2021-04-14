package com.nakaradasava.learntogether.repository.university;

import com.nakaradasava.learntogether.entity.university.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
}
