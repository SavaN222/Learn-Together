package com.nakaradasava.learntogether.service.student;

import com.nakaradasava.learntogether.entity.student.Student;
import com.nakaradasava.learntogether.repository.student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService implements UserDetailsService {

    private StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Optional<Student> optionalStudent = studentRepository.findByUsername(username);

        if (optionalStudent.isPresent()) {
            return optionalStudent.get();
        } else {
            throw new UsernameNotFoundException("User with email " + username + " not found :(");
        }
    }

    public Student findByUsernameOrEmail(String username, String email) {
        return studentRepository.findByUsernameOrEmail(username, email);
    }

    public Optional<Student> findStudentById(int studentId) {
        return studentRepository.findById(studentId);
    }

    public Optional<Student> findByUsername(String username) {
        return studentRepository.findByUsername(username);
    }

    public void updateStudent(Student student, Student studentInfo) {
        student.setId(studentInfo.getId());
        student.setEmail(studentInfo.getEmail());
        student.setPassword(studentInfo.getPassword());
        student.setEnabled(studentInfo.isEnabled());
        student.setProfilePic(studentInfo.getProfilePic());
        student.setRole(studentInfo.getRole());
        student.setUniversity(studentInfo.getUniversity());
        student.setStudyField(studentInfo.getStudyField());
        student.setGender(studentInfo.getGender());

        studentRepository.save(student);
    }
}
