package com.certicom.certiscan.managedBeans;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.certicom.certiscan.services.IndicadorCallServices;
import com.certicom.certiscan.domain.IndicadorCall;
import com.certicom.certiscan.commons.Constante;
import com.certicom.certiscan.commons.FacesUtils;
import com.certicom.certiscan.commons.GenericBeans;
import com.certicom.certiscan.commons.Utiles;
import com.certicom.certiscan.domain.DocumentoPagina;
import com.certicom.certiscan.domain.Expediente;
import com.certicom.certiscan.domain.ExpedienteDocumento;
import com.certicom.certiscan.domain.ExpedienteIncidencia;
import com.certicom.certiscan.domain.ExpedienteTracking;
import com.certicom.certiscan.domain.Log;
import com.certicom.certiscan.domain.Menu;
import com.certicom.certiscan.domain.Oficina;
import com.certicom.certiscan.domain.Usuario;
import com.certicom.certiscan.managedBeans.LogMB;
import com.certicom.certiscan.services.DocumentoPaginaService;
import com.certicom.certiscan.services.ExpedienteDocumentoService;
import com.certicom.certiscan.services.ExpedienteIncidenciaServices;
import com.certicom.certiscan.services.ExpedienteService;
import com.certicom.certiscan.services.ExpedienteTrackingService;
import com.certicom.certiscan.services.MenuServices;
import com.certicom.certiscan.services.OficinaService;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.lowagie.text.pdf.PdfReader;

import src.com.certicom.certiscan.utils.Utils;

@ManagedBean(name="recepExp")
@ViewScoped
public class RecepcionarExpedienteMB extends GenericBeans implements Serializable {
	
	private String msj_peso_archivo ="";
	private String msj_exp_not_found ="";
	
	private boolean renderedRegistro;
	private boolean renderedFileUpload;
	private boolean archivoCargado;
	private List<ExpedienteIncidencia> listExpIncidencia;
	private ExpedienteIncidenciaServices expinciServices;	
	private ExpedienteIncidencia expedienteincidencia;
	private List<ExpedienteIncidencia> listaExpIncidenciaMarcadas;
	
	private List<IndicadorCall> listaProceso;
	private List<IndicadorCall> listaTipoRecepcion;
	private List<IndicadorCall> listaProcesoSelect;
	private IndicadorCallServices indicadorCallServices;
	
	private String desProceso;
	
	private boolean initFieldsRegistro;
	private boolean renderedTablaDocumento;
	private boolean renderedObs;
	
	private Integer nro_archivos_subidos = 0;
	private Integer nro_archivos_guardados = 0;
	private boolean bandIncidencia;
	
	private Date fecha_recepcion;
	
	private List<UploadedFile> archivoCargaList = new LinkedList<UploadedFile>();
	private List<ExpedienteDocumento> listaDocumentos = new ArrayList<>();
	
	private List<ExpedienteDocumento> listaDocumentosRegistrados = new ArrayList<>();
	private List<ExpedienteDocumento> listaDocumentosRegistradosF;
	private List<DocumentoPagina> listaPaginas = new ArrayList<>();
	
	private ExpedienteService expedienteService;
	private ExpedienteDocumentoService expedienteDocumentoService;
	private DocumentoPaginaService documentoPaginaService;
	private ExpedienteTrackingService expedienteTrackingService;
	private OficinaService oficinaService;
	private String nroexpediente;
	private String nrosolicitud;
	
	private Expediente nuevoExpediente;
	private Expediente expedSelected;
	private ExpedienteDocumento expDocSelected;	
	
	@ManagedProperty(value = "#{loginMB.usuario}")
	private Usuario usuarioLogin;
	@ManagedProperty(value = "#{loginMB}")
	private LoginMB login;

	private List<Expediente> listaExpedientes;
	private List<Expediente> listaExpedientesFilter;
	private List<Integer> listEliminar;
	private List<ExpedienteIncidencia> listExpInd;
	private List<ExpedienteIncidencia> listExpIndObs;
	
	private String rutaCarpeta;
	
	private List<Oficina> listaOficinas;
	
	// Filtros
	private Integer f_idoficina;
	
	private Log log;
	private LogMB logmb;
	private MenuServices menuServices;
	
	
	
