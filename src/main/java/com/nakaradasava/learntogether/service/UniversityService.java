package com.nakaradasava.learntogether.service;

import com.nakaradasava.learntogether.entity.University;
import com.nakaradasava.learntogether.repository.UniversityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UniversityService {

    private UniversityRepository universityRepository;

    public UniversityService(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    public List<University> findUniversities() {
        return universityRepository.findAll();
    }

    public University findUniversity(int id) {
        return universityRepository.findById(id).get();
    }
}
