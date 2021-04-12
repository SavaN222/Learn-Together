package com.nakaradasava.learntogether.service.studyfield;

import com.nakaradasava.learntogether.entity.student.Student;
import com.nakaradasava.learntogether.entity.studyfield.LikeStudy;
import com.nakaradasava.learntogether.entity.studyfield.QuestionStudy;
import com.nakaradasava.learntogether.repository.studyfield.LikeStudyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikeStudyService {

    private LikeStudyRepository likeStudyRepository;

    @Autowired
    public LikeStudyService(LikeStudyRepository likeStudyRepository) {
        this.likeStudyRepository = likeStudyRepository;
    }

    public void like(LikeStudy like) {
        likeStudyRepository.save(like);
    }

    public void dislike(int id) {
        likeStudyRepository.deleteById(id);
    }

    public Optional<LikeStudy> findByQuestionStudyAndStudent(QuestionStudy questionStudy, Student student) {
        return likeStudyRepository.findByQuestionStudyAndStudent(questionStudy, student);
    }
}
