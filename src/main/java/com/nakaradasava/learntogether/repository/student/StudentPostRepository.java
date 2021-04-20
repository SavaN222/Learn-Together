package com.nakaradasava.learntogether.repository.student;

import com.nakaradasava.learntogether.entity.student.StudentPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentPostRepository extends JpaRepository<StudentPost, Integer> {
}