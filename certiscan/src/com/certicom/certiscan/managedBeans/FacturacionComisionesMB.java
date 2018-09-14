package com.certicom.certiscan.managedBeans;

import java.io.Serializable;
import java.text.DateFormat;
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

import org.apache.commons.collections.map.MultiKeyMap;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Font;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabChangeEvent;

import com.certicom.certiscan.commons.Constante;
import com.certicom.certiscan.commons.ExportarArchivo;
import com.certicom.certiscan.commons.FacesUtils;
import com.certicom.certiscan.commons.GenericBeans;
import com.certicom.certiscan.commons.Utils;
import com.certicom.certiscan.domain.Ciclo;
import com.certicom.certiscan.domain.Expediente;
import com.certicom.certiscan.domain.ExpedienteDocumento;
import com.certicom.certiscan.domain.ExpedienteTracking;
import com.certicom.certiscan.domain.FacturacionPlanillaCabecera;
import com.certicom.certiscan.domain.FacturacionPlanillaDetalle;
import com.certicom.certiscan.domain.Log;
import com.certicom.certiscan.domain.Menu;
import com.certicom.certiscan.domain.Negocio;
import com.certicom.certiscan.domain.Oficina;
import com.certicom.certiscan.domain.Tarifa;
import com.certicom.certiscan.domain.TipoCiclo;
import com.certicom.certiscan.domain.Usuario;
import com.certicom.certiscan.services.CicloService;
import com.certicom.certiscan.services.ExpedienteDocumentoService;
import com.certicom.certiscan.services.ExpedienteService;
import com.certicom.certiscan.services.ExpedienteTrackingService;
import com.certicom.certiscan.services.FacturacionPlanillaCabeceraServices;
import com.certicom.certiscan.services.FacturacionPlanillaDetalleServices;
import com.certicom.certiscan.services.MenuServices;
import com.certicom.certiscan.services.OficinaService;
import com.certicom.certiscan.services.TarifaService;
import com.certicom.certiscan.services.TipoCicloService;
import com.certicom.certiscan.services.UsuarioServices;

import net.sf.jasperreports.engine.JRParameter;

@ManagedBean(name="facturacionComisionesMB")
@ViewScoped
public class FacturacionComisionesMB extends GenericBeans implements Serializable{

	
	private List<Ciclo> listaCiclosPlanilla;
	private List<Ciclo> listaCiclosFacturacion;
	private List<Expediente> listaExpedientesPlanillables;
	private List<Expediente> listaExpedientesPlanillablesDet;
	private List<Expediente> listaExpedientesFacturables;
	private List<Expediente> listaExpedientesFacturablesDet;
	private List<Expediente> listaExpedientesPlanillasFilter;
	private List<Expediente> listaExpedientesFacturablesFilter;
	private List<Tarifa> listaRangoFacturacion;
	private List<Tarifa> listaRangoPlanilla;
	private List<Tarifa> listaTarifaTemporal;
	private List<Tarifa> listaTableroConteo;
	private List<Expediente> listaExpedienteDetalle;
	private Expediente expedienteFilter;
	private ExpedienteService expedienteService;
	private Boolean editar;
	private Integer[] chckResultadoss;
	private CicloService cicloService;
	private MenuServices menuServices;
	private Utils utils;
	private TipoCicloService tipoCicloService;
	private TarifaService tarifaServices;
	private List<Tarifa> listaCicloAFacturar;
	private List<Tarifa> listaCicloAFacturarEspecial;
	private Ciclo cicloSelecPlanilla;
	private Ciclo cicloSelecFacturacion;
	private TipoCiclo tipoCicloSelec;
	private Integer cod_ciclo_pla;
	private Integer cod_ciclo_fac;
	private Integer vista;
	private Integer[] chckResultadosPla;
	private Integer[] chckResultadosFac;
	private String ciclo_com;
	private String tipoCiclo;
	private Integer totalExpedientesFac;
	private Integer totalExpedientesPla;
	private Integer totalPaginasfac;
	private Integer totalPaginaspla;
	private Integer totalAprobadosConObservacionFac;
	private Integer totalObservadosFac;
	private Integer totalRechazadosFac;
	private Integer totalSubsanadosFac;
	private Integer totalExpedientesxCanalEmpresa;
	private Integer totalExpedientesxCanalPersona;
	private double	sumaTotalFac;
	private boolean btnImprimirFacDet;
	private boolean btnImprimirFacCon;
	private boolean btnImprimirPlaDet;
	private boolean btnImprimirPlaCon;
	private boolean renderedFrame;
	private boolean bDetExpPlaExpedientes;
	private boolean bDetExpPlaSolicitudes;
	private boolean bDetExpFacExpedientes;
	private boolean bDetExpFacSolicitudes;
	private List<ExpedienteDocumento> listaDocumentosRegistrados;
	private ExpedienteDocumentoService expedienteDocumentoService;
	private ExpedienteDocumento expDocSelected;
	private Usuario usuG;
	private Oficina ofiG;
	
	private Boolean detallado;
	private Boolean consolidado;
	private FacturacionPlanillaCabeceraServices fpCabeceraServices;
	private FacturacionPlanillaDetalleServices fpDetalleServices;
	private ExpedienteTrackingService expTrackServices;
	private Boolean btnCerrarPlanilla;
	private Boolean btnCerrarFacturacion;
	private Boolean btnEliminarPlanilla;
	private Boolean btnEliminarFacturacion;
	private String nombre_excel;
	private String nombre_excel_exp;
	private List<Oficina> listOficina;
	private UsuarioServices usuServices;
	private Boolean mostrarDetalleFac;
	private Boolean mostrarConsolidadoFac;
	private Boolean mostrarDetallePla;
	private Boolean mostrarConsolidadoPla;
	private String vradiofac;
	private String vradiopla;
	private List<Usuario> listaUsuarioCon;
	private ExpedienteTrackingService expTrackService;
	
	
	private List<Oficina> listOficinaCon;
	private List<Oficina> listOficinaDet;
	
	private String[] nlistaresultados;
	private List<Usuario> listaEjecutivos;
	private List<Integer> resultfacturacion;
	private List<Integer> resultplanilla;
	private List<ExpedienteTracking> listaExpedientesAGuardar;
	private List<FacturacionPlanillaDetalle> listafpDetalleAGuardar;
	private List<Tarifa> lista;
	private HashMap<Integer, Tarifa> tb_comision_ope_temp;
	private String tabPla;
	private String tabFac;	
	private OficinaService oficinaService;
	private MultiKeyMap mapOficinas;
	
	//datos Log
    private Log log;
	private LogMB logmb;
	@ManagedProperty(value="#{loginMB.usuario}")
	private Usuario usuarioLogin;
	
	@ManagedProperty(value= "#{loginMB}")
	private LoginMB login;
	
	public FacturacionComisionesMB(){}
	
