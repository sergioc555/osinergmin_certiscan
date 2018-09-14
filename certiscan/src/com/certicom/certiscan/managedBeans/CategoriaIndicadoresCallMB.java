package com.certicom.certiscan.managedBeans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRParameter;

import org.primefaces.context.RequestContext;

import com.certicom.certiscan.commons.Constante;
import com.certicom.certiscan.commons.FacesUtils;
import com.certicom.certiscan.commons.GenericBeans;
import com.certicom.certiscan.domain.CategoriaIndicadoresCall;
import com.certicom.certiscan.domain.IndicadorCall;
import com.certicom.certiscan.domain.Log;
import com.certicom.certiscan.domain.Menu;
import com.certicom.certiscan.domain.Negocio;
import com.certicom.certiscan.services.CategoriaIndicadoresCallServices;
import com.certicom.certiscan.services.IndicadorCallServices;
import com.certicom.certiscan.services.MenuServices;
import com.certicom.certiscan.services.NegocioServices;


@ManagedBean(name="categoriaIndicadoresCallMB")
@ViewScoped
public class CategoriaIndicadoresCallMB extends GenericBeans implements Serializable{

	private CategoriaIndicadoresCall categoriaIndicadoresCall;
	private List<CategoriaIndicadoresCall> listaCategoriaIndicadoresCall;
	private List<Negocio> listaNegocios;	
	private CategoriaIndicadoresCall categoriaIndicadoresCallSelec;
	private List<IndicadorCall> listaIndicadorCallFilter;
	private Boolean editar;	
	private CategoriaIndicadoresCallServices categoriaIndicadoresCallServices;
	private MenuServices menuServices;
	private Log log;
	private LogMB logmb;
	private IndicadorCall indicadorCallSelec;
	private IndicadorCall indicadorCallReport;
	private List<IndicadorCall> listaIndicadorCallReport;
	
	private NegocioServices negocioServices;	
	RequestContext context; 
	private IndicadorCallServices indicadorCallServices;
	private String desNegocio;	
	private String _negocio;
	private Integer _producto;
	private String _negocion;	
	private Integer consultar;
	private Integer accion;
	private boolean bandtieneindicador;
	private String descAntind;
	@ManagedProperty(value= "#{loginMB}")
	private LoginMB login;
	
	public CategoriaIndicadoresCallMB(){}
	
