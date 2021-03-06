package com.certicom.certiscan.managedBeans;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.util.ArrayList;
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
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.certicom.certiscan.commons.Constante;
import com.certicom.certiscan.commons.FacesUtils;
import com.certicom.certiscan.commons.GenericBeans;
import com.certicom.certiscan.commons.Utiles;
import com.certicom.certiscan.domain.Expediente;
import com.certicom.certiscan.domain.ExpedienteDocumento;
import com.certicom.certiscan.domain.ExpedienteTracking;
import com.certicom.certiscan.domain.Log;
import com.certicom.certiscan.domain.Menu;
import com.certicom.certiscan.domain.Oficina;
import com.certicom.certiscan.domain.Perfil;
import com.certicom.certiscan.domain.Usuario;
import com.certicom.certiscan.services.ExpedienteDocumentoService;
import com.certicom.certiscan.services.ExpedienteService;
import com.certicom.certiscan.services.ExpedienteTrackingService;
import com.certicom.certiscan.services.MenuServices;
import com.certicom.certiscan.services.OficinaService;


@ManagedBean(name="transfExpedMB")
@ViewScoped
public class TransfExpedMB extends GenericBeans implements Serializable{
	
	private Expediente expedienteFilter;
	
	private List<Expediente> listaExpedientes;
	private List<Expediente> listaExpedientesFilter;
	private List<Expediente> selectedExpediente;

	private boolean _activateCmb;
	private boolean _activateDealer;
	private Integer totalExpeCargados;
	private boolean bexpediente;
	private boolean bfecha;
	private SelectItem[] listaCanales;
	private ExpedienteService expedienteService;
	private ExpedienteTrackingService expedienteTrackingService;
	private OficinaService oficinaService;
	private RequestContext context;
	private Expediente expediente;
	private Expediente expSelected;
	private ExpedienteDocumentoService expdocService;
	private Boolean renderedVisorGrouped;
	private Boolean renderedVisorGroup;
	private StreamedContent file;
	
	private ExpedienteDocumento exSelec;
	
	private List<ExpedienteDocumento> listExpedDocu;
	
	private ExpedienteDocumento selectExpDocDesagrouped;
	private ExpedienteDocumento selectExpDocGrouped;
	
	
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
	
	
	private Log log;
	private LogMB logmb;
	
	private MenuServices menuServices;
	
	private List<Oficina> listaOficinas;
	
	private String ipcertiscan ;
	
	public TransfExpedMB(){
	}
	
