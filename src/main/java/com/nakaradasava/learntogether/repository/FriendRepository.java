package com.nakaradasava.learntogether.repository;

import com.nakaradasava.learntogether.entity.Friend;
import com.nakaradasava.learntogether.entity.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Integer> {

    public Optional<Friend> findByStudentLowerAndStudentHigher(Student lowerStudent, Student higherStudent);
}
