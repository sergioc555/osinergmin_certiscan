package com.certicom.certiscan.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import org.primefaces.context.RequestContext;

import com.certicom.certiscan.commons.Constante;
import com.certicom.certiscan.commons.FacesUtils;
import com.certicom.certiscan.commons.GenericBeans;
import com.certicom.certiscan.domain.Centro_Atencion;
import com.certicom.certiscan.domain.Log;
import com.certicom.certiscan.domain.Menu;
import com.certicom.certiscan.domain.Negocio;
import com.certicom.certiscan.services.Centro_AtencionServices;
import com.certicom.certiscan.services.MenuServices;
import com.certicom.certiscan.services.NegocioServices;

@ManagedBean(name="negocioMB")
@ViewScoped
public class NegocioMB extends GenericBeans implements Serializable{

	private Negocio negocio;	
	private List<Negocio> listaNegocios;
	private List<Negocio> listaNegociosFilter;
	private List<Centro_Atencion> listaCentro_Atencion;
	private Negocio negocioSelec;
	private Boolean editar;	
    private NegocioServices negocioServices;
	private Centro_AtencionServices centro_AtencionServices;
	private MenuServices menuServices;
	private Log log;
	private LogMB logmb;
	RequestContext context; 
	
	@ManagedProperty(value= "#{loginMB}")
	private LoginMB login;

	
	public NegocioMB(){}
	
	@PostConstruct
	public void inicia(){
		
		this.negocioSelec = new Negocio();		
		this.editar = Boolean.FALSE;
		this.negocioServices = new NegocioServices();
		this.centro_AtencionServices = new Centro_AtencionServices();
		menuServices = new MenuServices();
		
		try {
			this.listaNegocios = this.negocioServices.findAll();
			this.listaCentro_Atencion = this.centro_AtencionServices.listaCentroAtencionActivo();
		} catch (Exception e) {
	
		e.printStackTrace();
		}
		
		log = (Log)getSpringBean(Constante.SESSION_LOG);
		logmb = new LogMB();
	
	try {
			Menu codMenu = menuServices.opcionMenuByPretty("pretty:negocio");
			log.setCod_menu(codMenu.getCod_menu().intValue());
			log.setIdusuario(this.login.getIdUsuario());
		} catch (Exception e) {			
			e.printStackTrace();}

}
	
