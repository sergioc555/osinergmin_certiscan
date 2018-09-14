package com.certicom.certiscan.mail;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MailUtil {
	
	Properties prop = new Properties();
	InputStream input = null;
	

	public MailUtil(){
		try {
			System.out.println("intentando abrir el archivo");
			input = getClass().getClassLoader().getResourceAsStream("com/certicom/scpf/mail/mail.properties");
//			input = new FileInputStream("mail.properties");
			if (input == null ){
				System.out.println("el input es nulo");
			}else{
				System.out.println(input.toString());
			}
//			input = getClass().getClassLoader().getResourceAsStream("com/certicom/scpf/mail/mail.properties");
			// load a properties file
			prop.load(input);
	 
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	public List<String> getCCs(){
		String[] ccs =  readProperty("default.ccs").split(",");
		if(ccs!= null && ccs.length>0)
			return Arrays.asList(ccs);
		return new ArrayList<String>();
	}
	
	public String getCadenaCCs(){
		return readProperty("default.ccs");
	}
	
	
	public String getFrom_mail(){
		return readProperty("from.mail");
	}
	
	public String getFrom_pass(){
		return readProperty("from.pass");
	}
	
	public String getFrom_name(){
		return readProperty("from.name");
	}
	
	public String getTo_mail(){
		return readProperty("to.mail");
	}
	
	public String getTo_name(){
		return readProperty("to.name");
	}
	
	public String getDefaultMessage(){
		return readProperty("default.message");
	}
	
	public String getDefaultSubject(){
		return readProperty("default.subject");
	}
	
	
	public String readProperty(String property){
		String result;
		result = prop.getProperty(property);
		
		try {
			if (result==null){
				return "SYS_VALOR_NO_ENCONTRADO";
			} else{
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "SYS_ERROR";
		}
		
	}
	
}
