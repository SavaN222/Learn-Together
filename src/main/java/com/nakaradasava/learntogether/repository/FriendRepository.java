package com.nakaradasava.learntogether.repository;

import com.nakaradasava.learntogether.entity.Friend;
import com.nakaradasava.learntogether.entity.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Integer> {
    Optional<Friend> findByStudentLowerAndStudentHigher(Student lowerStudent, Student higherStudent);


    @Query("SELECT studentLower FROM Friend WHERE student_higher_id = ?1 AND status = 'ACCEPT'")
    Optional<List<Student>> findLowerFriends(int studentId);

    @Query("SELECT studentHigher FROM Friend WHERE student_lower_id = ?1 AND status = 'ACCEPT'")
    Optional<List<Student>> findHigherFriends(int studentId);
}