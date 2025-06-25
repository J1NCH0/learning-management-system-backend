package com.lms.core.service;

import com.lms.core.entity.Mail;
import com.lms.core.entity.User;
import com.lms.core.repository.MailRepository;
import com.lms.core.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MailService {

    private final JavaMailSender mailSender;
    private final MailRepository mailRepository;
    private final UserRepository userRepository;

    public void sendMail(String to, String subject, String text) {
        SimpleMailMessage mail = new SimpleMailMessage();
        Mail mailEntity = new Mail();
        User user = userRepository.findByEmail(to);
        boolean isMailDelivered = true;

        mail.setTo(to);
        mail.setSubject(subject);
        mail.setText(text);

        mailEntity.setUser(user);
        mailEntity.setSubject(subject);
        mailEntity.setText(text);

        try {
            mailSender.send(mail);
        } catch (MailException me) {
            isMailDelivered = false;
        }

        mailEntity.setIsDelivered(isMailDelivered);
        mailRepository.save(mailEntity);
    }
}
