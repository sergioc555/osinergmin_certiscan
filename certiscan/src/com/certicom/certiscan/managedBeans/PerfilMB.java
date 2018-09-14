package com.certicom.certiscan.managedBeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import org.primefaces.context.RequestContext;

import com.certicom.certiscan.commons.Constante;
import com.certicom.certiscan.commons.FacesUtils;
import com.certicom.certiscan.commons.GenericBeans;
import com.certicom.certiscan.domain.Log;
import com.certicom.certiscan.domain.Menu;
import com.certicom.certiscan.domain.Perfil;
import com.certicom.certiscan.domain.Sistema;
import com.certicom.certiscan.services.MenuServices;
import com.certicom.certiscan.services.PerfilServices;
import com.certicom.certiscan.services.SistemaServices;

@ManagedBean
//@SessionScoped
@ViewScoped
public class PerfilMB extends GenericBeans {

	private Perfil perfil;
	private List<Perfil> listaPerfil;
	private RequestContext context;
	private PerfilServices perfilServices;
	private Boolean editar;
	private MenuServices menuServices;
	private Log log;
	private LogMB logmb;
	
	@ManagedProperty(value= "#{loginMB}")
	private LoginMB login;
	
	

	public PerfilMB() throws Exception {
		;
	}
	
	@PostConstruct
	public void inicia(){
		//System.out.println("iniciando perfilMB");
		perfilServices = new PerfilServices();
		this.editar = Boolean.FALSE;
		menuServices = new MenuServices();
		this.context = RequestContext.getCurrentInstance();  
		perfil = new Perfil();
		
		try {
			listaPerfil = perfilServices.listPerfil();
			
		} catch (Exception e) {
			//System.out.println("Error:"+e.getMessage());
			e.printStackTrace();
		}
		
		log = (Log)getSpringBean(Constante.SESSION_LOG);
		logmb = new LogMB();
		try {
			Menu codMenu = menuServices.opcionMenuByPretty("pretty:perfiles");
			log.setCod_menu(codMenu.getCod_menu().intValue());
			log.setIdusuario(this.login.getIdUsuario());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		
		
	}
	
	/**
	 * Desc: metodo que envia un objeto Perfil a otro managebBean
	 * @param Objeto Perfil
	 * @return: cadena para el gestor de navegacion
	 */
	public String asignarPermisos(Perfil perf){
		String outcome = null;
		try {
			//System.out.println("perfil pasado:"+perf.getNombre());
    		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
    		flash.put("perfil", perf);
    		//return "/faces/control_acceso/asignarPermisos?faces-redirect=true";
    		outcome="pretty:asignaPermisosPerfil";
    		log.setAccion("UPDATE");
	        log.setDescripcion("El usuario "+this.login.getLoginUsuario()+" asignó el permiso "+perf.getDescripcion());
	        logmb.insertarLog(log);
    		
		} catch (Exception e) {	
			//System.out.println("Error:"+e.getMessage());
			e.printStackTrace();
		}
		return outcome;
	} 
	
	
	/**
	 * @Desc:Cambia de estado
	 * @Auth:Will
	 * @param: Objeto de la clase Perfil
	 */
	public void cambiarEstado(Perfil perf){
		   String estado = "";
		   if(perf.isInd_activo()){
			   //System.out.println("es igual a uno se pone a cero");
			   perf.setInd_activo(Boolean.FALSE);
			   //sistem.setInd_activo(new Integer(0));
			   estado = "INACTIVO";
		   }else{
			   //System.out.println("es igual a cero");
			   perf.setInd_activo(Boolean.TRUE);
			   //sistem.setInd_activo(new Integer(1));
			   estado = "ACTIVO";
		   }
		   
		   try {
			   this.perfilServices.updatePerfil(perf);
				   //this.sistemaServices.updateSistema(sistem);
			   FacesUtils.showFacesMessage("Estado de feril modificado correctamente",Constante.INFORMACION);
			   log.setAccion("UPDATE");
	           log.setDescripcion("El usuario "+this.login.getLoginUsuario()+" actualizó el estado a  : " + estado+" al perfil: "+perf.getDescripcion());
	           logmb.insertarLog(log);
		   } catch (Exception e) {
			   System.out.println("Error:"+e.getMessage());
			   e.printStackTrace();
		   }   
	}

	
	
	public void newInsert() {
		this.editar = Boolean.FALSE;
		this.perfil =new Perfil();
	}

	public void editarPerfil(Perfil perf) throws Exception {
		System.out.println("perfil seleccionado :"+perf.getNombre());
		this.editar= Boolean.TRUE;
		this.perfil = perf;
	}
	
	
	/**
	 * Desc:Metodo que edita un perfil
	 * @Auth: Will
	 * @throws Exception
	 */
	public void guardarPerfil(){
		if(this.editar){
			try {
				this.perfilServices.updatePerfil(perfil);
				FacesUtils.showFacesMessage("Perfil actualizado correctamente",Constante.INFORMACION);
				log.setAccion("UPDATE");
		        log.setDescripcion("El usuario "+this.login.getLoginUsuario()+" actualizó el perfil de: "+this.perfil.getDescripcion());
		        logmb.insertarLog(log);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			try {
				perfilServices.insertPerfil(perfil);
				FacesUtils.showFacesMessage("Perfil creado correctamente",Constante.INFORMACION);
				log.setAccion("INSERT");
		        log.setDescripcion("El usuario "+this.login.getLoginUsuario()+" registró el perfil: " + this.perfil.getDescripcion());
		        logmb.insertarLog(log);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//relistando
		try {
			this.listaPerfil = this.perfilServices.listPerfil();
			context.update("msgGeneral");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	/*
	public void updatePerfil() throws Exception {
			perfil = this.getPerfil();
			perfilServices.updatePerfil(perfil);
			listaPerfil = perfilServices.listPerfil();
			FacesUtils.showFacesMessage("Perfil Guardado correctamente",Constante.INFORMACION);
	}
*/
	

	public void newDelete(Perfil perf) throws Exception {
		System.out.print("Codigo de Perfil: " + perf.getCod_perfil());
		this.perfil= perf;
	}
	
	public void deletePerfil() {
		try{
		perfilServices.deletePerfil(perfil.getCod_perfil());
		listaPerfil = perfilServices.listPerfil();
		FacesUtils.showFacesMessage("Perfil eliminado",Constante.INFORMACION);
		log.setAccion("DELETE");
        log.setDescripcion("El usuario "+this.login.getLoginUsuario()+" ha eliminado el perfil: " + this.perfil.getDescripcion());
        logmb.insertarLog(log);
		}catch(Exception e){
			System.out.println("Error eliminando perfil:"+ e.getMessage());
			FacesUtils.showFacesMessage("No se puede eliminar tiene permisos asignados, quite los permisos y vuelva a intentarlo",Constante.ERROR);
		}
	}



	/*###############-----gettres y setters--------######################*/	

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public List<Perfil> getListaPerfil() {
		return listaPerfil;
	}

	public void setListaPerfil(List<Perfil> listaPerfil) {
		this.listaPerfil = listaPerfil;
	}

	public PerfilServices getPerfilServices() {
		return perfilServices;
	}

	public void setPerfilServices(PerfilServices perfilServices) {
		this.perfilServices = perfilServices;
	}

	public LoginMB getLogin() {
		return login;
	}

	public void setLogin(LoginMB login) {
		this.login = login;
	}
	
}