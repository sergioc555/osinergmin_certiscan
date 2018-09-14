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
import com.certicom.certiscan.domain.CategoriaIndicadoresProducto;
import com.certicom.certiscan.domain.Log;
import com.certicom.certiscan.domain.Menu;
import com.certicom.certiscan.domain.Oficina;
import com.certicom.certiscan.services.CategoriaIndicadoresProductoService;
import com.certicom.certiscan.services.MenuServices;
 
@ManagedBean(name="categoriaIndicadoresProductoMB")
@ViewScoped

public class CategoriaIndicadoresProductoMB extends GenericBeans implements Serializable{
	
	private CategoriaIndicadoresProducto categoriaIndicadoresProducto;
	private List<CategoriaIndicadoresProducto> listaCategoriaIndicadoresProducto;
	private Boolean editar;
	private CategoriaIndicadoresProductoService categoriaIndicadoresProductoService;
	private MenuServices menuServices;
	private Log log;
	private LogMB logmb;
	RequestContext context; 
	private CategoriaIndicadoresProducto categoriaIndicadoresProductoSelec;
	private List<CategoriaIndicadoresProducto> listaFiltroCategoriaIndicadoresProducto;
	@ManagedProperty(value= "#{loginMB}")
	private LoginMB login; 
	
	public CategoriaIndicadoresProductoMB(){
	}
	
	public List<CategoriaIndicadoresProducto> getListaFiltroCategoriaIndicadoresProducto() {
		return listaFiltroCategoriaIndicadoresProducto;
	}

	public void setListaFiltroCategoriaIndicadoresProducto(
			List<CategoriaIndicadoresProducto> listaFiltroCategoriaIndicadoresProducto) {
		this.listaFiltroCategoriaIndicadoresProducto = listaFiltroCategoriaIndicadoresProducto;
	}

