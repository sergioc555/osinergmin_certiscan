package com.certicom.certiscan.managedBeans;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.model.StreamedContent;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import com.certicom.certiscan.commons.Constante;
import com.certicom.certiscan.commons.FacesUtils;
import com.certicom.certiscan.commons.GenericBeans;
import com.certicom.certiscan.domain.Centro_Atencion;
import com.certicom.certiscan.domain.CompresionLectura;
import com.certicom.certiscan.domain.Log;
import com.certicom.certiscan.domain.Menu;
import com.certicom.certiscan.domain.Negocio;
import com.certicom.certiscan.domain.Oficina;
import com.certicom.certiscan.domain.Parametro;
import com.certicom.certiscan.domain.Perfil;
import com.certicom.certiscan.domain.Sistema;
import com.certicom.certiscan.domain.Usuario;
import com.certicom.certiscan.services.Centro_AtencionServices;
import com.certicom.certiscan.services.ComprensionLecturaService;
import com.certicom.certiscan.services.MenuServices;
import com.certicom.certiscan.services.NegocioServices;
import com.certicom.certiscan.services.OficinaService;
import com.certicom.certiscan.services.ParametroServices;
import com.certicom.certiscan.services.PerfilServices;
import com.certicom.certiscan.services.SistemaServices;
import com.certicom.certiscan.services.UsuarioPerfilServices;
import com.certicom.certiscan.services.UsuarioServices;
import com.ocpsoft.shade.org.apache.commons.beanutils.PropertyUtils;

@ManagedBean(name = "loginMB")
@SessionScoped
public class LoginMB extends GenericBeans implements Serializable {

	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	private String login;
	private String password;
	private String path_pdf;
	private List<Perfil> listaPerfiles;
	private List<Sistema> listaModulos;
	private List<Menu> listaMenu;
	private MenuModel model;
	private MenuModel modelSalir;
	private String fechaAcceso;
	private String centroAtencion;
	private String negocio;
	private String oficina_nombre;
	private String nuevoPass;
	private String nuevoPassRe;
	private int tiempo_cambio_password;
	private int idUsuario;
	private String loginUsuario;
	private Perfil perfilUsuario;
	private StreamedContent archivoFoto;
	private Parametro parametro;
	private Boolean parametro_lavado_activo;
	private Boolean primeraLectura = true;
	private StreamedContent file;

	// flag para validaciones especiales;
	private boolean admin = false;
	
	private boolean flagPyme = false;
	private boolean flagBanPer = false;
	private boolean flagVip = false;

	private boolean flagEjecutivo = false;
	private boolean flagSupervisor = false;
	private boolean flagCoordinador = false;
	private boolean flagBack = false;

	private boolean flagPymeEjecutivo = false;
	private boolean flagPymeSupervisor = false;
	private boolean flagPymeCoordinador = false;
	private boolean flagPymeBack = false;
	
	private boolean flagBanPerCoordinador = false;
	private boolean flagBanPerSupervisorHipo = false;
	private boolean flagBanPerEjecutivoHipo = false;
	
	private boolean flagBanPerEjecutivo = false;
	private boolean flagPHSupervisor = false;
	private boolean flagBanPerBack = false;
	
	
	private boolean flagConcesionario = false;
	private boolean flagSupervisorCon = false;
	private boolean flagVendedorCon = false;
	

	public StreamedContent getArchivoFoto() {
		return archivoFoto;
	}

	public void setArchivoFoto(StreamedContent archivoFoto) {
		this.archivoFoto = archivoFoto;
	}

	UsuarioServices usuarioServices;
	ParametroServices parametroServices;
	PerfilServices perfilServices;
	SistemaServices sistemaServices;
	MenuServices menuServices;
	Centro_AtencionServices centro_atencionServices;
	UsuarioPerfilServices usuarioPerfilServices;
	NegocioServices negocioServices;
	ComprensionLecturaService comprensionLecturaService;
	OficinaService oficinaService;

	private Log log;
	private LogMB logmb;

	public LoginMB() {

	}

