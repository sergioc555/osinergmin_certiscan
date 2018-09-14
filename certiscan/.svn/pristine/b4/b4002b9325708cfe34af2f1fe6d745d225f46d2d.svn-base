package com.certicom.certiscan.mail;

import java.util.List;
import java.util.Map;

import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailParseException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class MailMail
{
	private JavaMailSender mailSender;
	private JavaMailSender mailSender_prueba;
	private SimpleMailMessage simpleMailMessage;
	
	public void setSimpleMailMessage(SimpleMailMessage simpleMailMessage) {
		this.simpleMailMessage = simpleMailMessage;
	}

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	public void setMailSender_prueba(JavaMailSender mailSender_prueba) {
		this.mailSender_prueba = mailSender_prueba;
	}

	/*public void sendMailPrueba(String correo,String dear, String content) {
		MimeMessage message = mailSender.createMimeMessage();
		try{
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(simpleMailMessage.getFrom());
			helper.setTo(correo);
			helper.setSubject(simpleMailMessage.getSubject());
			helper.setText(String.format(simpleMailMessage.getText(), dear, content));
		}catch (MessagingException e) {
			throw new MailParseException(e);
		}
		mailSender.send(message);
		System.out.println("mensaje enviado");
	}*/
	
	public void sendMail(String correo,String dear, String content) {
		MimeMessage message = mailSender.createMimeMessage();
		try{
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(simpleMailMessage.getFrom());
			helper.setTo(correo);
			helper.setSubject(simpleMailMessage.getSubject());
			helper.setText(String.format(simpleMailMessage.getText(), dear, content));
		}catch (MessagingException e) {
			throw new MailParseException(e);
		}
		mailSender.send(message);
		System.out.println("mensaje enviado");
	}
	
	public void sendMailAttach(String correo,String dear, String content, FileSystemResource file) {
		MimeMessage message = mailSender.createMimeMessage();
		try{
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(simpleMailMessage.getFrom());
			helper.setTo(correo);
			helper.setSubject(simpleMailMessage.getSubject());
			helper.setText(String.format(simpleMailMessage.getText(), dear, content),true);
			helper.addAttachment(file.getFilename(), file);
		}catch (MessagingException e) {
			throw new MailParseException(e);
		}
		mailSender.send(message);
		System.out.println("mensaje enviado");
	}
	
	public void sendMailAttach2(
			String to_mail ,
			String to_name, 
			String from_name,
			String subject,
			String content,			
			List<String>ccs,
			Map<String,Object> files
			
			) throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(simpleMailMessage.getFrom());
			helper.setTo(to_mail);
			helper.setSubject(subject);
			helper.setText(String.format(simpleMailMessage.getText(), to_name, content, from_name),true);
//			helper.addAttachment(nameFile+".xls",file);
			for (Map.Entry<String, Object> entry : files.entrySet())
			{
				if(entry.getValue().getClass().toString().equalsIgnoreCase("class javax.mail.util.ByteArrayDataSource")){
					helper.addAttachment(entry.getKey(), (ByteArrayDataSource)entry.getValue());
				}else{
					System.out.println("EL ARCHIVO "+entry.getKey()+ " NO HA SIDO ENVIADO POR QUE EL FORMATO NO ESTA PERMIDITO PARA EL MENSAJE");
				}
			}
			if(ccs!=null)
			for(String cc : ccs){
				helper.addCc(cc); //desactivar esto para no enviar copias (por defecto envia al promotor)
			}
			
		mailSender.send(message);
	}
	
	public void sendMailAttach2Prueba(
			String to_mail ,
			String to_name, 
			String from_name,
			String subject,
			String content,	
			String[] cco,
			List<String>ccs,
			Map<String,Object> files
			
			) throws MessagingException {
		MimeMessage message = mailSender_prueba.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(simpleMailMessage.getFrom());
			helper.setTo(to_mail);
			helper.setSubject(subject);
			helper.setText(String.format(simpleMailMessage.getText(), to_name, content, from_name),true);
			helper.setCc(cco);
//			helper.addAttachment(nameFile+".xls",file);
			/*for (Map.Entry<String, Object> entry : files.entrySet())
			{
				if(entry.getValue().getClass().toString().equalsIgnoreCase("class javax.mail.util.ByteArrayDataSource")){
					helper.addAttachment(entry.getKey(), (ByteArrayDataSource)entry.getValue());
				}else{
					System.out.println("EL ARCHIVO "+entry.getKey()+ " NO HA SIDO ENVIADO POR QUE EL FORMATO NO ESTA PERMIDITO PARA EL MENSAJE");
				}
			}*/
			if(ccs!=null)
			for(String cc : ccs){
				helper.addCc(cc); //desactivar esto para no enviar copias (por defecto envia al promotor)
			}
			
			mailSender_prueba.send(message);
	}
	
	public void sendMailAttach2(String content, ByteArrayDataSource file, String nameFile) {
		MimeMessage message = mailSender.createMimeMessage();
		try{
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(simpleMailMessage.getFrom());
			helper.setSubject(simpleMailMessage.getSubject());
			helper.setText(String.format(simpleMailMessage.getText(), "nombre aqui", content));
			helper.addAttachment(nameFile+".xls",file);
			System.out.println("agregando cc");
			helper.addCc("emedina@certicom.com.pe");
		}catch (MessagingException e) {
			throw new MailParseException(e);
		}
		mailSender.send(message);
		System.out.println("mensaje enviado attach "+nameFile);
	}
	
	
}
