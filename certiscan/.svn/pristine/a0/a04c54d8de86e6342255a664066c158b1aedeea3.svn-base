package com.certicom.certiscan.managedBeans;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import org.primefaces.context.RequestContext;
import org.primefaces.event.TabChangeEvent;

import com.certicom.certiscan.commons.Constante;
import com.certicom.certiscan.commons.FacesUtils;
import com.certicom.certiscan.commons.GenericBeans;
import com.certicom.certiscan.commons.Utils;
import com.certicom.certiscan.domain.Ciclo;
import com.certicom.certiscan.domain.Log;
import com.certicom.certiscan.domain.Menu;
import com.certicom.certiscan.domain.Tarifa;
import com.certicom.certiscan.domain.TipoCiclo;
import com.certicom.certiscan.services.CicloService;
import com.certicom.certiscan.services.MenuServices;
import com.certicom.certiscan.services.TarifaService;
import com.certicom.certiscan.services.TipoCicloService;

@ManagedBean(name="tarifaMB")
@ViewScoped
public class TarifaMB extends GenericBeans implements Serializable{

	
	private List<Ciclo> listaCiclosFacturacion;
	private List<Ciclo> listaCiclosPlanilla;
	private List<Ciclo> listaCiclosInactivosFacturacion;
	private List<Ciclo> listaCiclosInactivosPlanilla;
	private Tarifa tarfacSelec;
	private Tarifa tarplaSelec;
	private Tarifa tarfacSelecRep;
	private Tarifa tarplaSelecRep;
	private Boolean editar;
	
	private CicloService cicloService;
	private MenuServices menuServices;
	private Utils utils;
	private TipoCicloService tipoCicloService;
	private Boolean mostrarTope;
	private Boolean mostrarFecha;
	private TarifaService tarServices;
	private List<Tarifa> listaTarifaFacturacion;
	private List<Tarifa> listaTarifaPlanilla;
	private Ciclo cicloSelec;
	private TipoCiclo tipoCicloSelec;
	private Integer cod_ciclo_pla;
	private Integer cod_ciclo_fac;
	private String ciclo_pla;
	private String ciclo_fac;
	private String tipoCiclo;
	private Boolean mostrarTablaPla;
	private Boolean mostrarTablaFac;
	
	//datos Log
    private Log log;
	private LogMB logmb;
	
	public TarifaMB(){}
	
