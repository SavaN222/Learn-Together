package com.nakaradasava.learntogether.service.studyfield;

import com.nakaradasava.learntogether.entity.studyfield.StudyField;
import com.nakaradasava.learntogether.repository.studyfield.StudyFieldRepository;
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

    public StudyField findStudyFieldById(int studyFieldId) {
        return studyRepository.findStudyFieldById(studyFieldId);
    }
}
