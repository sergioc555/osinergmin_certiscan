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
import com.certicom.certiscan.domain.CategoriaIndicadoresIndice;
import com.certicom.certiscan.domain.Indice;
import com.certicom.certiscan.domain.Log;
import com.certicom.certiscan.domain.Menu;
import com.certicom.certiscan.domain.Negocio;
import com.certicom.certiscan.services.CategoriaIndicadoresIndiceServices;
import com.certicom.certiscan.services.IndiceServices;
import com.certicom.certiscan.services.MenuServices;
import com.certicom.certiscan.services.NegocioServices;


@ManagedBean(name="categoriaIndiceMB")
@ViewScoped
public class CategoriaIndicadoresIndiceMB extends GenericBeans implements Serializable{

	private CategoriaIndicadoresIndice categoriaIndicadoresIndice;
	private List<CategoriaIndicadoresIndice> listaCategoriaIndicadoresIndice;	
	private List<Negocio> listaNegocios;	
	private CategoriaIndicadoresIndice categoriaIndicadoresIndiceSelec;
	private List<Indice> listaIndicadorIndiceFilter;
	private Boolean editar;	
	private CategoriaIndicadoresIndiceServices categoriaIndicadoresIndiceServices;
	private MenuServices menuServices;
	private Log log;
	private LogMB logmb;
	private Indice indicadorIndiceSelec;
	private Indice indicadorIndiceReport;
	private List<Indice> listaIndicadorIndice;
	
	private NegocioServices negocioServices;	
	RequestContext context; 
	private IndiceServices indicadorIndiceServices;
	private String desNegocio;	
	private String _negocio;
	private String _negocion;	
	private Integer consultar;
	private Integer accion;
	private boolean bandtieneindicador;
	private String descAntind;
	@ManagedProperty(value= "#{loginMB}")
	private LoginMB login;
	
	public CategoriaIndicadoresIndiceMB(){}
	
