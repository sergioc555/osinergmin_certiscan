package com.certicom.certiscan.managedBeans;

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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import src.com.certicom.certiscan.utils.Utils;

import com.certicom.certiscan.commons.Constante;
import com.certicom.certiscan.commons.FacesUtils;
import com.certicom.certiscan.commons.GenericBeans;
import com.certicom.certiscan.commons.Utiles;
import com.certicom.certiscan.domain.DocumentoPagina;
import com.certicom.certiscan.domain.Estados;
import com.certicom.certiscan.domain.Expediente;
import com.certicom.certiscan.domain.ExpedienteDocumento;
import com.certicom.certiscan.domain.ExpedienteTracking;
import com.certicom.certiscan.domain.Medios;
import com.certicom.certiscan.domain.Perfil;
import com.certicom.certiscan.domain.Usuario;
import com.certicom.certiscan.services.EstadosServices;
import com.certicom.certiscan.services.ExpedienteService;
import com.certicom.certiscan.services.ExpedienteTrackingService;
import com.certicom.certiscan.services.MediosServices;
import com.lowagie.text.pdf.PdfReader;


@ManagedBean(name="subirArchivosFirmadosMB")
@ViewScoped
public class SubirArchivosFirmadosMB extends GenericBeans implements Serializable{
	
	private Medios mediosFilter;
	
	private List<Medios> listaMedios;
	private List<Medios> listaMediosFilter;

	private boolean _activateCmb;
	private UploadedFile file;
	private File fileFinal;
	private boolean _activateDealer;
	private Integer totalExpeCargados;
	private boolean bexpediente;
	private boolean bfecha;
	private SelectItem[] listaCanales;
	private ExpedienteService expedienteService;
	private MediosServices mediosService;
	private ExpedienteTrackingService expedienteTrackingService;
	private RequestContext context;
	private Expediente expediente;
	private Expediente expSelected;
	private List<Estados> listEstados;
	private EstadosServices estadosServices;
	private Medios[] selectedMedioV;
	private StreamedContent fileExp;
	private String nom_arch;
	
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
	
	public SubirArchivosFirmadosMB(){
	}
	
