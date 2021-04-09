package com.nakaradasava.learntogether.service;

import com.nakaradasava.learntogether.entity.QuestionStudy;
import com.nakaradasava.learntogether.repository.QuestionStudyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionStudyService {

    private QuestionStudyRepository questionStudyRepository;

    @Autowired
    public QuestionStudyService(QuestionStudyRepository questionStudyRepository) {
        this.questionStudyRepository = questionStudyRepository;
    }

    public void saveQuestion(QuestionStudy questionStudy) {
        questionStudyRepository.save(questionStudy);
    }

    public List<QuestionStudy> findQuestionStudiesByStudyFieldId(int studyFieldId) {

        return questionStudyRepository.findQuestionStudiesByStudyFieldId(studyFieldId);
    }
}
