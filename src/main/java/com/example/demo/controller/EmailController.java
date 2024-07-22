package com.example.demo.controller;

import com.example.demo.model.EmailLog;
import com.example.demo.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/emails")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public void sendEmail(@RequestBody List<Long> vendorIds) {
        for (Long vendorId : vendorIds) {
            emailService.sendEmail(vendorId);
        }
    }

    @GetMapping("/logs")
    public List<EmailLog> getAllEmails() {
        return emailService.getAllEmails();
    }
}
