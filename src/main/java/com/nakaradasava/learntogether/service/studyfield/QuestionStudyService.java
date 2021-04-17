package com.nakaradasava.learntogether.service.studyfield;

import com.nakaradasava.learntogether.entity.student.Student;
import com.nakaradasava.learntogether.entity.studyfield.QuestionStudy;
import com.nakaradasava.learntogether.repository.studyfield.QuestionStudyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

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

        return questionStudyRepository.findQuestionStudiesByStudyFieldIdOrderByIdDesc(studyFieldId);
    }

    public QuestionStudy findById(int questionId) {
        return questionStudyRepository.findById(questionId).get();
    }

    public void deleteById(int id) {
        questionStudyRepository.deleteById(id);
    }

    public List<QuestionStudy> findQuestionsByStudent(Student student) {
        return questionStudyRepository.findQuestionStudiesByStudentOrderByIdDesc(student);
    }
}
