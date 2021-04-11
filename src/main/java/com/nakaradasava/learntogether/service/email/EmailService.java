package com.nakaradasava.learntogether.service.email;

import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class EmailService {

    private JavaMailSender javaMailSender;

    /**
     * Send mail for confirmation token to new register student
     * @param to student mail recipient
     * @param email content of email - html page
     */
    @Async
    public void sendEmail(String to, String email) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(email, true);
            helper.setTo(to);
            helper.setSubject("Learn Together - Confirm your mail");
            helper.setFrom("jessepickman321@gmail.com");
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new IllegalStateException("Faile to send mail");
        }
    }
}
