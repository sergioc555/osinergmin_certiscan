package com.certicom.certiscan.managedBeans;

import java.io.Serializable;
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

import net.sf.jasperreports.engine.JRParameter;

import org.primefaces.context.RequestContext;

import com.certicom.certiscan.commons.Constante;
import com.certicom.certiscan.commons.ExportarArchivo;
import com.certicom.certiscan.commons.FacesUtils;
import com.certicom.certiscan.commons.GenericBeans;
import com.certicom.certiscan.domain.Empresa;
import com.certicom.certiscan.domain.Log;
import com.certicom.certiscan.domain.Menu;
import com.certicom.certiscan.domain.Negocio;
import com.certicom.certiscan.domain.Oficina;
import com.certicom.certiscan.services.EmpresaService;
import com.certicom.certiscan.services.MenuServices;
import com.certicom.certiscan.services.NegocioServices;
import com.certicom.certiscan.services.OficinaService;

@ManagedBean(name="oficinaMB")
@ViewScoped
public class OficinaMB extends GenericBeans implements Serializable{

	private Oficina oficina;
	private List<Oficina> listaOficina;
	private Boolean editar;
	private OficinaService oficinaService;
	private String sdepartamento;
	private String sprovincia;
	private String sdistrito;
	private List<Oficina> listaFiltroOficina;
	private MenuServices menuServices;
	private List<Negocio> listaNegocio;
	private NegocioServices negocioServices;
	private Integer _negocio;
	private boolean view;
	private Log log;
	private LogMB logmb;
	RequestContext context; 
	private String mensajeBlock;
	
	private boolean btnImprimir;
	
	@ManagedProperty(value="#{loginMB}")
	private LoginMB login;
	
	public OficinaMB(){
	}
	
