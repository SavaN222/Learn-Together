package com.nakaradasava.learntogether.repository;

import com.nakaradasava.learntogether.entity.QuestionStudy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionStudyRepository extends JpaRepository<QuestionStudy, Integer> {

    List<QuestionStudy> findQuestionStudiesByStudyFieldIdOrderByIdDesc(int studyFieldId);

}
