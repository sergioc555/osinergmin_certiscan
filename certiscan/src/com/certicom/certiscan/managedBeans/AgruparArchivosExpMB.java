package com.certicom.certiscan.managedBeans;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseStream;
import javax.servlet.http.HttpServletResponse;

//import org.apache.commons.fileupload.FileUpload;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.DefaultUploadedFile;
import org.primefaces.model.TreeNode;
import org.primefaces.model.UploadedFile;
//import sun.org.mozilla.javascript.internal.Context;

import com.certicom.certiscan.commons.Constante;
import com.certicom.certiscan.commons.FacesUtils;
import com.certicom.certiscan.commons.Utiles;
import com.certicom.certiscan.domain.DocumentoPagina;
import com.certicom.certiscan.domain.Expediente;
import com.certicom.certiscan.domain.ExpedienteDocumento;
import com.certicom.certiscan.domain.Log;
import com.certicom.certiscan.domain.Menu;
import com.certicom.certiscan.domain.Usuario;
import com.certicom.certiscan.services.ExpedienteDocumentoService;
import com.certicom.certiscan.services.ExpedienteService;
import com.certicom.certiscan.services.MenuServices;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.lowagie.text.pdf.PdfDocument;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import com.sun.xml.internal.ws.client.ResponseContext;

import src.com.certicom.certiscan.utils.Utils;

@ManagedBean(name="agruArchExp")
@ViewScoped
public class AgruparArchivosExpMB implements Serializable {
	
	private boolean renderedRegistro;
	private boolean renderedFileUpload;
	private boolean archivoCargado;
	private boolean renderedBtnSave;
	private boolean initFieldsRegistro;
	private boolean renderedTablaDocumento;
	
	private boolean renderedTree;
	private boolean renderedTreeGroup;
	
	
	private String ruta_completa;
	private byte[] datafinal;
	
	private Integer nro_archivos_subidos = 0;
	private String linkPdf = "";
	private boolean renderedFrame;
	
	private File fileReemplazo = null;
	
	private Log log;
	private LogMB logmb;
	private MenuServices menuServices;
	
	private List<UploadedFile> archivoCargaList = new LinkedList<UploadedFile>();
	private List<ExpedienteDocumento> listaDocumentos = new ArrayList<>();
	private List<ExpedienteDocumento> listaDocumentosRegistrados = new ArrayList<>();
	private List<ExpedienteDocumento> listaDocumentosRegistradosF;
	
	
	
	private Expediente nuevoExpediente;
	private ExpedienteDocumento expDocumentoSelected;
	private ExpedienteDocumento expDocumentoReemplazo;
	
	private ExpedienteService expedienteService;
	private ExpedienteDocumentoService expedienteDocumentoService;
	
	
	@ManagedProperty(value = "#{loginMB.usuario}")
	private Usuario usuarioLogin;

	
	private TreeNode root;
	private TreeNode nodoSelect;
	
	@ManagedProperty(value= "#{loginMB}")
	private LoginMB login;
	
	@PostConstruct
	public void inicio(){
		
		this.expedienteService = new ExpedienteService();
		this.expedienteDocumentoService = new ExpedienteDocumentoService();
		
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
		
		this.menuServices = new MenuServices();
		
		try {
			Menu codMenu = menuServices.opcionMenuByPretty("pretty:agruparArchivosExp");
			log.setCod_menu(codMenu.getCod_menu().intValue());
			log.setIdusuario(this.login.getIdUsuario());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
	
	public void listarArchivosRegistrado() throws Exception{
		RequestContext context = RequestContext.getCurrentInstance();
		this.renderedTablaDocumento = Boolean.TRUE;
		
		Expediente ex = this.expedienteService.findByNroExpediente(this.nuevoExpediente.getNro_expediente());
		
		if(ex != null){
			this.listaDocumentosRegistrados = this.expedienteDocumentoService.listFilesbyExpediente_id(ex.getExpediente_id());
			//context.update("fmdt:dataTable2");
			
			generateTree(listaDocumentosRegistrados);
			
		} else{
			context.execute("PF('dlgAviso').show()");
		}
		
		
	}
	
	private void generateTree(List<ExpedienteDocumento> _listaExD){
		this.root = new DefaultTreeNode("Root",null);
		for (ExpedienteDocumento ed : _listaExD) {
			new DefaultTreeNode(ed.getId_expediente_documento()+"-"+ed.getNombre_archivo(), this.root);
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
	
	
	public void onNodeSelect(NodeSelectEvent event) {
		this.renderedFrame = Boolean.TRUE;
		String title = event.getTreeNode().getData().toString();
		Integer id = Integer.parseInt(title.substring(0,title.indexOf("-")));
		try {
			this.expDocumentoSelected = expedienteDocumentoService.findByID(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void eliminarArchivo(){
		RequestContext context = RequestContext.getCurrentInstance();
		String data = this.nodoSelect.getData().toString();
		Integer id = Integer.parseInt(data.substring(0,data.indexOf("-")));
		System.out.println("id a eliminar " +id);
		try {
			this.expDocumentoSelected = expedienteDocumentoService.findByID(id);
			if(this.expDocumentoSelected!= null){
				
				
				
				boolean result = expedienteDocumentoService.updateEstadoEliminadoArchivoPaginas(this.expDocumentoSelected);
				
				
	            
				if(result){
					Utiles.moveFileToDeleted(expDocumentoSelected);
					log.setAccion("UPDATE");
					log.setDescripcion("Se cambio al estado ELIMINADO el Archivo "+ this.expDocumentoSelected.getNombre_archivo());
		            logmb.insertarLog(log);
		            
		            log.setAccion("UPDATE");
					log.setDescripcion("Se cambio al estado ELIMINADO las p�ginas del Archivo "+ this.expDocumentoSelected.getNombre_archivo());
		            logmb.insertarLog(log);
		            
					listarArchivosRegistrado();
					context.update("fmdt:treeDocumentos");
					context.update("fmdt:pnlFrame");
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
		String data = this.nodoSelect.getData().toString();
		Integer id = Integer.parseInt(data.substring(0,data.indexOf("-")));
		System.out.println("id a reemplazar " +id);
		
		try {
			this.expDocumentoSelected = expedienteDocumentoService.findByID(id);
			this.renderedBtnSave = Boolean.TRUE;
		} catch (Exception e) {
			e.printStackTrace();
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
	
	
	public void subirArchivoReemplazo(){
		RequestContext context = RequestContext.getCurrentInstance();
		
		boolean result = expedienteDocumentoService.updateEstadoReemplazoArchivoPaginas(expDocumentoSelected, expDocumentoReemplazo, usuarioLogin.getIdusuario(),log);
		
		
        
		if(result) {
			
			//log.setAccion("UPDATE");
			//log.setDescripcion("Se cambio al estado REEMPLAZADO el Archivo "+ this.expDocumentoSelected.getNombre_archivo());
	        //logmb.insertarLog(log);
	        
		//	Utiles.moveFileToReplaced(this.fileReemplazo, expDocumentoSelected);
			context.update("fmdt:pnlFrame");
			
		} else{
			System.out.println("No se pudo reemplazar el archivo");
		}
	}
	
	
	// Agrupar Archivos
	
	public void setearAgruparArchivos(){
		System.out.println("hola q hay");
		this.renderedTree = Boolean.FALSE;
		this.renderedTreeGroup = Boolean.TRUE;
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

	public LoginMB getLogin() {
		return login;
	}

	public void setLogin(LoginMB login) {
		this.login = login;
	}
	
}