	@PostConstruct
	public void inicia(){
		this._producto = 1;
		this.categoriaIndicadoresCallSelec = new CategoriaIndicadoresCall();		
		this.editar = Boolean.FALSE;
		this.categoriaIndicadoresCallServices = new CategoriaIndicadoresCallServices();
		menuServices = new MenuServices();
		this.indicadorCallServices = new IndicadorCallServices();
		this.negocioServices = new NegocioServices();		
		this.listaNegocios = new ArrayList<Negocio>();		
		this.consultar=0;
		this.accion=0;
		try {
			listaNegocios=this.negocioServices.findAll();
			//obtenerToogle();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		log = (Log)getSpringBean(Constante.SESSION_LOG);
		logmb = new LogMB();
		try {
			Menu codMenu = menuServices.opcionMenuByPretty("pretty:indicadores");
			log.setCod_menu(codMenu.getCod_menu().intValue());
			log.setIdusuario(this.login.getIdUsuario());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();}
		
		
		
	}
	
	public void guardarCategoriaIndicadoresCall(){
		Boolean valido=Boolean.TRUE;
		RequestContext context = RequestContext.getCurrentInstance();  
   	    context.addCallbackParam("esValido", valido);
		
		try {
			
			this.categoriaIndicadoresCallSelec.setDescripcion(this.categoriaIndicadoresCallSelec.getDescripcion().trim().toUpperCase());
			
			if(this.editar) {	
				this.categoriaIndicadoresCallSelec.setId_producto(1);
				this.categoriaIndicadoresCallServices.actualizarCategoriaIndicadoresCall(this.categoriaIndicadoresCallSelec);
				FacesUtils.showFacesMessage("Categoria Indicadores Call ha sido actualizado", 3);
				log.setAccion("UPDATE");
		        log.setDescripcion("El usuario "+this.login.getLoginUsuario()+" actualizó la categoria indicador de call: "+this.categoriaIndicadoresCallSelec.getDescripcion());
		        logmb.insertarLog(log);
			}else{
				this.categoriaIndicadoresCallSelec.setId_producto(1);
				this.categoriaIndicadoresCallServices.crearCategoriaIndicadoresCall(this.categoriaIndicadoresCallSelec);
				FacesUtils.showFacesMessage("Categoria Indicadores Call ha sido creado", 3);
				log.setAccion("INSERT");
		        log.setDescripcion("El usuario "+this.login.getLoginUsuario()+" registró la categoria indicador : " + this.categoriaIndicadoresCallSelec.getDescripcion());
		        logmb.insertarLog(log);
		        //this._producto=String.valueOf(this.categoriaIndicadoresCallSelec.getId_producto());
			}
			
			this.categoriaIndicadoresCallSelec = new CategoriaIndicadoresCall();
			//this.listaProducto= new ArrayList<Producto>();
			this.editar = Boolean.FALSE;
			this.accion=0;
			//this.listaCategoriaIndicadoresCall = this.categoriaIndicadoresCallServices.findAll();
			context.update("msgGeneral");
			
			obtenerToogle();			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//obtenerToogle();
	}
	
	public void guardarIndicadorCall(){
		Boolean valido=Boolean.TRUE;
		RequestContext context = RequestContext.getCurrentInstance();  
   	    
		
   	    boolean existe=false;
   	    
   	    String indicadorCall = this.indicadorCallSelec.getDescripcion();
		try {
			
			List<IndicadorCall> listasearch = this.indicadorCallServices.listaIndxCategoria(this.indicadorCallSelec.getId_categoria_call());
			
			for(IndicadorCall indcall: listasearch){
				
				if (indcall.getDescripcion().equals(this.indicadorCallSelec.getDescripcion())){
					existe=true;
					break;
				}
			}
			
			if(this.editar){
				if(this.indicadorCallSelec.getDescripcion().equals(descAntind)){
					existe = false;
				}
			}
			
			
			if (existe == false){
			
			this.indicadorCallSelec.setId_categoria_call(this.indicadorCallSelec.getId_categoria_call());
			this.indicadorCallSelec.setDescripcion(this.indicadorCallSelec.getDescripcion().trim().toUpperCase());
			
			if(this.editar) {				
				this.indicadorCallServices.actualizarIndicadorCall(this.indicadorCallSelec);
				FacesUtils.showFacesMessage("Indicador ha sido actualizado", 3);
				log.setAccion("UPDATE");
		        log.setDescripcion("El usuario "+this.login.getLoginUsuario()+" actualizó el indicador: "
		        					+this.indicadorCallSelec.getDescripcion()+" de la categoría call: "
		        					+this.indicadorCallSelec.getDes_ctgCall());
		        logmb.insertarLog(log);
		        for(CategoriaIndicadoresCall catind: this.listaCategoriaIndicadoresCall){
					if (catind.getId_categoria_call().equals(this.indicadorCallSelec.getId_categoria_call())){
						catind.setListIndicadorCall(this.indicadorCallServices.listaIndxCategoria(this.indicadorCallSelec.getId_categoria_call()));
					}
				}
		        
			}else{					
				this.indicadorCallServices.crearIndicadorCall(this.indicadorCallSelec);
				FacesUtils.showFacesMessage("Indicador ha sido creado", 3);	
				log.setAccion("INSERT");
		        log.setDescripcion("El usuario "+this.login.getLoginUsuario()+" registró el indicador : " 
		        					+ this.indicadorCallSelec.getDescripcion()+" de la categoría call: "
		        					+this.indicadorCallSelec.getDes_ctgCall());
		        logmb.insertarLog(log);
		        }
			
			for(CategoriaIndicadoresCall catind: this.listaCategoriaIndicadoresCall){
				if (catind.getId_categoria_call().equals(this.indicadorCallSelec.getId_categoria_call())){
					catind.setListIndicadorCall(this.indicadorCallServices.listaIndxCategoria(this.indicadorCallSelec.getId_categoria_call()));
				}
			}
			
			this.indicadorCallSelec = new IndicadorCall();
			this.editar = Boolean.FALSE;		
			//this.listaIndicadorCall = this.indicadorCallServices.findAll();
			context.addCallbackParam("esValido", valido);
			context.update("msgGeneral");
			
			if(consultar == 1){
				obtenerToogle();
			}
			
			
			}else{
				this.indicadorCallSelec = new IndicadorCall();
				FacesUtils.showFacesMessage("Ya existe este indicador para esta categoria", 1);
				valido=Boolean.FALSE;
				context.addCallbackParam("esValido", valido);
				context.update("msgGeneral");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//obtenerToogle();
	}
	
	public void cambiarEstado(CategoriaIndicadoresCall ctgCall){
		   String estado="";
		   if(ctgCall.isEstado()){
			   ctgCall.setEstado(Boolean.FALSE);
			   estado="INACTIVO";
		   }else{
			   ctgCall.setEstado(Boolean.TRUE);
			   estado="ACTIVO";
		   }
		   
		   try {			 
			   this.categoriaIndicadoresCallServices.actualizarCategoriaIndicadoresCall(ctgCall);
			   FacesUtils.showFacesMessage("Categoria Indicadores Call modificado correctamente",Constante.INFORMACION);			  
			   this.listaCategoriaIndicadoresCall = this.categoriaIndicadoresCallServices.findAll();
			   log.setAccion("UPDATE");
	           log.setDescripcion("El usuario "+this.login.getLoginUsuario()+" actualizó el estado de la categoria indicadores call: "+ctgCall.getDescripcion()+" a : " + estado);
	           logmb.insertarLog(log);
	           
		   } catch (Exception e) {
			   //System.out.println("Error:"+e.getMessage());
			   e.printStackTrace();
		   }   
		   
		   obtenerToogle();
	}
	
	public void cambiarEstado(IndicadorCall indCall){
		   String estado="";
		   if(indCall.isEstado()){
			   indCall.setEstado(Boolean.FALSE);
			   estado="INACTIVO";
		   }else{
			   indCall.setEstado(Boolean.TRUE);
			   estado="ACTIVO";
		   }
		   
		   try {			 
			   this.indicadorCallServices.actualizarIndicadorCall(indCall);
			   FacesUtils.showFacesMessage("Indicador modificado correctamente",Constante.INFORMACION);			  
			   //this.listaIndicadorCall = this.indicadorCallServices.findAll();
			   log.setAccion("UPDATE");
	           log.setDescripcion("El usuario "+this.login.getLoginUsuario()+" actualizó el estado del indicador: "
	        		   				+indCall.getDescripcion()+" de la categoria indicador: "
	        		   				+indCall.getDes_ctgCall()+" a  : " + estado);
	           logmb.insertarLog(log);
	           
	           for(CategoriaIndicadoresCall catind: this.listaCategoriaIndicadoresCall){
					if (catind.getId_categoria_call().equals(indCall.getId_categoria_call())){
						catind.setListIndicadorCall(this.indicadorCallServices.listaIndxCategoria(indCall.getId_categoria_call()));
					}
				}
		   } catch (Exception e) {
			   //System.out.println("Error:"+e.getMessage());
			   e.printStackTrace();
		   }   
		   
		   //obtenerToogle();
	}
	
	
	public void nuevoIndicadorCall(CategoriaIndicadoresCall p){
		
		try {
			this.indicadorCallSelec = new IndicadorCall();
			this.indicadorCallSelec.setDes_ctgCall(this.categoriaIndicadoresCallServices.findById(p.getId_categoria_call()).getDescripcion());
			this.indicadorCallSelec.setId_categoria_call(p.getId_categoria_call());
			this.indicadorCallSelec.setEstado(Boolean.TRUE);
			this.editar = Boolean.FALSE;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void nuevoCategoriaIndicadoresCall(){
		
		try {
			this.categoriaIndicadoresCallSelec = new CategoriaIndicadoresCall();
			this.categoriaIndicadoresCallSelec.setEstado(Boolean.TRUE);
			this._negocion="";
			//this.listaProducto=this.productoServices.buscarProductoByNegocio(Integer.valueOf(this._negocion));
			this.editar = Boolean.FALSE;
			this.accion=1;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void editarCategoriaIndicadoresCall(CategoriaIndicadoresCall ctgCall){
		this.categoriaIndicadoresCallSelec = ctgCall;
		this.editar = Boolean.TRUE;
	}
	
	public void editarIndicadorCall(IndicadorCall indCall){
		this.indicadorCallSelec = indCall;
		this.editar = Boolean.TRUE;
		this.descAntind = indicadorCallSelec.getDescripcion();
	}
	
	public void eliminarCategoriaIndicadoresCall(CategoriaIndicadoresCall ctgCall){
		this.categoriaIndicadoresCallSelec = ctgCall;
	}
	
	public void eliminarIndicadorCall(IndicadorCall indCall){
		this.indicadorCallSelec = indCall;
	}
	
	public void confirmaEliminarIndicador(){
		try {
		
			this.indicadorCallServices.eliminarIndicadorCall(this.indicadorCallSelec.getId_indicadores_call());
			FacesUtils.showFacesMessage("Indicador ha sido eliminado", 3);
			//this.listaIndicadorCall = this.indicadorCallServices.findAll();
			log.setAccion("DELETE");
	        log.setDescripcion("El usuario "+this.login.getLoginUsuario()+" eliminó el indicador : " 
	        					+ this.indicadorCallSelec.getDescripcion()+" de la categoria : "
	        					+this.indicadorCallSelec.getDes_ctgCall());
	        logmb.insertarLog(log);
	        for(CategoriaIndicadoresCall catind: this.listaCategoriaIndicadoresCall){
				if (catind.getId_categoria_call().equals(this.indicadorCallSelec.getId_categoria_call())){
					catind.setListIndicadorCall(this.indicadorCallServices.listaIndxCategoria(this.indicadorCallSelec.getId_categoria_call()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//obtenerToogle();
		
	}
	
	public void confirmaEliminarCategoria(){
		
		try{			
			Integer cant = categoriaIndicadoresCallServices.listIndicadorxCategroriaCall(this.categoriaIndicadoresCallSelec.getId_categoria_call());
			
			if(cant > 0){
		
				FacesUtils.showFacesMessage("Categoria Indicador no se puede eliminar porque tiene un Indicador asociado", Constante.ERROR);
				
			} else{
				
				
				this.categoriaIndicadoresCallServices.eliminarCategoriaIndicadoresCall(this.categoriaIndicadoresCallSelec.getId_categoria_call());
				log.setAccion("DELETE");
		        log.setDescripcion("El usuario "+this.login.getLoginUsuario()+" eliminó la categoria call : " + this.categoriaIndicadoresCallSelec.getDescripcion());
		        logmb.insertarLog(log);				
				FacesUtils.showFacesMessage("Categoria Indicador ha sido eliminado", 3);
				this.listaCategoriaIndicadoresCall = this.categoriaIndicadoresCallServices.findAll();
				
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		
/*		try {
		
			this.categoriaIndicadoresCallServices.eliminarCategoriaIndicadoresCall(this.categoriaIndicadoresCallSelec.getId_categoria_call());
			FacesUtils.showFacesMessage("Negocio ha sido eliminado", 3);
			this.listaCategoriaIndicadoresCall = this.categoriaIndicadoresCallServices.findAll();
			log.setAccion("DELETE");
	        log.setDescripcion("Se ha eliminado  : " + this.categoriaIndicadoresCallSelec.getDescripcion());
	        logmb.insertarLog(log);
		
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		obtenerToogle();
		
	}
	
	
	
	public void obtenerToogle(){
		
		//System.out.println(" entra en obtenerToogle ");
		try {
			
			//System.out.println("Id producto:"+Integer.valueOf(this._producto));
			this.listaCategoriaIndicadoresCall = this.categoriaIndicadoresCallServices.findByProducto(Integer.valueOf(1));
			for(CategoriaIndicadoresCall catindc: listaCategoriaIndicadoresCall){
				List<IndicadorCall> indaux = this.indicadorCallServices.listaIndxCategoria(catindc.getId_categoria_call());
				
				catindc.setListIndicadorCall(indaux);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public void buscar(){
		this.consultar=1;
		this.accion=0;
		obtenerToogle();
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

	public CategoriaIndicadoresCall getCategoriaIndicadoresCall() {
		return categoriaIndicadoresCall;
	}

	public void setCategoriaIndicadoresCall(
			CategoriaIndicadoresCall categoriaIndicadoresCall) {
		this.categoriaIndicadoresCall = categoriaIndicadoresCall;
	}

	public List<CategoriaIndicadoresCall> getListaCategoriaIndicadoresCall() {
		return listaCategoriaIndicadoresCall;
	}

	public void setListaCategoriaIndicadoresCall(
			List<CategoriaIndicadoresCall> listaCategoriaIndicadoresCall) {
		this.listaCategoriaIndicadoresCall = listaCategoriaIndicadoresCall;
	}

	public CategoriaIndicadoresCall getCategoriaIndicadoresCallSelec() {
		return categoriaIndicadoresCallSelec;
	}

	public void setCategoriaIndicadoresCallSelec(
			CategoriaIndicadoresCall categoriaIndicadoresCallSelec) {
		this.categoriaIndicadoresCallSelec = categoriaIndicadoresCallSelec;
	}

	public List<IndicadorCall> getListaIndicadorCallFilter() {
		return listaIndicadorCallFilter;
	}

	public void setListaIndicadorCallFilter(
			List<IndicadorCall> listaIndicadorCallFilter) {
		this.listaIndicadorCallFilter = listaIndicadorCallFilter;
	}

	public IndicadorCall getIndicadorCallSelec() {
		return indicadorCallSelec;
	}

	public void setIndicadorCallSelec(IndicadorCall indicadorCallSelec) {
		this.indicadorCallSelec = indicadorCallSelec;
	}

	public String get_negocio() {
		return _negocio;
	}

	public void set_negocio(String _negocio) {
		this._negocio = _negocio;
	}

	public List<Negocio> getListaNegocios() {
		return listaNegocios;
	}

	public void setListaNegocios(List<Negocio> listaNegocios) {
		this.listaNegocios = listaNegocios;
	}

	
	public String get_negocion() {
		return _negocion;
	}

	public void set_negocion(String _negocion) {
		this._negocion = _negocion;
	}

	

	public Integer getConsultar() {
		return consultar;
	}

	public void setConsultar(Integer consultar) {
		this.consultar = consultar;
	}

	public Integer getAccion() {
		return accion;
	}

	public void setAccion(Integer accion) {
		this.accion = accion;
	}

	public String getDesNegocio() {
		return desNegocio;
	}

	public void setDesNegocio(String desNegocio) {
		this.desNegocio = desNegocio;
	}

	

	public LoginMB getLogin() {
		return login;
	}

	public void setLogin(LoginMB login) {
		this.login = login;
	}

	public Integer get_producto() {
		return _producto;
	}

	public void set_producto(Integer _producto) {
		this._producto = _producto;
	}
	
	
	

}
