package com.certicom.certiscan.managedBeans;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.lang.reflect.Field;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRParameter;

import org.apache.commons.collections.map.MultiKeyMap;
import org.primefaces.context.RequestContext;
import org.primefaces.event.TabChangeEvent;

import com.certicom.certiscan.commons.Constante;
import com.certicom.certiscan.commons.ExportarArchivo;
import com.certicom.certiscan.commons.FacesUtils;
import com.certicom.certiscan.commons.GenericBeans;
import com.certicom.certiscan.commons.Utils;
import com.certicom.certiscan.domain.Bono;
import com.certicom.certiscan.domain.Ciclo;
import com.certicom.certiscan.domain.Expediente;
import com.certicom.certiscan.domain.FacturacionPlanillaDetalle;
import com.certicom.certiscan.domain.Log;
import com.certicom.certiscan.domain.Menu;
import com.certicom.certiscan.domain.Tarifa;
import com.certicom.certiscan.domain.TipoCiclo;
import com.certicom.certiscan.services.BonoService;
import com.certicom.certiscan.services.CicloService;
import com.certicom.certiscan.services.ExpedienteService;
import com.certicom.certiscan.services.FacturacionPlanillaDetalleServices;
import com.certicom.certiscan.services.MenuServices;
import com.certicom.certiscan.services.TarifaService;
import com.certicom.certiscan.services.TipoCicloService;

@ManagedBean(name="consolidadoPlanillaMB")
@ViewScoped
public class ConsolidadoPlanillaMB extends GenericBeans implements Serializable{

	private List<Ciclo> listaCiclosPlanilla;
	private List<FacturacionPlanillaDetalle> listaConsolidadosComision;
	private List<FacturacionPlanillaDetalle> listaConsolidadosPlanillaPre;
	private List<Expediente> listaConsolidadosPlanillaEmpresa;
	private List<Expediente> listaConsolidadosPlanillaPersona;
	private List<Expediente> listaConsolidadosPlanillaEmpresaReniec;
	private List<Expediente> listaConsolidadosPlanillaPersonaReniec;
	private List<Tarifa> listaTablero;
	private List<Bono> listaBonos;
	private FacturacionPlanillaDetalleServices fpDetalleService;
	private Tarifa tabcomSelec;
	private Boolean editar;
	
	private CicloService cicloService;
	private MenuServices menuServices;
	private TarifaService tabComService;
	private ExpedienteService expService;
	private BonoService bonoServices;
	private Utils utils;
	private Integer cod_ciclo_fac;
	//datos Log
    private Log log;
	private LogMB logmb;
	
	public ConsolidadoPlanillaMB(){}
	
