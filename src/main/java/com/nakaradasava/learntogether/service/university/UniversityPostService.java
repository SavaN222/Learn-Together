package com.nakaradasava.learntogether.service.university;

import com.nakaradasava.learntogether.entity.student.Student;
import com.nakaradasava.learntogether.entity.university.UniversityPost;
import com.nakaradasava.learntogether.repository.university.UniversityPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UniversityPostService {

    private UniversityPostRepository universityPostRepository;

    @Autowired
    public UniversityPostService(UniversityPostRepository universityPostRepository) {
        this.universityPostRepository = universityPostRepository;
    }

    public void savePost(UniversityPost universityPost) {
        universityPostRepository.save(universityPost);
    }

    public UniversityPost findPostById(int id) {
        return universityPostRepository.findById(id).get();
    }

    public List<UniversityPost> findPostsByStudent(Student student) {
        return universityPostRepository.findAllByStudent(student);
    }

    public void deletePostById(int id) {
       universityPostRepository.deleteById(id);
    }
}