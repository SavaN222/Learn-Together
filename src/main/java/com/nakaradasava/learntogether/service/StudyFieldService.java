package com.nakaradasava.learntogether.service;

import com.nakaradasava.learntogether.entity.StudyField;
import com.nakaradasava.learntogether.repository.StudyFieldRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudyFieldService {

    private StudyFieldRepository studyRepository;

    public StudyFieldService(StudyFieldRepository studyRepository) {
        this.studyRepository = studyRepository;
    }

    public List<StudyField> findStudyFields() {
        return studyRepository.findAll();
    }
}