	@PostConstruct
	public void inicia(){
		
		this.editar = Boolean.FALSE;
		
		this.cicloService = new CicloService();
		this.menuServices=new MenuServices();
		this.tipoCicloService = new TipoCicloService();
		this.tarServices = new TarifaService();
		this.utils = new Utils();
		this.mostrarTope=true;
		this.tarfacSelec = new Tarifa();
		this.tarfacSelecRep = new Tarifa();
		this.tarplaSelec = new Tarifa();
		this.tarplaSelecRep = new Tarifa();
		this.mostrarTablaPla=false;
		
		try {
			this.tipoCicloSelec = this.tipoCicloService.findById(1);
			this.tipoCiclo = this.tipoCicloSelec.getDescripcion();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		log = (Log)getSpringBean(Constante.SESSION_LOG);
		logmb = new LogMB();
		
		try {
			this.listaCiclosFacturacion = this.cicloService.findByTipoCicloActivo(1);
			if(this.listaCiclosFacturacion.size()==0){
				this.listaCiclosFacturacion = this.cicloService.findByTipoCicloActivoSinTablero(1);
			}
			this.listaCiclosPlanilla = this.cicloService.findByTipoCicloActivo(2);
			if(this.listaCiclosPlanilla.size()==0){
				this.listaCiclosPlanilla = this.cicloService.findByTipoCicloActivoSinTablero(2);
			}
			this.listaCiclosInactivosFacturacion = this.cicloService.findByTipoCicloInactivo(1);
			this.listaCiclosInactivosPlanilla = this.cicloService.findByTipoCicloInactivo(2);
			//int codMenu = menuServices.opcionMenuByPrettyCod("pretty:tarifa");
			//log.setCod_menu(codMenu);
			Menu codMenu = menuServices.opcionMenuByPretty("pretty:tarifa");
			log.setCod_menu(codMenu.getCod_menu().intValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}	
		
	public void nuevoTableroPlanilla(){
		Boolean valido=Boolean.TRUE;
		RequestContext context = RequestContext.getCurrentInstance();  
   	    
   	    
	   	    if(this.cod_ciclo_pla > 0){
	   	    
			try {
				this.cicloSelec = new Ciclo();
				this.cicloSelec = this.cicloService.findById(this.cod_ciclo_pla);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			//c.setNfecini(sdf.format(c.getFecini()));
			//c.setNfecfin(sdf.format(c.getFecfin()));
			this.ciclo_pla = this.utils.convertirANombre(this.cicloSelec.getMes()) + " - " + this.cicloSelec.getAnio() + " - " + sdf.format(this.cicloSelec.getFecini()) + " - " + sdf.format(this.cicloSelec.getFecfin());
			this.tarplaSelec = new Tarifa();
			this.editar = Boolean.FALSE;
			this.tarplaSelec.setCod_tipo_ciclo(2);
			//this.tabcomSelec.setNtipociclo("COMISION");
			valido=Boolean.FALSE;
			context.addCallbackParam("esValido", valido);
	   	    }else{
	   	    		valido=Boolean.TRUE;
	   	    		context.addCallbackParam("esValido", valido);
					FacesUtils.showFacesMessage("Eliga un ciclo",Constante.ADVERTENCIA);
					context.update("sms");
					context.update("msgGeneral");
				
	   	    }
	}
	
	public void nuevoTableroFacturacion(){
		Boolean valido=Boolean.TRUE;
		RequestContext context = RequestContext.getCurrentInstance();  
   	    
   	    
   	    if(this.cod_ciclo_fac > 0){
   	    
		try {
			this.cicloSelec = new Ciclo();
			this.cicloSelec = this.cicloService.findById(this.cod_ciclo_fac);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		//c.setNfecini(sdf.format(c.getFecini()));
		//c.setNfecfin(sdf.format(c.getFecfin()));
		this.ciclo_fac = this.utils.convertirANombre(this.cicloSelec.getMes()) + " - " + this.cicloSelec.getAnio() + " - " + sdf.format(this.cicloSelec.getFecini()) + " - " + sdf.format(this.cicloSelec.getFecfin());
		this.tarfacSelec = new Tarifa();
		this.editar = Boolean.FALSE;
		this.tarfacSelec.setCod_tipo_ciclo(1);
		//this.tabcomSelec.setNtipociclo("COMISION");
		valido=Boolean.FALSE;
		context.addCallbackParam("esValido", valido);
   	    }else{
   	    		valido=Boolean.TRUE;
   	    		context.addCallbackParam("esValido", valido);
				FacesUtils.showFacesMessage("Eliga un ciclo",Constante.ADVERTENCIA);
				context.update("sms");
				context.update("msgGeneral");
			
   	    }
	}
	
	public void nuevaReplicaFacturacion(){
		Boolean valido=Boolean.TRUE;
		RequestContext context = RequestContext.getCurrentInstance();  
		
		if(this.cod_ciclo_fac > 0){
			this.cicloSelec = new Ciclo();
			try {
				this.cicloSelec = this.cicloService.findById(this.cod_ciclo_fac);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			this.ciclo_fac = this.utils.convertirANombre(this.cicloSelec.getMes()) + " - " + this.cicloSelec.getAnio() + " - " + sdf.format(this.cicloSelec.getFecini()) + " - " + sdf.format(this.cicloSelec.getFecfin());
			valido=Boolean.FALSE;
			context.addCallbackParam("esValido", valido);
		}else{
			valido=Boolean.TRUE;
	    		context.addCallbackParam("esValido", valido);
			FacesUtils.showFacesMessage("Eliga un ciclo",Constante.ADVERTENCIA);
			context.update("sms");
			context.update("msgGeneral");
			
		}
		
		
	}
	
	public void nuevaReplicaPlanilla(){
		Boolean valido=Boolean.TRUE;
		RequestContext context = RequestContext.getCurrentInstance();  
		
		if(this.cod_ciclo_pla > 0){
			this.cicloSelec = new Ciclo();
			try {
				this.cicloSelec = this.cicloService.findById(this.cod_ciclo_pla);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			this.ciclo_pla = this.utils.convertirANombre(this.cicloSelec.getMes()) + " - " + this.cicloSelec.getAnio() + " - " + sdf.format(this.cicloSelec.getFecini()) + " - " + sdf.format(this.cicloSelec.getFecfin());
			valido=Boolean.FALSE;
			context.addCallbackParam("esValido", valido);
		}else{
			valido=Boolean.TRUE;
	    		context.addCallbackParam("esValido", valido);
			FacesUtils.showFacesMessage("Eliga un ciclo",Constante.ADVERTENCIA);
			context.update("sms");
			context.update("msgGeneral");
			
		}
		
		
	}
	
	public void replicaTableroPlanilla(){
		try {
			Boolean valido=Boolean.TRUE;
			RequestContext context = RequestContext.getCurrentInstance();  
			
			if(this.tarplaSelecRep.getCod_ciclo() > 0){
				List<Tarifa> listaTarifaPlanillaAReplicar= new ArrayList<Tarifa>();
				for (Tarifa tabpla : this.listaTarifaPlanilla) {
					Tarifa tabplatemp= tabpla;
					tabplatemp.setCod_ciclo(this.tarplaSelecRep.getCod_ciclo());
					listaTarifaPlanillaAReplicar.add(tabplatemp);
				}
					Ciclo cicloAReplicar = this.cicloService.findById(this.cod_ciclo_fac);
					this.tarServices.insertBatchTarifa(listaTarifaPlanillaAReplicar);
					log.setAccion("INSERT");
					DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			        log.setDescripcion("Se replicó la tarifa Del ciclo: "+this.ciclo_fac + " para el ciclo: " + this.utils.convertirANombre(cicloAReplicar.getMes()) + " - " + cicloAReplicar.getAnio() + " - " + sdf.format(cicloAReplicar.getFecini()) + " - " + sdf.format(cicloAReplicar.getFecfin()));
			        logmb.insertarLog(log);
					Ciclo cicloG = new Ciclo();
					cicloG.setCod_ciclo(this.tarplaSelecRep.getCod_ciclo());
					cicloG.setFlag_tab(true);
					this.cicloService.actualizarFlagTab(cicloG);
					log.setAccion("UPDATE");
			        log.setDescripcion("Se actualizó el flag del ciclo: "+this.utils.convertirANombre(cicloAReplicar.getMes()) + " - " + cicloAReplicar.getAnio() + " - " + sdf.format(cicloAReplicar.getFecini()) + " - " + sdf.format(cicloAReplicar.getFecfin()));
			        logmb.insertarLog(log);
					this.listaCiclosFacturacion = this.cicloService.findByTipoCicloActivo(1);
					valido=Boolean.TRUE;
					context.addCallbackParam("esValido", valido);
			}else{
				valido=Boolean.FALSE;
	    		context.addCallbackParam("esValido", valido);
			FacesUtils.showFacesMessage("Eliga un ciclo",Constante.ADVERTENCIA);
			context.update("sms");
			context.update("msgGeneral");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void replicaTableroFacturacion(){
		try {
			Boolean valido=Boolean.TRUE;
			RequestContext context = RequestContext.getCurrentInstance();  
			
			if(this.tarfacSelecRep.getCod_ciclo() > 0){
				List<Tarifa> listaTarifaFacturacionAReplicar= new ArrayList<Tarifa>();
				for (Tarifa tabfac : this.listaTarifaFacturacion) {
					Tarifa tabcomtemp= tabfac;
					tabcomtemp.setCod_ciclo(this.tarfacSelecRep.getCod_ciclo());
					listaTarifaFacturacionAReplicar.add(tabcomtemp);
				}
					Ciclo cicloAReplicar = this.cicloService.findById(this.cod_ciclo_pla);
					this.tarServices.insertBatchTarifa(listaTarifaFacturacionAReplicar);
					DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					log.setAccion("INSERT");
			        log.setDescripcion("Se replicó la tarifa del ciclo: "+this.ciclo_pla + " para el ciclo: " + this.utils.convertirANombre(cicloAReplicar.getMes()) + " - " + cicloAReplicar.getAnio() + " - " + sdf.format(cicloAReplicar.getFecini()) + " - " + sdf.format(cicloAReplicar.getFecfin()));
			        logmb.insertarLog(log);
					Ciclo cicloG = new Ciclo();
					cicloG.setCod_ciclo(this.tarfacSelecRep.getCod_ciclo());
					cicloG.setFlag_tab(true);
					this.cicloService.actualizarFlagTab(cicloG);
					log.setAccion("UPDATE");
			        log.setDescripcion("Se actualizó el flag del ciclo: "+this.utils.convertirANombre(cicloAReplicar.getMes()) + " - " + cicloAReplicar.getAnio() + " - " + sdf.format(cicloAReplicar.getFecini()) + " - " + sdf.format(cicloAReplicar.getFecfin()));
			        logmb.insertarLog(log);
					this.listaCiclosFacturacion = this.cicloService.findByTipoCicloActivo(1);
					valido=Boolean.TRUE;
					context.addCallbackParam("esValido", valido);
			}else{
				valido=Boolean.FALSE;
	    		context.addCallbackParam("esValido", valido);
			FacesUtils.showFacesMessage("Eliga un ciclo",Constante.ADVERTENCIA);
			context.update("sms");
			context.update("msgGeneral");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void listarTableroPlanilla(){
		if(this.cod_ciclo_pla > 0){
			try {
				this.listaTarifaPlanilla=this.tarServices.listarTarifa(this.tipoCicloSelec.getCod_tipo_ciclo(), this.cod_ciclo_pla);
				
				System.out.println("TAMAÑO: "+this.listaTarifaPlanilla.size());
				this.mostrarTablaPla=true;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			this.mostrarTablaPla=false;
		}
	}
	
	public void listarTableroFacturacion(){
		if(this.cod_ciclo_fac > 0){
			try {
				this.listaTarifaFacturacion=this.tarServices.listarTarifa(this.tipoCicloSelec.getCod_tipo_ciclo(), this.cod_ciclo_fac);
				
				System.out.println("TAMAÑO: "+this.listaTarifaFacturacion.size());
				this.mostrarTablaFac=true;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			this.mostrarTablaFac=false;
		}
	}
	
	public void guardarTableroFacturacion(){
		Boolean valido=Boolean.TRUE;
		RequestContext context = RequestContext.getCurrentInstance();  
   	    context.addCallbackParam("esValido", valido);
		
		try {
			
			this.tarfacSelec.setCod_ciclo(this.cod_ciclo_fac);
					
			if(this.editar) {
				this.tarServices.actualizarTarifa(this.tarfacSelec);
				 log.setAccion("UPDATE");
	             log.setDescripcion("Se actualiza el tablero comision: " + this.tarfacSelec.getId_tarifa());
	             logmb.insertarLog(log);
				FacesUtils.showFacesMessage("Registro ha sido actualizado", 3);
			}else{
				this.tarServices.crearTarifa(this.tarfacSelec);
				log.setAccion("INSERT");
	            log.setDescripcion("Se inserta la tarifa : " +  this.tarfacSelec.getId_tarifa());
	            logmb.insertarLog(log);
				Ciclo cicloG = new Ciclo();
				cicloG.setCod_ciclo(this.cod_ciclo_fac);
				cicloG.setFlag_tab(true);
				this.cicloService.actualizarFlagTab(cicloG);
				 log.setAccion("INSERT");
	             log.setDescripcion("Se inserta el estado : " +  this.tarfacSelec.getId_tarifa());
	             logmb.insertarLog(log);
				FacesUtils.showFacesMessage("Registro ha sido creado", 3);
			}
			//this.listaTarifaFacturacion = this.tarServices.listarTarifa(this.tarfacSelec.getCod_tipo_ciclo(), this.tarfacSelec.getCod_ciclo());
			this.listaTarifaFacturacion = this.tarServices.listarTarifa(this.tipoCicloSelec.getCod_tipo_ciclo(), this.tarfacSelec.getCod_ciclo());
			
			System.out.println(" listaTarifaFacturacion" + listaTarifaFacturacion.size());
			this.tarfacSelec = new Tarifa();
			this.editar = Boolean.FALSE;
			
			
			context.update("msgGeneral");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void guardarTableroPlanilla(){
		Boolean valido=Boolean.TRUE;
		RequestContext context = RequestContext.getCurrentInstance();  
   	    context.addCallbackParam("esValido", valido);
		
		try {
			
			this.tarplaSelec.setCod_ciclo(this.cod_ciclo_pla);
					
			if(this.editar) {
				this.tarServices.actualizarTarifa(this.tarplaSelec);
				 log.setAccion("UPDATE");
	             log.setDescripcion("Se actualiza el tablero comision: " + this.tarplaSelec.getId_tarifa());
	             logmb.insertarLog(log);
				FacesUtils.showFacesMessage("Registro ha sido actualizado", 3);
			}else{
				this.tarServices.crearTarifa(this.tarplaSelec);
				log.setAccion("INSERT");
	            log.setDescripcion("Se inserta la tarifa : " +  this.tarplaSelec.getId_tarifa());
	            logmb.insertarLog(log);
				Ciclo cicloG = new Ciclo();
				cicloG.setCod_ciclo(this.cod_ciclo_pla);
				cicloG.setFlag_tab(true);
				this.cicloService.actualizarFlagTab(cicloG);
				 log.setAccion("UPDATE");
	             log.setDescripcion("Se inserta el estado : " +  this.tarplaSelec.getId_tarifa());
	             logmb.insertarLog(log);
				FacesUtils.showFacesMessage("Registro ha sido creado", 3);
			}
			this.listaTarifaPlanilla = this.tarServices.listarTarifa(this.tipoCicloSelec.getCod_tipo_ciclo(), this.tarplaSelec.getCod_ciclo());
			this.tarplaSelec = new Tarifa();
			this.editar = Boolean.FALSE;
			
			
			context.update("msgGeneral");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void onTabChange(TabChangeEvent event) {
		if (event.getTab().getId().equals("tabPlanilla")){
			
			try {
				this.tipoCicloSelec = this.tipoCicloService.findById(2);
				this.tipoCiclo = this.tipoCicloSelec.getDescripcion();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			try {
				this.tipoCicloSelec = this.tipoCicloService.findById(1);
				this.tipoCiclo = this.tipoCicloSelec.getDescripcion();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
        //FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	/*public void cambiarEstado(Estado estado){
		
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
		
	
	public void editarTableroFacturacion(Tarifa tabfac){
		this.tarfacSelec = tabfac;
		
		try {
			this.cicloSelec = new Ciclo();
			this.cicloSelec = this.cicloService.findById(this.tarfacSelec.getCod_ciclo());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		//c.setNfecini(sdf.format(c.getFecini()));
		//c.setNfecfin(sdf.format(c.getFecfin()));
		this.ciclo_fac = this.utils.convertirANombre(this.cicloSelec.getMes()) + " - " + this.cicloSelec.getAnio() + " - " + sdf.format(this.cicloSelec.getFecini()) + " - " + sdf.format(this.cicloSelec.getFecini());
		
		this.editar = Boolean.TRUE;
		
		/*Boolean valido=Boolean.TRUE;
		RequestContext context = RequestContext.getCurrentInstance();  
   	    context.execute("PF('dlgNuevo').show();");*/
	}
	
	public void editarTableroPlanilla(Tarifa tabpla){
		this.tarplaSelec = tabpla;
		
		try {
			this.cicloSelec = new Ciclo();
			this.cicloSelec = this.cicloService.findById(this.tarplaSelec.getCod_ciclo());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		//c.setNfecini(sdf.format(c.getFecini()));
		//c.setNfecfin(sdf.format(c.getFecfin()));
		this.ciclo_pla = this.utils.convertirANombre(this.cicloSelec.getMes()) + " - " + this.cicloSelec.getAnio() + " - " + sdf.format(this.cicloSelec.getFecini()) + " - " + sdf.format(this.cicloSelec.getFecini());
		
		this.editar = Boolean.TRUE;
		
		/*Boolean valido=Boolean.TRUE;
		RequestContext context = RequestContext.getCurrentInstance();  
   	    context.execute("PF('dlgNuevo').show();");*/
	}
	
	public void eliminarTableroFacturacion(Tarifa tabfac){
		this.tarfacSelec = tabfac;
	}
		
	public void eliminarTableroPlanilla(Tarifa tabpla){
		this.tarfacSelec = tabpla;
	}
	
	public void confirmaEliminarFacturacion(){
		try {
		
			this.tarServices.eliminarTarifa(this.tarfacSelec.getId_tarifa());
			
			log.setAccion("DELETE");
			log.setDescripcion("Se elimina la tarifa : " + this.tarfacSelec.getId_tarifa());
			logmb.insertarLog(log);
			FacesUtils.showFacesMessage("Registro ha sido eliminado", 3);
			
			this.listaTarifaFacturacion = this.tarServices.listarTarifa(this.tipoCicloSelec.getCod_tipo_ciclo(), this.tarfacSelec.getCod_ciclo());
			this.tarfacSelec = new Tarifa();
			this.editar = Boolean.FALSE;
			
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void confirmaEliminarPlanilla(){
		try {
		
			this.tarServices.eliminarTarifa(this.tarplaSelec.getId_tarifa());
			
			log.setAccion("DELETE");
			log.setDescripcion("Se elimina la tarifa: " + this.tarplaSelec.getId_tarifa());
			logmb.insertarLog(log);
			FacesUtils.showFacesMessage("Registro ha sido eliminado", 3);
			
			this.listaTarifaPlanilla = this.tarServices.listarTarifa(this.tipoCicloSelec.getCod_tipo_ciclo(), this.tarplaSelec.getCod_ciclo());
			this.tarfacSelec = new Tarifa();
			this.editar = Boolean.FALSE;
			
			
		
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

	public Boolean getMostrarTope() {
		return mostrarTope;
	}

	public void setMostrarTope(Boolean mostrarTope) {
		this.mostrarTope = mostrarTope;
	}

	public Ciclo getCicloSelec() {
		return cicloSelec;
	}

	public void setCicloSelec(Ciclo cicloSelec) {
		this.cicloSelec = cicloSelec;
	}		

	public Integer getCod_ciclo_pla() {
		return cod_ciclo_pla;
	}

	public void setCod_ciclo_pla(Integer cod_ciclo_pla) {
		this.cod_ciclo_pla = cod_ciclo_pla;
	}

	public String getCiclo_pla() {
		return ciclo_pla;
	}

	public void setCiclo_pla(String ciclo_pla) {
		this.ciclo_pla = ciclo_pla;
	}

	public TipoCiclo getTipoCicloSelec() {
		return tipoCicloSelec;
	}

	public void setTipoCicloSelec(TipoCiclo tipoCicloSelec) {
		this.tipoCicloSelec = tipoCicloSelec;
	}

	public String getTipoCiclo() {
		return tipoCiclo;
	}

	public void setTipoCiclo(String tipoCiclo) {
		this.tipoCiclo = tipoCiclo;
	}

	public Boolean getMostrarTablaPla() {
		return mostrarTablaPla;
	}

	public void setMostrarTablaPla(Boolean mostrarTablaPla) {
		this.mostrarTablaPla = mostrarTablaPla;
	}

	public Integer getCod_ciclo_fac() {
		return cod_ciclo_fac;
	}

	public void setCod_ciclo_fac(Integer cod_ciclo_fac) {
		this.cod_ciclo_fac = cod_ciclo_fac;
	}


	public List<Ciclo> getListaCiclosFacturacion() {
		for (Ciclo c : listaCiclosFacturacion) {
			c.setNmes(this.utils.convertirANombre(c.getMes()));
			DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			c.setNfecini(sdf.format(c.getFecini()));
			c.setNfecfin(sdf.format(c.getFecfin()));
			
		}
		return listaCiclosFacturacion;
	}

	public void setListaCiclosFacturacion(List<Ciclo> listaCiclosFacturacion) {
		this.listaCiclosFacturacion = listaCiclosFacturacion;
	}

	public String getCiclo_fac() {
		return ciclo_fac;
	}

	public void setCiclo_fac(String ciclo_fac) {
		this.ciclo_fac = ciclo_fac;
	}

	public Boolean getMostrarTablaFac() {
		return mostrarTablaFac;
	}

	public void setMostrarTablaFac(Boolean mostrarTablaFac) {
		this.mostrarTablaFac = mostrarTablaFac;
	}

	

	public Boolean getMostrarFecha() {
		return mostrarFecha;
	}

	public void setMostrarFecha(Boolean mostrarFecha) {
		this.mostrarFecha = mostrarFecha;
	}

	public List<Ciclo> getListaCiclosInactivosFacturacion() {
		for (Ciclo c : listaCiclosInactivosFacturacion) {
			c.setNmes(this.utils.convertirANombre(c.getMes()));
			DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			c.setNfecini(sdf.format(c.getFecini()));
			c.setNfecfin(sdf.format(c.getFecfin()));
			
		}
		return listaCiclosInactivosFacturacion;
	}

	public void setListaCiclosInactivosFacturacion(
			List<Ciclo> listaCiclosInactivosFacturacion) {
		this.listaCiclosInactivosFacturacion = listaCiclosInactivosFacturacion;
	}

	public Tarifa getTarfacSelec() {
		return tarfacSelec;
	}

	public void setTarfacSelec(Tarifa tarfacSelec) {
		this.tarfacSelec = tarfacSelec;
	}

	public Tarifa getTarfacSelecRep() {
		return tarfacSelecRep;
	}

	public void setTarfacSelecRep(Tarifa tarfacSelecRep) {
		this.tarfacSelecRep = tarfacSelecRep;
	}

	public List<Tarifa> getListaTarifaFacturacion() {
		return listaTarifaFacturacion;
	}

	public void setListaTarifaFacturacion(List<Tarifa> listaTarifaFacturacion) {
		this.listaTarifaFacturacion = listaTarifaFacturacion;
	}

	public List<Ciclo> getListaCiclosPlanilla() {
		for (Ciclo c : listaCiclosPlanilla) {
			c.setNmes(this.utils.convertirANombre(c.getMes()));
			DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			c.setNfecini(sdf.format(c.getFecini()));
			c.setNfecfin(sdf.format(c.getFecfin()));
			
		}
		return listaCiclosPlanilla;
	}

	public void setListaCiclosPlanilla(List<Ciclo> listaCiclosPlanilla) {
		this.listaCiclosPlanilla = listaCiclosPlanilla;
	}

	public List<Ciclo> getListaCiclosInactivosPlanilla() {
		for (Ciclo c : listaCiclosInactivosPlanilla) {
			c.setNmes(this.utils.convertirANombre(c.getMes()));
			DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			c.setNfecini(sdf.format(c.getFecini()));
			c.setNfecfin(sdf.format(c.getFecfin()));
			
		}
		return listaCiclosInactivosPlanilla;
	}

	public void setListaCiclosInactivosPlanilla(
			List<Ciclo> listaCiclosInactivosPlanilla) {
		this.listaCiclosInactivosPlanilla = listaCiclosInactivosPlanilla;
	}

	public List<Tarifa> getListaTarifaPlanilla() {
		return listaTarifaPlanilla;
	}

	public void setListaTarifaPlanilla(List<Tarifa> listaTarifaPlanilla) {
		this.listaTarifaPlanilla = listaTarifaPlanilla;
	}

	public Tarifa getTarplaSelec() {
		return tarplaSelec;
	}

	public void setTarplaSelec(Tarifa tarplaSelec) {
		this.tarplaSelec = tarplaSelec;
	}

	public Tarifa getTarplaSelecRep() {
		return tarplaSelecRep;
	}

	public void setTarplaSelecRep(Tarifa tarplaSelecRep) {
		this.tarplaSelecRep = tarplaSelecRep;
	}		
	
}
