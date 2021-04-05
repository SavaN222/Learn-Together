package com.nakaradasava.learntogether.service;

import com.nakaradasava.learntogether.entity.ConfirmationToken;
import com.nakaradasava.learntogether.entity.RegistrationStudent;
import com.nakaradasava.learntogether.entity.Student;
import com.nakaradasava.learntogether.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegistrationService {

    private StudentRepository studentRepository;
    private EmailService emailService;
    private ConfirmationTokenService confirmationTokenService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public RegistrationService(StudentRepository studentRepository, EmailService emailService,
                               ConfirmationTokenService confirmationTokenService,
                               BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.studentRepository = studentRepository;
        this.emailService = emailService;
        this.confirmationTokenService = confirmationTokenService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void registerStudent(RegistrationStudent registrationStudent) {

        Student student = new Student();
        student.setUsername(registrationStudent.getUsername());
        student.setEmail(registrationStudent.getEmail());
        student.setPassword(bCryptPasswordEncoder.encode(registrationStudent.getPassword()));
        student.setCollege(registrationStudent.getCollege());
        student.setStudyField(registrationStudent.getStudyField());
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