	@PostConstruct
	public void inicia() {
		
		usuario = new Usuario();
		perfilUsuario = new Perfil();
		usuarioServices = new UsuarioServices();
		perfilServices = new PerfilServices();
		sistemaServices = new SistemaServices();
		menuServices = new MenuServices();
		parametroServices = new ParametroServices();
		centro_atencionServices = new Centro_AtencionServices();
		usuarioPerfilServices = new UsuarioPerfilServices();
		negocioServices = new NegocioServices();
		comprensionLecturaService = new ComprensionLecturaService();
		oficinaService = new OficinaService();
		parametro_lavado_activo = Boolean.FALSE;
		
		//this.path_pdf = "resources/lecturas_pdf/Prevencion_del_lavado_de_Activos.pdf";
		this.path_pdf = "resources/lecturas_pdf/Prestamos_de_Consumo_Fuvex_Mayo.pdf";
		
		// FileInputStream stream;
		// try {
		// stream = new FileInputStream(new
		// File("resources/lecturas_pdf/pymes.pdf"));
		// this.file = new DefaultStreamedContent(stream, "application/pdf",
		// "pymes.pdf");
		// } catch (FileNotFoundException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// flags
		this.flagPyme = false;
		this.flagBanPer = false;
		this.flagVip = false;

		this.flagEjecutivo = false;
		this.flagSupervisor = false;
		this.flagCoordinador = false;

		this.flagPymeEjecutivo = false;
		this.flagPymeSupervisor = false;
		this.flagPymeCoordinador = false;

		this.flagBanPerEjecutivo = false;
		this.flagBanPerBack = false;
		log = (Log)getSpringBean(Constante.SESSION_LOG);
		logmb = new LogMB();
		//int codMenu;
		try {
			//codMenu = menuServices.opcionMenuByPrettyCod("pretty:usuarios");
			//log.setCod_menu(codMenu);
			Menu codMenu = menuServices.opcionMenuByPretty("pretty:usuarios");
			log.setCod_menu(codMenu.getCod_menu().intValue());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public Date sumaDias(Date fecha, int dias) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(fecha);
		cal.add(Calendar.DAY_OF_YEAR, dias);
		return cal.getTime();
	}

	public String efectuarLogin(String ipClient) {
		String retorno = null;
		log = new Log();
		logmb = new LogMB();

		try {
			String valor = parametroServices.findParametro_byNombre("TIEMPO_CAMBIO_PASSWORD");
			tiempo_cambio_password = Integer.parseInt(valor);

			List<Usuario> list = usuarioServices.buscarPorLoginClave(new Usuario(login, password));
			Usuario user = usuarioServices.buscarPorLogin(login);
			// String localHost=
			// String.valueOf(InetAddress.getLocalHost().getHostAddress());
			getMacAddres(ipClient);
			/** Para Log **/

			log.setCod_menu(0);
			log.setAccion("LOGIN");
			log.setIp_client(ipClient);
			setBean(log);
			Log logEnSesion = (Log) getSpringBean(Constante.SESSION_LOG);
			PropertyUtils.copyProperties(logEnSesion, log);
			System.out.println("3");
			listaPerfiles = new ArrayList<Perfil>();
			listaModulos = new ArrayList<Sistema>();
			listaMenu = new ArrayList<Menu>();
			if (list.size() == 0) {
				FacesUtils.showFacesMessage("Usuario y/o clave incorrectos", Constante.ERROR);
				log.setDescripcion("Usuario " + login + " no pudo logearse");
				logmb.insertarLog(log);
			} else {
				log.setIdusuario(user.getIdusuario());
				if (list.get(0).getEstado()) {

					usuario = (list.get(0));

					setBean(usuario);
					idUsuario = usuario.getIdusuario();
					loginUsuario = usuario.getLogin();
					Usuario usuarioEnSesion = (Usuario) getSpringBean(Constante.SESSION_USER);
					PropertyUtils.copyProperties(usuarioEnSesion, usuario);
					/******************************************************************************/

					Centro_Atencion c = new Centro_Atencion();
					c = centro_atencionServices.findById(usuario.getId_centro_atencion());
					centroAtencion = c.getNombre();
					
					Oficina ofic = new Oficina();
					ofic  = oficinaService.findById(usuario.getId_oficina());
					oficina_nombre = ofic.getNombre();

					Negocio n = new Negocio();
					n = negocioServices.findById(usuario.getId_negocio());
					negocio = n.getDescripcion();

					FacesUtils.setUsuarioLogueado(usuario);

					if (this.usuario.getFecha_acceso() != null) {
						String date = sumaDias(this.usuario.getFecha_acceso(), tiempo_cambio_password).toString().substring(0, 10);
						String fechaActual = new Date().toString().substring(0, 10);
						if (fechaActual.equals(date)) {
							this.usuario.setEs_nuevo(Boolean.TRUE);
						}

					}
					// actualizar fecha de ultimo acceso
					DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
					usuario.setFecha_acceso(new Date());
					usuarioServices.actualizarFechaAcceso(usuario);
					this.fechaAcceso = df.format(usuario.getFecha_acceso());

					// Listar los perfiles del usuario
					listaPerfiles = perfilServices.listarPerfilesxUsuario(usuario);

					List<Sistema> lista = new ArrayList<Sistema>();
					List<Menu> lista2 = new ArrayList<Menu>();

					// Listar los modulos que tiene el usuario
					if (listaPerfiles.size() > 0) {
						for (int k = 0; k < listaPerfiles.size(); k++) {
							lista = sistemaServices.listSistemaxPerfil(getListaPerfiles().get(k));
							lista2 = menuServices.listMenuxSistema(getListaPerfiles().get(k));

							// Aï¿½adir cada modulo
							// listaModulos
							if (lista.size() > 0) {
								if (listaModulos.size() > 0) {
									// Comparar que no se repitan los modulos
									for (int p = 0; p < lista.size(); p++) {
										for (int o = 0; o < listaModulos.size(); o++) {
											if (getListaModulos().get(o).getCod_sistema() == lista.get(p).getCod_sistema()) {
												break;
											} else {
												if (o == (listaModulos.size() - 1))
													listaModulos.add(lista.get(p));
												else
													continue;
											}
										}
									}
								} else {
									for (int m = 0; m < lista.size(); m++) {
										listaModulos.add(lista.get(m));
									}
								}
							}

							if (lista2.size() > 0) {
								if (listaMenu.size() > 0) {
									// Comparar que no se repitan los modulos
									for (int p = 0; p < lista2.size(); p++) {
										for (int o = 0; o < listaMenu.size(); o++) {
											if (getListaMenu().get(o).getCod_menu() == lista2.get(p).getCod_menu()) {
												break;
											} else {
												if (o == (listaMenu.size() - 1))
													listaMenu.add(lista2.get(p));
												else
													continue;
											}
										}
									}
								} else {
									for (int m = 0; m < lista2.size(); m++) {
										listaMenu.add(lista2.get(m));
									}
								}
							}
						}

						model = new DefaultMenuModel();

						DefaultMenuItem item = new DefaultMenuItem();
						DefaultSubMenu submenu = new DefaultSubMenu();

						List<Menu> listaSubMenu = new ArrayList();
						listaSubMenu = listaMenu;
						MethodExpression methodExpression;
						for (int k = 0; k < listaModulos.size(); k++) {

							DefaultSubMenu menu = new DefaultSubMenu(listaModulos.get(k).getNombre_sistema());
							for (int m = 0; m < listaMenu.size(); m++) {
								if (listaMenu.get(m).getCod_sistema() == listaModulos.get(k).getCod_sistema()) {
									boolean genSubmenu = false, gen = false;
									submenu = new DefaultSubMenu("");
									for (int p = 0; p < listaSubMenu.size(); p++) {
										if (listaSubMenu.get(p).getCod_menu_padre() == listaMenu.get(m).getCod_menu()) {
											Menu men = new Menu();
											men = listaMenu.get(p);
											men.setEst(true);
											listaMenu.set(p, men);
											men = new Menu();
											men = listaMenu.get(m);
											men.setEst(true);
											listaMenu.set(m, men);
											genSubmenu = true;
											gen = true;

											DefaultMenuItem mi = new DefaultMenuItem(listaSubMenu.get(p).getNombre());
											mi.setOutcome(listaSubMenu.get(p).getAction());
											mi.setIcon(listaSubMenu.get(p).getIcono());
											submenu.addElement(mi);
										}

										if (p == (listaSubMenu.size() - 1)) {
											if (gen) {
												if (genSubmenu) {
													
													submenu.setLabel(listaMenu.get(m).getNombre());
													submenu.setIcon(listaMenu.get(m).getIcono());
													menu.addElement(submenu);
												} else {
													
													item = new DefaultMenuItem();
													item.setValue(listaMenu.get(m).getNombre());
													item.setOutcome(listaMenu.get(m).getAction());
													item.setIcon(listaMenu.get(m).getIcono());
													menu.addElement(item);
												}
												genSubmenu = false;
												gen = false;
											} else if (listaMenu.get(m).isEst() == false) {
												
												item = new DefaultMenuItem();
												item.setValue(listaMenu.get(m).getNombre());
												item.setIcon(listaMenu.get(m).getIcono());
												if(listaMenu.get(m).getNombre().equals("E-Learning")){
													item.setUrl("http://10.162.172.247/Dokeos/dokeos/index.php");
													item.setTarget("_blank");
												} else{
													item.setOutcome(listaMenu.get(m).getAction());
												}
												
												menu.addElement(item);
											}
										}
									}
								}
							}
							model.addElement(menu);
						}

						DefaultSubMenu menu = new DefaultSubMenu();
						menu.setLabel("Opciones");
						item = new DefaultMenuItem();
						item.setValue("Cambiar Password");
						item.setIcon("icon-password");
						item.setCommand("#{loginMB.iniciarCambioPassword_Voluntario}");
						menu.addElement(item);

						// Aï¿½adir el log off

						item = new DefaultMenuItem();
						item.setValue("Salir");
						item.setIcon("icon-exit");
						item.setCommand("#{loginMB.efectuarLogoff}");
						menu.addElement(item);
						model.addElement(menu);

						// Aï¿½adir Home
						/*
						 * item = new MenuItem();
						 * item.setValue("Principal");item
						 * .setIcon("icon-home");item.setUrl("/");
						 * menu.getChildren().add(item); model.addSubmenu(menu);
						 */
						log.setDescripcion("Usuario " + login + " Logeado correctamente");
						System.out.println("---------Log:-------- \n Usuario:" + log.getIdusuario() + "\n Ip: " + log.getIp_client());

						logmb.insertarLog(log);
						System.out.println("Login usuario:" + this.usuario.getLogin());
						setModel(model);

						// VERIFICANDO CUAL ES EL MAYOR PERFIL QUE TIENE EL
						// USUARIO:(nO cambiar el orden de los for)
						if (listaPerfiles.size() > 1) {
							for (Perfil p : listaPerfiles) {
								if (p.getCod_perfil().equals(Constante.PERFIL_COD_EJECUTIVO) || p.getCod_perfil().equals(Constante.PERFIL_COD_BACKOFFICE) 
										|| p.getCod_perfil().equals(Constante.PERFIL_COD_EJECUTIVO_BANCA_PERSONA) || p.getCod_perfil().equals(Constante.PERFIL_COD_EJECUTIVO_PYME)) {
									perfilUsuario = p;
								}
							}

							for (Perfil p : listaPerfiles) {
								if (p.getCod_perfil().equals(Constante.PERFIL_COD_SUPERVISOR)) {
									perfilUsuario = p;
								}
							}

							for (Perfil p : listaPerfiles) {
								if (p.getCod_perfil().equals(Constante.PERFIL_COD_COORDINADOR)) {
									perfilUsuario = p;
								}
							}

							for (Perfil p : listaPerfiles) {
								if (p.getCod_perfil().equals(Constante.PERFIL_COD_GERENTE_PROYECTO)) {
									perfilUsuario = p;
								}
							}
						} else
							perfilUsuario = listaPerfiles.get(0);

						if (perfilUsuario.getCod_perfil().intValue() == Constante.PERFIL_COD_SUPERVISOR || perfilUsuario.getCod_perfil().intValue() == Constante.PERFIL_COD_COORDINADOR || perfilUsuario.getCod_perfil().intValue() == Constante.PERFIL_COD_GERENTE_PROYECTO_VIP || perfilUsuario.getCod_perfil().intValue() == Constante.PERFIL_COD_GERENTE_PROYECTO_BNPERSON) {
							this.parametro = this.parametroServices.findByNombre(Constante.ALERTA_SUPERVISOR);

						} else if (perfilUsuario.getCod_perfil().intValue() == Constante.PERFIL_COD_EJECUTIVO) {
							this.parametro = this.parametroServices.findByNombre(Constante.ALERTA_EJECUTIVO);

						} else if (perfilUsuario.getCod_perfil().intValue() == Constante.PERFIL_COD_BACKOFFICE_PYME || perfilUsuario.getCod_perfil().intValue() == Constante.PERFIL_COD_SUPERVISOR_PYME || perfilUsuario.getCod_perfil().intValue() == Constante.PERFIL_COD_COORDINADOR_PYME || perfilUsuario.getCod_perfil().intValue() == Constante.PERFIL_COD_EJECUTIVO) {
							Parametro paramet = this.parametroServices.findByNombre(Constante.LAVADO_ACTIVO);
							if (paramet != null) {
								if (paramet.getValor() == 1) {
									// System.out.println(" EL PARAMETRO DE LAVADO DE ACTIVO ES "
									// + paramet.getValor());
									//this.parametro_lavado_activo = Boolean.TRUE;
								} else {
									//this.parametro_lavado_activo = Boolean.FALSE;
								}

							}
							System.out.println("el valor de parametro_lavado_activo " + parametro_lavado_activo);

							Integer cantidad = this.comprensionLecturaService.getLecturasByUser(this.usuario.getIdusuario());
							if (cantidad == 0)
								this.primeraLectura = Boolean.TRUE;
							else
								this.primeraLectura = Boolean.FALSE;

						}

						retorno = "pretty:principal";
					} else {
						FacesUtils.showFacesMessage("Usuario no tiene ningun perfil asociado", Constante.ERROR);
						return retorno;
					}
				} else {
					System.out.println("usuario bloqueado");
					FacesUtils.showFacesMessage("Usuario bloqueado", Constante.ERROR);
				}
			}

			guardarFlagPerfil(); // guardando los flags de usuario

			// CAMBIO PARA QUE EL MENSAJE SE MUESTRE SIEMPRE
			
			if(this.isFlagBanPer()){
				Parametro paramet = this.parametroServices.findByNombre(Constante.LAVADO_ACTIVO);
				if (paramet != null) {
					if (paramet.getValor() == 1) {
						this.parametro_lavado_activo = Boolean.TRUE;
					} else {
						this.parametro_lavado_activo = Boolean.FALSE;
					}

				}
			}

			return retorno;
		} catch (Exception e) {
			FacesUtils.showFacesMessage("Ocurrio un problema de conexion, intentelo mas tarde", Constante.ERROR);
			e.printStackTrace();
			return retorno;
		}

	}

	public void getMacAddres(String ip) {
		try {
			InetAddress address = InetAddress.getByName("10.162.170.103");

			NetworkInterface ni2 = NetworkInterface.getByName("eth0");

			NetworkInterface ni = NetworkInterface.getByInetAddress(address);
			if (ni != null) {
				byte[] mac = ni.getHardwareAddress();
				if (mac != null) {
					for (int i = 0; i < mac.length; i++) {
						System.out.format("Mac: %02X%s", mac[i], (i < mac.length - 1) ? "-" : "");
					}
				} else {
					System.out.println("Address doesn't exist or is not " + "accessible.");
				}
			} else {
				System.out.println("Network Interface for the specified " + "address is not found.");
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	public MethodExpression generarActionMenu(String action) {
		ExpressionFactory factory = FacesContext.getCurrentInstance().getApplication().getExpressionFactory();
		return factory.createMethodExpression(FacesContext.getCurrentInstance().getELContext(), action, String.class, new Class[] {});
	}

	public String efectuarLogoff() {

		FacesUtils.removeUsuarioLogueado();
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.invalidate();
		return "pretty:login";
	}

	public String iniciaCambiarPassword() {
		String outcome = null;
		this.usuario.setEs_nuevo(Boolean.TRUE);
		this.usuarioServices.actualizar(this.usuario);
		outcome = "pretty:principal";
		return outcome;
	}

	public String iniciarCambioPassword_Voluntario() {
		return "pretty:cambioPass";
	}

	public void cambiarPassword_Voluntario() {
		// String outcome = null;
		// comparando y cambiando
		if (this.nuevoPass.equals(this.nuevoPassRe)) { // ok
			if (nuevoPass.length() >= 6) {
				this.usuario.setPassword(this.nuevoPass);
				this.usuario.setFecha_acceso(new Date());
				this.usuario.setEs_nuevo(Boolean.FALSE);
				this.usuario.setFecha_cambio_password(new Date());
				
				this.usuarioServices.actualizarPassword(this.usuario);
				
				log.setAccion("UPDATE");
		        log.setDescripcion("Se actualizó el password del usuario: "+this.usuario.getLogin());
		        logmb.insertarLog(log);
				this.usuarioServices.actualizarFechaAcceso(this.usuario);
				
				log.setAccion("UPDATE");
		        log.setDescripcion("Se actualizó el acceso del usuario: "+this.usuario.getLogin());
		        logmb.insertarLog(log);
				// Insertar en la tabla de Log
				// log.setAccion("Cambio Password");
				// log.setDescripcion("Usuario: "+login+" cambiÃ³ su password correctamente");
				// logmb.insertarLog(log);

				nuevoPass = "";
				nuevoPassRe = "";
				// matar sesion:
				// outcome="pretty:cambioPass";
				// this.efectuarLogoff();

				FacesUtils.showFacesMessage("El password se ha cambiado correctamente", Constante.INFORMACION);
			} else {
				FacesUtils.showFacesMessage("Por seguridad, la contraseÃ±a debe tener al menos 6 caracteres", Constante.ADVERTENCIA);
			}

		} else {
			// mostrar alerta
			FacesUtils.showFacesMessage("ContraseÃ±as no coinciden", Constante.ADVERTENCIA);
		}
		// return outcome;
	}

	/**
	 * Desc: cambia password
	 */
	public String cambiarPassword() {
		String outcome = null;
		// comparando y cambiando
		if (this.nuevoPass.equals(this.nuevoPassRe)) { // ok
			this.usuario.setPassword(this.nuevoPass);
			this.usuario.setFecha_acceso(new Date());
			this.usuario.setEs_nuevo(Boolean.FALSE);
			this.usuario.setFecha_cambio_password(new Date());

			this.usuarioServices.actualizarPassword(this.usuario);
			
			log.setAccion("UPDATE");
	        log.setDescripcion("Se actualizó el password del usuario: "+this.usuario.getLogin());
	        logmb.insertarLog(log);
			
			this.usuarioServices.actualizarFechaAcceso(this.usuario);
			
			log.setAccion("UPDATE");
	        log.setDescripcion("Se actualizó el acceso del usuario: "+this.usuario.getLogin());
	        logmb.insertarLog(log);
			// matar sesion:

			outcome = "pretty:login";
			this.efectuarLogoff();
		} else {
			// mostrar alerta
			FacesUtils.showFacesMessage("Contraseï¿½as no coinciden", Constante.ADVERTENCIA);
		}
		return outcome;
	}

	// evaluador de perfil
	public void guardarFlagPerfil() {
		if(this.perfilUsuario.getCod_perfil() == Constante.PERFIL_COD_GERENTE_PROYECTO){
			this.admin = true;
		}
		
		if (this.usuario.getId_negocio() == Constante.NEGOCIO_COD_PYME) {
			this.flagPyme = true;
			if (this.perfilUsuario.getCod_perfil() == Constante.PERFIL_COD_EJECUTIVO  || this.perfilUsuario.getCod_perfil() == Constante.PERFIL_COD_EJECUTIVO_PYME) {
				this.flagEjecutivo = true;
				this.flagPymeEjecutivo = true;
			} else if (this.perfilUsuario.getCod_perfil() == Constante.PERFIL_COD_VENDEDOR ){
				this.flagConcesionario  = true;
				this.flagVendedorCon = true;
			} else if (this.perfilUsuario.getCod_perfil() == Constante.PERFIL_COD_SUPERVISOR_CONSESIONARIO ){
				this.flagConcesionario  = true;
				this.flagSupervisorCon = true;
			} else if (this.perfilUsuario.getCod_perfil() == Constante.PERFIL_COD_SUPERVISOR_PYME) {
				this.flagSupervisor = true;
				this.flagPymeSupervisor = true;
			} else if (this.perfilUsuario.getCod_perfil() == Constante.PERFIL_COD_COORDINADOR_PYME) {
				this.flagCoordinador = true;
				this.flagPymeCoordinador = true;
			} else if (this.perfilUsuario.getCod_perfil() == Constante.PERFIL_COD_BACKOFFICE_PYME) {
				this.flagBack = true;
				this.flagPymeBack = true;
			}
		} else if (this.usuario.getId_negocio() == Constante.NEGOCIO_COD_BANCA_PERSONA) {
			this.flagBanPer = true;
			if(this.perfilUsuario.getCod_perfil() == Constante.PERFIL_COD_COORDINADOR){
				this.flagBanPerCoordinador = true;
				this.flagPHSupervisor = true;
			} else if(this.perfilUsuario.getCod_perfil() == Constante.PERFIL_COD_SUPERVISOR){
				this.flagSupervisor = true;
			} else if (this.perfilUsuario.getCod_perfil() == Constante.PERFIL_COD_EJECUTIVO) {
				this.flagEjecutivo = true;
				this.flagBanPerEjecutivo = true;
			} else if (this.perfilUsuario.getCod_perfil() == Constante.PERFIL_COD_SUPERVISOR_PH) {
				this.flagPHSupervisor = true;
			} else if (this.perfilUsuario.getCod_perfil() == Constante.PERFIL_COD_SUPERVISOR_HIPOTECARIO) {
				this.flagBanPerSupervisorHipo = true;
			} else if (this.perfilUsuario.getCod_perfil() == Constante.PERFIL_COD_BACKOFFICE) {
				this.flagBanPerBack = true;
			} else if(this.perfilUsuario.getCod_perfil() == Constante.PERFIL_COD_EJECUTIVO_HIPOTECARIO){
				this.flagBanPerEjecutivoHipo = true;
			}
		}else if(this.usuario.getId_negocio() == Constante.NEGOCIO_COD_VIP){
			this.flagVip = true;
		}

	}

	public void registrarLecturaComprendida() {
		try {
			CompresionLectura comprensionLectura = new CompresionLectura();
			comprensionLectura.setFec_registro(new Date());
			comprensionLectura.setId_negocio(this.usuario.getId_negocio());
			comprensionLectura.setIdusuario(this.usuario.getIdusuario());
			// comprensionLectura.setPeriodo(periodo);
			comprensionLectura.setTexto_pdf("Prestamos_de_Consumo_Fuvex_Mayo.pdf");
			this.comprensionLecturaService.insertarComprensionLectura(comprensionLectura);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* ###########-------setters y getters-------################## */

	public Usuario getUsuario() {
		return usuario;
	}

	public Perfil getPerfilUsuario() {
		return perfilUsuario;
	}

	public void setPerfilUsuario(Perfil perfilUsuario) {
		this.perfilUsuario = perfilUsuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Perfil> getListaPerfiles() {
		return listaPerfiles;
	}

	public void setListaPerfiles(List<Perfil> listaPerfiles) {
		this.listaPerfiles = listaPerfiles;
	}

	public List<Sistema> getListaModulos() {
		return listaModulos;
	}

	public void setListaModulos(List<Sistema> listaModulos) {
		this.listaModulos = listaModulos;
	}

	public List<Menu> getListaMenu() {
		return listaMenu;
	}

	public void setListaMenu(List<Menu> listaMenu) {
		this.listaMenu = listaMenu;
	}

	public MenuModel getModel() {
		return model;
	}

	public void setModel(MenuModel model) {
		this.model = model;
	}

	public String getFechaAcceso() {
		return fechaAcceso;

	}

	public void setFechaAcceso(String fechaAcceso) {
		this.fechaAcceso = fechaAcceso;
	}

	public MenuModel getModelSalir() {
		return modelSalir;
	}

	public void setModelSalir(MenuModel modelSalir) {
		this.modelSalir = modelSalir;
	}

	public String getNuevoPass() {
		return nuevoPass;
	}

	public String getNuevoPassRe() {
		return nuevoPassRe;
	}

	public void setNuevoPass(String nuevoPass) {
		this.nuevoPass = nuevoPass;
	}

	public void setNuevoPassRe(String nuevoPassRe) {
		this.nuevoPassRe = nuevoPassRe;
	}

	public int getTiempo_cambio_password() {
		return tiempo_cambio_password;
	}

	public void setTiempo_cambio_password(int tiempo_cambio_password) {
		this.tiempo_cambio_password = tiempo_cambio_password;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getLoginUsuario() {
		return loginUsuario;
	}

	public void setLoginUsuario(String loginUsuario) {
		this.loginUsuario = loginUsuario;
	}

	public String getCentroAtencion() {
		return centroAtencion;
	}

	public void setCentroAtencion(String centroAtencion) {
		this.centroAtencion = centroAtencion;
	}

	public String getNegocio() {
		return negocio;
	}

	public void setNegocio(String negocio) {
		this.negocio = negocio;
	}

	public Parametro getParametro() {
		return parametro;
	}

	public void setParametro(Parametro parametro) {
		this.parametro = parametro;
	}

	public boolean isFlagPyme() {
		return flagPyme;
	}

	public boolean isFlagEjecutivo() {
		return flagEjecutivo;
	}

	public boolean isFlagSupervisor() {
		return flagSupervisor;
	}

	public boolean isFlagPymeEjecutivo() {
		return flagPymeEjecutivo;
	}

	public boolean isFlagPymeSupervisor() {
		return flagPymeSupervisor;
	}

	public boolean isFlagCoordinador() {
		return flagCoordinador;
	}

	public boolean isFlagPymeCoordinador() {
		return flagPymeCoordinador;
	}

	public boolean isFlagBanPer() {
		return flagBanPer;
	}

	public boolean isFlagBanPerEjecutivo() {
		return flagBanPerEjecutivo;
	}

	public boolean isFlagPHSupervisor() {
		return flagPHSupervisor;
	}

	public void setFlagPHSupervisor(boolean flagPHSupervisor) {
		this.flagPHSupervisor = flagPHSupervisor;
	}

	public boolean isFlagPymeBack() {
		return flagPymeBack;
	}

	public void setFlagPymeBack(boolean flagPymeBack) {
		this.flagPymeBack = flagPymeBack;
	}

	public void setFlagPyme(boolean flagPyme) {
		this.flagPyme = flagPyme;
	}

	public StreamedContent getFile() {
		return file;
	}

	public void setFile(StreamedContent file) {
		this.file = file;
	}

	public String getPath_pdf() {
		return path_pdf;
	}

	public void setPath_pdf(String path_pdf) {
		this.path_pdf = path_pdf;
	}

	public Boolean getParametro_lavado_activo() {
		return parametro_lavado_activo;
	}

	public void setParametro_lavado_activo(Boolean parametro_lavado_activo) {
		this.parametro_lavado_activo = parametro_lavado_activo;
	}

	public Boolean getPrimeraLectura() {
		return primeraLectura;
	}

	public void setPrimeraLectura(Boolean primeraLectura) {
		this.primeraLectura = primeraLectura;
	}

	public boolean isFlagBanPerBack() {
		return flagBanPerBack;
	}

	public boolean isFlagBanPerSupervisorHipo() {
		return flagBanPerSupervisorHipo;
	}

	public boolean isFlagBanPerEjecutivoHipo() {
		return flagBanPerEjecutivoHipo;
	}

	public void setFlagBanPerEjecutivoHipo(boolean flagBanPerEjecutivoHipo) {
		this.flagBanPerEjecutivoHipo = flagBanPerEjecutivoHipo;
	}

	public boolean isFlagVip() {
		return flagVip;
	}

	public void setFlagVip(boolean flagVip) {
		this.flagVip = flagVip;
	}

	public boolean isFlagBanPerCoordinador() {
		return flagBanPerCoordinador;
	}

	public void setFlagBanPerCoordinador(boolean flagBanPerCoordinador) {
		this.flagBanPerCoordinador = flagBanPerCoordinador;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public boolean isFlagConcesionario() {
		return flagConcesionario;
	}

	public void setFlagConcesionario(boolean flagConcesionario) {
		this.flagConcesionario = flagConcesionario;
	}

	public boolean isFlagSupervisorCon() {
		return flagSupervisorCon;
	}

	public void setFlagSupervisorCon(boolean flagSupervisorCon) {
		this.flagSupervisorCon = flagSupervisorCon;
	}

	public boolean isFlagVendedorCon() {
		return flagVendedorCon;
	}

	public void setFlagVendedorCon(boolean flagVendedorCon) {
		this.flagVendedorCon = flagVendedorCon;
	}

	public String getOficina_nombre() {
		return oficina_nombre;
	}

	public void setOficina_nombre(String oficina_nombre) {
		this.oficina_nombre = oficina_nombre;
	}
	
	
	
}