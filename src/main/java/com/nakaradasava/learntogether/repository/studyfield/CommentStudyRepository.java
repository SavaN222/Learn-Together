package com.nakaradasava.learntogether.repository.studyfield;

import com.nakaradasava.learntogether.entity.studyfield.CommentStudy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentStudyRepository extends JpaRepository<CommentStudy, Integer> {
}
