package com.credmarg.services;

import com.credmarg.model.Vendor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.from}")
    private String fromEmail;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmailToVendor(Vendor vendor) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(vendor.getEmail());
        message.setSubject("Payment Notification");
        message.setText("Sending payments to vendor " + vendor.getName() + " at UPI " + vendor.getUpi());

        mailSender.send(message);

        System.out.println("Email sent to vendor " + vendor.getName() + " at " + vendor.getEmail());
    }
}

