package com.certicom.certiscan.managedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.primefaces.context.RequestContext;

import com.certicom.certiscan.commons.Constante;
import com.certicom.certiscan.commons.FacesUtils;
import com.certicom.certiscan.commons.GenericBeans;
import com.certicom.certiscan.domain.Estados;
import com.certicom.certiscan.domain.Expediente;
import com.certicom.certiscan.domain.ExpedienteTracking;
import com.certicom.certiscan.domain.Perfil;
import com.certicom.certiscan.domain.Usuario;
import com.certicom.certiscan.services.EstadosServices;
import com.certicom.certiscan.services.ExpedienteService;
import com.certicom.certiscan.services.ExpedienteTrackingService;


@ManagedBean(name="digitalizacionMB")
@ViewScoped
public class DigitalizacionMB extends GenericBeans implements Serializable{
	
	private Expediente expedienteFilter;
	
	private List<Expediente> listaExpedientes;
	private List<Expediente> listaExpedientesFilter;

	private boolean _activateCmb;
	private boolean _activateDealer;
	private Integer totalExpeCargados;
	private boolean bexpediente;
	private boolean bfecha;
	private SelectItem[] listaCanales;
	private ExpedienteService expedienteService;
	private ExpedienteTrackingService expedienteTrackingService;
	private RequestContext context;
	private Expediente expediente;
	private Expediente expSelected;
	private List<Estados> listEstados;
	private EstadosServices estadosServices;
	
	private List<ExpedienteTracking> listaExpTracking;
	private List<ExpedienteTracking> listaExpTrackingfilter;
	
	//inicio piscoya
	private String obsGen;
	//fin piscoya
	
	@ManagedProperty(value="#{loginMB.perfilUsuario}")
	private Perfil perfilUsuario;
	
	@ManagedProperty(value="#{loginMB.usuario}")
	private Usuario usuarioLogin;

	private Date fechaLimite;

	private Date fechaActual;
	
	private Date fecha_ini;
	private Date fecha_fin;
	
	public DigitalizacionMB(){
	}
	
