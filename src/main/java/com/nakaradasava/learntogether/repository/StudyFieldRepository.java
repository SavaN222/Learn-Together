package com.nakaradasava.learntogether.repository;

import com.nakaradasava.learntogether.entity.StudyField;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyFieldRepository extends JpaRepository<StudyField, Integer> {
    StudyField findStudyFieldById(int studyField);
}