	@PostConstruct
	public void inicio(){
		
		this.listaProceso = new ArrayList<IndicadorCall>();
		this.listaTipoRecepcion = new ArrayList<IndicadorCall>();
		this.indicadorCallServices = new IndicadorCallServices();
		
		this.expedienteService = new ExpedienteService();
		this.expedienteDocumentoService = new ExpedienteDocumentoService();
		this.documentoPaginaService = new DocumentoPaginaService();
		this.expedienteTrackingService = new ExpedienteTrackingService();
		this.oficinaService = new OficinaService();
		this.listExpIncidencia = new ArrayList<ExpedienteIncidencia>();
		this.expinciServices = new ExpedienteIncidenciaServices();
		this.expedienteincidencia = new ExpedienteIncidencia();
		this.bandIncidencia = false;
		this.listaExpIncidenciaMarcadas = new ArrayList<ExpedienteIncidencia>();
		this.listEliminar = new ArrayList<Integer>();
		this.listExpInd = new ArrayList<ExpedienteIncidencia>();
		this.listExpIndObs = new ArrayList<ExpedienteIncidencia>();
		this.menuServices=new MenuServices();
		log = (Log) getSpringBean(Constante.SESSION_LOG);
		logmb = new LogMB();
		try {						
			this.listaProceso = this.indicadorCallServices.getProceso(Constante.IND_PROCESO);
			this.listaTipoRecepcion = this.indicadorCallServices.getTipoRecepcion(Constante.IND_RECEP);
			Menu codMenu = menuServices.opcionMenuByPretty("pretty:recepcionarExpediente");
			log.setCod_menu(codMenu.getCod_menu().intValue());
			//int codMenu = menuServices.opcionMenuByPrettyCod("pretty:recepcionarExpediente");
			//log.setCod_menu(codMenu);
			log.setIdusuario(this.login.getIdUsuario());
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		limpiarExpediente();
		this.expDocSelected = new ExpedienteDocumento();
		
		this.renderedRegistro = Boolean.FALSE;
		this.initFieldsRegistro = Boolean.TRUE;
		this.renderedFileUpload = Boolean.TRUE;
		this.renderedTablaDocumento = Boolean.FALSE;
		this.renderedObs = Boolean.FALSE;
		
		this.fecha_recepcion = new Date();
		
		if(login.isAdmin()){
			this.f_idoficina = 0;
		} else{
			this.f_idoficina = usuarioLogin.getId_oficina();
		}
		
		initCombos();
		initLista();	
		
	}	
	
	private void initLista(){
		this.listaExpedientes = new ArrayList<>();
	}
	
	private void initCombos(){
		try {
			this.listaOficinas = oficinaService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void activarNuevoRegistro(){
		initFieldsRegistro =  Boolean.FALSE;
	}
	
	private void limpiarExpediente(){
		
		this.nuevoExpediente = new Expediente();
		this.nuevoExpediente.setArchivadores(0);
		this.nuevoExpediente.setAnillados(0);
		this.nuevoExpediente.setCantidad_cd(0);
		this.nuevoExpediente.setCantidad_otro_medio(0);
		this.nuevoExpediente.setFile(0);
		this.nuevoExpediente.setVinifiles(0);
		this.nuevoExpediente.setPaquete(0);
		this.nuevoExpediente.setPioner(0);
		this.nuevoExpediente.setCarta_fianza(0);
		this.nuevoExpediente.setSobre(0);
		this.expedSelected = new Expediente();
	}
	
	private void limpiarListaArchivos(){
		this.archivoCargaList = new LinkedList<UploadedFile>();
		this.listaDocumentos = new ArrayList<>();
	}
	
	public void mostrarObservacion(){
		if(this.nuevoExpediente.getCondicion() != null){
		
			if(this.nuevoExpediente.getCondicion().equals("RECHAZADO")){
				this.renderedObs = Boolean.TRUE;
			} else{
				this.renderedObs = Boolean.FALSE;
				this.nuevoExpediente.setObservacion(null);
			} 
		
		} else{
			this.renderedObs = Boolean.FALSE;
		}		
		
	}
	
	public void listaIncidencia(){
		try {
			if(this.nuevoExpediente.getCondicion().equals("ACEPTADO")){
				
				this.bandIncidencia = Boolean.FALSE;
				this.renderedObs = Boolean.FALSE;
			}else{
				this.renderedObs = Boolean.TRUE;
				//Expediente ex = this.expedienteService.findByNroExpediente(this.nuevoExpediente.getNro_expediente());
				//this.listExpIncidencia = this.expinciServices.listarIncidencias(ex.getExpediente_id());
				this.listExpIncidencia = this.expinciServices.findAll2();
				System.out.println("ENTRANDO A LISTAAAAAAAAAAAAR");
				this.bandIncidencia = Boolean.TRUE;
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void buscarExpedientes(){
		try {
			this.listaExpedientes = this.expedienteService.buscarExpedientes(this.f_idoficina);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	public void registrarExpediente(){
		RequestContext context = RequestContext.getCurrentInstance();
		try {
			
			
			this.nuevoExpediente.setNro_expediente(this.nuevoExpediente.getNro_expediente().trim());
			

			Expediente ex_anterior = this.expedienteService.findByNroExpNroSol(this.nuevoExpediente.getNro_expediente(), this.nuevoExpediente.getNro_solicitud());
				
//			if(ex_anterior == null)
//			if(ex_expe == null && nuevoExpediente.getNro_solicitud() != ex_anterior.getNro_solicitud()){
			System.out.println("NUEVO!!! " + nuevoExpediente.getNro_solicitud());

			//if(!nuevoExpediente.getNro_solicitud().equals(ex_anterior.getNro_solicitud())){
			if((ex_anterior==null)){
			
			/*
			for(int i = 0; i<this.listaProceso.size(); i++){
				if(this.listaProceso.get(i).getId_categoria_call() == this.nuevoExpediente.getId_proceso()){
					this.desProceso = this.listaProceso.get(i).getDescripcion();
				}
			}*/
				
			for (IndicadorCall indC : this.listaProceso) {
				
				if(indC.getId_indicadores_call().equals(this.nuevoExpediente.getId_proceso())){
					this.desProceso = indC.getDescripcion();
				}
				
			}
					
			this.nuevoExpediente.setFecha_reg(new Date());
			this.nuevoExpediente.setIdusuario(this.usuarioLogin.getIdusuario());
			this.nuevoExpediente.setFecha_recepcion(this.fecha_recepcion);
			this.nuevoExpediente.setId_estado(Constante.EST_PREPARADO_PARA_PREPARAR);
			this.nuevoExpediente.setId_oficina(this.usuarioLogin.getId_oficina());
			this.nuevoExpediente.setProceso(this.desProceso);
			
			boolean _pase = false;
			
			if(this.bandIncidencia){
				if(this.listaExpIncidenciaMarcadas.size()>0){
					_pase = true;
				}else{
					FacesUtils.showFacesMessage("Debe registrar por lo menos una incidencia y Observacion",Constante.INFORMACION);
				}
			}else {
				_pase = true;
			}
			
			expedienteService.crearExpediente(this.nuevoExpediente);
			
			log.setAccion("INSERT");
            log.setDescripcion("Se registró el Nro Expediente : " + this.nuevoExpediente.getNro_expediente());
            logmb.insertarLog(log);
			
			if(this.nuevoExpediente.getExpediente_id() !=0 && _pase == true){
				System.out.println("Nuevo Expediente ID "+this.nuevoExpediente.getExpediente_id());
				
				ExpedienteTracking et = new ExpedienteTracking();
				et.setExpediente_id(this.nuevoExpediente.getExpediente_id());
				et.setFecha_registro(new Date());
				et.setIdusuario(this.usuarioLogin.getIdusuario());
				et.setIdusuario_registro(this.usuarioLogin.getIdusuario());
				et.setId_estado(Constante.EST_RECEPCIONADO);
				et.setCondicion(this.nuevoExpediente.getCondicion());
				et.setFecha_recepcion(this.nuevoExpediente.getFecha_recepcion());
				et.setObservacion(this.nuevoExpediente.getObservacion());
				et.setId_oficina(this.usuarioLogin.getId_oficina());	
				
				if(this.bandIncidencia){
					
					this.listEliminar = new ArrayList<Integer>();
					this.listExpInd = new ArrayList<ExpedienteIncidencia>();
					this.listExpIndObs = new ArrayList<ExpedienteIncidencia>();
					
					for (int i = 0; i < this.listExpIncidencia.size(); i++) { 
						ExpedienteIncidencia einc  = this.listExpIncidencia.get(i);
						if(einc.getCod_expincidencia() !=null){
							if(einc.getValor_exp() == 0){
								//this.expedienteIncidenciaService.eliminarExpedienteIncidencia(einc.getCod_expincidencia());
								this.listEliminar.add(einc.getCod_expincidencia());
							}
						} else{
							if(einc.getValor_exp() != 0){
								ExpedienteIncidencia ei = new ExpedienteIncidencia();
								ei.setExpediente_id(this.nuevoExpediente.getExpediente_id());
								ei.setId_incidencia(einc.getId_incidencia());
								ei.setObservacion(einc.getObservacion());
								ei.setFecregistro(new Date());
								
								//this.expedienteIncidenciaService.crearExpedienteIncidencia(ei);
								this.listExpInd.add(ei);
							}
						}
						
						if(einc.getCod_expincidencia() !=null){
							if(einc.getValor_act()!=null){
								if(einc.getValor_act() == 0){
									//this.expedienteIncidenciaService.actualizarObsExpedienteIncidencia(einc);
									this.listExpIndObs.add(einc);
									
								}
							}
						}
					}
					/*for (int i = 0; i < listaExpedienteIncidencia.size() ; i++) {
						ExpedienteIncidencia exi = listaExpedienteIncidencia.get(i);
						exi.setCod_resultado(this.cod_resultadoNuevo);
						expedienteIncidenciaService.crearExpedienteIncidencia(exi);
					}*/
					this.listExpIncidencia = new ArrayList<>();
				}
				
				this.expinciServices.deleteBatchExpedienteIncidencia(this.listEliminar, this.nuevoExpediente.getNro_expediente(), log);
				//log.setAccion("DELETE");
	            //log.setDescripcion("Se eliminaron las incidencias del expediente : " + this.nuevoExpediente.getNro_expediente());
	            //logmb.insertarLog(log);
	            
				this.expinciServices.insertBatchExpedienteIncidencia(this.listExpInd, this.nuevoExpediente.getNro_expediente(), log);
				//log.setAccion("INSERT");
	            //log.setDescripcion("Se ingresaron las incidencias al expediente : " + this.nuevoExpediente.getNro_expediente());
	            //logmb.insertarLog(log);
	            
				this.expinciServices.updateBatchExpedienteIncidencia(this.listExpIndObs, this.nuevoExpediente.getNro_expediente(), log);
				//log.setAccion("UPDATE");
	            //log.setDescripcion("Se actualizaron las incidencias del expediente : " + this.nuevoExpediente.getNro_expediente());
	            //logmb.insertarLog(log);				
				
				expedienteTrackingService.crearExpedienteTracking(et);				
				log.setAccion("UPDATE");
	            log.setDescripcion("Se creó el tracking del Nro Expediente : " + this.nuevoExpediente.getNro_expediente()+" con el estado RECEPCIONADO");
	            logmb.insertarLog(log);
				
				if(et.getCondicion().equals("ACEPTADO")){
						crearCarpeta();
					Expediente ex_actual = this.expedienteService.findByNroExp(this.nuevoExpediente.getNro_expediente());
					ex_actual.setRuta_carpeta(this.rutaCarpeta);					
		            
					this.expedienteService.updateRutaCarpetaExpedientebyId(ex_actual);
					
					log.setAccion("UPDATE");
		            log.setDescripcion("Se actualizó la ruta de la carpeta del expediente : " + this.nuevoExpediente.getNro_expediente());
		            logmb.insertarLog(log);
		            
					et.setId_estado(Constante.EST_PREPARADO_PARA_PREPARAR);
				}
				
				et.setFecha_registro(new Date());
				expedienteTrackingService.crearExpedienteTracking(et);
				
				log.setAccion("INSERT");
	            log.setDescripcion("Se creó el tracking del Nro Expediente : " + this.nuevoExpediente.getNro_expediente()+" para el estado PREPARADO PARA DIGITALIZAR");
	            logmb.insertarLog(log);
				
				FacesUtils.showFacesMessage("Se registro el expediente Nro " + this.nuevoExpediente.getNro_expediente(), Constante.INFORMACION);
				this.nroexpediente = this.nuevoExpediente.getNro_expediente();
				this.nrosolicitud = this.nuevoExpediente.getNro_solicitud();
				initNuevoExpediente();
				
				renderedRegistro = Boolean.TRUE;
				initFieldsRegistro =  Boolean.TRUE;
				context.update("dlgDetSuccesGroup");
				context.execute("PF('dlgSuccessGroup').show()");
				
				ex_anterior = new Expediente();
			}
			

			
			} else{
				msj_exp_not_found = "Ya existe el expediente con Numero " + this.nuevoExpediente.getNro_expediente() + " y numero de solictud con Numero " + this.nuevoExpediente.getNro_solicitud();
				context.update("cdlExpNotfound");
				context.execute("PF('wvcdlExpNotfound').show()");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			renderedRegistro = Boolean.FALSE;
		}
		
		
		
	}
	
	public void crearCarpeta(){
		
			System.out.println("enviando el archivo de imagen");
			
			/*
			String HOST = "192.168.1.10";
		    Integer PUERTO = 22;
		    String USUARIO = "will";
		    String PASSWORD = "gadiel";
		    */
			
			String HOST = Constante.HOST_SCPF;
		    Integer PUERTO = Constante.PUERTO_SCPF;
		    String USUARIO = Constante.USUARIO_SCPF;
		    String PASSWORD = Constante.PASSWORD_SCPF;
		    
		    
		    //String RUTA = "/var/www/html/tipodocumental/";
		    //String RUTA_PREVIA = "/opt/digitalizacion/";
		    String RUTA_PREVIA = Constante.RUTA_DIGITILZACION;
		    String RUTA = RUTA_PREVIA+this.nuevoExpediente.getNro_expediente();
		    
		    File folder = new File(RUTA_PREVIA);
		    Session miSesion = null;
		    Channel miCanal = null;
		    ChannelSftp canalSFTP = null;
			
	        try
	        {
	            JSch jsch = new JSch(); //instancia de jsch
	            miSesion= jsch.getSession(USUARIO,HOST,PUERTO);//establezco la sesion
	            miSesion.setPassword(PASSWORD); //le doy la clave
	            Properties configuracion = new Properties(); //nueva instancia de arch de config.
	            configuracion.put("StrictHostKeyChecking","no"); //seteo una propiedad
	            miSesion.setConfig(configuracion);//se la paso a la sesion
	            System.out.println("iniciando conexion...");
	            miSesion.connect();//intento la conexion
	            miCanal =  miSesion.openChannel("sftp"); //abro un canal sftp
	            miCanal.connect();//me conecto
	            canalSFTP = (ChannelSftp)miCanal;//casteo
	            canalSFTP.cd(RUTA_PREVIA);//exploroe el directorio remoto
	            //this.rutaImagen=RUTA+archImg.getName();
	            //archImg.setReadOnly();
	            canalSFTP.mkdir(RUTA);
	            this.rutaCarpeta = RUTA;
	            //canalSFTP.put(new FileInputStream(folder),this.nuevoExpediente.getNro_expediente());
	            
	           
	        }catch(Exception g){
	            System.out.println("Transferencia Fallida");
	            g.printStackTrace();
	        }finally {
	        	
	            // Cerramos el canal y session
	            if (canalSFTP.isConnected())
	                    canalSFTP.disconnect();
	            if (miSesion.isConnected())
	                    miSesion.disconnect();
	        }
	}
	
	private void initNuevoExpediente(){
		this.nuevoExpediente = new Expediente();
		this.nuevoExpediente.setArchivadores(0);
		this.nuevoExpediente.setAnillados(0);
		this.nuevoExpediente.setCantidad_cd(0);
		this.nuevoExpediente.setCantidad_otro_medio(0);
		this.nuevoExpediente.setFile(0);
		this.nuevoExpediente.setVinifiles(0);
		this.nuevoExpediente.setPaquete(0);
		this.nuevoExpediente.setPioner(0);
		this.nuevoExpediente.setCarta_fianza(0);
		this.nuevoExpediente.setSobre(0);
		this.fecha_recepcion = new Date();
		this.renderedObs = Boolean.FALSE;
	}
	
	public void cancelarExpediente(){
		initNuevoExpediente();
	}
	
	public void cargarDocumentos(FileUploadEvent event) throws Exception{
		RequestContext context = RequestContext.getCurrentInstance();
		this.archivoCargado = true;
		
		if(event.getFile() != null) {
			
			this.archivoCargaList.add(event.getFile());
			
			ExpedienteDocumento ed = new ExpedienteDocumento();
			ed.setNombre_archivo(event.getFile().getFileName());
			ed.setPeso(event.getFile().getSize());
			ed.setDescripcion_peso(Utils.convertirLongBytes(event.getFile().getSize(), false));
		//	ed.setId_usuario_creacion(this.usuarioLogin.getIdusuario());
			
			ed.setNro_archivo(this.expedienteDocumentoService.getNroArchivoSeq());
			
			//File temporal3 = File.createTempFile("prefix", ".pdf");
			File _archivo = File.createTempFile("prefix", ".pdf");
			
			  InputStream in = event.getFile().getInputstream();
			  OutputStream out = new FileOutputStream(_archivo);
			  byte[] buf = new byte[1024];
	            int len;
	             
	            while ((len = in.read(buf)) > 0) {
	              out.write(buf, 0, len);
	            }
	            
	            in.close();
	            out.close();
	            ed.setFile(_archivo);
	           
	           StringBuffer cab = new StringBuffer("Las siguientes paginas del archivo "+  ed.getNombre_archivo() + " no esta entre el rango 15kb-300kb :"); 
	          StringBuffer bf = new StringBuffer();
	           
	            
//	            System.out.println("PESO DEL ARCHIVO " +_archivo.length());
//	            PDDocument pdf_doc = PDDocument.load(_archivo);
//	            pdf_doc.
//	            PDPage page = pdf_doc.getPage(0);
//	            System.out.println("pdf box " +pdf_doc.getNumberOfPages());
//	            System.out.println("ss " + page.getContents().available());
	            
	            PdfReader pf = new PdfReader(new FileInputStream(_archivo));
	     //     PdfReader subrep = new PdfReader(ExportarArchivo.getPath("resources/1.pdf"));
	            
	            for(int i = 0; i < pf.getNumberOfPages(); i++) {
	            	System.out.println("" + pf.getPageContent(i+1).length + " - " +Utils.convertirLongBytes(pf.getPageContent(i+1).length, false));
	            	System.out.println("subrep " +pf.getPageContent(i+1).length);
//	            	subrep.setPageContent(i+1, pf.getPageContent(i+1));
//	            	subrep.close();
//	            	System.out.println(subrep.getFileLength());
	            	DocumentoPagina  dp = new DocumentoPagina();
	            	dp.setNro_pagina(i+1);
	            	dp.setPeso(Long.valueOf(pf.getPageContent(i+1).length));
	            	dp.setDescripcion_peso(Utils.convertirLongBytes(Long.valueOf(pf.getPageContent(i+1).length), false));
	            	ed.getListaPaginas().add(dp);
	            	
	            	 if(Long.valueOf(pf.getPageContent(i+1).length) < 15000 || Long.valueOf(pf.getPageContent(i+1).length) > 300000){
	            		 bf.append("pag "+dp.getNro_pagina() + " - " +dp.getDescripcion_peso()+ " ");
	            	 }
	            	
	            }
	            
//	            PdfStamper stamper;
//   	            String path;
//   	            pf.selectPages("1-2");
//   	            path = String.format("E:/prueba_pdf/pdos-%s.pdf", 5);
//   	            stamper = new PdfStamper(pf,new FileOutputStream(path));
//   	            stamper.close();
	          ed.setNro_paginas(pf.getNumberOfPages());
	          pf.close();
	            
	          this.listaDocumentos.add(ed);  
	          this.nro_archivos_subidos=  listaDocumentos.size();
	          this.renderedFileUpload = Boolean.FALSE;
	          if(bf.length()>0){
	        	  msj_peso_archivo = "";
	        	  System.out.println("hay archivos");
	        	  //FacesUtils.showFacesMessage(cab.toString()+" "+ bf.toString(), Constante.ADVERTENCIA);
	        	  msj_peso_archivo = cab.toString()+" "+ bf.toString();
	        	  context.update("cdlPesoArchivos");
	        	  context.execute("PF('wcdlPesoArchivos').show()");
	          }
		}		
		
	}	
	
	public void subirRegistrarDocumentos(){
		RequestContext context = RequestContext.getCurrentInstance();
		try {
			if(listaDocumentos != null && listaDocumentos.size()>0){
			
		 boolean res =	expedienteDocumentoService.insertDocumentosRegistros(this.nuevoExpediente.getExpediente_id(),listaDocumentos, this.usuarioLogin.getIdusuario(),null, log);
		 
		 if(res){
			 
		//	Utiles.enviarArchivoViaSFTPMasivo(listaDocumentos);
			context.addCallbackParam("esValido", Boolean.TRUE);
			 this.nro_archivos_guardados =	this.listaDocumentos.size();
			limpiarListaArchivos();
			this.renderedFileUpload = Boolean.TRUE;
			this.nro_archivos_subidos = 0;
			
			listarArchivosRegistrado();
			this.renderedTablaDocumento = Boolean.TRUE;
			
			this.listaDocumentosRegistrados = this.expedienteDocumentoService.listFilesbyExpediente_id(this.nuevoExpediente.getExpediente_id());
			
			} else{
				context.addCallbackParam("esValido", Boolean.FALSE);
				FacesUtils.showFacesMessage("No se pudo guardar los documentos", Constante.ERROR);
			}

			} else{
				context.addCallbackParam("esValido", Boolean.FALSE);
				FacesUtils.showFacesMessage("Debe seleccionar uno o Varios Archivos", Constante.ERROR);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void cancelarCarga(){
		limpiarListaArchivos();
		this.renderedFileUpload = Boolean.TRUE;
		this.nro_archivos_subidos = 0;
	}
	
	
	public void listarArchivosRegistrado() throws Exception{
		System.out.println("PASO PASO");
		this.renderedTablaDocumento = Boolean.TRUE;
		
		this.listaDocumentosRegistrados = this.expedienteDocumentoService.listFilesbyExpediente_id(this.nuevoExpediente.getExpediente_id());
		System.out.println("listaDocumentosRegistrados " + listaDocumentosRegistrados.size());
		System.out.println("PASO PASO");
	}
	
	
	public void selecccionarDocumento(ExpedienteDocumento ed){
		this.expDocSelected = ed;
	}
	
	public void beforeUpload(){  
		System.out.println("hola"); 
	}
	
	public void setIncidencia(ExpedienteIncidencia ein){
		// Inactivo: 0
		for (ExpedienteIncidencia exin : this.listExpIncidencia) {
			if(ein == exin){
				if(exin.getValor_exp() == 0){
					exin.setValor_exp(Integer.parseInt(this.nuevoExpediente.getNro_expediente()));
					
					ein.setValor_exp(Integer.parseInt(this.nuevoExpediente.getNro_expediente()));
				} else{
					if(this.listaExpIncidenciaMarcadas.size()>0){
						this.listaExpIncidenciaMarcadas.remove(exin);
					}
					exin.setValor_exp(0);
					ein.setValor_exp(0);
				}
			}
		}

		RequestContext context = RequestContext.getCurrentInstance();
		if (ein.getValor_exp() != 0){
			System.out.println("DIFERENTE DE CERO");
			context.addCallbackParam("esValido", Boolean.TRUE);
			this.expedienteincidencia=ein;
		}else{
			System.out.println("IGUAL A CERO");
			context.addCallbackParam("esValido", Boolean.FALSE);
			//this.expedienteincidencia= new ExpedienteIncidencia();
		}
		
		
		
	}
	
	public void setIncidencia2(ExpedienteIncidencia ein){
		// Inactivo: 0
		for (ExpedienteIncidencia exin : this.listExpIncidencia) {
			if(ein == exin){
				if(exin.getValor_exp() == 0){
					exin.setValor_exp(Integer.parseInt(this.nuevoExpediente.getNro_expediente()));
					
					ein.setValor_exp(Integer.parseInt(this.nuevoExpediente.getNro_expediente()));
				} else{
					if(this.listaExpIncidenciaMarcadas.size()>0){
						this.listaExpIncidenciaMarcadas.remove(exin);
					}
					exin.setValor_exp(0);
					ein.setValor_exp(0);
				}
			}
		}

		RequestContext context = RequestContext.getCurrentInstance();
		if (ein.getValor_exp() != 0){
			System.out.println("DIFERENTE DE CERO");
			context.addCallbackParam("esValido", Boolean.TRUE);
			this.expedienteincidencia=ein;
		}else{
			System.out.println("IGUAL A CERO");
			context.addCallbackParam("esValido", Boolean.FALSE);
			//this.expedienteincidencia= new ExpedienteIncidencia();
		}
		
		
		
	}
	
	public void buscarPaginas(ExpedienteDocumento ed){
		this.expDocSelected = ed;
		try {
			this.listaPaginas = documentoPaginaService.listByIdExpDoc(expDocSelected.getId_expediente_documento());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void cerrar(){
		for (ExpedienteIncidencia exin : this.listExpIncidencia) {
			if(this.expedienteincidencia == exin){
				exin.setValor_exp(0);
				if(this.listaExpIncidenciaMarcadas.size()>0){
					this.listaExpIncidenciaMarcadas.remove(exin);
				}
			}
		}
		System.out.println("CERRANDO DIALOGOOOOO");
	}
		
	public void actualizarListaIncidencia(){
		int i = 0;
		boolean existe;
		
		/*for (ExpedienteIncidencia exin : this.listaExpIncidencia) {
			if(this.expedienteincidencia == exin){
				if(exin.getValor_exp() == 0){
					exin.setValor_exp(this.expediente.getCod_expediente());
					ein.setValor_exp(this.expediente.getCod_expediente());
				} else{
					exin.setValor_exp(0);
					ein.setValor_exp(0);
				}
			}
		}*/
		
		for (ExpedienteIncidencia exin : this.listExpIncidencia) {
			
			if (this.expedienteincidencia.getCod_expincidencia()==null){
				
				if(this.expedienteincidencia.getDes_incidencia()==exin.getDesIncidencia()){
				
					
					this.expedienteincidencia.setValor_act(1);
				this.listExpIncidencia.set(i, this.expedienteincidencia);
				
				
				}
			}else{
			
				if(this.expedienteincidencia.getCod_expincidencia() == exin.getCod_expincidencia()){
					/*if(exin.getValor_exp() == 0){
						exin.setValor_exp(this.expediente.getCod_expediente());
					} else{
						exin.setValor_exp(0);
					}*/
					
					this.expedienteincidencia.setValor_act(0);
					this.listExpIncidencia.set(i, this.expedienteincidencia);
					break;
				}
			}
			i++;
		}
		
		
		existe = this.listaExpIncidenciaMarcadas.contains(this.expedienteincidencia);
		
		if(!existe){
			if(this.expedienteincidencia.getValor_exp()!=0 ){
				this.listaExpIncidenciaMarcadas.add(this.expedienteincidencia);
				
			}
		}
		
		
	}
	
  // Eliminar Expediente
	
	public void setearEliminar(Expediente ex){
		this.expedSelected = ex;
	}
	
	public void eliminar(){
		try {
			this.expedienteService.eliminarExpediente(expedSelected.getExpediente_id());
			log.setAccion("DELETE");
            log.setDescripcion("Se eliminó el Nro Expediente : " + this.nuevoExpediente.getNro_expediente());
            logmb.insertarLog(log);
            
			buscarExpedientes();
			FacesUtils.showFacesMessage("Se eliminó correctamente el expediente nro " +expedSelected.getNro_expediente(), Constante.INFORMACION);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean isRenderedRegistro() { return renderedRegistro; 	}
	public void setRenderedRegistro(boolean renderedRegistro) {	this.renderedRegistro = renderedRegistro;}

	public boolean isRenderedFileUpload() {	return renderedFileUpload; 	}
	public void setRenderedFileUpload(boolean renderedFileUpload) {	this.renderedFileUpload = renderedFileUpload;	}

	public Expediente getNuevoExpediente() {return nuevoExpediente; }
	public void setNuevoExpediente(Expediente nuevoExpediente) {this.nuevoExpediente = nuevoExpediente; }

	public Usuario getUsuarioLogin() {	return usuarioLogin; }
	public void setUsuarioLogin(Usuario usuarioLogin) {	this.usuarioLogin = usuarioLogin; }

	public boolean isArchivoCargado() { return archivoCargado; }
	public void setArchivoCargado(boolean archivoCargado) { this.archivoCargado = archivoCargado; }

	public List<UploadedFile> getArchivoCargaList() { return archivoCargaList; }
	public void setArchivoCargaList(List<UploadedFile> archivoCargaList) {this.archivoCargaList = archivoCargaList; }

	public boolean isInitFieldsRegistro() { return initFieldsRegistro; 	}

	public void setInitFieldsRegistro(boolean initFieldsRegistro) { this.initFieldsRegistro = initFieldsRegistro;}

	public Integer getNro_archivos_subidos() { 	return nro_archivos_subidos; }

	public void setNro_archivos_subidos(Integer nro_archivos_subidos) { this.nro_archivos_subidos = nro_archivos_subidos;}

	public boolean isRenderedTablaDocumento() {	return renderedTablaDocumento;	}

	public void setRenderedTablaDocumento(boolean renderedTablaDocumento) {	this.renderedTablaDocumento = renderedTablaDocumento; }

	public List<ExpedienteDocumento> getListaDocumentosRegistrados() {return listaDocumentosRegistrados;}

	public void setListaDocumentosRegistrados(List<ExpedienteDocumento> listaDocumentosRegistrados) {this.listaDocumentosRegistrados = listaDocumentosRegistrados;}

	public List<ExpedienteDocumento> getListaDocumentosRegistradosF() { return listaDocumentosRegistradosF; }

	public void setListaDocumentosRegistradosF(	List<ExpedienteDocumento> listaDocumentosRegistradosF) { this.listaDocumentosRegistradosF = listaDocumentosRegistradosF;	}

	public ExpedienteDocumento getExpDocSelected() { return expDocSelected; }

	public void setExpDocSelected(ExpedienteDocumento expDocSelected) { this.expDocSelected = expDocSelected; }

	public Integer getNro_archivos_guardados() { return nro_archivos_guardados; }

	public void setNro_archivos_guardados(Integer nro_archivos_guardados) { this.nro_archivos_guardados = nro_archivos_guardados;}

	public List<DocumentoPagina> getListaPaginas() { return listaPaginas; }

	public void setListaPaginas(List<DocumentoPagina> listaPaginas) { this.listaPaginas = listaPaginas; }

	public String getMsj_peso_archivo() { return msj_peso_archivo; }

	public void setMsj_peso_archivo(String msj_peso_archivo) { this.msj_peso_archivo = msj_peso_archivo; }

	public Date getFecha_recepcion() { return fecha_recepcion; }

	public void setFecha_recepcion(Date fecha_recepcion) { this.fecha_recepcion = fecha_recepcion; }

	public boolean isRenderedObs() { return renderedObs; }

	public void setRenderedObs(boolean renderedObs) { this.renderedObs = renderedObs; }

	public String getMsj_exp_not_found() {return msj_exp_not_found; }

	public void setMsj_exp_not_found(String msj_exp_not_found) { this.msj_exp_not_found = msj_exp_not_found; }

	public List<Expediente> getListaExpedientes() { return listaExpedientes; }

	public void setListaExpedientes(List<Expediente> listaExpedientes) { this.listaExpedientes = listaExpedientes; 	}

	public List<Expediente> getListaExpedientesFilter() { 	return listaExpedientesFilter; }

	public void setListaExpedientesFilter(List<Expediente> listaExpedientesFilter) {this.listaExpedientesFilter = listaExpedientesFilter; }

	public Integer getF_idoficina() { return f_idoficina; }
	public void setF_idoficina(Integer f_idoficina) { this.f_idoficina = f_idoficina; 	}

	public Expediente getExpedSelected() { 	return expedSelected; }
	public void setExpedSelected(Expediente expedSelected) {this.expedSelected = expedSelected; }

	public List<Oficina> getListaOficinas() {return listaOficinas; }
	public void setListaOficinas(List<Oficina> listaOficinas) { this.listaOficinas = listaOficinas; }

	public LoginMB getLogin() { return login;}

	public void setLogin(LoginMB login) {	this.login = login; }

	public List<ExpedienteIncidencia> getListExpIncidencia() {	return listExpIncidencia; }

	public void setListExpIncidencia(List<ExpedienteIncidencia> listExpIncidencia) { this.listExpIncidencia = listExpIncidencia; }

	public boolean isBandIncidencia() {	return bandIncidencia; }

	public void setBandIncidencia(boolean bandIncidencia) {	this.bandIncidencia = bandIncidencia; }

	public List<Integer> getListEliminar() { return listEliminar; }

	public void setListEliminar(List<Integer> listEliminar) { this.listEliminar = listEliminar; }

	public List<ExpedienteIncidencia> getListExpInd() {	return listExpInd; }

	public void setListExpInd(List<ExpedienteIncidencia> listExpInd) { this.listExpInd = listExpInd; }

	public List<ExpedienteIncidencia> getListExpIndObs() { return listExpIndObs; }

	public void setListExpIndObs(List<ExpedienteIncidencia> listExpIndObs) { this.listExpIndObs = listExpIndObs; }

	public ExpedienteIncidencia getExpedienteincidencia() {	return expedienteincidencia; }

	public void setExpedienteincidencia(ExpedienteIncidencia expedienteincidencia) { this.expedienteincidencia = expedienteincidencia; }

	public String getNroexpediente() { return nroexpediente; }

	public void setNroexpediente(String nroexpediente) { this.nroexpediente = nroexpediente; }

	public String getRutaCarpeta() { return rutaCarpeta; }

	public void setRutaCarpeta(String rutaCarpeta) { this.rutaCarpeta = rutaCarpeta; }

	public String getNrosolicitud() { return nrosolicitud; }

	public void setNrosolicitud(String nrosolicitud) { this.nrosolicitud = nrosolicitud; }

	public List<IndicadorCall> getListaProceso() {
		return listaProceso;
	}

	public void setListaProceso(List<IndicadorCall> listaProceso) {
		this.listaProceso = listaProceso;
	}

	public IndicadorCallServices getIndicadorCallServices() {
		return indicadorCallServices;
	}

	public void setIndicadorCallServices(IndicadorCallServices indicadorCallServices) {
		this.indicadorCallServices = indicadorCallServices;
	}

	public List<IndicadorCall> getListaProcesoSelect() {
		return listaProcesoSelect;
	}

	public void setListaProcesoSelect(List<IndicadorCall> listaProcesoSelect) {
		this.listaProcesoSelect = listaProcesoSelect;
	}

	public String getDesProceso() {
		return desProceso;
	}

	public void setDesProceso(String desProceso) {
		this.desProceso = desProceso;
	}

	public List<IndicadorCall> getListaTipoRecepcion() {
		return listaTipoRecepcion;
	}

	public void setListaTipoRecepcion(List<IndicadorCall> listaTipoRecepcion) {
		this.listaTipoRecepcion = listaTipoRecepcion;
	}	
	
	
	
}
