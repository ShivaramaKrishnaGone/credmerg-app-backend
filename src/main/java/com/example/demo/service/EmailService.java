package com.example.demo.service;

import com.example.demo.model.EmailLog;
import com.example.demo.model.Vendor;
import com.example.demo.repository.EmailLogRepository;
import com.example.demo.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EmailService {

    @Autowired
    private EmailLogRepository emailLogRepository;

    @Autowired
    private VendorRepository vendorRepository;

    public void sendEmail(Long vendorId) {
        Optional<Vendor> vendorOptional = vendorRepository.findById(vendorId);
        if (vendorOptional.isPresent()) {
            Vendor vendor = vendorOptional.get();
            String subject = "Payment Notification";
            String body = String.format("Sending payments to vendor %s at upi %s", vendor.getName(), vendor.getUpi());

            EmailLog emailLog = new EmailLog();
            emailLog.setRecipient(vendor.getEmail());
            emailLog.setSubject(subject);
            emailLog.setBody(body);
            emailLog.setSentAt(LocalDateTime.now());

            emailLogRepository.save(emailLog);

            System.out.println("Sending email to: " + vendor.getEmail());
            System.out.println("Subject: " + subject);
            System.out.println("Body: " + body);
        }
    }

    public List<EmailLog> getAllEmails() {
        return emailLogRepository.findAll();
    }
}