	@PostConstruct
	public void inicia()
	{
		this.categoriaIndicadoresProductoSelec = new CategoriaIndicadoresProducto();
		this.editar = Boolean.FALSE;
		menuServices = new MenuServices();
		this.categoriaIndicadoresProductoService = new CategoriaIndicadoresProductoService();
		
		try {
			this.listaCategoriaIndicadoresProducto = this.categoriaIndicadoresProductoService.findAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		log = (Log)getSpringBean(Constante.SESSION_LOG);
		logmb = new LogMB();
		try {
			Menu codMenu = menuServices.opcionMenuByPretty("pretty:catIndicadoresProducto");
			log.setCod_menu(codMenu.getCod_menu().intValue());
			log.setIdusuario(this.login.getIdUsuario());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();}
		
	}
	
	
	public void guardarCategoriaIndicadoresProducto() {
		Boolean valido=Boolean.TRUE;
		RequestContext context = RequestContext.getCurrentInstance();  
   	    context.addCallbackParam("esValido", valido);
		
		try {
			this.categoriaIndicadoresProductoSelec.setDescripcion(this.categoriaIndicadoresProductoSelec.getDescripcion().trim().toUpperCase());
			
			if(this.editar) {
				this.categoriaIndicadoresProductoService.actualizarCategoriaIndicadoresProducto(categoriaIndicadoresProductoSelec);
				FacesUtils.showFacesMessage("Tipo de Indicador ha sido actualizado", 3);
				log.setAccion("UPDATE");
		        log.setDescripcion("El usuario "+this.login.getLoginUsuario()+" actualizó el tipo de Indicador: "+this.categoriaIndicadoresProductoSelec.getDescripcion());
		        logmb.insertarLog(log);
		        
			}else{
				//this.resultado.setEstado(Boolean.TRUE);
				this.categoriaIndicadoresProductoService.crearCategoriaIndicadoresProducto(categoriaIndicadoresProductoSelec);
				FacesUtils.showFacesMessage("Tipo de Indicador ha sido creado", 3);
				log.setAccion("INSERT");
		        log.setDescripcion("El usuario "+this.login.getLoginUsuario()+" registró la categoría indicador producto  : " + this.categoriaIndicadoresProductoSelec.getDescripcion()+this.categoriaIndicadoresProductoSelec);
		        logmb.insertarLog(log);
			}
			
			this.editar = Boolean.FALSE;
			this.categoriaIndicadoresProducto = new CategoriaIndicadoresProducto();
			this.listaCategoriaIndicadoresProducto = this.categoriaIndicadoresProductoService.findAll();
			this.categoriaIndicadoresProductoSelec = new CategoriaIndicadoresProducto();
			context.update("sms");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void nuevoCategoriaIndicadoresProducto(){
		this.categoriaIndicadoresProductoSelec  = new CategoriaIndicadoresProducto();
		this.editar = Boolean.FALSE;
		this.categoriaIndicadoresProductoSelec.setEstado(Boolean.TRUE);
	}
	

	public void editarCategoriaIndicadoresProducto(CategoriaIndicadoresProducto tipDoc){
		this.categoriaIndicadoresProductoSelec = tipDoc;
		this.editar = Boolean.TRUE;
	}
	
	public void eliminarCategoriaIndicadoresProducto(CategoriaIndicadoresProducto tipDoc){
		this.categoriaIndicadoresProductoSelec = tipDoc;
		
	}
	
	public void confirmaEliminar()
	{
		try {
			this.categoriaIndicadoresProductoService.eliminarCategoriaIndicadoresProducto(this.categoriaIndicadoresProductoSelec.getId_cat_producto());
			FacesUtils.showFacesMessage("Tipo Indicador ha sido eliminado", 3);
			this.listaCategoriaIndicadoresProducto = this.categoriaIndicadoresProductoService.findAll();
			log.setAccion("DELETE");
	        log.setDescripcion("El usuario "+this.login.getLoginUsuario()+" eliminó la categoria indicador producto  : " + this.categoriaIndicadoresProductoSelec.getDescripcion());
	        logmb.insertarLog(log);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	public void cambiarEstado(CategoriaIndicadoresProducto categoriaIndicadoresProducto){
		   String estado="";
		   if(categoriaIndicadoresProducto.getEstado()){
			   categoriaIndicadoresProducto.setEstado(Boolean.FALSE);
			   estado="INACTIVO";
		   }else{
			   categoriaIndicadoresProducto.setEstado(Boolean.TRUE);
			   estado="ACTIVO";
		   }
		   
		   try {
			   this.categoriaIndicadoresProductoService.actualizarCategoriaIndicadoresProducto(categoriaIndicadoresProducto);;
			   FacesUtils.showFacesMessage("Tipo Indicador modificado correctamente",Constante.INFORMACION);
			   this.listaCategoriaIndicadoresProducto = this.categoriaIndicadoresProductoService.findAll();
			   log.setAccion("UPDATE");
	           log.setDescripcion("El usuario "+this.login.getLoginUsuario()+" actualizó el estado de la categoria indicador producto: "+categoriaIndicadoresProducto.getDescripcion()+" a  : " + estado);
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

	public CategoriaIndicadoresProducto getCategoriaIndicadoresProducto() {
		return categoriaIndicadoresProducto;
	}

	public void setCategoriaIndicadoresProducto(CategoriaIndicadoresProducto categoriaIndicadoresProducto) {
		this.categoriaIndicadoresProducto = categoriaIndicadoresProducto;
	}

	public List<CategoriaIndicadoresProducto> getListaCategoriaIndicadoresProducto() {
		return listaCategoriaIndicadoresProducto;
	}

	public void setListaCategoriaIndicadoresProducto(List<CategoriaIndicadoresProducto> listaCategoriaIndicadoresProducto) {
		this.listaCategoriaIndicadoresProducto = listaCategoriaIndicadoresProducto;
	}

	public void setEditar(Boolean editar) {
		this.editar = editar;
	}

	public CategoriaIndicadoresProducto getCategoriaIndicadoresProductoSelec() {
		return categoriaIndicadoresProductoSelec;
	}

	public void setCategoriaIndicadoresProductoSelec(
			CategoriaIndicadoresProducto categoriaIndicadoresProductoSelec) {
		this.categoriaIndicadoresProductoSelec = categoriaIndicadoresProductoSelec;
	}

	public LoginMB getLogin() {
		return login;
	}

	public void setLogin(LoginMB login) {
		this.login = login;
	}

	
	
	
	

}