	@PostConstruct
	public void inicia(){
		this.categoriaIndicadoresIndiceSelec = new CategoriaIndicadoresIndice();		
		this.editar = Boolean.FALSE;
		this.categoriaIndicadoresIndiceServices = new CategoriaIndicadoresIndiceServices();
		menuServices = new MenuServices();
		this.indicadorIndiceServices = new IndiceServices();
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
			Menu codMenu = menuServices.opcionMenuByPretty("pretty:indices");
			log.setCod_menu(codMenu.getCod_menu().intValue());
			log.setIdusuario(this.login.getIdUsuario());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();}
		
		
		
	}
	
	public void guardarCategoriaIndicadoresIndice(){
		Boolean valido=Boolean.TRUE;
		RequestContext context = RequestContext.getCurrentInstance();  
   	    context.addCallbackParam("esValido", valido);
		
		try {
			
			this.categoriaIndicadoresIndiceSelec.setDescripcion(this.categoriaIndicadoresIndiceSelec.getDescripcion().trim().toUpperCase());
			
			if(this.editar) {				
				this.categoriaIndicadoresIndiceServices.actualizarCategoriaIndicadoresIndice(this.categoriaIndicadoresIndiceSelec);
				FacesUtils.showFacesMessage("Categoria Indicadores Indice ha sido actualizado", 3);
				log.setAccion("UPDATE");
		        log.setDescripcion("El usuario "+this.login.getLoginUsuario()+" actualizó la categoria indicador de call: "+this.categoriaIndicadoresIndiceSelec.getDescripcion());
		        logmb.insertarLog(log);
			}else{				
				this.categoriaIndicadoresIndiceServices.crearCategoriaIndicadoresCall(this.categoriaIndicadoresIndiceSelec);
				FacesUtils.showFacesMessage("Categoria Indicadores Indice ha sido creado", 3);
				log.setAccion("INSERT");
		        log.setDescripcion("El usuario "+this.login.getLoginUsuario()+" registró la categoria indicador : " + this.categoriaIndicadoresIndiceSelec.getDescripcion());
		        logmb.insertarLog(log);
		        //this._producto=String.valueOf(this.categoriaIndicadoresCallSelec.getId_producto());
			}
			
			this.categoriaIndicadoresIndiceSelec = new CategoriaIndicadoresIndice();
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
	
	public void guardarIndicadorIndice(){
		Boolean valido=Boolean.TRUE;
		RequestContext context = RequestContext.getCurrentInstance();  
   	    
		
   	    boolean existe=false;
   	    
   	    String indicadorIndice = this.indicadorIndiceSelec.getDescripcion();
		try {
			
			List<Indice> listasearch = this.indicadorIndiceServices.listaIndxTipoDocumento(this.indicadorIndiceSelec.getId_tipo_documento());
			List<Indice> listasearch1 = this.indicadorIndiceServices.listaIndxTipoDato(this.indicadorIndiceSelec.getId_tipo_dato());
			
			for(Indice indcall: listasearch){
				
				if (indcall.getDescripcion().equals(this.indicadorIndiceSelec.getDescripcion())){
					existe=true;
					break;
				}
			}
			
			for(Indice indcall: listasearch1){
				
				if (indcall.getDescripcion().equals(this.indicadorIndiceSelec.getDescripcion())){
					existe=true;
					break;
				}
			}
			
			if(this.editar){
				if(this.indicadorIndiceSelec.getDescripcion().equals(descAntind)){
					existe = false;
				}
			}
			
			
			if (existe == false){
			
			this.indicadorIndiceSelec.setId_tipo_documento(this.indicadorIndiceSelec.getId_tipo_documento());
			this.indicadorIndiceSelec.setId_tipo_dato(this.indicadorIndiceSelec.getId_tipo_dato());
			this.indicadorIndiceSelec.setDescripcion(this.indicadorIndiceSelec.getDescripcion().trim().toUpperCase());
			
			if(this.editar) {				
				this.indicadorIndiceServices.actualizarIndice(this.indicadorIndiceSelec);
				FacesUtils.showFacesMessage("Indicador ha sido actualizado", 3);				
		        for(CategoriaIndicadoresIndice catind: this.listaCategoriaIndicadoresIndice){
					if (catind.getId_categoria_call().equals(this.indicadorIndiceSelec.getId_tipo_documento())){
						catind.setListIndicadorIndice(this.indicadorIndiceServices.listaIndxTipoDocumento(this.indicadorIndiceSelec.getId_tipo_documento()));
						catind.setListIndicadorIndice(this.indicadorIndiceServices.listaIndxTipoDato(this.indicadorIndiceSelec.getId_tipo_dato()));
					}
				}
		        
			}else{					
				this.indicadorIndiceServices.crearIndice(this.indicadorIndiceSelec);
				FacesUtils.showFacesMessage("Indicador ha sido creado", 3);				
		        }
			
			for(CategoriaIndicadoresIndice catind: this.listaCategoriaIndicadoresIndice){
				if (catind.getId_categoria_call().equals(this.indicadorIndiceSelec.getId_tipo_documento())){
					catind.setListIndicadorIndice(this.indicadorIndiceServices.listaIndxTipoDocumento(this.indicadorIndiceSelec.getId_tipo_documento()));
					catind.setListIndicadorIndice(this.indicadorIndiceServices.listaIndxTipoDato(this.indicadorIndiceSelec.getId_tipo_dato()));
				}
			}
			
			this.indicadorIndiceSelec = new Indice();
			this.editar = Boolean.FALSE;		
			//this.listaIndicadorCall = this.indicadorCallServices.findAll();
			context.addCallbackParam("esValido", valido);
			context.update("msgGeneral");
			
			if(consultar == 1){
				obtenerToogle();
			}
			
			
			}else{
				this.indicadorIndiceSelec = new Indice();
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
	
	public void cambiarEstado(CategoriaIndicadoresIndice ctgIndice){
		   String estado="";
		   if(ctgIndice.isEstado()){
			   ctgIndice.setEstado(Boolean.FALSE);
			   estado="INACTIVO";
		   }else{
			   ctgIndice.setEstado(Boolean.TRUE);
			   estado="ACTIVO";
		   }
		   
		   try {			 
			   this.categoriaIndicadoresIndiceServices.actualizarCategoriaIndicadoresIndice(ctgIndice);
			   FacesUtils.showFacesMessage("Categoria Indicadores Call modificado correctamente",Constante.INFORMACION);			  
			   this.listaCategoriaIndicadoresIndice = this.categoriaIndicadoresIndiceServices.findAll();
			   log.setAccion("UPDATE");
	           log.setDescripcion("El usuario "+this.login.getLoginUsuario()+" actualizó el estado de la categoria indicadores call: "+ctgIndice.getDescripcion()+" a : " + estado);
	           logmb.insertarLog(log);
	           
		   } catch (Exception e) {
			   //System.out.println("Error:"+e.getMessage());
			   e.printStackTrace();
		   }   
		   
		   obtenerToogle();
	}
	

	
	
	public void nuevoIndicadorCall(CategoriaIndicadoresIndice p){
		
		try {
			this.indicadorIndiceSelec = new Indice();
			this.indicadorIndiceSelec.setDes_ctgCall(this.categoriaIndicadoresIndiceServices.findById(p.getId_categoria_call()).getDescripcion());
			this.indicadorIndiceSelec.setId_tipo_documento(p.getId_categoria_call());
			this.indicadorIndiceSelec.setId_tipo_dato(p.getId_categoria_call());
			this.indicadorIndiceSelec.setEstado(Boolean.TRUE);
			this.editar = Boolean.FALSE;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void nuevoCategoriaIndicadoresIndice(){
		
		try {
			this.categoriaIndicadoresIndiceSelec = new CategoriaIndicadoresIndice();
			this.categoriaIndicadoresIndiceSelec.setEstado(Boolean.TRUE);
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

	public void editarCategoriaIndicadoresIndice(CategoriaIndicadoresIndice ctgCall){
		this.categoriaIndicadoresIndiceSelec = ctgCall;
		this.editar = Boolean.TRUE;
	}
	
	public void editarIndicadorIndice(Indice indCall){
		this.indicadorIndiceSelec = indCall;
		this.editar = Boolean.TRUE;
		this.descAntind = indicadorIndiceSelec.getDescripcion();
	}
	
	public void eliminarCategoriaIndicadoresIndice(CategoriaIndicadoresIndice ctgCall){
		this.categoriaIndicadoresIndiceSelec = ctgCall;
	}
	
	public void eliminarIndicadorCall(Indice indCall){
		this.indicadorIndiceSelec = indCall;
	}
	
	public void confirmaEliminarIndicador(){
		try {
		
			this.indicadorIndiceServices.eliminarIndice(this.indicadorIndiceSelec.getId_indice());
			FacesUtils.showFacesMessage("Indicador ha sido eliminado", 3);
			
	        for(CategoriaIndicadoresIndice catind: this.listaCategoriaIndicadoresIndice){
				if (catind.getId_categoria_call().equals(this.indicadorIndiceSelec.getId_indice())){
					catind.setListIndicadorIndice(this.indicadorIndiceServices.listaIndxTipoDocumento(this.indicadorIndiceSelec.getId_tipo_documento()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//obtenerToogle();
		
	}
	
	public void confirmaEliminarCategoria(){
		
		try{			
			Integer cant = categoriaIndicadoresIndiceServices.listIndicadorxCategroriaIndice(this.categoriaIndicadoresIndiceSelec.getId_categoria_call());
			
			if(cant > 0){
		
				FacesUtils.showFacesMessage("Categoria Indicador no se puede eliminar porque tiene un Indicador asociado", Constante.ERROR);
				
			} else{
				
				
				this.categoriaIndicadoresIndiceServices.eliminarCategoriaIndicadoresIndice(this.categoriaIndicadoresIndiceSelec.getId_categoria_call());
				this.listaCategoriaIndicadoresIndice = this.categoriaIndicadoresIndiceServices.findAll();
				
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
			this.listaCategoriaIndicadoresIndice = this.categoriaIndicadoresIndiceServices.findByProducto(Integer.valueOf(1));
			for(CategoriaIndicadoresIndice catindc: listaCategoriaIndicadoresIndice){
				List<Indice> indaux = this.indicadorIndiceServices.listaIndxTipoDocumento(catindc.getId_categoria_call());
				
				catindc.setListIndicadorIndice(indaux);
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

	public CategoriaIndicadoresIndice getCategoriaIndicadoresIndice() {
		return categoriaIndicadoresIndice;
	}

	public void setCategoriaIndicadoresIndice(
			CategoriaIndicadoresIndice categoriaIndicadoresIndice) {
		this.categoriaIndicadoresIndice = categoriaIndicadoresIndice;
	}

	public List<CategoriaIndicadoresIndice> getListaCategoriaIndicadoresIndice() {
		return listaCategoriaIndicadoresIndice;
	}

	public void setListaCategoriaIndicadoresIndice(
			List<CategoriaIndicadoresIndice> listaCategoriaIndicadoresIndice) {
		this.listaCategoriaIndicadoresIndice = listaCategoriaIndicadoresIndice;
	}

	public CategoriaIndicadoresIndice getCategoriaIndicadoresIndiceSelec() {
		return categoriaIndicadoresIndiceSelec;
	}

	public void setCategoriaIndicadoresIndiceSelec(
			CategoriaIndicadoresIndice categoriaIndicadoresIndiceSelec) {
		this.categoriaIndicadoresIndiceSelec = categoriaIndicadoresIndiceSelec;
	}

	public List<Indice> getListaIndicadorIndiceFilter() {
		return listaIndicadorIndiceFilter;
	}

	public void setListaIndicadorIndiceFilter(
			List<Indice> listaIndicadorIndiceFilter) {
		this.listaIndicadorIndiceFilter = listaIndicadorIndiceFilter;
	}

	public CategoriaIndicadoresIndiceServices getCategoriaIndicadoresIndiceServices() {
		return categoriaIndicadoresIndiceServices;
	}

	public void setCategoriaIndicadoresIndiceServices(
			CategoriaIndicadoresIndiceServices categoriaIndicadoresIndiceServices) {
		this.categoriaIndicadoresIndiceServices = categoriaIndicadoresIndiceServices;
	}

	public Indice getIndicadorIndiceSelec() {
		return indicadorIndiceSelec;
	}

	public void setIndicadorIndiceSelec(Indice indicadorIndiceSelec) {
		this.indicadorIndiceSelec = indicadorIndiceSelec;
	}

	public Indice getIndicadorIndiceReport() {
		return indicadorIndiceReport;
	}

	public void setIndicadorIndiceReport(Indice indicadorIndiceReport) {
		this.indicadorIndiceReport = indicadorIndiceReport;
	}

	public List<Indice> getListaIndicadorIndice() {
		return listaIndicadorIndice;
	}

	public void setListaIndicadorIndice(List<Indice> listaIndicadorIndice) {
		this.listaIndicadorIndice = listaIndicadorIndice;
	}

	public IndiceServices getIndicadorIndiceServices() {
		return indicadorIndiceServices;
	}

	public void setIndicadorIndiceServices(IndiceServices indicadorIndiceServices) {
		this.indicadorIndiceServices = indicadorIndiceServices;
	}

	public boolean isBandtieneindicador() {
		return bandtieneindicador;
	}

	public void setBandtieneindicador(boolean bandtieneindicador) {
		this.bandtieneindicador = bandtieneindicador;
	}

	public String getDescAntind() {
		return descAntind;
	}

	public void setDescAntind(String descAntind) {
		this.descAntind = descAntind;
	}


}
