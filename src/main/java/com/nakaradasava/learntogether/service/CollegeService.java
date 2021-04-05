package com.nakaradasava.learntogether.service;

import com.nakaradasava.learntogether.entity.College;
import com.nakaradasava.learntogether.repository.CollegeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollegeService {

    private CollegeRepository collegeRepository;

    public CollegeService(CollegeRepository collegeRepository) {
        this.collegeRepository = collegeRepository;
    }

    public List<College> findColleges() {
        return collegeRepository.findAll();
    }
}
