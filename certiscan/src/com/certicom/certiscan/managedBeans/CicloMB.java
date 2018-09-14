package com.certicom.certiscan.managedBeans;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import org.primefaces.context.RequestContext;

import com.certicom.certiscan.commons.Constante;
import com.certicom.certiscan.commons.FacesUtils;
import com.certicom.certiscan.commons.GenericBeans;
import com.certicom.certiscan.commons.Utils;
import com.certicom.certiscan.domain.Ciclo;
import com.certicom.certiscan.domain.Log;
import com.certicom.certiscan.domain.Menu;
import com.certicom.certiscan.domain.TipoCiclo;
import com.certicom.certiscan.obj.Periodo;
import com.certicom.certiscan.services.CicloService;
import com.certicom.certiscan.services.MenuServices;
import com.certicom.certiscan.services.TipoCicloService;

@ManagedBean(name="cicloMB")
@ViewScoped
public class CicloMB extends GenericBeans implements Serializable{

	
	private List<Ciclo> listaCiclos;
	private Ciclo cicloSelec;
	private Boolean editar;
	
	private CicloService cicloService;
	private MenuServices menuServices;
	private Date fechaActual;
	private Date fechaLimite;
	private List<TipoCiclo> listTiposCiclos;
	private TipoCicloService tipoCicloService;
	private List<Periodo> listaperiodomensuales;
	private Utils utils;
	private String anioActual;
	private List<String> listAnios;
	
	//datos Log
    private Log log;
	private LogMB logmb;
	
	public CicloMB(){}
	
