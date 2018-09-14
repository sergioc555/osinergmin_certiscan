package com.certicom.certiscan.managedBeans;

import java.awt.print.PageFormat;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TreeDragDropEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.DefaultUploadedFile;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.TreeNode;
import org.primefaces.model.UploadedFile;

//import sun.org.mozilla.javascript.internal.Context;

import com.certicom.certiscan.commons.Constante;
import com.certicom.certiscan.commons.ExportarArchivo;
import com.certicom.certiscan.commons.FacesUtils;
import com.certicom.certiscan.commons.GenericBeans;
import com.certicom.certiscan.commons.Utiles;
import com.certicom.certiscan.domain.DocumentoPagina;
import com.certicom.certiscan.domain.Expediente;
import com.certicom.certiscan.domain.ExpedienteDocumento;
import com.certicom.certiscan.domain.ExpedienteTracking;
import com.certicom.certiscan.domain.Log;
import com.certicom.certiscan.domain.Menu;
import com.certicom.certiscan.domain.Oficina;
import com.certicom.certiscan.domain.Usuario;
import com.certicom.certiscan.services.ExpedienteDocumentoService;
import com.certicom.certiscan.services.ExpedienteService;
import com.certicom.certiscan.services.ExpedienteTrackingService;
import com.certicom.certiscan.services.MenuServices;
import com.certicom.certiscan.services.OficinaService;
import com.google.common.collect.Iterables;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfCopy;
import com.lowagie.text.pdf.PdfDocument;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfPage;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfSmartCopy;
import com.lowagie.text.pdf.PdfStamper;
import com.lowagie.text.pdf.PdfWriter;
import com.sun.xml.internal.ws.client.ResponseContext;

import src.com.certicom.certiscan.utils.ResultBean;
import src.com.certicom.certiscan.utils.Utils;

@ManagedBean(name="controlCalidad")
@ViewScoped
public class ControlCalidadMB extends GenericBeans implements Serializable {
	
	private boolean renderedRegistro;
	private boolean renderedFileUpload;
	private boolean archivoCargado;
	private boolean renderedBtnSave;
	private boolean initFieldsRegistro;
	private boolean renderedTablaDocumento;
	private boolean renderedPnlDescargar;
	private ExpedienteDocumento expDocG;
	private boolean renderedTextoInformativo;
	
	private boolean renderedTree;
	private boolean renderedTreeGroup;
	
	
	private boolean renderedBtnAgrup;
	private boolean renderedPnlGroup;
	private boolean renderedPnlBusqueda;
	private boolean txtNumeroPagina;
	private boolean txtNumeroPaginaAgr;
	private boolean txtNumeroPaginaDesAgr;
	private Integer valorOpcion;
	private String nameFileG;
	private Expediente selectedExpediente;
	
	private boolean renderedPgCerrar;
	private StreamedContent fileExp;
	private boolean existeG;
	
	private boolean btnAnterior;
	private boolean btnSiguiente;
	
	private boolean btnAnteriorAgr;
	private boolean btnSiguienteAgr;
	private boolean btnAnteriorDesAgr;
	private boolean btnSiguienteDesAgr;
	
	private String nombreArchivo;
	
	private String nombreArchivoAgr;
	private String nombreArchivoDesAgr;
	
	private Integer numero_documento;
	private Integer numero_documentoAgr;
	private Integer numero_documentoDesAgr;
	
	private Log log;
	private LogMB logmb;
	
	private Integer nro_archivos_selected = 0;
	private List<ExpedienteDocumento> listArchivosSelected;
	private List<ExpedienteDocumento> listArchivosHijos;
	
	private String ruta_completa;
	private byte[] datafinal;
	
	private Integer nro_archivos_subidos = 0;
	private String linkPdf = "";
	private boolean renderedFrame;
	
	private File fileReemplazo = null;
	private Integer nro_pagina_actual;
	
	private List<UploadedFile> archivoCargaList = new LinkedList<UploadedFile>();
	private List<ExpedienteDocumento> listaDocumentos = new ArrayList<>();
	private List<ExpedienteDocumento> listaDocumentosRegistrados = new ArrayList<>();
	private List<ExpedienteDocumento> listaDocumentosRegistradosF;
	
	private List<ExpedienteDocumento> listaDocumentosAgrupados = new ArrayList<>();
	
	private Expediente nuevoExpediente;
	private ExpedienteDocumento expDocumentoSelected;
	private ExpedienteDocumento expDocumentoReemplazo;
	
	private ExpedienteService expedienteService;
	private ExpedienteDocumentoService expedienteDocumentoService;
	private ExpedienteTrackingService expedienteTrackingService;
	private OficinaService oficinaService;
	private MenuServices menuServices;
	
	@ManagedProperty(value = "#{loginMB.usuario}")
	private Usuario usuarioLogin;
	private String nuevoNombre;
	
	private TreeNode root;
	private TreeNode nodoSelect;
	private TreeNode[] nodo_archivosSelected;
//	private list nodo_archivosSelected;
	
	private String nombreArchivoAgrupado ="";
	
	private Expediente expedienteSelected = new Expediente();
	private Integer _id_Expediente = 0;
	private String estado_exp_busqueda ="";
	
	private Integer _totalxAgrupar = 0; 
	private Integer _totalAgrupadas = 0; 
	
	private Integer _totalDigitalizados = 0;
	private Integer _totalDigitalizadosReempl = 0;
	private Integer _totalDigitalizadosElim = 0;
	
	
	private ExpedienteDocumento selectedExpDocHijo;
	private ExpedienteDocumento selectedExpDocGropuped;
	private boolean renderedVisorGrouped;
	private boolean renderedVisorHijo;
	
	private String tipOperacion_NewFile ="";
	
	private String msjeExpNoPCC;
	
	
	private List<Oficina> listaOficinas;
	
	// Filtros
	private Integer f_idoficina;
	
	private List<Expediente> listaExpedientes;
	private List<Expediente> listaExpedientesFilter;
	
	private Expediente expedSelected;
	
	private boolean renderedSelectAllCheckBox;
	
	private boolean renderedNuevoCancelar;
	private ResultBean resultMessage = new ResultBean();
	
	private String ipcertiscan ;
	
