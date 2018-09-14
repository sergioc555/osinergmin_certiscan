package com.certicom.certiscan.managedBeans;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipOutputStream;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.primefaces.context.RequestContext;

import com.certicom.certiscan.commons.Constante;
import com.certicom.certiscan.commons.GenericBeans;
import com.certicom.certiscan.commons.Utiles;
import com.certicom.certiscan.domain.Estados;
import com.certicom.certiscan.domain.Expediente;
import com.certicom.certiscan.domain.ExpedienteDocumento;
import com.certicom.certiscan.domain.ExpedienteIncidencia;
import com.certicom.certiscan.domain.ExpedienteTracking;
import com.certicom.certiscan.domain.IndicadorCall;
import com.certicom.certiscan.domain.Lote;
import com.certicom.certiscan.domain.Medios;
import com.certicom.certiscan.domain.Perfil;
import com.certicom.certiscan.domain.Usuario;
import com.certicom.certiscan.services.EstadosServices;
import com.certicom.certiscan.services.ExpedienteDocumentoService;
import com.certicom.certiscan.services.ExpedienteIncidenciaServices;
import com.certicom.certiscan.services.ExpedienteService;
import com.certicom.certiscan.services.ExpedienteTrackingService;
import com.certicom.certiscan.services.IndicadorCallServices;
import com.certicom.certiscan.services.MediosServices;

import src.com.certicom.certiscan.utils.Utils;


@ManagedBean(name="generaMediosMB")
@ViewScoped
public class GeneraMediosMB extends GenericBeans implements Serializable{
	
	private ExpedienteDocumento expedienteDocumentoFilter;
	
	private List<ExpedienteDocumento> listaExpedienteDocumentos;
	private List<ExpedienteDocumento> listaExpedienteDocumentosVista;
	private List<ExpedienteDocumento> listaExpedienteDocumentosFilter;
	private List<Expediente> listExpedientes;
	private List<ExpedienteDocumento> listExpedientesDocAgrupado;
	private List<ExpedienteDocumento> listaSedes;
	private Lote loteSelected;
	private Long tamanio;
	private Medios medioSelected;
	private MediosServices mediosServices;

	private boolean _activateCmb;
	private boolean _activateDealer;
	private Integer totalExpeCargados;
	private boolean bexpediente;
	private ExpedienteDocumento expedienteDocumento;
	private ExpedienteDocumentoService expedienteDocumentoServices;
	private boolean bfecha;
	private SelectItem[] listaCanales;
	private ExpedienteService expedienteService;
	private ExpedienteTrackingService expedienteTrackingService;
	private RequestContext context;
	private Expediente expediente;
	private Expediente expSelected;
	private List<Estados> listEstados;
	private EstadosServices estadosServices;
	private List<ExpedienteIncidencia> listExpedienteIncidencias;
	private ExpedienteIncidenciaServices expedienteIncidenciaServices;
	private List<IndicadorCall> listIndicadorCalls;
	private IndicadorCallServices indicadorCallServices;
	private Integer id_indicador_call;
	private Expediente[] selectedExpediente;
	private boolean bAgrupar;
	private Integer cantidad_expedientes;
	private Date fechaRegistro;
	private Integer id_estado;
	private String formato;
	private Integer id_indicador;
	private List<Medios> listMedios;
	private String vestado;
	private EstadosServices estadoServices;
	
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
	
	public GeneraMediosMB(){
	}
	
