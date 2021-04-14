package com.nakaradasava.learntogether.service.university;

import com.nakaradasava.learntogether.entity.student.Student;
import com.nakaradasava.learntogether.entity.studyfield.LikeStudy;
import com.nakaradasava.learntogether.entity.studyfield.QuestionStudy;
import com.nakaradasava.learntogether.entity.university.LikePost;
import com.nakaradasava.learntogether.entity.university.Post;
import com.nakaradasava.learntogether.repository.university.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikeService {

    private LikeRepository likeRepository;

    @Autowired
    public LikeService(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    public void like(LikePost like) {
        likeRepository.save(like);
    }

    public void dislike(int id) {
        likeRepository.deleteById(id);
    }

    public Optional<LikePost> findByPostAndStudent(Post post, Student student) {
        return likeRepository.findByPostAndStudent(post, student);
    }
}
