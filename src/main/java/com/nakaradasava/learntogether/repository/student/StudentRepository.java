package com.nakaradasava.learntogether.repository.student;

import com.nakaradasava.learntogether.entity.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    Optional<Student> findByUsername(String username);
    Student findByUsernameOrEmail(String username, String email);
}