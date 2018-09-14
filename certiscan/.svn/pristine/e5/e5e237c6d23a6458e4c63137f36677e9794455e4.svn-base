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
import com.certicom.certiscan.domain.Cliente;
import com.certicom.certiscan.domain.Empresa;
import com.certicom.certiscan.domain.Log;
import com.certicom.certiscan.domain.Menu;
import com.certicom.certiscan.domain.Usuario;
import com.certicom.certiscan.services.Centro_AtencionServices;
import com.certicom.certiscan.services.ClienteServices;
import com.certicom.certiscan.services.EmpresaService;
import com.certicom.certiscan.services.MenuServices;
import com.certicom.certiscan.services.UsuarioServices;

@ManagedBean(name="centro_atencionMB")
@ViewScoped
public class Centro_AtencionMB extends GenericBeans implements Serializable{

	private Centro_Atencion centro_atencion;
	private List<Centro_Atencion> listaCentro_Atencion;
	private List<Centro_Atencion> listaCentro_AtencionFilter;
	private List<Empresa> listaEmpresas;
	private List<Centro_Atencion>  listaOficinaFilter;
	private Centro_Atencion centro_atencionSelect;
	private Boolean editar;
	private Centro_AtencionServices centro_atencionService;
	private EmpresaService empresaService;
	private UsuarioServices usuarioServices;
	private MenuServices menuServices;
	private RequestContext context;
	
	private Log log;
	private LogMB logmb;
	@ManagedProperty(value= "#{loginMB}")
	private LoginMB login;
	
	public Centro_AtencionMB(){
		;
	}
	
