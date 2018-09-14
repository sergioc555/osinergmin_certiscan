package com.certicom.certiscan.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.context.RequestContext;

import com.certicom.certiscan.commons.Constante;
import com.certicom.certiscan.commons.FacesUtils;
import com.certicom.certiscan.commons.GenericBeans;
import com.certicom.certiscan.domain.Log;
import com.certicom.certiscan.domain.Menu;
import com.certicom.certiscan.domain.Parametro;
import com.certicom.certiscan.domain.Usuario;
import com.certicom.certiscan.services.MenuServices;
import com.certicom.certiscan.services.ParametroServices;

@ManagedBean
@SessionScoped
public class ParametroMB extends GenericBeans implements Serializable{

	private ParametroServices parametroServices;
	private MenuServices menuServices;
	private Parametro parametro;
	private List<Parametro> listaParametro;
	private Log log;
	private LogMB logmb;
	private String valor;
	private Boolean editar;
	@ManagedProperty(value= "#{loginMB}")
	private LoginMB login;
	
	public ParametroMB() {	
		
	}

	
	@PostConstruct
	public void inicio()
	{
		menuServices = new MenuServices();
		log = (Log)getSpringBean(Constante.SESSION_LOG);
		logmb = new LogMB();
		try {
			
			Menu codMenu = menuServices.opcionMenuByPretty("pretty:parametros");
			log.setCod_menu(codMenu.getCod_menu().intValue());
			log.setIdusuario(this.login.getIdUsuario());
			parametroServices=new ParametroServices();
			parametro = new Parametro();
		    listaParametro=parametroServices.listParametro();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

	public Boolean getEditar() {
		return editar;
	}



	public void setEditar(Boolean editar) {
		this.editar = editar;
	}



	public Parametro getParametro() 
	{return parametro;} 
 
	public void setParametro(Parametro parametro) 
	{this.parametro=parametro;} 
 
	public List<Parametro> getListaParametro()	throws Exception{
		return listaParametro;
	} 
 
	public void setListaParametro(List<Parametro> listaParametro) 
	{this.listaParametro=listaParametro;} 
 
	public void listParametro(){
		try{
			listaParametro=parametroServices.listParametro();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public String getValor() {
		return valor;
	}



	public void setValor(String valor) {
		this.valor = valor;
	}



	public void insertParametro(){
		Boolean valido=Boolean.TRUE;
		RequestContext context = RequestContext.getCurrentInstance(); 
		try{
			//this.parametro.setValor(Long.valueOf(valor));
			if(this.editar) {
				parametroServices.updateParametro(parametro);
				log.setAccion("UPDATE");
		        log.setDescripcion ("El usuario "+this.login.getLoginUsuario()+" ha actualizado el parametro "+parametro.getDescripcion());
		        logmb.insertarLog(log);
				listaParametro=parametroServices.listParametro();
			}else{
			parametroServices.insertParametro(parametro);
			log.setAccion("INSERT");
	        log.setDescripcion ("El usuario "+this.login.getLoginUsuario()+" ha creado el parametro "+parametro.getDescripcion());
	        logmb.insertarLog(log);
			listaParametro=parametroServices.listParametro();
			}
			
			context.addCallbackParam("esValido", valido);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void updateParametro(){
		try{
			
			parametroServices.updateParametro(parametro);
			log.setAccion("UPDATE");
	        log.setDescripcion ("El usuario "+this.login.getLoginUsuario()+" ha actualizado el parametro "+parametro.getDescripcion());
	        logmb.insertarLog(log);
			listaParametro=parametroServices.listParametro();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void findParametro(){
		try{
			parametroServices.findParametro(parametro);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void findParametros(){
		try{
			listaParametro=parametroServices.findParametros(parametro);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void deleteParametro(){
		
			try {
				parametroServices.deleteParametro(parametro.getCod_parametro());
				log.setAccion("DELETE");
		        log.setDescripcion ("El usuario "+this.login.getLoginUsuario()+" ha eliminado el parametro "+parametro.getDescripcion());
		        logmb.insertarLog(log);
				listaParametro=parametroServices.listParametro();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
	}

	public void newInsert(){
		parametro = new Parametro();
		this.editar = Boolean.FALSE;
	}

	public void newUpdate(int cod_parametro)  throws Exception {
		try{
		this.parametro = new Parametro();
		this.parametro = parametroServices.findParametroPorCodigo(cod_parametro);
		this.editar = Boolean.TRUE;
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void newDelete(int cod_parametro)  throws Exception {
		try{
		this.parametro = new Parametro();
		this.parametro = parametroServices.findParametroPorCodigo(cod_parametro);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void cambiarEstado(Parametro par){
		String estado = "";
		if(par.isInd_activo()){
			par.setInd_activo(Boolean.FALSE);
			estado = "INACTIVO";
		}else{
			par.setInd_activo(Boolean.TRUE);
			estado = "ACTIVO";
		}
		try {
			this.parametroServices.updateParametro(par);
			log.setAccion("UPDATE");
            log.setDescripcion("El usuario "+this.login.getLoginUsuario()+" actualiz� el estado a : " +estado+ " al parametro: "+par.getDescripcion());
            logmb.insertarLog(log);
			FacesUtils.showFacesMessage("Se cambio de estado",Constante.INFORMACION);
		} catch (Exception e) {
			System.out.println("error actualizar estado parametro"+e.getMessage());	
			e.printStackTrace();
		}
	 }



	public LoginMB getLogin() {
		return login;
	}



	public void setLogin(LoginMB login) {
		this.login = login;
	}
	
	}