	@PostConstruct
	public void inicia(){
		this.fecha_ini = new Date();
		this.fecha_fin = new Date();
		this.fechaActual = new Date();
		this.listaExpedienteDocumentos = new ArrayList<ExpedienteDocumento>();
		this.listaExpTracking = new ArrayList<>();
		this._activateCmb = Boolean.FALSE;
		this._activateDealer = Boolean.TRUE;
		this.expedienteDocumentoFilter = new ExpedienteDocumento();
		this.expedienteDocumentoFilter.setFiltradopor(1);
		this.bexpediente = true;
		this.bfecha = false;
		this.medioSelected = new Medios();
		this.expSelected = new Expediente();
		this.estadosServices = new EstadosServices();
		this.listEstados = new ArrayList<Estados>();
		this.listIndicadorCalls = new ArrayList<IndicadorCall>();
		this.indicadorCallServices = new IndicadorCallServices();
		this.expedienteIncidenciaServices = new ExpedienteIncidenciaServices();
		this.bAgrupar = true;
		this.expedienteDocumentoServices = new ExpedienteDocumentoService();
		this.listaExpedienteDocumentosFilter = new ArrayList<ExpedienteDocumento>();
		this.loteSelected = new Lote();
		this.mediosServices = new MediosServices();	
		this.estadoServices = new EstadosServices();
		
		this.context = RequestContext.getCurrentInstance();
		this.expedienteService = new ExpedienteService(); 
		this.expedienteTrackingService  = new ExpedienteTrackingService();
		
		try {
			
			Estados est = new Estados();
			this.expedienteDocumentoFilter.setFec_ini(this.fecha_ini);
			this.expedienteDocumentoFilter.setFec_fin(this.fecha_fin);
			est = this.estadoServices.findById(Constante.EST_PREPARADO_PARA_DESCARGA_DE_FEDATARIO);
			
			this.vestado = est.getDescripcion();
			
			this.listEstados = this.estadosServices.listaEstadosActivo();
			//this.totalExpeCargados = this.expedienteService.getExpedienteAsignados(this.usuarioLogin.getIdusuario());
			this.listIndicadorCalls = this.indicadorCallServices.findByCategoria(Constante.IND_MEDIOS);
			
			if(this.perfilUsuario.getProceso().equals("EXPEDIENTES PROPIOS") || this.perfilUsuario.getProceso().equals("OFICINA LOCAL")){
				this._activateCmb = true;
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
	
	public SelectItem[] filterCanal(){
		SelectItem[] items ;
			items = new SelectItem[]{new SelectItem("","--Seleccione--"),
									 new SelectItem("PERSONA"),
					 				 new SelectItem("EMPRESA")};
			return items;
	}
	
	public void guardar(){
		
		try {				
			comprimirBash();
			this.listMedios = new ArrayList<>();								
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
	
	}
	
	public void comprimirBash() {	
		System.out.println("Actualizar medios");
		
		try {		
			/*Medios medio1ex = new Medios();
			Medios medio2ex = new Medios();
			medio1ex.setId_medio(1);
			medio1ex.setDescripcion("Medio 1");
			medio2ex.setId_medio(2);
			medio2ex.setDescripcion("Medio 2");
			
			this.listMedios.add(medio1ex);
			this.listMedios.add(medio2ex);*/
			for (Medios medio : listMedios) {				
				Date fec_ini = new Date();				
				Utiles.ejecutarBash(medio);				
				Date fec_fin = new Date();				
				medio.setFecha_ini(fec_ini);
				medio.setFecha_fin(fec_fin);				
				this.mediosServices.actualizarFechaInicioFin(medio);				
			}		
		} catch (Exception e) {
			// TODO: handle exception
		}		
	}
	
	public List<Medios> crearMultipleZipArchivos(){		
			
		new Thread(){
			public void run(){
				
				try {
					for (Medios m : listMedios) {
						ByteArrayOutputStream out = new ByteArrayOutputStream();
						ZipOutputStream zos = new ZipOutputStream(out);
						for(ExpedienteDocumento ed: m.getListExpedienteDocumentos()){							
							//System.out.println("Size Expediente Documento: " + m.getListExpedienteDocumentos().size());
							System.out.println("El archivo a procesar pesa : " + ed.getPeso() + " es : " + ed.getDescripcion_peso());							
							byte[] data = Utiles.descargarArchivoViaSFTP2(ed.getRuta());							
							if(!(data==null)){								
								Utiles.addToZipFile(data,""+ed.getNombre_archivo(), zos);								
							}else{								
								System.out.println("Error al procesar archivo : " + ed.getNombre_archivo());
							}																
						}
						m.setRuta(m.getRuta());
						zos.close();
						File file = new File(m.getDescripcion()+".zip");
						FileOutputStream fos = new FileOutputStream (file); 
						out.writeTo(fos);
						Utiles.enviarArchivoViaSFTP2(file, m.getDescripcion()+".zip", Constante.RUTA_MEDIOS);
					}					
				
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}	
				
		}.start();	
		return listMedios;							  
	}
	
	public void mostrarExpedientes(Lote l){
		try {
			
			System.out.println("BUSCAR EXPEDIENTES");
			
			this.listExpedientes = new ArrayList<Expediente>();		
			this.listExpedientes = this.expedienteService.findByIdLote(l.getId_lote());
			
			System.out.println("Tama�o de expedientes: "+this.listExpedientes.size());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void cambiarEstado(Lote l) {		
		this.loteSelected = l;		
		this.id_estado = Constante.EST_ENTREGADO_A_MENSAJERIA;						
	}
	
	public void visualizarExpediente(Medios medio) {		
		this.medioSelected = medio;		
		this.listaExpedienteDocumentosVista = medio.getListExpedienteDocumentos();		
	}
	
	public void buscarExpedientes() {		
		RequestContext context = RequestContext.getCurrentInstance();		
		System.out.println("Buscar expediente");	
		
			try {
				this.listaExpedienteDocumentos = new ArrayList<>();
				this.expedienteDocumentoFilter.setGrupo_formato(this.formato);																				
				
				if (this.id_indicador.equals(Constante.IND_CD)) {				
					this.tamanio = 629145600L;					
				} else if (this.id_indicador.equals(Constante.IND_DVD)) {					
					//this.tamanio = 5242880L;
					this.tamanio = 4294967296L;					
				}
											
				this.listMedios = new ArrayList<>();
				
				int medioId = this.mediosServices.findMax();
												
				String zonal = "ZONA LIMA";				
				if (zonal.equals(Constante.ZONA_LIMA)) {
					System.out.println("ZONA LIMA");
					this.listaExpedienteDocumentos = obtenerLista(zonal);		
					System.out.println("getGrupo_formato" + this.expedienteDocumentoFilter.getGrupo_formato());	
					this.listaSedes = this.expedienteDocumentoServices.obtenerSedes(this.expedienteDocumentoFilter.getGrupo_formato(), zonal, Constante.NUMERO_ENTREGABLE);
					System.out.println("CANTIDAD DE DOCUMENTOS LIMA: "+this.listaExpedienteDocumentos.size());
					System.out.println("CANTIDAD DE SEDES LIMA: "+this.listaSedes.size());
					
					for (ExpedienteDocumento expSede : this.listaSedes) {							
						List<ExpedienteDocumento> listaDocumentoSede = new ArrayList<>();
						for (ExpedienteDocumento expDoc : this.listaExpedienteDocumentos) {
							if (expSede.getSede_oficina().equals(expDoc.getSede_oficina())) {
								listaDocumentoSede.add(expDoc);
							}
						}	
						
						List<ExpedienteDocumento> listaDocumento = new ArrayList<>();
						int contatidadArchivo = 0;
						Long sum_tamanio = 0L;
						int cantidadRegistro = 0;
						
						for (ExpedienteDocumento docSede : listaDocumentoSede) {
							cantidadRegistro ++;
							sum_tamanio += docSede.getPeso();
							if (sum_tamanio <= this.tamanio) {
								contatidadArchivo ++;
								listaDocumento.add(docSede);
							} else {
								sum_tamanio -= docSede.getPeso();
								medioId += 1;									
								Medios medio = new Medios();									
								medio.setDescripcion("Medio " + medioId);
								medio.setId_medio(medioId);
								medio.setDescripcion_peso(Utils.convertirLongBytes(sum_tamanio, false));
								medio.setFecha_registro(new Date());
								medio.setPeso(sum_tamanio);
								medio.setTipo_medio(this.formato);
								medio.setId_estado(Constante.EST_PREPARADO_PARA_DESCARGA_DE_FEDATARIO);
								medio.setDesEstado(this.vestado);
								medio.setListExpedienteDocumentos(listaDocumento);
								medio.setTotal_archivos(contatidadArchivo);
								medio.setRuta(Constante.RUTA_MEDIOS + medio.getDescripcion() + ".zip");
								this.listMedios.add(medio);									
								listaDocumento = new ArrayList<>();
								listaDocumento.add(docSede);
								sum_tamanio = docSede.getPeso();
								contatidadArchivo = 1;
							}
							
							if (cantidadRegistro == listaDocumentoSede.size()) {
								medioId += 1;									
								Medios medio = new Medios();									
								medio.setDescripcion("Medio " + medioId);
								medio.setId_medio(medioId);
								medio.setDescripcion_peso(Utils.convertirLongBytes(sum_tamanio, false));
								medio.setFecha_registro(new Date());
								medio.setPeso(sum_tamanio);
								medio.setTipo_medio(this.formato);
								medio.setId_estado(Constante.EST_PREPARADO_PARA_DESCARGA_DE_FEDATARIO);
								medio.setDesEstado(this.vestado);
								medio.setListExpedienteDocumentos(listaDocumento);
								medio.setTotal_archivos(contatidadArchivo);
								medio.setRuta(Constante.RUTA_MEDIOS + medio.getDescripcion() + ".zip");
								this.listMedios.add(medio);	
							}
						}					
					}					
					System.out.println("CANTIDAD MEDIOS ZONA LIMA: "+this.listMedios.size());				
				} 
				
				zonal = "ZONA NORTE";									
				if(zonal.equals(Constante.ZONA_NORTE)){
					System.out.println("ZONA NORTE");
					this.listaExpedienteDocumentos = obtenerLista(zonal);
					this.listaSedes = this.expedienteDocumentoServices.obtenerSedes(this.expedienteDocumentoFilter.getGrupo_formato(), zonal, Constante.NUMERO_ENTREGABLE);
					System.out.println("CANTIDAD DE DOCUMENTOS NORTE: "+this.listaExpedienteDocumentos.size());
					System.out.println("CANTIDAD DE ZONAS NORTE: "+this.listaSedes.size());
					
					List<ExpedienteDocumento> listaDocumento = new ArrayList<>();
					int contatidadArchivo = 0;
					Long sum_tamanio = 0L;
					Long tamanioSede = 0L;
					int cantidadRegistro = 0;
					
					for (ExpedienteDocumento expSede : this.listaSedes) {		
						cantidadRegistro ++;
						List<ExpedienteDocumento> listaDocumentoSede = new ArrayList<>();						
						for (ExpedienteDocumento expDoc : this.listaExpedienteDocumentos) {
							if (expSede.getSede_oficina().equals(expDoc.getSede_oficina())) {
								tamanioSede += expDoc.getPeso();
								listaDocumentoSede.add(expDoc);
							}
						}
						
						if (tamanioSede > this.tamanio) {
							for (ExpedienteDocumento docSede : listaDocumentoSede) {								
								sum_tamanio += docSede.getPeso();
								if (sum_tamanio <= this.tamanio) {
									contatidadArchivo ++;
									listaDocumento.add(docSede);
								} else {
									sum_tamanio -= docSede.getPeso();
									medioId += 1;									
									Medios medio = new Medios();									
									medio.setDescripcion("Medio " + medioId);
									medio.setId_medio(medioId);
									medio.setDescripcion_peso(Utils.convertirLongBytes(sum_tamanio, false));
									medio.setFecha_registro(new Date());
									medio.setPeso(sum_tamanio);
									medio.setTipo_medio(this.formato);
									medio.setId_estado(Constante.EST_PREPARADO_PARA_DESCARGA_DE_FEDATARIO);
									medio.setDesEstado(this.vestado);
									medio.setListExpedienteDocumentos(listaDocumento);
									medio.setTotal_archivos(contatidadArchivo);
									medio.setRuta(Constante.RUTA_MEDIOS + medio.getDescripcion() + ".zip");
									this.listMedios.add(medio);									
									listaDocumento = new ArrayList<>();
									listaDocumento.add(docSede);
									sum_tamanio = docSede.getPeso();
									contatidadArchivo = 1;
								}
							}	
							tamanioSede = sum_tamanio;
						} else {
							sum_tamanio = tamanioSede;
							contatidadArchivo += listaDocumentoSede.size();
							listaDocumento.addAll(listaDocumentoSede);
						}	
						
						if (cantidadRegistro == this.listaSedes.size()) {
							medioId += 1;									
							Medios medio = new Medios();									
							medio.setDescripcion("Medio " + medioId);
							medio.setId_medio(medioId);
							medio.setDescripcion_peso(Utils.convertirLongBytes(sum_tamanio, false));
							medio.setFecha_registro(new Date());
							medio.setPeso(sum_tamanio);
							medio.setTipo_medio(this.formato);
							medio.setId_estado(Constante.EST_PREPARADO_PARA_DESCARGA_DE_FEDATARIO);
							medio.setDesEstado(this.vestado);
							medio.setListExpedienteDocumentos(listaDocumento);
							medio.setTotal_archivos(contatidadArchivo);
							medio.setRuta(Constante.RUTA_MEDIOS + medio.getDescripcion() + ".zip");
							this.listMedios.add(medio);	
						}
					}									
				System.out.println("CANTIDAD MEDIOS ZONA NORTE: "+this.listMedios.size());
			}
				
			zonal = "ZONA SUR";
			if(zonal.equals(Constante.ZONA_SUR)){
				System.out.println("ZONA SUR");
				this.listaExpedienteDocumentos = obtenerLista(zonal);
				this.listaSedes = this.expedienteDocumentoServices.obtenerSedes(this.expedienteDocumentoFilter.getGrupo_formato(), zonal, Constante.NUMERO_ENTREGABLE);
				System.out.println("CANTIDAD DE DOCUMENTOS SUR: "+this.listaExpedienteDocumentos.size());
				System.out.println("CANTIDAD DE ZONAS SUR: "+this.listaSedes.size());
				
				List<ExpedienteDocumento> listaDocumento = new ArrayList<>();
				int contatidadArchivo = 0;
				Long sum_tamanio = 0L;
				Long tamanioSede = 0L;
				int cantidadRegistro = 0;
				
				for (ExpedienteDocumento expSede : this.listaSedes) {		
					cantidadRegistro ++;
					List<ExpedienteDocumento> listaDocumentoSede = new ArrayList<>();						
					for (ExpedienteDocumento expDoc : this.listaExpedienteDocumentos) {
						if (expSede.getSede_oficina().equals(expDoc.getSede_oficina())) {
							tamanioSede += expDoc.getPeso();
							listaDocumentoSede.add(expDoc);
						}
					}
					
					if (tamanioSede > this.tamanio) {
						for (ExpedienteDocumento docSede : listaDocumentoSede) {								
							sum_tamanio += docSede.getPeso();
							if (sum_tamanio <= this.tamanio) {
								contatidadArchivo ++;
								listaDocumento.add(docSede);
							} else {
								sum_tamanio -= docSede.getPeso();
								medioId += 1;									
								Medios medio = new Medios();									
								medio.setDescripcion("Medio " + medioId);
								medio.setId_medio(medioId);
								medio.setDescripcion_peso(Utils.convertirLongBytes(sum_tamanio, false));
								medio.setFecha_registro(new Date());
								medio.setPeso(sum_tamanio);
								medio.setTipo_medio(this.formato);
								medio.setId_estado(Constante.EST_PREPARADO_PARA_DESCARGA_DE_FEDATARIO);
								medio.setDesEstado(this.vestado);
								medio.setListExpedienteDocumentos(listaDocumento);
								medio.setTotal_archivos(contatidadArchivo);
								medio.setRuta(Constante.RUTA_MEDIOS + medio.getDescripcion() + ".zip");
								this.listMedios.add(medio);									
								listaDocumento = new ArrayList<>();
								listaDocumento.add(docSede);
								sum_tamanio = docSede.getPeso();
								contatidadArchivo = 1;
							}
						}	
						tamanioSede = sum_tamanio;
					} else {
						sum_tamanio = tamanioSede;
						contatidadArchivo += listaDocumentoSede.size();
						listaDocumento.addAll(listaDocumentoSede);
					}	
					
					if (cantidadRegistro == this.listaSedes.size()) {
						medioId += 1;									
						Medios medio = new Medios();									
						medio.setDescripcion("Medio " + medioId);
						medio.setId_medio(medioId);
						medio.setDescripcion_peso(Utils.convertirLongBytes(sum_tamanio, false));
						medio.setFecha_registro(new Date());
						medio.setPeso(sum_tamanio);
						medio.setTipo_medio(this.formato);
						medio.setId_estado(Constante.EST_PREPARADO_PARA_DESCARGA_DE_FEDATARIO);
						medio.setDesEstado(this.vestado);
						medio.setListExpedienteDocumentos(listaDocumento);
						medio.setTotal_archivos(contatidadArchivo);
						medio.setRuta(Constante.RUTA_MEDIOS + medio.getDescripcion() + ".zip");
						this.listMedios.add(medio);	
					}
				}
			System.out.println("CANTIDAD MEDIOS ZONA SUR: "+this.listMedios.size());
		}
			
		System.out.println("FIN DE LA GENERACION POR ZONAS");
		this.mediosServices.generarMedios(this.listMedios, this.usuarioLogin.getIdusuario(), this.usuarioLogin.getId_oficina());
		Medios medioFilter = new Medios();
		medioFilter.setFec_ini(this.expedienteDocumentoFilter.getFec_ini());
		medioFilter.setFec_fin(this.expedienteDocumentoFilter.getFec_fin());
		medioFilter.setTipo_medio(this.expedienteDocumentoFilter.getGrupo_formato());
							
		List<Medios> listMediosPendientes = this.mediosServices.buscarPendientes(medioFilter);
								
		for (Medios medios : listMediosPendientes) {
			List<ExpedienteDocumento> expDoc = new ArrayList<ExpedienteDocumento>();
			expDoc = this.expedienteDocumentoServices.listExpDocMedio(medios.getId_medio());			
			medios.setListExpedienteDocumentos(new ArrayList<ExpedienteDocumento>());
			medios.setListExpedienteDocumentos(expDoc);
		}
		
		this.listMedios = listMediosPendientes;
		System.out.println("Tama�o lista pendientes: "+listMediosPendientes.size());
		this.totalExpeCargados = this.listaExpedienteDocumentos.size();				
		context.update("formListaLotes");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<ExpedienteDocumento> obtenerLista(String zona) throws Exception{
		System.out.println(this.formato);
		this.listExpedientesDocAgrupado = this.expedienteDocumentoServices.obtenerCarpetas(this.formato, zona, Constante.NUMERO_ENTREGABLE);
		this.listaExpedienteDocumentos = new ArrayList<>();
		
		for (ExpedienteDocumento ex : this.listExpedientesDocAgrupado) {
			this.expedienteDocumentoFilter.setUbicacion(ex.getUbicacion());
			this.expedienteDocumentoFilter.setZona(zona);
			this.expedienteDocumentoFilter.setEntregable(Constante.NUMERO_ENTREGABLE);
			this.listaExpedienteDocumentos.addAll(this.expedienteDocumentoServices.consultaPreparadoMedioExpediente(this.expedienteDocumentoFilter));
			System.out.println(this.listaExpedienteDocumentos.size());
		}
		return this.listaExpedienteDocumentos;
	}
	
	public void cambiarFecha(){
		this.fechaLimite = this.expedienteDocumentoFilter.getFec_ini();		
	}
	
	public void cambiarCombo(){
		if(this.expedienteDocumentoFilter.getFiltradopor()==1){
			this.expedienteDocumentoFilter.setFec_ini(null);
			this.expedienteDocumentoFilter.setFec_fin(null);
			this.bexpediente = true;
			this.bfecha = false;
		}else{
			this.expedienteDocumentoFilter.setVfiltradopor(null);
			this.bexpediente = false;
			this.bfecha = true;
		}
	}
	
	/**##########################setter and  getter#####################################*/
	

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

	public List<IndicadorCall> getListIndicadorCalls() {
		return listIndicadorCalls;
	}

	public void setListIndicadorCalls(List<IndicadorCall> listIndicadorCalls) {
		this.listIndicadorCalls = listIndicadorCalls;
	}

	public Integer getId_indicador_call() {
		return id_indicador_call;
	}

	public void setId_indicador_call(Integer id_indicador_call) {
		this.id_indicador_call = id_indicador_call;
	}

	public List<ExpedienteIncidencia> getListExpedienteIncidencias() {
		return listExpedienteIncidencias;
	}

	public void setListExpedienteIncidencias(
			List<ExpedienteIncidencia> listExpedienteIncidencias) {
		this.listExpedienteIncidencias = listExpedienteIncidencias;
	}

	public Expediente[] getSelectedExpediente() {
		return selectedExpediente;
	}

	public void setSelectedExpediente(Expediente[] selectedExpediente) {
		this.selectedExpediente = selectedExpediente;
	}

	public boolean isbAgrupar() {
		return bAgrupar;
	}

	public void setbAgrupar(boolean bAgrupar) {
		this.bAgrupar = bAgrupar;
	}

	public Integer getCantidad_expedientes() {
		return cantidad_expedientes;
	}

	public void setCantidad_expedientes(Integer cantidad_expedientes) {
		this.cantidad_expedientes = cantidad_expedientes;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public List<Expediente> getListExpedientes() {
		return listExpedientes;
	}

	public void setListExpedientes(List<Expediente> listExpedientes) {
		this.listExpedientes = listExpedientes;
	}

	public Integer getId_estado() {
		return id_estado;
	}

	public void setId_estado(Integer id_estado) {
		this.id_estado = id_estado;
	}

	public Lote getLoteSelected() {
		return loteSelected;
	}

	public void setLoteSelected(Lote loteSelected) {
		this.loteSelected = loteSelected;
	}

	public ExpedienteDocumento getExpedienteDocumentoFilter() {
		return expedienteDocumentoFilter;
	}

	public void setExpedienteDocumentoFilter(
			ExpedienteDocumento expedienteDocumentoFilter) {
		this.expedienteDocumentoFilter = expedienteDocumentoFilter;
	}

	public List<ExpedienteDocumento> getListaExpedienteDocumentos() {
		return listaExpedienteDocumentos;
	}

	public void setListaExpedienteDocumentos(
			List<ExpedienteDocumento> listaExpedienteDocumentos) {
		this.listaExpedienteDocumentos = listaExpedienteDocumentos;
	}

	public List<ExpedienteDocumento> getListaExpedienteDocumentosFilter() {
		return listaExpedienteDocumentosFilter;
	}

	public void setListaExpedienteDocumentosFilter(
			List<ExpedienteDocumento> listaExpedienteDocumentosFilter) {
		this.listaExpedienteDocumentosFilter = listaExpedienteDocumentosFilter;
	}

	public ExpedienteDocumento getExpedienteDocumento() {
		return expedienteDocumento;
	}

	public void setExpedienteDocumento(ExpedienteDocumento expedienteDocumento) {
		this.expedienteDocumento = expedienteDocumento;
	}

	public String getFormato() {
		return formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}

	public Integer getId_indicador() {
		return id_indicador;
	}

	public void setId_indicador(Integer id_indicador) {
		this.id_indicador = id_indicador;
	}

	public Long getTamanio() {
		return tamanio;
	}

	public void setTamanio(Long tamanio) {
		this.tamanio = tamanio;
	}

	public List<Medios> getListMedios() {
		return listMedios;
	}

	public void setListMedios(List<Medios> listMedios) {
		this.listMedios = listMedios;
	}

	public List<ExpedienteDocumento> getListaExpedienteDocumentosVista() {
		return listaExpedienteDocumentosVista;
	}

	public void setListaExpedienteDocumentosVista(
			List<ExpedienteDocumento> listaExpedienteDocumentosVista) {
		this.listaExpedienteDocumentosVista = listaExpedienteDocumentosVista;
	}

	public Medios getMedioSelected() {
		return medioSelected;
	}

	public void setMedioSelected(Medios medioSelected) {
		this.medioSelected = medioSelected;
	}

	public String getVestado() {
		return vestado;
	}

	public void setVestado(String vestado) {
		this.vestado = vestado;
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

	public List<ExpedienteDocumento> getListExpedientesDocAgrupado() {
		return listExpedientesDocAgrupado;
	}

	public void setListExpedientesDocAgrupado(
			List<ExpedienteDocumento> listExpedientesDocAgrupado) {
		this.listExpedientesDocAgrupado = listExpedientesDocAgrupado;
	}		
}