	@PostConstruct
	public void inicia(){	
		
		this.fecha_ini = new Date();
		this.fecha_fin = new Date();
		this.ipcertiscan = Constante.IP_certiscan;
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
		this.exSelec = new ExpedienteDocumento();
		this.expdocService = new ExpedienteDocumentoService();
		this.listExpedDocu = new ArrayList<ExpedienteDocumento>();
		this.selectExpDocDesagrouped = new ExpedienteDocumento();
		this.renderedVisorGrouped = false;
		this.context = RequestContext.getCurrentInstance();
		this.expedienteService = new ExpedienteService(); 
		this.expedienteTrackingService  = new ExpedienteTrackingService();
		this.oficinaService = new OficinaService();
		this.selectedExpediente = new ArrayList<Expediente>();
		this.menuServices=new MenuServices();
		log = (Log)getSpringBean(Constante.SESSION_LOG);
		logmb = new LogMB();
		try {
			
			this.expedienteFilter.setFec_ini(this.fecha_ini);
			this.expedienteFilter.setFec_fin(this.fecha_fin);
			
			//this.totalExpeCargados = this.expedienteService.getExpedienteAsignados(this.usuarioLogin.getIdusuario());
			
			if(this.perfilUsuario.getProceso().equals("EXPEDIENTES PROPIOS") || this.perfilUsuario.getProceso().equals("OFICINA LOCAL")){
				this._activateCmb = true;
				this.expedienteFilter.setId_oficina(this.usuarioLogin.getId_oficina());
				this._activateDealer = false;
			}else{
				this._activateCmb = false;
				this._activateDealer = true;
			}
			
			//int codMenu = menuServices.opcionMenuByPrettyCod("pretty:ciclo");
			//log.setCod_menu(codMenu);
			
			Menu codMenu = menuServices.opcionMenuByPretty("pretty:transfExp");
			log.setCod_menu(codMenu.getCod_menu().intValue());
			
			initCombos();
			
			this.listaCanales = filterCanal();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void initCombos(){
		try {
			this.listaOficinas = oficinaService.findAll();
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
			
				this.listaExpedientes = this.expedienteService.buscarExpedienteTransferencia(expedienteFilter);
				
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
	
	public void mostrarAgrupado(Expediente exped){
		try {
			
			this.exSelec = new ExpedienteDocumento();
			this.listExpedDocu = this.expdocService.listFilesGroupedByExpediente_idAGI(exped.getExpediente_id());
			this.exSelec.setNro_expediente(exped.getNro_expediente());
			this.exSelec.setFec_recep(exped.getFecha_recepcion());
			this.exSelec.setDesOficina(exped.getDesOficina());
			this.exSelec.setDesEstado(exped.getDesestado());
			this.renderedVisorGroup = Boolean.FALSE;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void mostrarDesagrupado(Expediente expedi){
		try {
			this.exSelec = new ExpedienteDocumento();
			this.exSelec.setNro_expediente(expedi.getNro_expediente());
			this.exSelec.setFec_recep(expedi.getFecha_recepcion());
			this.exSelec.setDesOficina(expedi.getDesOficina());
			this.exSelec.setDesEstado(expedi.getDesestado());
			this.listExpedDocu = this.expdocService.listFilesDisGroupedByExpediente_idAG(expedi.getExpediente_id());
			this.renderedVisorGrouped = false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	public void descargarZip(Expediente expedi){
		this.file = crearMultipleZip(expedi);
		
		
	}
	
	public StreamedContent crearMultipleZip(Expediente expedi) {
		StreamedContent fil = null;
		byte[] buffer = new byte[1024];

		try {
			
			this.listExpedDocu = new ArrayList<ExpedienteDocumento>();
			this.listExpedDocu = this.expdocService.listFilesDisGroupedByExpediente_idAG(expedi.getExpediente_id());
			//List<ByteArrayOutputStream> txtList = new ArrayList<ByteArrayOutputStream>();
			List<ByteArrayOutputStream> zipList = new ArrayList<ByteArrayOutputStream>();
			
			for(ExpedienteDocumento ed:this.listExpedDocu){
				byte[] b=Utiles.descargarArchivoViaSFTP(ed.getRuta(), null);
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				baos.write(b, 0, b.length);
				ed.setBaoss(baos);
				//txtList.add(baos);
			}
			
			/*for (List<String> rucs : listaRucs) {
				ByteArrayOutputStream txt = new ByteArrayOutputStream();
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(txt));

				for (String ruc : rucs) {
					bw.write(ruc + "|");
					bw.newLine();
				}
				bw.close();
				txtList.add(txt);
			}*/

			int i = 1;
			ByteArrayOutputStream zip = new ByteArrayOutputStream();
			ZipOutputStream zos = new ZipOutputStream(zip);
			for (ExpedienteDocumento ed:this.listExpedDocu) {
				
				//ByteArrayOutputStream zip = new ByteArrayOutputStream();
				//ZipOutputStream zos = new ZipOutputStream(zip);

				ZipEntry ze = new ZipEntry(ed.getNombre_archivo());
				
				zos.putNextEntry(ze);
				
				InputStream in = new ByteArrayInputStream(ed.getBaoss().toByteArray());
				
				int len;
				
				while ((len = in.read(buffer)) > 0) {
					zos.write(buffer, 0, len);
				}

				in.close();
				zos.closeEntry();
				

				zipList.add(zip);
				i++;
			}
			zos.close();
			// construye zip final

			//ByteArrayOutputStream out = new ByteArrayOutputStream();
			//ZipOutputStream zos = new ZipOutputStream(out);

			/*int j = 1;
			for (ByteArrayOutputStream zip : zipList) {
				ZipEntry ze = new ZipEntry("ruc_" + j + "_zip.zip");
				zos.putNextEntry(ze);
				InputStream in = new ByteArrayInputStream(zip.toByteArray());
				int len;
				while ((len = in.read(buffer)) > 0) {
					zos.write(buffer, 0, len);
				}

				in.close();
				zos.closeEntry();
				j++;
			}*/

			zos.close();

			return fil = new DefaultStreamedContent(new ByteArrayInputStream(zip.toByteArray()), "application/zip", expedi.getNro_expediente()+".zip");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return fil;
		}
	}
	
	public void onRowSelect(SelectEvent event) {
	 	this.renderedVisorGrouped = Boolean.TRUE;
	 	System.out.println("ENTRAMOS A TRANSFERIR!!!!!!!!!!!!");
	 	System.out.println("entro");
	 	
    }
	
	public void onRowSelectGroup(SelectEvent event){
		this.renderedVisorGroup = Boolean.TRUE;
	}
	
	public void verificar(){
		System.out.println("ENTRAMOS A TRANSFERIR!!!!!!!!!!!!");
		System.out.println("holaaaaaaaaaa");
		System.out.println("Tama�o en selecci�n multiple: "+this.selectedExpediente.size());
	}
	
	public void verificarTodos(){
		System.out.println("ENTRAMOS A TRANSFERIR TODOOOOO!!!!!!!!!!!!");
		System.out.println("holaaaaaaaaaa");
		System.out.println("Tama�o en selecci�n total: "+this.selectedExpediente.size());
	}
	
	public void transferir(){
		
		RequestContext context = RequestContext.getCurrentInstance();
		boolean transferido = this.expedienteService.updateBatchExpediente(this.selectedExpediente, this.usuarioLogin.getIdusuario(), log);
		
		if(transferido){
			/*for (Expediente expediente: this.selectedExpediente) {
				log.setAccion("UPDATE");
		        log.setDescripcion("Se transfiri� el expediente: "+expediente.getNro_expediente() + " al estado ENVIADO A APENS");
		        logmb.insertarLog(log);
			}*/
			
			FacesUtils.showFacesMessage("Se transfiri� correctamente",Constante.INFORMACION);
		}else{
			FacesUtils.showFacesMessage("No se transfiri� correctamente",Constante.ERROR);
		}
		
		try {
			this.listaExpedientes = this.expedienteService.buscarExpedienteTransferencia(expedienteFilter);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		context.update("sms");
		
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
			this.listaExpTracking = this.expedienteTrackingService.findByExpedienteId(expSelected.getExpediente_id());
			System.out.println(" this.listaExpTracking " +this.listaExpTracking.size());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void cerrarAgrupados(){
		this.renderedVisorGroup = Boolean.FALSE;
	}
	
	public void cerrarDesagrupados(){
		this.renderedVisorGrouped = Boolean.FALSE;
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

	public List<Oficina> getListaOficinas() {
		return listaOficinas;
	}

	public void setListaOficinas(List<Oficina> listaOficinas) {
		this.listaOficinas = listaOficinas;
	}

	public ExpedienteDocumento getExSelec() {
		return exSelec;
	}

	public void setExSelec(ExpedienteDocumento exSelec) {
		this.exSelec = exSelec;
	}

	public List<ExpedienteDocumento> getListExpedDocu() {
		return listExpedDocu;
	}

	public void setListExpedDocu(List<ExpedienteDocumento> listExpedDocu) {
		this.listExpedDocu = listExpedDocu;
	}

	public ExpedienteDocumento getSelectExpDocDesagrouped() {
		return selectExpDocDesagrouped;
	}

	public void setSelectExpDocDesagrouped(
			ExpedienteDocumento selectExpDocDesagrouped) {
		this.selectExpDocDesagrouped = selectExpDocDesagrouped;
	}

	public Boolean getRenderedVisorGrouped() {
		return renderedVisorGrouped;
	}

	public void setRenderedVisorGrouped(Boolean renderedVisorGrouped) {
		this.renderedVisorGrouped = renderedVisorGrouped;
	}

	public String getIpcertiscan() {
		return ipcertiscan;
	}

	public void setIpcertiscan(String ipcertiscan) {
		this.ipcertiscan = ipcertiscan;
	}

	public StreamedContent getFile() {
		return file;
	}

	public void setFile(StreamedContent file) {
		this.file = file;
	}

	public List<Expediente> getSelectedExpediente() {
		return selectedExpediente;
	}

	public void setSelectedExpediente(List<Expediente> selectedExpediente) {
		this.selectedExpediente = selectedExpediente;
	}

	public ExpedienteDocumento getSelectExpDocGrouped() {
		return selectExpDocGrouped;
	}

	public void setSelectExpDocGrouped(ExpedienteDocumento selectExpDocGrouped) {
		this.selectExpDocGrouped = selectExpDocGrouped;
	}

	public Boolean getRenderedVisorGroup() {
		return renderedVisorGroup;
	}

	public void setRenderedVisorGroup(Boolean renderedVisorGroup) {
		this.renderedVisorGroup = renderedVisorGroup;
	}
	
	
	
}
