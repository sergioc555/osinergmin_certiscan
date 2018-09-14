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
import com.certicom.certiscan.domain.CategoriaIndicadoresCall;
import com.certicom.certiscan.domain.IndicadorCall;
import com.certicom.certiscan.domain.Log;
import com.certicom.certiscan.domain.Menu;
import com.certicom.certiscan.domain.SubIndicadorCall;
import com.certicom.certiscan.services.CategoriaIndicadoresCallServices;
import com.certicom.certiscan.services.IndicadorCallServices;
import com.certicom.certiscan.services.MenuServices;
import com.certicom.certiscan.services.SubIndicadorCallServices;

@ManagedBean(name="indicadorCallMB")
@ViewScoped
public class IndicadorCallMB extends GenericBeans implements Serializable{

	private IndicadorCall indicadorCall;
	private List<IndicadorCall> listaIndicadorCall;
	private List<IndicadorCall> listaIndicadorCallFilter;
	private List<SubIndicadorCall> listaSubIndicadorCall;
	private List<SubIndicadorCall> listaSubIndicadorCallFilter;
	//private List<CategoriaIndicadoresCall> listaCategoriaIndicadoresCall;
	private List<CategoriaIndicadoresCall> listCategoriaIndicadoresCall;
	private IndicadorCall indicadorCallSelec;
	private SubIndicadorCall subindicadorCallSelec;
	private SubIndicadorCall subindicadorCall;
	private Boolean editar;	
	private Boolean editarsub;
	private IndicadorCallServices indicadorCallServices;
	private SubIndicadorCallServices subindicadorCallServices;
	private CategoriaIndicadoresCallServices categoriaIndicadoresCallServices;
	private MenuServices menuServices;
	private Log log;
	private LogMB logmb;
	RequestContext context; 
	@ManagedProperty(value= "#{loginMB}")
	private LoginMB login;
	
	
	public IndicadorCallMB(){}
	
	@PostConstruct
	public void inicia(){
		
		this.indicadorCallSelec = new IndicadorCall();	
		this.subindicadorCallSelec = new SubIndicadorCall();	
		this.subindicadorCall = new SubIndicadorCall();
		this.editar = Boolean.FALSE;
		this.indicadorCallServices = new IndicadorCallServices();
		this.subindicadorCallServices = new SubIndicadorCallServices();
		this.listCategoriaIndicadoresCall = new ArrayList<CategoriaIndicadoresCall>();
		this.listaSubIndicadorCall = new ArrayList<SubIndicadorCall>();
		this.categoriaIndicadoresCallServices = new CategoriaIndicadoresCallServices();
		menuServices = new MenuServices();
		
		
		
		log = (Log)getSpringBean(Constante.SESSION_LOG);
		logmb = new LogMB();
		try {
			obtenerToogle();
			Menu codMenu = menuServices.opcionMenuByPretty("pretty:indicadoresCall");
			log.setCod_menu(codMenu.getCod_menu().intValue());
			log.setIdusuario(this.login.getIdUsuario());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();}
	}
	
