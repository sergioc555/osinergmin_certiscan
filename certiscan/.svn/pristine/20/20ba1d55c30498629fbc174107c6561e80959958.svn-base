package com.certicom.certiscan.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import com.certicom.certiscan.commons.Constante;
import com.certicom.certiscan.commons.FacesUtils;
import com.certicom.certiscan.commons.GenericBeans;
import com.certicom.certiscan.domain.CategoriaIndicador;
import com.certicom.certiscan.domain.Log;
import com.certicom.certiscan.domain.Menu;
import com.certicom.certiscan.services.CategoriaIndicadorServices;
import com.certicom.certiscan.services.MenuServices;

@ManagedBean(name="categoriaIndicadorMB")
@ViewScoped
public class CategoriaIndicadorMB extends GenericBeans implements Serializable{

	private CategoriaIndicador categoria;
	private List<CategoriaIndicador> listaCategoriaIndicador;
	private Boolean editar;
	private CategoriaIndicadorServices categoriaIndicadorService;
	private RequestContext context;
	private Log log;
	private LogMB logmb;
	private MenuServices menuServices;
	
	public CategoriaIndicadorMB(){
		;
	}
	
	@PostConstruct
	public void inicia(){
		this.categoria = new CategoriaIndicador();
		this.editar = Boolean.FALSE;
		this.categoriaIndicadorService = new CategoriaIndicadorServices();
		this.context = RequestContext.getCurrentInstance();
		menuServices = new MenuServices();
		log = (Log)getSpringBean(Constante.SESSION_LOG);
		logmb = new LogMB();
		try {
			Menu codMenu = menuServices.opcionMenuByPretty("pretty:categoriaIndicador");
			log.setCod_menu(codMenu.getCod_menu().intValue());
			this.listaCategoriaIndicador = this.categoriaIndicadorService.findAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void NuevoCategoriaIndicador(){
		this.editar = Boolean.FALSE;
		this.categoria = new CategoriaIndicador();
	}
	
	public void EditarCategoriaIndicador(CategoriaIndicador cate){
		//System.out.println("editando cliente");
		this.categoria = cate;
		this.editar = Boolean.TRUE;
	}
	
	public void guardarCategoriaIndicador(){
		Boolean valido=Boolean.TRUE;
		//System.out.println("insertando nuevo cliente");
		RequestContext context = RequestContext.getCurrentInstance();  
		context.addCallbackParam("esValido", valido);
		try {
			
			if(this.editar){
				//System.out.println("editando cliente");
				
				this.categoriaIndicadorService.actualizarCategoriaIndicador(this.categoria);
				log.setAccion("UPDATE");
		        log.setDescripcion("Se editó la Categoria Indicador: "+this.categoria.getDescripcion());
		        logmb.insertarLog(log);
			}else{
				//System.out.println("creando cliente");							
				this.categoriaIndicadorService.crearCategoriaIndicador(this.categoria);
				log.setAccion("INSERT");
		        log.setDescripcion("Se insertó la Categoria Indicador: "+this.categoria.getDescripcion());
		        logmb.insertarLog(log);
			}
			
			FacesUtils.showFacesMessage("Categoria Indicador guardado correctamente",Constante.INFORMACION);
			this.listaCategoriaIndicador = this.categoriaIndicadorService.findAll();
			this.context.update("sms");
		} catch (Exception e) {
			//System.out.println("Error al insertar/editar cliente"+ e.getMessage());
			e.printStackTrace();
		}
		this.editar = Boolean.FALSE;
	}
	
	public void eliminarCategoriaIndicador(){
		try {
			this.categoriaIndicadorService.eliminarCategoriaIndicador(this.categoria.getId_categoria());
			log.setAccion("DELETE");
	        log.setDescripcion("Se eliminó la Categoria Indicador: "+this.categoria.getDescripcion());
	        logmb.insertarLog(log);
			this.listaCategoriaIndicador = this.categoriaIndicadorService.findAll();
			FacesUtils.showFacesMessage("Categoria Indicador eliminado",Constante.INFORMACION);
			this.context.update("sms");
		} catch (Exception e) {
			//System.out.println("Error eliminando cliente" + e.getMessage() );
			e.printStackTrace();
		}
	}
	
	


	
	/*################-------getters ys setters-------####################*/
		

	public Boolean getEditar() {
		return editar;
	}

	public void setEditar(Boolean editar) {
		this.editar = editar;
	}

	public CategoriaIndicador getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaIndicador categoria) {
		this.categoria = categoria;
	}

	public List<CategoriaIndicador> getListaCategoriaIndicador() {
		return listaCategoriaIndicador;
	}

	public void setListaCategoriaIndicador(
			List<CategoriaIndicador> listaCategoriaIndicador) {
		this.listaCategoriaIndicador = listaCategoriaIndicador;
	}

	public CategoriaIndicadorServices getCategoriaIndicadorService() {
		return categoriaIndicadorService;
	}

	public void setCategoriaIndicadorService(
			CategoriaIndicadorServices categoriaIndicadorService) {
		this.categoriaIndicadorService = categoriaIndicadorService;
	}
	
	

}