	@PostConstruct
	public void inicia(){
		this.fechaActual = new Date();
		this.listaMedios = new ArrayList<>();
		this.listaExpTracking = new ArrayList<>();
		this._activateCmb = Boolean.FALSE;
		this._activateDealer = Boolean.TRUE;
		this.mediosFilter = new Medios();
		this.mediosFilter.setFiltradopor(1);
		this.bexpediente = true;
		this.bfecha = false;
		this.expSelected = new Expediente();
		this.estadosServices = new EstadosServices();
		this.listEstados = new ArrayList<Estados>();
		
		this.context = RequestContext.getCurrentInstance();
		this.mediosService = new MediosServices(); 
		this.expedienteTrackingService  = new ExpedienteTrackingService();
		
		try {
			
			this.listEstados = this.estadosServices.listaEstadosActivo();
			//this.totalExpeCargados = this.expedienteService.getExpedienteAsignados(this.usuarioLogin.getIdusuario());
			
			if(this.perfilUsuario.getProceso().equals("EXPEDIENTES PROPIOS") || this.perfilUsuario.getProceso().equals("OFICINA LOCAL")){
				this._activateCmb = true;
				this.mediosFilter.setId_oficina(this.usuarioLogin.getId_oficina());
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
	
	public void handleFileUpload(FileUploadEvent event) throws FileNotFoundException, IOException {
        UploadedFile file = event.getFile();
        
        System.out.println("entramos al método de carga");
        
        if(file == null){
        	System.out.println("No existe el archivo");
        }else{
        	System.out.println("Existe el archivo");
        }
    }
	
	public void prueba (){
		System.out.println("Esto es una prueba");
		for (Medios me : this.listaMedios) {
			
			System.out.println("Nombre: "+me.getDescripcion());
			
			System.out.println("---------------------");
			
			if(me.getUpFile() == null){
				System.out.println("No existe el archivo");
	        }else{
	        	System.out.println("Existe el archivo");
	        }
			
		}
		
	}
	
	public void cargarDocumentos(FileUploadEvent event) throws Exception{
		RequestContext context = RequestContext.getCurrentInstance();
		
		
		/*String fileName  = event.getFile().getFileName();
		ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
		String newFileName = servletContext.getRealPath("") + File.separator + "upload" +    File.separator+ fileName;*/
		
		if(event.getFile() != null) {
			
			this.file = event.getFile();
			InputStream c;
			
				
			
			//File temporal3 = File.createTempFile("prefix", ".pdf");
			File _archivo = File.createTempFile("prefix", ".zip");
			
			  InputStream in = event.getFile().getInputstream();
			  OutputStream out = new FileOutputStream(_archivo);
			  byte[] buf = new byte[1024];
	            int len;
	             
	            while ((len = in.read(buf)) > 0) {
	              out.write(buf, 0, len);
	            }
	            
	            in.close();
	            out.close();
	           this.fileFinal = _archivo;
	           this.nom_arch = file.getFileName();
	           StringBuffer cab = new StringBuffer("Las siguientes paginas del archivo "+  file.getFileName() + " no esta entre el rango 15kb-300kb :"); 
	           StringBuffer bf = new StringBuffer();
	           
	            
//	            System.out.println("PESO DEL ARCHIVO " +_archivo.length());
//	            PDDocument pdf_doc = PDDocument.load(_archivo);
//	            pdf_doc.
//	            PDPage page = pdf_doc.getPage(0);
//	            System.out.println("pdf box " +pdf_doc.getNumberOfPages());
//	            System.out.println("ss " + page.getContents().available());
	           
	           
	            
//	            PdfStamper stamper;
//   	            String path;
//   	            pf.selectPages("1-2");
//   	            path = String.format("E:/prueba_pdf/pdos-%s.pdf", 5);
//   	            stamper = new PdfStamper(pf,new FileOutputStream(path));
//   	            stamper.close();
	         
	          
	          }	          
	          
	          //context.addCallbackParam("valido", Boolean.FALSE);	
		
	}
	
	public void subirRegistrarDocumentos(){
		RequestContext context = RequestContext.getCurrentInstance();
		
		try {
				
				
				
//				for (ExpedienteDocumento ed : listaDocumentos) {
//					
//				}}			
				
			
		 //boolean res =	expedienteDocumentoService.insertDocumentosRegistros(this.nuevoExpediente.getExpediente_id(),listaDocumentos, this.usuarioLogin.getIdusuario(),this.nuevoExpediente, log);
		 
			 	//log.setAccion("INSERT");
		        //log.setDescripcion("Se registraron los documentos del expediente : "+this.nuevoExpediente.getNro_expediente());
		        //logmb.insertarLog(log);
			Utiles.enviarArchivoViaSFTP2(this.fileFinal, this.nom_arch, Constante.RUTA_MEDIOS_SUBIDOS);				
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void upLoad(Medios m){
		System.out.println("entramos al método de carga");
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
		et.setPlanos_a0(this.expSelected.getPlanos_a0());
		et.setPlanos_a1(this.expSelected.getPlanos_a1());
		et.setPlanos_a2(this.expSelected.getPlanos_a2());
		et.setPlanos_a3(this.expSelected.getPlanos_a3());
			
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
		
		if (this.mediosFilter.getFiltradopor() == null){
			existe = false;
		}else{
			existe = true;
		}
		
		//if (existe){
			try {
				this.listaMedios = new ArrayList<>();
				
				
				if(perfilUsuario.getCod_perfil() == 1 || perfilUsuario.getCod_perfil()==34){
					//System.out.println("Hola1");
				}else if(this.perfilUsuario.getProceso().equals("OFICINA LOCAL")){
					//System.out.println("Hola2");
				}else if(this.perfilUsuario.getProceso().equals("TODAS LAS OFICINAS")){ 
					//System.out.println("Hola3");
				}else{
					//System.out.println("Hola4");
					this.mediosFilter.setIdusuario(getUsuarioLogin().getIdusuario());
				}
				//System.out.println("Imprimimos el valor del Cod_Delaer:"+this.expedienteFilter.getCod_dealer());			
			
				this.listaMedios = this.mediosService.consultaMedios(mediosFilter);
				
				/*for(int i= 0; i<listaExpedientes.size(); i++){
					System.out.println("Nro solicitud:"+ listaExpedientes.get(i).getNrosolicitud() +"Fecha de Rec:"+listaExpedientes.get(i).getFec_rec());
				}*/
				
				this.totalExpeCargados = this.listaMedios.size();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
	//	}else{
	//		FacesUtils.showFacesMessage("Por favor, ingresar un parametro de búsqueda",Constante.ADVERTENCIA);
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
		this.fechaLimite = this.mediosFilter.getFec_ini();
		
	}
	
	public void cambiarCombo(){
		if(this.mediosFilter.getFiltradopor()==1){
			this.mediosFilter.setFec_ini(null);
			this.mediosFilter.setFec_fin(null);
			this.bexpediente = true;
			this.bfecha = false;
		}else{
			this.mediosFilter.setVfiltradopor(null);
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
	
	public void descargarArchivos2(Medios me){
		try {			
				System.out.println("boton 2");							
				this.fileExp = crearSimpleZipArchivos(me);			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public void descargarArchivos3(){
		try {
			System.out.println("boton 3");
			
				this.fileExp = crearMultipleZipArchivos();
		
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public StreamedContent crearSimpleZipArchivos(Medios med){
		StreamedContent fil = null;
		byte[] buffer = new byte[1024];
		try {
			
			List<ByteArrayOutputStream> zipList = new ArrayList<ByteArrayOutputStream>();
			
			
			
			File _archivo = File.createTempFile(med.getDescripcion(), ".zip");
			FileOutputStream fos = new FileOutputStream(_archivo);
			BufferedOutputStream bs = null;
			bs = new BufferedOutputStream(fos);
		    bs.write(Utiles.descargarArchivoViaSFTP3(Constante.RUTA_MEDIOS+med.getDescripcion()+".zip"));
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
			
			zos.close();*/			
			    //ed.setBaos(baos);
			
			
			/*ByteArrayOutputStream out = new ByteArrayOutputStream();
			ZipOutputStream zos = new ZipOutputStream(out);

			int j = 1, ni = 0;
			for(ExpedienteDocumento ed: lstcr){				
				//ZipEntry ze = new ZipEntry("ruc_" + j + "_zip.zip");
				System.out.println("NOMBRE SIN ZIP: "+lstcr.get(ni).getNombre_archivo().substring(0, lstcr.get(ni).getNombre_archivo().length()-4));
				ZipEntry ze = new ZipEntry("" + lstcr.get(ni).getNombre_archivo().substring(0, lstcr.get(ni).getNombre_archivo().length()-4) + ".pdf");
				zos.putNextEntry(ze);
				InputStream in = new ByteArrayInputStream(ed.getBaos().toByteArray());
				int len;
				while ((len = in.read(buffer)) > 0) {
					zos.write(buffer, 0, len);
				}
				
				in.close();
				zos.closeEntry();
				j++;
				ni++;
			}

			zos.close();*/
				
			return fil = new DefaultStreamedContent(new ByteArrayInputStream(bos.toByteArray()), "application/zip", ""+med.getDescripcion()+".zip");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			return fil;
		}		
	    
	}
	
	public StreamedContent crearMultipleZipArchivos(){
		StreamedContent fil = null;
		byte[] buffer = new byte[1024];
		try {
			
			List<ByteArrayOutputStream> zipList = new ArrayList<ByteArrayOutputStream>();
						
				
				for (int i = 0; i < selectedMedioV.length; i++) {				
				
				byte[] data = Utiles.descargarArchivoViaSFTP3(Constante.RUTA_MEDIOS+this.selectedMedioV[i].getDescripcion()+".zip");					
				
				if (!(data == null)){
				
				File _archivo = File.createTempFile(this.selectedMedioV[i].getDescripcion(), ".zip");
				FileOutputStream fos = new FileOutputStream(_archivo);
				BufferedOutputStream bs = null;
				
				bs = new BufferedOutputStream(fos);
			    //bs.write(Utiles.descargarArchivoViaSFTP(Constante.RUTA_ARCHIVO+ed.getNombre_archivo(), ed.getNombre_archivo()));
				bs.write(data);
			    bs.close();			    
			      
				ByteArrayOutputStream zip = new ByteArrayOutputStream();				
				      
				      ByteArrayOutputStream baos = new ByteArrayOutputStream();
				      FileInputStream f = new FileInputStream(_archivo);
				      
				      ByteArrayOutputStream bos = new ByteArrayOutputStream();
				        byte[] buf = new byte[1024];
				        try {
				            for (int readNum; (readNum = f.read(buf)) != -1;) {
				                bos.write(buf, 0, readNum); //no doubt here is 0
				            }
				        } catch (IOException ex) {
				            
				        }			      
				      		
				  this.selectedMedioV[i].setBaos(bos);
				    
				}
					
				}				
			
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				ZipOutputStream zos = new ZipOutputStream(out);
	
				int j = 1, ni = 0;
				for(int i = 0; i < selectedMedioV.length; i++){				
					//ZipEntry ze = new ZipEntry("ruc_" + j + "_zip.zip");
					System.out.println("NOMBRE SIN ZIP: "+this.selectedMedioV[i].getDescripcion() + ".zip");
					ZipEntry ze = new ZipEntry("" + this.selectedMedioV[i].getDescripcion() + ".zip");
					zos.putNextEntry(ze);
					InputStream in = new ByteArrayInputStream(this.selectedMedioV[i].getBaos().toByteArray());
					int len;
					while ((len = in.read(buffer)) > 0) {
						
						zos.write(buffer, 0, len);
												
					}
					
					in.close();
					zos.closeEntry();
					
					j++;
					ni++;
				}
				//this.selectedExpedienteV[i].setBaos(out);
				zipList.add(out);
				zos.close();
			
			return fil = new DefaultStreamedContent(new ByteArrayInputStream(out.toByteArray()), "application/zip", "DescargaMasiva.zip");
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			return fil;
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

	public Medios getMediosFilter() {
		return mediosFilter;
	}

	public void setMediosFilter(Medios mediosFilter) {
		this.mediosFilter = mediosFilter;
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

	public List<Medios> getListaMedios() {
		return listaMedios;
	}

	public void setListaMedios(List<Medios> listaMedios) {
		this.listaMedios = listaMedios;
	}

	public List<Medios> getListaMediosFilter() {
		return listaMediosFilter;
	}

	public void setListaMediosFilter(List<Medios> listaMediosFilter) {
		this.listaMediosFilter = listaMediosFilter;
	}

	public Medios[] getSelectedMedioV() {
		return selectedMedioV;
	}

	public void setSelectedMedioV(Medios[] selectedMedioV) {
		this.selectedMedioV = selectedMedioV;
	}

	public StreamedContent getFileExp() {
		return fileExp;
	}

	public void setFileExp(StreamedContent fileExp) {
		this.fileExp = fileExp;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public File getFileFinal() {
		return fileFinal;
	}

	public void setFileFinal(File fileFinal) {
		this.fileFinal = fileFinal;
	}

	public String getNom_arch() {
		return nom_arch;
	}

	public void setNom_arch(String nom_arch) {
		this.nom_arch = nom_arch;
	}
	
	
	
}
