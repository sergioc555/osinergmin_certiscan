package com.certicom.certiscan.managedBeans;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import com.certicom.certiscan.commons.Constante;
import com.certicom.certiscan.commons.FacesUtils;
import com.certicom.certiscan.commons.GenericBeans;
import com.certicom.certiscan.commons.Utils;
import com.certicom.certiscan.domain.Bono;
import com.certicom.certiscan.domain.Centro_Atencion;
import com.certicom.certiscan.domain.Ciclo;
import com.certicom.certiscan.domain.Log;
import com.certicom.certiscan.domain.Menu;
import com.certicom.certiscan.domain.Oficina;
import com.certicom.certiscan.domain.TipoBono;
import com.certicom.certiscan.domain.Usuario;
import com.certicom.certiscan.services.BonoService;
import com.certicom.certiscan.services.Centro_AtencionServices;
import com.certicom.certiscan.services.CicloService;
import com.certicom.certiscan.services.MenuServices;
import com.certicom.certiscan.services.OficinaService;
import com.certicom.certiscan.services.TipoBonoService;
import com.certicom.certiscan.services.UsuarioServices;



@ManagedBean(name="bonosMB")
@ViewScoped
public class BonosMB extends GenericBeans implements Serializable{

	private Integer id_negocio;
	private Bono bonoFilter;
	private List<Bono> listaBono;
	private List<Bono> listaBonoFiltro;
	private List<TipoBono> listaTipoBonos;
	private String anio;
	private String mes;
	private String anioG;
	private String mesG;
	private List<Usuario> listaPersonal;
	private List<Usuario> listaPersonalFilter;
	private List<Ciclo> listaCiclos;
	private List<Oficina> listaOficinas;
	private String titulo;
	private Usuario usuSelec;
	
	private Bono bono;
	private Bono bonoSelec;
	private Log log;
	private LogMB logmb;
	@ManagedProperty(value= "#{loginMB}")
	private LoginMB login;
	
	private UsuarioServices usuarioService;
	private MenuServices menuServices;
	private BonoService bonoService;
	private OficinaService oficinaServices;
	private CicloService cicloService;
	private TipoBonoService tipoBonoService;
	private boolean editar;
	
	private Utils utils = new Utils();  
	 
	SimpleDateFormat sdfMes = new SimpleDateFormat("MM");
	SimpleDateFormat sdfAnio = new SimpleDateFormat("yyyy");
	
	private Integer f_cod_oficina = 0;
	private Integer f_cod_ciclo = 0;

	
	public BonosMB(){
	}
	
