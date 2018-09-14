package com.certicom.certiscan.managedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;








import org.primefaces.model.chart.MeterGaugeChartModel;

import com.certicom.certiscan.commons.Constante;
import com.certicom.certiscan.commons.FacesUtils;
import com.certicom.certiscan.commons.GenericBeans;
import com.certicom.certiscan.domain.Empresa;
import com.certicom.certiscan.domain.Log;
import com.certicom.certiscan.domain.Menu;
import com.certicom.certiscan.services.EmpresaService;
import com.certicom.certiscan.services.MenuServices;

@ManagedBean(name="empresaMB")
@ViewScoped

public class EmpresaMB extends GenericBeans implements Serializable{
	
	private Empresa empresa;
	private List<Empresa> listaEmpresa;
	private Boolean editar;
	private List<Empresa> listaFiltroEmpresa;
	private EmpresaService empresaService;
	private MenuServices menuServices;
	private Log log;
	private LogMB logmb;
	RequestContext context; 
	private Empresa empresaSelec;
	@ManagedProperty(value= "#{loginMB}")
	private LoginMB login;
	
	
	 private MeterGaugeChartModel meterGaugeModel1;
	
	private Integer inc = 0;

	public EmpresaMB(){
	}
	
	@PostConstruct
	public void inicia()
	{
		this.empresaSelec = new Empresa();
		this.listaEmpresa = new ArrayList<>();
		this.editar = Boolean.FALSE;
		menuServices = new MenuServices();
		this.empresaService = new EmpresaService();
		
		try {
			this.listaEmpresa = this.empresaService.findAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log = (Log)getSpringBean(Constante.SESSION_LOG);
		logmb = new LogMB();
		try {
			Menu codMenu = menuServices.opcionMenuByPretty("pretty:empresa");
			log.setCod_menu(codMenu.getCod_menu().intValue());
			log.setIdusuario(this.login.getIdUsuario());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();}
		
		createMeterGaugeModels();
	}
	
	
	public void guardarEmpresa() {
		Boolean valido=Boolean.TRUE;
		RequestContext context = RequestContext.getCurrentInstance();  
   	    context.addCallbackParam("esValido", valido);
		
		try {
			
			this.empresaSelec.setRazon_social(this.empresaSelec.getRazon_social().trim().toUpperCase());
			this.empresaSelec.setRuc(this.empresaSelec.getRuc());
		    
			
			if(this.editar) {
				this.empresaService.actualizarEmpresa(empresaSelec);;
				FacesUtils.showFacesMessage("Empresa ha sido actualizado", 3);
				log.setAccion("UPDATE");
		        log.setDescripcion("El usuario "+this.login.getLoginUsuario()+" actualizó la empresa: "+this.empresaSelec.getRazon_social());
		        logmb.insertarLog(log);
			}else{
				//this.resultado.setEstado(Boolean.TRUE);
				this.empresaService.crearEmpresa(empresaSelec);;
				FacesUtils.showFacesMessage("Empresa ha sido creado", 3);
				log.setAccion("INSERT");
		        log.setDescripcion("El usuario "+this.login.getLoginUsuario()+" registró: " + this.empresaSelec.getRazon_social());
		        logmb.insertarLog(log);
			}
			
			this.empresaSelec = new Empresa();
			this.editar = Boolean.FALSE;
			
			this.listaEmpresa = empresaService.findAll();
			context.update("sms");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	


	public void nuevoEmpresa(){
		this.empresaSelec = new Empresa();
		this.empresaSelec.setEstado(Boolean.TRUE);
		this.editar = Boolean.FALSE;
		System.out.println("***********creando Empresa");
	}
	

	public void editarEmpresa(Empresa emp){
		this.empresaSelec = emp;
		this.editar = Boolean.TRUE;
	}
	
	public void eliminarEmpresa(Empresa emp){
		this.empresaSelec = emp;
		
	}
	
	public void confirmaEliminar()
	{

		try{			
			
			Integer cant = empresaService.listProyectoxEmpresa(this.empresaSelec.getId_empresa());
			
			if(cant > 0){
		
				FacesUtils.showFacesMessage("Empresa no se puede eliminar tiene un Proyecto asociado", Constante.ERROR);
				
			} else{
									
				this.empresaService.eliminarEmpresa(this.empresaSelec.getId_empresa());
				FacesUtils.showFacesMessage("Empresa no se puede eliminar tiene un Proyecto asociado", 3);
				log.setAccion("DELETE");
		        log.setDescripcion("El usuario "+this.login.getLoginUsuario()+" eliminó la empresa: " + this.empresaSelec.getRazon_social());
		        logmb.insertarLog(log);				
				this.listaEmpresa = this.empresaService.findAll();
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		
/*		try {
			this.empresaService.eliminarEmpresa(this.empresaSelec.getId_empresa());
			FacesUtils.showFacesMessage("La Empresa ha sido eliminado", 3);
			this.listaEmpresa = this.empresaService.findAll();
			log.setAccion("DELETE");
	        log.setDescripcion("Se ha eliminado  : " + this.empresaSelec.getRazon_social());
	        logmb.insertarLog(log);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	}

	
	public void cambiarEstado(Empresa empresa){
		   String estado="";
		   if(empresa.getEstado()){
			   empresa.setEstado(Boolean.FALSE);
			   estado="INACTIVO";
		   }else{
			   empresa.setEstado(Boolean.TRUE);
			   estado="ACTIVO";
		   }
		   
		   try {
			   this.empresaService.actualizarEmpresa(empresa);;
			   FacesUtils.showFacesMessage("Empresa se ha  modificado correctamente",Constante.INFORMACION);
			   this.listaEmpresa = this.empresaService.findAll();
			   log.setAccion("UPDATE");
	           log.setDescripcion("El usuario "+this.login.getLoginUsuario()+" actualizó el estado a  : " + estado);
	           logmb.insertarLog(log);
		   } catch (Exception e) {
			   System.out.println("Error:"+e.getMessage());
			   e.printStackTrace();
		   }   
	}
	
	
	
	 private MeterGaugeChartModel initMeterGaugeModel() {
	        List<Number> intervals = new ArrayList<Number>(){{
	            add(20);
	            add(50);
	            add(120);
	            add(220);
	        }};
	         
	        return new MeterGaugeChartModel(inc, intervals);
	    }
	 
	 private void createMeterGaugeModels() {
	        meterGaugeModel1 = initMeterGaugeModel();
	        meterGaugeModel1.setTitle("MeterGauge Chart");
	        meterGaugeModel1.setGaugeLabel("km/h");
	 }
	 public void increment(){
		 System.out.println("entro ...");
		 inc=inc+10;
		 System.out.println(inc);
		 meterGaugeModel1.setValue(inc);
	 }
	 
	/**##########################setter and  getter#####################################*/

	public Boolean getEditar() {
		return editar;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public List<Empresa> getListaEmpresa() {
		return listaEmpresa;
	}

	public void setListaEmpresa(List<Empresa> listaEmpresa) {
		this.listaEmpresa = listaEmpresa;
	}

	public void setEditar(Boolean editar) {
		this.editar = editar;
	}

	public List<Empresa> getListaFiltroEmpresa() {
		return listaFiltroEmpresa;
	}

	public void setListaFiltroEmpresa(List<Empresa> listaFiltroEmpresa) {
		this.listaFiltroEmpresa = listaFiltroEmpresa;
	}
	
	public Empresa getEmpresaSelec() {
		return empresaSelec;
	}

	public void setEmpresaSelec(Empresa empresaSelec) {
		this.empresaSelec = empresaSelec;
	}

	public LoginMB getLogin() {
		return login;
	}

	public void setLogin(LoginMB login) {
		this.login = login;
	}

	public MeterGaugeChartModel getMeterGaugeModel1() {
		return meterGaugeModel1;
	}

	public void setMeterGaugeModel1(MeterGaugeChartModel meterGaugeModel1) {
		this.meterGaugeModel1 = meterGaugeModel1;
	}

	
	
}
