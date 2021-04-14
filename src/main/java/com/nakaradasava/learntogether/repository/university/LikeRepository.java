package com.nakaradasava.learntogether.repository.university;

import com.nakaradasava.learntogether.entity.student.Student;
import com.nakaradasava.learntogether.entity.university.LikePost;
import com.nakaradasava.learntogether.entity.university.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<LikePost, Integer> {
    Optional<LikePost> findByPostAndStudent(Post post, Student student);

}
