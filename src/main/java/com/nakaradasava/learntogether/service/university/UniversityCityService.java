package com.nakaradasava.learntogether.service.university;

import com.nakaradasava.learntogether.entity.university.UniversityCity;
import com.nakaradasava.learntogether.repository.university.UniversityCityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UniversityCityService {

    private UniversityCityRepository universityCityRepository;

    @Autowired
    public UniversityCityService(UniversityCityRepository universityCityRepository) {
        this.universityCityRepository = universityCityRepository;
    }

    public List<UniversityCity> findCities() {
        return universityCityRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }
}
