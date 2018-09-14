package com.certicom.certiscan.managedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import com.certicom.certiscan.commons.Constante;
import com.certicom.certiscan.commons.FacesUtils;
import com.certicom.certiscan.commons.GenericBeans;
import com.certicom.certiscan.domain.Log;
import com.certicom.certiscan.domain.Menu;
import com.certicom.certiscan.domain.Requisito;
import com.certicom.certiscan.services.MenuServices;
import com.certicom.certiscan.services.RequisitoService;

@ManagedBean(name="requisitoMB")
@ViewScoped
public class RequisitoMB extends GenericBeans implements Serializable{

	private Requisito requisito;
	private List<Requisito> listaRequisito;
	private Boolean editar;
	
	private RequisitoService requisitoService;
	private MenuServices menuServices;
	private Log log;
	private LogMB logmb;
	@ManagedProperty(value= "#{loginMB}")
	private LoginMB login;
	
	public RequisitoMB(){
	}
	
	@PostConstruct
	public void inicia()
	{
		this.requisito = new Requisito();
		this.listaRequisito = new ArrayList<>();
		this.editar = Boolean.FALSE;
		this.requisitoService = new RequisitoService();
		menuServices = new MenuServices();
		log = (Log)getSpringBean(Constante.SESSION_LOG);
		logmb = new LogMB();
		try {
			Menu codMenu = menuServices.opcionMenuByPretty("pretty:requisito");
			log.setCod_menu(codMenu.getCod_menu().intValue());
			log.setIdusuario(this.login.getIdUsuario());
			this.listaRequisito = this.requisitoService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public void guardarRequisito() {
		Boolean valido=Boolean.TRUE;
		RequestContext context = RequestContext.getCurrentInstance();  
   	    context.addCallbackParam("esValido", valido);
		
		try {
			this.requisito.setDescripcion(this.requisito.getDescripcion().trim().toUpperCase());
			
			if(this.editar) {
				this.requisitoService.actualizarRequisito(this.requisito);
				log.setAccion("UPDATE");
		        log.setDescripcion ("El usuario "+this.login.getLoginUsuario()+" ha actualizado el requisito "+this.requisito.getDescripcion());
		        logmb.insertarLog(log);
				FacesUtils.showFacesMessage("Requisito ha sido actualizado", 3);
			}else{
				//this.resultado.setEstado(Boolean.TRUE);
				this.requisitoService.crearRequisito(this.requisito);
				log.setAccion("INSERT");
		        log.setDescripcion ("El usuario "+this.login.getLoginUsuario()+" ha creado el requisito "+this.requisito.getDescripcion());
		        logmb.insertarLog(log);
				FacesUtils.showFacesMessage("Requisito ha sido creado", 3);
			}
			this.requisito = new Requisito();
			this.editar = Boolean.FALSE;
			
			this.listaRequisito = requisitoService.findAll();
			context.update("sms");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void nuevoRequisito(){
		this.requisito = new Requisito();
		this.editar = Boolean.FALSE;
	}
	

	public void editarRequisito(Requisito requisito){
		this.requisito = requisito;
		this.editar = Boolean.TRUE;
	}
	
	public void eliminarCanales(Requisito requisito){
		this.requisito = requisito;
		
	}
	
	public void confirmaEliminar()
	{
		try {
			this.requisitoService.eliminarRequisito(this.requisito.getCod_requisito());
			log.setAccion("DELETE");
	        log.setDescripcion ("El usuario "+this.login.getLoginUsuario()+" ha eliminado el requisito "+this.requisito.getDescripcion());
	        logmb.insertarLog(log);
			FacesUtils.showFacesMessage("Requisito ha sido eliminado", 3);
			this.listaRequisito = this.requisitoService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
	public void cambiarEstado(Requisito requisito){
		   String estado = "";
		   if(requisito.getEstado()){
			   requisito.setEstado(Boolean.FALSE);
			   estado = "INACTIVO";
		   }else{
			   requisito.setEstado(Boolean.TRUE);
			   estado = "ACTIVO";
		   }
		   
		   try {
			   this.requisitoService.actualizarRequisito(requisito);
			   log.setAccion("UPDATE");
		       log.setDescripcion ("El usuario "+this.login.getLoginUsuario()+" ha actualizado el estado a: "+estado+" del requisito "+requisito.getDescripcion());
		       logmb.insertarLog(log);
			   FacesUtils.showFacesMessage("Requisito  modificado correctamente",Constante.INFORMACION);
			   this.listaRequisito = this.requisitoService.findAll();
		   } catch (Exception e) {
			   System.out.println("Error:"+e.getMessage());
			   e.printStackTrace();
		   }   
	}

	
	/**##########################setter and  getter#####################################*/

	public Requisito getRequisito() {
		return requisito;
	}

	public void setRequisito(Requisito requisito) {
		this.requisito = requisito;
	}

	public List<Requisito> getListaRequisito() {
		return listaRequisito;
	}

	public void setListaRequisito(List<Requisito> listaRequisito) {
		this.listaRequisito = listaRequisito;
	}

	public Boolean getEditar() {
		return editar;
	}

	public void setEditar(Boolean editar) {
		this.editar = editar;
	}

	public LoginMB getLogin() {
		return login;
	}

	public void setLogin(LoginMB login) {
		this.login = login;
	}
	
}
