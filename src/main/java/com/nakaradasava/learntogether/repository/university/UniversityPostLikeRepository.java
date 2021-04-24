package com.nakaradasava.learntogether.repository.university;

import com.nakaradasava.learntogether.entity.student.Student;
import com.nakaradasava.learntogether.entity.university.UniversityPostLike;
import com.nakaradasava.learntogether.entity.university.UniversityPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface UniversityPostLikeRepository extends JpaRepository<UniversityPostLike, Integer> {

    Optional<UniversityPostLike> findByUniversityPostAndStudent(UniversityPost universityPost, Student student);

    List<UniversityPostLike> findAllByStudent(Student student);
}