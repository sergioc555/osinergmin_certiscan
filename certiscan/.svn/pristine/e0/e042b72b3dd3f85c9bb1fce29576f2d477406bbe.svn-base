package com.certicom.certiscan.managedBeans;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import com.certicom.certiscan.commons.Constante;
import com.certicom.certiscan.commons.FacesUtils;
import com.certicom.certiscan.commons.GenericBeans;
import com.certicom.certiscan.domain.Cargo;
import com.certicom.certiscan.domain.Centro_Atencion;
import com.certicom.certiscan.domain.Empresa;
import com.certicom.certiscan.domain.Log;
import com.certicom.certiscan.domain.Menu;
import com.certicom.certiscan.domain.Negocio;
import com.certicom.certiscan.domain.NegocioResponsables;
import com.certicom.certiscan.domain.Oficina;
import com.certicom.certiscan.domain.Perfil;
import com.certicom.certiscan.domain.Usuario;
import com.certicom.certiscan.domain.UsuarioPerfil;
import com.certicom.certiscan.services.CargoServices;
import com.certicom.certiscan.services.Centro_AtencionServices;
import com.certicom.certiscan.services.EmpresaService;
import com.certicom.certiscan.services.MenuServices;
import com.certicom.certiscan.services.NegocioResponsablesService;
import com.certicom.certiscan.services.NegocioServices;
import com.certicom.certiscan.services.OficinaService;
import com.certicom.certiscan.services.PerfilServices;
import com.certicom.certiscan.services.UsuarioPerfilServices;
import com.certicom.certiscan.services.UsuarioServices;
import com.ocpsoft.shade.org.apache.commons.beanutils.PropertyUtils;

@ManagedBean(name="usuarioMB")
@ViewScoped
public class UsuarioMB extends GenericBeans implements Serializable{
	
	@ManagedProperty(value="#{loginMB}")
	private LoginMB login;
	
	//private static Logger log = Logger.getLogger(UsuarioMB.class.getName());
	
	@ManagedProperty(value="#{loginMB.usuario}")
	private Usuario usuario;
	private List<Usuario> usuarios;
	private List<Usuario> usuariosFilter;
	private List<Usuario> ejecutivosFilter;
	private List<Cargo> listaCargo;
	private CargoServices cargoServices;
	private int id_perfil;
	private Perfil perfil;
	private UsuarioServices usuarioServices;
	private NegocioServices negocioServices;
	private MenuServices menuServices;
	private Centro_AtencionServices centro_atencionServices;
	private OficinaService oficinaService;
	private EmpresaService empresaService;
	private NegocioResponsablesService negocioResponsablesService;
	private int id_centro_atencion;
	private Boolean editar;
	private String lastLogin;
	private List<Empresa> listaEmpresa;
	private List<Centro_Atencion> listaCentrosAtencion;
	private List<Negocio> listaNegocio;
	private List<Oficina> listaOficina;
	private boolean view;
	RequestContext context;
	
	private Log log;
	private LogMB logmb;
	
	// Constructor
	public UsuarioMB() {		
	}