	@PostConstruct
	public void inicia()
	{
		this.listaPersonal = new ArrayList<>();
		this.listaBono = new ArrayList<>();
		this.listaCiclos = new ArrayList<>();
		this.listaOficinas = new ArrayList<>();
		
		this.usuarioService = new UsuarioServices();
		this.menuServices = new MenuServices();
		this.bonoService = new BonoService();
		this.oficinaServices = new OficinaService();
		this.cicloService = new CicloService();
		this.tipoBonoService = new TipoBonoService();
		
		this.log = (Log)getSpringBean(Constante.SESSION_LOG);
		this.logmb = new LogMB();
		this.bono = new Bono();
		this.mes = sdfMes.format(new Date());
		this.anio = sdfAnio.format(new Date());
		
		this.mesG = sdfMes.format(new Date());
		this.anioG = sdfAnio.format(new Date());
		
		
		
		try {
			this.listaTipoBonos = this.tipoBonoService.findAll();
			Menu codMenu = menuServices.opcionMenuByPretty("pretty:bonos");
			
			log.setCod_menu(codMenu.getCod_menu().intValue());
			
			
			this.listaOficinas = oficinaServices.findAll();
			this.listaCiclos = cicloService.findByTipoCicloActivo(2);
			getListaCiclosComision();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private  List<Ciclo> getListaCiclosComision() {
		for (Ciclo c : listaCiclos) {
			c.setNmes(this.utils.convertirANombre(c.getMes()));
			DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			c.setNfecini(sdf.format(c.getFecini()));
			c.setNfecfin(sdf.format(c.getFecfin()));
			
		}
		return listaCiclos;
	}
	
	public void buscarPersonal(){
		try {
			this.listaPersonal = this.usuarioService.getUsuariosByFilters(this.f_cod_oficina);
			for (int i = 0; i < this.listaPersonal.size(); i++) {
				Usuario usua = this.listaPersonal.get(i);
			//	usua.setMontoTotal(this.bonoService.obtenerMonto(usua.getIdusuario(),Utiles.stringToDate("01", this.mesG, this.anioG)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void asignarComision(Usuario usuario){
	//	historialxUsuario(usuario);
		try {
			this.editar = false;
			this.titulo = "ASIGNAR COMISION";
			this.bono = new Bono();
			this.f_cod_ciclo =0;
			this.bono.setNombre_completo(usuario.getNombre() + ", "+usuario.getApellido_paterno()+" "+usuario.getApellido_materno());
			this.bono.setIdusuario(usuario.getIdusuario());
			System.out.println("pasooo");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void historialxUsuario(Usuario usuario){
		try {
			this.bonoFilter = new Bono();
			this.usuSelec = new Usuario();
			this.usuSelec = usuario;
			this.bonoFilter.setIdusuario(usuario.getIdusuario());
		//	bonoFilter.setPeriodo(Utiles.stringToDate("01", this.mesG, this.anioG));
			this.listaBono = this.bonoService.listBonoByUsuario(this.bonoFilter);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void editarComision(Bono bono){
		//	historialxUsuario(usuario);
			try {
				this.editar = true;
				this.titulo = "EDITAR COMISION";
				this.bono = bono;
				this.f_cod_ciclo = this.bono.getCod_ciclo();
				System.out.println("pasooo");
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	private Ciclo getCicloItem(Integer id_ciclo){
		Ciclo item = new Ciclo();
		for (Ciclo ciclo : this.listaCiclos) {
			if(ciclo.getCod_ciclo().intValue() == id_ciclo.intValue() ){
				item = ciclo;
				break;
			}
		}
		return item;
	}
	
	private TipoBono getTipoBonoItem(Integer id_tipobono){
		TipoBono item = new TipoBono();
		for (TipoBono tipoBono : this.listaTipoBonos) {
			if(tipoBono.getIdtipobono().intValue() == id_tipobono.intValue() ){
				item = tipoBono;
				break;
			}
		}
		return item;
	}
	
	public void confirmaEliminar(){
		try {
		
			this.bonoService.eliminarBono(this.bonoSelec.getId_bono());
			Ciclo c = getCicloItem(this.bonoSelec.getCod_ciclo());
			TipoBono tb = getTipoBonoItem(this.bonoSelec.getIdtipobono());
			this.bonoSelec.setDesTipoBono(tb.getDescripcion());
			log.setAccion("DELETE");
			log.setDescripcion("Se elimnó el BONO: " + this.bonoSelec.getDesTipoBono() + " al Usuario "+this.bonoSelec.getNombre_completo()+" del ciclo "+c.getNmes()+" - "+c.getAnio()+ " - "+c.getNfecini()+" - "+c.getNfecfin());
			logmb.insertarLog(log);
			FacesUtils.showFacesMessage("Estado ha sido eliminado", 3);
			
			this.listaBono = this.bonoService.listBonoByUsuario(this.bonoFilter);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void registroBono(){
		try {
			Boolean valido=Boolean.TRUE;
			RequestContext context = RequestContext.getCurrentInstance();  
	   	    context.addCallbackParam("esValido", valido);
	   	    
			this.bono.setUsu_registro(this.login.getUsuario().getIdusuario());
			this.bono.setFec_registro(new Date());
			
		//	this.bono.setPeriodo(Utiles.stringToDate("01", this.mes, this.anio));
			this.bono.setCod_ciclo(this.f_cod_ciclo);
			Ciclo c = getCicloItem(this.f_cod_ciclo);
			TipoBono tb = getTipoBonoItem(this.bono.getIdtipobono());
			this.bono.setDesTipoBono(tb.getDescripcion());
			
			this.bono.setAnio(c.getAnio());
			this.bono.setMes(c.getMes());
			
			if(this.editar){
				
				this.bonoService.actualizaBono(this.bono);
				log.setAccion("UPDATE");
				log.setDescripcion("Se actualizó el bono "+this.bono.getDesTipoBono() + " al Usuario "+this.bono.getNombre_completo()+" del ciclo "+c.getNmes()+" - "+c.getAnio()+ " - "+c.getNfecini()+" - "+c.getNfecfin());
				logmb.insertarLog(log);
				FacesUtils.showFacesMessage("Bono actualizado correctamente", 3);
				this.listaBono = this.bonoService.listBonoByUsuario(this.bonoFilter);
				
			}else{
				this.bonoService.registraBono(this.bono);
				log.setAccion("INSERT");
				log.setDescripcion("Se insertó el bono "+this.bono.getDesTipoBono()+ " al Usuario "+this.bono.getNombre_completo()+" del ciclo "+c.getNmes()+" - "+c.getAnio()+ " - "+c.getNfecini()+" - "+c.getNfecfin());
				logmb.insertarLog(log);
				FacesUtils.showFacesMessage("Bono registrado correctamente", 3);
				buscarPersonal();
			}
			
			
			this.bono = null;
			buscarPersonal();
			
			context.update("msgGeneral");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void eliminarBono(Bono bono){
		this.bonoSelec = bono;
	}
	
	/**##########################setter and  getter#####################################*/
	
	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		this.log = log;
	}

	public LogMB getLogmb() {
		return logmb;
	}

	public void setLogmb(LogMB logmb) {
		this.logmb = logmb;
	}

	public Integer getId_negocio() {
		return id_negocio;
	}

	public void setId_negocio(Integer id_negocio) {
		this.id_negocio = id_negocio;
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

	public SimpleDateFormat getSdfMes() {
		return sdfMes;
	}

	public void setSdfMes(SimpleDateFormat sdfMes) {
		this.sdfMes = sdfMes;
	}

	public SimpleDateFormat getSdfAnio() {
		return sdfAnio;
	}

	public void setSdfAnio(SimpleDateFormat sdfAnio) {
		this.sdfAnio = sdfAnio;
	}

	public List<Usuario> getListaPersonal() {
		return listaPersonal;
	}

	public void setListaPersonal(List<Usuario> listaPersonal) {
		this.listaPersonal = listaPersonal;
	}

	public List<Usuario> getListaPersonalFilter() {
		return listaPersonalFilter;
	}

	public void setListaPersonalFilter(List<Usuario> listaPersonalFilter) {
		this.listaPersonalFilter = listaPersonalFilter;
	}

	public Bono getBono() {
		return bono;
	}

	public void setBono(Bono bono) {
		this.bono = bono;
	}

	public LoginMB getLogin() {
		return login;
	}

	public void setLogin(LoginMB login) {
		this.login = login;
	}

	public String getAnioG() {
		return anioG;
	}

	public void setAnioG(String anioG) {
		this.anioG = anioG;
	}

	public String getMesG() {
		return mesG;
	}

	public void setMesG(String mesG) {
		this.mesG = mesG;
	}

	public List<Bono> getListaBono() {
		return listaBono;
	}

	public void setListaBono(List<Bono> listaBono) {
		this.listaBono = listaBono;
	}

	public List<Bono> getListaBonoFiltro() {
		return listaBonoFiltro;
	}

	public void setListaBonoFiltro(List<Bono> listaBonoFiltro) {
		this.listaBonoFiltro = listaBonoFiltro;
	}

	public List<Ciclo> getListaCiclos() {
		return listaCiclos;
	}

	public void setListaCiclos(List<Ciclo> listaCiclos) {
		this.listaCiclos = listaCiclos;
	}

	public List<Oficina> getListaOficinas() {
		return listaOficinas;
	}

	public void setListaOficinas(List<Oficina> listaOficinas) {
		this.listaOficinas = listaOficinas;
	}

	public Integer getF_cod_oficina() {
		return f_cod_oficina;
	}

	public void setF_cod_oficina(Integer f_cod_oficina) {
		this.f_cod_oficina = f_cod_oficina;
	}

	public Integer getF_cod_ciclo() {
		return f_cod_ciclo;
	}

	public void setF_cod_ciclo(Integer f_cod_ciclo) {
		this.f_cod_ciclo = f_cod_ciclo;
	}

	public List<TipoBono> getListaTipoBonos() {
		return listaTipoBonos;
	}

	public void setListaTipoBonos(List<TipoBono> listaTipoBonos) {
		this.listaTipoBonos = listaTipoBonos;
	}

	public boolean isEditar() {
		return editar;
	}

	public void setEditar(boolean editar) {
		this.editar = editar;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Bono getBonoSelec() {
		return bonoSelec;
	}

	public void setBonoSelec(Bono bonoSelec) {
		this.bonoSelec = bonoSelec;
	}

	public Usuario getUsuSelec() {
		return usuSelec;
	}

	public void setUsuSelec(Usuario usuSelec) {
		this.usuSelec = usuSelec;
	}

	public Bono getBonoFilter() {
		return bonoFilter;
	}

	public void setBonoFilter(Bono bonoFilter) {
		this.bonoFilter = bonoFilter;
	}

	
}
