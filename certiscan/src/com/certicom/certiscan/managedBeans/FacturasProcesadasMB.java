package com.certicom.certiscan.managedBeans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.certicom.certiscan.commons.Constante;
import com.certicom.certiscan.commons.GenericBeans;
import com.certicom.certiscan.commons.Utiles;
import com.certicom.certiscan.domain.Facturacion;
import com.certicom.certiscan.domain.FacturacionCabecera;
import com.certicom.certiscan.domain.Log;
import com.certicom.certiscan.domain.Menu;
import com.certicom.certiscan.domain.Negocio;
import com.certicom.certiscan.domain.Perfil;
import com.certicom.certiscan.domain.Usuario;
import com.certicom.certiscan.services.FacturacionCabeceraService;
import com.certicom.certiscan.services.FacturacionService;
import com.certicom.certiscan.services.MenuServices;
import com.certicom.certiscan.services.NegocioServices;


@ManagedBean(name="facturasProcesadasMB")
@ViewScoped
public class FacturasProcesadasMB extends GenericBeans implements Serializable{
	
	private FacturacionService facturacionService;
	private FacturacionCabeceraService facturacionCabeceraService;
	private NegocioServices negocioService;
	
	
	@ManagedProperty(value= "#{loginMB}")
	private LoginMB login;
	private Perfil perfil;
	
	@ManagedProperty(value="#{loginMB.usuario}")
	private Usuario usuarioLogin;
	
	private LazyDataModel<FacturacionCabecera> listFacturacionCabecera;
	private List<FacturacionCabecera> listFacturacionCabeceraFiltered;
	
	private List<Facturacion> modelListFacturacion;
	private List<Facturacion> listFacturacionFiltered; 
	private List<Usuario> listaEjecutivos;
	private List<Usuario> listaSupervisores;
	private List<Negocio> listaNegocios;
	private MenuServices menuServices;
	
	private FacturacionCabecera facturacionCabeceraSelected;
	private Negocio negocio;
	
	private String anio;
	private String mes;
	private Boolean block_negocio;
	private Boolean activarDetalle;
	
	private Integer cant_factura = 0;
	private Integer cant_compl = 0;
	private Integer cant_dscto = 0;
	private Log log;
	private LogMB logmb;
	
	SimpleDateFormat sdfMes = new SimpleDateFormat("MM");
	SimpleDateFormat sdfAnio = new SimpleDateFormat("yyyy");

	public FacturasProcesadasMB(){}