	@PostConstruct
	public void inicia(){
		this.centro_atencion = new Centro_Atencion();
		this.editar = Boolean.FALSE;
		centro_atencionService = new Centro_AtencionServices();
		empresaService = new EmpresaService();
		
		usuarioServices = new UsuarioServices();
		menuServices = new MenuServices();
		this.context = RequestContext.getCurrentInstance();
		try {
			this.listaCentro_Atencion= this.centro_atencionService.findAll();
			this.listaEmpresas = this.empresaService.findAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		log = (Log)getSpringBean(Constante.SESSION_LOG);
		logmb = new LogMB();
		try {
			Menu codMenu = menuServices.opcionMenuByPretty("pretty:centro_atencion");
			log.setCod_menu(codMenu.getCod_menu().intValue());
			log.setIdusuario(this.login.getIdUsuario());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	public void iniciaNuevoCentro_Atencion(){
		this.editar = Boolean.FALSE;
		this.centro_atencion = new Centro_Atencion();
	}
	
	public void iniciaEditarCentro_Atencion(Centro_Atencion centro_atencion){
		//System.out.println("editando proyecto");
		//System.out.println("la empresa que entra al proyecto es : " + this.centro_atencion.getId_empresa());
		this.centro_atencion = centro_atencion;	
		this.centro_atencion.setId_empresa(1);
		this.editar = Boolean.TRUE;

	}
	
	public void guardarCentroAtencion(){
		//System.out.println("insertando nuevo proyecto");
		try {
			
			this.centro_atencion.setNombre(this.centro_atencion.getNombre().trim().toUpperCase());
			this.centro_atencion.setDescripcion(this.centro_atencion.getDescripcion().trim().toUpperCase());
			this.centro_atencion.setResponsable(this.centro_atencion.getResponsable().trim().toUpperCase());
			
			if(this.editar){
				//System.out.println("editando proyecto");
				this.centro_atencionService.actualizarCentro_Atencion(this.centro_atencion);
				log.setAccion("UPDATE");
	            log.setDescripcion("El usuario "+this.login.getLoginUsuario()+" actualizó el Proyecto: " + this.centro_atencion.getNombre());
	            logmb.insertarLog(log);
			}else{
				//System.out.println("creando proyecto");
				this.centro_atencionService.crearCentro_Atencion(this.centro_atencion);
				log.setAccion("INSERT");
	            log.setDescripcion("El usuario "+this.login.getLoginUsuario()+" registró el proyecto: " + this.centro_atencion.getNombre());
	            logmb.insertarLog(log);
			}
			
			FacesUtils.showFacesMessage("Proyecto guardado correctamente",Constante.INFORMACION);
			this.listaCentro_Atencion = this.centro_atencionService.findAll();
			this.context.update("grlGeneral"); 
		} catch (Exception e) {
			//System.out.println("Error al insertar/editar Proyecto"+ e.getMessage());
			e.printStackTrace();
		}
		this.editar = Boolean.FALSE;
	}
	
	public void eliminarCentroAtencion(){
		
		try{
			
			Integer cant = centro_atencionService.listNegocioxProyecto(this.centro_atencion.getId_centro_atencion());
			
			if(cant > 0){
		
				FacesUtils.showFacesMessage("Proyecto no se puede eliminar porque tiene un Negocio asociado", Constante.ERROR);
				
			} else{				
					
				this.centro_atencionService.eliminarCentro_Atencion(this.centro_atencion.getId_centro_atencion());
				log.setAccion("DELETE");
	            log.setDescripcion("El usuario "+this.login.getLoginUsuario()+" eliminó el proyecto: " + this.centro_atencion.getNombre());
	            logmb.insertarLog(log);
				FacesUtils.showFacesMessage("Proyecto ha sido eliminado", 3);
								
				this.listaCentro_Atencion = this.centro_atencionService.findAll();
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		
/*		try {
			
			List<Usuario>  listaCA =  usuarioServices.getlistaUsuario_byCentroAtencion(this.centro_atencion.getId_centro_atencion()); 
			if(listaCA.size()==0){
				this.centro_atencionService.eliminarCentro_Atencion(this.centro_atencion.getId_centro_atencion());
				this.listaCentro_Atencion = this.centro_atencionService.findAll();
				FacesUtils.showFacesMessage("Proyecto eliminado",Constante.INFORMACION);	
				log.setAccion("DELETE");
	            log.setDescripcion("Se ha eliminado el proyecto: " + this.centro_atencion.getNombre());
	            logmb.insertarLog(log);
			}else{
				FacesUtils.showFacesMessage("No se puede eliminar este proyecto ya que tiene usuarios asignados.",Constante.ERROR); 				
			}
			
			this.context.update("grlGeneral");
		} catch (Exception e) {
			//System.out.println("Error eliminando Proyecto" + e.getMessage() );
			e.printStackTrace();
		}*/
	}
	
	/*################-------getters y setters-------####################*/
	
	public List<Centro_Atencion> getListaOficinaFilter() {
		return listaOficinaFilter;
	}

	public void setListaOficinaFilter(List<Centro_Atencion> listaOficinaFilter) {
		this.listaOficinaFilter = listaOficinaFilter;
	}

	public Centro_Atencion getCentro_atencion() {
		return centro_atencion;
	}

	public void setCentro_atencion(Centro_Atencion centro_atencion) {
		this.centro_atencion = centro_atencion;
	}

	public List<Centro_Atencion> getListaCentro_Atencion() {
		return listaCentro_Atencion;
	}

	public void setListaCentro_Atencion(List<Centro_Atencion> listaCentro_Atencion) {
		this.listaCentro_Atencion = listaCentro_Atencion;
	}

	public Boolean getEditar() {
		return editar;
	}

	public void setEditar(Boolean editar) {
		this.editar = editar;
	}

	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		this.log = log;
	}

	public LogMB getLogmb() {
		return logmb;
	}

	public void setLogmb(LogMB logmb) {
		this.logmb = logmb;
	}

	public List<Centro_Atencion> getListaCentro_AtencionFilter() {
		return listaCentro_AtencionFilter;
	}

	public void setListaCentro_AtencionFilter(
			List<Centro_Atencion> listaCentro_AtencionFilter) {
		this.listaCentro_AtencionFilter = listaCentro_AtencionFilter;
	}

	public List<Empresa> getListaEmpresas() {
		return listaEmpresas;
	}

	public void setListaEmpresas(List<Empresa> listaEmpresas) {
		this.listaEmpresas = listaEmpresas;
	}

	public Centro_Atencion getCentro_atencionSelect() {
		return centro_atencionSelect;
	}

	public void setCentro_atencionSelect(Centro_Atencion centro_atencionSelect) {
		this.centro_atencionSelect = centro_atencionSelect;
	}

	public LoginMB getLogin() {
		return login;
	}

	public void setLogin(LoginMB login) {
		this.login = login;
	}
	

	

	
	

}
