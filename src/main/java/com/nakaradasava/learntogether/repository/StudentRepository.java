package com.nakaradasava.learntogether.repository;

import com.nakaradasava.learntogether.entity.Student;
import com.nakaradasava.learntogether.entity.StudentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    Optional<StudentDetails> findByEmail(String email);
}
