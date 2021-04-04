package com.nakaradasava.learntogether.service;

import com.nakaradasava.learntogether.entity.ConfirmationToken;
import com.nakaradasava.learntogether.entity.Student;
import com.nakaradasava.learntogether.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService implements UserDetailsService {

    private StudentRepository studentRepository;
    private EmailService emailService;
    private ConfirmationTokenService confirmationTokenService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public StudentService(StudentRepository studentRepository, EmailService emailService,
                          ConfirmationTokenService confirmationTokenService,
                          BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.studentRepository = studentRepository;
        this.emailService = emailService;
        this.confirmationTokenService = confirmationTokenService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final Optional<Student> optionalStudent = studentRepository.findByEmail(email);

        if (optionalStudent.isPresent()) {
            return optionalStudent.get();
        } else {
            throw new UsernameNotFoundException("User with email " + email + " not found :(");
        }
    }

    public void registerStudent(Student student) {
        student.setPassword(bCryptPasswordEncoder.encode(student.getPassword()));
        student.setRole("student");
        studentRepository.save(student);

        ConfirmationToken confirmationToken = new ConfirmationToken(student);
        confirmationTokenService.saveConfirmationToken(confirmationToken);

        sendConfirmationMail(student.getEmail(), confirmationToken.getConfirmationToken());

    }

    public void confirmRegistration(ConfirmationToken confirmationToken) {
        Student student = confirmationToken.getStudent();

        student.setEnabled(true);

        studentRepository.save(student);

        confirmationTokenService.deleteConfirmationToken(confirmationToken.getId());
    }

    private void sendConfirmationMail(String userMail, String token) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setTo(userMail);
        mailMessage.setSubject("Learn Together - Confirm Registration");
        mailMessage.setFrom("jessepickman321@gmail.com");
        mailMessage.setText(
                "Mvica ti je dozvolila da se registrujes na njen sajt ZATO POZURI I KLIKNI NA LIK ODMA!." + "http://localhost:8080/register/confirm?token="
                        + token);
        emailService.sendEmail(mailMessage);
    }
}
