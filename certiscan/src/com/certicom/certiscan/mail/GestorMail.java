package com.certicom.certiscan.mail;

import java.util.List;
import java.util.Map;

import javax.mail.util.ByteArrayDataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;

public class GestorMail {
	
	static ApplicationContext context = new ClassPathXmlApplicationContext("com/certicom/scpf/mail/Spring-Mail.xml");
	
	
	public static void sendMail(String mail, String name, String message){
		MailMail mm = (MailMail) context.getBean("mailMail");
		mm.sendMail(mail, name, message);
	}
	
	public static void sendMailPrueba(String mail, String name, String message){
		MailMail mm = (MailMail) context.getBean("mailMail");
		mm.sendMail(mail, name, message);
	}
	
	public static void sendMailAttach(String mail, String name, String message,FileSystemResource file){
		MailMail mm = (MailMail) context.getBean("mailMail");
        mm.sendMailAttach(mail,name, message, file);
	}
	
	
	public static void sendMailAttach2 (
			String to_mail ,
			String to_name, 
			String from_name,
			String subject,
			String content,			
			List<String>ccs,
			Map<String,Object> files) throws Exception{
		MailMail mm = (MailMail) context.getBean("mailMail");
        mm.sendMailAttach2(to_mail,to_name,from_name, subject,content,ccs, files);
	}
	
	public static void sendMailAttach2Prueba (
			String to_mail ,
			String to_name, 
			String from_name,
			String subject,
			String content,
			String[] cco,
			List<String>ccs,
			Map<String,Object> files) throws Exception{
		MailMail mm = (MailMail) context.getBean("mailMail");
        mm.sendMailAttach2Prueba(to_mail,to_name,from_name, subject,content,cco,ccs, files);
	}
	
	public static void sendMailAttach3(String message,ByteArrayDataSource file,String fileName){
		MailMail mm = (MailMail) context.getBean("mailMail");
        mm.sendMailAttach2(message, file, fileName);
	}

}
