package com.devalex.spring_ionic.services;

import org.springframework.mail.SimpleMailMessage;

import com.devalex.spring_ionic.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);

	void sendEmail(SimpleMailMessage msg);

}
