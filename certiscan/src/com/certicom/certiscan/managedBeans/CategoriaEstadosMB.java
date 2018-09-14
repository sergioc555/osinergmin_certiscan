package com.certicom.certiscan.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import com.certicom.certiscan.commons.Constante;
import com.certicom.certiscan.commons.FacesUtils;
import com.certicom.certiscan.commons.GenericBeans;
import com.certicom.certiscan.domain.CategoriaEstados;
import com.certicom.certiscan.domain.Log;
import com.certicom.certiscan.domain.Menu;
import com.certicom.certiscan.services.CategoriaEstadosServices;
import com.certicom.certiscan.services.MenuServices;

@ManagedBean(name="categoriaEstadosMB")
@ViewScoped
public class CategoriaEstadosMB extends GenericBeans implements Serializable{

	private CategoriaEstados categoriaEstados;
	private List<CategoriaEstados> listaCategoriaEstados;
	private Boolean editar;	
    private CategoriaEstadosServices categoriaEstadosServices;
    private MenuServices menuServices;
	private Log log;
	private LogMB logmb;
	RequestContext context; 
	private CategoriaEstados categoriaEstadosSelec;
	
	@ManagedProperty(value= "#{loginMB}")
	private LoginMB login;
	
      
    public CategoriaEstadosMB(){}
	
	@PostConstruct
	public void inicia(){
		
		this.categoriaEstadosSelec = new CategoriaEstados();		
		this.editar = Boolean.FALSE;
		this.categoriaEstadosServices = new CategoriaEstadosServices();
		menuServices = new MenuServices();
		
		try {
			this.listaCategoriaEstados = this.categoriaEstadosServices.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		log = (Log)getSpringBean(Constante.SESSION_LOG);
		logmb = new LogMB();
		try {
			Menu codMenu = menuServices.opcionMenuByPretty("pretty:categoriaEstados");
			log.setCod_menu(codMenu.getCod_menu().intValue());
			log.setIdusuario(this.login.getIdUsuario());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();}
		
	}
	
	public void guardarCategoriaEstados(){
		Boolean valido=Boolean.TRUE;
		RequestContext context = RequestContext.getCurrentInstance();  
   	    context.addCallbackParam("esValido", valido);
		
		try {
			
			this.categoriaEstadosSelec.setDescripcion(this.categoriaEstadosSelec.getDescripcion().trim().toUpperCase());
			this.categoriaEstadosSelec.setObservacion(this.categoriaEstadosSelec.getObservacion().trim().toUpperCase());
			
			if(this.editar) {		
				this.categoriaEstadosServices.actualizarCategoriaEstados(this.categoriaEstadosSelec);
				FacesUtils.showFacesMessage("Categoria de Estados ha sido actualizado", 3);
				log.setAccion("UPDATE");
		        log.setDescripcion("El usuario "+this.login.getLoginUsuario()+" actualizó la categoria estado: "+this.categoriaEstadosSelec.getDescripcion());
		        logmb.insertarLog(log);
		        
		        
			}else{					
				this.categoriaEstadosServices.crearCategoriaEstados(this.categoriaEstadosSelec);
				FacesUtils.showFacesMessage("Categoria de Estados ha sido creado", 3);
				log.setAccion("INSERT");
		        log.setDescripcion("El usuario "+this.login.getLoginUsuario()+" registró la categoria estado: " + this.categoriaEstadosSelec.getDescripcion());
		        logmb.insertarLog(log);
			}
			
			this.categoriaEstadosSelec = new CategoriaEstados();
			this.editar = Boolean.FALSE;
			this.listaCategoriaEstados = this.categoriaEstadosServices.findAll();
			context.update("msgGeneral");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void cambiarEstado(CategoriaEstados categoriaEstados){
			String estado="";
		   if(categoriaEstados.isEstado()){
			   categoriaEstados.setEstado(Boolean.FALSE);
			   estado="INACTIVO";
		   }else{
			   categoriaEstados.setEstado(Boolean.TRUE);
			   estado="ACTIVO";
		   }
		   
		   try {			 
			   this.categoriaEstadosServices.actualizarCategoriaEstados(categoriaEstados);
			   FacesUtils.showFacesMessage("Categoria de Estados modificado correctamente",Constante.INFORMACION);			  
			   this.listaCategoriaEstados = this.categoriaEstadosServices.findAll();
			   log.setAccion("UPDATE");
	           log.setDescripcion("El usuario "+this.login.getLoginUsuario()+" actualizó el estado de la categoria estado "+categoriaEstados.getDescripcion()+" a: " + estado);
	           logmb.insertarLog(log);
		   } catch (Exception e) {
			   System.out.println("Error:"+e.getMessage());
			   e.printStackTrace();
		   }   
	}
	
	public void nuevoCategoriaEstados(){
		this.categoriaEstadosSelec = new CategoriaEstados();
		this.categoriaEstadosSelec.setEstado(Boolean.TRUE);
		this.editar = Boolean.FALSE;
	}

	public void editarCategoriaEstados(CategoriaEstados catgest){
		this.categoriaEstadosSelec = catgest;
		this.editar = Boolean.TRUE;
	}
	
	public void eliminarCategoriaEstados(CategoriaEstados catgest){
		this.categoriaEstadosSelec = catgest;
	}
	
	
	public void confirmaEliminar(){
		try {
			
			
			Integer cant = categoriaEstadosServices.listCategoriaxEstado(this.categoriaEstadosSelec.getId_categoria_estado());
			
			if(cant > 0){
		
				FacesUtils.showFacesMessage("Categoría  no se puede eliminar porque tiene un Estado asociado", Constante.ERROR);
				
			} else{
				
				this.categoriaEstadosServices.eliminarCategoriaEstados(this.categoriaEstadosSelec.getId_categoria_estado());
				FacesUtils.showFacesMessage("Categoria de Estados ha sido eliminado", 3);
				this.listaCategoriaEstados = this.categoriaEstadosServices.findAll();
				log.setAccion("DELETE");
		        log.setDescripcion("El usuario "+this.login.getLoginUsuario()+" eliminó la categoria estado : " + this.categoriaEstadosSelec.getDescripcion());
		        logmb.insertarLog(log);
				
			}
		
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/*##################################################################################################*/
	/*####################################------setters y getters----###################################*/
	/*##################################################################################################*/
	

	public Boolean getEditar() {
		return editar;
	}

	public void setEditar(Boolean editar) {
		this.editar = editar;
	}

	public CategoriaEstados getCategoriaEstados() {
		return categoriaEstados;
	}

	public void setCategoriaEstados(CategoriaEstados categoriaEstados) {
		this.categoriaEstados = categoriaEstados;
	}

	public List<CategoriaEstados> getListaCategoriaEstados() {
		return listaCategoriaEstados;
	}

	public void setListaCategoriaEstados(
			List<CategoriaEstados> listaCategoriaEstados) {
		this.listaCategoriaEstados = listaCategoriaEstados;
	}

	public CategoriaEstados getCategoriaEstadosSelec() {
		return categoriaEstadosSelec;
	}

	public void setCategoriaEstadosSelec(CategoriaEstados categoriaEstadosSelec) {
		this.categoriaEstadosSelec = categoriaEstadosSelec;
	}

	public LoginMB getLogin() {
		return login;
	}

	public void setLogin(LoginMB login) {
		this.login = login;
	}
	

	
    


}
