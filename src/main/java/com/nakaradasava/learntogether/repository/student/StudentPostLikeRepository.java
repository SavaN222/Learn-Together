package com.nakaradasava.learntogether.repository.student;

import com.nakaradasava.learntogether.entity.student.Student;
import com.nakaradasava.learntogether.entity.student.StudentPost;
import com.nakaradasava.learntogether.entity.student.StudentPostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentPostLikeRepository extends JpaRepository<StudentPostLike, Integer> {
    Optional<StudentPostLike> findByStudentPostAndStudent(StudentPost studentPost, Student student);
}