	@PostConstruct
	public void inicia(){
		

		this.facturacionService = new FacturacionService();
		this.facturacionCabeceraService = new FacturacionCabeceraService();
		this.negocioService = new NegocioServices();
		
		this.perfil=this.login.getPerfilUsuario();
		this.negocio = new Negocio();
		
		this.listaEjecutivos = new ArrayList<>();
		this.listaSupervisores = new ArrayList<>();
		
		this.mes = sdfMes.format(new Date());
		this.anio = sdfAnio.format(new Date());
		
		this.activarDetalle = Boolean.FALSE;
		
		this.menuServices=new MenuServices();
		
		log = (Log)getSpringBean(Constante.SESSION_LOG);
		logmb = new LogMB();
		
		if(Constante.PERFIL_COD_GERENTE_PROYECTO == this.login.getPerfilUsuario().getCod_perfil() 
				||	Constante.PERFIL_COD_GERENTE_PROYECTO_BBVA == this.login.getPerfilUsuario().getCod_perfil()){
			this.negocio.setId_negocio(0);
			this.block_negocio = false;
		}else{
			this.negocio.setId_negocio(this.login.getUsuario().getId_negocio());
			this.block_negocio = true;
		}
		
		try {
			Menu codMenu = menuServices.opcionMenuByPretty("pretty:facturacion");
			log.setCod_menu(codMenu.getCod_menu().intValue());
			//int codMenu = menuServices.opcionMenuByPrettyCod("pretty:facturasProcesadas");
			//log.setCod_menu(codMenu);
			this.listaNegocios = this.negocioService.findAll();
			iniciarCargaCabeceraExpediente();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void iniciarCargaCabeceraExpediente() throws Exception{
		this.activarDetalle = Boolean.FALSE;
		try {
			//LAZY
			listFacturacionCabecera = new LazyDataModel<FacturacionCabecera>() {
				
				private static final long serialVersionUID = 1L;
				private List<FacturacionCabecera> datasource; 
				private Integer totalRow=0;
				
				
	             @Override  
	             public FacturacionCabecera getRowData(String rowKey) {  
	                    for(FacturacionCabecera e : datasource) {  
	                        if(e.getIdFacturacionCabecera().equals(rowKey))  
	                            return e;  
	                    }  

	                    return null;  
	            }
	             
	             @Override
	             public void setRowIndex(int rowIndex){
	                 if (rowIndex == -1 || getPageSize() == 0) {
	                     super.setRowIndex(-1);
	                 }
	                 else
	                     super.setRowIndex(rowIndex % getPageSize());
	             }
	             
	             @Override  
	            public Object getRowKey(FacturacionCabecera e) {  
	                return e.getIdFacturacionCabecera();  
	            }  
		            
				@Override  
	            public List<FacturacionCabecera> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) {              
					try {
						//Map<String,String> filterSt =(Map<String,String>) filters;
					   totalRow = facturacionCabeceraService.getCountPAGINATOR(filters, "BANCO",negocio.getId_negocio(),Utiles.stringToDate("01", mes, anio));
					   return datasource = facturacionCabeceraService.findByPAGINATOR(first, pageSize, filters, sortField, sortOrder.toString(), "BANCO",negocio.getId_negocio(),Utiles.stringToDate("01", mes, anio));
					   //return null;
					} catch (Exception e) {
						e.printStackTrace();
						return null;
					}
	            }  
	            
				@Override  
				public int getRowCount() {                
					return totalRow;
	            }
			};
			
			//*******************************************
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void listarDetalle(){
		this.cant_compl = 0;
		this.cant_dscto = 0;
		this.cant_factura = 0;
		
		this.activarDetalle = Boolean.TRUE;
		
		try {
			
			// cargadndo el detalle
			this.modelListFacturacion = this.facturacionService.obtenerFacturacionbyCabecera(this.facturacionCabeceraSelected.getIdFacturacionCabecera());
			
			for (Facturacion fac : modelListFacturacion) {
				switch (fac.getOrigen()) {
				case Constante.ORIGEN_FAC_FACTURA:
					cant_factura++;
					break;
				case Constante.ORIGEN_FAC_COMPLEMENTO:
					cant_compl++;
					break;	
				case Constante.ORIGEN_FAC_DESCUENTO:
					cant_dscto++;
					break;	
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	


	
	
	/*#################-------setters and getters-------########################33*/



	public LoginMB getLogin() {
		return login;
	}

	public void setLogin(LoginMB login) {
		this.login = login;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public Usuario getUsuarioLogin() {
		return usuarioLogin;
	}

	public void setUsuarioLogin(Usuario usuarioLogin) {
		this.usuarioLogin = usuarioLogin;
	}

	public List<Facturacion> getModelListFacturacion() {
		return modelListFacturacion;
	}

	public void setModelListFacturacion(List<Facturacion> modelListFacturacion) {
		this.modelListFacturacion = modelListFacturacion;
	}

	public List<Facturacion> getListFacturacionFiltered() {
		return listFacturacionFiltered;
	}

	public void setListFacturacionFiltered(List<Facturacion> listFacturacionFiltered) {
		this.listFacturacionFiltered = listFacturacionFiltered;
	}


	public List<Usuario> getListaEjecutivos() {
		return listaEjecutivos;
	}

	public void setListaEjecutivos(List<Usuario> listaEjecutivos) {
		this.listaEjecutivos = listaEjecutivos;
	}

	public List<Usuario> getListaSupervisores() {
		return listaSupervisores;
	}

	public void setListaSupervisores(List<Usuario> listaSupervisores) {
		this.listaSupervisores = listaSupervisores;
	}

	public Integer getCant_factura() {
		return cant_factura;
	}

	public void setCant_factura(Integer cant_factura) {
		this.cant_factura = cant_factura;
	}

	public Integer getCant_compl() {
		return cant_compl;
	}

	public void setCant_compl(Integer cant_compl) {
		this.cant_compl = cant_compl;
	}

	public Integer getCant_dscto() {
		return cant_dscto;
	}

	public void setCant_dscto(Integer cant_dscto) {
		this.cant_dscto = cant_dscto;
	}


	public Negocio getNegocio() {
		return negocio;
	}

	public void setNegocio(Negocio negocio) {
		this.negocio = negocio;
	}

	public String getAnio() {
		return anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public Boolean getBlock_negocio() {
		return block_negocio;
	}

	public void setBlock_negocio(Boolean block_negocio) {
		this.block_negocio = block_negocio;
	}

	public FacturacionCabecera getFacturacionCabeceraSelected() {
		return facturacionCabeceraSelected;
	}

	public void setFacturacionCabeceraSelected(
			FacturacionCabecera facturacionCabeceraSelected) {
		this.facturacionCabeceraSelected = facturacionCabeceraSelected;
	}

	public LazyDataModel<FacturacionCabecera> getListFacturacionCabecera() {
		return listFacturacionCabecera;
	}

	public void setListFacturacionCabecera(
			LazyDataModel<FacturacionCabecera> listFacturacionCabecera) {
		this.listFacturacionCabecera = listFacturacionCabecera;
	}

	public List<Negocio> getListaNegocios() {
		return listaNegocios;
	}

	public void setListaNegocios(List<Negocio> listaNegocios) {
		this.listaNegocios = listaNegocios;
	}

	public List<FacturacionCabecera> getListFacturacionCabeceraFiltered() {
		return listFacturacionCabeceraFiltered;
	}

	public void setListFacturacionCabeceraFiltered(
			List<FacturacionCabecera> listFacturacionCabeceraFiltered) {
		this.listFacturacionCabeceraFiltered = listFacturacionCabeceraFiltered;
	}

	public Boolean getActivarDetalle() {
		return activarDetalle;
	}

	public void setActivarDetalle(Boolean activarDetalle) {
		this.activarDetalle = activarDetalle;
	}
	
	
	
	
}