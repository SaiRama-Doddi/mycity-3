package com.example.hotels.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.hotels.Model.Contact;


@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

     public void sendContactFormEmail(Contact contact) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo("tempdeepak810@gmail.com"); // Replace with your website email
        mailMessage.setSubject("New Contact Form Submission");

        String emailBody = "Name: " + contact.getName() + "\n" +
                           "Email: " + contact.getEmail() + "\n" +
                           "Contact Number: " + contact.getNumber() + "\n" +
                           "Message: " + contact.getMessage();

        mailMessage.setText(emailBody);
        mailSender.send(mailMessage);
    }
}
