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
import com.certicom.certiscan.domain.Estado;
import com.certicom.certiscan.domain.Log;
import com.certicom.certiscan.domain.Menu;
import com.certicom.certiscan.services.EstadoServices;
import com.certicom.certiscan.services.MenuServices;

@ManagedBean(name="estadoMB")
@ViewScoped

public class EstadoMB extends GenericBeans implements Serializable{
	
	private Estado estado;
	private List<Estado> listaEstado;
	private Boolean editar;	
	private EstadoServices estadoService;
	private MenuServices menuServices;
	private Log log;
	private LogMB logmb;
	RequestContext context; 
	private Estado estadoSelec;
	@ManagedProperty(value= "#{loginMB}")
	private LoginMB login;
	
	

	
	private Integer inc = 0;

	public EstadoMB(){
	}
	
	@PostConstruct
	public void inicia()
	{
		this.estadoSelec = new Estado();
		this.listaEstado = new ArrayList<>();
		this.editar = Boolean.FALSE;
		menuServices = new MenuServices();
		this.estadoService = new EstadoServices();
		
		try {
			this.listaEstado = this.estadoService.findAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log = (Log)getSpringBean(Constante.SESSION_LOG);
		logmb = new LogMB();
		try {
			Menu codMenu = menuServices.opcionMenuByPretty("pretty:estado");
			log.setCod_menu(codMenu.getCod_menu().intValue());
			log.setIdusuario(this.login.getIdUsuario());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();}
	
	}
	
	
	public void guardarEstado() {
		Boolean valido=Boolean.TRUE;
		RequestContext context = RequestContext.getCurrentInstance();  
   	    context.addCallbackParam("esValido", valido);
		
		try {
			if(this.editar) {
				this.estadoService.actualizarEstado(estadoSelec);;
				FacesUtils.showFacesMessage("Estado ha sido actualizado", 3);
				log.setAccion("UPDATE");
		        log.setDescripcion("El usuario "+this.login.getLoginUsuario()+" actualizó el Estado: "+this.estadoSelec.getDescripcion());
		        logmb.insertarLog(log);
			}else{
				//this.resultado.setEstado(Boolean.TRUE);
				this.estadoService.crearEstado(estadoSelec);;
				FacesUtils.showFacesMessage("Estado ha sido creado", 3);
				log.setAccion("INSERT");
		        log.setDescripcion("El usuario "+this.login.getLoginUsuario()+" registró: " + this.estadoSelec.getDescripcion());
		        logmb.insertarLog(log);
			}
			
			this.estadoSelec = new Estado();
			this.editar = Boolean.FALSE;
			
			this.listaEstado = estadoService.findAll();
			context.update("sms");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	


	public void nuevoEstado(){
		this.estadoSelec = new Estado();
		this.estadoSelec.setEstado(Boolean.TRUE);
		this.editar = Boolean.FALSE;
		System.out.println("***********creando Empresa");
	}
	

	public void editarEstado(Estado esta){
		this.estadoSelec = esta;
		this.editar = Boolean.TRUE;
	}
	
	public void eliminarEstado(){
		try {
			this.estadoService.eliminarEstado(this.estado.getId_estado());
			log.setAccion("DELETE");
	        log.setDescripcion("Se eliminó Estado: "+this.estado.getDescripcion());
	        logmb.insertarLog(log);
			this.listaEstado = this.estadoService.findAll();
			FacesUtils.showFacesMessage("Estado eliminado",Constante.INFORMACION);

		} catch (Exception e) {
			//System.out.println("Error eliminando cliente" + e.getMessage() );
			e.printStackTrace();
		}
	}

	
	public void cambiarEstado(Estado est){
		   String estado="";
		   if(est.getEstado()){
			   est.setEstado(Boolean.FALSE);
			   estado="INACTIVO";
		   }else{
			   est.setEstado(Boolean.TRUE);
			   estado="ACTIVO";
		   }
		   
		   try {
			   this.estadoService.actualizarEstadoEstado(est);;
			   FacesUtils.showFacesMessage("Empresa se ha  modificado correctamente",Constante.INFORMACION);
			   this.listaEstado = this.estadoService.findAll();
			   log.setAccion("UPDATE");
	           log.setDescripcion("El usuario "+this.login.getLoginUsuario()+" actualizó el estado a  : " + est);
	           logmb.insertarLog(log);
		   } catch (Exception e) {
			   System.out.println("Error:"+e.getMessage());
			   e.printStackTrace();
		   }   
	}
	
	

	 
	/**##########################setter and  getter#####################################*/

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

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public List<Estado> getListaEstado() {
		return listaEstado;
	}

	public void setListaEstado(List<Estado> listaEstado) {
		this.listaEstado = listaEstado;
	}

	public EstadoServices getEstadoService() {
		return estadoService;
	}

	public void setEstadoService(EstadoServices estadoService) {
		this.estadoService = estadoService;
	}

	public Estado getEstadoSelec() {
		return estadoSelec;
	}

	public void setEstadoSelec(Estado estadoSelec) {
		this.estadoSelec = estadoSelec;
	}



	
	
}
