package com.nakaradasava.learntogether.repository.studyfield;

import com.nakaradasava.learntogether.entity.studyfield.LikeStudy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeStudyRepository extends JpaRepository<LikeStudy, Integer> {
}
