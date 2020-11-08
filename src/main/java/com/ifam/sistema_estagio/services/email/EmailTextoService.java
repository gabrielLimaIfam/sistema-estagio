package com.ifam.sistema_estagio.services.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ifam.sistema_estagio.dto.EmailSimplesDto;

@Service
public class EmailTextoService implements IEmailService{

	@Autowired
	private JavaMailSender emailSender;

	@Override
	public void send(EmailSimplesDto email) throws Exception{
		try{
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(email.getTo());
			message.setText(email.getMessage());
			message.setSubject(email.getSubject());

			emailSender.send(message);

			return;
		}catch (Exception e) {
			throw new Exception("[email-texto-service] Erro ao enviar e-mail: " + e.getMessage());
		}
	}
}
