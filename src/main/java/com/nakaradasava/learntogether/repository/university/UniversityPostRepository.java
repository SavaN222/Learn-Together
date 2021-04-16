package com.nakaradasava.learntogether.repository.university;

import com.nakaradasava.learntogether.entity.student.Student;
import com.nakaradasava.learntogether.entity.university.UniversityPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UniversityPostRepository extends JpaRepository<UniversityPost, Integer> {
    List<UniversityPost> findAllByStudent(Student student);
}