	@PostConstruct
	public void inicia(){
		
		this.editar = Boolean.FALSE;
		
		this.cicloService = new CicloService();
		this.menuServices=new MenuServices();
		this.utils = new Utils();
		this.tabcomSelec = new Tarifa();
		this.listaConsolidadosComision = new ArrayList<FacturacionPlanillaDetalle>();
		this.listaConsolidadosPlanillaPre = new ArrayList<FacturacionPlanillaDetalle>();
		this.fpDetalleService = new FacturacionPlanillaDetalleServices();
		this.tabComService = new TarifaService();
		this.expService = new ExpedienteService();
		this.listaConsolidadosPlanillaEmpresa = new ArrayList<Expediente>();
		this.listaConsolidadosPlanillaPersona = new ArrayList<Expediente>();
		this.listaConsolidadosPlanillaEmpresaReniec = new ArrayList<Expediente>();
		this.listaConsolidadosPlanillaPersonaReniec = new ArrayList<Expediente>();
		this.bonoServices = new BonoService();
		
		log = (Log)getSpringBean(Constante.SESSION_LOG);
		logmb = new LogMB();
		
		try {
			this.listaCiclosPlanilla = this.cicloService.findByTipoCicloActivo(2);
			Menu codMenu = menuServices.opcionMenuByPretty("pretty:consolidadoPlanilla");
			log.setCod_menu(codMenu.getCod_menu().intValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void listarConsolidadoPlanilla(){
		try {
			RequestContext context = RequestContext.getCurrentInstance(); 
		if(this.cod_ciclo_fac > 0){
			Ciclo cic = this.cicloService.findById(this.cod_ciclo_fac);
			//listarConsolidadPlanillaPreliminar();
			HashMap<Integer, Tarifa> tb_comision_ope = new HashMap<Integer, Tarifa>();
			MultiKeyMap tb_comision_reniec = new MultiKeyMap();
				double totalFacturar=0.0, totalFacturarOtros=0.0;
				int totPag=0;
					this.listaConsolidadosComision = this.fpDetalleService.findConsolidadoPlanillaByIdCiclo(this.cod_ciclo_fac);
					System.out.println("cantidad de registros en el consolidado de planilla: "+this.listaConsolidadosComision.size());
					this.listaTablero= this.tabComService.findByIdCiclo(this.cod_ciclo_fac);
					
					this.listaBonos = this.bonoServices.listarxIdCiclo(this.cod_ciclo_fac);
					
					for (Tarifa tb : this.listaTablero) {
						//tb_comision_ope.put(tb.getCod_subtipo(),tb.getCod_canal(), tb);
						tb_comision_ope.put(tb.getId_tarifa(), tb);
					}
					
					double totmontocomision = 0.0, totmontoComisionOperativo = 0.0, totmontoComisionXRegularizar = 0.0, totmontoPenalidadXMalaRevision = 0.0,
							totmontoPenalidadXFueraPlazo = 0.0, totmontoPenalidadXCarpetaPerdida = 0.0, totmontoDeteccionError = 0.0, totmontoPenalidadXMalaDeteccionFraude = 0.0,
							totmontoDescuento = 0.0, totmontoBonoXCapacitacion = 0.0, totmontoBonoPosibleFraude = 0.0, totmontoBono1 = 0.0, totmontoBono2 = 0.0, totmontoBono3 = 0.0, 
							totmontoTotalComisiones = 0.0;				
					
				for (FacturacionPlanillaDetalle fpDetalle : this.listaConsolidadosComision) {
					//Tarifa tabcom = (Tarifa) tb_comision_ope.get(fpDetalle.getCodConcepto());
					Tarifa tabcom=null;
					Tarifa tabcomreniec=null;
					
					fpDetalle=setMontosBonos(fpDetalle);
					totmontocomision = totmontocomision + fpDetalle.getMontoComision();
					totmontoComisionOperativo = totmontoComisionOperativo + fpDetalle.getMontoComisionOperativo();
					totmontoComisionXRegularizar = totmontoComisionXRegularizar + fpDetalle.getMontoComisionXRegularizar();
					totmontoPenalidadXMalaRevision = totmontoPenalidadXMalaRevision + fpDetalle.getMontoPenalidadXMalaRevision();
					totmontoPenalidadXFueraPlazo = totmontoPenalidadXFueraPlazo + fpDetalle.getMontoPenalidadXFueraPlazo();
					totmontoPenalidadXCarpetaPerdida = totmontoPenalidadXCarpetaPerdida + fpDetalle.getMontoPenalidadXCarpetaPerdida();
					totmontoDeteccionError = totmontoDeteccionError + fpDetalle.getMontoDeteccionError();
					totmontoPenalidadXMalaDeteccionFraude = totmontoPenalidadXMalaDeteccionFraude + fpDetalle.getMontoPenalidadXMalaDeteccionFraude();
					totmontoDescuento = totmontoDescuento + fpDetalle.getMontoDescuento();
					totmontoBonoXCapacitacion = totmontoBonoXCapacitacion + fpDetalle.getMontoBonoXCapacitacion();
					totmontoBonoPosibleFraude = totmontoBonoPosibleFraude + fpDetalle.getMontoBonoPosibleFraude();
					totmontoBono1 = totmontoBono1 + fpDetalle.getMontoBono1();
					totmontoBono2 = totmontoBono2 + fpDetalle.getMontoBono2();
					totmontoBono3 = totmontoBono3 + fpDetalle.getMontoBono3();
					totmontoTotalComisiones = totmontoTotalComisiones + fpDetalle.getMontoTotalComisiones();
					
					fpDetalle.setTotalFacturar(fpDetalle.getCosto_planilla_facturacion()*fpDetalle.getCantidadPag());
					totalFacturar +=fpDetalle.getTotalFacturar();
					totPag = totPag + fpDetalle.getCantidadPag();
					
				}
				
				Collections.sort(this.listaConsolidadosComision, new Comparator<FacturacionPlanillaDetalle>() {
					@Override
					public int compare(FacturacionPlanillaDetalle o1, FacturacionPlanillaDetalle o2) {
						// TODO Auto-generated method stub
						return new Integer(o1.getId_tarifa()).compareTo(new Integer(o2.getId_tarifa()));
					}
				});
				
				FacturacionPlanillaDetalle fpDetalle = new FacturacionPlanillaDetalle();
				fpDetalle.setUsuarioAsignado("Total: ");
				fpDetalle.setCantidad(totPag);
				fpDetalle.setCodConcepto(5);
				fpDetalle.setTotalFacturar(totalFacturar);
				fpDetalle.setMontoComision(totmontocomision);
				fpDetalle.setMontoComisionOperativo(totmontoComisionOperativo);
				fpDetalle.setMontoComisionXRegularizar(totmontoComisionXRegularizar);
				fpDetalle.setMontoPenalidadXMalaRevision(totmontoPenalidadXMalaRevision);
				fpDetalle.setMontoPenalidadXFueraPlazo(totmontoPenalidadXFueraPlazo);
				fpDetalle.setMontoPenalidadXCarpetaPerdida(totmontoPenalidadXCarpetaPerdida);
				fpDetalle.setMontoDeteccionError(totmontoDeteccionError);
				fpDetalle.setMontoPenalidadXMalaDeteccionFraude(totmontoPenalidadXMalaDeteccionFraude);
				fpDetalle.setMontoDescuento(totmontoDescuento);
				fpDetalle.setMontoBonoXCapacitacion(totmontoBonoXCapacitacion);
				fpDetalle.setMontoBonoPosibleFraude(totmontoBonoPosibleFraude);
				fpDetalle.setMontoBono1(totmontoBono1);
				fpDetalle.setMontoBono2(totmontoBono2);
				fpDetalle.setMontoBono3(totmontoBono3);
				fpDetalle.setMontoTotalComisiones(totmontoTotalComisiones);
				this.listaConsolidadosComision.add(fpDetalle);
				
				
				
				/*this.listaConsolidadosPlanillaEmpresa = this.expService.obtenerExpedienteXIdCicloIdCanal(this.cod_ciclo_fac, Constante.CANAL_EMPRESA);
				this.listaConsolidadosPlanillaPersona = this.expService.obtenerExpedienteXIdCicloIdCanal(this.cod_ciclo_fac, Constante.CANAL_PERSONA);
				this.listaConsolidadosPlanillaPersonaReniec = this.expService.obtenerExpedienteXReniecIdCanal(cic.getFecini(),cic.getFecfin(),Constante.CANAL_PERSONA);
				this.listaConsolidadosPlanillaEmpresaReniec = this.expService.obtenerExpedienteXReniecIdCanal(cic.getFecini(),cic.getFecfin(),Constante.CANAL_EMPRESA);*/
		}else{
			
			FacesUtils.showFacesMessage("Seleccione un ciclo, por favor", 3);
			context.update("msgGeneral");
			
		}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
public FacturacionPlanillaDetalle setMontosBonos(FacturacionPlanillaDetalle fpDet){
		

		for (Bono b : this.listaBonos) {
			
			if(fpDet.getIdusuario().equals(b.getIdusuario())){
			
				switch (b.getIdtipobono()){
				
				case Constante.BONO_COMISIONES_POR_REGULARIZAR: 
					fpDet.setMontoComisionXRegularizar(b.getMonto());
					break;
				case Constante.BONO_DESCUENTO:
					fpDet.setMontoDescuento((-1)*b.getMonto());
					break;
				case Constante.BONO_BONO_POR_CUMPLIMIENTO:
					fpDet.setMontoBonoXCumplimiento(b.getMonto());
					break;
				case Constante.BONO_DETECCION_ERROR:
					fpDet.setMontoDeteccionError(b.getMonto());
					break;
				case Constante.BONO_PENALIDAD_POR_CARPETA_PERDIDA:
					fpDet.setMontoPenalidadXCarpetaPerdida((-1)*b.getMonto());
					break;
				case Constante.BONO_PENALIDAD_POR_FUERA_DE_PLAZO:
					fpDet.setMontoPenalidadXFueraPlazo((-1)*b.getMonto());
					break;
				case Constante.BONO_PENALIDAD_POR_MALA_REVISION:
					fpDet.setMontoPenalidadXMalaRevision((-1)*b.getMonto());
					break;
				case Constante.BONO_PENALIDAD_POR_MALA_DETECCION_FRAUDE:
					fpDet.setMontoPenalidadXMalaDeteccionFraude((-1)*b.getMonto());
					break;
				case Constante.BONO_POR_CAPACITACION:
					fpDet.setMontoBonoXCapacitacion(b.getMonto());
					break;
				/*case Constante.BONO_SIMPLE:
					fpDet.setMontoBonoSimple(b.getMonto());
					break;*/
				case Constante.BONO_POSIBLE_FRAUDE:
					fpDet.setMontoBonoPosibleFraude(b.getMonto());
					break;
				case Constante.BONO_1:
					fpDet.setMontoBono1(b.getMonto());
					break;
				case Constante.BONO_2:
					fpDet.setMontoBono2(b.getMonto());
					break;
				case Constante.BONO_3:
					fpDet.setMontoBono3(b.getMonto());
					break;
				}
			}
		}
		
		fpDet.setMontoTotalComisiones(fpDet.getMontoComision()+fpDet.getMontoComisionOperativo()+fpDet.getMontoComisionXRegularizar()
				+fpDet.getMontoDescuento()+fpDet.getMontoPenalidadXMalaRevision()+fpDet.getMontoPenalidadXFueraPlazo()+fpDet.getMontoPenalidadXCarpetaPerdida()
				+fpDet.getMontoDeteccionError()+fpDet.getMontoPenalidadXMalaDeteccionFraude()+fpDet.getMontoBonoXCapacitacion()
				/*+fpDet.getMontoBonoSimple()*/+fpDet.getMontoBonoPosibleFraude()+fpDet.getMontoBono1()+fpDet.getMontoBono2()+fpDet.getMontoBono3());
		
		return fpDet;
	}

	private void imprimirFactura(){
		
		try {
			ServletContext servletContext = (ServletContext) (FacesContext.getCurrentInstance().getExternalContext().getContext());
			
			
			/*SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");		
			String fecha = formato.format(this.encomiendaReporte.getfRegistro());
			
			String mifecha[] =  fecha.split("/");
			dia = mifecha[0];
			mes = mifecha[1];
			anio = mifecha[2];
			
			
			System.out.println("Fecha:"+this.encomiendaReporte.getfRegistro());
			System.out.println("Dia:"+this.encomiendaReporte.getfRegistro().getDay());
			System.out.println("Mes:"+this.encomiendaReporte.getfRegistro().getMonth());
			System.out.println("Anio:"+this.encomiendaReporte.getfRegistro().getYear());*/

			Map<String, Object> input =new  HashMap<String,Object>();
			/*input.put("P_REMITENTE", this.encomiendaReporte.getNombresRemitente()+" " +this.encomiendaReporte.getApellidosRemitente());
			input.put("P_DNIREMITE", this.encomiendaReporte.getDniRemitente());
			
			System.out.println("Razol Social:"+this.encomiendaReporte.getRazonSocialDestinatario1());
			System.out.println("Direccion:"+this.encomiendaReporte.getDireccionRemitente());
			System.out.println("Telefono:"+this.encomiendaReporte.getTelefonoRemitente());
			//encomiendaReporte.
			//input.put("P_", this.encomiendaReporte.getDniRemitente());
			//System.out.println();
			
			
			input.put("P_RAZON_SOCIAL", this.encomiendaReporte.getRazonSocialRemitente());
			input.put("P_DIRECCION_REMITENTE", this.encomiendaReporte.getDireccionRemitente());//PONER LA DIRECCION DEL REMITENTE
			input.put("P_TELEFONO_REMITENTE", this.encomiendaReporte.getTelefonoRemitente());
			input.put("P_ORIGEN", this.encomiendaReporte.getDesOrigen());
			
			
			
	        //System.out.println("dia:"+fecha.);
			
			input.put("P_FECHA", fecha);
			input.put("P_DIA", dia);
			input.put("P_MES", mes);
			input.put("P_ANIO", anio);
			
			input.put("P_DESTINATARIO", this.encomiendaReporte.getNombresDestinatario1()+" " + this.encomiendaReporte.getApellidosDestinatario1());
			System.out.println("Direccion Envio:"+this.encomiendaReporte.getDireccionEnvio());
			
			//input.put("P_DIRECNVIO", this.encomiendaReporte.getDireccionEnvio());
			input.put("P_DIRECNVIO", this.encomiendaReporte.getDireccionDestinatario1());
			input.put("P_TELEFONO", this.encomiendaReporte.getTelefonoDestinatario1());
			
			input.put("P_SUBTOTAL", this.subTotalRpt);
			input.put("P_IGV", this.IGVRpt);
			input.put("P_TOTAL_COBRADO", this.TotalRpt);
			
			input.put("P_TOTAL_LETRAS", "SON "+ numeroALetra.Convertir(this.TotalRpt.toString(), true, "SOLES"));
			input.put("P_DESTINO", this.encomiendaReporte.getDesDestino());
		//	input.put("P_COMPROBANTE", this.encomiendaReporte.getComprobante());
			input.put(JRParameter.REPORT_LOCALE, new Locale("es"));*/
			//input.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.TRUE); // no parte la pagina todo lo mete en un A4
			
			String path = ExportarArchivo.getPath("/resources/reports/jxrml/rptPlanillaPreliminar.jasper");
			HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
			
			
			byte[] bytes;
				bytes = ExportarArchivo.exportPdf(path, input, this.listaConsolidadosPlanillaPre);
			
				// SEGUNO REPORTE
				SimpleDateFormat formato2 = new SimpleDateFormat("dd/MM/yyyy");		
				String fecha2 = formato2.format(new Date());
				
				/*SimpleDateFormat formato2 = new SimpleDateFormat("dd/MM/yyyy");		
				String fecha2 = formato2.format(new Date());*/
				
				/*System.out.println("Fecha2:"+fecha2);
				String mifecha[] =  fecha2.split("/");
				System.out.println("Dia:"+mifecha[0]);
				System.out.println("Mes:"+mifecha[1]);
				System.out.println("Anio:"+mifecha[2]);*/
				
				//Date fechaactual = new Date();
				
				System.out.println();
				
				//String dia = 
				
				Map<String, Object> input2 =new  HashMap<String,Object>();
				/*input2.put("P_RAZON_SOCIAL_REMITENTE", this.encomiendaReporte.getRazonSocialRemitente());//PONER LA DIRECCION DEL REMITENTE
				input2.put("P_DNI_REMITENTE", this.encomiendaReporte.getDniRemitente());//PONER LA DIRECCION DEL REMITENTE
				input2.put("P_RAZON_SOCIAL_DESTINO", this.encomiendaReporte.getRazonSocialDestinatario1());//PONER LA DIRECCION DEL REMITENTE
				input2.put("P_DNI_DESTINO", this.encomiendaReporte.getDniDestinatario1());
				
				//input2.put("P_DNI_DESTINO", this.encomiendaReporte.getDniDestinatario1());//PONER LA DIRECCION DEL REMITENTE
				//encomiendaReporte.getN
				
				input2.put("P_OFI_ORIGEN", this.encomiendaReporte.getDesOrigen());
				input2.put("P_OFI_DESTINO", this.encomiendaReporte.getDesDestino());
				input2.put("P_DEP_ORIGEN", this.encomiendaReporte.getDesOrigen());
				input2.put("P_DEP_DESTINO", this.encomiendaReporte.getDesDestino());
				System.out.println("Guia Remitente:"+this.encomiendaReporte.getGuiaRemitente());
				//encomiendaReporte.getGui
				input2.put("P_GUIA_REMITENTE", this.encomiendaReporte.getGuiaRemitente());
				
				input2.put("P_RZ_EMPRESA_RE", "ITTSA");
				input2.put("P_RUC_EMPRESA_RE", "2013227213");
				input2.put("P_RZ_EMPRESA_DES", "ITTSA");
				input2.put("P_RUC_EMPRESA_DES", "2013227213");
			    System.out.println("Placa::"+this.busSeleccionado.getPlacaBus());
				
				input2.put("P_PLACA", this.busSeleccionado.getPlacaBus());
				//input2.put("P_PLACA", "");
				input2.put("P_TARJ_CIR", "000");
				input2.put("P_LICE_CON", this.busSeleccionado.getLicPiloto()+"/"+this.busSeleccionado.getLicCopiloto());
				//input2.put("P_FECHA", fecha2);
				input2.put("P_DIA", dia);
				input2.put("P_MES", mes);
				input2.put("P_ANIO", anio);
				
				input2.put("P_TDOC", this.encomiendaReporte.getTipoDocumento());
				input2.put("P_COMPROBANTE", this.encomiendaReporte.getComprobante());
				input2.put(JRParameter.REPORT_LOCALE, new Locale("es"));*/
				//input.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.TRUE); // no parte la pagina todo lo mete en un A4
				
				String path2 = ExportarArchivo.getPath("/resources/reports/jxrml/rptPlanillaAdenda.jasper");
				HttpServletResponse response2 = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
				
				
				byte[] bytes2;
					/*for(int i=0; i<listaRptDetalle.size(); i++){
						System.out.println("Peso:"+listaRptDetalle.get(i).getPeso());
					}*/
				
					bytes2 = ExportarArchivo.exportPdf(path2, input2, this.listaConsolidadosComision);	
				
				//byte[] byteFinal = ExportarArchivo.appendFiles(bytes, bytes2);
				
			//ExportarArchivo.executePdf(byteFinal, "FacturaGuiaRemisionEnc.pdf");
			FacesContext.getCurrentInstance().responseComplete();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	/*##################################################################################################*/
	/*####################################------setters y getters----###################################*/
	/*##################################################################################################*/

	public Tarifa getTabcomSelec() {
		return tabcomSelec;
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

	public void setTabcomSelec(Tarifa tabcomSelec) {
		this.tabcomSelec = tabcomSelec;
	}

	public Boolean getEditar() {
		return editar;
	}

	public void setEditar(Boolean editar) {
		this.editar = editar;
	}

	public Integer getCod_ciclo_fac() {
		return cod_ciclo_fac;
	}

	public void setCod_ciclo_fac(Integer cod_ciclo_fac) {
		this.cod_ciclo_fac = cod_ciclo_fac;
	}	

	public List<FacturacionPlanillaDetalle> getListaConsolidadosComision() {
		return listaConsolidadosComision;
	}

	public void setListaConsolidadosComision(
			List<FacturacionPlanillaDetalle> listaConsolidadosComision) {
		this.listaConsolidadosComision = listaConsolidadosComision;
	}

	public List<Bono> getListaBonos() {
		return listaBonos;
	}

	public void setListaBonos(List<Bono> listaBonos) {
		this.listaBonos = listaBonos;
	}

	public List<Tarifa> getListaTablero() {
		return listaTablero;
	}

	public void setListaTablero(List<Tarifa> listaTablero) {
		this.listaTablero = listaTablero;
	}

	public List<Expediente> getListaConsolidadosPlanillaEmpresa() {
		return listaConsolidadosPlanillaEmpresa;
	}

	public void setListaConsolidadosPlanillaEmpresa(
			List<Expediente> listaConsolidadosPlanillaEmpresa) {
		this.listaConsolidadosPlanillaEmpresa = listaConsolidadosPlanillaEmpresa;
	}

	public List<Expediente> getListaConsolidadosPlanillaPersona() {
		return listaConsolidadosPlanillaPersona;
	}

	public void setListaConsolidadosPlanillaPersona(
			List<Expediente> listaConsolidadosPlanillaPersona) {
		this.listaConsolidadosPlanillaPersona = listaConsolidadosPlanillaPersona;
	}

	public List<FacturacionPlanillaDetalle> getListaConsolidadosPlanillaPre() {
		return listaConsolidadosPlanillaPre;
	}

	public void setListaConsolidadosPlanillaPre(
			List<FacturacionPlanillaDetalle> listaConsolidadosPlanillaPre) {
		this.listaConsolidadosPlanillaPre = listaConsolidadosPlanillaPre;
	}

	public List<Expediente> getListaConsolidadosPlanillaEmpresaReniec() {
		return listaConsolidadosPlanillaEmpresaReniec;
	}

	public void setListaConsolidadosPlanillaEmpresaReniec(
			List<Expediente> listaConsolidadosPlanillaEmpresaReniec) {
		this.listaConsolidadosPlanillaEmpresaReniec = listaConsolidadosPlanillaEmpresaReniec;
	}

	public List<Expediente> getListaConsolidadosPlanillaPersonaReniec() {
		return listaConsolidadosPlanillaPersonaReniec;
	}

	public void setListaConsolidadosPlanillaPersonaReniec(
			List<Expediente> listaConsolidadosPlanillaPersonaReniec) {
		this.listaConsolidadosPlanillaPersonaReniec = listaConsolidadosPlanillaPersonaReniec;
	}
	
}