	@PostConstruct
	public void inicia()
	{
		this.oficina = new Oficina();
		this.listaOficina = new ArrayList<>();
		this.editar = Boolean.FALSE;
		this.oficinaService = new OficinaService();
        this.negocioServices = new NegocioServices();
        this.listaNegocio = new ArrayList<Negocio>();
        menuServices = new MenuServices();
        this.view = Boolean.TRUE;
        this.btnImprimir = true;
        
		//listando
		try {
			//this.listaOficina = this.oficinaService.findAll();
			//this.listaTerritorio = this.territorioService.findAll();
			this.listaNegocio = this.negocioServices.findAll();
			
			if(this.login.getPerfilUsuario().getCod_perfil()!= Constante.PERFIL_COD_GERENTE_PROYECTO){
				this._negocio = this.login.getUsuario().getId_negocio();
				this.view = true;
			}else{
				this.view = false;
			}	
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log = (Log)getSpringBean(Constante.SESSION_LOG);
		logmb = new LogMB();
		try {
			Menu codMenu = menuServices.opcionMenuByPretty("pretty:oficina");
			log.setCod_menu(codMenu.getCod_menu().intValue());
			log.setIdusuario(this.login.getIdUsuario());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();}
	}
	
	public void listarTerritorioByNegocio(){
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// sirve para listar provincias por cada departamento 
	public void listarProvincias(){
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// sirve para listar  distritos por cada provincia
	public void listarDistritos(){
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public void limpiarValores(){
		//this.listaDepartamentos = ;
		this.sdepartamento = "";
		this.sprovincia = "";
		this.sdistrito = "";
	}
	
	
	// este metodo es invocado para  nuevo/ editar oficina 
	public void guardarOficina() {
		Boolean valido=Boolean.TRUE;
		RequestContext context = RequestContext.getCurrentInstance();  
   	    
 	 try {
  		 	
			this.oficina.setNombre(this.oficina.getNombre().trim().toUpperCase());
			this.oficina.setDireccion(this.oficina.getDireccion().trim().toUpperCase());
			this.oficina.setEncargado(this.oficina.getEncargado().trim().toUpperCase());
			this.oficina.setEstado(Boolean.TRUE);
			this.oficina.setDepartamento(this.sdepartamento);
			this.oficina.setProvincia(this.sprovincia);
			this.oficina.setDistrito(this.sdistrito);
			//this.listaDepartamentos = new ArrayList<>();
			this.limpiarValores();
			
			
			if(this.editar) {
				this.oficinaService.actualizarOficina(oficina);
				FacesUtils.showFacesMessage("Tipo Oficina ha sido actualizado", 3);
				log.setAccion("UPDATE");
		        log.setDescripcion("El usuario "+this.login.getLoginUsuario()+" actualizó la oficina: "+this.oficina.getNombre());
		        logmb.insertarLog(log);
		        this.oficina = new Oficina();
				this.editar = Boolean.FALSE;
				this.listaOficina = this.oficinaService.listOficinaxNegocioORTodos(this._negocio);
				
				context.update("sms");
				context.addCallbackParam("esValido", valido);
			}else{
				Oficina oficinasearch = new Oficina();
	   		 	oficinasearch= this.oficinaService.findByCodigoByNegocio2(this.oficina.getCodigo_oficina(),this.oficina.getId_negocio(),this.oficina.getId_territorio());
	   		 if(oficinasearch==null){
				this.oficinaService.crearOficina(oficina);
				FacesUtils.showFacesMessage("Tipo Oficina ha sido creado", 3);
				log.setAccion("INSERT");
		        log.setDescripcion("El usuario "+this.login.getLoginUsuario()+" registró la oficina  : " + this.oficina.getNombre());
		        logmb.insertarLog(log);
		        
		        this.listaOficina = this.oficinaService.listOficinaxNegocioORTodos((Integer.valueOf(this._negocio)));
		        valido=Boolean.TRUE;
	   		 	context.update("sms");
				context.addCallbackParam("esValido", valido);
	   		}else{
	   			this.setMensajeBlock("Buscando, espere por favor...");
	   		    FacesUtils.showFacesMessage("El codigo oficina ya existe", 1);
	   		 	valido=Boolean.FALSE;
	   		 	context.update("sms");
				context.addCallbackParam("esValido", valido);
	   		 	}
			}
   		 	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// metodo que se invoca en el boton Nuevo Oficina
	public void nuevoOficina(){
		this.oficina = new Oficina();
		if(this.login.getPerfilUsuario().getCod_perfil()!= Constante.PERFIL_COD_GERENTE_PROYECTO)
			oficina.setId_negocio(this.login.getUsuario().getId_negocio());
		this.oficina.setEstado(Boolean.TRUE);
		this.editar = Boolean.FALSE;
		this.setMensajeBlock("Guardando, espere por favor...");
	}
	
   // metodo que se invoca en el boton editar para cada registro 
	public void editarOficina(Oficina tipDoc){
	
		  
		
		try {
			        this.oficina = tipDoc;
			        this.editar = Boolean.TRUE;
			        //this.listaOficina = this.oficinaService.findAll();	
			        this.listaOficina = this.oficinaService.listOficinaxNegocioORTodos((Integer.valueOf(this._negocio)));
					//obteniendo los datos de ubigeo 

					
					//String texto = new String("    Hola    esto   es una prueba      de eliminar      espacios                    ");
					//System.out.println(texto);
					//texto = texto.replaceAll(" +", " ");
					//texto = texto.trim();
					//System.out.println(texto);
					
					this.sdepartamento = this.oficina.getDepartamento();
					this.sprovincia = this.oficina.getProvincia();
					this.sdistrito = this.oficina.getDistrito();
					this.sdistrito = this.sdistrito.replaceAll(" +", " ");
					this.sdistrito = this.sdistrito.trim();
					this.setMensajeBlock("Actualizando, espere por favor...");
					System.out.println(this.getMensajeBlock());
					//this.listaOficina = this.oficinaService.findAll();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
	}  
	
	// metodo que se invoca en la opcion eliminar que se encuentra en el dialogo del boton eliminar
	

	public void eliminarOficina(Oficina tipOrd){
		this.oficina = tipOrd;
		
	}
	
	// metodo que elimana registros ya que invoca al service y este al mapper 
	public void confirmaEliminar()
	{
		try {
			this.oficinaService.eliminarOficina(this.oficina.getId_oficina());
			FacesUtils.showFacesMessage("Tipo Oficina ha sido eliminado", 3);
			this.listaOficina = this.oficinaService.listOficinaxNegocioORTodos((Integer.valueOf(this._negocio)));
			log.setAccion("DELETE");
	        log.setDescripcion("El usuario "+this.login.getLoginUsuario()+" ha eliminado la oficina: " + this.oficina.getNombre());
	        logmb.insertarLog(log);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	public void cambiarEstado(Oficina oficina){
		
		   if(oficina.getEstado()){
			   oficina.setEstado(Boolean.FALSE);
		   }else{
			   oficina.setEstado(Boolean.TRUE);
		   }
		   
		   try {
			   this.oficinaService.actualizarOficina(oficina);;
			   FacesUtils.showFacesMessage("Tipo Oficina modificado correctamente",Constante.INFORMACION);
			   this.listaOficina = this.oficinaService.listOficinaxNegocioORTodos((Integer.valueOf(this._negocio)));
			   log.setAccion("UPDATE");
	           log.setDescripcion("El usuario "+this.login.getLoginUsuario()+" actualizó el estado a  : " + oficina.getEstado());
	           logmb.insertarLog(log);
		   } catch (Exception e) {
			   System.out.println("Error:"+e.getMessage());
			   e.printStackTrace();
		   }   
	

	}
	
	
	public void listarOficina(){
		System.out.println("ingresa a listarOficina");
		System.out.println("el negocio que entra es " + this._negocio);
		try {
			this.listaOficina = this.oficinaService.listOficinaxNegocioORTodos(this._negocio.intValue());
			System.out.println("la longitud de la lista" + listaOficina.size());
			
			if(this.listaOficina.size() ==0){
				this.btnImprimir = Boolean.TRUE;
			} else{
				this.btnImprimir = Boolean.FALSE;
			}
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
public void imprimirXLS(){
		String par_negocio="";
		Negocio negocioParametro=  new Negocio();

		List<Oficina> listaRpt = new ArrayList<Oficina>();

		
		try {
			ServletContext servletContext = (ServletContext) (FacesContext.getCurrentInstance().getExternalContext().getContext());
		
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat formatofechahora = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
			String fechaActual = formato.format(new Date());
			
			if((Integer.valueOf(this._negocio))== 0){
				par_negocio="TODOS"; 
			}else{
				negocioParametro= this.negocioServices.findById((Integer.valueOf(this._negocio))); 
				par_negocio= negocioParametro.getDescripcion(); 
			}
			
			Integer total = listaOficina.size();
			String p_logo = ExportarArchivo.getPath("/resources/img/certicom.jpg");
			
			Map<String, Object> input =new  HashMap<String,Object>();
			input.put("P_TOTAL", total.toString());
			input.put("P_NEGOCIO",par_negocio);
			input.put("P_FSISTEMA", fechaActual);
		//	 input.put("P_LOGO", p_logo);
			input.put(JRParameter.REPORT_LOCALE, new Locale("es"));

			
			String path = ExportarArchivo.getPath("/resources/reports/jxrml/rptOficinaXLS.jasper");
			HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
			
			log.setAccion("PRINT");
	        log.setDescripcion("El usuario "+this.login.getLoginUsuario()+" ha impreso el archivo ConsultaOficina.xls");
	        logmb.insertarLog(log);
			
			byte[] bytes;
				bytes = ExportarArchivo.exportXls(path, input,listaOficina,false);
			ExportarArchivo.executeExccel(bytes, "ConsultaOficina.xls");
			FacesContext.getCurrentInstance().responseComplete();
			} catch (Exception e) {
				e.printStackTrace();
			}	
	}

	
	/*##################################################################################################*/
	/*####################################------setters y getters----###################################*/
	/*##################################################################################################*/
	
	
	public Oficina getOficina() {
		return oficina;
	}

	public void setOficina(Oficina oficina) {
		this.oficina = oficina;
	}

	public List<Oficina> getListaOficina() {
		return listaOficina;
	}

	public void setListaOficina(List<Oficina> listaOficina) {
		this.listaOficina = listaOficina;
	}

	public Boolean getEditar() {
		return editar;
	}

	public void setEditar(Boolean editar) {
		this.editar = editar;
	}

	public OficinaService getOficinaService() {
		return oficinaService;
	}

	public void setOficinaService(OficinaService oficinaService) {
		this.oficinaService = oficinaService;
	}

	public String getSdepartamento() {
		return sdepartamento;
	}

	public void setSdepartamento(String sdepartamento) {
		this.sdepartamento = sdepartamento;
	}

	public String getSprovincia() {
		return sprovincia;
	}

	public void setSprovincia(String sprovincia) {
		this.sprovincia = sprovincia;
	}

	
	public List<Oficina> getListaFiltroOficina() {
		return listaFiltroOficina;
	}

	public void setListaFiltroOficina(List<Oficina> listaFiltroOficina) {
		this.listaFiltroOficina = listaFiltroOficina;
	}
	

	public List<Negocio> getListaNegocio() {
		return listaNegocio;
	}

	public void setListaNegocio(List<Negocio> listaNegocio) {
		this.listaNegocio = listaNegocio;
	}

	public Integer get_negocio() {
		return _negocio;
	}

	public void set_negocio(Integer _negocio) {
		this._negocio = _negocio;
	}

	public String getMensajeBlock() {
		return mensajeBlock;
	}

	public void setMensajeBlock(String mensajeBlock) {
		this.mensajeBlock = mensajeBlock;
	}

	public boolean isBtnImprimir() {
		return btnImprimir;
	}

	public void setBtnImprimir(boolean btnImprimir) {
		this.btnImprimir = btnImprimir;
	}
	
	public String getSdistrito() {
		return sdistrito;
	}

	public void setSdistrito(String sdistrito) {
		this.sdistrito = sdistrito;
	}

	public LoginMB getLogin() {
		return login;
	}

	public void setLogin(LoginMB login) {
		this.login = login;
	}

	public boolean isView() {
		return view;
	}

	public void setView(boolean view) {
		this.view = view;
	}

	
	
	
}	
