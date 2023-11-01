package com.example.demo.controller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Email;
import com.example.demo.service.EmailService;

import jakarta.mail.MessagingException;


@RestController
public class EmailController {
    
	private final EmailService emailService;

	public EmailController(EmailService emailService) {
		this.emailService = emailService;
	}
	
	@GetMapping("/")
	public ResponseEntity<String> testConnect() {
		System.out.println("Connected");
        Email email = new Email();
        email.setEmail("testemail@email.com");
        email.setName("test sender");
        email.setMessage("gcp");
        try {
			emailService.sendEmail(email);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>("Connected", HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<String> sendEmail(@RequestBody Email email) throws MessagingException, IOException {
		try {
			emailService.sendEmail(email);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>("Sent", HttpStatus.OK);
	}
}
