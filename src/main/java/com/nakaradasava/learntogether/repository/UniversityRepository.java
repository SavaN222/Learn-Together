package com.nakaradasava.learntogether.repository;

import com.nakaradasava.learntogether.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UniversityRepository extends JpaRepository<University, Integer> {
}
