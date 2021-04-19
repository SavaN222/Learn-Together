package com.nakaradasava.learntogether.service.student;

import com.nakaradasava.learntogether.entity.student.Student;
import com.nakaradasava.learntogether.repository.student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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

    public void updateStudent(Student student, Student studentInfo, MultipartFile profileImage) {
        String studentId = String.valueOf(student.getId());
        String uploadDir = "/images/user-photos/" + studentId + "/";


        student.setId(studentInfo.getId());
        student.setEmail(studentInfo.getEmail());
        student.setPassword(studentInfo.getPassword());
        student.setEnabled(studentInfo.isEnabled());
        String fileName = StringUtils.cleanPath(profileImage.getOriginalFilename()); // get image name for database
        student.setProfilePic(uploadDir + fileName);
        student.setRole(studentInfo.getRole());
        student.setUniversity(studentInfo.getUniversity());
        student.setStudyField(studentInfo.getStudyField());
        student.setGender(studentInfo.getGender());

        studentRepository.save(student);

        try {
            savePicture(profileImage, fileName, studentId);
        } catch (IOException e) {
           e.printStackTrace();
        }
    }

    private void savePicture(MultipartFile profileImage, String fileName, String studentId) throws IOException {
        String windowsPath = "C:\\Users\\Korisnik\\IdeaProjects\\Learn Together\\src\\main\\resources\\static";
        String uploadDir = windowsPath + "\\images\\user-photos\\" + studentId;

        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try(InputStream inputStream = profileImage.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            throw new IOException("Could not save image file " + fileName, ex);
        }
    }
}