	@PostConstruct
	public void inicia(){
		
		this.cicloSelec = new Ciclo();
		this.fechaActual = new Date();
		this.editar = Boolean.FALSE;
		this.tipoCicloService = new TipoCicloService();
		
		this.cicloService = new CicloService();
		this.menuServices=new MenuServices();
		this.utils = new Utils();
		/*DateFormat formatoFecha = new SimpleDateFormat("yyyy");
		this.anioActual=formatoFecha.format(this.fechaActual);
		System.out.println("AÑO ACTUAL: "+this.anioActual);*/
		log = (Log)getSpringBean(Constante.SESSION_LOG);
		logmb = new LogMB();
		
		try {
			//this.listAnios = obtenerAnios(this.anioActual);
			this.listaperiodomensuales = this.utils.armar_mes();
			this.listTiposCiclos= tipoCicloService.findAll();
			this.listaCiclos = this.cicloService.findAll();
			Menu codMenu = menuServices.opcionMenuByPretty("pretty:ciclo");
			log.setCod_menu(codMenu.getCod_menu().intValue());
			//int codMenu = menuServices.opcionMenuByPrettyCod("pretty:ciclo");
			//log.setCod_menu(codMenu);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/*private List<String> obtenerAnios(String anioActual2) {
		// TODO Auto-generated method stub
		Integer anio = Integer.parseInt(anioActual2);
		List<String> anios = new ArrayList<String>();
		for (int i = 0; i <= 10; i++) {
			
			anios.add(String.valueOf(anio+i));
		}
		return anios;
	}*/

	public void cambiarFecha(){
		this.fechaLimite = this.cicloSelec.getFecini();
		
	}
	
	private Ciclo getCicloItem(Integer id_ciclo){
		Ciclo item = new Ciclo();
		for (Ciclo ciclo : this.listaCiclos) {
			if(ciclo.getCod_ciclo().intValue() == id_ciclo.intValue() ){
				item = ciclo;
				break;
			}
		}
		return item;
	}
	
	private TipoCiclo getTipoCiclosItem(Integer id_tipoCiclo){
		TipoCiclo item = new TipoCiclo();
		for (TipoCiclo tipoCiclo : this.listTiposCiclos) {
			if(tipoCiclo.getCod_tipo_ciclo().intValue() == id_tipoCiclo.intValue() ){
				item = tipoCiclo;
				break;
			}
		}
		return item;
	}
	
	public void guardarCiclo(){
		Boolean valido=Boolean.TRUE;
		RequestContext context = RequestContext.getCurrentInstance();  
   	    context.addCallbackParam("esValido", valido);
		   	    
		try {
			
			TipoCiclo c = getTipoCiclosItem(this.cicloSelec.getCod_tipo_ciclo());
			
			//this.cicloSelec.setDescripcion(this.estadoSelec.getDescripcion().trim().toUpperCase());
			
			if(this.editar) {
				this.cicloService.actualizarCiclo(this.cicloSelec);
				 log.setAccion("UPDATE");
	             log.setDescripcion("Se actualiza el ciclo: " + this.cicloSelec.getMes() + this.cicloSelec.getAnio()+" del Tipo: "+c.getDescripcion());
	             logmb.insertarLog(log);
				FacesUtils.showFacesMessage("Ciclo ha sido actualizado", 3);
			}else{
				if(this.cicloService.buscarXPeriodo(this.cicloSelec.getMes(), this.cicloSelec.getAnio(), this.cicloSelec.getCod_tipo_ciclo()).size()==0){
				this.cicloService.crearCiclo(this.cicloSelec);
				log.setAccion("INSERT");
	            log.setDescripcion("Se inserta el ciclo : " + this.cicloSelec.getMes() + this.cicloSelec.getAnio()+" del Tipo: "+c.getDescripcion());
	            logmb.insertarLog(log);
				FacesUtils.showFacesMessage("Ciclo ha sido creado", 3);
				}else{
					
					context.addCallbackParam("esValido", valido);
					FacesUtils.showFacesMessage("Ya se creo el periodo",Constante.ADVERTENCIA);
					context.update("sms");
					context.update("msgGeneral");
				}
			}
			
			this.cicloSelec = new Ciclo();
			this.editar = Boolean.FALSE;
			
			this.listaCiclos = this.cicloService.findAll();
			context.update("msgGeneral");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/*public void cambiarCiclo(Estado estado){
		
		   if(estado.isEstado()){
			   estado.setEstado(Boolean.FALSE);
		   }else{
			   estado.setEstado(Boolean.TRUE);
		   }
		   
		   try {
			   this.estadoService.actualizarEstado(estado);
			   log.setAccion("CHANGE ESTADO");
	           log.setDescripcion("Se cambio el estado del Estado: " +estado.getDescripcion() +" a " + estado.isEstado());
	           logmb.insertarLog(log);
			   FacesUtils.showFacesMessage("Estado  modificado correctamente",Constante.INFORMACION);
			   this.listaEstados = this.estadoService.findAll();
		   } catch (Exception e) {
			   System.out.println("Error:"+e.getMessage());
			   e.printStackTrace();
		   }   
	}*/
	
	public void nuevoCiclo(){
		this.cicloSelec = new Ciclo();
		this.editar = Boolean.FALSE;
	}

	public void editarCiclo(Ciclo cic){
		this.cicloSelec = cic;
		this.editar = Boolean.TRUE;
	}
	
	public void eliminarCiclo(Ciclo cic){
		this.cicloSelec = cic;
	}
	
	
	public void confirmaEliminar(){
		try {
		
			Ciclo existeCiclo = this.cicloService.findByIdCiclo(this.cicloSelec.getCod_ciclo());
			
			TipoCiclo c = getTipoCiclosItem(this.cicloSelec.getCod_tipo_ciclo());
			
			if(existeCiclo==null){
				
				this.cicloService.eliminarCiclo(this.cicloSelec.getCod_ciclo());
				
				log.setAccion("DELETE");
				log.setDescripcion("Se elimina el CICLO: " + this.cicloSelec.getMes() + this.cicloSelec.getAnio()+" del Tipo: "+c.getDescripcion());
				logmb.insertarLog(log);
				FacesUtils.showFacesMessage("Ciclo ha sido eliminado", 3);				
				
			}else{
			
				FacesUtils.showFacesMessage("Ciclo no se eliminó porque tiene tarifas ingresadas", Constante.ERROR);	
			}
			
			this.listaCiclos = this.cicloService.findAll();
			
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

	public List<Ciclo> getListaCiclos() {
		for (Ciclo c : listaCiclos) {
			c.setNmes(this.utils.convertirANombre(c.getMes()));
		}
		return listaCiclos;
	}

	public void setListaCiclos(List<Ciclo> listaCiclos) {
		this.listaCiclos = listaCiclos;
	}

	public Ciclo getCicloSelec() {
		return cicloSelec;
	}

	public void setCicloSelec(Ciclo cicloSelec) {
		this.cicloSelec = cicloSelec;
	}

	public void setEditar(Boolean editar) {
		this.editar = editar;
	}

	public Date getFechaActual() {
		return fechaActual;
	}

	public void setFechaActual(Date fechaActual) {
		this.fechaActual = fechaActual;
	}

	public Date getFechaLimite() {
		return fechaLimite;
	}

	public void setFechaLimite(Date fechaLimite) {
		this.fechaLimite = fechaLimite;
	}

	public List<TipoCiclo> getListTiposCiclos() {
		return listTiposCiclos;
	}

	public void setListTiposCiclos(List<TipoCiclo> listTiposCiclos) {
		this.listTiposCiclos = listTiposCiclos;
	}

	public List<Periodo> getListaperiodomensuales() {
		return listaperiodomensuales;
	}

	public void setListaperiodomensuales(List<Periodo> listaperiodomensuales) {
		this.listaperiodomensuales = listaperiodomensuales;
	}

	public String getAnioActual() {
		return anioActual;
	}

	public void setAnioActual(String anioActual) {
		this.anioActual = anioActual;
	}

	public List<String> getListAnios() {
		return listAnios;
	}

	public void setListAnios(List<String> listAnios) {
		this.listAnios = listAnios;
	}

}
