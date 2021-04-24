package com.nakaradasava.learntogether.service.university;

import com.nakaradasava.learntogether.entity.student.Student;
import com.nakaradasava.learntogether.entity.university.UniversityPostLike;
import com.nakaradasava.learntogether.entity.university.UniversityPost;
import com.nakaradasava.learntogether.repository.university.UniversityPostLikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UniversityPostLikeService {

    private UniversityPostLikeRepository universityPostLikeRepository;

    @Autowired
    public UniversityPostLikeService(UniversityPostLikeRepository universityPostLikeRepository) {
        this.universityPostLikeRepository = universityPostLikeRepository;
    }

    public void like(UniversityPostLike like) {
        universityPostLikeRepository.save(like);
    }

    public void dislike(int id) {
        universityPostLikeRepository.deleteById(id);
    }

    public Optional<UniversityPostLike> findByPostAndStudent(UniversityPost universityPost, Student student) {
        return universityPostLikeRepository.findByUniversityPostAndStudent(universityPost, student);
    }

    public List<UniversityPostLike> findStudentFavoritePosts(Student student) {
        return universityPostLikeRepository.findAllByStudent(student);
    }
}