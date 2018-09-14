package com.certicom.certiscan.managedBeans;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import src.com.certicom.certiscan.utils.LoteFilter;
import src.com.certicom.certiscan.utils.Utils;

import com.certicom.certiscan.commons.Constante;
import com.certicom.certiscan.commons.FacesUtils;
import com.certicom.certiscan.commons.GenericBeans;
import com.certicom.certiscan.commons.Utiles;
import com.certicom.certiscan.domain.Estado;
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
import com.certicom.certiscan.services.LoteServices;
import com.certicom.certiscan.services.MediosServices;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;


@ManagedBean(name="generaMediosMB_")
@ViewScoped
public class GeneraMediosMB_ extends GenericBeans implements Serializable{
	
	private ExpedienteDocumento expedienteDocumentoFilter;
	
	private List<ExpedienteDocumento> listaExpedienteDocumentos;
	private List<ExpedienteDocumento> listaExpedienteDocumentosVista;
	private List<ExpedienteDocumento> listaExpedienteDocumentosFilter;
	private List<Expediente> listExpedientes;
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
	private ExpedienteIncidencia expIncSelected;
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
	
	public GeneraMediosMB_(){
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
		this.expIncSelected = new ExpedienteIncidencia();
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
	
	public void eliminarIndidencia(ExpedienteIncidencia einc){
		this.expIncSelected = einc;
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
			//this.listMedios = crearMultipleZipArchivos();
			
			//this.mediosServices.generarMedios(this.listMedios, this.usuarioLogin.getIdusuario(), this.usuarioLogin.getId_oficina());		
			
			//this.mediosServices.actualizarMediosCreados(this.listMedios);
			
				this.listMedios = new ArrayList<Medios>();	
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
	
	}
	
	public void comprimirBash(){
		
		System.out.println("vamos a recorrer");
		
		try {
					
			for (Medios m : listMedios) {
				
				Date fec_ini = new Date();
				
				Utiles.ejecutarBash(m);
				
				Date fec_fin = new Date();
				
				m.setFecha_ini(fec_ini);
				m.setFecha_fin(fec_fin);
				
				this.mediosServices.actualizarFechaInicioFin(m);
				
			}
		
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public List<Medios> crearMultipleZipArchivos(){
		
			
		new Thread(){
			public void run(){
				
				try {
					
					//List<byte[]> zipList = new ArrayList<byte[]>();
					//int counter = 1;
					for (Medios m : listMedios) {
						//System.out.println("file: " + counter);
						//counter++;
						//System.out.println("Size Lista Medios: "+ listMedios.size());						
						//int index = 0;
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
							
							
							
//							if (!(data == null)){
//								System.out.println("Creamos el temFile");
//								//File _archivo = File.createTempFile(ed.getNombre_archivo().substring(0, ed.getNombre_archivo().length()-4), ".pdf");
//								File _archivo = File.createTempFile(ed.getNombre_archivo().substring(0, ed.getNombre_archivo().length()-4), ed.getNombre_archivo().substring(ed.getNombre_archivo().length()-4, ed.getNombre_archivo().length()));
//								FileOutputStream fos = new FileOutputStream(_archivo);
//								BufferedOutputStream bs = new BufferedOutputStream(fos);
//								
//								bs.write(data);
//							    bs.close();
//							    
//							    FileInputStream f = new FileInputStream(_archivo);	
//							    ByteArrayOutputStream bos = new ByteArrayOutputStream();
//						        byte[] buf = new byte[(int)_archivo.length()];
//						        try {
//						            for (int readNum; (readNum = f.read(buf)) != -1;) {
//						        	//for (int readNum; (readNum = f.read(data)) != -1;) {
//						                bos.write(buf, 0, readNum); //no doubt here is 0
//						                //Writes len bytes from the specified byte array starting at offset off to this byte array output stream.
//						                //System.out.println("read " + readNum + " bytes,");
//						            }
//						        } catch (Exception ex) {
//						        	ex.printStackTrace();
//						        	System.out.println(ex.getMessage());
//						            
//						        }
//						        
//						        ed.setBaos(bos);
//						        //ed.setFile(_archivo);
//							
//
//							}					
							
						}
						m.setRuta(m.getRuta());
						zos.close();
						File file = new File(m.getDescripcion()+".zip");
						FileOutputStream fos = new FileOutputStream (file); 
						out.writeTo(fos);
						Utiles.enviarArchivoViaSFTP2(file, m.getDescripcion()+".zip", Constante.RUTA_MEDIOS);
					

						
//						
//						int index = 0;
//						//FileOutputStream zipFileOu = new FileOutputStream(m.getDescripcion());
//						ByteArrayOutputStream out = new ByteArrayOutputStream();
//						ZipOutputStream zos = new ZipOutputStream(out);
//						for(ExpedienteDocumento ed: m.getListExpedienteDocumentos()){
//							
//							Utiles.addToZipFile(ed.getBaos(),ed.getFile().length(),"" +m.getListExpedienteDocumentos().get(index).getNombre_archivo(), zos);
//							index++;
//							
//							
//						}
//						m.setRuta(m.getRuta());
//						//zipFileOu.close();
//						zos.close();
//						File file = new File(m.getDescripcion()+".zip");
//						FileOutputStream fos = new FileOutputStream (file); 
//						out.writeTo(fos);
//						Utiles.enviarArchivoViaSFTP2(file, m.getDescripcion()+".zip", Constante.RUTA_MEDIOS);
//						
						
						
						
						
						
						
						
						
//						ByteArrayOutputStream out = new ByteArrayOutputStream();
//						ZipOutputStream zos = new ZipOutputStream(out);
//						int j = 1, ni = 0;						
//						for(ExpedienteDocumento ed: m.getListExpedienteDocumentos()){
//							Deflater deflater = new Deflater();  
//							deflater.setInput(ed.getBaos().toByteArray());  
//							ByteArrayOutputStream outputStream = new ByteArrayOutputStream(ed.getBaos().size()); 
//							deflater.finish();  
//							//ZipEntry ze = new ZipEntry("ruc_" + j + "_zip.zip");
//							System.out.println("NOMBRE SIN ZIP: "+m.getListExpedienteDocumentos().get(ni).getNombre_archivo());
//							//ZipEntry ze = new ZipEntry("" + m.getListExpedienteDocumentos().get(ni).getNombre_archivo().substring(m.getListExpedienteDocumentos().get(ni).getNombre_archivo().length()-4, m.getListExpedienteDocumentos().get(ni).getNombre_archivo().length()));
//							ZipEntry ze = new ZipEntry("" + m.getListExpedienteDocumentos().get(ni).getNombre_archivo());
//							zos.putNextEntry(ze);
//							//InputStream in = new ByteArrayInputStream(ed.getBaos().toByteArray());
//							byte[] buffer = new byte[1024*1024];
//							
//							 while (!deflater.finished()) {  
//								    int count = deflater.deflate(buffer); // returns the generated code... index  
//								    outputStream.write(buffer, 0, count);   
//							 }  
							
//							//int len;
//							while ((len = in.read(buffer)) > 0) {
//								System.out.println("Escribiendo..");
//								zos.write(buffer, 0, len);														
//							}
							
//							//in.close();
//							 
//							outputStream.close();
//							zos.closeEntry();
//							
//							j++;
//							ni++;
//							zipList.add(outputStream);
//						}
						//System.out.println("Salimos del For despues Nombre sin ZIP");
						//this.selectedExpedienteV[i].setBaos(out);
						//zipList.add(out);
						
						//zos.close();
						
						//m.setRuta(m.getRuta());
						//File file = new File(m.getDescripcion()+".zip");
						//FileOutputStream fos = new FileOutputStream (file); 
						//out.writeTo(fos);
						//Utiles.enviarArchivoViaSFTP2(file, m.getDescripcion()+".zip", Constante.RUTA_MEDIOS);

					}
					
					
					
				
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}	
				
		}.start();	
		return listMedios;		
		
		
	    
	}
	
	/*public boolean isDirExist(String directory) {
		
		String HOST = Constante.HOST_SCPF;
	    Integer PUERTO = Constante.PUERTO_SCPF;
	    String USUARIO = Constante.USUARIO_SCPF;
	    String PASSWORD = Constante.PASSWORD_SCPF;
		
		boolean isDirExistFlag = false;
		
		//File folder = new File(directory);
	    Session miSesion = null;
	    Channel miCanal = null;
	    ChannelSftp canalSFTP = null;
	    
		try {
			JSch jsch = new JSch(); //instancia de jsch
            miSesion= jsch.getSession(USUARIO,HOST,PUERTO);//establezco la sesion
            miSesion.setPassword(PASSWORD); //le doy la clave
            Properties configuracion = new Properties(); //nueva instancia de arch de config.
            configuracion.put("StrictHostKeyChecking","no"); //seteo una propiedad
            miSesion.setConfig(configuracion);//se la paso a la sesion
            System.out.println("iniciando conexion de validaci�n ...");
            miSesion.connect();//intento la conexion
            miCanal =  miSesion.openChannel("sftp"); //abro un canal sftp
            miCanal.connect();//me conecto
            canalSFTP = (ChannelSftp)miCanal;
			SftpATTRS sftpATTRS = canalSFTP.lstat(directory);

			isDirExistFlag = true;

			return sftpATTRS.isDir();

		} catch (Exception e) {

			if (e.getMessage().toLowerCase().equals("no such file")) {

				isDirExistFlag = false;

			}

		}

		return isDirExistFlag;

	}
	
	public void crearCarpeta(){
		
		System.out.println("enviando el archivo de imagen");
		
		
		//String HOST = "192.168.1.10";
	    //Integer PUERTO = 22;
	    //String USUARIO = "will";
	    //String PASSWORD = "gadiel";
	    
		
		String HOST = Constante.HOST_SCPF;
	    Integer PUERTO = Constante.PUERTO_SCPF;
	    String USUARIO = Constante.USUARIO_SCPF;
	    String PASSWORD = Constante.PASSWORD_SCPF;
	    
	    
	    //String RUTA = "/var/www/html/tipodocumental/";
	    //String RUTA_PREVIA = "/opt/digitalizacion/";
	    String RUTA_PREVIA = Constante.RUTA_DIGITILZACION;
	    String RUTA = RUTA_PREVIA+"";
	    
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
            //this.rutaCarpeta = RUTA;
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
}*/
	
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
	
	public void cambiarEstado(Lote l){
		
		this.loteSelected = l;
		
		this.id_estado = Constante.EST_ENTREGADO_A_MENSAJERIA;
		
		
		
	}
	
	public void visualizarExpediente(Medios m){
		
		this.medioSelected = m;
		
		this.listaExpedienteDocumentosVista = m.getListExpedienteDocumentos();
		
	}
	
	public void buscarExpedientes(){
		
		RequestContext context = RequestContext.getCurrentInstance();
		
		System.out.println("BUSCAR LOTES");
		
		boolean existe=false;		
		
			try {
				//this.listaLoteFilter = new ArrayList<Lote>();
				
				this.listaExpedienteDocumentos = new ArrayList<ExpedienteDocumento>();
				this.expedienteDocumentoFilter.setGrupo_formato(this.formato);
				this.expedienteDocumentoFilter.setEntregable(Constante.NUMERO_ENTREGABLE);
				this.listaExpedienteDocumentos = this.expedienteDocumentoServices.consultaPreparadoMedioExpediente(this.expedienteDocumentoFilter);
				
				if(this.id_indicador.equals(Constante.IND_CD)){
					
					this.tamanio = 629145600L;
					
				}else if(this.id_indicador.equals(Constante.IND_DVD)){
					
					//this.tamanio = 5242880L;
					this.tamanio = 4294967296L;
					
				}
				
				Long sum_tamanio = 0L;
				
				this.listMedios = new ArrayList<Medios>();
				
				
				
				//this.listMedios.addAll(listMediosPendientes);
				
				
				
				//int i = 0;
				int i = this.mediosServices.findMax();
				
				int contReg=0;
				
				Integer contaArchivo = 0;
				
				List<ExpedienteDocumento> led = new ArrayList<ExpedienteDocumento>();
				
				System.out.println("CANTIDAD DE DOCUMENTOS : "+this.listaExpedienteDocumentos.size());
				
				for (ExpedienteDocumento expDoc : this.listaExpedienteDocumentos) {
					
					contReg = contReg +1;					
					
					System.out.println("tamanio: "+sum_tamanio);
					
					if(expDoc.getGrupo_formato().equals(this.formato)){
						sum_tamanio = sum_tamanio + expDoc.getPeso();
					}
					
					if(sum_tamanio <= this.tamanio){
						
						System.out.println("MENOR O IGUAL");
						
						if(expDoc.getGrupo_formato().equals(this.formato)){
							
							contaArchivo = contaArchivo + 1;
							//sum_tamanio = sum_tamanio + expDoc.getPeso();
							
							led.add(expDoc);
								
						}
						
						if(contReg==this.listaExpedienteDocumentos.size()){
							
							i = i+1;
							
							Medios m = new Medios();
							
							m.setDescripcion("Medio " + i);
							m.setId_medio(i);
							m.setDescripcion_peso(Utils.convertirLongBytes(sum_tamanio, false));
							m.setFecha_registro(new Date());
							m.setPeso(sum_tamanio);
							m.setTipo_medio(this.formato);
							m.setId_estado(Constante.EST_PREPARADO_PARA_DESCARGA_DE_FEDATARIO);
							m.setDesEstado(this.vestado);
							m.setListExpedienteDocumentos(new ArrayList<ExpedienteDocumento>());
							m.setListExpedienteDocumentos(led);
							m.setRuta(Constante.RUTA_MEDIOS+m.getDescripcion()+".zip");
							m.setTotal_archivos(contaArchivo);
							
							this.listMedios.add(m);
							
						}
						
					}else{
						
						System.out.println("MAYOR");
						
						i = i+1;
						
						Medios m = new Medios();
						
						m.setDescripcion("Medio " + i);
						m.setId_medio(i);
						m.setDescripcion_peso(Utils.convertirLongBytes(sum_tamanio-expDoc.getPeso(), false));
						m.setFecha_registro(new Date());
						m.setPeso(sum_tamanio-expDoc.getPeso());
						m.setTipo_medio(this.formato);
						m.setId_estado(Constante.EST_PREPARADO_PARA_DESCARGA_DE_FEDATARIO);
						m.setDesEstado(this.vestado);
						m.setListExpedienteDocumentos(new ArrayList<ExpedienteDocumento>());
						m.setListExpedienteDocumentos(led);
						m.setTotal_archivos(contaArchivo);
						m.setRuta(Constante.RUTA_MEDIOS+m.getDescripcion()+".zip");
						
						this.listMedios.add(m);
						
						//if(expDoc.getTipo_archivo().equals(this.formato)){
							
							sum_tamanio = 0L;
							contaArchivo = 0;
							contaArchivo = contaArchivo + 1;
							sum_tamanio = sum_tamanio + expDoc.getPeso();							
							led = new ArrayList<ExpedienteDocumento>();
							led.add(expDoc);
							
							if(contReg==this.listaExpedienteDocumentos.size()){
								i = i+1;
								Medios me = new Medios();
								
								me.setDescripcion("Medio " + i);
								me.setId_medio(i);
								me.setDescripcion_peso(Utils.convertirLongBytes(sum_tamanio, false));
								me.setFecha_registro(new Date());
								me.setPeso(sum_tamanio);
								me.setTipo_medio(this.formato);
								me.setId_estado(Constante.EST_PREPARADO_PARA_DESCARGA_DE_FEDATARIO);
								me.setDesEstado(this.vestado);
								me.setListExpedienteDocumentos(new ArrayList<ExpedienteDocumento>());
								me.setListExpedienteDocumentos(led);
								me.setTotal_archivos(contaArchivo);
								me.setRuta(Constante.RUTA_MEDIOS+me.getDescripcion()+".zip");
								this.listMedios.add(me);
								
							}
							
						//}
						
					}
					
				}
				
				this.mediosServices.generarMedios(this.listMedios, this.usuarioLogin.getIdusuario(), this.usuarioLogin.getId_oficina());
				
				List<Medios> listMediosPendientes = new ArrayList<Medios>();
				
				Medios medioFilter = new Medios();
				// falta llenar el filter
				
				medioFilter.setFec_ini(this.expedienteDocumentoFilter.getFec_ini());
				medioFilter.setFec_fin(this.expedienteDocumentoFilter.getFec_fin());
				medioFilter.setTipo_medio(this.expedienteDocumentoFilter.getGrupo_formato());
				
				
				listMediosPendientes = this.mediosServices.buscarPendientes(medioFilter);
								
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
	//	}else{
	//		FacesUtils.showFacesMessage("Por favor, ingresar un parametro de b�squeda",Constante.ADVERTENCIA);
	//		context.update(":sms");
	//	}
	}
		
	public void cambiarFecha(){
		//System.out.println("la fecha que se capturo es " + this.expedienteFilter.getFec_ini());
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

	public ExpedienteIncidencia getExpIncSelected() {
		return expIncSelected;
	}

	public void setExpIncSelected(ExpedienteIncidencia expIncSelected) {
		this.expIncSelected = expIncSelected;
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
	
	
	
}