	public void guardarIndicadorCall(){
		Boolean valido=Boolean.TRUE;
		RequestContext context = RequestContext.getCurrentInstance();  
   	    
		
   	    boolean existe=false;
   	    
		try {
			
			List<IndicadorCall> listasearch = this.indicadorCallServices.listaIndxCategoria(this.indicadorCallSelec.getId_categoria_call());
			
			for(IndicadorCall indcall: listasearch){
				
				if (indcall.getDescripcion().equals(this.indicadorCallSelec.getDescripcion())){
					existe=true;
					break;
				}
			}
			
			if (existe == false){
			
			this.indicadorCallSelec.setId_categoria_call(this.indicadorCallSelec.getId_categoria_call());
			this.indicadorCallSelec.setDescripcion(this.indicadorCallSelec.getDescripcion().trim().toUpperCase());
			
			if(this.editar) {		
				this.indicadorCallServices.actualizarIndicadorCall(this.indicadorCallSelec);
				FacesUtils.showFacesMessage("Indicador Call ha sido actualizado", 3);
				log.setAccion("UPDATE");
		        log.setDescripcion("El usuario "+this.login.getLoginUsuario()+" actualizó el indicador call: "+this.indicadorCallSelec.getDescripcion());
		        logmb.insertarLog(log);
		        
			}else{					
				this.indicadorCallServices.crearIndicadorCall(this.indicadorCallSelec);
				FacesUtils.showFacesMessage("Indicador Call ha sido creado", 3);	
				log.setAccion("INSERT");
		        log.setDescripcion("El usuario "+this.login.getLoginUsuario()+" insertó el indicador call : " + this.indicadorCallSelec.getDescripcion());
		        logmb.insertarLog(log);
		        }
			
			this.indicadorCallSelec = new IndicadorCall();
			this.editar = Boolean.FALSE;		
			//this.listaIndicadorCall = this.indicadorCallServices.findAll();
			context.addCallbackParam("esValido", valido);
			context.update("msgGeneral");
			
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
		
		obtenerToogle();
	}
	
	public void guardarSubIndicadorCall(){
		Boolean valido=Boolean.TRUE;
		RequestContext context = RequestContext.getCurrentInstance();  
   	    
   	    
   	    boolean existe=false;
		
		try {
			//this.indicadorCallSelec.setId_categoria_call(this.indicadorCallSelec.getId_categoria_call());
			//this.indicadorCallSelec.setDescripcion(this.indicadorCallSelec.getDescripcion().trim().toUpperCase());
			
			List<SubIndicadorCall> listasearch = this.subindicadorCallServices.findById(this.subindicadorCall.getId_indicadores_call(), this.subindicadorCall.getId_categoria_call());
			
			for(SubIndicadorCall sbindcall: listasearch){
				
				if (sbindcall.getDescripcion().equals(this.subindicadorCall.getDescripcion())){
					existe=true;
					break;
				}
			}
			
			if (existe == false){
			
			if(this.editarsub) {		
				this.subindicadorCallServices.actualizarSubIndicadorCall(this.subindicadorCall);
				FacesUtils.showFacesMessage("Sub Indicador Call ha sido actualizado", 3);
				log.setAccion("UPDATE");
		        log.setDescripcion("El usuario "+this.login.getLoginUsuario()+" actualizó el sub indicador call: "+this.subindicadorCall.getDescripcion());
		        logmb.insertarLog(log);
		        
			}else{					
				this.subindicadorCallServices.crearSubIndicadorCall(this.subindicadorCall);
				FacesUtils.showFacesMessage("Sub Indicador Call ha sido creado", 3);	
				log.setAccion("INSERT");
		        log.setDescripcion("El usuario "+this.login.getLoginUsuario()+" insertó el sub indicador call : " + this.subindicadorCall.getDescripcion());
		        logmb.insertarLog(log);
		        }
			
			this.subindicadorCall = new SubIndicadorCall();
			this.editar = Boolean.FALSE;		
			//this.listaIndicadorCall = this.indicadorCallServices.findAll();
			context.addCallbackParam("esValido", valido);
			context.update("msgGeneral");
			}else{
				this.subindicadorCall = new SubIndicadorCall();
				FacesUtils.showFacesMessage("Ya existe este sub indicador para este indicador", 1);
				valido=Boolean.FALSE;
				context.addCallbackParam("esValido", valido);
				context.update("msgGeneral");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		obtenerToogle();
		
	}
	
	public void cambiarEstado(IndicadorCall indCall){
		
		   if(indCall.isEstado()){
			   indCall.setEstado(Boolean.FALSE);
		   }else{
			   indCall.setEstado(Boolean.TRUE);
		   }
		   
		   try {			 
			   this.indicadorCallServices.actualizarIndicadorCall(indCall);
			   FacesUtils.showFacesMessage("Indicador Call modificado correctamente",Constante.INFORMACION);			  
			   //this.listaIndicadorCall = this.indicadorCallServices.findAll();
			   log.setAccion("UPDATE");
	           log.setDescripcion("El usuario "+this.login.getLoginUsuario()+" actualizó el estado del indicador call "+indCall.getDescripcion()+" a  : " + indCall.isEstado());
	           logmb.insertarLog(log);
		   } catch (Exception e) {
			   System.out.println("Error:"+e.getMessage());
			   e.printStackTrace();
		   }   
		   
		   obtenerToogle();
	}
	
	public void nuevoIndicadorCall(){
		this.indicadorCallSelec = new IndicadorCall();
		this.indicadorCallSelec.setEstado(Boolean.TRUE);
		this.editar = Boolean.FALSE;
	}

	public void nuevoSubIndicadorCall(IndicadorCall p){
		this.subindicadorCall = new SubIndicadorCall();
		try {
			this.subindicadorCall.setId_categoria_call(p.getId_categoria_call());
			this.subindicadorCall.setId_indicadores_call(p.getId_indicadores_call());
			this.subindicadorCall.setCategoria(this.categoriaIndicadoresCallServices.findById(p.getId_categoria_call()).getDescripcion());
			this.subindicadorCall.setIndicador(this.indicadorCallServices.findById(p.getId_indicadores_call()).getDescripcion());
			
			//this.subindicadorCall.setCategoria(this.categoriaIndicadoresCallServices.findById(this.subindicadorCallSelec.getId_categoria_call()).getDescripcion());
			//this.subindicadorCall.setIndicador(this.indicadorCallServices.findById(this.subindicadorCallSelec.getId_indicadores_call()).getDescripcion());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.editarsub = Boolean.FALSE;
	}
	
	public void editarIndicadorCall(IndicadorCall indCall){
		this.indicadorCallSelec = indCall;
		this.editar = Boolean.TRUE;
	}
	
	public void editarSubIndicadorCall(SubIndicadorCall subindCall){
		this.subindicadorCall = subindCall;
		try {
			this.subindicadorCall.setCategoria(this.categoriaIndicadoresCallServices.findById(subindCall.getId_categoria_call()).getDescripcion());
			this.subindicadorCall.setIndicador(this.indicadorCallServices.findById(subindCall.getId_indicadores_call()).getDescripcion());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.editarsub = Boolean.TRUE;
	}
	
	public void eliminarIndicadorCall(IndicadorCall indCall){
		this.indicadorCallSelec = indCall;
	}
	
	public void eliminarSubIndicadorCall(SubIndicadorCall subindCall){
		this.subindicadorCall = subindCall;
	}
	
	public void confirmaEliminar(){
		try {
		
			this.indicadorCallServices.eliminarIndicadorCall(this.indicadorCallSelec.getId_indicadores_call());
			FacesUtils.showFacesMessage("Indicador Call ha sido eliminado", 3);
			//this.listaIndicadorCall = this.indicadorCallServices.findAll();
			log.setAccion("DELETE");
	        log.setDescripcion("El usuario "+this.login.getLoginUsuario()+" ha eliminado  el indicador call: " + this.indicadorCallSelec.getDescripcion());
	        logmb.insertarLog(log);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		obtenerToogle();
		
	}
	
	public void confirmaEliminar1(){
		try {
		
			this.subindicadorCallServices.eliminarSubIndicadorCall(this.subindicadorCall.getIdsubindicadores_call());
			FacesUtils.showFacesMessage("Sub Indicador Call ha sido eliminado", 3);
			//this.listaIndicadorCall = this.indicadorCallServices.findAll();
			log.setAccion("DELETE");
	        log.setDescripcion("El usuario "+this.login.getLoginUsuario()+" ha eliminado el sub indicador call : " + this.subindicadorCall.getDescripcion());
	        logmb.insertarLog(log);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		obtenerToogle();
	}
	

	
	public void obtenerToogle(){
		try {
			this.listCategoriaIndicadoresCall = this.categoriaIndicadoresCallServices.listaCategoriaIndicadoresCallActivo();
			for(CategoriaIndicadoresCall catindc: listCategoriaIndicadoresCall){
				List<IndicadorCall> indaux = this.indicadorCallServices.listaIndxCategoria(catindc.getId_categoria_call());
				for(IndicadorCall indc: indaux){
					List<SubIndicadorCall> subaux = this.listaSubIndicadorCall = this.subindicadorCallServices.findById(indc.getId_indicadores_call(), indc.getId_categoria_call());
					indc.setListSubIndicadorCall(subaux);
				}
				catindc.setListIndicadorCall(indaux);
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

	public IndicadorCall getIndicadorCall() {
		return indicadorCall;
	}

	public void setIndicadorCall(IndicadorCall indicadorCall) {
		this.indicadorCall = indicadorCall;
	}

	public List<IndicadorCall> getListaIndicadorCall() {
		return listaIndicadorCall;
	}

	public void setListaIndicadorCall(List<IndicadorCall> listaIndicadorCall) {
		this.listaIndicadorCall = listaIndicadorCall;
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

	public List<CategoriaIndicadoresCall> getListCategoriaIndicadoresCall() {
		return listCategoriaIndicadoresCall;
	}

	public void setListCategoriaIndicadoresCall(
			List<CategoriaIndicadoresCall> listCategoriaIndicadoresCall) {
		this.listCategoriaIndicadoresCall = listCategoriaIndicadoresCall;
	}

	public List<SubIndicadorCall> getListaSubIndicadorCall() {
		return listaSubIndicadorCall;
	}

	public void setListaSubIndicadorCall(
			List<SubIndicadorCall> listaSubIndicadorCall) {
		this.listaSubIndicadorCall = listaSubIndicadorCall;
	}

	public List<SubIndicadorCall> getListaSubIndicadorCallFilter() {
		return listaSubIndicadorCallFilter;
	}

	public void setListaSubIndicadorCallFilter(
			List<SubIndicadorCall> listaSubIndicadorCallFilter) {
		this.listaSubIndicadorCallFilter = listaSubIndicadorCallFilter;
	}

	public SubIndicadorCall getSubindicadorCallSelec() {
		return subindicadorCallSelec;
	}

	public void setSubindicadorCallSelec(SubIndicadorCall subindicadorCallSelec) {
		this.subindicadorCallSelec = subindicadorCallSelec;
	}

	public SubIndicadorCall getSubindicadorCall() {
		return subindicadorCall;
	}

	public void setSubindicadorCall(SubIndicadorCall subindicadorCall) {
		this.subindicadorCall = subindicadorCall;
	}

	public Boolean getEditarsub() {
		return editarsub;
	}

	public void setEditarsub(Boolean editarsub) {
		this.editarsub = editarsub;
	}

	public LoginMB getLogin() {
		return login;
	}

	public void setLogin(LoginMB login) {
		this.login = login;
	}

    


}
