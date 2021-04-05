package com.nakaradasava.learntogether.repository;

import com.nakaradasava.learntogether.entity.College;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollegeRepository extends JpaRepository<College, Integer> {
}