	@PostConstruct
	public void inicia(){
		this.fecha_ini = new Date();
		this.fecha_fin = new Date();
		this.fechaActual = new Date();
		this.listaExpedientes = new ArrayList<>();
		this.listaExpTracking = new ArrayList<>();
		this._activateCmb = Boolean.FALSE;
		this._activateDealer = Boolean.TRUE;
		this.expedienteFilter = new Expediente();
		this.expedienteFilter.setFiltradopor(1);
		this.bexpediente = true;
		this.bfecha = false;
		this.expSelected = new Expediente();
		this.estadosServices = new EstadosServices();
		this.listEstados = new ArrayList<Estados>();
		
		this.context = RequestContext.getCurrentInstance();
		this.expedienteService = new ExpedienteService(); 
		this.expedienteTrackingService  = new ExpedienteTrackingService();
		
		try {
			this.expedienteFilter.setFec_ini(this.fecha_ini);
			this.expedienteFilter.setFec_fin(this.fecha_fin);
			this.listEstados = this.estadosServices.listaEstadosActivo();
			//this.totalExpeCargados = this.expedienteService.getExpedienteAsignados(this.usuarioLogin.getIdusuario());
			
			if(this.perfilUsuario.getProceso().equals("EXPEDIENTES PROPIOS") || this.perfilUsuario.getProceso().equals("OFICINA LOCAL")){
				this._activateCmb = true;
				this.expedienteFilter.setId_oficina(this.usuarioLogin.getId_oficina());
				this._activateDealer = false;
			}else{
				this._activateCmb = false;
				this._activateDealer = true;
			}
			
			
			
			this.listaCanales = filterCanal();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void modificarEstado(){
		
		RequestContext context = RequestContext.getCurrentInstance();
		
		try {
		
		ExpedienteTracking et = new ExpedienteTracking();
		et.setExpediente_id(this.expSelected.getExpediente_id());
		et.setFecha_registro(new Date());
		et.setIdusuario(this.usuarioLogin.getIdusuario());
		et.setIdusuario_registro(this.usuarioLogin.getIdusuario());
		et.setId_estado(Constante.EST_DIGITALIZADO);
		et.setCondicion(this.expSelected.getCondicion());
		et.setFecha_recepcion(this.expSelected.getFecha_recepcion());
		et.setObservacion(this.expSelected.getObservacion());
		et.setId_oficina(this.usuarioLogin.getId_oficina());
		et.setCant_imagenes(this.expSelected.getCant_imagenes());
		et.setCant_planos_imagenes(this.expSelected.getCant_planos_imagenes());
		et.setPlanos_excede(this.expSelected.getPlanos_excede());
		et.setPlanos_a0(this.expSelected.getPlanos_a0()*16);
		et.setPlanos_a1(this.expSelected.getPlanos_a1()*8);
		et.setPlanos_a2(this.expSelected.getPlanos_a2()*4);
		et.setPlanos_a3(this.expSelected.getPlanos_a3()*2);
			
			this.expedienteService.updateEstadoExpedientebyId(Constante.EST_PREPARADO_PARA_CC, this.expSelected.getExpediente_id());
			
			this.expedienteTrackingService.crearExpedienteTracking(et);
			
			et = new ExpedienteTracking();
			et.setExpediente_id(this.expSelected.getExpediente_id());
			et.setFecha_registro(new Date());
			et.setIdusuario(this.usuarioLogin.getIdusuario());
			et.setIdusuario_registro(this.usuarioLogin.getIdusuario());
			et.setCondicion(this.expSelected.getCondicion());
			et.setFecha_recepcion(this.expSelected.getFecha_recepcion());
			et.setObservacion(this.expSelected.getObservacion());
			et.setId_oficina(this.usuarioLogin.getId_oficina());
			
			et.setId_estado(Constante.EST_PREPARADO_PARA_CC);
			
			this.expedienteTrackingService.crearExpedienteTracking(et);
			
			buscarExpedientes();
			
			context.update("dlgDetSuccesGroup");
			context.execute("PF('dlgSuccessGroup').show()");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public SelectItem[] filterCanal(){
		SelectItem[] items ;
			items = new SelectItem[]{new SelectItem("","--Seleccione--"),
									 new SelectItem("PERSONA"),
					 				 new SelectItem("EMPRESA")};
			return items;
	}
	
	public void sumar() {
		System.out.println("VAMOS A SUMAR!!!");
		this.expSelected.setCant_imagenes(this.expSelected.getCant_planos_imagenes()+
		this.expSelected.getPlanos_a0()*16+
		this.expSelected.getPlanos_a1()*8+
		this.expSelected.getPlanos_a2()*4+
		this.expSelected.getPlanos_a3()*2+
		this.expSelected.getPlanos_excede());
		
		
		System.out.println("SUMA: "+this.expSelected.getCant_imagenes());
		
	}
	
	public void eliminarDuplicado(ExpedienteTracking expTra){
		try {
			//registramos el expedieteen tracking 
		//	this.expedienteTrackingService.registrarTrackingDuplicado(expTra);
			
			//eliminamos
//			this.expedienteTrackingService.eliminarExpedienteTracking(expTra.getCod_exp_tracking());
//			this.listaExpTracking = this.expedienteTrackingService.listarTrackingxExpediente(expTra.getCod_expediente());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void buscarExpedientes(){
	
		System.out.println("BUSCAR EXPEDIENTE");
		
		boolean existe=false;
		
		if (this.expedienteFilter.getFiltradopor() == null){
			existe = false;
		}else{
			existe = true;
		}
		
		//if (existe){
			try {
				this.listaExpedientes = new ArrayList<>();
				/*if(this.masivo){
				this.expedienteFilter.setFiltradopor(0);
				}*/
	//			if(perfilUsuario.getCod_perfil() == 1){
	//				this.listaExpedientes = this.expedienteService.listTodosExpedientexOficina(this.expedienteFilter.getIdoficina());
	//			}else if(this.perfilUsuario.getProceso().equals("OFICINA LOCAL")){
	//				this.listaExpedientes = this.expedienteService.listTodosExpedientexOficinaLocal(this.expedienteFilter.getIdoficina());
	//			}else if(this.perfilUsuario.getProceso().equals("TODAS LAS OFICINAS")){ 
	//				this.listaExpedientes = this.expedienteService.listTodosExpedientexOficinaLocal(this.expedienteFilter.getIdoficina());
	//			}else{
	//				this.listaExpedientes = this.expedienteService.listTodosExpedientexUsuario(this.expedienteFilter.getIdoficina(), getUsuarioLogin().getIdusuario());
	//			}
				
				if(perfilUsuario.getCod_perfil() == 1 || perfilUsuario.getCod_perfil()==34){
					//System.out.println("Hola1");
				}else if(this.perfilUsuario.getProceso().equals("OFICINA LOCAL")){
					//System.out.println("Hola2");
				}else if(this.perfilUsuario.getProceso().equals("TODAS LAS OFICINAS")){ 
					//System.out.println("Hola3");
				}else{
					//System.out.println("Hola4");
					this.expedienteFilter.setIdusuario(getUsuarioLogin().getIdusuario());
				}
				//System.out.println("Imprimimos el valor del Cod_Delaer:"+this.expedienteFilter.getCod_dealer());			
			
				this.listaExpedientes = this.expedienteService.consultaDigitalizacionExpediente(expedienteFilter);
				
				/*for(int i= 0; i<listaExpedientes.size(); i++){
					System.out.println("Nro solicitud:"+ listaExpedientes.get(i).getNrosolicitud() +"Fecha de Rec:"+listaExpedientes.get(i).getFec_rec());
				}*/
				
				this.totalExpeCargados = this.listaExpedientes.size();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
	//	}else{
	//		FacesUtils.showFacesMessage("Por favor, ingresar un parametro de b�squeda",Constante.ADVERTENCIA);
	//		context.update(":sms");
	//	}
	}
	
	public void datosVerDocumento(Expediente exp){
		try {
			this.expediente = exp;			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void cambiarFecha(){
		//System.out.println("la fecha que se capturo es " + this.expedienteFilter.getFec_ini());
		this.fechaLimite = this.expedienteFilter.getFec_ini();
		
	}
	
	public void cambiarCombo(){
		if(this.expedienteFilter.getFiltradopor()==1){
			this.expedienteFilter.setFec_ini(null);
			this.expedienteFilter.setFec_fin(null);
			this.bexpediente = true;
			this.bfecha = false;
		}else{
			this.expedienteFilter.setVfiltradopor(null);
			this.bexpediente = false;
			this.bfecha = true;
		}
	}
	
	public void cargarDatos(Expediente exp) {
		this.expSelected = exp;
		
		try {
			
			this.expSelected.setId_estado(Constante.EST_DIGITALIZADO);
			this.expSelected.setCant_imagenes(0);
			this.expSelected.setCant_planos_imagenes(0);
			this.expSelected.setPlanos_a0(0);
			this.expSelected.setPlanos_a1(0);
			this.expSelected.setPlanos_a2(0);
			this.expSelected.setPlanos_a3(0);
			this.expSelected.setPlanos_excede(0);
			//this.listaExpTracking = this.expedienteTrackingService.findByExpedienteId(expSelected.getExpediente_id());
			System.out.println(" this.listaExpTracking " +this.listaExpTracking.size());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
	/**##########################setter and  getter#####################################*/
	public List<Expediente> getListaExpedientesFilter() {
		return listaExpedientesFilter;
	}

	public Perfil getPerfilUsuario() {
		return perfilUsuario;
	}

	public void setPerfilUsuario(Perfil perfilUsuario) {
		this.perfilUsuario = perfilUsuario;
	}

	public Usuario getUsuarioLogin() {
		return usuarioLogin;
	}

	public void setUsuarioLogin(Usuario usuarioLogin) {
		this.usuarioLogin = usuarioLogin;
	}

	public boolean is_activateCmb() {
		return _activateCmb;
	}

	public void set_activateCmb(boolean _activateCmb) {
		this._activateCmb = _activateCmb;
	}

	public void setListaExpedientesFilter(List<Expediente> listaExpedientesFilter) {
		this.listaExpedientesFilter = listaExpedientesFilter;
	}

	public List<Expediente> getListaExpedientes() {
		return listaExpedientes;
	}

	public void setListaExpedientes(List<Expediente> listaExpedientes) {
		this.listaExpedientes = listaExpedientes;
	}

	public Expediente getExpedienteFilter() {
		return expedienteFilter;
	}

	public void setExpedienteFilter(Expediente expedienteFilter) {
		this.expedienteFilter = expedienteFilter;
	}

	

	public SelectItem[] getListaCanales() {
		return listaCanales;
	}

	public void setListaCanales(SelectItem[] listaCanales) {
		this.listaCanales = listaCanales;
	}

	public Expediente getExpSelected() {
		return expSelected;
	}

	public void setExpSelected(Expediente expSelected) {
		this.expSelected = expSelected;
	}

	public List<ExpedienteTracking> getListaExpTracking() {
		return listaExpTracking;
	}

	public void setListaExpTracking(List<ExpedienteTracking> listaExpTracking) {
		this.listaExpTracking = listaExpTracking;
	}

	public List<ExpedienteTracking> getListaExpTrackingfilter() {
		return listaExpTrackingfilter;
	}

	public void setListaExpTrackingfilter(
			List<ExpedienteTracking> listaExpTrackingfilter) {
		this.listaExpTrackingfilter = listaExpTrackingfilter;
	}

	public Integer getTotalExpeCargados() {
		return totalExpeCargados;
	}

	public void setTotalExpeCargados(Integer totalExpeCargados) {
		this.totalExpeCargados = totalExpeCargados;
	}

	public boolean is_activateDealer() {
		return _activateDealer;
	}

	public void set_activateDealer(boolean _activateDealer) {
		this._activateDealer = _activateDealer;
	}	

	public boolean isBexpediente() {
		return bexpediente;
	}

	public void setBexpediente(boolean bexpediente) {
		this.bexpediente = bexpediente;
	}

	public boolean isBfecha() {
		return bfecha;
	}

	public void setBfecha(boolean bfecha) {
		this.bfecha = bfecha;
	}

	public Expediente getExpediente() {
		return expediente;
	}

	public void setExpediente(Expediente expediente) {
		this.expediente = expediente;
	}

	public String getObsGen() {
		return obsGen;
	}

	public void setObsGen(String obsGen) {
		this.obsGen = obsGen;
	}

	public Date getFechaLimite() {
		return fechaLimite;
	}

	public void setFechaLimite(Date fechaLimite) {
		this.fechaLimite = fechaLimite;
	}

	public Date getFechaActual() {
		return fechaActual;
	}

	public void setFechaActual(Date fechaActual) {
		this.fechaActual = fechaActual;
	}

	public List<Estados> getListEstados() {
		return listEstados;
	}

	public void setListEstados(List<Estados> listEstados) {
		this.listEstados = listEstados;
	}

	public Date getFecha_ini() {
		return fecha_ini;
	}

	public void setFecha_ini(Date fecha_ini) {
		this.fecha_ini = fecha_ini;
	}

	public Date getFecha_fin() {
		return fecha_fin;
	}

	public void setFecha_fin(Date fecha_fin) {
		this.fecha_fin = fecha_fin;
	}
	
	
	
}
