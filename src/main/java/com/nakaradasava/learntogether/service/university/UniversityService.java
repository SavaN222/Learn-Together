package com.nakaradasava.learntogether.service.university;

import com.nakaradasava.learntogether.entity.university.University;
import com.nakaradasava.learntogether.repository.university.UniversityRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UniversityService {

    private UniversityRepository universityRepository;

    public UniversityService(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    public List<University> findUniversities() {

        return universityRepository.findAll(Sort.by(Sort.Direction.ASC, "fullName"));
    }

    public University findUniversity(int id) {
        return universityRepository.findById(id).get();
    }
}