	@PostConstruct
	public void iniciass(){
		
		this.editar = Boolean.FALSE;		
		this.cicloService = new CicloService();
		this.menuServices=new MenuServices();
		this.tipoCicloService = new TipoCicloService();
		this.expedienteFilter = new Expediente();
		this.expedienteService = new ExpedienteService();
		this.tarifaServices = new TarifaService();
		this.utils = new Utils();
		this.totalExpedientesFac = 0;
		this.totalPaginasfac = 0;
		this.sumaTotalFac=0.0;
		this.oficinaService = new OficinaService();
		this.listOficina = new ArrayList<Oficina>();
		this.listOficinaCon = new ArrayList<Oficina>();
		this.listOficinaDet = new ArrayList<Oficina>();
		this.totalExpedientesxCanalEmpresa=0;
		this.totalExpedientesxCanalPersona=0;
		this.listaExpedientesPlanillables = new ArrayList<Expediente>();
		this.listaExpedientesFacturables = new ArrayList<Expediente>();
		this.listaCicloAFacturar = new ArrayList<Tarifa>();
		this.listaTarifaTemporal = new ArrayList<Tarifa>();
		this.listaTableroConteo = new ArrayList<Tarifa>();
		this.detallado=false;
		this.consolidado=true;
		this.fpCabeceraServices = new FacturacionPlanillaCabeceraServices();
		this.fpDetalleServices = new FacturacionPlanillaDetalleServices();
		this.expTrackServices = new ExpedienteTrackingService();
		this.btnCerrarPlanilla = true;
		this.btnCerrarFacturacion = true;
		this.btnEliminarPlanilla = true;
		this.btnEliminarFacturacion = true;
		this.tabPla = "rojo";
		this.tabFac = "blanco";
		this.vista=2;
		this.nombre_excel = "Consolidado de Planilla";	
		this.nombre_excel_exp = "Detalle Expedientes";	
		this.tb_comision_ope_temp = new HashMap<Integer, Tarifa>();
		this.listaEjecutivos = new ArrayList<Usuario>();
		this.usuServices = new UsuarioServices();
		this.vradiofac = "0";
		this.btnImprimirFacCon = true;
		this.btnImprimirFacDet = false;
		this.vradiopla = "0";
		this.btnImprimirPlaCon = true;
		this.btnImprimirPlaDet = false;
		this.mostrarConsolidadoFac = true;
		this.mostrarConsolidadoPla = true;
		this.listaExpedientesPlanillablesDet = new ArrayList<Expediente>();
		this.expTrackServices = new ExpedienteTrackingService();
		this.expTrackService = new ExpedienteTrackingService();
		this.expedienteDocumentoService = new ExpedienteDocumentoService();
		
		try {
			this.tipoCicloSelec = this.tipoCicloService.findById(1);
			this.tipoCiclo = this.tipoCicloSelec.getDescripcion();
			int i = 0;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		log = (Log)getSpringBean(Constante.SESSION_LOG);
		logmb = new LogMB();
		
		try {
			
			this.listaCiclosFacturacion = this.cicloService.findByTipoCicloActivo(1);
			this.listaCiclosPlanilla = this.cicloService.findByTipoCicloActivo(2);
			this.listaEjecutivos = this.usuServices.listarUsuarios();
			Menu codMenu = menuServices.opcionMenuByPretty("pretty:facturacion");
			log.setCod_menu(codMenu.getCod_menu().intValue());
			//int codMenu = menuServices.opcionMenuByPrettyCod("pretty:facturacion");
			//log.setCod_menu(codMenu);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.resultfacturacion = new ArrayList<>();
		this.resultplanilla = new ArrayList<>();		
			
			System.out.println(this.resultfacturacion.size());
			System.out.println(this.resultplanilla.size());		
		
	}		
	
	public void cambiaCicloFacturacion(){
		
		this.btnCerrarFacturacion=true;
		this.listaExpedientesFacturables = new ArrayList<Expediente>();
		
		try {
			if(this.fpCabeceraServices.findByIdCiclo(this.cod_ciclo_fac)!=null){
				this.btnEliminarFacturacion = false;
			}else{
				this.btnCerrarFacturacion=false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.totalExpedientesFac = 0;
		this.totalPaginasfac = 0;
		
	}
	
	public void cambiaCicloPlanilla(){
		
		this.btnCerrarPlanilla=true;
		this.listaExpedientesPlanillables = new ArrayList<Expediente>();
		
		try {
			if(this.fpCabeceraServices.findByIdCiclo(this.cod_ciclo_pla)!=null){
				this.btnEliminarPlanilla = false;
			}else{
				this.btnCerrarPlanilla=false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		this.totalExpedientesPla = 0;
		this.totalPaginaspla = 0;
		
	}
	
	public void mostrarDetalleUsuExpedientes(Usuario us){
		this.bDetExpPlaExpedientes = Boolean.TRUE;
		this.bDetExpPlaSolicitudes = Boolean.FALSE;
		this.bDetExpFacExpedientes = Boolean.FALSE;
		this.bDetExpFacSolicitudes = Boolean.FALSE;
		//us.getListaExpedientes()
		this.usuG = us;
		this.listaExpedienteDetalle = us.getListaExpedientes();
	}
	
	public void mostrarDetalleUsuSolicitudes(Usuario us){
		this.bDetExpPlaSolicitudes = Boolean.TRUE;
		this.bDetExpPlaExpedientes = Boolean.FALSE;
		this.bDetExpFacExpedientes = Boolean.FALSE;
		this.bDetExpFacSolicitudes = Boolean.FALSE;
		//us.getListaExpedientes()
		this.usuG = us;
		this.listaExpedienteDetalle = us.getListaSolicitudes();
	}
	
	public void mostrarDetalleExpedientes(Oficina of){
		this.bDetExpFacExpedientes = Boolean.TRUE;
		this.bDetExpPlaExpedientes = Boolean.FALSE;
		this.bDetExpPlaSolicitudes = Boolean.FALSE;
		this.bDetExpFacSolicitudes = Boolean.FALSE;
		//us.getListaExpedientes()
		this.ofiG = of;
		this.listaExpedienteDetalle = of.getListExpedientes();
	}
	
	public void mostrarDetalleSolicitudes(Oficina of){
		this.bDetExpFacSolicitudes = Boolean.TRUE;
		this.bDetExpPlaExpedientes = Boolean.FALSE;
		this.bDetExpPlaSolicitudes = Boolean.FALSE;
		this.bDetExpFacExpedientes = Boolean.FALSE;
		this.ofiG = of;
		//us.getListaExpedientes()
		this.listaExpedienteDetalle = of.getListSolicitudes();
	}
	
	public void cambiarCriterioFac(){
		if(this.vradiofac.equals("0")){
			this.btnImprimirFacCon = true;
			this.btnImprimirFacDet = false;
			this.mostrarConsolidadoFac = true;
			this.mostrarDetalleFac = false;
		}else if(this.vradiofac.equals("1")){
			this.btnImprimirFacCon = false;
			this.btnImprimirFacDet = true;
			this.mostrarConsolidadoFac = false;
			this.mostrarDetalleFac = true;
		}
	}
	
	public void cambiarCriterioPla(){
		if(this.vradiopla.equals("0")){
			this.btnImprimirPlaCon = true;
			this.btnImprimirPlaDet = false;
			this.mostrarConsolidadoPla = true;
			this.mostrarDetallePla = false;
		}else if(this.vradiopla.equals("1")){
			this.btnImprimirPlaCon = false;
			this.btnImprimirPlaDet = true;
			this.mostrarConsolidadoPla = false;
			this.mostrarDetallePla = true;
		}
	}	
	
	public void ejecutarFacturacion(){
				
		try {
			RequestContext context = RequestContext.getCurrentInstance(); 
			System.out.println("PROCESANDO FACTURACI�N ........... ");
			this.expedienteFilter.setId_estado(5);
			this.cicloSelecFacturacion = this.cicloService.findById(this.cod_ciclo_fac);
			
			this.listaTarifaTemporal = this.tarifaServices.findByIdCiclo(this.cod_ciclo_fac);
			
			if(this.listaTarifaTemporal.size()>0){
				
			this.expedienteFilter.setFec_ini(this.cicloSelecFacturacion.getFecini());
			this.expedienteFilter.setFec_fin(this.cicloSelecFacturacion.getFecfin());
			//this.expedienteFilter.setResultados(this.resultfacturacion);
			
			this.listaExpedientesFacturables = this.expedienteService.procesaFacturacion(this.expedienteFilter);
			
			System.out.println("Tama�o de la lista: "+this.listaExpedientesFacturables.size());
						
			//this.listaCicloAFacturar = this.tableroComisionesServices.findByIdCiclo(this.cod_ciclo_fac);
			
			if(this.cod_ciclo_fac > 0){
				try {
					this.listaRangoFacturacion=this.tarifaServices.listarTarifa(1, this.cod_ciclo_fac);
					System.out.println("Tama�o del rango: "+this.listaRangoFacturacion.size());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				System.out.println("No paso!!!!");
			}
			//eliminarFueraDePlazo();
			this.totalExpedientesFac = this.listaExpedientesFacturables.size();
			this.totalPaginasfac = 0;
			cargarCostosFacturacion();
			Expediente etot = new Expediente();
			etot.setCantidadHojas(0);
			etot.setTotFacturar(0.0);
			this.listaExpedientesFacturablesDet = this.listaExpedientesFacturables;
			for (Expediente e : this.listaExpedientesFacturablesDet) {
				if(e.getCantidadHojas() == null){
					e.setCantidadHojas(0);
					
				}
				System.out.println("Entrando al for de asignaci�n ...");
				System.out.println("Asignando el costo: "+this.listaRangoFacturacion.get(0).getCosto());
					e.setCosto(this.listaRangoFacturacion.get(0).getCosto());
					System.out.println("Costo asignado: "+e.getCosto());	
					e.setTotFacturar(e.getCantidadHojas()*e.getCosto());
					etot.setCantidadHojas(etot.getCantidadHojas()+e.getCantidadHojas());
					//exptotal.setTotFacturar(exptotal.getTotFacturar()+expdet.getCosto());
					etot.setTotFacturar(etot.getTotFacturar()+e.getTotFacturar());
					this.totalPaginasfac = this.totalPaginasfac + e.getCantidadHojas();
			}
			
			etot.setUsuarioAsignado("TOTAL");			
			
			if(this.listaExpedientesFacturablesDet.size()>0){
				this.listaExpedientesFacturablesDet.add(etot);
			}
						
			if(this.fpCabeceraServices.findByIdCiclo(this.cod_ciclo_fac)!=null){
				FacesUtils.showFacesMessage("La facturaci�n procesada ya fue cerrada", 3);
				this.btnEliminarFacturacion = false;
				context.update("msgGeneral");
			}else{
				this.btnCerrarFacturacion=false;
			}
			//this.btnCerrarFacturacion=false;
			}else{
				FacesUtils.showFacesMessage("No existe Tablero de Comisiones para el ciclo seleccionado", 3);
				context.update("msgGeneral");
			}
			System.out.println("FINALIZ�N PROCESO ........... ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}
	
	public void ejecutarPlanilla(){
		
		try {
			
			this.listaEjecutivos = this.usuServices.listarUsuarios();
			RequestContext context = RequestContext.getCurrentInstance(); 
			System.out.println("PROCESANDO PLANILLA ........... ");
			this.expedienteFilter.setId_estado(5);
			this.cicloSelecPlanilla = this.cicloService.findById(this.cod_ciclo_pla);
			
			this.listaTarifaTemporal = this.tarifaServices.findByIdCiclo(this.cod_ciclo_pla);
			
			if(this.listaTarifaTemporal.size()>0){
				
			this.expedienteFilter.setFec_ini(this.cicloSelecPlanilla.getFecini());
			this.expedienteFilter.setFec_fin(this.cicloSelecPlanilla.getFecfin());
			//this.expedienteFilter.setResultados(this.resultfacturacion);
			
			this.listaExpedientesPlanillables = this.expedienteService.procesaPlanilla(this.expedienteFilter);
			
			System.out.println("Tama�o de la lista: "+this.listaExpedientesPlanillables.size());
						
			
			if(this.cod_ciclo_pla > 0){
				try {
					this.listaRangoPlanilla=this.tarifaServices.listarTarifa(2, this.cod_ciclo_pla);
					System.out.println("Tama�o del rango: "+this.listaRangoPlanilla.size());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				System.out.println("No paso!!!!");
			}
			
			cargarCantidadesXEjecutivo();
			this.totalExpedientesPla = this.listaExpedientesPlanillables.size();
			this.listaExpedientesPlanillablesDet = this.listaExpedientesPlanillables;
			
			Expediente exptotal = new Expediente();
			exptotal.setCosto(0.0);
			exptotal.setCantidadHojas(0);
			exptotal.setTotFacturar(0.0);
			
			for (Expediente expdet : this.listaExpedientesPlanillablesDet) {
				
				//exptotal.setCosto(exptotal.getCosto()+expdet.getCosto());
				
				if(expdet.getCantidadHojas() == null){
					expdet.setCantidadHojas(0);
					
				}
				
				exptotal.setCantidadHojas(exptotal.getCantidadHojas()+expdet.getCantidadHojas());
				//exptotal.setTotFacturar(exptotal.getTotFacturar()+expdet.getCosto());
				exptotal.setTotFacturar(exptotal.getTotFacturar()+expdet.getTotFacturar());
			}		
			
			exptotal.setUsuarioAsignado("TOTAL");			
			
			if(this.listaExpedientesPlanillablesDet.size()>0){
				this.listaExpedientesPlanillablesDet.add(exptotal);
			}
			
			this.totalPaginaspla = 0;
			//cargarCostosFacturacion();
			
			for(Usuario usu: this.listaEjecutivos){
				if(usu.getNroPaginas()== null){
					usu.setNroPaginas(0);
				}
				
				
				
				this.totalPaginaspla = this.totalPaginaspla + usu.getNroPaginas();
			}
			
			/*for (Expediente e : this.listaExpedientesPlanillables) {
				System.out.println("Entrando al for de asignaci�n ...");
				System.out.println("Asignando el costo: "+this.listaRangoPlanilla.get(0).getCosto());
					e.setCosto(this.listaRangoPlanilla.get(0).getCosto());
					System.out.println("Costo asignado: "+e.getCosto());	
					this.totalPaginaspla = this.totalPaginaspla + e.getCantidadHojas();
			}*/
			
			
			
			if(this.fpCabeceraServices.findByIdCiclo(this.cod_ciclo_pla)!=null){
				FacesUtils.showFacesMessage("La planilla procesada ya fue cerrada", 3);
				this.btnEliminarPlanilla = false;
				context.update("msgGeneral");
			}else{
				this.btnCerrarPlanilla=false;
			}
			//this.btnCerrarPlanilla=false;
			}else{
				FacesUtils.showFacesMessage("No existe Tablero de Comisiones para el ciclo seleccionado", 3);
				context.update("msgGeneral");
			}
			System.out.println("FINALIZ�N PROCESO ........... ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}
	
	public void visualizarPagina(SelectEvent event){
		System.out.println("VISUALIZAR");
		this.renderedFrame = Boolean.TRUE;
	}
	
	public void visualizarPaginaOLD(ExpedienteDocumento ed){
		this.expDocSelected = ed;
		System.out.println("VISUALIZAR");
		this.renderedFrame = Boolean.TRUE;
	}
	
	public void verPaginas(Expediente exp){
		try {
			System.out.println("VER: "+exp.getExpediente_id());
			this.renderedFrame = Boolean.FALSE;
			this.listaDocumentosRegistrados = this.expedienteDocumentoService.listFilesSinEliminadosbyExpediente_id(exp.getExpediente_id());
			System.out.println("VER: "+listaDocumentosRegistrados.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void cerrarFacturacion(){
		try {
			RequestContext context = RequestContext.getCurrentInstance(); 
			System.out.println("INCIANDO CIERRE FACTURACI�N .......");
		FacturacionPlanillaCabecera fpCabecera= new FacturacionPlanillaCabecera();
		
		fpCabecera.setCod_ciclo(this.cod_ciclo_fac);
		fpCabecera.setFecha(new Date());
		fpCabecera.setIdusuario(this.usuarioLogin.getIdusuario());
		fpCabecera.setNro_registros(this.listaExpedientesFacturables.size());
		
		this.fpCabeceraServices.crearFacturacionPlanillaCabecera(fpCabecera);
		
		log.setAccion("INSERT");
        log.setDescripcion("Se insert� la planilla cabecera del ciclo: "+this.cod_ciclo_fac);
        logmb.insertarLog(log);
		
		fpCabecera= new FacturacionPlanillaCabecera();
		
		fpCabecera= this.fpCabeceraServices.findByIdCiclo(this.cod_ciclo_fac);
				
		this.listaExpedientesAGuardar = new ArrayList<ExpedienteTracking>();
		this.listafpDetalleAGuardar = new ArrayList<FacturacionPlanillaDetalle>();
		
		
		for (Expediente exp : this.listaExpedientesFacturables) {
			
			System.out.println("Entrando al for ........");
			FacturacionPlanillaDetalle fpDetalle = new FacturacionPlanillaDetalle();
			fpDetalle.setCod_exp_tracking(exp.getId_tracking());
			fpDetalle.setCod_expediente(exp.getExpediente_id());
			fpDetalle.setCosto_planilla_facturacion(exp.getCosto());
			fpDetalle.setId_facturacion_planilla_cabecera(fpCabecera.getId_facturacion_planilla_cabecera());
			fpDetalle.setIdusuario(exp.getIdusuario());
			fpDetalle.setCod_tab_com(this.listaRangoFacturacion.get(0).getId_tarifa());
			fpDetalle.setFlgcomisionado(true);
			
			this.listafpDetalleAGuardar.add(fpDetalle);
			
			//this.fpDetalleServices.crearFacturacionPlanillaDetalle(fpDetalle);
			
			ExpedienteTracking expTrack = new ExpedienteTracking();
			expTrack.setId_expediente_tracking(exp.getId_tracking());
			expTrack.setFacturado(true);
			
			this.listaExpedientesAGuardar.add(expTrack);
			
			//this.expTrackServices.actualizarExpedienteTrackingxPagado(expTrack);
			
		}
		
		System.out.println("Entrando a grabar la planilla ........");
		
		//this.fpDetalleServices.crearFacturacionPlanillaDetalle(this.listafpDetalleAGuardar.get(0));
		
		this.fpDetalleServices.insertBatchFacturacionPlanillaDetalle(this.listafpDetalleAGuardar);
		
		log.setAccion("INSERT");
        log.setDescripcion("Se insertaron los registros de los expedientes a pagar del expediente cabecera: "+fpCabecera.getId_facturacion_planilla_cabecera());
        logmb.insertarLog(log);
		
		System.out.println("Entrando a actualizar la planilla del expediente ........");
		this.expTrackServices.updateBatchExpedienteTrackingxFacturado(this.listaExpedientesAGuardar);
		
		log.setAccion("UPDATE");
        log.setDescripcion("Se actualiz� la planilla de los registros del expediente cabecera: "+fpCabecera.getId_facturacion_planilla_cabecera());
        logmb.insertarLog(log);
		
		this.cicloSelecFacturacion.setEstado(false);
		
		this.cicloService.actualizarEstadoCiclo(this.cicloSelecFacturacion);
		
		log.setAccion("UPDATE");
        log.setDescripcion("Se actualiz� el estado del ciclo: "+this.cicloSelecFacturacion.getCod_ciclo());
        logmb.insertarLog(log);
		
		FacesUtils.showFacesMessage("Se ha cerrado facturaci�n", 3);
		context.update("msgGeneral");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.btnCerrarFacturacion=true;
		System.out.println("FINALIZ� CIERRE FACTURACI�N!!!!");
	}	
	
	public void cerrarPlanilla(){
		
		try {
			RequestContext context = RequestContext.getCurrentInstance(); 
			System.out.println("INCIANDO CIERRE FACTURACI�N .......");
		FacturacionPlanillaCabecera fpCabecera= new FacturacionPlanillaCabecera();
		
		fpCabecera.setCod_ciclo(this.cod_ciclo_pla);
		fpCabecera.setFecha(new Date());
		fpCabecera.setIdusuario(this.usuarioLogin.getIdusuario());
		fpCabecera.setNro_registros(this.listaExpedientesPlanillables.size());
		
		this.fpCabeceraServices.crearFacturacionPlanillaCabecera(fpCabecera);
		
		log.setAccion("INSERT");
        log.setDescripcion("Se insert� la facturaci�n cabecera del ciclo: "+this.cod_ciclo_pla);
        logmb.insertarLog(log);
		
		fpCabecera= new FacturacionPlanillaCabecera();
		
		fpCabecera= this.fpCabeceraServices.findByIdCiclo(this.cod_ciclo_pla);
				
		this.listaExpedientesAGuardar = new ArrayList<ExpedienteTracking>();
		this.listafpDetalleAGuardar = new ArrayList<FacturacionPlanillaDetalle>();
		
		
		for (Expediente exp : this.listaExpedientesPlanillables) {
			
			if(!exp.getUsuarioAsignado().equals("TOTAL")){
			
			System.out.println("Entrando al for ........");
			FacturacionPlanillaDetalle fpDetalle = new FacturacionPlanillaDetalle();
			fpDetalle.setCod_exp_tracking(exp.getId_tracking());
			fpDetalle.setCod_expediente(exp.getExpediente_id());
			fpDetalle.setCosto_planilla_facturacion(exp.getCosto());
			fpDetalle.setId_facturacion_planilla_cabecera(fpCabecera.getId_facturacion_planilla_cabecera());
			fpDetalle.setIdusuario(exp.getIdusuario());
			fpDetalle.setCod_tab_com(this.listaRangoPlanilla.get(0).getId_tarifa());
			fpDetalle.setFlgcomisionado(true);
			
			this.listafpDetalleAGuardar.add(fpDetalle);
			
			//this.fpDetalleServices.crearFacturacionPlanillaDetalle(fpDetalle);
			
			ExpedienteTracking expTrack = new ExpedienteTracking();
			expTrack.setId_expediente_tracking(exp.getId_tracking());
			expTrack.setFacturado(true);
			
			this.listaExpedientesAGuardar.add(expTrack);
			
			//this.expTrackServices.actualizarExpedienteTrackingxPagado(expTrack);
			}
		}
		
		System.out.println("Entrando a grabar la planilla ........");
		
		//this.fpDetalleServices.crearFacturacionPlanillaDetalle(this.listafpDetalleAGuardar.get(0));
		
		this.fpDetalleServices.insertBatchFacturacionPlanillaDetalle(this.listafpDetalleAGuardar);
		
		log.setAccion("INSERT");
        log.setDescripcion("Se insertaron los registros de los expedientes a pagar del expediente cabecera: "+fpCabecera.getId_facturacion_planilla_cabecera());
        logmb.insertarLog(log);
		
		System.out.println("Entrando a actualizar la planilla del expediente ........");
		this.expTrackServices.updateBatchExpedienteTrackingxFacturado(this.listaExpedientesAGuardar);
		
		log.setAccion("UPDATE");
        log.setDescripcion("Se actualiz� la planilla de los registros del expediente cabecera: "+fpCabecera.getId_facturacion_planilla_cabecera());
        logmb.insertarLog(log);
        
		this.cicloSelecPlanilla.setEstado(false);
		
		this.cicloService.actualizarEstadoCiclo(this.cicloSelecPlanilla);
		
		log.setAccion("UPDATE");
        log.setDescripcion("Se actualiz� el estado del ciclo: "+this.cicloSelecPlanilla.getCod_ciclo());
        logmb.insertarLog(log);
		
		FacesUtils.showFacesMessage("Se ha cerrado planilla", 3);
		context.update("msgGeneral");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.btnCerrarPlanilla=true;
		System.out.println("FINALIZ� CIERRE FACTURACI�N!!!!");
	}
	
	public void cargarCantidadesXEjecutivo(){
		
		for(Expediente exp : this.listaExpedientesPlanillables){
			for(Usuario usu: this.listaEjecutivos){
				if(exp.getIdusuario().equals(usu.getIdusuario())){
					
					if(usu.getNroPaginas()== null){
						usu.setNroPaginas(0);
					}					
					if(usu.getListaExpedientes()==null){
						usu.setListaExpedientes(new ArrayList<Expediente>());
					}
					if(usu.getListaSolicitudes()==null){
						usu.setListaSolicitudes(new ArrayList<Expediente>());
					}
					if(exp.getCantidadHojas()== null){
						exp.setCantidadHojas(0);
					}
					if(usu.getCantidadExpedientes()== null){
						usu.setCantidadExpedientes(0);
					}
					if(usu.getTotalExpedientes() == null){
						usu.setTotalExpedientes(0);
					}					
					if(usu.getTotalSolicitudes() == null){
						usu.setTotalSolicitudes(0);
					}
					
					if(usu.getTotalPaginasExpedientes() == null){
						usu.setTotalPaginasExpedientes(0);
					}					
					if(usu.getTotalPaginasSolicitudes() == null){
						usu.setTotalPaginasSolicitudes(0);
					}
					
					if(((Double)usu.getTotalFacturar())==null){
						usu.setTotalFacturar(0.0);
					}					
					if(usu.getId_cargo() == null){
						usu.setId_cargo(0);
					}					
					if(usu.getId_cargo() == 1){
						
						usu.setCosto(this.listaRangoPlanilla.get(0).getCosto_supervisor());
						
					}					
					if(usu.getId_cargo() == 2){
						
						usu.setCosto(this.listaRangoPlanilla.get(0).getCosto_digitalizador());
						
					}
					if(usu.getId_cargo() == 3){
	
						usu.setCosto(this.listaRangoPlanilla.get(0).getCosto_back());
	
					}
					
					usu.setNroPaginas(usu.getNroPaginas()+exp.getCantidadHojas());
					usu.setCantidadExpedientes(usu.getCantidadExpedientes()+1);
					
					
					if(exp.getTipo_doc().equals("EXPEDIENTE")){
						usu.setTotalExpedientes(usu.getTotalExpedientes()+1);
						usu.getListaExpedientes().add(exp);
						usu.setTotalPaginasExpedientes(usu.getTotalPaginasExpedientes()+exp.getCantidadHojas());
					}
					if(exp.getTipo_doc().equals("SOLICITUD")){
						usu.setTotalSolicitudes(usu.getTotalSolicitudes()+1);
						usu.getListaSolicitudes().add(exp);
						usu.setTotalPaginasSolicitudes(usu.getTotalPaginasSolicitudes()+exp.getCantidadHojas());
					}
					
					usu.setDni(exp.getNum_doc());
					System.out.println("Total factura: "+usu.getTotalFacturar());
					System.out.println("Nro Paginas: "+usu.getNroPaginas());
					System.out.println("Tarifa: "+this.listaRangoPlanilla.get(0).getCosto());
					usu.setTotalFacturar(usu.getTotalFacturar()+(exp.getCantidadHojas()*usu.getCosto()));
					exp.setCosto(usu.getCosto());
					exp.setTotFacturar(exp.getCantidadHojas()*usu.getCosto());
					exp.setDesOficina(usu.getDesOficina());
					exp.setDesCargo(usu.getDesCargo());
				}
			}
		}
		this.listaUsuarioCon = new ArrayList<Usuario>();
		
		Usuario usutot = new Usuario();
		usutot.setNroPaginas(0);
		usutot.setCantidadExpedientes(0);
		usutot.setTotalFacturar(0.0);
		usutot.setListaExpedientes(new ArrayList<Expediente>());
		usutot.setListaSolicitudes(new ArrayList<Expediente>());
		usutot.setTotalPaginasExpedientes(0);
		usutot.setTotalPaginasSolicitudes(0);
		
		for(Usuario usu: this.listaEjecutivos){
			if(usu.getCantidadExpedientes() == null){
				usu.setCantidadExpedientes(0);
			}
			
			if(usu.getNroPaginas()== null){
				usu.setNroPaginas(0);
			}
					
			if(usu.getListaExpedientes()==null){
				usu.setListaExpedientes(new ArrayList<Expediente>());
			}
			if(usu.getListaSolicitudes()==null){
				usu.setListaSolicitudes(new ArrayList<Expediente>());
			}
			
			if(usu.getTotalPaginasExpedientes() == null){
				usu.setTotalPaginasExpedientes(0);
			}					
			if(usu.getTotalPaginasSolicitudes() == null){
				usu.setTotalPaginasSolicitudes(0);
			}
			
			if(((Double)usu.getTotalFacturar())==null){
				usu.setTotalFacturar(0.0);
			}
			
			usutot.getListaSolicitudes().addAll(usu.getListaSolicitudes());
			usutot.getListaExpedientes().addAll(usu.getListaExpedientes());
			usutot.setTotalPaginasExpedientes(usutot.getTotalPaginasExpedientes()+usu.getTotalPaginasExpedientes());
			usutot.setTotalPaginasSolicitudes(usutot.getTotalPaginasSolicitudes()+usu.getTotalPaginasSolicitudes());
			usutot.setNroPaginas(usutot.getNroPaginas()+usu.getNroPaginas());
			if(usu.getCantidadExpedientes() > 0){
				
				usutot.setCantidadExpedientes(usutot.getCantidadExpedientes()+usu.getCantidadExpedientes());
				usutot.setTotalFacturar(usutot.getTotalFacturar()+usu.getTotalFacturar());
				this.listaUsuarioCon.add(usu);
			}
		}
		
		usutot.setUsuarioAsignado("TOTAL: ");
		if(this.listaUsuarioCon.size()>0){
			this.listaUsuarioCon.add(usutot);
		}
	}
	
	public void cargarCostosFacturacion(){
				
		this.mapOficinas = new MultiKeyMap();
		try {
			this.listOficina = new ArrayList<Oficina>();
			this.listOficina = this.oficinaService.findAll();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		this.listOficinaDet = new ArrayList<Oficina>();
		this.listOficinaCon = new ArrayList<Oficina>();
		for (Expediente e : this.listaExpedientesFacturables) {
			
			//for (Tarifa tabcom : this.listaRangoFacturacion) {
				for(Oficina o: this.listOficina){
						//e.setCosto(this.listaRangoFacturacion.get(0).getCosto());
						//e.setId_tarifa(this.listaRangoFacturacion.get(0).getId_tarifa());
					if(o.getCantidadHojas() == null){
						o.setCantidadHojas(0);
					}
					
					if(e.getCantidadHojas() == null){
						e.setCantidadHojas(0);
					}
					
					if(o.getTotalExpediente() == null){
						o.setTotalExpediente(0);
					}
					
					if(o.getTotalExpedientes() == null){
						o.setTotalExpedientes(0);
					}
					
					if(o.getTotalSolicitudes() == null){
						o.setTotalSolicitudes(0);
					}
					
					if(((Double)o.getTotalFacturar())==null){
						o.setTotalFacturar(0);
					}
					
					if(o.getListSolicitudes()== null){
						o.setListSolicitudes(new ArrayList<Expediente>());
					}
					
					if(o.getTotalPaginasExpedientes() == null){
						o.setTotalPaginasExpedientes(0);
					}					
					if(o.getTotalPaginasSolicitudes() == null){
						o.setTotalPaginasSolicitudes(0);
					}
					
					if(o.getListExpedientes()== null){
						o.setListExpedientes(new ArrayList<Expediente>());
					}
					
					if(e.getId_oficina().equals(o.getId_oficina())){
						
						System.out.println("VAMOS A VERIFICAR");
						System.out.println("Cantidad de Hojas: "+e.getCantidadHojas());
						System.out.println("Cantidad de Expedientes: "+o.getTotalExpediente());
						System.out.println("Total a Facturar: "+(e.getCantidadHojas()*this.listaRangoFacturacion.get(0).getCosto()));
						
						o.setCantidadHojas(o.getCantidadHojas()+e.getCantidadHojas());
						o.setTotalExpediente(o.getTotalExpediente()+1);
						
						if(e.getTipo_doc().equals("EXPEDIENTE")){
							o.setTotalExpedientes(o.getTotalExpedientes()+1);
							o.getListExpedientes().add(e);
							o.setTotalPaginasExpedientes(o.getTotalPaginasExpedientes()+e.getCantidadHojas());
						}
						
						if(e.getTipo_doc().equals("SOLICITUD")){
							o.setTotalSolicitudes(o.getTotalSolicitudes()+1);
							o.getListSolicitudes().add(e);
							o.setTotalPaginasSolicitudes(o.getTotalPaginasSolicitudes()+e.getCantidadHojas());
						}
						
						o.setTotalFacturar(o.getTotalFacturar()+(e.getCantidadHojas()*this.listaRangoFacturacion.get(0).getCosto()));
					
					}
					
					
					//o.setCantidadHojas(o.);
				}
								
			//}
			//System.out.println("Costo:"+e.getCosto());
		}		
		
		Oficina ofi = new Oficina();
		ofi.setCantidadHojas(0);
		ofi.setTotalExpediente(0);
		ofi.setTotalExpedientes(0);
		ofi.setTotalSolicitudes(0);
		ofi.setTotalFacturar(0.0);
		ofi.setListExpedientes(new ArrayList<Expediente>());
		ofi.setListSolicitudes(new ArrayList<Expediente>());
		ofi.setTotalPaginasExpedientes(0);
		ofi.setTotalPaginasSolicitudes(0);
		
		for(Oficina of: this.listOficina){
			if(of.getTotalExpediente() == null){
				of.setTotalExpediente(0);
			}
			
			if(of.getTotalExpedientes() == null){
				of.setTotalExpedientes(0);
			}
			
			if(of.getTotalSolicitudes() == null){
				of.setTotalSolicitudes(0);
			}
			
			if(of.getCantidadHojas() == null){
				of.setCantidadHojas(0);
			}
			
			if((Double)of.getTotalFacturar() == null){
				of.setTotalFacturar(0);
			}
			
			if(of.getListSolicitudes()== null){
				of.setListSolicitudes(new ArrayList<Expediente>());
			}
			
			if(of.getListExpedientes()== null){
				of.setListExpedientes(new ArrayList<Expediente>());
			}
			
			if(of.getTotalExpediente()>0){
				of.setCosto(this.listaRangoFacturacion.get(0).getCosto());
				this.listOficinaDet.add(of);
			}
			
			ofi.setTotalPaginasExpedientes(ofi.getTotalPaginasExpedientes()+of.getTotalPaginasExpedientes());
			ofi.setTotalPaginasSolicitudes(ofi.getTotalPaginasSolicitudes()+of.getTotalPaginasSolicitudes());
			ofi.setCantidadHojas(ofi.getCantidadHojas()+of.getCantidadHojas());
			ofi.setTotalExpediente(ofi.getTotalExpediente()+of.getTotalExpediente());
			ofi.setTotalExpedientes(ofi.getTotalExpedientes()+of.getTotalExpedientes());
			ofi.setTotalSolicitudes(ofi.getTotalSolicitudes()+of.getTotalSolicitudes());
			ofi.getListSolicitudes().addAll(of.getListSolicitudes());
			ofi.getListExpedientes().addAll(of.getListExpedientes());
			ofi.setTotalFacturar(ofi.getTotalFacturar()+of.getTotalFacturar());			
			
		}
		
		ofi.setNombre("TOTAL: ");
		
		ofi.setCosto(this.listaRangoFacturacion.get(0).getCosto());
		if(this.listOficinaDet.size()>0){
			this.listOficinaDet.add(ofi);
		}
		this.listOficinaCon.add(ofi);
		
	}
	
	public void cambiaRadio()
    {
		if(this.vista == 1){
			this.detallado=true;
			this.consolidado=false;
		}else if(this.vista == 2){
			this.detallado=false;
			this.consolidado=true;
		}
    }
	
	public void onTabChange(TabChangeEvent event) {
		if (event.getTab().getId().equals("tabPlanilla")){
			this.tabFac = "blanco";
			this.tabPla = "rojo";
			
		}else{
			this.tabFac = "rojo";
			this.tabPla = "blanco";
		}
        //FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	
	public void eliminarPlanilla(){
		try {
		FacturacionPlanillaCabecera fpCabecera = this.fpCabeceraServices.findByIdCiclo(this.cod_ciclo_pla);
		
		//List<FacturacionPlanillaDetalle> listFpDet = this.fpDetalleServices.findByIdCabeceraDetalle(fpCabecera.getId_facturacion_planilla_cabecera());
		
		/*List<ExpedienteTracking> listExpTrack = this.expTrackService.findByIdDetalle(fpCabecera.getId_facturacion_planilla_cabecera());
		
		for (ExpedienteTracking expTrack : listExpTrack) {
			
			expTrack.setFacturado(null);
			
		}*/		
		
		
		this.fpDetalleServices.eliminarFacturacionPlanillaDetalleXCabecera(fpCabecera.getId_facturacion_planilla_cabecera());
		//this.expTrackService.updateBatchExpedienteTrackingxFacturado(listExpTrack);
		this.fpCabeceraServices.eliminarFacturacionPlanillaCabecera(fpCabecera.getId_facturacion_planilla_cabecera());
		
		Ciclo c = new Ciclo();
		c.setCod_ciclo(this.cod_ciclo_pla);
		c.setFlag_tab(null);
		
		this.cicloService.actualizarEstadoCiclo(c);
		
		//this.cicloService.actualizarFlagTab(c);
		
		this.btnEliminarPlanilla = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void eliminarFacturacion(){
		try {
		FacturacionPlanillaCabecera fpCabecera = this.fpCabeceraServices.findByIdCiclo(this.cod_ciclo_fac);
		
		List<ExpedienteTracking> listExpTrack = this.expTrackService.findByIdDetalle(fpCabecera.getId_facturacion_planilla_cabecera());
		
		for (ExpedienteTracking expTrack : listExpTrack) {
			
			expTrack.setFacturado(null);
			
		}
		
		this.fpDetalleServices.eliminarFacturacionPlanillaDetalleXCabecera(fpCabecera.getId_facturacion_planilla_cabecera());
		this.expTrackService.updateBatchExpedienteTrackingxFacturado(listExpTrack);
		this.fpCabeceraServices.eliminarFacturacionPlanillaCabecera(fpCabecera.getId_facturacion_planilla_cabecera());
		
		Ciclo c = new Ciclo();
		c.setCod_ciclo(this.cod_ciclo_fac);
		c.setFlag_tab(null);
		
		this.cicloService.actualizarEstadoCiclo(c);
		
		//this.cicloService.actualizarFlagTab(c);
		this.btnEliminarFacturacion = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void postProcessXLS(Object document) {
        HSSFWorkbook wb = (HSSFWorkbook) document;
        HSSFSheet sheet = wb.getSheetAt(0);
        
        
        
        HSSFRow header = sheet.getRow(0); 
        sheet.setColumnWidth(2, 250);
        
        /**Pintando las columnas cabeceras*/
        
        Font font;
        
        
        HSSFCellStyle cellStyle = wb.createCellStyle(); 
        cellStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        
         
        for(int i=0; i < header.getPhysicalNumberOfCells();i++) {
            HSSFCell cell = header.getCell(i);
            sheet.autoSizeColumn(i); 
            cell.setCellStyle(cellStyle);
        }

        
        /** seteando typo de de dato double a los numeros*/
        HSSFCellStyle cellnumeric = wb.createCellStyle();
        HSSFDataFormat hssfDataFormat2 = wb.createDataFormat(); 
        cellnumeric.setDataFormat(hssfDataFormat2.getFormat("#,##0.00"));
        
        /*for (int j = 0; j < this.listOficinaDet.size()+1; j++) {
        	  HSSFRow rows = sheet.getRow(j+1); 
        	  for(int k=0; k < rows.getPhysicalNumberOfCells();k++) {
                  HSSFCell cell = rows.getCell(k);
                  if(k>3){
                	  cell.setCellValue(stringNumberToDouble(cell.getStringCellValue()));
                	  cell.setCellStyle(cellnumeric);
                	  cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                  }
              }
		}*/
        
    }
	

	public void imprimirFacturacionConsolidadoXLS(){
		String par_periodo="";
		Negocio negocioParametro=  new Negocio();

		List<Oficina> listaRpt = new ArrayList<Oficina>();

		
		try {
			ServletContext servletContext = (ServletContext) (FacesContext.getCurrentInstance().getExternalContext().getContext());
		
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat formatofechahora = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
			String fechaActual = formato.format(new Date());
			
			/*if((Integer.valueOf(this._negocio))== 0){
				par_negocio="TODOS"; 
			}else{
				negocioParametro= this.negocioServices.findById((Integer.valueOf(this._negocio))); 
				par_negocio= negocioParametro.getDescripcion(); 
			}*/
			DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			par_periodo = this.utils.convertirANombre(this.cicloSelecFacturacion.getMes()) + " - " + this.cicloSelecFacturacion.getAnio() + " - " + sdf.format(this.cicloSelecFacturacion.getFecini()) + " - " + sdf.format(this.cicloSelecFacturacion.getFecfin()); 
			
			System.out.println("Consolidado Facturacion: "+par_periodo);
			
			Integer total = this.listOficinaCon.size();
			String p_logo = ExportarArchivo.getPath("/resources/img/certicom.jpg");
			
			Map<String, Object> input =new  HashMap<String,Object>();
			input.put("P_TOTAL", total.toString());
			input.put("P_PERIODO",par_periodo);
		//	 input.put("P_LOGO", p_logo);
			input.put(JRParameter.REPORT_LOCALE, new Locale("es"));

			
			String path = ExportarArchivo.getPath("/resources/reports/jxrml/rptConsolidadoFacturacion.jasper");
			HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
			
			log.setAccion("PRINT");
	        log.setDescripcion("El usuario "+this.login.getLoginUsuario()+" ha impreso el archivo rptConsolidadoFacturacion.xls");
	        logmb.insertarLog(log);
			
			byte[] bytes;
				bytes = ExportarArchivo.exportXls(path, input,this.listOficinaCon,false);
			ExportarArchivo.executeExccel(bytes, "rptConsolidadoFacturacion.xls");
			FacesContext.getCurrentInstance().responseComplete();
			} catch (Exception e) {
				e.printStackTrace();
			}	
	}
	
	public void imprimirFacturacionDetalleXLS(){
		String par_periodo="";
		Negocio negocioParametro=  new Negocio();

		List<Oficina> listaRpt = new ArrayList<Oficina>();

		
		try {
			ServletContext servletContext = (ServletContext) (FacesContext.getCurrentInstance().getExternalContext().getContext());
		
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat formatofechahora = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
			String fechaActual = formato.format(new Date());
			
			/*if((Integer.valueOf(this._negocio))== 0){
				par_negocio="TODOS"; 
			}else{
				negocioParametro= this.negocioServices.findById((Integer.valueOf(this._negocio))); 
				par_negocio= negocioParametro.getDescripcion(); 
			}*/
			DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			par_periodo = this.utils.convertirANombre(this.cicloSelecFacturacion.getMes()) + " - " + this.cicloSelecFacturacion.getAnio() + " - " + sdf.format(this.cicloSelecFacturacion.getFecini()) + " - " + sdf.format(this.cicloSelecFacturacion.getFecfin()); 
			System.out.println("Detalle Facturacion: "+par_periodo);
			//Integer total = this.listOficinaDet.size();
			Integer total = listaExpedientesFacturablesDet.size();
			String p_logo = ExportarArchivo.getPath("/resources/img/certicom.jpg");
			
			Map<String, Object> input =new  HashMap<String,Object>();
			input.put("P_TOTAL", total.toString());
			input.put("P_PERIODO",par_periodo);
		//	 input.put("P_LOGO", p_logo);
			input.put(JRParameter.REPORT_LOCALE, new Locale("es"));

			
			String path = ExportarArchivo.getPath("/resources/reports/jxrml/rptDetalleFacturacion.jasper");
			HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
			
			log.setAccion("PRINT");
	        log.setDescripcion("El usuario "+this.login.getLoginUsuario()+" ha impreso el archivo rptDetalleFacturacion.xls");
	        logmb.insertarLog(log);
			
			byte[] bytes;
				//bytes = ExportarArchivo.exportXls(path, input,this.listOficinaDet,false);
			bytes = ExportarArchivo.exportXls(path, input,this.listaExpedientesFacturablesDet,false);
			ExportarArchivo.executeExccel(bytes, "rptDetalleFacturacion.xls");
			FacesContext.getCurrentInstance().responseComplete();
			} catch (Exception e) {
				e.printStackTrace();
			}	
	}
	

	public void imprimirPlanillaConsolidadoXLS(){
		String par_periodo="";
		Negocio negocioParametro=  new Negocio();

		List<Oficina> listaRpt = new ArrayList<Oficina>();

		
		try {
			ServletContext servletContext = (ServletContext) (FacesContext.getCurrentInstance().getExternalContext().getContext());
		
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat formatofechahora = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
			String fechaActual = formato.format(new Date());
			
			/*if((Integer.valueOf(this._negocio))== 0){
				par_negocio="TODOS"; 
			}else{
				negocioParametro= this.negocioServices.findById((Integer.valueOf(this._negocio))); 
				par_negocio= negocioParametro.getDescripcion(); 
			}*/
			DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			par_periodo = this.utils.convertirANombre(this.cicloSelecPlanilla.getMes()) + " - " + this.cicloSelecPlanilla.getAnio() + " - " + sdf.format(this.cicloSelecPlanilla.getFecini()) + " - " + sdf.format(this.cicloSelecPlanilla.getFecfin()); 
			System.out.println("Consolidado Planilla: "+par_periodo);
			Integer total = this.listaUsuarioCon.size();
			String p_logo = ExportarArchivo.getPath("/resources/img/certicom.jpg");
			
			Map<String, Object> input =new  HashMap<String,Object>();
			input.put("P_TOTAL", total.toString());
			input.put("P_PERIODO",par_periodo);
		//	 input.put("P_LOGO", p_logo);
			input.put(JRParameter.REPORT_LOCALE, new Locale("es"));

			
			String path = ExportarArchivo.getPath("/resources/reports/jxrml/rptConsolidadoPlanilla.jasper");
			HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
			
			log.setAccion("PRINT");
	        log.setDescripcion("El usuario "+this.login.getLoginUsuario()+" ha impreso el archivo rptConsolidadoPlanilla.xls");
	        logmb.insertarLog(log);
			
			byte[] bytes;
				bytes = ExportarArchivo.exportXls(path, input,this.listaUsuarioCon,false);
			ExportarArchivo.executeExccel(bytes, "rptConsolidadoPlanilla.xls");
			FacesContext.getCurrentInstance().responseComplete();
			} catch (Exception e) {
				e.printStackTrace();
			}	
	}
	
	public void imprimirPlanillaDetalleXLS(){
		String par_periodo="";
		Negocio negocioParametro=  new Negocio();

		List<Oficina> listaRpt = new ArrayList<Oficina>();

		
		try {
			ServletContext servletContext = (ServletContext) (FacesContext.getCurrentInstance().getExternalContext().getContext());
		
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat formatofechahora = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
			String fechaActual = formato.format(new Date());
			
			/*if((Integer.valueOf(this._negocio))== 0){
				par_negocio="TODOS"; 
			}else{
				negocioParametro= this.negocioServices.findById((Integer.valueOf(this._negocio))); 
				par_negocio= negocioParametro.getDescripcion(); 
			}*/
			DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			par_periodo = this.utils.convertirANombre(this.cicloSelecPlanilla.getMes()) + " - " + this.cicloSelecPlanilla.getAnio() + " - " + sdf.format(this.cicloSelecPlanilla.getFecini()) + " - " + sdf.format(this.cicloSelecPlanilla.getFecfin()); 
			System.out.println("Detalle Planilla: "+par_periodo);
			Integer total = this.listaExpedientesPlanillablesDet.size();
			String p_logo = ExportarArchivo.getPath("/resources/img/certicom.jpg");
			
			Map<String, Object> input =new  HashMap<String,Object>();
			input.put("P_TOTAL", total.toString());
			input.put("P_PERIODO",par_periodo);
		//	 input.put("P_LOGO", p_logo);
			input.put(JRParameter.REPORT_LOCALE, new Locale("es"));

			
			String path = ExportarArchivo.getPath("/resources/reports/jxrml/rptDetallePlanilla.jasper");
			HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
			
			log.setAccion("PRINT");
	        log.setDescripcion("El usuario "+this.login.getLoginUsuario()+" ha impreso el archivo rptDetallePlanilla.xls");
	        logmb.insertarLog(log);
			
			byte[] bytes;
				bytes = ExportarArchivo.exportXls(path, input,this.listaExpedientesPlanillablesDet,false);
			ExportarArchivo.executeExccel(bytes, "rptDetallePlanilla.xls");
			FacesContext.getCurrentInstance().responseComplete();
			} catch (Exception e) {
				e.printStackTrace();
			}	
	}
	
	/*##################################################################################################*/
	/*####################################------setters y getters----###################################*/
	/*##################################################################################################*/
	
		/*for (Ciclo c : listaCiclosComision) {
			c.setNmes(this.utils.convertirANombre(c.getMes()));
			DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			c.setNfecini(sdf.format(c.getFecini()));
			c.setNfecfin(sdf.format(c.getFecfin()));
			
		}*/

	public List<Ciclo> getListaCiclosPlanilla() {
		for (Ciclo c : this.listaCiclosPlanilla) {
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

	public Boolean getEditar() {
		return editar;
	}	

	public void setEditar(Boolean editar) {
		this.editar = editar;
	}

	public Ciclo getCicloSelecPlanilla() {
		return cicloSelecPlanilla;
	}

	public void setCicloSelecPlanilla(Ciclo cicloSelecPlanilla) {
		this.cicloSelecPlanilla = cicloSelecPlanilla;
	}

	public Integer getCod_ciclo_pla() {
		return cod_ciclo_pla;
	}

	public void setCod_ciclo_pla(Integer cod_ciclo_pla) {
		this.cod_ciclo_pla = cod_ciclo_pla;
	}

	public String getCiclo_com() {
		return ciclo_com;
	}

	public void setCiclo_com(String ciclo_com) {
		this.ciclo_com = ciclo_com;
	}

	public String getTipoCiclo() {
		return tipoCiclo;
	}

	public void setTipoCiclo(String tipoCiclo) {
		this.tipoCiclo = tipoCiclo;
	}

	public Integer[] getChckResultadosPla() {
		return chckResultadosPla;
	}

	public void setChckResultadosPla(Integer[] chckResultadosPla) {
		this.chckResultadosPla = chckResultadosPla;
	}

	public Integer[] getChckResultadoss() {
		return chckResultadoss;
	}

	public void setChckResultadoss(Integer[] chckResultadoss) {
		this.chckResultadoss = chckResultadoss;
	}	

	public List<Expediente> getListaExpedientesPlanillables() {
		return listaExpedientesPlanillables;
	}

	public void setListaExpedientesPlanillables(
			List<Expediente> listaExpedientesPlanillables) {
		this.listaExpedientesPlanillables = listaExpedientesPlanillables;
	}

	public Expediente getExpedienteFilter() {
		return expedienteFilter;
	}

	public void setExpedienteFilter(Expediente expedienteFilter) {
		this.expedienteFilter = expedienteFilter;
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

	public Integer getCod_ciclo_fac() {
		return cod_ciclo_fac;
	}

	public void setCod_ciclo_fac(Integer cod_ciclo_fac) {
		this.cod_ciclo_fac = cod_ciclo_fac;
	}	

	public List<Expediente> getListaExpedientesPlanillasFilter() {
		return listaExpedientesPlanillasFilter;
	}

	public void setListaExpedientesPlanillasFilter(
			List<Expediente> listaExpedientesPlanillasFilter) {
		this.listaExpedientesPlanillasFilter = listaExpedientesPlanillasFilter;
	}

	public String[] getNlistaresultados() {
		return nlistaresultados;
	}

	public void setNlistaresultados(String[] nlistaresultados) {
		this.nlistaresultados = nlistaresultados;
	}

	public Integer[] getChckResultadosFac() {
		return chckResultadosFac;
	}

	public void setChckResultadosFac(Integer[] chckResultadosFac) {
		this.chckResultadosFac = chckResultadosFac;
	}

	public List<Expediente> getListaExpedientesFacturables() {
		return listaExpedientesFacturables;
	}

	public void setListaExpedientesFacturables(
			List<Expediente> listaExpedientesFacturables) {
		this.listaExpedientesFacturables = listaExpedientesFacturables;
	}

	public List<Integer> getResultfacturacion() {
		return resultfacturacion;
	}

	public void setResultfacturacion(List<Integer> resultfacturacion) {
		this.resultfacturacion = resultfacturacion;
	}

	public List<Integer> getResultplanilla() {
		return resultplanilla;
	}

	public void setResultplanilla(List<Integer> resultplanilla) {
		this.resultplanilla = resultplanilla;
	}

	public Integer getTotalExpedientesFac() {
		return totalExpedientesFac;
	}

	public void setTotalExpedientesFac(Integer totalExpedientesFac) {
		this.totalExpedientesFac = totalExpedientesFac;
	}

	public Integer getTotalAprobadosConObservacionFac() {
		return totalAprobadosConObservacionFac;
	}

	public void setTotalAprobadosConObservacionFac(
			Integer totalAprobadosConObservacionFac) {
		this.totalAprobadosConObservacionFac = totalAprobadosConObservacionFac;
	}

	public Integer getTotalObservadosFac() {
		return totalObservadosFac;
	}

	public void setTotalObservadosFac(Integer totalObservadosFac) {
		this.totalObservadosFac = totalObservadosFac;
	}

	public Integer getTotalRechazadosFac() {
		return totalRechazadosFac;
	}

	public void setTotalRechazadosFac(Integer totalRechazadosFac) {
		this.totalRechazadosFac = totalRechazadosFac;
	}

	public Integer getTotalSubsanadosFac() {
		return totalSubsanadosFac;
	}

	public void setTotalSubsanadosFac(Integer totalSubsanadosFac) {
		this.totalSubsanadosFac = totalSubsanadosFac;
	}

	public double getSumaTotalFac() {
		return sumaTotalFac;
	}

	public void setSumaTotalFac(double sumaTotalFac) {
		this.sumaTotalFac = sumaTotalFac;
	}

	public List<Expediente> getListaExpedientesFacturablesFilter() {
		return listaExpedientesFacturablesFilter;
	}

	public void setListaExpedientesFacturablesFilter(
			List<Expediente> listaExpedientesFacturablesFilter) {
		this.listaExpedientesFacturablesFilter = listaExpedientesFacturablesFilter;
	}

	public Integer getTotalExpedientesxCanalEmpresa() {
		return totalExpedientesxCanalEmpresa;
	}

	public void setTotalExpedientesxCanalEmpresa(
			Integer totalExpedientesxCanalEmpresa) {
		this.totalExpedientesxCanalEmpresa = totalExpedientesxCanalEmpresa;
	}

	public Integer getTotalExpedientesxCanalPersona() {
		return totalExpedientesxCanalPersona;
	}

	public void setTotalExpedientesxCanalPersona(
			Integer totalExpedientesxCanalPersona) {
		this.totalExpedientesxCanalPersona = totalExpedientesxCanalPersona;
	}

	public Integer getVista() {
		return vista;
	}

	public void setVista(Integer vista) {
		this.vista = vista;
	}

	public Boolean getDetallado() {
		return detallado;
	}

	public void setDetallado(Boolean detallado) {
		this.detallado = detallado;
	}

	public Boolean getConsolidado() {
		return consolidado;
	}

	public void setConsolidado(Boolean consolidado) {
		this.consolidado = consolidado;
	}
	public List<Usuario> getListaEjecutivos() {
		return listaEjecutivos;
	}

	public void setListaEjecutivos(List<Usuario> listaEjecutivos) {
		this.listaEjecutivos = listaEjecutivos;
	}

	public Usuario getUsuarioLogin() {
		return usuarioLogin;
	}

	public void setUsuarioLogin(Usuario usuarioLogin) {
		this.usuarioLogin = usuarioLogin;
	}

	public List<ExpedienteTracking> getListaExpedientesAGuardar() {
		return listaExpedientesAGuardar;
	}

	public void setListaExpedientesAGuardar(
			List<ExpedienteTracking> listaExpedientesAGuardar) {
		this.listaExpedientesAGuardar = listaExpedientesAGuardar;
	}

	public List<FacturacionPlanillaDetalle> getListafpDetalleAGuardar() {
		return listafpDetalleAGuardar;
	}

	public void setListafpDetalleAGuardar(
			List<FacturacionPlanillaDetalle> listafpDetalleAGuardar) {
		this.listafpDetalleAGuardar = listafpDetalleAGuardar;
	}

	public Ciclo getCicloSelecFacturacion() {
		return cicloSelecFacturacion;
	}

	public void setCicloSelecFacturacion(Ciclo cicloSelecFacturacion) {
		this.cicloSelecFacturacion = cicloSelecFacturacion;
	}

	public Boolean getBtnCerrarPlanilla() {
		return btnCerrarPlanilla;
	}

	public void setBtnCerrarPlanilla(Boolean btnCerrarPlanilla) {
		this.btnCerrarPlanilla = btnCerrarPlanilla;
	}

	public Boolean getBtnCerrarFacturacion() {
		return btnCerrarFacturacion;
	}

	public void setBtnCerrarFacturacion(Boolean btnCerrarFacturacion) {
		this.btnCerrarFacturacion = btnCerrarFacturacion;
	}

	public String getNombre_excel() {
		return nombre_excel;
	}

	public void setNombre_excel(String nombre_excel) {
		this.nombre_excel = nombre_excel;
	}

	public List<Expediente> getListaExpedienteDetalle() {
		return listaExpedienteDetalle;
	}

	public void setListaExpedienteDetalle(List<Expediente> listaExpedienteDetalle) {
		this.listaExpedienteDetalle = listaExpedienteDetalle;
	}

	public String getNombre_excel_exp() {
		return nombre_excel_exp;
	}

	public void setNombre_excel_exp(String nombre_excel_exp) {
		this.nombre_excel_exp = nombre_excel_exp;
	}

	public String getTabPla() {
		return tabPla;
	}

	public void setTabPla(String tabPla) {
		this.tabPla = tabPla;
	}

	public String getTabFac() {
		return tabFac;
	}

	public void setTabFac(String tabFac) {
		this.tabFac = tabFac;
	}

	public List<Oficina> getListOficina() {
		return listOficina;
	}

	public void setListOficina(List<Oficina> listOficina) {
		this.listOficina = listOficina;
	}

	public List<Tarifa> getListaRangoFacturacion() {
		return listaRangoFacturacion;
	}

	public void setListaRangoFacturacion(List<Tarifa> listaRangoFacturacion) {
		this.listaRangoFacturacion = listaRangoFacturacion;
	}

	public List<Tarifa> getListaTarifaTemporal() {
		return listaTarifaTemporal;
	}

	public void setListaTarifaTemporal(List<Tarifa> listaTarifaTemporal) {
		this.listaTarifaTemporal = listaTarifaTemporal;
	}

	public List<Tarifa> getListaTableroConteo() {
		return listaTableroConteo;
	}

	public void setListaTableroConteo(List<Tarifa> listaTableroConteo) {
		this.listaTableroConteo = listaTableroConteo;
	}

	public List<Tarifa> getListaCicloAFacturar() {
		return listaCicloAFacturar;
	}

	public void setListaCicloAFacturar(List<Tarifa> listaCicloAFacturar) {
		this.listaCicloAFacturar = listaCicloAFacturar;
	}

	public List<Tarifa> getListaCicloAFacturarEspecial() {
		return listaCicloAFacturarEspecial;
	}

	public void setListaCicloAFacturarEspecial(
			List<Tarifa> listaCicloAFacturarEspecial) {
		this.listaCicloAFacturarEspecial = listaCicloAFacturarEspecial;
	}

	public TipoCiclo getTipoCicloSelec() {
		return tipoCicloSelec;
	}

	public void setTipoCicloSelec(TipoCiclo tipoCicloSelec) {
		this.tipoCicloSelec = tipoCicloSelec;
	}

	public Integer getTotalPaginasfac() {
		return totalPaginasfac;
	}

	public void setTotalPaginasfac(Integer totalPaginasfac) {
		this.totalPaginasfac = totalPaginasfac;
	}

	public MultiKeyMap getMapOficinas() {
		return mapOficinas;
	}

	public void setMapOficinas(MultiKeyMap mapOficinas) {
		this.mapOficinas = mapOficinas;
	}

	public List<Oficina> getListOficinaCon() {
		return listOficinaCon;
	}

	public void setListOficinaCon(List<Oficina> listOficinaCon) {
		this.listOficinaCon = listOficinaCon;
	}

	public List<Oficina> getListOficinaDet() {
		return listOficinaDet;
	}

	public void setListOficinaDet(List<Oficina> listOficinaDet) {
		this.listOficinaDet = listOficinaDet;
	}

	public List<Tarifa> getListaRangoPlanilla() {
		return listaRangoPlanilla;
	}

	public void setListaRangoPlanilla(List<Tarifa> listaRangoPlanilla) {
		this.listaRangoPlanilla = listaRangoPlanilla;
	}

	public Integer getTotalExpedientesPla() {
		return totalExpedientesPla;
	}

	public void setTotalExpedientesPla(Integer totalExpedientesPla) {
		this.totalExpedientesPla = totalExpedientesPla;
	}

	public Boolean getMostrarDetalleFac() {
		return mostrarDetalleFac;
	}

	public void setMostrarDetalleFac(Boolean mostrarDetalleFac) {
		this.mostrarDetalleFac = mostrarDetalleFac;
	}

	public Boolean getMostrarConsolidadoFac() {
		return mostrarConsolidadoFac;
	}

	public void setMostrarConsolidadoFac(Boolean mostrarConsolidadoFac) {
		this.mostrarConsolidadoFac = mostrarConsolidadoFac;
	}

	public Boolean getMostrarDetallePla() {
		return mostrarDetallePla;
	}

	public void setMostrarDetallePla(Boolean mostrarDetallePla) {
		this.mostrarDetallePla = mostrarDetallePla;
	}

	public Boolean getMostrarConsolidadoPla() {
		return mostrarConsolidadoPla;
	}

	public void setMostrarConsolidadoPla(Boolean mostrarConsolidadoPla) {
		this.mostrarConsolidadoPla = mostrarConsolidadoPla;
	}

	public String getVradiofac() {
		return vradiofac;
	}

	public void setVradiofac(String vradiofac) {
		this.vradiofac = vradiofac;
	}

	public String getVradiopla() {
		return vradiopla;
	}

	public void setVradiopla(String vradiopla) {
		this.vradiopla = vradiopla;
	}

	public Integer getTotalPaginaspla() {
		return totalPaginaspla;
	}

	public void setTotalPaginaspla(Integer totalPaginaspla) {
		this.totalPaginaspla = totalPaginaspla;
	}

	public List<Usuario> getListaUsuarioCon() {
		return listaUsuarioCon;
	}

	public void setListaUsuarioCon(List<Usuario> listaUsuarioCon) {
		this.listaUsuarioCon = listaUsuarioCon;
	}

	public List<Expediente> getListaExpedientesPlanillablesDet() {
		return listaExpedientesPlanillablesDet;
	}

	public void setListaExpedientesPlanillablesDet(
			List<Expediente> listaExpedientesPlanillablesDet) {
		this.listaExpedientesPlanillablesDet = listaExpedientesPlanillablesDet;
	}

	public Boolean getBtnEliminarPlanilla() {
		return btnEliminarPlanilla;
	}

	public void setBtnEliminarPlanilla(Boolean btnEliminarPlanilla) {
		this.btnEliminarPlanilla = btnEliminarPlanilla;
	}

	public Boolean getBtnEliminarFacturacion() {
		return btnEliminarFacturacion;
	}

	public void setBtnEliminarFacturacion(Boolean btnEliminarFacturacion) {
		this.btnEliminarFacturacion = btnEliminarFacturacion;
	}

	public LoginMB getLogin() {
		return login;
	}

	public void setLogin(LoginMB login) {
		this.login = login;
	}

	public boolean isBtnImprimirFacDet() {
		return btnImprimirFacDet;
	}

	public void setBtnImprimirFacDet(boolean btnImprimirFacDet) {
		this.btnImprimirFacDet = btnImprimirFacDet;
	}

	public boolean isBtnImprimirFacCon() {
		return btnImprimirFacCon;
	}

	public void setBtnImprimirFacCon(boolean btnImprimirFacCon) {
		this.btnImprimirFacCon = btnImprimirFacCon;
	}

	public boolean isBtnImprimirPlaDet() {
		return btnImprimirPlaDet;
	}

	public void setBtnImprimirPlaDet(boolean btnImprimirPlaDet) {
		this.btnImprimirPlaDet = btnImprimirPlaDet;
	}

	public boolean isBtnImprimirPlaCon() {
		return btnImprimirPlaCon;
	}

	public void setBtnImprimirPlaCon(boolean btnImprimirPlaCon) {
		this.btnImprimirPlaCon = btnImprimirPlaCon;
	}

	public List<ExpedienteDocumento> getListaDocumentosRegistrados() {
		return listaDocumentosRegistrados;
	}

	public void setListaDocumentosRegistrados(
			List<ExpedienteDocumento> listaDocumentosRegistrados) {
		this.listaDocumentosRegistrados = listaDocumentosRegistrados;
	}

	public ExpedienteDocumento getExpDocSelected() {
		return expDocSelected;
	}

	public void setExpDocSelected(ExpedienteDocumento expDocSelected) {
		this.expDocSelected = expDocSelected;
	}

	public boolean isRenderedFrame() {
		return renderedFrame;
	}

	public void setRenderedFrame(boolean renderedFrame) {
		this.renderedFrame = renderedFrame;
	}	

	public boolean isbDetExpPlaExpedientes() {
		return bDetExpPlaExpedientes;
	}

	public void setbDetExpPlaExpedientes(boolean bDetExpPlaExpedientes) {
		this.bDetExpPlaExpedientes = bDetExpPlaExpedientes;
	}

	public boolean isbDetExpPlaSolicitudes() {
		return bDetExpPlaSolicitudes;
	}

	public void setbDetExpPlaSolicitudes(boolean bDetExpPlaSolicitudes) {
		this.bDetExpPlaSolicitudes = bDetExpPlaSolicitudes;
	}

	public boolean isbDetExpFacExpedientes() {
		return bDetExpFacExpedientes;
	}

	public void setbDetExpFacExpedientes(boolean bDetExpFacExpedientes) {
		this.bDetExpFacExpedientes = bDetExpFacExpedientes;
	}

	public boolean isbDetExpFacSolicitudes() {
		return bDetExpFacSolicitudes;
	}

	public void setbDetExpFacSolicitudes(boolean bDetExpFacSolicitudes) {
		this.bDetExpFacSolicitudes = bDetExpFacSolicitudes;
	}

	public Usuario getUsuG() {
		return usuG;
	}

	public void setUsuG(Usuario usuG) {
		this.usuG = usuG;
	}

	public Oficina getOfiG() {
		return ofiG;
	}

	public void setOfiG(Oficina ofiG) {
		this.ofiG = ofiG;
	}

	public List<Expediente> getListaExpedientesFacturablesDet() {
		return listaExpedientesFacturablesDet;
	}

	public void setListaExpedientesFacturablesDet(
			List<Expediente> listaExpedientesFacturablesDet) {
		this.listaExpedientesFacturablesDet = listaExpedientesFacturablesDet;
	}

	
}