	@PostConstruct
	public void inicio(){
		this.ipcertiscan = Constante.IP_certiscan;
		
		this.expedienteService = new ExpedienteService();
		this.expedienteDocumentoService = new ExpedienteDocumentoService();
		this.expedienteTrackingService = new ExpedienteTrackingService();
		this.oficinaService = new OficinaService();
		
		this.nuevoExpediente = new Expediente();
		this.expDocumentoSelected = new ExpedienteDocumento();
		
		this.renderedRegistro = Boolean.FALSE;
		this.initFieldsRegistro = Boolean.TRUE;
		this.renderedFileUpload = Boolean.FALSE;
		
		this.renderedTablaDocumento = Boolean.FALSE;
		this.renderedFrame = Boolean.FALSE;
		this.renderedBtnSave = Boolean.TRUE;
		
		this.renderedTree = Boolean.TRUE;
		this.renderedTreeGroup = Boolean.FALSE;
		
		this.renderedBtnAgrup = Boolean.FALSE;
		this.renderedPnlGroup = Boolean.FALSE;
		
		this.renderedPnlBusqueda = Boolean.TRUE;
		this.renderedPnlDescargar = Boolean.TRUE;
		this.renderedVisorGrouped = Boolean.FALSE;
		
		this.renderedPgCerrar = Boolean.FALSE;
		this.menuServices=new MenuServices();
		this.renderedTextoInformativo = Boolean.FALSE;
		
		this.renderedSelectAllCheckBox = Boolean.FALSE;
		
		//RequestContext context = RequestContext.getCurrentInstance();
		
		//context.addCallbackParam("valorOpcion", this.valorOpcion);
		
		RequestContext.getCurrentInstance().execute("inicia();");
		
		renderedNuevoCancelar = Boolean.FALSE;
		
		this.btnAnterior = Boolean.TRUE;
		this.btnSiguiente = Boolean.TRUE;
		this.txtNumeroPagina = Boolean.TRUE;
		
		initCombos();
		
		log = (Log)getSpringBean(Constante.SESSION_LOG);
		logmb = new LogMB();
		
		//int codMenu;
		try {
			//codMenu = menuServices.opcionMenuByPrettyCod("pretty:controlcalidad");
			//log.setCod_menu(codMenu);
			Menu codMenu = menuServices.opcionMenuByPretty("pretty:controlCalidad");
			log.setCod_menu(codMenu.getCod_menu().intValue());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	private void initCombos(){
		try {
			
			System.out.println("ID OFICINA: "+usuarioLogin.getId_oficina());
			this.f_idoficina = usuarioLogin.getId_oficina();
			this.listaOficinas = oficinaService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void nuevaBusqueda(String tb){
		System.out.println("Limpio la busqueda 1");
		this.nuevoExpediente.setNro_expediente("");
		this.nuevoExpediente.setNro_solicitud("");
		
	    
	    this.renderedPnlBusqueda = Boolean.FALSE;
	    
	    if(tb.equals("NB")){
	    	renderedNuevoCancelar = Boolean.TRUE;
	        renderedPnlDescargar = Boolean.TRUE;
	    } else{
	    	renderedNuevoCancelar = Boolean.FALSE;
	    	renderedPnlDescargar = Boolean.TRUE;
	    }
	    
	    resetValuesInit();
	    System.out.println("Limpio la busqueda 8");
	}
	
	private void resetValuesInit(){
		
		resetGroupValues();
		System.out.println("Limpio la busqueda 2");
		//limpiarTreeLista();
		resetRootPrincipalTree();
		System.out.println("Limpio la busqueda 3");
		resetTreeValues();
		System.out.println("Limpio la busqueda 4");
		resetValuesInfoEstado();
		System.out.println("Limpio la busqueda 5");
		resetValuesPrincipalVisor();
		System.out.println("Limpio la busqueda 6");
		resetBotonesNavegacion();
		System.out.println("Limpio la busqueda 7");
	}
	
	private void resetValuesInfoEstado(){
		this.estado_exp_busqueda = "";
		this.listaDocumentosRegistrados = new ArrayList<>();
		this.listaDocumentosAgrupados = new ArrayList<>();
		this.renderedTextoInformativo = Boolean.FALSE;
		this._totalAgrupadas = 0;
		this._totalxAgrupar = 0;
		this._totalDigitalizados = 0;
		this._totalDigitalizadosElim = 0;
		this._totalDigitalizadosReempl = 0;
		this.renderedPgCerrar = Boolean.FALSE;
		this.nombreArchivo = "";
	}
	
	private void resetBotonesNavegacion(){
		this.btnAnterior = Boolean.TRUE;
		this.btnSiguiente = Boolean.TRUE;
		this.numero_documento = null;
		this.txtNumeroPagina = Boolean.TRUE;
	}
	
	private void resetBotonesNavegacionAgr(){
		
		this.btnAnteriorAgr = Boolean.TRUE;
		this.btnSiguienteAgr = Boolean.TRUE;
		this.numero_documentoAgr = null;
		//this.txtNumeroPaginaAgr = Boolean.TRUE;
		this.nombreArchivoAgr = "";
	}
	
	private void resetBotonesNavegacionDesAgr(){
		
		this.btnAnteriorDesAgr = Boolean.TRUE;
		this.btnSiguienteDesAgr = Boolean.TRUE;
		this.numero_documentoDesAgr = null;
		//this.txtNumeroPaginaDesAgr = Boolean.TRUE;
		this.nombreArchivoDesAgr = "";
	}
	
	private void resetValuesPrincipalVisor(){
		this.renderedFrame = Boolean.FALSE;
	}
	
	private void resetGroupValues(){
	// Panel Group
		this.renderedPnlGroup = Boolean.FALSE;
	// soloa activar el botin agrupar
		this.renderedBtnAgrup = Boolean.FALSE;
	}
	
	private void resetTreeValues(){
		this.renderedTree = Boolean.TRUE;
		this.renderedTreeGroup = Boolean.FALSE;
	}
	
	private void resetRootPrincipalTree(){
		this.root = new DefaultTreeNode("Root",null);
	}
	
	
	public void activarNuevoRegistro(){
		initFieldsRegistro =  Boolean.FALSE;
	}
	
	private void limpiarExpediente(){
		this.nuevoExpediente = new Expediente();
	}
	
	private void limpiarListaArchivos(){
		this.archivoCargaList = new LinkedList<UploadedFile>();
		this.listaDocumentos = new ArrayList<>();
	}
	
	public void descargarArchivos(){
		try {
			//this.selectedExpediente = exp;
			List<ExpedienteDocumento> listExpDoc = new ArrayList<ExpedienteDocumento>();
			
			this.selectedExpediente = this.expedienteService.findByNroExp(this.nuevoExpediente.getNro_expediente());
			
			if (!(this.selectedExpediente == null)){
			
				//listExpDoc = this.expedienteDocumentoService.listFilesbyExpediente_id(this.selectedExpediente.getExpediente_id());
				
				listExpDoc = this.expedienteDocumentoService.listFilesGroupedByExpediente_id(this.selectedExpediente.getExpediente_id());
				
				this.fileExp = crearMultipleZipArchivos2(listExpDoc);
			
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public void onDragDrop2(TreeDragDropEvent event) {
        TreeNode dragNode = event.getDragNode();
        TreeNode dropNode = event.getDropNode();
        int dropIndex = event.getDropIndex();
        
        
         
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Dragged " + dragNode.getData(), "Dropped on " + dropNode.getData() + " at " + dropIndex);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
	
	public void onDragDrop(TreeDragDropEvent event) {
        TreeNode dragNode = event.getDragNode();
        TreeNode dropNode = event.getDropNode();
        int dropIndex = event.getDropIndex();        
        
        try {
        	
        	ExpedienteDocumento exDrag = (ExpedienteDocumento) dragNode.getData();
            
            System.out.println("DRAG NOMBRE: "+exDrag.getNombre_archivo());
           
            //ExpedienteDocumento exDrop = (ExpedienteDocumento) dropNode.getData();
            
            ExpedienteDocumento exDrop = this.listaDocumentosRegistrados.get(dropIndex);
        	
            if(exDrag.getOrden_expediente()>exDrop.getOrden_expediente()){
            
				this.expedienteDocumentoService.updateCorrelativoArchivosSinExpDrag(exDrag.getExpediente_id(), exDrop.getOrden_expediente(), exDrag.getId_expediente_documento());
				
				this.expedienteDocumentoService.updateCorrelativoArchivosExpDrag(exDrag.getExpediente_id(), exDrop.getOrden_expediente(), exDrag.getId_expediente_documento());
			
            }else{
            	
            	System.out.println("EXPEDIENTE ID: "+exDrag.getExpediente_id());
            	
            	System.out.println("DRAG ORDEN: "+exDrag.getOrden_expediente());
            	
            	System.out.println("EXPEDIENTE DOCUMENTO: "+exDrag.getId_expediente_documento());
            	
            	System.out.println("DROP ORDEN: "+exDrop.getOrden_expediente());
            	
            	this.expedienteDocumentoService.updateCorrelativoArchivosRango(exDrag.getExpediente_id(), exDrag.getOrden_expediente(), exDrop.getOrden_expediente());
            	           	
            	this.expedienteDocumentoService.updateCorrelativoArchivosExpDrag(exDrag.getExpediente_id(), exDrop.getOrden_expediente(), exDrag.getId_expediente_documento());
            	            	
            }
			
			System.out.println("PRINCIPAL DROP NOMBRE: "+dropNode.getData());
	        
	        System.out.println("DROP NOMBRE: "+exDrop.getNombre_archivo());
	        
	        
	        
	        listarArchivosRegistrado();
        
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}        
         
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Dragged " + dragNode.getData(), "Dropped on " + dropNode.getData() + " at " + dropIndex);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
	
	public void descargarArchivos2(Expediente exp){
		try {
			this.selectedExpediente = exp;
			List<ExpedienteDocumento> listExpDoc = new ArrayList<ExpedienteDocumento>();
			
			if (!(this.selectedExpediente == null)){
				
				if(this.selectedExpediente.getId_estado() == 4){
				
					listExpDoc = this.expedienteDocumentoService.listFilesbyExpediente_id(this.selectedExpediente.getExpediente_id());
					
					this.fileExp = crearMultipleZipArchivos(listExpDoc);
				
				}
				
				if(this.selectedExpediente.getId_estado() == 5){
					
					listExpDoc = this.expedienteDocumentoService.listFilesbyExpediente_id(this.selectedExpediente.getExpediente_id());
					
					listExpDoc = this.expedienteDocumentoService.listFilesGroupedByExpediente_id(this.selectedExpediente.getExpediente_id());
					
					this.fileExp = crearMultipleZipArchivos2(listExpDoc);
					
				}				
			
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public StreamedContent crearMultipleZipArchivos(List<ExpedienteDocumento> lstcr){
		StreamedContent fil = null;
		byte[] buffer = new byte[1024];
		try {
			
			List<ByteArrayOutputStream> zipList = new ArrayList<ByteArrayOutputStream>();
			
			for(ExpedienteDocumento ed: lstcr){
			
			File _archivo = File.createTempFile(ed.getNombre_archivo().substring(0, ed.getNombre_archivo().length()-4), ".pdf");
			FileOutputStream fos = new FileOutputStream(_archivo);
			BufferedOutputStream bs = null;
			bs = new BufferedOutputStream(fos);
		    //bs.write(Utiles.descargarArchivoViaSFTP(Constante.RUTA_ARCHIVO+ed.getNombre_archivo(), ed.getNombre_archivo()));
			System.out.println("CARPETA: "+this.selectedExpediente.getRuta_carpeta());
			bs.write(Utiles.descargarArchivoViaSFTP(this.selectedExpediente.getRuta_carpeta()+"/", ed.getNombre_archivo()));
		    bs.close();
		    //bs = null;
				
			//byte[] data = Utiles.descargarArchivoViaSFTP(Constante.RUTA_ARCHIVO+ed.getNombre_archivo(), ed.getNombre_archivo());
		    
		    //PdfWriter writer = PdfWriter.getInstance(document, fos);
		      
			ByteArrayOutputStream zip = new ByteArrayOutputStream();	
			ZipOutputStream zos = new ZipOutputStream(zip);
		
			      
			      ByteArrayOutputStream baos = new ByteArrayOutputStream();
			      FileInputStream f = new FileInputStream(_archivo);
			      //FileInputStream f = null;
			      
			      //f.read(data);
			      //f.
			      
			      ByteArrayOutputStream bos = new ByteArrayOutputStream();
			        byte[] buf = new byte[1024];
			        try {
			            for (int readNum; (readNum = f.read(buf)) != -1;) {
			        	//for (int readNum; (readNum = f.read(data)) != -1;) {
			                bos.write(buf, 0, readNum); //no doubt here is 0
			                //Writes len bytes from the specified byte array starting at offset off to this byte array output stream.
			                System.out.println("read " + readNum + " bytes,");
			            }
			        } catch (IOException ex) {
			            
			        }			      
			      
				ZipEntry ze = new ZipEntry(ed.getNombre_archivo());
				
				zos.putNextEntry(ze);
				
				InputStream in = new ByteArrayInputStream(bos.toByteArray());
				
				int len;
				
				while ((len = in.read(buffer)) > 0) {
					zos.write(buffer, 0, len);
				}

				in.close();
				zos.closeEntry();
				zipList.add(zip);
				//i++;
			
			zos.close();			

				
			}
			
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			ZipOutputStream zos = new ZipOutputStream(out);

			int j = 1, ni = 0;
			for (ByteArrayOutputStream zip : zipList) {				
				//ZipEntry ze = new ZipEntry("ruc_" + j + "_zip.zip");
				System.out.println("NOMBRE SIN ZIP: "+lstcr.get(ni).getNombre_archivo().substring(0, lstcr.get(ni).getNombre_archivo().length()-4));
				ZipEntry ze = new ZipEntry("" + lstcr.get(ni).getNombre_archivo().substring(0, lstcr.get(ni).getNombre_archivo().length()-4) + ".zip");
				zos.putNextEntry(ze);
				InputStream in = new ByteArrayInputStream(zip.toByteArray());
				int len;
				while ((len = in.read(buffer)) > 0) {
					zos.write(buffer, 0, len);
				}

				in.close();
				zos.closeEntry();
				j++;
				ni++;
			}

			zos.close();
				
			return fil = new DefaultStreamedContent(new ByteArrayInputStream(out.toByteArray()), "application/zip", ""+this.selectedExpediente.getNro_expediente()+".zip");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			return fil;
		}		
	    
	}
	
	
	public StreamedContent crearMultipleZipArchivos2(List<ExpedienteDocumento> lstcr){
		StreamedContent fil = null;
		byte[] buffer = new byte[1024];
		try {
			
			List<ByteArrayOutputStream> zipList = new ArrayList<ByteArrayOutputStream>();
			
			for(ExpedienteDocumento ed: lstcr){
			System.out.println("RUTA PREVIA: "+ed.getNombre_archivo());
			System.out.println("TAMA�O TOTAL: "+ed.getNombre_archivo().length());
			File _archivo = File.createTempFile("DOCUMENTO", ".pdf");
			//File _archivo = null;
			/*if (ed.getNombre_archivo().equals("E1.pdf")){
				_archivo = File.createTempFile("E12", ".pdf");
			}else{
				_archivo = File.createTempFile(ed.getNombre_archivo().substring(0, ed.getNombre_archivo().length()-4), ".pdf");
			}*/
			FileOutputStream fos = new FileOutputStream(_archivo);
			BufferedOutputStream bs = null;
			bs = new BufferedOutputStream(fos);
		    //bs.write(Utiles.descargarArchivoViaSFTP(Constante.RUTA_ARCHIVO+ed.getNombre_archivo(), ed.getNombre_archivo()));
			System.out.println("CARPETA: "+this.selectedExpediente.getRuta_carpeta());
			bs.write(Utiles.descargarArchivoViaSFTP(this.selectedExpediente.getRuta_carpeta()+"/", ed.getNombre_archivo()));
		    bs.close();
		    //bs = null;
				
			//byte[] data = Utiles.descargarArchivoViaSFTP(Constante.RUTA_ARCHIVO+ed.getNombre_archivo(), ed.getNombre_archivo());
		    
		    //PdfWriter writer = PdfWriter.getInstance(document, fos);
		      
			ByteArrayOutputStream zip = new ByteArrayOutputStream();	
			//ZipOutputStream zos = new ZipOutputStream(zip);
		
			      
			      ByteArrayOutputStream baos = new ByteArrayOutputStream();
			      FileInputStream f = new FileInputStream(_archivo);
			      //FileInputStream f = null;
			      
			      //f.read(data);
			      //f.
			      
			      ByteArrayOutputStream bos = new ByteArrayOutputStream();
			        byte[] buf = new byte[1024];
			        try {
			            for (int readNum; (readNum = f.read(buf)) != -1;) {
			        	//for (int readNum; (readNum = f.read(data)) != -1;) {
			                bos.write(buf, 0, readNum); //no doubt here is 0
			                //Writes len bytes from the specified byte array starting at offset off to this byte array output stream.
			                //System.out.println("read " + readNum + " bytes,");
			            }
			        } catch (IOException ex) {
			            
			        }			      
			      
				/*ZipEntry ze = new ZipEntry(ed.getNombre_archivo());
				
				//zos.putNextEntry(ze);
				
				InputStream in = new ByteArrayInputStream(bos.toByteArray());
				
				int len;
				
				while ((len = in.read(buffer)) > 0) {
					zip.write(buffer, 0, len);
				}

				in.close();*/
				//zos.closeEntry();
				//zipList.add(zip);
				//i++;
			        ed.setBaos(bos);
			//zos.close();			

				
			}
			
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			ZipOutputStream zos = new ZipOutputStream(out);
			byte[] buf = new byte[1024];
			int j = 1, ni = 0;
			for(ExpedienteDocumento ed: lstcr){			
				//ZipEntry ze = new ZipEntry("ruc_" + j + "_zip.zip");
				System.out.println("NOMBRE SIN ZIP: "+lstcr.get(ni).getNombre_archivo().substring(0, lstcr.get(ni).getNombre_archivo().length()-4));
				ZipEntry ze = new ZipEntry("" + lstcr.get(ni).getNombre_archivo().substring(0, lstcr.get(ni).getNombre_archivo().length()-4) + ".pdf");
				zos.putNextEntry(ze);
				InputStream in = new ByteArrayInputStream(ed.getBaos().toByteArray());
				int len;
				while ((len = in.read(buf)) > 0) {
					zos.write(buf, 0, len);
				}

				in.close();
				//out.close()
				zipList.add(out);
				j++;
				ni++;
			}

			zos.close();
				
			return fil = new DefaultStreamedContent(new ByteArrayInputStream(out.toByteArray()), "application/zip", ""+this.selectedExpediente.getNro_expediente()+".zip");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			return fil;
		}		
	    
	}
	
	public void listarArchivosRegistrado() throws Exception{
		RequestContext context = RequestContext.getCurrentInstance();
		this.renderedTablaDocumento = Boolean.TRUE;
				
		
		//Expediente ex = this.expedienteService.findByNroExpedientePrepToCalidad(this.nuevoExpediente.getNro_expediente());
		
		Expediente ex = this.expedienteService.findByNroExpedienteNroSolicitudPrepToCalidad(this.nuevoExpediente.getNro_expediente(), this.nuevoExpediente.getNro_solicitud());
		
		if(ex != null){
			if(ex.getId_estado() == Constante.EST_PREPARADO_PARA_CC){
				
			this.expedienteSelected = ex;
			this._id_Expediente = ex.getExpediente_id();
			
			this.estado_exp_busqueda = ex.getDes_estado();

		//	this.listaDocumentosRegistrados = this.expedienteDocumentoService.listFilesbyExpediente_id(ex.getExpediente_id());
		//	generateTree(listaDocumentosRegistrados);
			listarDocumentosCantidades(ex.getExpediente_id());
			
			this.renderedPnlGroup = Boolean.TRUE;
			this.renderedPnlBusqueda = Boolean.TRUE;
			this.txtNumeroPagina = Boolean.FALSE;
			this.renderedPnlDescargar = Boolean.FALSE;
			context.update("downFile");
			} else{
				context.update("dlgDetAvisoExp");
				context.execute("PF('dlgAvisoExp').show()");
				this.msjeExpNoPCC ="Nro :" + ex.getNro_expediente() +"- Estado Actual: " + ex.getDes_estado();
				this.renderedPnlGroup = Boolean.FALSE;
				this.renderedPnlBusqueda = Boolean.FALSE;
				this.renderedPnlDescargar = Boolean.FALSE;
				context.update("downFile");
			}
			
		} else{
			context.execute("PF('dlgAviso').show()");
			this.renderedPnlBusqueda = Boolean.FALSE;
			this.renderedPnlDescargar = Boolean.FALSE;
			context.update("downFile");
		}
		
		
	}
	
	private  void listarDocumentosCantidades(Integer _expediente_id) throws Exception{
		this._totalxAgrupar = 0;
		this._totalAgrupadas= 0;
		
		this._totalDigitalizados = this.expedienteDocumentoService.listFilesDigitalizados(_expediente_id);
		this._totalDigitalizadosElim = this.expedienteDocumentoService.listFilesDigitalizadosEliminados(_expediente_id);
		this._totalDigitalizadosReempl = this.expedienteDocumentoService.listFilesDigitalizadosReempl(_expediente_id);
		
		this.listaDocumentosRegistrados = this.expedienteDocumentoService.listFilesbyExpediente_id(_expediente_id);
		this.listaDocumentosAgrupados =  this.expedienteDocumentoService.listFilesGroupedByExpediente_id(_expediente_id);
		
		/*Collections.sort(this.listaDocumentosRegistrados, new Comparator<ExpedienteDocumento>() {
			@Override
			public int compare(ExpedienteDocumento o1, ExpedienteDocumento o2) {
				// TODO Auto-generated method stub
				return new String(o1.getNombre_archivo()).compareTo(new String(o2.getNombre_archivo()));
			}
		});*/
		
		generateTree(listaDocumentosRegistrados);
		
		for (ExpedienteDocumento ed : listaDocumentosRegistrados) {
			if(!ed.getEstado_accion().equals(Constante.EST_ACC_AGRUPADO)){
				System.out.println("entro 1");
				this._totalxAgrupar =this._totalxAgrupar+1;
			} else{
			//	this._totalAgrupadas =this._totalAgrupadas+1;
			}
		}
		_totalAgrupadas =  this.listaDocumentosAgrupados.size();
		validarCerrarExpediente();
		
	}
	
	
	private void validarCerrarExpediente(){
		 if(this._totalxAgrupar >0){
			 this.renderedPgCerrar = Boolean.FALSE;
		 } else{
			 this.renderedPgCerrar = Boolean.TRUE;
		 }
	}
	
	private void limpiarListas(){
		this.listArchivosSelected = new ArrayList<>();
		this.listaDocumentosAgrupados = new ArrayList<>();
	}
	private void initBooleanValues(){
		this.renderedPgCerrar = Boolean.FALSE;
		this.renderedFrame = Boolean.FALSE;
	}
	
	private void initStringValues(){
		this.estado_exp_busqueda = "";
	}
	
	
	
	private void generateTree(List<ExpedienteDocumento> _listaExD){
		this.root = new DefaultTreeNode("Root",null);
		for (ExpedienteDocumento ed : _listaExD) {
			//new DefaultTreeNode(ed.getId_expediente_documento()+"-"+ed.getNombre_archivo(), this.root);
			new DefaultTreeNode(ed, this.root);
		}
	}
	
	public void documentoSeleccionado(SelectEvent event){
		ExpedienteDocumento expdoc= (ExpedienteDocumento) event.getObject();
		System.out.println("RUTA: "+expdoc.getRuta());
		
		RequestContext contextreq = RequestContext.getCurrentInstance();  
		
		ResponseStream rs = null;
		
		//ruta_completa = Constante.RUT;
		byte[] data = null;
		InputStream is = null;
		   
		   ByteArrayOutputStream osx=new ByteArrayOutputStream();
		
		String HOST = Constante.HOST_SCPF;
	    Integer PUERTO = Constante.PUERTO_SCPF;
	    String USUARIO = Constante.USUARIO_SCPF;
	    String PASSWORD = Constante.PASSWORD_SCPF;
	   
	    String RUTA = expdoc.getRuta();
	    Session miSesion = null;
	    Channel miCanal = null;
	    ChannelSftp canalSFTP = null;
	    
	    try{
	    
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
            //canalSFTP.cd(RUTA);//exploroe el directorio remoto
            //this.rutaImagen=RUTA+archImg.getName();
            //canalSFTP.put(new FileInputStream(archImg),archImg.getName());
            
            System.out.println("RUTA: "+RUTA);
            //System.out.println("RUTA: "+miCanal.);
            
            is=canalSFTP.get(RUTA);
            
            try {
                byte[] buffer = new byte[256];
                int count;
                while (-1 != (count = is.read(buffer))) {
                	osx.write(buffer, 0, count);
                }
            } finally {
            	data = osx.toByteArray();
            	
            	is.read(data);
                is.close();
            }
            
        }catch(Exception g){
            System.out.println("Transferencia Fallida");
            g.printStackTrace();
        }finally {
        	System.out.println("Termino de leer");
            // Cerramos el canal y session
            if (canalSFTP.isConnected())
                    canalSFTP.disconnect();
            if (miSesion.isConnected())
                    miSesion.disconnect();
        }
	   
	    

	   is.read(data);
	   is.close();
	    
	   
	   this.datafinal = data;
	   FacesContext.getCurrentInstance().getExternalContext().getFlash().put("img01", data);
	   rs.write(data);
	   FacesContext.getCurrentInstance().setResponseStream(rs);
	   
	   
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	    
	   // escribiendo el contenido d ela imagen  a la respuesta.
	   //response.getOutputStream().write(data);		
	}
	
	public void abrirVisor(ExpedienteDocumento ed){
		this.renderedFrame = Boolean.TRUE;
		this.expDocumentoSelected = ed;
		System.out.println("entro "+expDocumentoSelected.getId_expediente_documento());
		System.out.println("entro");
	}
	
	public void anterior(){ 
		
		//Boolean valido=Boolean.TRUE;
		//int valor = 1;
		//RequestContext context = RequestContext.getCurrentInstance();  
   	    //context.addCallbackParam("esValido", valido);
		//context.addCallbackParam("esValido", valor);
   	    
		int i = 0;
		System.out.println("anterior()");
		if(this.expDocumentoSelected == null){
			System.out.println("No existe documento");
		}else{
			i=this.listaDocumentosRegistrados.indexOf(this.expDocumentoSelected);
			
			//if (i>0){
			
				
				if(this.numero_documento == 1){
					this.btnSiguiente = false;
					this.btnAnterior = true;
					
			//}else{
				}else{
					this.expDocumentoSelected=(ExpedienteDocumento) this.listaDocumentosRegistrados.get(i-1);
					i=this.listaDocumentosRegistrados.indexOf(this.expDocumentoSelected);
					System.out.println("Indice: "+i);
					this.numero_documento = i+1;
					this.btnSiguiente = false;
				}
			//}
			
			
		}
		
		this.renderedTextoInformativo = Boolean.TRUE;
		this.nombreArchivo = this.expDocumentoSelected.getNombre_archivo();
		
		//if(this.root.)
		
		this.nodoSelect = new DefaultTreeNode(this.expDocumentoSelected);
		
		Integer ij = root.getChildren().indexOf(this.nodoSelect);
		this.nodoSelect.setSelected(true);
		
		for (TreeNode n : root.getChildren()) {
			
			n.setSelected(false);
			
		}
		
		root.getChildren().set(ij, this.nodoSelect);
		
		
		//this.nodoSelect.setSelected(true);
		
	}
	
	public void anteriorAgr(){ 
		int i = 0;
		
		if(this.selectedExpDocGropuped == null){
			this.selectedExpDocGropuped=listArchivosSelected.get(0);
			this.numero_documentoAgr = 0+1;
			this.btnAnteriorAgr = true;
			this.btnSiguienteAgr = false;
			System.out.println("No existe documento");
			
		}else{
			i=this.listArchivosSelected.indexOf(this.selectedExpDocGropuped);			
			//if (i>0){			
				
				if(this.numero_documentoAgr == 1){
					this.btnSiguienteAgr = false;
					this.btnAnteriorAgr = true;
					
			//}else{
				}else{
					this.selectedExpDocGropuped=(ExpedienteDocumento) this.listArchivosSelected.get(i-1);
					i=this.listArchivosSelected.indexOf(this.selectedExpDocGropuped);
					System.out.println("Indice: "+i);
					this.numero_documentoAgr = i+1;
					this.btnSiguienteAgr = false;
				}
			//}					
		}
		
		this.renderedTextoInformativo = Boolean.TRUE;
		this.nombreArchivoAgr = this.selectedExpDocGropuped.getNombre_archivo();
		
		//if(this.root.)		
		//this.nodoSelect = new DefaultTreeNode(this.expDocumentoSelected,root);		
		//this.nodoSelect.setSelected(true);
		
	}
	
	public void anteriorDesAgr(){ 
		int i = 0;
		
		if(this.selectedExpDocHijo == null){
			this.selectedExpDocHijo=listArchivosHijos.get(0);
			this.numero_documentoDesAgr = 0+1;
			this.btnAnteriorDesAgr = true;
			this.btnSiguienteDesAgr = false;
			System.out.println("No existe documento");
			
		}else{
			i=this.listArchivosHijos.indexOf(this.selectedExpDocHijo);			
			//if (i>0){			
				
				if(this.numero_documentoDesAgr == 1){
					this.btnSiguienteDesAgr = false;
					this.btnAnteriorDesAgr = true;
					
			//}else{
				}else{
					this.selectedExpDocHijo=(ExpedienteDocumento) this.listArchivosHijos.get(i-1);
					i=this.listArchivosHijos.indexOf(this.selectedExpDocHijo);
					System.out.println("Indice: "+i);
					this.numero_documentoDesAgr = i+1;
					this.btnSiguienteDesAgr = false;
				}
			//}					
		}
		
		this.renderedTextoInformativo = Boolean.TRUE;
		this.nombreArchivoDesAgr = this.selectedExpDocHijo.getNombre_archivo();
		
		//if(this.root.)		
		//this.nodoSelect = new DefaultTreeNode(this.expDocumentoSelected,root);		
		//this.nodoSelect.setSelected(true);
		
	}
	
	public void siguiente(){
		int i = 0;
		
		System.out.println("siguiente()");
		if(this.expDocumentoSelected == null){
			System.out.println("No existe documento");
		}else{
			i=this.listaDocumentosRegistrados.indexOf(this.expDocumentoSelected);
			
			//if (i<(this.listaDocumentosRegistrados.size())){
			
				
			/*}else{
				this.btnSiguiente = true;
			}*/
				
				if(i==(this.listaDocumentosRegistrados.size()-1)){
					this.btnSiguiente = true;
					this.btnAnterior = false;
				}else{
					this.expDocumentoSelected=(ExpedienteDocumento) this.listaDocumentosRegistrados.get(i+1);
					i=this.listaDocumentosRegistrados.indexOf(this.expDocumentoSelected);
					System.out.println("Indice: "+i);
					this.numero_documento = i+1;
					if(i==(this.listaDocumentosRegistrados.size()-1)){
						this.btnSiguiente = true;
					}
					this.btnAnterior = false;
				}
		}
		//this.nodoSelect = new DefaultTreeNode(this.expDocumentoSelected,root);
		//this.nodoSelect.setSelected(true);
		this.renderedTextoInformativo = Boolean.TRUE;
		
		this.nombreArchivo = this.expDocumentoSelected.getNombre_archivo();
		
		this.nodoSelect = new DefaultTreeNode(this.expDocumentoSelected);
		
		Integer ij = root.getChildren().indexOf(this.nodoSelect);
		this.nodoSelect.setSelected(true);
		
		for (TreeNode n : root.getChildren()) {
			
			n.setSelected(false);
			
		}
		
		root.getChildren().set(ij, this.nodoSelect);
	}
	
	public void siguienteAgr(){
		int i = 0;
		if(this.selectedExpDocGropuped == null){
			
			this.selectedExpDocGropuped=listArchivosSelected.get(0);
			this.numero_documentoAgr = 0+1;
			this.btnAnteriorAgr = true;
			this.btnSiguienteAgr = false;
			//System.out.println("No existe documento");
		}else{
			i=this.listArchivosSelected.indexOf(this.selectedExpDocGropuped);
			
			//if (i<(this.listaDocumentosRegistrados.size())){
			
				
			/*}else{
				this.btnSiguiente = true;
			}*/
				
				if(i==(this.listArchivosSelected.size()-1)){
					this.btnSiguienteAgr = true;
					this.btnAnteriorAgr = false;
				}else{
					this.selectedExpDocGropuped=(ExpedienteDocumento) this.listArchivosSelected.get(i+1);
					i=this.listArchivosSelected.indexOf(this.selectedExpDocGropuped);
					System.out.println("Indice: "+i);
					this.numero_documentoAgr = i+1;
					if(i==(this.listArchivosSelected.size()-1)){
						this.btnSiguienteAgr = true;
					}
					this.btnAnteriorAgr = false;
				}
		}
		//this.nodoSelect = new DefaultTreeNode(this.expDocumentoSelected,root);
		//this.nodoSelect.setSelected(true);
		this.renderedTextoInformativo = Boolean.TRUE;
		
		this.nombreArchivoAgr = this.selectedExpDocGropuped.getNombre_archivo();
	}
	
	public void siguienteDesAgr(){
		int i = 0;
		if(this.selectedExpDocHijo == null){
			
			this.selectedExpDocHijo=listArchivosHijos.get(0);
			this.numero_documentoDesAgr = 0+1;
			this.btnAnteriorDesAgr = true;
			this.btnSiguienteDesAgr = false;
			//System.out.println("No existe documento");
		}else{
			i=this.listArchivosHijos.indexOf(this.selectedExpDocHijo);
			
			//if (i<(this.listaDocumentosRegistrados.size())){
			
				
			/*}else{
				this.btnSiguiente = true;
			}*/
				
				if(i==(this.listArchivosHijos.size()-1)){
					this.btnSiguienteDesAgr = true;
					this.btnAnteriorDesAgr = false;
				}else{
					this.selectedExpDocHijo=(ExpedienteDocumento) this.listArchivosHijos.get(i+1);
					i=this.listArchivosHijos.indexOf(this.selectedExpDocHijo);
					System.out.println("Indice: "+i);
					this.numero_documentoDesAgr = i+1;
					if(i==(this.listArchivosHijos.size()-1)){
						this.btnSiguienteDesAgr = true;
					}
					this.btnAnteriorDesAgr = false;
				}
		}
		//this.nodoSelect = new DefaultTreeNode(this.expDocumentoSelected,root);
		//this.nodoSelect.setSelected(true);
		this.renderedTextoInformativo = Boolean.TRUE;
		
		this.nombreArchivoDesAgr = this.selectedExpDocHijo.getNombre_archivo();
	}
	
	public void ingresarNumero(){
		int i = 0;
		System.out.println("entrando con enter");
		this.expDocumentoSelected = this.listaDocumentosRegistrados.get(this.numero_documento-1);
		i=this.listaDocumentosRegistrados.indexOf(this.expDocumentoSelected);
		
		if(i==0){
			this.btnAnterior = true;
		}else{
			this.btnAnterior = false;
		}
		
		if(i==this.listaDocumentosRegistrados.size()-1){
			this.btnSiguiente = true;
		}else{
			this.btnSiguiente = false;
		}
		
		this.renderedFrame=true;
		
		this.renderedTextoInformativo = Boolean.TRUE;
		this.nombreArchivo = this.expDocumentoSelected.getNombre_archivo();
		
		this.nodoSelect = new DefaultTreeNode(this.expDocumentoSelected);
		
		Integer ij = root.getChildren().indexOf(this.nodoSelect);
		this.nodoSelect.setSelected(true);
		
		for (TreeNode n : root.getChildren()) {
			
			n.setSelected(false);
			
		}
		
		root.getChildren().set(ij, this.nodoSelect);
	}
	
	public void ingresarNumeroAgr(){
		int i = 0;
		System.out.println("entrando con enter");
		this.selectedExpDocHijo = this.listArchivosHijos.get(this.numero_documentoDesAgr-1);
		i=this.listArchivosHijos.indexOf(this.selectedExpDocHijo);
		
		if(i==0){
			this.btnAnteriorDesAgr = true;
		}else{
			this.btnAnteriorDesAgr = false;
		}
		
		if(i==this.listArchivosHijos.size()-1){
			this.btnSiguienteDesAgr = true;
		}else{
			this.btnSiguienteDesAgr = false;
		}
		
		this.renderedFrame=true;
		
		this.renderedTextoInformativo = Boolean.TRUE;
		this.nombreArchivoDesAgr = this.selectedExpDocHijo.getNombre_archivo();
	}
	
	public void ingresarNumeroDesAgr(){
		int i = 0;
		System.out.println("entrando con enter");
		this.selectedExpDocGropuped = this.listArchivosSelected.get(this.numero_documentoAgr-1);
		i=this.listArchivosSelected.indexOf(this.selectedExpDocGropuped);
		
		if(i==0){
			this.btnAnteriorAgr = true;
		}else{
			this.btnAnteriorAgr = false;
		}
		
		if(i==this.listArchivosSelected.size()-1){
			this.btnSiguienteAgr = true;
		}else{
			this.btnSiguienteAgr = false;
		}
		
		this.renderedFrame=true;
		
		this.renderedTextoInformativo = Boolean.TRUE;
		this.nombreArchivoAgr = this.selectedExpDocGropuped.getNombre_archivo();
	}
	
	public void onNodeSelect(NodeSelectEvent event) {
		int i = 0;
		this.renderedFrame = Boolean.TRUE;
		//String title = event.getTreeNode().getData().toString();
		
		System.out.println("Id Expediente " +((ExpedienteDocumento)event.getTreeNode().getData()).getId_expediente_documento());
		
		//Integer id = Integer.parseInt(title.substring(0,title.indexOf("-")));
		System.out.println("entro");
		
		try {
		//	this.expDocumentoSelected = expedienteDocumentoService.findByID(id);
			this.expDocumentoSelected = (ExpedienteDocumento)event.getTreeNode().getData();
			System.out.println("expDocumentoSelected "+expDocumentoSelected.getEstado_accion());
			
			i=this.listaDocumentosRegistrados.indexOf(this.expDocumentoSelected);
			
			System.out.println("El indice del nodo seleccionado es: "+i);
			if(i==0){
				this.btnAnterior = true;
			}else{
				this.btnAnterior = false;
			}
			
			if(i==this.listaDocumentosRegistrados.size()-1){
				this.btnSiguiente = true;
			}else{
				this.btnSiguiente = false;
			}
			
			this.numero_documento = i+1;
			if(!Arrays.asList(nodo_archivosSelected).contains(event.getTreeNode())){
			//	Arrays.asList(nodo_archivosSelected).add(this.nodoSelect);
			
			}
			System.out.println(" nodo_archivosSelected "+nodo_archivosSelected.length);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.renderedTextoInformativo = Boolean.TRUE;
		
		this.nombreArchivo = this.expDocumentoSelected.getNombre_archivo();
	}
	
	public void eliminarArchivo(){
		RequestContext context = RequestContext.getCurrentInstance();
		
		//String data = this.nodoSelect.getData().toString();
		//Integer id = Integer.parseInt(data.substring(0,data.indexOf("-")));
		
		//System.out.println("id a eliminar " +id);
		try {
			//this.expDocumentoSelected = expedienteDocumentoService.findByID(id);
			this.expDocumentoSelected = (ExpedienteDocumento)this.nodoSelect.getData();
			if(this.expDocumentoSelected!= null){
				
				boolean result = expedienteDocumentoService.updateEstadoEliminadoArchivoPaginas(this.expDocumentoSelected);
				
				if(result){
					
					log.setAccion("UPDATE");
					log.setDescripcion("Se cambio al estado ELIMINADO el Archivo "+ this.expDocumentoSelected.getNombre_archivo()+" del Nro de Expediente "+this.nuevoExpediente.getNro_expediente());
		            logmb.insertarLog(log);
		            
		            log.setAccion("UPDATE");
					log.setDescripcion("Se cambio al estado ELIMINADO las p�ginas del Archivo "+ this.expDocumentoSelected.getNombre_archivo()+" del Nro de Expediente "+this.nuevoExpediente.getNro_expediente());
		            logmb.insertarLog(log);
					
					//log.setAccion("UPDATE");
				 	//log.setDescripcion("Se actualiz� el estado del expediente documento : " + this.expedienteSelected.getNro_expediente());
				 	//logmb.insertarLog(log);
					Utiles.moveFileToDeleted(expDocumentoSelected);
					listarArchivosRegistrado();
					context.update("tvContCal:fmdt:treeDocumentos");
					context.update("tvContCal:fmdt:pnlFrame");
					
					//FacesUtils.showFacesMessage("Se elimin� correctamente el archivo",Constante.INFORMACION);
					//context.update("smsGeneralCC");
					
				 	this.resultMessage = new ResultBean();
					resultMessage.setNombre_archivo(expDocumentoSelected.getNombre_archivo());
				 	
					context.update("dlgDetSuccesEl");
					context.execute("PF('dlgSuccessEl').show()");
					
					
					
					this.renderedFrame = Boolean.FALSE;
				} else{
					System.out.println("no se pudo eliminar ");
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// reemplazar Archivo
	public void setearArchivoReem(){
		RequestContext context = RequestContext.getCurrentInstance();
		//String data = this.nodoSelect.getData().toString();
		//Integer id = Integer.parseInt(data.substring(0,data.indexOf("-")));
		//System.out.println("id a reemplazar " +id);
		
		try {
			//this.expDocumentoSelected = expedienteDocumentoService.findByID(id);
			this.expDocumentoSelected = (ExpedienteDocumento)this.nodoSelect.getData();
			this.renderedBtnSave = Boolean.TRUE;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setearCambioNombre(){
		RequestContext context = RequestContext.getCurrentInstance();
		//String data = this.nodoSelect.getData().toString();
		//Integer id = Integer.parseInt(data.substring(0,data.indexOf("-")));
		//System.out.println("id a reemplazar " +id);
		
		try {
			//this.expDocumentoSelected = expedienteDocumentoService.findByID(id);
			//this.expDocumentoSelected = (ExpedienteDocumento)this.nodoSelect.getData();
			this.expDocG= (ExpedienteDocumento)this.nodoSelect.getData();
			//dssdfds
			
			this.nuevoNombre = this.expDocumentoSelected.getNombre_archivo();
			this.renderedBtnSave = Boolean.TRUE;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void exportarArchivo(){
		try {
			
		this.expDocumentoSelected = (ExpedienteDocumento)this.nodoSelect.getData();
		
		this.nameFileG = this.expDocumentoSelected.getNombre_archivo();
		
		Expediente ex = new Expediente();		
		
		ex = this.expedienteService.findById(this.expDocumentoSelected.getExpediente_id());		
		
		System.out.println("file " + this.nameFileG);
		
		System.out.println("RUTA TOTAL " + Constante.RUTA_ARCHIVO+ex.getNro_expediente()+"/");
		
		byte[] _file= Utiles.descargarArchivoViaSFTP(Constante.RUTA_ARCHIVO+ex.getNro_expediente()+"/", this.nameFileG);
		
		ExportarArchivo.executeFile(_file, this.nameFileG);
		FacesContext.getCurrentInstance().responseComplete();
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Nuevos archivos ANTERIOR - DESPUES
	
	public void setearNuevoArchivo(String tipOpe){
		RequestContext context = RequestContext.getCurrentInstance();
	//	String data = this.nodoSelect.getData().toString();
	//	Integer id = Integer.parseInt(data.substring(0,data.indexOf("-")));
	//	System.out.println("id a reemplazar " +id);
		
		this.tipOperacion_NewFile = tipOpe;
		try {
			//this.expDocumentoSelected = expedienteDocumentoService.findByID(id);
			this.expDocumentoSelected = (ExpedienteDocumento)this.nodoSelect.getData();
			this.renderedBtnSave = Boolean.TRUE;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Desagrupar Archivos
	public void setearDesagrupar(ExpedienteDocumento ed){
//		RequestContext context = RequestContext.getCurrentInstance();
//		String data = this.nodoSelect.getData().toString();
//		Integer id = Integer.parseInt(data.substring(0,data.indexOf("-")));
//		System.out.println("id a reemplazar " +id);
		
		 this.renderedVisorHijo = Boolean.FALSE;
		
		try {
			this.expDocumentoSelected = ed;
			this.selectedExpDocGropuped = ed;
			this.listArchivosHijos = expedienteDocumentoService.listArchivosByPadre(ed.getId_expediente_documento());
			this.renderedBtnSave = Boolean.TRUE;
			RequestContext.getCurrentInstance().execute("desagrupado();");
			resetBotonesNavegacionDesAgr();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void desagruparArchivo(){
		boolean individual = true;
			RequestContext context = RequestContext.getCurrentInstance();
		
		if(this.listArchivosHijos.size() == 1){
			if(this.listArchivosHijos.get(0).getId_expediente_documento().equals(this.listArchivosHijos.get(0).getId_expediente_documento())){
				individual = true;
			}else{
				individual = false;
			}
			
		}else{
			individual = false;
		}
		
		if(!individual){
		System.out.println("MASIVO");
		boolean result = expedienteDocumentoService.desagruparArchivos(expDocumentoSelected);
		if(result) {
			log.setAccion("UPDATE");
			log.setDescripcion("Se cambio al estado DESAGRUPADO del Archivo "+ this.expDocumentoSelected.getNombre_archivo()+" del Nro de Expediente "+this.nuevoExpediente.getNro_expediente());
            logmb.insertarLog(log);
            
            log.setAccion("UPDATE");
			log.setDescripcion("Se cambio al estado ELIMINADO las p�ginas del Archivo "+ this.expDocumentoSelected.getNombre_archivo()+" del Nro de Expediente "+this.nuevoExpediente.getNro_expediente());
            logmb.insertarLog(log);
            
			context.update("tvContCal:fmdt:treeDocumentos");
			context.update("tvContCal:pngdd");
			context.update("tvContCal:fmdt:info_estado");
			 
			try {
				listarDocumentosCantidades(_id_Expediente);
				FacesUtils.showFacesMessage("Se Desagrupo Correctamente el archivo", Constante.INFORMACION);
				context.update("smsGeneralCC");
				context.update("tvContCal:fmdt:pnlGroupedInfo");
				
				this.resultMessage = new ResultBean();
				resultMessage.setNombre_archivo(expDocumentoSelected.getNombre_archivo());
			 	
				context.execute("PF('dlgDesagrupar').hide()");
				context.execute("PF('dlgFilesGrouped').hide()");
				
				context.update("dlgDetSuccesDesGT"); 
				context.execute("PF('dlgSuccessDesGT').show()");
				RequestContext.getCurrentInstance().execute("inicia();");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else{
			System.out.println("No se pudo reemplazar el archivo");
		}
		}else{
			System.out.println("INDIVIDUAL");
			try {
				this.expedienteDocumentoService.updateEstadoDesgrupadoByIdExpDoc(this.listArchivosHijos.get(0).getId_expediente_documento());
			
			this.resultMessage = new ResultBean();
			this.resultMessage.setNombre_archivo(this.listArchivosHijos.get(0).getNombre_archivo());
			this.resultMessage.setNro_hojas(this.listArchivosHijos.get(0).getNro_paginas());
			listarDocumentosCantidades(_id_Expediente);
			context.execute("PF('dlgDesagrupar').hide()");
			
			context.update("dlgDetSuccesDesGT");
			context.execute("PF('dlgSuccessDesGT').show()");
			context.update("tvContCal:fmdt:pnlGroupedInfo");
			
			
			context.update("tvContCal:fmdt:pngdd");
			context.update("tvContCal:fmdt:pg_treeDocumentos");
			this.renderedBtnAgrup = Boolean.FALSE;
			
			//context.update("pg_treeDocumentos");
			this.renderedTree = Boolean.TRUE;
			
			//context.update("pg_treeDocumentosGroup");
			this.renderedTreeGroup = Boolean.FALSE;
			
			this.renderedPnlBusqueda = Boolean.FALSE;
			
			
			RequestContext.getCurrentInstance().execute("inicia();");
			
			
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
		
	public void cargarDocumentosReemplazo(FileUploadEvent event) throws Exception{
		RequestContext context = RequestContext.getCurrentInstance();
		fileReemplazo = null;
		this.archivoCargado = true;
		
		
		
		if(event.getFile() != null) {
			
			expDocumentoReemplazo = new ExpedienteDocumento();
			expDocumentoReemplazo.setNombre_archivo(event.getFile().getFileName());
			expDocumentoReemplazo.setPeso(event.getFile().getSize());
			expDocumentoReemplazo.setDescripcion_peso(Utils.convertirLongBytes(event.getFile().getSize(), false));
			
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
	            expDocumentoReemplazo.setFile(_archivo);
	            
	            this.fileReemplazo = _archivo;
	           
	            
	            PdfReader pf = new PdfReader(new FileInputStream(_archivo));
	     //     PdfReader subrep = new PdfReader(ExportarArchivo.getPath("resources/1.pdf"));
	            
	            for(int i = 0; i < pf.getNumberOfPages(); i++) {

	            	DocumentoPagina  dp = new DocumentoPagina();
	            	dp.setNro_pagina(i+1);
	            	dp.setPeso(Long.valueOf(pf.getPageContent(i+1).length));
	            	dp.setDescripcion_peso(Utils.convertirLongBytes(Long.valueOf(pf.getPageContent(i+1).length), false));
	            	dp.setFlag(Boolean.TRUE);
	            	 if(Long.valueOf(pf.getPageContent(i+1).length) < 15000 || Long.valueOf(pf.getPageContent(i+1).length) > 300000){
	            		 dp.setFlag(Boolean.FALSE);
	            	 }
	            	expDocumentoReemplazo.getListaPaginas().add(dp);
	            	
	            }
	            
	            expDocumentoReemplazo.setNro_paginas(pf.getNumberOfPages());
	          pf.close();
	            
//	          this.listaDocumentos.add(ed);  
//	          this.nro_archivos_subidos=  listaDocumentos.size();
	          
	          this.renderedFileUpload = Boolean.FALSE;
	          
	          this.renderedBtnSave = Boolean.FALSE;
		}
		}
		
	public void setearVerFilesGrouped(){
		this.selectedExpDocGropuped = null;
		this.renderedVisorGrouped = Boolean.FALSE;
	}
	
	public void subirArchivoReemplazo(){
		RequestContext context = RequestContext.getCurrentInstance();
		
		boolean result = expedienteDocumentoService.updateEstadoReemplazoArchivoPaginas(expDocumentoSelected, expDocumentoReemplazo, usuarioLogin.getIdusuario(),log);
		if(result) {
			//log.setAccion("UPDATE");
            //log.setDescripcion("Se reemplazaron las p�ginas del expediente " + this.expDocumentoSelected.getNro_expediente());
            //logmb.insertarLog(log);

			Utiles.moveFileToReplaced(this.fileReemplazo, expDocumentoSelected,this.expedienteSelected.getRuta_carpeta());
			context.update(":tvContCal:fmdt:pnlFrame");
			
			this.resultMessage = new ResultBean();
			resultMessage.setNombre_archivo(expDocumentoSelected.getNombre_archivo());
			
			context.update("dlgDetSuccesRE");
			context.execute("PF('dlgSuccessRE').show()");
			
		} else{
			System.out.println("No se pudo reemplazar el archivo");
		}
	}
	
	public void reemplazarSubir(){
		cambiarNombreArchivo();
		
		/*if(!(this.existeG)){*/
			
		agruparArchivo();
		
		/*}*/
		
	}
	
	public void agruparArchivo(){
		RequestContext context = RequestContext.getCurrentInstance();
		
		this.nombreArchivoAgrupado = "";
		
		listArchivosSelected = new ArrayList<>();
		this.renderedVisorGrouped = Boolean.FALSE;
		
		resetBotonesNavegacionAgr();
		
		
			//context.execute("PF('dlgGroupArchivos').show()"); 
		try {
		
			//String title = treeNode.getData().toString();
			//Integer id = Integer.parseInt(title.substring(0,title.indexOf("-")));
			
			//ExpedienteDocumento ed =  expedienteDocumentoService.findByID(id);
			ExpedienteDocumento ed = this.expDocG;
			listArchivosSelected.add(ed);
			agruparUnArchivo();
		//RequestContext.getCurrentInstance().execute("agrupado();");
		} catch (Exception e) {
			e.printStackTrace();
		}			
		
	}
	
	public void cambiarNombreArchivo(){
		RequestContext context = RequestContext.getCurrentInstance();
		
		//boolean result = expedienteDocumentoService.updateEstadoReemplazoArchivoPaginas(expDocumentoSelected, expDocumentoReemplazo, usuarioLogin.getIdusuario(),log);
		//if(result) {
			//log.setAccion("UPDATE");
            //log.setDescripcion("Se reemplazaron las p�ginas del expediente " + this.expDocumentoSelected.getNro_expediente());
            //logmb.insertarLog(log);
		try {
		Expediente ex = new Expediente();
		
		ex = this.expedienteService.findById(this.expDocG.getExpediente_id());
			
			/*boolean existe = true;
		
			for (ExpedienteDocumento expDoc : this.listaDocumentosRegistrados) {
				
				if(expDoc.getNombre_archivo().equals(this.nuevoNombre)){
					existe = false;
					
					break;
				}
				
			}*/
		
			//if(existe){
				
		
				System.out.println("NOMBRE ANTES: "+this.nuevoNombre);
		
				this.nuevoNombre= this.nuevoNombre+".pdf";
				
				System.out.println("NOMBRE DESPUES: "+this.nuevoNombre);
		
				Utiles.cambiarNombreViaSFTP(this.nuevoNombre, this.expDocG.getNombre_archivo(), Constante.RUTA_ARCHIVO+ex.getNro_expediente()+"/");
				this.expDocG.setNombre_archivo(this.nuevoNombre);
				this.expDocG.setRuta(Constante.RUTA_ARCHIVO+ex.getNro_expediente()+"/"+this.nuevoNombre);
				
					this.expedienteDocumentoService.actualizarNombreArchivo(this.expDocG);
				
				context.update(":tvContCal:fmdt:pnlFrame");
				
				listarArchivosRegistrado();
				
				//context.update("fmdt:treeDocumentos");
				
				this.resultMessage = new ResultBean();
				resultMessage.setNombre_archivo(expDocG.getNombre_archivo());
			
				//this.existeG = false;
				
			/*}else{
				
				this.existeG = true;
				context.execute("PF('dlgExisteArch').show()"); 
				context.update("frmExisteArch");
			}*/
			
			/*context.update("dlgDetSuccesRE");
			context.execute("PF('dlgSuccessRE').show()");*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*} else{
			System.out.println("No se pudo reemplazar el archivo");
		}*/
	}
	
	
	// Insertar Anterior - Despues
	
	public void cargarDocumentosNuevoArchivo(FileUploadEvent event) throws Exception{
		RequestContext context = RequestContext.getCurrentInstance();
		fileReemplazo = null;
		this.archivoCargado = true;
		
		
		
		if(event.getFile() != null) {
			
			expDocumentoReemplazo = new ExpedienteDocumento();
			expDocumentoReemplazo.setNombre_archivo(event.getFile().getFileName());
			expDocumentoReemplazo.setPeso(event.getFile().getSize());
			expDocumentoReemplazo.setDescripcion_peso(Utils.convertirLongBytes(event.getFile().getSize(), false));
			
			expDocumentoReemplazo.setNro_archivo(this.expedienteDocumentoService.getNroArchivoSeq());
			
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
	            expDocumentoReemplazo.setFile(_archivo);
	            
	            this.fileReemplazo = _archivo;
	           
	            
	            PdfReader pf = new PdfReader(new FileInputStream(_archivo));
	     //     PdfReader subrep = new PdfReader(ExportarArchivo.getPath("resources/1.pdf"));
	            
	            for(int i = 0; i < pf.getNumberOfPages(); i++) {

	            	DocumentoPagina  dp = new DocumentoPagina();
	            	dp.setNro_pagina(i+1);
	            	dp.setPeso(Long.valueOf(pf.getPageContent(i+1).length));
	            	dp.setDescripcion_peso(Utils.convertirLongBytes(Long.valueOf(pf.getPageContent(i+1).length), false));
	            	dp.setFlag(Boolean.TRUE);
	            	 if(Long.valueOf(pf.getPageContent(i+1).length) < 15000 || Long.valueOf(pf.getPageContent(i+1).length) > 300000){
	            		 dp.setFlag(Boolean.FALSE);
	            	 }
	            	expDocumentoReemplazo.getListaPaginas().add(dp);
	            	
	            }
	            
	            expDocumentoReemplazo.setNro_paginas(pf.getNumberOfPages());
	          pf.close();
	            
//	          this.listaDocumentos.add(ed);  
//	          this.nro_archivos_subidos=  listaDocumentos.size();
	          
	          this.renderedFileUpload = Boolean.FALSE;
	          
	          this.renderedBtnSave = Boolean.FALSE;
		}
		}
	
	public void subirNuevoArchivo(){
		RequestContext context = RequestContext.getCurrentInstance();
		
		boolean result = expedienteDocumentoService.subirNuevoArchivo(this.tipOperacion_NewFile, expDocumentoSelected.getExpediente_id(),expDocumentoSelected, expDocumentoReemplazo, usuarioLogin.getIdusuario(),this.expedienteSelected,log);
		if(result) {
			//log.setAccion("INSERT");
            //log.setDescripcion("Se registr� el expediente " + this.expDocumentoSelected.getNro_expediente());
            //logmb.insertarLog(log);
			Utiles.enviarArchivoViaSFTP(expDocumentoReemplazo.getFile(), expDocumentoReemplazo.getNombre_archivo(), this.expedienteSelected.getRuta_carpeta());
			context.update("tvContCal:fmdt:treeDocumentos");
			context.update("tvContCal:pngdd");
			context.update("tvContCal:fmdt:info_estado");

			try {
				listarDocumentosCantidades(_id_Expediente);
				

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else{
			System.out.println("Se registro el archivo");
		}
	}
	
	
	// Agrupar Archivos
	
	public void setearAgruparArchivos(){
		
		System.out.println("hola q hay");
		
		this.renderedTree = Boolean.FALSE;
		this.renderedTreeGroup = Boolean.TRUE;
		
		this.renderedBtnAgrup = Boolean.TRUE;
		renderedPnlBusqueda = Boolean.TRUE;
		
		for (TreeNode node : nodo_archivosSelected) {
			node.setSelected(Boolean.FALSE);
			nodo_archivosSelected = ArrayUtils.removeElement(nodo_archivosSelected, node);
		}
		
		if(this.nodoSelect==null){
			
			System.out.println("no hay nodo seleccionado");
			
		}else{
		
		this.nodoSelect.setSelected(Boolean.FALSE);
		
		nodo_archivosSelected = ArrayUtils.removeElement(nodo_archivosSelected, this.nodoSelect);
		}
		this.renderedSelectAllCheckBox = Boolean.FALSE;
	}
		
	public void finalizarAgrupacion(TreeNode[] nodes){
		RequestContext context = RequestContext.getCurrentInstance();
		System.out.println("this.nodo_archivosSelected.length  " +nodes.length );
		
		this.nombreArchivoAgrupado = "";
		
		this.nro_archivos_selected = nodes.length; 
		listArchivosSelected = new ArrayList<>();
		this.renderedVisorGrouped = Boolean.FALSE;
		
		resetBotonesNavegacionAgr();
		if(nodes.length>0){
		
			context.execute("PF('dlgGroupArchivos').show()"); 
		try {
		for (TreeNode treeNode : nodes) {
			//String title = treeNode.getData().toString();
			//Integer id = Integer.parseInt(title.substring(0,title.indexOf("-")));
			
			//ExpedienteDocumento ed =  expedienteDocumentoService.findByID(id);
			ExpedienteDocumento ed = (ExpedienteDocumento)treeNode.getData();
			listArchivosSelected.add(ed);
			}
		RequestContext.getCurrentInstance().execute("agrupado();");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		} else{
			context.execute("PF('dlgNotSelected').show()"); 
		}
	}
		
	
	public void agruparUnArchivo(){
		System.out.println("Agrupando archivos .....");
		RequestContext context = RequestContext.getCurrentInstance();
		
		if(listArchivosSelected.size()>0){
			// obtenemos los archivos
			listArchivosSelected = Utiles.obtenerArchivosFisico(listArchivosSelected);
			
			try {
			File _archivo = File.createTempFile("prefix", ".pdf");
			OutputStream out = new FileOutputStream(_archivo);
			
			Document document = new Document();
			PdfWriter writer = PdfWriter.getInstance(document, out);
			document.open();
			PdfContentByte pageContentByte = writer.getDirectContent();
			PdfImportedPage pdfImportedPage ;
			int currentPdfReaderPage = 1;
	//		PdfCopy writer2 = new PdfCopy(document, new FileOutputStream(_archivo));
			for (ExpedienteDocumento ed : listArchivosSelected) {
					//PdfReader pf = new PdfReader(new FileInputStream(ed.getFile()));
				PdfReader pf = ed.getPdfReader();
					
					while(currentPdfReaderPage<=pf.getNumberOfPages()){
						document.setPageSize(pf.getPageSizeWithRotation(currentPdfReaderPage)); 
						document.newPage();
						//document.setPageSize(pf.getPageSize(currentPdfReaderPage));
						pdfImportedPage = writer.getImportedPage(pf, currentPdfReaderPage);
						
//						Rectangle rectangle = pf.getPageSizeWithRotation(currentPdfReaderPage);
//		                    document.setPageSize(rectangle);
//		                    
//		                    
//		                    switch (rectangle.getRotation()) {
//		                    case 0:
//		                    	pageContentByte.addTemplate(pdfImportedPage, 1f, 0, 0, 1f, 0, 0);
//		                        break;
//		                    case 90:
//		                    	pageContentByte.addTemplate(pdfImportedPage, 0, -1f, 1f, 0, 0, pf.getPageSizeWithRotation(currentPdfReaderPage).getHeight());
//		                        break;
//		                    case 180:
//		                    	pageContentByte.addTemplate(pdfImportedPage, -1f, 0, 0, -1f, 0, 0);
//		                        break;
//		                    case 270:
//		                    	pageContentByte.addTemplate(pdfImportedPage, 0, 1.0F, -1.0F, 0, pf
//		                                .getPageSizeWithRotation(currentPdfReaderPage).getWidth(), 0);
//		                        break;
//		                    default:
//		                        break;
//		                    }
//		                    
//		                    pageContentByte.beginText();
//		                    pageContentByte.getPdfDocument().getPageSize();
//		                    pageContentByte.endText();
						
						pageContentByte.addTemplate(pdfImportedPage,0,0);
						
						currentPdfReaderPage++;
					}
					currentPdfReaderPage = 1;
					
//					while(currentPdfReaderPage<=pf.getNumberOfPages()){
//						pdfImportedPage = writer2.getImportedPage(pf, currentPdfReaderPage);
//						 writer2.addPage(pdfImportedPage);
//					}
//					currentPdfReaderPage = 1;
			}
			out.flush();
		//	out.close();
			document.close();
			
			System.out.println(" tama�o " +_archivo.length());
			
			this.nuevoNombre = this.nuevoNombre.trim();
			
			// Registramos el nuevo archivo con
			PdfReader pf_grouped = new PdfReader(new FileInputStream(_archivo));
			ExpedienteDocumento ed  = new ExpedienteDocumento();
			ed.setNombre_archivo(this.nuevoNombre);
			ed.setPeso(Long.valueOf(pf_grouped.getFileLength()));
			ed.setDescripcion_peso(Utils.convertirLongBytes(ed.getPeso(), false));
			ed.setNro_archivo(this.expedienteDocumentoService.getNroArchivoSeq());
			ed.setNro_paginas(pf_grouped.getNumberOfPages());
			
			for (int i = 0; i < pf_grouped.getNumberOfPages(); i++) {
				DocumentoPagina  dp = new DocumentoPagina();
            	dp.setNro_pagina(i+1);
            	dp.setPeso(Long.valueOf(pf_grouped.getPageContent(i+1).length));
            	dp.setDescripcion_peso(Utils.convertirLongBytes(Long.valueOf(pf_grouped.getPageContent(i+1).length), false));
            	ed.getListaPaginas().add(dp);
            	dp.setFlag(Boolean.TRUE);
            	 if(Long.valueOf(pf_grouped.getPageContent(i+1).length) < 15000 || Long.valueOf(pf_grouped.getPageContent(i+1).length) > 300000){
            		 dp.setFlag(Boolean.FALSE);
            	 }
			}
			
			// Actualizamos los archivos anteriores
			// Despues registramos el archivo combinado
			
			  boolean result = expedienteDocumentoService.updateEstadoAgrupadoArchivosPaginas2(listArchivosSelected.get(0), this.usuarioLogin.getIdusuario(),
					 ed, _id_Expediente,this.expedienteSelected,log);
			  
			 System.out.println("pf_grouped "+pf_grouped.getFileLength());
			 System.out.println("pf_grouped "+pf_grouped.getNumberOfPages());
			 System.out.println("pf_grouped "+ed.getNombre_archivo());
			
			if(result){
				//log.setAccion("UPDATE");
	            //log.setDescripcion("Se actualizaron las p�ginas del expediente " + this.expDocumentoSelected.getNro_expediente());
	            //logmb.insertarLog(log);
				this.resultMessage = new ResultBean();
				resultMessage.setNombre_archivo(ed.getNombre_archivo());
				resultMessage.setNro_hojas(ed.getNro_paginas());
				
				// enviamos el archivo al servidor
				Utiles.enviarArchivoViaSFTP(_archivo, this.nuevoNombre,this.expedienteSelected.getRuta_carpeta());

				//context.execute("PF('dlgGroupArchivos').hide()");
				
				context.update("tvContCal:fmdt:pngdd");
				this.renderedBtnAgrup = Boolean.FALSE;
				
				//context.update("pg_treeDocumentos");
				this.renderedTree = Boolean.TRUE;
				
				//context.update("pg_treeDocumentosGroup");
				this.renderedTreeGroup = Boolean.FALSE;
				
				this.renderedPnlBusqueda = Boolean.FALSE;
				
				for (TreeNode node : nodo_archivosSelected) {
					node.setSelected(Boolean.FALSE);
					nodo_archivosSelected = ArrayUtils.removeElement(nodo_archivosSelected, node);
					
				}
				
				// mensaje OK
				context.update("fmSuccesRS");
				context.execute("PF('dlgSuccessRS').show()");
				RequestContext.getCurrentInstance().execute("inicia();");
				
				context.execute("PF('dlgCNArchivo').hide()");
				
				listarDocumentosCantidades(_id_Expediente);
				
				
				
			}
			
			
			} catch (DocumentException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}  catch (Exception e) {
				e.printStackTrace();
			} 
		}
		
	}
	
	public void agruparArchivos(){
		System.out.println("Agrupando archivos .....");
		RequestContext context = RequestContext.getCurrentInstance();
		
		if(listArchivosSelected.size()>0){
			// obtenemos los archivos
			listArchivosSelected = Utiles.obtenerArchivosFisico(listArchivosSelected);
			
			try {
			File _archivo = File.createTempFile("prefix", ".pdf");
			OutputStream out = new FileOutputStream(_archivo);
			
			Document document = new Document();
			PdfWriter writer = PdfWriter.getInstance(document, out);
			document.open();
			PdfContentByte pageContentByte = writer.getDirectContent();
			PdfImportedPage pdfImportedPage ;
			int currentPdfReaderPage = 1;
	//		PdfCopy writer2 = new PdfCopy(document, new FileOutputStream(_archivo));
			for (ExpedienteDocumento ed : listArchivosSelected) {
					//PdfReader pf = new PdfReader(new FileInputStream(ed.getFile()));
				PdfReader pf = ed.getPdfReader();
					
					while(currentPdfReaderPage<=pf.getNumberOfPages()){
						document.setPageSize(pf.getPageSizeWithRotation(currentPdfReaderPage)); 
						document.newPage();
						//document.setPageSize(pf.getPageSize(currentPdfReaderPage));
						pdfImportedPage = writer.getImportedPage(pf, currentPdfReaderPage);
						
//						Rectangle rectangle = pf.getPageSizeWithRotation(currentPdfReaderPage);
//		                    document.setPageSize(rectangle);
//		                    
//		                    
//		                    switch (rectangle.getRotation()) {
//		                    case 0:
//		                    	pageContentByte.addTemplate(pdfImportedPage, 1f, 0, 0, 1f, 0, 0);
//		                        break;
//		                    case 90:
//		                    	pageContentByte.addTemplate(pdfImportedPage, 0, -1f, 1f, 0, 0, pf.getPageSizeWithRotation(currentPdfReaderPage).getHeight());
//		                        break;
//		                    case 180:
//		                    	pageContentByte.addTemplate(pdfImportedPage, -1f, 0, 0, -1f, 0, 0);
//		                        break;
//		                    case 270:
//		                    	pageContentByte.addTemplate(pdfImportedPage, 0, 1.0F, -1.0F, 0, pf
//		                                .getPageSizeWithRotation(currentPdfReaderPage).getWidth(), 0);
//		                        break;
//		                    default:
//		                        break;
//		                    }
//		                    
//		                    pageContentByte.beginText();
//		                    pageContentByte.getPdfDocument().getPageSize();
//		                    pageContentByte.endText();
						
						pageContentByte.addTemplate(pdfImportedPage,0,0);
						
						currentPdfReaderPage++;
					}
					currentPdfReaderPage = 1;
					
//					while(currentPdfReaderPage<=pf.getNumberOfPages()){
//						pdfImportedPage = writer2.getImportedPage(pf, currentPdfReaderPage);
//						 writer2.addPage(pdfImportedPage);
//					}
//					currentPdfReaderPage = 1;
			}
			out.flush();
		//	out.close();
			document.close();
			
			System.out.println(" tama�o " +_archivo.length());
			
			this.nombreArchivoAgrupado = this.nombreArchivoAgrupado.trim() + ".pdf";
			
			// Registramos el nuevo archivo con
			PdfReader pf_grouped = new PdfReader(new FileInputStream(_archivo));
			ExpedienteDocumento ed  = new ExpedienteDocumento();
			ed.setNombre_archivo(this.nombreArchivoAgrupado);
			ed.setPeso(Long.valueOf(pf_grouped.getFileLength()));
			ed.setDescripcion_peso(Utils.convertirLongBytes(ed.getPeso(), false));
			ed.setNro_archivo(this.expedienteDocumentoService.getNroArchivoSeq());
			ed.setNro_paginas(pf_grouped.getNumberOfPages());
			
			for (int i = 0; i < pf_grouped.getNumberOfPages(); i++) {
				DocumentoPagina  dp = new DocumentoPagina();
            	dp.setNro_pagina(i+1);
            	dp.setPeso(Long.valueOf(pf_grouped.getPageContent(i+1).length));
            	dp.setDescripcion_peso(Utils.convertirLongBytes(Long.valueOf(pf_grouped.getPageContent(i+1).length), false));
            	ed.getListaPaginas().add(dp);
            	dp.setFlag(Boolean.TRUE);
            	 if(Long.valueOf(pf_grouped.getPageContent(i+1).length) < 15000 || Long.valueOf(pf_grouped.getPageContent(i+1).length) > 300000){
            		 dp.setFlag(Boolean.FALSE);
            	 }
			}
			
			// Actualizamos los archivos anteriores
			// Despues registramos el archivo combinado
			
			  boolean result = expedienteDocumentoService.updateEstadoAgrupadoArchivosPaginas(listArchivosSelected, this.usuarioLogin.getIdusuario(),
					 ed, _id_Expediente,this.expedienteSelected,log);
			  
			 System.out.println("pf_grouped "+pf_grouped.getFileLength());
			 System.out.println("pf_grouped "+pf_grouped.getNumberOfPages());
			
			if(result){
				//log.setAccion("UPDATE");
	            //log.setDescripcion("Se actualizaron las p�ginas del expediente " + this.expDocumentoSelected.getNro_expediente());
	            //logmb.insertarLog(log);
				this.resultMessage = new ResultBean();
				resultMessage.setNombre_archivo(ed.getNombre_archivo());
				resultMessage.setNro_hojas(ed.getNro_paginas());
				
				// enviamos el archivo al servidor
				Utiles.enviarArchivoViaSFTP(_archivo, this.nombreArchivoAgrupado,this.expedienteSelected.getRuta_carpeta());

				//context.execute("PF('dlgGroupArchivos').hide()");
				
				context.update("tvContCal:fmdt:pngdd");
				this.renderedBtnAgrup = Boolean.FALSE;
				
				//context.update("pg_treeDocumentos");
				this.renderedTree = Boolean.TRUE;
				
				//context.update("pg_treeDocumentosGroup");
				this.renderedTreeGroup = Boolean.FALSE;
				
				this.renderedPnlBusqueda = Boolean.FALSE;
				
				for (TreeNode node : nodo_archivosSelected) {
					node.setSelected(Boolean.FALSE);
					nodo_archivosSelected = ArrayUtils.removeElement(nodo_archivosSelected, node);
					
				}
				
				// mensaje OK
				context.update("dlgDetSuccesGroup");
				context.execute("PF('dlgSuccessGroup').show()");
				RequestContext.getCurrentInstance().execute("inicia();");
				
				context.execute("PF('dlgCNArchivo').hide()");
				
				listarDocumentosCantidades(_id_Expediente);
				
				
				
			}
			
			
			} catch (DocumentException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}  catch (Exception e) {
				e.printStackTrace();
			} 
		}
		
	}
	
	public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
         
        /*if(newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }*/
    }
	
	public void cancelarAgruparArchivo(){
		this.renderedBtnAgrup = Boolean.FALSE;
		
		//this.renderedPnlBusqueda = Boolean.FALSE;
		
		this.renderedTree = Boolean.TRUE;
		this.renderedTreeGroup = Boolean.FALSE;
		this.renderedPnlDescargar = Boolean.TRUE;
		
		for (TreeNode node : nodo_archivosSelected) {
			node.setSelected(Boolean.FALSE);
			nodo_archivosSelected = ArrayUtils.removeElement(nodo_archivosSelected, node);
			
		}
		
		
	}
	
	
	 public void onRowSelect(SelectEvent event) {
		 int i = 0;
		 i=this.listArchivosSelected.indexOf(this.selectedExpDocGropuped);
			
			System.out.println("El indice del nodo seleccionado es: "+i);
			if(i==0){
				this.btnAnteriorAgr = true;
			}else{
				this.btnAnteriorAgr = false;
			}
			
			if(i==this.listArchivosSelected.size()-1){
				this.btnSiguienteAgr = true;
			}else{
				this.btnSiguienteAgr = false;
			}
			
			this.numero_documentoAgr = i+1;
		 	this.renderedVisorGrouped = Boolean.TRUE;
		 	this.nombreArchivoAgr = this.selectedExpDocGropuped.getNombre_archivo();
	    }
	 
	 public void onRowSelect1(SelectEvent event) {
		 
		 	this.renderedVisorGrouped = Boolean.TRUE;
	    }
	 
	 public void onRowSelectHijo(SelectEvent event) {
		 
		 int i = 0;
		 i=this.listArchivosHijos.indexOf(this.selectedExpDocHijo);
			
			System.out.println("El indice del nodo seleccionado es: "+i);
			if(i==0){
				this.btnAnteriorDesAgr = true;
			}else{
				this.btnAnteriorDesAgr = false;
			}
			
			if(i==this.listArchivosHijos.size()-1){
				this.btnSiguienteDesAgr = true;
			}else{
				this.btnSiguienteDesAgr = false;
			}
			
			this.numero_documentoDesAgr = i+1;
		 	//this.renderedVisorGrouped = Boolean.TRUE;
		 	this.nombreArchivoDesAgr = this.selectedExpDocHijo.getNombre_archivo();
		 	//this.renderedVisorGrouped = Boolean.TRUE;
		 this.renderedVisorHijo = Boolean.TRUE;
	 }
	 
	 // quitar un solo archivo del agrupado 
	 public void removerArchivoSeleccionado(ExpedienteDocumento _edSelected){
		 RequestContext context = RequestContext.getCurrentInstance();
		List<ExpedienteDocumento> listaEdRestantes = null;
		System.out.println("Entramos ");
		try { 
			// el archivo padre seleccionado
		if(this.selectedExpDocGropuped != null){
			
		if(_edSelected.getId_expediente_documento().equals(this.selectedExpDocGropuped.getId_expediente_documento())){
			
			this.expedienteDocumentoService.updateEstadoDesgrupadoByIdExpDoc(_edSelected.getId_expediente_documento());
			this.resultMessage = new ResultBean();
			this.resultMessage.setNombre_archivo(_edSelected.getNombre_archivo());
			this.resultMessage.setNro_hojas(_edSelected.getNro_paginas());
			
			context.execute("PF('dlgDesagrupar').hide()");
			
			context.update("dlgDetSuccesDesG");
			context.execute("PF('dlgSuccessDesG').show()");
			
			
			context.update("tvContCal:fmdt:pngdd");
			this.renderedBtnAgrup = Boolean.FALSE;
			
			//context.update("pg_treeDocumentos");
			this.renderedTree = Boolean.TRUE;
			
			//context.update("pg_treeDocumentosGroup");
			this.renderedTreeGroup = Boolean.FALSE;
			
			this.renderedPnlBusqueda = Boolean.FALSE;
			
			for (TreeNode node : nodo_archivosSelected) {
				node.setSelected(Boolean.FALSE);
				nodo_archivosSelected = ArrayUtils.removeElement(nodo_archivosSelected, node);
			}
			
			listarDocumentosCantidades(_id_Expediente);
			
			
		}else{	
		
			
		System.out.println("EXP DOCUMENTO AG: "+this.selectedExpDocGropuped.getId_expediente_documento());
		System.out.println("EXP DOCUMENTO: "+_edSelected.getId_expediente_documento());
			 
		listaEdRestantes = this.expedienteDocumentoService.listArchivosHermanos(this.selectedExpDocGropuped.getId_expediente_documento(),_edSelected.getId_expediente_documento());
		
		if(listaEdRestantes.size()>0){
			// obtenemos los archivos
			listaEdRestantes = Utiles.obtenerArchivosFisico(listaEdRestantes);
			
			try {
			File _archivo = File.createTempFile("prefix", ".pdf");
			OutputStream out = new FileOutputStream(_archivo);
			
			Document document = new Document();
			PdfWriter writer = PdfWriter.getInstance(document, out);
			document.open();
			PdfContentByte pageContentByte = writer.getDirectContent();
			PdfImportedPage pdfImportedPage ;
			int currentPdfReaderPage = 1;
			for (ExpedienteDocumento ed : listaEdRestantes) {
				PdfReader pf = ed.getPdfReader();
					
					while(currentPdfReaderPage<=pf.getNumberOfPages()){
						document.setPageSize(pf.getPageSizeWithRotation(currentPdfReaderPage)); 
						document.newPage();
						pdfImportedPage = writer.getImportedPage(pf, currentPdfReaderPage);
						pageContentByte.addTemplate(pdfImportedPage,0,0);
						currentPdfReaderPage++;
					}
					currentPdfReaderPage = 1;
			}
			out.flush();
			document.close();
			
			// Registramos el nuevo archivo con
			PdfReader pf_grouped = new PdfReader(new FileInputStream(_archivo));
			ExpedienteDocumento ed  = new ExpedienteDocumento();
			ed.setNombre_archivo(this.selectedExpDocGropuped.getNombre_archivo());
			ed.setPeso(Long.valueOf(pf_grouped.getFileLength()));
			ed.setDescripcion_peso(Utils.convertirLongBytes(ed.getPeso(), false));
			ed.setNro_archivo(this.expedienteDocumentoService.getNroArchivoSeq());
			ed.setNro_paginas(pf_grouped.getNumberOfPages());
			
			for (int i = 0; i < pf_grouped.getNumberOfPages(); i++) {
				DocumentoPagina  dp = new DocumentoPagina();
            	dp.setNro_pagina(i+1);
            	dp.setPeso(Long.valueOf(pf_grouped.getPageContent(i+1).length));
            	dp.setDescripcion_peso(Utils.convertirLongBytes(Long.valueOf(pf_grouped.getPageContent(i+1).length), false));
            	ed.getListaPaginas().add(dp);
            	dp.setFlag(Boolean.TRUE);
            	 if(Long.valueOf(pf_grouped.getPageContent(i+1).length) < 15000 || Long.valueOf(pf_grouped.getPageContent(i+1).length) > 300000){
            		 dp.setFlag(Boolean.FALSE);
            	 }
			}
			
			// Actualizamos los archivos anteriores
			// Despues registramos el archivo combinado
			  ResultBean result = expedienteDocumentoService.removerHijoArchivoAgrupado(listaEdRestantes, this.usuarioLogin.getIdusuario(),
					 ed, _id_Expediente, this.expDocumentoSelected, _edSelected,this.expedienteSelected,log);
			  
			if(result.isResultado()){
				//log.setAccion("DELETE");
			 	//log.setDescripcion("Se elimin� el expediente : " + this.expedienteSelected.getNro_expediente());
			 	//logmb.insertarLog(log);
				this.resultMessage = new ResultBean();
				Utiles.eliminarArchivoViaSFTP(expDocumentoSelected.getRuta(),expedienteSelected.getRuta_carpeta());
				Utiles.enviarArchivoViaSFTP(_archivo, ed.getNombre_archivo(),expedienteSelected.getRuta_carpeta());
				

				this.listArchivosHijos = expedienteDocumentoService.listArchivosByPadre(result.getId_expediente_documento());
				this.renderedVisorHijo = Boolean.FALSE;
				this.selectedExpDocGropuped = this.expedienteDocumentoService.findByID(result.getId_expediente_documento());
				
				this.resultMessage.setNombre_archivo(this.selectedExpDocGropuped.getNombre_archivo());
				this.resultMessage.setNro_hojas(this.selectedExpDocGropuped.getNro_paginas());
				
				context.execute("PF('dlgDesagrupar').hide()");
				
				context.update("dlgDetSuccesDesG");
				context.execute("PF('dlgSuccessDesG').show()");
				
				
				context.update("tvContCal:fmdt:pngdd");
				this.renderedBtnAgrup = Boolean.FALSE;
				
				//context.update("pg_treeDocumentos");
				this.renderedTree = Boolean.TRUE;
				
				//context.update("pg_treeDocumentosGroup");
				this.renderedTreeGroup = Boolean.FALSE;
				
				this.renderedPnlBusqueda = Boolean.FALSE;
				
				for (TreeNode node : nodo_archivosSelected) {
					node.setSelected(Boolean.FALSE);
					nodo_archivosSelected = ArrayUtils.removeElement(nodo_archivosSelected, node);
				}
				
				listarDocumentosCantidades(_id_Expediente);
				
			}
			
			
			} catch (DocumentException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}  catch (Exception e) {
				e.printStackTrace();
			} 
		  }
		
		
		}
		
		 }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	 }
	 
	 // Quitar archivos a agrupar Todos
	 
	 public void removerArchivoAgrupar(ExpedienteDocumento ed){
		 
		 RequestContext context = RequestContext.getCurrentInstance();
		 
		 for (ExpedienteDocumento item : listArchivosSelected) {
			if(item.getId_expediente_documento().intValue() == ed.getId_expediente_documento().intValue()){
				listArchivosSelected.remove(item);
				break;
			}
		}
		
		 
	 }
	 
	 // Navegar Tree Up down
	 public void onUp()  {
	        if(nodoSelect == null) {
	            initSelection();
	            return;
	        }

	        TreeNode prev = findPrev(nodoSelect); 
	        if(prev != null)    {
	        	nodoSelect.setSelected(false);
	        	nodoSelect = prev;
	        	nodoSelect.setSelected(true);
	        }
	    }
	 
	 private void initSelection() {
	        List<TreeNode> children = root.getChildren();
	        if(!children.isEmpty()) {
	            nodoSelect = children.get(0);
	            nodoSelect.setSelected(true);
	        }
	    }
	 
	  public void onDown() { 
		  System.out.println("1");
		  if(nodoSelect == null)  {
	            initSelection();
	            return;
	        }

	        
	        TreeNode next = findNext(nodoSelect);
	        if(next != null)
	        {
	        	nodoSelect.setSelected(false);
	        	nodoSelect = next;
	        	nodoSelect.setSelected(true);
	        }
	    }
	  
	  private TreeNode findNext(TreeNode node)
	    {
	        TreeNode parent = node.getParent();
	        if(parent == null)
	        {
	            return null;
	        }

	        List<TreeNode> brothers = parent.getChildren();
	        int index = brothers.indexOf(node);
	        if(index < brothers.size() - 1)
	        {
	            return brothers.get(index + 1);
	        }
	        System.out.println("ya no llego por aca");

	        return findNext(parent);
	    }

	    private TreeNode findPrev(TreeNode node)
	    {
	        TreeNode parent = node.getParent();
	        if(parent == null)
	        {
	            return null;
	        }

	        List<TreeNode> brothers = parent.getChildren();
	        int index = brothers.indexOf(node);
	        if(index > 0)
	        {
	            return findLastUnexpanded(brothers.get(index - 1));
	        }

	        if(!parent.equals(root))
	        {
	            return parent;
	        }

	        return null;

	    }
	 
	    private TreeNode findLastUnexpanded(TreeNode node)
	    {
	        if(!node.isExpanded())
	        {
	            return node;
	        }

	        List<TreeNode> children = node.getChildren();
	        if(children.isEmpty())
	        {
	            return node;
	        }

	        return findLastUnexpanded(Iterables.getLast(children));
	    }
	  
	 /**** Cerrar Expediente
	  * 
	  * 
	  */
	 
	 public void cerrarExpediente(){
		 RequestContext context = RequestContext.getCurrentInstance();
		 ExpedienteTracking et = new ExpedienteTracking();
		 et.setId_oficina(usuarioLogin.getId_oficina());
		 boolean result = expedienteTrackingService.insertarTrackingGeneral(expedienteSelected, et, usuarioLogin.getIdusuario(), Constante.EST_SUBIO_ARCHIVOS);
		 
		 if(result) {
			 
			 	//log.setAccion("INSERT");
			 	//log.setDescripcion("Se insert� el expediente : " + this.expedienteSelected.getNro_expediente());
			 	//logmb.insertarLog(log);
			 	this.resultMessage = new ResultBean();
				resultMessage.setNro_expediente(expedienteSelected.getNro_expediente());
				resultMessage.setNro_archivos_agrupados(listaDocumentosAgrupados.size());
			 	
				context.update("dlgDetSuccesCerrarExp");
				context.execute("PF('dlgSuccessCerrarExp').show()");				
				
			 	context.update("tvContCal:fmdt:treeDocumentos");
				context.update("tvContCal:pngdd");
				context.update("tvContCal:fmdt:info_estado");
				
				try {
									
					listarDocumentosCantidades(_id_Expediente);
					limpiarListas();
					initBooleanValues();
					initStringValues();
					resetValuesInfoEstado();
					context.update("tvContCal:fmdt:pnlGroupedInfo");
					context.update("tvContCal:fmdt:pnlFrame");
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		 } else{
			 System.out.println("No se cerro el archivo");
		 }
	 }
	
		public void setearEliminar(Expediente ex){
			this.expedSelected = ex;
		}
		
		public void eliminar(){
			 RequestContext context = RequestContext.getCurrentInstance();
			try {				
				
				this.expedienteService.eliminarExpediente(expedSelected.getExpediente_id());
				log.setAccion("DELETE");
			 	log.setDescripcion("Se elimin� el expediente : " + this.expedienteSelected.getNro_expediente());
			 	logmb.insertarLog(log);
				buscarExpedientes();
				
				FacesUtils.showFacesMessage("Se elimin� correctamente el expediente nro " +expedSelected.getNro_expediente(), Constante.INFORMACION);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public void buscarExpedientes(){
			try {
				
				RequestContext context = RequestContext.getCurrentInstance();				
				System.out.println("ID OFICINA: "+this.f_idoficina);
				this.listaExpedientes = this.expedienteService.buscarExpedientes(this.f_idoficina);
				this.renderedPnlDescargar = Boolean.FALSE;
				
				context.update("downFile");
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	 
		
		// Marcar/Desmarcar Tree's CheckBoxes 
		
		public void marcarOrDesmarcarCheckBox(){
			if(this.renderedSelectAllCheckBox){
				System.out.println("Marcar Todos");
				selectAllCheckBoxes();
			} else{
				System.out.println("Desmarcar Todos");
				deselectAllCheckBoxes();
			}
		}
		
		private void selectAllCheckBoxes(){
			List<TreeNode> nodes = this.root.getChildren();
			this.nodo_archivosSelected = new TreeNode[nodes.size()];
			
			for (int i = 0; i < nodes.size(); i++) {
				this.nodo_archivosSelected[i]= nodes.get(i);
				this.nodo_archivosSelected[i].setSelected(Boolean.TRUE);
			}
		}
		
		private void deselectAllCheckBoxes(){
			for (TreeNode node : nodo_archivosSelected) {
				node.setSelected(Boolean.FALSE);
				nodo_archivosSelected = ArrayUtils.removeElement(nodo_archivosSelected, node);
				
			}
		}
		
		
		
		
	/** ------------------------------ Getters and Setters ---------------------------- */


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

	public ExpedienteDocumento getExpDocumentoSelected() {
		return expDocumentoSelected;
	}

	public void setExpDocumentoSelected(ExpedienteDocumento expDocumentoSelected) {
		this.expDocumentoSelected = expDocumentoSelected;
	}

	public String getRuta_completa() {
		return ruta_completa;
	}

	public void setRuta_completa(String ruta_completa) {
		this.ruta_completa = ruta_completa;
	}

	public byte[] getDatafinal() {
		return datafinal;
	}

	public void setDatafinal(byte[] datafinal) {
		this.datafinal = datafinal;
	}

	public boolean isRenderedFrame() {
		return renderedFrame;
	}

	public void setRenderedFrame(boolean renderedFrame) {
		this.renderedFrame = renderedFrame;
	}

	public TreeNode getRoot() {
		return root;
	}

	public void setRoot(TreeNode root) {
		this.root = root;
	}

	public TreeNode getNodoSelect() {
		return nodoSelect;
	}

	public void setNodoSelect(TreeNode nodoSelect) {
		this.nodoSelect = nodoSelect;
	}

	public boolean isRenderedBtnSave() {
		return renderedBtnSave;
	}

	public void setRenderedBtnSave(boolean renderedBtnSave) {
		this.renderedBtnSave = renderedBtnSave;
	}

	public ExpedienteDocumento getExpDocumentoReemplazo() {
		return expDocumentoReemplazo;
	}

	public void setExpDocumentoReemplazo(ExpedienteDocumento expDocumentoReemplazo) {
		this.expDocumentoReemplazo = expDocumentoReemplazo;
	}

	public boolean isRenderedTree() {
		return renderedTree;
	}

	public void setRenderedTree(boolean renderedTree) {
		this.renderedTree = renderedTree;
	}

	public boolean isRenderedTreeGroup() {
		return renderedTreeGroup;
	}

	public void setRenderedTreeGroup(boolean renderedTreeGroup) {
		this.renderedTreeGroup = renderedTreeGroup;
	}

	public boolean isRenderedBtnAgrup() {
		return renderedBtnAgrup;
	}

	public void setRenderedBtnAgrup(boolean renderedBtnAgrup) {
		this.renderedBtnAgrup = renderedBtnAgrup;
	}

	public List<ExpedienteDocumento> getListaDocumentos() {
		return listaDocumentos;
	}

	public void setListaDocumentos(List<ExpedienteDocumento> listaDocumentos) {
		this.listaDocumentos = listaDocumentos;
	}

	public TreeNode[] getNodo_archivosSelected() {
		return nodo_archivosSelected;
	}

	public void setNodo_archivosSelected(TreeNode[] nodo_archivosSelected) {
		this.nodo_archivosSelected = nodo_archivosSelected;
	}

	public Integer getNro_archivos_selected() {
		return nro_archivos_selected;
	}

	public void setNro_archivos_selected(Integer nro_archivos_selected) {
		this.nro_archivos_selected = nro_archivos_selected;
	}

	public List<ExpedienteDocumento> getListArchivosSelected() {
		return listArchivosSelected;
	}

	public void setListArchivosSelected(
			List<ExpedienteDocumento> listArchivosSelected) {
		this.listArchivosSelected = listArchivosSelected;
	}

	public String getNombreArchivoAgrupado() {
		return nombreArchivoAgrupado;
	}

	public void setNombreArchivoAgrupado(String nombreArchivoAgrupado) {
		this.nombreArchivoAgrupado = nombreArchivoAgrupado;
	}

	public Integer get_id_Expediente() {
		return _id_Expediente;
	}

	public void set_id_Expediente(Integer _id_Expediente) {
		this._id_Expediente = _id_Expediente;
	}

	public String getEstado_exp_busqueda() {
		return estado_exp_busqueda;
	}

	public void setEstado_exp_busqueda(String estado_exp_busqueda) {
		this.estado_exp_busqueda = estado_exp_busqueda;
	}

	public boolean isRenderedPnlGroup() {
		return renderedPnlGroup;
	}

	public void setRenderedPnlGroup(boolean renderedPnlGroup) {
		this.renderedPnlGroup = renderedPnlGroup;
	}

	public boolean isRenderedPnlBusqueda() {
		return renderedPnlBusqueda;
	}

	public void setRenderedPnlBusqueda(boolean renderedPnlBusqueda) {
		this.renderedPnlBusqueda = renderedPnlBusqueda;
	}

	public ExpedienteDocumento getSelectedExpDocGropuped() {
		return selectedExpDocGropuped;
	}

	public void setSelectedExpDocGropuped(ExpedienteDocumento selectedExpDocGropuped) {
		this.selectedExpDocGropuped = selectedExpDocGropuped;
	}

	public boolean isRenderedVisorGrouped() {
		return renderedVisorGrouped;
	}

	public void setRenderedVisorGrouped(boolean renderedVisorGrouped) {
		this.renderedVisorGrouped = renderedVisorGrouped;
	}

	public Integer get_totalxAgrupar() {
		return _totalxAgrupar;
	}

	public void set_totalxAgrupar(Integer _totalxAgrupar) {
		this._totalxAgrupar = _totalxAgrupar;
	}

	public Integer get_totalAgrupadas() {
		return _totalAgrupadas;
	}

	public void set_totalAgrupadas(Integer _totalAgrupadas) {
		this._totalAgrupadas = _totalAgrupadas;
	}

	public String getTipOperacion_NewFile() {
		return tipOperacion_NewFile;
	}

	public void setTipOperacion_NewFile(String tipOperacion_NewFile) {
		this.tipOperacion_NewFile = tipOperacion_NewFile;
	}

	public List<ExpedienteDocumento> getListaDocumentosAgrupados() {
		return listaDocumentosAgrupados;
	}

	public void setListaDocumentosAgrupados(
			List<ExpedienteDocumento> listaDocumentosAgrupados) {
		this.listaDocumentosAgrupados = listaDocumentosAgrupados;
	}

	public boolean isRenderedPgCerrar() {
		return renderedPgCerrar;
	}

	public void setRenderedPgCerrar(boolean renderedPgCerrar) {
		this.renderedPgCerrar = renderedPgCerrar;
	}

	public Expediente getExpedienteSelected() {
		return expedienteSelected;
	}

	public void setExpedienteSelected(Expediente expedienteSelected) {
		this.expedienteSelected = expedienteSelected;
	}

	public String getMsjeExpNoPCC() {
		return msjeExpNoPCC;
	}

	public void setMsjeExpNoPCC(String msjeExpNoPCC) {
		this.msjeExpNoPCC = msjeExpNoPCC;
	}

	public List<Oficina> getListaOficinas() {
		return listaOficinas;
	}

	public void setListaOficinas(List<Oficina> listaOficinas) {
		this.listaOficinas = listaOficinas;
	}

	public Integer getF_idoficina() {
		return f_idoficina;
	}

	public void setF_idoficina(Integer f_idoficina) {
		this.f_idoficina = f_idoficina;
	}


	public List<Expediente> getListaExpedientes() {
		return listaExpedientes;
	}


	public void setListaExpedientes(List<Expediente> listaExpedientes) {
		this.listaExpedientes = listaExpedientes;
	}


	public List<Expediente> getListaExpedientesFilter() {
		return listaExpedientesFilter;
	}


	public void setListaExpedientesFilter(List<Expediente> listaExpedientesFilter) {
		this.listaExpedientesFilter = listaExpedientesFilter;
	}


	public Expediente getExpedSelected() {
		return expedSelected;
	}


	public void setExpedSelected(Expediente expedSelected) {
		this.expedSelected = expedSelected;
	}


	public boolean isRenderedSelectAllCheckBox() {
		return renderedSelectAllCheckBox;
	}


	public void setRenderedSelectAllCheckBox(boolean renderedSelectAllCheckBox) {
		this.renderedSelectAllCheckBox = renderedSelectAllCheckBox;
	}


	public List<ExpedienteDocumento> getListArchivosHijos() {
		return listArchivosHijos;
	}


	public void setListArchivosHijos(List<ExpedienteDocumento> listArchivosHijos) {
		this.listArchivosHijos = listArchivosHijos;
	}


	public ExpedienteDocumento getSelectedExpDocHijo() {
		return selectedExpDocHijo;
	}


	public void setSelectedExpDocHijo(ExpedienteDocumento selectedExpDocHijo) {
		this.selectedExpDocHijo = selectedExpDocHijo;
	}


	public boolean isRenderedVisorHijo() {
		return renderedVisorHijo;
	}


	public void setRenderedVisorHijo(boolean renderedVisorHijo) {
		this.renderedVisorHijo = renderedVisorHijo;
	}


	public ResultBean getResultMessage() {
		return resultMessage;
	}


	public void setResultMessage(ResultBean resultMessage) {
		this.resultMessage = resultMessage;
	}


	public String getIpcertiscan() {
		return ipcertiscan;
	}


	public void setIpcertiscan(String ipcertiscan) {
		this.ipcertiscan = ipcertiscan;
	}


	public Integer get_totalDigitalizados() {
		return _totalDigitalizados;
	}


	public void set_totalDigitalizados(Integer _totalDigitalizados) {
		this._totalDigitalizados = _totalDigitalizados;
	}


	public Integer get_totalDigitalizadosReempl() {
		return _totalDigitalizadosReempl;
	}


	public void set_totalDigitalizadosReempl(Integer _totalDigitalizadosReempl) {
		this._totalDigitalizadosReempl = _totalDigitalizadosReempl;
	}


	public Integer get_totalDigitalizadosElim() {
		return _totalDigitalizadosElim;
	}


	public void set_totalDigitalizadosElim(Integer _totalDigitalizadosElim) {
		this._totalDigitalizadosElim = _totalDigitalizadosElim;
	}


	public boolean isRenderedNuevoCancelar() {
		return renderedNuevoCancelar;
	}


	public void setRenderedNuevoCancelar(boolean renderedNuevoCancelar) {
		this.renderedNuevoCancelar = renderedNuevoCancelar;
	}


	public Integer getNro_pagina_actual() {
		return nro_pagina_actual;
	}


	public void setNro_pagina_actual(Integer nro_pagina_actual) {
		this.nro_pagina_actual = nro_pagina_actual;
	}


	public Integer getNumero_documento() {
		return numero_documento;
	}


	public void setNumero_documento(Integer numero_documento) {
		this.numero_documento = numero_documento;
	}


	public boolean isBtnAnterior() {
		return btnAnterior;
	}


	public void setBtnAnterior(boolean btnAnterior) {
		this.btnAnterior = btnAnterior;
	}


	public boolean isBtnSiguiente() {
		return btnSiguiente;
	}


	public void setBtnSiguiente(boolean btnSiguiente) {
		this.btnSiguiente = btnSiguiente;
	}


	public String getNombreArchivo() {
		return nombreArchivo;
	}


	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}


	public boolean isRenderedTextoInformativo() {
		return renderedTextoInformativo;
	}


	public void setRenderedTextoInformativo(boolean renderedTextoInformativo) {
		this.renderedTextoInformativo = renderedTextoInformativo;
	}


	public boolean isTxtNumeroPagina() {
		return txtNumeroPagina;
	}


	public void setTxtNumeroPagina(boolean txtNumeroPagina) {
		this.txtNumeroPagina = txtNumeroPagina;
	}


	public boolean isBtnAnteriorAgr() {
		return btnAnteriorAgr;
	}


	public void setBtnAnteriorAgr(boolean btnAnteriorAgr) {
		this.btnAnteriorAgr = btnAnteriorAgr;
	}


	public boolean isBtnSiguienteAgr() {
		return btnSiguienteAgr;
	}


	public void setBtnSiguienteAgr(boolean btnSiguienteAgr) {
		this.btnSiguienteAgr = btnSiguienteAgr;
	}


	public String getNombreArchivoAgr() {
		return nombreArchivoAgr;
	}


	public void setNombreArchivoAgr(String nombreArchivoAgr) {
		this.nombreArchivoAgr = nombreArchivoAgr;
	}


	public Integer getNumero_documentoAgr() {
		return numero_documentoAgr;
	}


	public void setNumero_documentoAgr(Integer numero_documentoAgr) {
		this.numero_documentoAgr = numero_documentoAgr;
	}


	public boolean isTxtNumeroPaginaAgr() {
		return txtNumeroPaginaAgr;
	}


	public void setTxtNumeroPaginaAgr(boolean txtNumeroPaginaAgr) {
		this.txtNumeroPaginaAgr = txtNumeroPaginaAgr;
	}


	public boolean isTxtNumeroPaginaDesAgr() {
		return txtNumeroPaginaDesAgr;
	}


	public void setTxtNumeroPaginaDesAgr(boolean txtNumeroPaginaDesAgr) {
		this.txtNumeroPaginaDesAgr = txtNumeroPaginaDesAgr;
	}


	public boolean isBtnAnteriorDesAgr() {
		return btnAnteriorDesAgr;
	}


	public void setBtnAnteriorDesAgr(boolean btnAnteriorDesAgr) {
		this.btnAnteriorDesAgr = btnAnteriorDesAgr;
	}


	public boolean isBtnSiguienteDesAgr() {
		return btnSiguienteDesAgr;
	}


	public void setBtnSiguienteDesAgr(boolean btnSiguienteDesAgr) {
		this.btnSiguienteDesAgr = btnSiguienteDesAgr;
	}


	public String getNombreArchivoDesAgr() {
		return nombreArchivoDesAgr;
	}


	public void setNombreArchivoDesAgr(String nombreArchivoDesAgr) {
		this.nombreArchivoDesAgr = nombreArchivoDesAgr;
	}


	public Integer getNumero_documentoDesAgr() {
		return numero_documentoDesAgr;
	}


	public void setNumero_documentoDesAgr(Integer numero_documentoDesAgr) {
		this.numero_documentoDesAgr = numero_documentoDesAgr;
	}


	public Integer getValorOpcion() {
		return valorOpcion;
	}


	public void setValorOpcion(Integer valorOpcion) {
		this.valorOpcion = valorOpcion;
	}


	public String getNuevoNombre() {
		return nuevoNombre;
	}


	public void setNuevoNombre(String nuevoNombre) {
		this.nuevoNombre = nuevoNombre;
	}


	public String getNameFileG() {
		return nameFileG;
	}


	public void setNameFileG(String nameFileG) {
		this.nameFileG = nameFileG;
	}


	public StreamedContent getFileExp() {
		return fileExp;
	}


	public void setFileExp(StreamedContent fileExp) {
		this.fileExp = fileExp;
	}


	public boolean isRenderedPnlDescargar() {
		return renderedPnlDescargar;
	}


	public void setRenderedPnlDescargar(boolean renderedPnlDescargar) {
		this.renderedPnlDescargar = renderedPnlDescargar;
	}


	public boolean isExisteG() {
		return existeG;
	}


	public void setExisteG(boolean existeG) {
		this.existeG = existeG;
	}


	public ExpedienteDocumento getExpDocG() {
		return expDocG;
	}


	public void setExpDocG(ExpedienteDocumento expDocG) {
		this.expDocG = expDocG;
	}	
	
	
	
}