	public void guardarNegocio(){
		Boolean valido=Boolean.TRUE;
		RequestContext context = RequestContext.getCurrentInstance();  

    context.addCallbackParam("esValido", valido);
		
		try {
			
			this.negocioSelec.setDescripcion(this.negocioSelec.getDescripcion().trim().toUpperCase());
			
			if(this.editar) {					

				this.negocioServices.actualizarNegocio(this.negocioSelec);
				FacesUtils.showFacesMessage("Negocio ha sido actualizado", 3);
			

				log.setAccion("UPDATE");
		        log.setDescripcion("El usuario "+this.login.getLoginUsuario()+" actualizó el Negocio: "+this.negocioSelec.getDescripcion());
		        logmb.insertarLog(log);
			}else{					

				this.negocioServices.crearNegocio(this.negocioSelec);
				FacesUtils.showFacesMessage("Negocio ha sido creado", 3);				

				log.setAccion("INSERT");
		        log.setDescripcion("El usuario "+this.login.getLoginUsuario()+ " registró el Negocio: " + this.negocioSelec.getDescripcion());
		        logmb.insertarLog(log);
			}
			
			this.negocioSelec = new Negocio();
			this.editar = Boolean.FALSE;	
			this.listaNegocios = this.negocioServices.findAll();
			

context.update("msgGeneral");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void cambiarEstado(Negocio negocio){
			String estado = "";
		   if(negocio.isEstado()){
			   

negocio.setEstado(Boolean.FALSE);
			   estado = "INACTIVO";
		   }else{
			   negocio.setEstado(Boolean.TRUE);		

	   estado = "ACTIVO";
		   }
		   
		   try {			 
			   
this.negocioServices.actualizarNegocio(negocio);
			   FacesUtils.showFacesMessage("Negocio  modificado correctamente",Constante.INFORMACION);	
		  
			   this.listaNegocios = this.negocioServices.findAll();
			   log.setAccion("UPDATE");
	       
log.setDescripcion("El usuario "+this.login.getLoginUsuario()+" actualizó el estado del Negocio"+negocio.getDescripcion()+" a  : " + estado);
	    logmb.insertarLog(log);
		   } catch (Exception e) {
			   System.out.println("Error:"+e.getMessage());
			   e.printStackTrace();
	
	   }   
	}
	
	public void nuevoNegocio(){
		this.negocioSelec = new Negocio();
		this.negocioSelec.setEstado(Boolean.TRUE);
	
	this.editar = Boolean.FALSE;
	}

	public void editarNegocio(Negocio neg){
		this.negocioSelec = neg;
		this.editar = Boolean.TRUE;
	
}
	
	public void eliminarNegocio(Negocio neg){
		this.negocioSelec = neg;
	}
		
	public void confirmaEliminar(){
		
		try{		
			
			Integer cant = negocioServices.listProductoxNegocio(this.negocioSelec.getId_negocio());
	
			if(cant > 0){
		
				FacesUtils.showFacesMessage("Negocio no se puede eliminar tiene un Producto asociado", Constante.ERROR);
				
			} else{
				
this.negocioServices.eliminarNegocio(this.negocioSelec.getId_negocio());
				log.setAccion("DELETE");
		        
log.setDescripcion("El usuario "+this.login.getLoginUsuario()+" eliminó el Negocio: " + negocioSelec.getDescripcion());
		        logmb.insertarLog(log);
		
		FacesUtils.showFacesMessage("Negocio ha sido eliminado", 3);
		
this.listaNegocios = this.negocioServices.findAll();
			}

		}catch(Exception e){
			e.printStackTrace();
		}	
		
/*		try {
		
			this.negocioServices.eliminarNegocio(this.negocioSelec.getId_negocio());
		

	FacesUtils.showFacesMessage("Negocio ha sido eliminado", 3);
			this.listaNegocios = this.negocioServices.findAll();
			

log.setAccion("DELETE");
	        log.setDescripcion("Se ha eliminado  : " + this.negocioSelec.getDescripcion());
	        logmb.insertarLog(log);
		
	

	} catch (Exception e) {
			e.printStackTrace();
		}*/
		
	}
	
	public String configurarResponables(Negocio 

negocio){
		String outcome = null;
		try {
    		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
    	

	flash.put("negocio", negocio);
    		//return "/faces/control_acceso/asignarPermisos?faces-redirect=true";
    		outcome="pretty:confResponsableNegocio";
		} catch (Exception e) {	
			System.out.println("Error:"+e.getMessage());
			

			e.printStackTrace();
		}
		return outcome;
	}
	
	

/*##################################################################################################*/
	/*####################################------setters y 

getters----###################################*/
	/*##################################################################################################*/
		

public Boolean getEditar() {
		return editar;
	}

	public Negocio getNegocio() {
		return negocio;
	}

	public void setNegocio(Negocio negocio) 

{
		this.negocio = negocio;
	}

	public List<Negocio> getListaNegocios() {
		return listaNegocios;
	}

	public void setListaNegocios(List<Negocio> listaNegocios) {
		this.listaNegocios = listaNegocios;
	}

	public Negocio getNegocioSelec() {
		return negocioSelec;
	}

	

public void setNegocioSelec(Negocio negocioSelec) {
		this.negocioSelec = negocioSelec;
	}

	public void setEditar(Boolean editar) {
		

this.editar = editar;
	}

	public List<Centro_Atencion> getListaCentro_Atencion() {
		return listaCentro_Atencion;
	}

	public void setListaCentro_Atencion(List<Centro_Atencion> listaCentro_Atencion) {
		this.listaCentro_Atencion = listaCentro_Atencion;
	}

	public List<Negocio> getListaNegociosFilter() {
		return listaNegociosFilter;
	}

	public void setListaNegociosFilter(List<Negocio> listaNegociosFilter) {
		
		this.listaNegociosFilter = listaNegociosFilter;
	}

	public LoginMB getLogin() {
		return login;
	}

	public void setLogin(LoginMB login) {
		this.login = login;
	}


	

	
    


}
