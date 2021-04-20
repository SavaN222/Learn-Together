package com.nakaradasava.learntogether.repository.university;

import com.nakaradasava.learntogether.entity.university.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversityRepository extends JpaRepository<University, Integer> {
}