	@PostConstruct
	public void inicia(){ 
		this.editar = Boolean.FALSE;
		this.usuarioServices = new UsuarioServices();
		this.menuServices = new MenuServices();
		this.usuario = new Usuario();
		this.cargoServices = new CargoServices();
		this.listaCargo = new ArrayList<Cargo>();
		
		
	//	this.usuarios = usuarioServices.getlistaUsuario();
		if(this.login.getPerfilUsuario().getCod_perfil()== Constante.PERFIL_COD_GERENTE_PROYECTO){
			this.usuarios = usuarioServices.getlistaEjecutivo();
			this.view = true;
		}else{
			this.view = false;
			this.usuarios = usuarioServices.getlistaEjecutivoByNegocio(this.login.getUsuario().getId_negocio());
		}
		
		this.listaEmpresa = new ArrayList<>();
		this.listaCentrosAtencion = new ArrayList<>();
		this.listaNegocio = new ArrayList<>();
		this.listaOficina = new ArrayList<>();
		this.empresaService = new EmpresaService();
		this.centro_atencionServices = new Centro_AtencionServices();
		this.negocioServices = new NegocioServices();
		this.negocioResponsablesService = new NegocioResponsablesService();
		this.oficinaService = new OficinaService();
		
		try {
			this.listaCargo = this.cargoServices.findAll();
			this.listaEmpresa = this.empresaService.listaEmpresasActivas();
			this.listaOficina = this.oficinaService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.context = RequestContext.getCurrentInstance(); 
		this.log = (Log)getSpringBean(Constante.SESSION_LOG);
		this.logmb = new LogMB();
		
		try {
			Menu codMenu = menuServices.opcionMenuByPretty("pretty:usuarios");
			log.setCod_menu(codMenu.getCod_menu().intValue());
			log.setIdusuario(this.login.getIdUsuario());
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}
	
	public void listarProyectos(){
		try {
			this.listaCentrosAtencion = this.centro_atencionServices.listaProyectosxEmpresa(this.usuario.getId_empresa());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void listarNegocios(){
		try {
			this.listaNegocio = this.negocioServices.listaNegocioxEmpresa(this.usuario.getId_centro_atencion());
			if(this.login.getPerfilUsuario().getCod_perfil()!= Constante.PERFIL_COD_GERENTE_PROYECTO){
				usuario.setId_negocio(this.login.getUsuario().getId_negocio());
			}
			this.view = false;	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void cambiarEstado(Usuario user){
		if(user.getEstado()){
			user.setEstado(Boolean.FALSE);
		}else{
			user.setEstado(Boolean.TRUE);
		}
		try {
			this.usuarioServices.actualizarEstado(user);
			log.setAccion("UPDATE");
            log.setDescripcion("El usuario "+this.login.getLoginUsuario()+" actualizó el estado a : " + user.getEstado() + " del usuario: "+this.usuario.getLogin());
            logmb.insertarLog(log);
			FacesUtils.showFacesMessage("Se cambio de estado",Constante.INFORMACION);
		} catch (Exception e) {
			e.printStackTrace();
		}
	 }
	
	// Para generar los mensajes de validacion esto debe ir en otra clase
	// pero por avanze se hizo aca
	private void addMessage(String key, FacesMessage.Severity severity,
			String message, String detail) {
		FacesMessage msg = new FacesMessage(severity, message, detail);
		FacesContext.getCurrentInstance().addMessage(key, msg);
	}

	public String addErrorMessage() {
		addMessage(null, FacesMessage.SEVERITY_ERROR,
				"Sistema de Nextel", "Verifique sus Datos");
		return null;
	}

	// Para Cerrar la Session
	public String closeSession() throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext()
				.getSession(true);
		if (session == null) {
			return "invalid";
		} else {
			session.invalidate();
			System.out.println("Desconexion Correcta");
			return "login";
		}
	}

// registra 
	public void registrarUsuario() {
		Boolean valido=Boolean.FALSE;
		RequestContext context2 = RequestContext.getCurrentInstance();  
   	    context2.addCallbackParam("validationFailed", valido );
		
   	 	usuario.setEstado(true);
		
   	 	this.usuario.setApellido_paterno(this.usuario.getApellido_paterno().trim().toUpperCase());
   	 	this.usuario.setApellido_materno(this.usuario.getApellido_materno().trim().toUpperCase());
		this.usuario.setNombre(this.usuario.getNombre().trim().toUpperCase());
		this.usuario.setEmail(this.usuario.getEmail().trim().toUpperCase());
		this.usuario.setLogin(this.usuario.getLogin().trim());
		this.usuario.setDireccion(this.usuario.getDireccion().trim().toUpperCase());
		try {	
   	 	if(this.editar){
   	 		boolean pasaDni = true;
   	 		boolean pasaLogin = true;

   	 		Usuario usu = this.usuarioServices.getUsuario_byIdUsuario(this.usuario.getIdusuario());
   	 		if(usu.getDni().equals(this.usuario.getDni())){
   	 			pasaDni = true;
   	 		} else{
   	 			Usuario usuarioByDNI = usuarioServices.buscarPorDni(this.usuario.getDni());
   	 			if(usuarioByDNI==null){
   	 				pasaDni = true;
   	 			}else{
   	 				pasaDni = false;
   	 			}
   	 		}
   	 		
   	 		if(usu.getLogin().equals(this.usuario.getLogin())){
	 			pasaLogin = true;
	 		} else{
	 			Usuario usuarioByLogin = usuarioServices.buscarPorLogin(this.usuario.getLogin());
	 			if(usuarioByLogin==null){
	 				pasaLogin = true;
	 			}else{
	 				pasaLogin = false;
	 			}
	 		}
   	 		
   	 		
   	 		if(pasaDni){
   	 			if(pasaLogin){
   	 		
   	 				this.usuarioServices.actualizar(usuario);
   	 				id_centro_atencion = 0;
   	 				log.setAccion("UPDATE");
   	 				log.setDescripcion("El usuario "+this.login.getLoginUsuario()+" actualizó el usuario: "+this.usuario.getLogin());
   	 				logmb.insertarLog(log);
   	 				FacesUtils.showFacesMessage("Se actualizo correctamente el usuario",Constante.INFORMACION);
   	 				context2.update("msgGeneral");
   	 			
   	 			} else{
   	 			FacesUtils.showFacesMessage("Usuario ya existe",Constante.ERROR);
				 context2.addCallbackParam("validationFailed", Boolean.TRUE);
   	 			}
   	 		} else{
   	 			FacesUtils.showFacesMessage("DNI ya existe",Constante.ERROR);	
	 			context2.addCallbackParam("validationFailed", Boolean.TRUE );
   	 		}
	        
   	 	}else {
   	 		//Validando si el login ya se encuentra registrado
   	 		 Usuario usuarioByDNI = usuarioServices.buscarPorDni(this.usuario.getDni());
   	 			if(usuarioByDNI==null){
   	 			Usuario usuarioByLogin = usuarioServices.buscarPorLogin(this.usuario.getLogin());
				if(usuarioByLogin==null){
					usuario.setPassword(this.usuario.getDni());
		   	 		usuario.setEs_nuevo(Boolean.TRUE);
		   	 		usuario.setEstado_planilla(Boolean.TRUE);
		   	 		int idca = usuario.getId_centro_atencion();
		   	 		
		   	 		usuario.setId_centro_atencion(idca); 
		   	 		usuario.setDotacion(Boolean.TRUE);
		   	 		
		   	 		
					usuarioServices.insertUsuario(usuario);
					Integer lastId =  this.usuarioServices.getLastUser();
					
					NegocioResponsables nr = new NegocioResponsables();
					nr.setId_negocio(this.usuario.getId_negocio());
					nr.setIdusuario(lastId);
					nr.setEstado(Boolean.TRUE);
					this.negocioResponsablesService.crearNegocioResponsables(nr);
					
					id_centro_atencion = 0;
					log.setAccion("INSERT");
		            log.setDescripcion("El usuario "+this.login.getLoginUsuario()+" ingresó el usuario: "+ this.usuario.getLogin());
		            logmb.insertarLog(log);
		            FacesUtils.showFacesMessage("Usuario guardado exitosamente.",Constante.INFORMACION);
		            context2.update("msgGeneral");
				}else{
					FacesUtils.showFacesMessage("Usuario ya existe",Constante.ERROR);
					 context2.addCallbackParam("validationFailed", Boolean.TRUE);
				}
   	 			}else{
   	 			FacesUtils.showFacesMessage("DNI ya existe",Constante.ERROR);	
   	 			context2.addCallbackParam("validationFailed", Boolean.TRUE );
   	 		}
   	 			
   	 			
   	 	}	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   	 		this.usuarios = usuarioServices.getlistaUsuario();
   	 		this.usuarios = usuarioServices.getlistaEjecutivo();
			this.editar = Boolean.FALSE;
	}

	
	
	public void eliminarUsuario() {
		System.out.println("Vamos a Realizar una eliminacion ");
		try {
			usuarioServices.deleteUsuario(usuario.getIdusuario());
			usuarios = usuarioServices.getlistaUsuario();
			usuarios = usuarioServices.getlistaEjecutivo();
			/*for(Usuario u : usuarios){
				try {
					Centro_Atencion c = new Centro_Atencion();
					c = centro_atencionServices.findById(u.getId_centro_atencion());
					u.setNombre_centro_atencion(c.getNombre()); 
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}*/
			log.setAccion("DELETE");
            log.setDescripcion("El usuario "+this.login.getLoginUsuario()+" eliminó el usuario: "+this.usuario.getLogin());
            logmb.insertarLog(log);
			FacesUtils.showFacesMessage("Usuario eliminado exitosamente.", Constante.INFORMACION);
		} catch (Exception e) {
			FacesUtils.showFacesMessage("Usuario no se puede eliminar tiene perfiles asociados", Constante.ERROR);
			e.printStackTrace();
		}
		//context.update("msgGeneral");
		
	}


	public void newInsert() {
		usuario = new Usuario();
		
		this.editar = Boolean.FALSE;
	}

	public void newUpdate(Usuario usu) {
		System.out.println("editando usuario");
		this.editar = Boolean.TRUE;
		this.usuario = usuarioServices.buscarPorId(usu.getIdusuario());
		//this.usuario = usu;
		lastLogin = usuario.getLogin();
		System.out.println("centro de atencion: "+ usuario.getId_centro_atencion());

	try {
		this.listaEmpresa = this.empresaService.findAll();
		this.listaCentrosAtencion = this.centro_atencionServices.listaProyectosxEmpresa(this.usuario.getId_empresa());
		this.listaNegocio = this.negocioServices.listaNegocioxEmpresa(this.usuario.getId_centro_atencion());
	
	} catch (Exception e) {
		e.printStackTrace();
	}
		 //this.usuario.getId_empresa(); 
		// this.usuario.getId_centro_atencion(); 
		 //this.usuario.getId_negocio();
		
   
		
	}

	public void newDelete(Usuario user) {
		this.usuario = user;
	}

	public void resetearPassword(){
		RequestContext context2;
		context2 = RequestContext.getCurrentInstance();
		 
		System.out.println("el idusuario que selecciono es  "+ usuario.getIdusuario());
		
		try {
			this.usuarioServices.resetearPassword(this.usuario);
			 FacesUtils.showFacesMessage("Reseteado Password Correctamente.",Constante.INFORMACION);
			context2.update("msgGeneral");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/****************************/
	public void newUserResetPass(Usuario usu){
		this.usuario = usu;
	}
	

	public String agregarPerfil(Usuario user) {
		String outcome=null;
		//log.info("entrando a agregar perfil");
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("usuario", user);
		System.out.println("enviando cadena de redireccion");
		outcome="pretty:addPerfilUsuario";	
		log.setAccion("UPDATE");
        log.setDescripcion("El usuario "+this.login.getLoginUsuario()+ " actualizó el perfil a : " + user.getEstado() + " del usuario: "+this.usuario.getLogin());
        logmb.insertarLog(log);
		return outcome;
		//return "/faces/control_acceso/addPerfilUsuario?faces-redirect=true";
			
	}
	
	// Getter and Setter
	
		public Usuario getUsuario() {
			return usuario;
		}

		public List<Empresa> getListaEmpresa() {
			return listaEmpresa;
		}

		public void setListaEmpresa(List<Empresa> listaEmpresa) {
			this.listaEmpresa = listaEmpresa;
		}

		public List<Negocio> getListaNegocio() {
			return listaNegocio;
		}

		public void setListaNegocio(List<Negocio> listaNegocio) {
			this.listaNegocio = listaNegocio;
		}

		public List<Usuario> getUsuariosFilter() {
			return usuariosFilter;
		}

		public void setUsuariosFilter(List<Usuario> usuariosFilter) {
			this.usuariosFilter = usuariosFilter;
		}

		public void setUsuario(Usuario usuario) {
			this.usuario = usuario;
		}

		public UsuarioServices getUsuarioServices() {
			return usuarioServices;
		}

		public void setUsuarioServices(UsuarioServices usuarioServices) {
			this.usuarioServices = usuarioServices;
		}

		public void setUsuarios(List<Usuario> usuarios) {
			this.usuarios = usuarios;
		}

		public List<Usuario> getUsuarios() {
			return usuarios;
		}
		
		public Perfil getPerfil() {
			return perfil;
		}

		public void setPerfil(Perfil perfil) {
			this.perfil = perfil;
		}

		

		public int getId_perfil() {
			return id_perfil;
		}

		public void setId_perfil(int id_perfil) {
			this.id_perfil = id_perfil;
		}

		
		public void setLogin(LoginMB login) {
			this.login = login;
		}

		public LoginMB getLogin() {
			return login;
		}

		public Boolean getEditar() {
			return editar;
		}

		public void setEditar(Boolean editar) {
			this.editar = editar;
		}
		
		public List<Centro_Atencion> getListaCentrosAtencion() throws Exception {
			//listaCentrosAtencion = centro_atencionServices.findAll();
			return listaCentrosAtencion;
			//return centro_atencionServices.findAll();
		}

		public void setListaCentrosAtencion(List<Centro_Atencion> listaCentrosAtencion) {
			this.listaCentrosAtencion = listaCentrosAtencion;
		}

		public int getId_centro_atencion() {
			return id_centro_atencion;
		}

		public void setId_centro_atencion(int id_centro_atencion) {
			this.id_centro_atencion = id_centro_atencion;
		}

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

		public String getLastLogin() {
			return lastLogin;
		}

		public void setLastLogin(String lastLogin) {
			this.lastLogin = lastLogin;
		}

		public List<Usuario> getEjecutivosFilter() {
			return ejecutivosFilter;
		}

		public void setEjecutivosFilter(List<Usuario> ejecutivosFilter) {
			this.ejecutivosFilter = ejecutivosFilter;
		}

		public boolean isView() {
			return view;
		}

		public void setView(boolean view) {
			this.view = view;
		}

		public List<Oficina> getListaOficina() {
			return listaOficina;
		}

		public void setListaOficina(List<Oficina> listaOficina) {
			this.listaOficina = listaOficina;
		}

		public List<Cargo> getListaCargo() {
			return listaCargo;
		}

		public void setListaCargo(List<Cargo> listaCargo) {
			this.listaCargo = listaCargo;
		}

		
	

	
	
	/*
	
	public void insertarPerfil(){
		
		Boolean valido=Boolean.FALSE;
		RequestContext context = RequestContext.getCurrentInstance();
		if(this.id_perfil==0){ //validando que seleccion dferente a "seleccione"
			addMessage(null, FacesMessage.SEVERITY_WARN,
					"Seleccione", "Seleccione un perfil valido");
			//System.out.println("seleccione un perfil valido");
			context.addCallbackParam("esValido", valido );
		}else{
			//System.out.println("verificando que no este repetido");
			try {
				this.usuarioPerfil= this.usuarioPerfilServices.buscarPerfilUsuario(this.usuario.getIdusuario(), this.id_perfil);
				if(this.usuarioPerfil==null){
					valido=Boolean.TRUE;
				}else{
					addMessage(null, FacesMessage.SEVERITY_WARN,"Perfil ya existe", "Seleccione un perfil valido");
				}
			} catch (Exception e) {
				e.printStackTrace();
			  }

			context.addCallbackParam("esValido", valido );
			
			if(valido){
				//System.out.println("Ahora si grabamos");
				try {
					this.perfil=this.perfilServices.findPerfilPorCodigo(this.id_perfil);
					UsuarioPerfil usrPerfil = new UsuarioPerfil();
					usrPerfil.setCod_perfil(this.id_perfil);
					usrPerfil.setIdusuario(this.usuario.getIdusuario());
					usrPerfil.setFecha_finvig(new Date());
					usrPerfil.setFecha_iniciovig(new Date());
					usrPerfil.setFecha_modif(new Date());
					usrPerfil.setUsuario_modif(this.login.getLogin());
					usrPerfil.setUsuario_registro(this.login.getLogin());
					usrPerfil.setFecha_registro(new Date());
					usrPerfil.setInd_activo(Boolean.TRUE);
					this.usuarioPerfilServices.insertUsuarioPeril(usrPerfil);
					this.listaPerfilUsuario=this.usuarioPerfilServices.listarPerfilesPorUsuario(this.usuario.getIdusuario());
				} catch (Exception e) {
					System.out.println("Error :"+e.getMessage());
					e.printStackTrace();
				}
				addMessage(null, FacesMessage.SEVERITY_INFO,
						"Guardado", "Perfil guardado correctamente");
				RequestContext.getCurrentInstance().update("grSms");
			}
		}
		
	}
	
	
	public void eliminarPerfil(){
		System.out.println("eliminar perfil: "+this.usuarioPerfil.getCod_perfil());
		try{
			this.usuarioPerfilServices.eliminarPerfilUsuario(this.usuario.getIdusuario(),this.usuarioPerfil.getCod_perfil());
			PerfilUsuario = this.usuarioPerfilServices.listarPerfilesPorUsuario(this.usuario.getIdusuario());
			addMessage(null, FacesMessage.SEVERITY_INFO,
					"Eliminacion", "Perfil eliminado");
			RequestContext.getCurrentInstance().update("grSms");
		}catch(Exception e){
			System.out.println("Error :"+e.getMessage());
		}
		
	}
*/
	

}